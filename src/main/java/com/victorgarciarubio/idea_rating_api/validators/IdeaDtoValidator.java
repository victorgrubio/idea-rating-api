package com.victorgarciarubio.idea_rating_api.validators;

import com.victorgarciarubio.idea_rating_api.dtos.requests.DtoRequest;
import com.victorgarciarubio.idea_rating_api.dtos.requests.EvaluationSentenceDtoRequest;
import com.victorgarciarubio.idea_rating_api.dtos.requests.IdeaDtoRequest;
import org.apache.commons.lang3.StringUtils;

import javax.el.EvaluationListener;
import java.util.ArrayList;
import java.util.List;

public class IdeaDtoValidator implements DtoValidator{

    // Method overloading / hiding
    public static List<String> validate(IdeaDtoRequest ideaDtoRequest){
        List<String> errors = new ArrayList<>();

        if (ideaDtoRequest == null){
            errors.add("Please add the title");
            errors.add("Please add the description");
            errors.add("Please add some pro/con sentences");
        }

        if (StringUtils.isEmpty(ideaDtoRequest.getTitle())) {
            errors.add("Please add the title");
        }
        if (StringUtils.isEmpty(ideaDtoRequest.getDescription())) {
            errors.add("Please add the description");
        }

        List<EvaluationSentenceDtoRequest> evaluationSentenceDtoList = ideaDtoRequest.getEvaluationSentences();

        evaluationSentenceDtoList.stream().map(
                evaluationSentenceDtoRequest -> {
                    errors.addAll(EvaluationSentenceValidator.validate(evaluationSentenceDtoRequest));
                    return evaluationSentenceDtoRequest;
                }
        );

        return errors;
    }
}
