package com.victorgarciarubio.idea_rating_api.services;

import com.victorgarciarubio.idea_rating_api.dtos.requests.IdeaDtoRequest;
import com.victorgarciarubio.idea_rating_api.dtos.requests.IdeaVoteDtoRequest;
import com.victorgarciarubio.idea_rating_api.dtos.responses.IdeaDtoResponse;
import com.victorgarciarubio.idea_rating_api.models.EvaluationWeight;

import java.util.List;

public interface IdeaService {

    List<IdeaDtoResponse> findAll();

    List<IdeaDtoResponse> findAllByUserId(String username);

    IdeaDtoResponse save(IdeaDtoRequest ideaDto, String username);

    IdeaDtoResponse findById(Long ideaId);

    IdeaDtoResponse update(Long ideaId, String username, IdeaDtoRequest ideaDto);

    void delete(Long ideaId, String username);

    void vote(String voterId, Long ideaId, IdeaVoteDtoRequest ideaVoteDto);

    List<EvaluationWeight> findAllSentenceWeights();
}
