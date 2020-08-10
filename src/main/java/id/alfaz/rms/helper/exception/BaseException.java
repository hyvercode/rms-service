package id.alfaz.rms.helper.exception;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class BaseException extends RuntimeException {
    protected HttpStatus httpStatus;
    protected String errorCode;
    protected String errorMessage;

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

    public BaseException(HttpStatus httpStatus,String errorCode,String errorMessage){
        super(errorMessage);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}