package com.victorgarciarubio.idea_rating_api;

import com.victorgarciarubio.idea_rating_api.controllers.IdeaController;
import com.victorgarciarubio.idea_rating_api.dtos.responses.IdeaDtoResponse;
import com.victorgarciarubio.idea_rating_api.models.Idea;
import com.victorgarciarubio.idea_rating_api.services.impl.IdeaServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(IdeaController.class)
public class IdeaServiceImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IdeaServiceImpl service;

    @Test
    public void getByIdShouldReturnIdea() throws Exception {
        Long ideaId = 1L;
        Idea testIdea = new Idea();
        testIdea.setId(1L);
        testIdea.setTitle("Example");
        testIdea.setDescription("Example");
        when(service.findById(ideaId)).thenReturn(IdeaDtoResponse.fromEntity(testIdea));

        this.mockMvc.perform(get("/victorgarciar/ideas/1")).andDo(print()).andExpect(status().isOk());
    }

}
