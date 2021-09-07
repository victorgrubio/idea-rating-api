package com.victorgarciarubio.idea_rating_api.unit_tests.dtos;

import com.victorgarciarubio.idea_rating_api.dtos.requests.IdeaDtoRequest;
import com.victorgarciarubio.idea_rating_api.models.Idea;
import com.victorgarciarubio.idea_rating_api.models.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


class IdeaDtoRequestTest {


    @Test
    void ideaDtoRequestToEntityShouldReturnIdea() {
        // Tests if ideaDtoRequest is converted to idea
        IdeaDtoRequest ideaDtoRequest = new IdeaDtoRequest();
        ideaDtoRequest.setTitle("Title");
        ideaDtoRequest.setDescription("Description");
        ideaDtoRequest.setEvaluationSentences(new ArrayList<>());
        User user = new User();
        user.setUsername("Username");

        assert IdeaDtoRequest.toEntity(ideaDtoRequest, user).getClass() == Idea.class;
    }
}
