package com.victorgarciarubio.idea_rating_api.validators;

import com.victorgarciarubio.idea_rating_api.dtos.requests.DtoRequest;
import com.victorgarciarubio.idea_rating_api.dtos.requests.IdeaDtoRequest;

import java.util.ArrayList;
import java.util.List;

public interface DtoValidator {

    public static List<String> validate(DtoRequest dtoRequest){
        List<String> errors = new ArrayList<>();
        return errors;
    }
}
