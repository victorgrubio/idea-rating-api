package com.victorgarciarubio.idea_rating_api.dtos.requests;

import com.victorgarciarubio.idea_rating_api.models.DatabaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IdeaVoteDtoRequest implements DtoRequest {

    private String username;
    private List<Long> sentenceVoteIdList;

    public static DatabaseModel toEntity(DtoRequest dtoRequest) {
        return null;
    }
}
