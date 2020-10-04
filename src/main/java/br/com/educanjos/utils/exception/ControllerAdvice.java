package br.com.educanjos.utils.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(ExceptionEducanjosApi.class)
    public ResponseEntity<ResponseException> exceptionResponseEntity(ExceptionEducanjosApi exception) {
        return new ResponseEntity<>(new ResponseException(exception), exception.getStatus());
    }
}
