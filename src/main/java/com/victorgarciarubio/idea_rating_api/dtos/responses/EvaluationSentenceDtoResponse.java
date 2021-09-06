package com.victorgarciarubio.idea_rating_api.dtos.responses;

import com.victorgarciarubio.idea_rating_api.dtos.requests.DtoRequest;
import com.victorgarciarubio.idea_rating_api.models.EvaluationSentence;
import com.victorgarciarubio.idea_rating_api.models.EvaluationWeight;
import com.victorgarciarubio.idea_rating_api.models.Idea;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluationSentenceDtoResponse implements DtoRequest {

    private Long id;

    private String content;

    private String type;

    private EvaluationWeight weight;

    public static EvaluationSentenceDtoResponse fromEntity(EvaluationSentence evaluationSentence) {

        return EvaluationSentenceDtoResponse.builder()
                .id(evaluationSentence.getId())
                .content(evaluationSentence.getContent())
                .type(evaluationSentence.getType())
                .weight(evaluationSentence.getWeight())
                .build();
    }
}
