package com.victorgarciarubio.idea_rating_api.controllers;

import com.victorgarciarubio.idea_rating_api.controllers.api.IdeaApi;
import com.victorgarciarubio.idea_rating_api.dtos.requests.IdeaDtoRequest;
import com.victorgarciarubio.idea_rating_api.dtos.requests.IdeaVoteDtoRequest;
import com.victorgarciarubio.idea_rating_api.dtos.responses.IdeaDtoResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class IdeaController implements IdeaApi {
    @Override
    public ResponseEntity<List<IdeaDtoResponse>> getAllIdeas() {
        return null;
    }

    @Override
    public ResponseEntity<List<IdeaDtoResponse>> getUserIdeaList(String userId) {
        return null;
    }

    @Override
    public ResponseEntity<IdeaDtoResponse> createIdea(IdeaDtoRequest ideaDto, String userId) {
        return null;
    }

    @Override
    public ResponseEntity<IdeaDtoResponse> getUserIdea(String userId, Long ideaId) {
        return null;
    }

    @Override
    public ResponseEntity<IdeaDtoResponse> updateUserIdea(String userId, Long ideaId, IdeaDtoRequest ideaDto) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteUserIdea(String userId, Long ideaId) {
        return null;
    }

    @Override
    public ResponseEntity<?> voteUserIdea(String userId, Long ideaId, IdeaVoteDtoRequest ideaVotes) {
        return null;
    }
}
