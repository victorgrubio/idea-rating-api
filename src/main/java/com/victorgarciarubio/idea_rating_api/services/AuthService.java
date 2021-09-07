package com.victorgarciarubio.idea_rating_api.services;

import com.victorgarciarubio.idea_rating_api.dtos.requests.UserDtoRequest;
import com.victorgarciarubio.idea_rating_api.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

public interface AuthService {

    User login(UserDtoRequest userDtoRequest);
}
