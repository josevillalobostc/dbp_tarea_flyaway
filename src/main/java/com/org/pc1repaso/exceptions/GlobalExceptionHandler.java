package com.org.pc1repaso.exceptions;

import java.time.Instant;


import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;


@RestControllerAdvice
public class GlobalExceptionHandler {

    
    @ExceptionHandler({ResourceNotFoundException.class})
    public ProblemDetail
    handleNotFound(ResourceNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(404);
        problemDetail.setTitle("Resource Not Found");
        problemDetail.setDetail(ex.getMessage());        
        return problemDetail;
    }

    @ExceptionHandler({BadRequestException.class})
    public ProblemDetail
    handleBadRequest(BadRequestException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(400);
        problemDetail.setTitle("Bad request");
        problemDetail.setDetail(ex.getMessage());        
        return problemDetail;
    }

}