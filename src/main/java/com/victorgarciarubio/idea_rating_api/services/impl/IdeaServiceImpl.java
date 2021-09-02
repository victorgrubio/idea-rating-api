package com.victorgarciarubio.idea_rating_api.services.impl;

import com.victorgarciarubio.idea_rating_api.dtos.requests.IdeaDtoRequest;
import com.victorgarciarubio.idea_rating_api.dtos.requests.IdeaVoteDtoRequest;
import com.victorgarciarubio.idea_rating_api.dtos.responses.DtoResponse;
import com.victorgarciarubio.idea_rating_api.dtos.responses.IdeaDtoResponse;
import com.victorgarciarubio.idea_rating_api.exceptions.EntityNotFoundException;
import com.victorgarciarubio.idea_rating_api.exceptions.ErrorCodes;
import com.victorgarciarubio.idea_rating_api.exceptions.InvalidEntityException;
import com.victorgarciarubio.idea_rating_api.models.Idea;
import com.victorgarciarubio.idea_rating_api.repositories.EvaluationSentenceRepository;
import com.victorgarciarubio.idea_rating_api.repositories.IdeaRepository;
import com.victorgarciarubio.idea_rating_api.repositories.UserRepository;
import com.victorgarciarubio.idea_rating_api.services.IdeaService;
import com.victorgarciarubio.idea_rating_api.validators.IdeaDtoValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class IdeaServiceImpl implements IdeaService {

    public final IdeaRepository ideaRepository;
    public final EvaluationSentenceRepository evaluationSentenceRepository;
    public final UserRepository userRepository;


    public IdeaServiceImpl(
            IdeaRepository ideaRepository,
            EvaluationSentenceRepository evaluationSentenceRepository,
            UserRepository userRepository
    ){
        this.ideaRepository = ideaRepository;
        this.evaluationSentenceRepository = evaluationSentenceRepository;
        this.userRepository = userRepository;
    }
    @Override
    public List<IdeaDtoResponse> findAll() {
        return this.ideaRepository.findAll().stream()
                .map(IdeaDtoResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<IdeaDtoResponse> findAllByUserId(String username) {
        return this.ideaRepository.findIdeasByUser_Username(username).stream()
                .map(IdeaDtoResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public IdeaDtoResponse save(IdeaDtoRequest ideaDto, String username) {
        List<String> errors = IdeaDtoValidator.validate(ideaDto);
        if (errors.isEmpty()){
            Idea idea = ideaRepository.save(IdeaDtoRequest.toEntity(ideaDto, username));
            return IdeaDtoResponse.fromEntity(idea);
        }
        log.error("Idea is not valid {}", ideaDto);
        throw new InvalidEntityException("Idea is not valid", ErrorCodes.IDEA_NOT_VALID, errors);
    }

    @Override
    public IdeaDtoResponse findById(Long ideaId) {
        if (checkNullId(ideaId)){
            return null;
        };
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
        };
        List<String> errors = IdeaDtoValidator.validate(ideaDto);
        if (errors.isEmpty()){
            Idea idea = IdeaDtoRequest.toEntity(ideaDto, username);
            idea.setId(ideaId);
            return IdeaDtoResponse.fromEntity(idea);
        }
        log.error("Idea is not valid {}", ideaDto);
        throw new InvalidEntityException("Idea is not valid", ErrorCodes.IDEA_NOT_VALID, errors);
    }

    @Override
    public void delete(Long ideaId, String username) {
        if (checkNullId(ideaId)){
            return;
        };
        ideaRepository.deleteIdeaByIdAndUser_Username(ideaId, username);

    }

    @Override
    public void vote(String voterId, Long ideaId, IdeaVoteDtoRequest ideaVoteDto) {
        if (checkNullId(ideaId)){
            return;
        };
        if (userRepository.existsUserByUsername(voterId) == null){
            return;
        }

    }

}
