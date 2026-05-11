package com.org.pc1repaso.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.org.pc1repaso.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ErrorResponse> 
    handleNotFound(ResourceNotFoundException ex, WebRequest request) {
        ErrorResponse error = 
        new ErrorResponse("NOT_FOUND", ex.getMessage(), Instant.now(), request.getDescription(false).replace("uri=",""));

        return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ErrorResponse>
    handleBadRequest(BadRequestException ex, WebRequest request){
        ErrorResponse error = 
        new ErrorResponse("BAD_REQUEST", ex.getMessage(), Instant.now(), request.getDescription(false).replace("uri=",""));
        return new ResponseEntity<ErrorResponse>(error,HttpStatus.BAD_REQUEST);
    }

}