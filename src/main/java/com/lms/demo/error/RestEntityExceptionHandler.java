package com.lms.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotAnAdminException.class)
    public ResponseEntity<ErrorMessage> notAnAdminExceptionHandler(NotAnAdminException e, WebRequest req) {
        ErrorMessage message = new ErrorMessage(HttpStatus.UNAUTHORIZED, e.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
    }

    @ExceptionHandler(IllegalPropertyValueException.class)
    public ResponseEntity<ErrorMessage> illegalPropertyValueExceptionHandler(IllegalPropertyValueException e, WebRequest req) {
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_ACCEPTABLE, e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(message);
    }

    @ExceptionHandler(DuplicateEntityException.class)
    public ResponseEntity<ErrorMessage> duplicateEntityExceptionHandler(DuplicateEntityException e, WebRequest req) {
        ErrorMessage message = new ErrorMessage(HttpStatus.CONFLICT, e.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> entityNotFoundExceptionHandler(EntityNotFoundException e, WebRequest req) {
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(CopiesNotAvailableException.class)
    public ResponseEntity<ErrorMessage> copiesNotAvailableExceptionHandler(CopiesNotAvailableException e, WebRequest req) {
        ErrorMessage message = new ErrorMessage(HttpStatus.CONFLICT, e.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }

    @ExceptionHandler(InvalidEntityException.class)
    public ResponseEntity<ErrorMessage> invalidEntityExceptionHandler(InvalidEntityException e, WebRequest req) {
        ErrorMessage message = new ErrorMessage(HttpStatus.CONFLICT, e.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }
}
