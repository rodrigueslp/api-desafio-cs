package com.luizpaulo.apidesafiocs.exception.authorization;


import org.springframework.http.HttpStatus;

public class AuthorizationException extends Exception {

    private HttpStatus status;

    public AuthorizationException(String message, HttpStatus status) {
        super(message);
        this.setStatus(status);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
