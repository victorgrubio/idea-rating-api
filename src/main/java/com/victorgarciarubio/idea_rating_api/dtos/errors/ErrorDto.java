package com.victorgarciarubio.idea_rating_api.dtos.errors;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Holds error code, error message and related error messages of an error")
public class ErrorDto {

    @Schema(name="Http error code", required = true)
    private Integer httpCode;

    @Schema(name="The error code", required = true)
    private Integer code;

    @Schema(name="The error message")
    private String message;

    @Schema(name = "The input fields related to the error, if any.")
    List<String> errors = new ArrayList<>();
}
