package com.victorgarciarubio.idea_rating_api.dtos.requests;

import com.victorgarciarubio.idea_rating_api.models.DatabaseModel;
import org.hibernate.dialect.Database;

public interface DtoRequest {

    static DatabaseModel toEntity(DtoRequest dtoRequest){
        return null;
    }
}
