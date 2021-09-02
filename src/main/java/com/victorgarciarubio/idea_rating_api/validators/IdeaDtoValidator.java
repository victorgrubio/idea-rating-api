package com.victorgarciarubio.idea_rating_api.validators;

import com.victorgarciarubio.idea_rating_api.dtos.requests.DtoRequest;
import com.victorgarciarubio.idea_rating_api.dtos.requests.IdeaDtoRequest;

import java.util.ArrayList;
import java.util.List;

public class IdeaDtoValidator implements DtoValidator{

    // Method overloading / hiding
    public static List<String> validate(IdeaDtoRequest ideaDtoRequest){
        List<String> errors = new ArrayList<>();
        return errors;
    }
}
