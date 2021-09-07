package com.victorgarciarubio.idea_rating_api.services.impl;

import com.victorgarciarubio.idea_rating_api.dtos.requests.EvaluationSentenceDtoRequest;
import com.victorgarciarubio.idea_rating_api.dtos.requests.IdeaDtoRequest;
import com.victorgarciarubio.idea_rating_api.dtos.requests.IdeaVoteDtoRequest;
import com.victorgarciarubio.idea_rating_api.dtos.responses.IdeaDtoResponse;
import com.victorgarciarubio.idea_rating_api.exceptions.EntityNotFoundException;
import com.victorgarciarubio.idea_rating_api.exceptions.ErrorCodes;
import com.victorgarciarubio.idea_rating_api.exceptions.InvalidEntityException;
import com.victorgarciarubio.idea_rating_api.models.*;
import com.victorgarciarubio.idea_rating_api.repositories.*;
import com.victorgarciarubio.idea_rating_api.services.IdeaService;
import com.victorgarciarubio.idea_rating_api.validators.IdeaDtoValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class IdeaServiceImpl implements IdeaService {

    public final IdeaRepository ideaRepository;
    public final EvaluationSentenceRepository evaluationSentenceRepository;
    public final UserRepository userRepository;
    public final EvaluationWeightRepository evaluationWeightRepository;
    public final UserIdeaEvaluationRepository userIdeaEvaluationRepository;


    public IdeaServiceImpl(
            IdeaRepository ideaRepository,
            EvaluationSentenceRepository evaluationSentenceRepository,
            UserRepository userRepository,
            EvaluationWeightRepository evaluationWeightRepository,
            UserIdeaEvaluationRepository userIdeaEvaluationRepository
    ){
        this.ideaRepository = ideaRepository;
        this.evaluationSentenceRepository = evaluationSentenceRepository;
        this.userRepository = userRepository;
        this.evaluationWeightRepository = evaluationWeightRepository;
        this.userIdeaEvaluationRepository = userIdeaEvaluationRepository;
    }
    @Override
    public List<IdeaDtoResponse> findAll() {
        return this.ideaRepository.findAll().stream()
                .map(IdeaDtoResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<IdeaDtoResponse> findAllByUserId(String username) {
        return this.ideaRepository.findIdeasByUserUsername(username).stream()
                .map(IdeaDtoResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public IdeaDtoResponse save(IdeaDtoRequest ideaDto, String username) {
        List<String> errors = IdeaDtoValidator.validate(ideaDto);
        if (errors.isEmpty()) {
            Optional<User> user = userRepository.findById(username);
            if (user.isPresent()) {
                Idea idea = ideaRepository.save(IdeaDtoRequest.toEntity(ideaDto, user.get()));
                List<EvaluationSentence> evaluationSentenceList = new ArrayList<>();
                for (int i = 0; i < ideaDto.getEvaluationSentences().size(); i++) {
                    evaluationSentenceList.add(
                            EvaluationSentenceDtoRequest.toEntity(ideaDto.getEvaluationSentences().get(i), idea)
                    );
                }
                evaluationSentenceRepository.saveAll(evaluationSentenceList);
                idea.setEvaluationSentenceList(evaluationSentenceList);
                return IdeaDtoResponse.fromEntity(idea);
            }
        }
        log.error("Idea is not valid {}", ideaDto);
        throw new InvalidEntityException("Idea is not valid", ErrorCodes.IDEA_NOT_VALID, errors);
    }

    @Override
    public IdeaDtoResponse findById(Long ideaId) {
        if (checkNullId(ideaId)){
            return null;
        }
        return this.ideaRepository.findById(ideaId).map(IdeaDtoResponse::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Idea not found with id " + ideaId,
                        ErrorCodes.IDEA_NOT_FOUND));
    }

    private static boolean checkNullId(Long ideaId){
        if (ideaId == null){
            log.error("Idea id is null");
            return true;
        }
        return false;
    }

    @Override
    public IdeaDtoResponse update(Long ideaId, String username, IdeaDtoRequest ideaDto) {
        if (checkNullId(ideaId)){
            return null;
        }
        List<String> errors = IdeaDtoValidator.validate(ideaDto);
        if (errors.isEmpty()) {
            Optional<User> user = userRepository.findById(username);
            if (user.isPresent()) {
                Idea idea = IdeaDtoRequest.toEntity(ideaDto, user.get());
                List<EvaluationSentence> evaluationSentenceList = new ArrayList<>();
                List<EvaluationSentenceDtoRequest> evaluationSentenceDtoList = ideaDto.getEvaluationSentences();
                for (EvaluationSentenceDtoRequest evaluationSentenceDto : evaluationSentenceDtoList) {
                    if (evaluationSentenceDto == null) {
                        continue;
                    }
                    evaluationSentenceList.add(
                            EvaluationSentenceDtoRequest.toEntity(evaluationSentenceDto, idea)
                    );
                }
                idea.setId(ideaId);
                idea.setEvaluationSentenceList(new ArrayList<>());
                ideaRepository.save(idea);
                evaluationSentenceRepository.saveAll(evaluationSentenceList);
                idea.setEvaluationSentenceList(evaluationSentenceList);
                return IdeaDtoResponse.fromEntity(idea);
            }
            log.error("User not found {}", username);
            throw new EntityNotFoundException("User not found", ErrorCodes.USER_NOT_FOUND);
        }
        log.error("Idea is not valid {}", ideaDto);
        throw new InvalidEntityException("Idea is not valid", ErrorCodes.IDEA_NOT_VALID, errors);
    }

    @Override
    public void delete(Long ideaId, String username) {
        if (checkNullId(ideaId)){
            return;
        }
        ideaRepository.deleteIdeaByIdAndUserUsername(ideaId, username);

    }

    @Override
    public void vote(String creatorUsername, Long ideaId, IdeaVoteDtoRequest ideaVoteDto) {
        if (checkNullId(ideaId)) return;

        Optional<User> user = userRepository.findById(ideaVoteDto.getUsername());
        User voter = null;
        if (user.isEmpty()) {
            User newUser = new User();
            newUser.setUsername(ideaVoteDto.getUsername());
            userRepository.save(newUser);
            voter = newUser;
        } else {
            voter = user.get();
        }

        List<UserIdeaEvaluation> userIdeaEvaluationList = new ArrayList<>();

        for (Long sentenceId : ideaVoteDto.getSentenceVoteIdList()) {
            UserIdeaEvaluation userIdeaEvaluation = new UserIdeaEvaluation();
            Optional<EvaluationSentence> evaluationSentence = evaluationSentenceRepository.findById(sentenceId);
            if (evaluationSentence.isPresent()) {
                userIdeaEvaluation.setUser(voter);
                userIdeaEvaluation.setEvaluationSentence(evaluationSentence.get());
                userIdeaEvaluationList.add(userIdeaEvaluation);
            }
        }
        userIdeaEvaluationRepository.saveAll(userIdeaEvaluationList);
    }

    @Override
    public List<EvaluationWeight> findAllSentenceWeights() {
        return evaluationWeightRepository.findAll();
    }

}
