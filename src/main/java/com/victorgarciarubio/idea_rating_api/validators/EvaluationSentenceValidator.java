package com.victorgarciarubio.idea_rating_api.validators;

import com.victorgarciarubio.idea_rating_api.dtos.requests.EvaluationSentenceDto;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EvaluationSentenceValidator implements DtoValidator {

    // Method overloading / hiding
    public static List<String> validate(EvaluationSentenceDto evaluationSentenceDto){
        List<String> errors = new ArrayList<>();

        if (evaluationSentenceDto == null){
            errors.add("Please add the content");
            errors.add("Please add the type");
            errors.add("Please add the weight");
        }

        if (StringUtils.isEmpty(evaluationSentenceDto.getContent())){
            errors.add("Please add the content");
        }

        if (ObjectUtils.isEmpty(evaluationSentenceDto.getWeight())){
            errors.add("Please add the weight");
        }

        return errors;
    }
}
