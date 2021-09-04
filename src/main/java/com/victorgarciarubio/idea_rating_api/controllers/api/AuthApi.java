package com.victorgarciarubio.idea_rating_api.controllers.api;

import com.victorgarciarubio.idea_rating_api.dtos.requests.UserDtoRequest;
import com.victorgarciarubio.idea_rating_api.dtos.responses.IdeaDtoResponse;
import com.victorgarciarubio.idea_rating_api.models.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Tag(name="auth")
public interface AuthApi {

    /**
     * POST /auth/login - Login user
     *
     * @param userDto user
     * @return logged user
     */
    @PostMapping(
            value = "/auth/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Login user", description = "Logs user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The logged user")
    })
    ResponseEntity<User> login(
            @Parameter(name = "user", required = true)
            @RequestBody UserDtoRequest userDto
    );
}