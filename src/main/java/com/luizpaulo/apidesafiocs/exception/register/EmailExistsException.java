package com.luizpaulo.apidesafiocs.exception.register;

import org.springframework.http.HttpStatus;

public class EmailExistsException extends Exception {

    private HttpStatus status = HttpStatus.BAD_REQUEST;

    public EmailExistsException() {
        super("E-mail já existente");
        this.setStatus(status);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

}
