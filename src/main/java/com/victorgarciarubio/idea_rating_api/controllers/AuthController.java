package com.victorgarciarubio.idea_rating_api.controllers;


import com.victorgarciarubio.idea_rating_api.config.ConfigConstants;
import com.victorgarciarubio.idea_rating_api.controllers.api.AuthApi;
import com.victorgarciarubio.idea_rating_api.dtos.requests.UserDtoRequest;
import com.victorgarciarubio.idea_rating_api.models.User;
import com.victorgarciarubio.idea_rating_api.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = ConfigConstants.ORIGINS, maxAge = ConfigConstants.MAX_AGE)
public class AuthController implements AuthApi {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    public ResponseEntity<User> login(UserDtoRequest userDtoRequest){
        return new ResponseEntity<>(this.authService.login(userDtoRequest), HttpStatus.OK);
    }
}
