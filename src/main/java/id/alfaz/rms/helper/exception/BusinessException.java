package id.alfaz.rms.helper.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends BaseException{
    private HttpStatus httpStatus;
    private String errorCode;
    private String errorMessage;

    public BusinessException(HttpStatus httpStatus, String errorCode, String errorMessage) {
        super(httpStatus,errorCode,errorMessage);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
