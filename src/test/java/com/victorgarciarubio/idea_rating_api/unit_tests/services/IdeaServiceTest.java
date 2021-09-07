package com.victorgarciarubio.idea_rating_api.unit_tests.services;

import com.victorgarciarubio.idea_rating_api.dtos.responses.IdeaDtoResponse;
import com.victorgarciarubio.idea_rating_api.models.EvaluationSentence;
import com.victorgarciarubio.idea_rating_api.models.Idea;
import com.victorgarciarubio.idea_rating_api.models.User;
import com.victorgarciarubio.idea_rating_api.repositories.*;
import com.victorgarciarubio.idea_rating_api.services.impl.IdeaServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;


@WebMvcTest(IdeaServiceImpl.class)
class IdeaServiceTest {

    @Autowired
    private IdeaServiceImpl ideaService;

    @MockBean
    private EvaluationSentenceRepository evaluationSentenceRepository;

    @MockBean
    private EvaluationWeightRepository evaluationWeightRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserIdeaEvaluationRepository userIdeaEvaluationRepository;

    @MockBean
    private IdeaRepository ideaRepository;

    @Test
    void findAllShouldReturnIdeaList() {

        // Mocks repository response
        List<Idea> ideaList = getMockIdeaList();
        when(ideaRepository.findAll()).thenReturn(ideaList);

        assert Objects.equals(
                this.ideaService.findAll(),
                getMockIdeaList().stream().map(IdeaDtoResponse::fromEntity)
                        .collect(Collectors.toList())
        );
    }

    private List<Idea> getMockIdeaList(){
        List<Idea> ideaList = new ArrayList<>();
        Idea idea = getMockIdea();
        ideaList.add(idea);
        return ideaList;
    }

    private Idea getMockIdea() {
        Idea idea = new Idea();
        idea.setTitle("Title");
        idea.setDescription("Description");
        User user = new User();
        user.setUsername("test");
        idea.setUser(user);
        idea.setEvaluationSentenceList(new ArrayList<EvaluationSentence>());
        return idea;
    }
}
