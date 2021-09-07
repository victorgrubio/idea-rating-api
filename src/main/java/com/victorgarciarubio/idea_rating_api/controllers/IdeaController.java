package com.victorgarciarubio.idea_rating_api.controllers;

import com.victorgarciarubio.idea_rating_api.config.ConfigConstants;
import com.victorgarciarubio.idea_rating_api.controllers.api.IdeaApi;
import com.victorgarciarubio.idea_rating_api.dtos.requests.IdeaDtoRequest;
import com.victorgarciarubio.idea_rating_api.dtos.requests.IdeaVoteDtoRequest;
import com.victorgarciarubio.idea_rating_api.dtos.responses.IdeaDtoResponse;
import com.victorgarciarubio.idea_rating_api.models.EvaluationWeight;
import com.victorgarciarubio.idea_rating_api.services.IdeaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@CrossOrigin(
        origins = {
                "http://localhost:4200", "https://idea-rating-frontend.vercel.app"
        },
        maxAge = ConfigConstants.MAX_AGE
)
public class IdeaController implements IdeaApi {

    private final IdeaService ideaService;

    public IdeaController(IdeaService ideaService) {
        this.ideaService = ideaService;
    }

    @Override
    public ResponseEntity<List<IdeaDtoResponse>> getAllIdeas() {
        return new ResponseEntity<>(ideaService.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<EvaluationWeight>> getIdeaSentenceWeights() {
        return new ResponseEntity<>(ideaService.findAllSentenceWeights(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<IdeaDtoResponse>> getUserIdeaList(String username) {

        return new ResponseEntity<>(ideaService.findAllByUserId(username), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<IdeaDtoResponse> createIdea(IdeaDtoRequest ideaDto, String username) {

        return new ResponseEntity<>(ideaService.save(ideaDto, username), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<IdeaDtoResponse> getUserIdea(String username, Long ideaId) {

        return new ResponseEntity<>(ideaService.findById(ideaId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<IdeaDtoResponse> updateUserIdea(String username, Long ideaId, IdeaDtoRequest ideaDto) {
        return new ResponseEntity<>(ideaService.update(ideaId, username, ideaDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteUserIdea(String username, Long ideaId) {

        ideaService.delete(ideaId, username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> voteUserIdea(String username, Long ideaId, IdeaVoteDtoRequest ideaVote) {
        ideaService.vote(username, ideaId, ideaVote);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
