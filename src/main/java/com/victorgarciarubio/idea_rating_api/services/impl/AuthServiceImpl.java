package com.victorgarciarubio.idea_rating_api.services.impl;

import com.victorgarciarubio.idea_rating_api.dtos.requests.UserDtoRequest;
import com.victorgarciarubio.idea_rating_api.models.User;
import com.victorgarciarubio.idea_rating_api.repositories.UserRepository;
import com.victorgarciarubio.idea_rating_api.services.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public User login(UserDtoRequest userDtoRequest) {
        Optional<User> user = this.userRepository.findById(userDtoRequest.getUsername());
        if (user.isPresent()){
            return user.get();
        } else {
            User newUser = UserDtoRequest.toEntity(userDtoRequest);
            this.userRepository.save(newUser);
            return newUser;
        }

    }
}
