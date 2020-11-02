package br.com.educanjos.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(ExceptionEducanjosApi.class)
    public ResponseEntity<ResponseException> apiLinkedinException(ExceptionEducanjosApi exception) {
        return new ResponseEntity<>(new ResponseException(exception), exception.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseException> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return new ResponseEntity<>(new ResponseException(exception), HttpStatus.BAD_REQUEST);
    }
}
