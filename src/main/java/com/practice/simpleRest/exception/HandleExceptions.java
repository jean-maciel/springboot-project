package com.practice.simpleRest.exception;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
@NoArgsConstructor
public class HandleExceptions extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> defaultException(final Exception exception, final WebRequest request) {
        log.error("Unexpected error", exception);
        return new ResponseEntity<>(buildExceptionResponse("Unexpected Error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EmailAlreadyTakenException.class)
    public final ResponseEntity<ExceptionResponse> emailAlreadyTakenException(final Exception exception, final WebRequest request) {
        return new ResponseEntity<>(buildExceptionResponse("Email already taken"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StudentIdDoesNotExistException.class)
    public final ResponseEntity<ExceptionResponse> studentIdDoesNotExistException(final Exception exception, final WebRequest request) {
        return new ResponseEntity<>(buildExceptionResponse("Student not found"), HttpStatus.NOT_FOUND);
    }

    private ExceptionResponse buildExceptionResponse(String errorMessage) {
        return ExceptionResponse.builder().message(errorMessage).build();
    }
}
