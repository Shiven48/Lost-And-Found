package com.app.Exception;

import com.app.Exception.ExceptionTypes.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
            ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), request.getDescription(false));
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
}
