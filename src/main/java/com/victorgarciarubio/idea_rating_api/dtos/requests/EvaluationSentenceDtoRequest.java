package com.victorgarciarubio.idea_rating_api.dtos.requests;

import com.victorgarciarubio.idea_rating_api.models.EvaluationSentence;
import com.victorgarciarubio.idea_rating_api.models.EvaluationWeight;
import com.victorgarciarubio.idea_rating_api.models.Idea;
import com.victorgarciarubio.idea_rating_api.models.SentenceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluationSentenceDtoRequest implements DtoRequest {

    private Optional<Long> id;

    private String content;

    private String type;

    private EvaluationWeight weight;

    public static EvaluationSentence toEntity(EvaluationSentenceDtoRequest evaluationSentenceDto, Idea idea) {

        EvaluationSentence sentence = new EvaluationSentence();
        if (evaluationSentenceDto.getId().isPresent()){
            sentence.setId(evaluationSentenceDto.getId().get());
        }
        sentence.setContent(evaluationSentenceDto.getContent());
        sentence.setType(evaluationSentenceDto.getType());
        sentence.setIdea(idea);
        sentence.setWeight(evaluationSentenceDto.getWeight());
        return sentence;
    }

}
