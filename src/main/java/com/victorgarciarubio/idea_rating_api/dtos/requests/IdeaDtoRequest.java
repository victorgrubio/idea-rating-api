package com.victorgarciarubio.idea_rating_api.dtos.requests;

import com.victorgarciarubio.idea_rating_api.models.EvaluationSentence;
import com.victorgarciarubio.idea_rating_api.models.Idea;
import com.victorgarciarubio.idea_rating_api.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IdeaDtoRequest implements DtoRequest{

    private String title;

    private String description;

    private List<EvaluationSentenceDtoRequest> evaluationSentences;


    public static Idea toEntity(IdeaDtoRequest ideaDto, User user) {

        Idea idea = new Idea();
        idea.setTitle(ideaDto.getTitle());
        idea.setDescription(ideaDto.getDescription());
        idea.setUser(user);
        return idea;
    }
}
