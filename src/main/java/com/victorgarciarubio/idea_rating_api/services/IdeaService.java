package com.victorgarciarubio.idea_rating_api.services;

import com.victorgarciarubio.idea_rating_api.dtos.requests.IdeaDtoRequest;
import com.victorgarciarubio.idea_rating_api.dtos.requests.IdeaVoteDtoRequest;
import com.victorgarciarubio.idea_rating_api.dtos.responses.IdeaDtoResponse;

import java.util.List;

public interface IdeaService {

    List<IdeaDtoResponse> findAll();

    List<IdeaDtoResponse> findAllByUserId(String userId);

    IdeaDtoResponse save(IdeaDtoRequest ideaDto, String userId);

    IdeaDtoResponse findById(Long ideaId);

    IdeaDtoResponse update(Long ideaId, String userId, IdeaDtoRequest ideaDto);

    void delete(Long ideaId, String userId);

    void vote(String voterId, Long ideaId, IdeaVoteDtoRequest ideaVoteDto);
}
