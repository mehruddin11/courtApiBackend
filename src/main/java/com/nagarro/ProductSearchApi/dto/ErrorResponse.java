package com.nagarro.ProductSearchApi.dto;

import org.springframework.http.HttpStatus;

public class ErrorResponse {

    private HttpStatus status;
    private String message;
    private String exceptionType;
    private String[] stackTrace;

    public ErrorResponse(HttpStatus status, String message, String exceptionType, Throwable throwable) {
        this.status = status;
        this.message = message;
        this.exceptionType = exceptionType;
        this.stackTrace = getStackTrace(throwable);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public String[] getStackTrace() {
        return stackTrace;
    }

    private String[] getStackTrace(Throwable throwable) {
        if (throwable != null) {
            StackTraceElement[] elements = throwable.getStackTrace();
            String[] stackTrace = new String[elements.length];
            for (int i = 0; i < elements.length; i++) {
                stackTrace[i] = elements[i].toString();
            }
            return stackTrace;
        }
        return new String[0];
    }
}
