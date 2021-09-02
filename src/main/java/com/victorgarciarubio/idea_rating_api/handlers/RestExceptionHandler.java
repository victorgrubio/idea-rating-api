package com.victorgarciarubio.idea_rating_api.handlers;

import com.victorgarciarubio.idea_rating_api.dtos.errors.ErrorDto;
import com.victorgarciarubio.idea_rating_api.exceptions.EntityNotFoundException;
import com.victorgarciarubio.idea_rating_api.exceptions.ErrorCodes;
import com.victorgarciarubio.idea_rating_api.exceptions.InvalidEntityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> handleException(EntityNotFoundException exception){
        logger.error(exception);
        final HttpStatus notFound = HttpStatus.NOT_FOUND;
        final ErrorDto errorDto = generateErrorDto(exception, notFound, exception.getErrorCode());

        return new ResponseEntity<>(errorDto, notFound);
    }

    @ExceptionHandler(InvalidEntityException.class)
    public ResponseEntity<ErrorDto> handleException(InvalidEntityException exception){
        logger.error(exception);
        final HttpStatus badRequest = HttpStatus.NOT_FOUND;
        final ErrorDto errorDto = generateErrorDto(exception, badRequest, exception.getErrorCode());

        return new ResponseEntity<>(errorDto, badRequest);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ErrorDto> handleException(EmptyResultDataAccessException exception) {
        logger.error(exception);
        final HttpStatus notFound = HttpStatus.NOT_FOUND;
        final ErrorCodes errorCode = ErrorCodes.IDEA_NOT_VALID;
        final ErrorDto errorDto = generateErrorDto(exception, notFound, errorCode);

        return new ResponseEntity<>(errorDto, notFound);
    }

    private ErrorDto generateErrorDto(
            RuntimeException exception, HttpStatus status, ErrorCodes errorCode){
        return ErrorDto.builder()
                .code(errorCode.getCode())
                .httpCode(status.value())
                .message(exception.getMessage())
                .build();
    }

}
