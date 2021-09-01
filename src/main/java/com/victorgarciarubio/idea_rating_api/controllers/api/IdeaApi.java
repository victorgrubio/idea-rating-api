package com.victorgarciarubio.idea_rating_api.controllers.api;

import com.victorgarciarubio.idea_rating_api.dtos.requests.IdeaDtoRequest;
import com.victorgarciarubio.idea_rating_api.dtos.requests.IdeaVoteDtoRequest;
import com.victorgarciarubio.idea_rating_api.dtos.responses.IdeaDtoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="ideas")
public interface IdeaApi {

    /**
     * GET /ideas - List all ideas
     *
     * @return list of user ideas
     */
    @GetMapping(
            value = "/ideas",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Get user ideas", description = "Get a list of all ideas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all ideas.")
    })
    ResponseEntity<List<IdeaDtoResponse>> getAllIdeas(
    );

    /**
     * GET /{userId}/ideas - List user ideas
     *
     * @param userId userId
     * @return list of user ideas
     */
    @GetMapping(
            value = "/{userId}/ideas",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Get user ideas", description = "Get a list of user ideas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The list of user ideas.")
    })
    ResponseEntity<List<IdeaDtoResponse>> getUserIdeaList(
            @Parameter(name = "UserId", required = true)
            @PathVariable String userId
    );

    /**
     * POST /{userId}/ideas - Creates new idea
     *
     * @param userId userId
     * @param ideaDto new idea
     * @return created idea
     */
    @PostMapping(
            value = "/{userId}/ideas",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Create Idea", description = "Creates a new idea")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The newly created idea.")
    })
    ResponseEntity<IdeaDtoResponse> createIdea(
            @Parameter(name = "Idea", required = true)
            @RequestBody IdeaDtoRequest ideaDto,
            @Parameter(name = "UserId", required = true)
            @PathVariable String userId
    );


    /**
     * GET /{userId}/ideas/{ideaId} - Get user idea
     *
     * @param userId user id
     * @param ideaId idea id
     * @return user idea
     */
    @GetMapping(
            value = "/{userId}/ideas/{ideaId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Get user idea by id", description = "Get user idea by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The user idea.")
    })
    ResponseEntity<IdeaDtoResponse> getUserIdea(
            @Parameter(name = "UserId", required = true)
            @PathVariable String userId,
            @Parameter(name = "IdeaId", required = true)
            @PathVariable Long ideaId
    );

    /**
     * PUT /{userId}/ideas/{ideaId} - Updates user idea
     *
     * @param userId user id
     * @param ideaDto updated idea
     * @param ideaId idea id
     * @return user idea
     */
    @PutMapping(
            value = "/{userId}/ideas/{ideaId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Updates user idea by id", description = "Updates user idea by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The user updated idea")
    })
    ResponseEntity<IdeaDtoResponse> updateUserIdea(
            @Parameter(name = "UserId", required = true)
            @PathVariable String userId,
            @Parameter(name = "IdeaId", required = true)
            @PathVariable Long ideaId,
            @Parameter(name = "Idea", required = true)
            @RequestBody IdeaDtoRequest ideaDto
    );

    /**
     * DELETE /{userId}/ideas/{ideaId} - Deletes user idea
     *
     * @param userId user id
     * @param ideaId idea id
     * @return OK
     */
    @DeleteMapping(
            value = "/{userId}/ideas/{ideaId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Deletes user idea by id", description = "Deletes user idea")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    ResponseEntity<?> deleteUserIdea(
            @Parameter(name = "UserId", required = true)
            @PathVariable String userId,
            @Parameter(name = "IdeaId", required = true)
            @PathVariable Long ideaId
    );

    /**
     * POST /{userId}/ideas/{ideaId}/votes - Votes for a user idea
     *
     * @param userId user id
     * @param ideaId idea id
     * @return OK
     */
    @PostMapping(
            value = "/{userId}/ideas/{ideaId}/votes",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Votes for a user Idea", description = "Send votes for user idea")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    ResponseEntity<?> voteUserIdea(
            @Parameter(name = "UserId", required = true)
            @PathVariable String userId,
            @Parameter(name = "IdeaId", required = true)
            @PathVariable Long ideaId,
            @Parameter(name = "Votes", required = true)
            @PathVariable IdeaVoteDtoRequest ideaVotes
    );


}
