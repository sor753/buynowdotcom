package com.dailycodework.buynowdotcom.exceptions;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

//    DefaultHandlerExceptionResolverでハンドリングされない例外に対するエラー処理を実装する
//    DefaultHandlerExceptionResolver : Spring MVC のフロントコントローラの処理で発生する例外をハンドリングする
    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<String> handleAlreadyExists(EntityExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleNotFound(EntityNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAlreadyExists(Exception ex) {
        return new ResponseEntity<>("Error: "+ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
