package com.victorgarciarubio.idea_rating_api.services;

import com.victorgarciarubio.idea_rating_api.dtos.requests.IdeaDtoRequest;
import com.victorgarciarubio.idea_rating_api.dtos.responses.IdeaDtoResponse;

import java.util.List;

public interface IdeaService {

    List<IdeaDtoResponse> findAll();

    List<IdeaDtoResponse> findAllByUserId(String userId);

    IdeaDtoResponse save(IdeaDtoRequest ideaDto);

    IdeaDtoResponse findById(Long id);

    IdeaDtoResponse update(Long id, IdeaDtoRequest ideaDto);


    void delete(Long id);
}
