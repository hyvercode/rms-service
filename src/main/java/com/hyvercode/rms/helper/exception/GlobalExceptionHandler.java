package com.hyvercode.rms.helper.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<BaseException> handleCustomException(BusinessException ex) {
        BaseException error= new BaseException(ex.getHttpStatus(),ex.getErrorCode(),ex.getErrorMessage());
        return new ResponseEntity<>(error,ex.getHttpStatus());
    }
}
