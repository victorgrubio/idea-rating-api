package com.victorgarciarubio.idea_rating_api.dtos.requests;

import com.victorgarciarubio.idea_rating_api.models.EvaluationSentence;
import com.victorgarciarubio.idea_rating_api.models.EvaluationWeight;
import com.victorgarciarubio.idea_rating_api.models.Idea;
import com.victorgarciarubio.idea_rating_api.models.SentenceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluationSentenceDto implements DtoRequest {

    private String content;

    private String type;

    private EvaluationWeight weight;

    public static EvaluationSentence toEntity(EvaluationSentenceDto evaluationSentenceDto, Idea idea){

        EvaluationSentence sentence = new EvaluationSentence();
        sentence.setContent(evaluationSentenceDto.getContent());
        sentence.setType(evaluationSentenceDto.getType());
        sentence.setIdea(idea);
        sentence.setWeight(evaluationSentenceDto.getWeight());
        return sentence;
    }

    public static EvaluationSentenceDto fromEntity(EvaluationSentence evaluationSentence){

        return EvaluationSentenceDto.builder()
                .content(evaluationSentence.getContent())
                .type(evaluationSentence.getType())
                .weight(evaluationSentence.getWeight())
                .build();
    }
}
