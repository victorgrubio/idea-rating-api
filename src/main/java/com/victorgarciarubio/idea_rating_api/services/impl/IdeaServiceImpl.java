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
import java.util.Objects;
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
        manageErrors(ideaDto);
        return userRepository.findById(username).map(user -> {
            Idea idea = createIdea(ideaDto, user);
            return IdeaDtoResponse.fromEntity(idea);
        }).orElseThrow(
                () -> new EntityNotFoundException("User not found", ErrorCodes.USER_NOT_FOUND)
        );
    }

    private Idea createIdea(IdeaDtoRequest ideaDto, User user){
        Idea idea = ideaRepository.save(IdeaDtoRequest.toEntity(ideaDto, user));
        List<EvaluationSentence> evaluationSentenceList = ideaDto.getEvaluationSentences()
                .stream().map(sentence ->
                        EvaluationSentenceDtoRequest.toEntity(sentence, idea)
                ).collect(Collectors.toList());
        evaluationSentenceRepository.saveAll(evaluationSentenceList);
        idea.setEvaluationSentenceList(evaluationSentenceList);
        return idea;
    }

    private void manageErrors(IdeaDtoRequest ideaDtoRequest){
        List<String> errors = IdeaDtoValidator.validate(ideaDtoRequest);
        if(!errors.isEmpty()){
            log.error("Idea is not valid {}", ideaDtoRequest);
            throw new InvalidEntityException("Idea is not valid", ErrorCodes.IDEA_NOT_VALID, errors);
        }
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
        manageErrors(ideaDto);
        return userRepository.findById(username).map(user -> {
            Idea idea = updateIdea(ideaId, user, ideaDto);
            return IdeaDtoResponse.fromEntity(idea);
        }).orElseThrow(() -> new EntityNotFoundException("User not found", ErrorCodes.USER_NOT_FOUND));
    }

    private Idea updateIdea(Long ideaId, User user, IdeaDtoRequest ideaDto){
        Idea idea = IdeaDtoRequest.toEntity(ideaDto, user);
        List<EvaluationSentence> evaluationSentenceList = ideaDto.getEvaluationSentences()
                .stream()
                .filter(Objects::nonNull)
                .map(sentence -> EvaluationSentenceDtoRequest.toEntity(sentence, idea))
                .collect(Collectors.toList());
        idea.setId(ideaId);
        idea.setEvaluationSentenceList(new ArrayList<>());
        ideaRepository.save(idea);
        evaluationSentenceRepository.saveAll(evaluationSentenceList);
        idea.setEvaluationSentenceList(evaluationSentenceList);
        return idea;
    }

    @Override
    public void delete(Long ideaId, String username) {
        if (checkNullId(ideaId)) return;
        ideaRepository.deleteIdeaByIdAndUserUsername(ideaId, username);
    }

    @Override
    public void vote(String creatorUsername, Long ideaId, IdeaVoteDtoRequest ideaVoteDto) {
        if (checkNullId(ideaId)) return;

        User user = userRepository.findById(ideaVoteDto.getUsername())
                .orElseGet(()->createNewUser(ideaVoteDto));
        List<UserIdeaEvaluation> userIdeaEvaluationList = ideaVoteDto.getSentenceVoteIdList()
        .stream()
        .map(sentenceId -> evaluationSentenceRepository.findById(sentenceId).map(sentence ->
                createIdeaEvaluation(user, sentence)
        ).orElseThrow(
                () -> new EntityNotFoundException("Sentence not found", ErrorCodes.EVALUATION_SENTENCE_NOT_FOUND))
        ).collect(Collectors.toList());
        userIdeaEvaluationRepository.saveAll(userIdeaEvaluationList);
    }


    private User createNewUser(IdeaVoteDtoRequest ideaVoteDtoRequest){
        User newUser = new User();
        newUser.setUsername(ideaVoteDtoRequest.getUsername());
        return userRepository.save(newUser);
    }

    private UserIdeaEvaluation createIdeaEvaluation(User user, EvaluationSentence sentence){
        UserIdeaEvaluation userIdeaEvaluation = new UserIdeaEvaluation();
        userIdeaEvaluation.setUser(user);
        userIdeaEvaluation.setEvaluationSentence(sentence);
        return userIdeaEvaluation;
    }

    @Override
    public List<EvaluationWeight> findAllSentenceWeights() {
        return evaluationWeightRepository.findAll();
    }

}
