package com.victorgarciarubio.idea_rating_api.dtos.requests;

import com.victorgarciarubio.idea_rating_api.models.Idea;
import com.victorgarciarubio.idea_rating_api.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDtoRequest implements DtoRequest{

    private String username;

    public static User toEntity(UserDtoRequest userDtoRequest) {

        User user = new User();
        user.setUsername(userDtoRequest.getUsername());
        return user;
    }

}
