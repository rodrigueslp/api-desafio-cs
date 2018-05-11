package com.luizpaulo.apidesafiocs.exception;

import org.springframework.http.HttpStatus;

public class LoginException extends Exception {

    private HttpStatus status;

    public LoginException(String string, HttpStatus httpStatus) {
        super(string);
        this.setStatus(httpStatus);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

}
