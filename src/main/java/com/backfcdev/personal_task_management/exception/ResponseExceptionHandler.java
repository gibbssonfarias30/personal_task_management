package com.backfcdev.personal_task_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;


@RestControllerAdvice
public class ResponseExceptionHandler {
    @ResponseBody
    @ExceptionHandler(TaskNotFoundException.class)
    ProblemDetail handleTaskNotFoundException(TaskNotFoundException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Task Not Found");
        problemDetail.setType(URI.create("/not-found"));
        return problemDetail;
    }
}
