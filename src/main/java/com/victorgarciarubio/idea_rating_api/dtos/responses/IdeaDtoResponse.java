package com.victorgarciarubio.idea_rating_api.dtos.responses;

import com.victorgarciarubio.idea_rating_api.dtos.requests.EvaluationSentenceDto;
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

    private List<EvaluationSentenceDto> evaluationSentences;

    private Double rating;

    
    public static IdeaDtoResponse fromEntity(Idea idea) {

        return IdeaDtoResponse.builder()
                .id(idea.getId())
                .title(idea.getTitle())
                .username(idea.getUser().getUsername())
                .evaluationSentences(
                        idea.getEvaluationSentenceList().stream()
                                .map(EvaluationSentenceDto::fromEntity)
                                .collect(Collectors.toList())
                )
                .rating(idea.computeRating())
                .build();


    }
}
