package com.victorgarciarubio.idea_rating_api.dtos.responses;

import com.victorgarciarubio.idea_rating_api.models.DatabaseModel;
import com.victorgarciarubio.idea_rating_api.models.Idea;
import com.victorgarciarubio.idea_rating_api.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IdeaDtoResponse implements DtoResponse{

    private Long id;

    private String title;

    private String description;

    private String username;

    private List<EvaluationSentenceDtoResponse> evaluationSentenceList;

    private Float rating;

    
    public static IdeaDtoResponse fromEntity(Idea idea) {

        return IdeaDtoResponse.builder()
                .id(idea.getId())
                .title(idea.getTitle())
                .description(idea.getDescription())
                .username(idea.getUser().getUsername())
                .evaluationSentenceList(
                        idea.getEvaluationSentenceList().stream()
                                .map(EvaluationSentenceDtoResponse::fromEntity)
                                .collect(Collectors.toList())
                )
                .rating(idea.computeRating())
                .build();


    }
}
