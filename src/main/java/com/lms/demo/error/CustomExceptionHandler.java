package com.lms.demo.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@ResponseStatus
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {



    @ExceptionHandler(NotAnAdminException.class)
    public ResponseEntity<ErrorMessage> notAnAdminExceptionHandler(NotAnAdminException e) {
        ErrorMessage message = new ErrorMessage(HttpStatus.UNAUTHORIZED, e.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
    }

    @ExceptionHandler(IllegalPropertyValueException.class)
    public ResponseEntity<ErrorMessage> illegalPropertyValueExceptionHandler(IllegalPropertyValueException e) {
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_ACCEPTABLE, e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(message);
    }

    @ExceptionHandler(DuplicateEntityException.class)
    public ResponseEntity<ErrorMessage> duplicateEntityExceptionHandler(DuplicateEntityException e) {
        ErrorMessage message = new ErrorMessage(HttpStatus.CONFLICT, e.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> entityNotFoundExceptionHandler(EntityNotFoundException e) {
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(CopiesNotAvailableException.class)
    public ResponseEntity<ErrorMessage> copiesNotAvailableExceptionHandler(CopiesNotAvailableException e) {
        ErrorMessage message = new ErrorMessage(HttpStatus.CONFLICT, e.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }

    @ExceptionHandler(InvalidEntityException.class)
    public ResponseEntity<ErrorMessage> invalidEntityExceptionHandler(InvalidEntityException e) {
        ErrorMessage message = new ErrorMessage(HttpStatus.CONFLICT, e.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }

    @ExceptionHandler(BookAlreadyReturnedException.class)
    public ResponseEntity<ErrorMessage> bookAlreadyReturnedExceptionHandler(BookAlreadyReturnedException e) {
        ErrorMessage message = new ErrorMessage(HttpStatus.CONFLICT, e.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String errorField = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(errorField, errorMessage);
        });

        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
    }
}
