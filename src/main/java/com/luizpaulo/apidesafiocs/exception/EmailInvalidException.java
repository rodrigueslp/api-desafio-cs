package com.luizpaulo.apidesafiocs.exception;

import org.springframework.http.HttpStatus;

public class EmailInvalidException extends LoginException {

    public EmailInvalidException(String msg) {
        super(msg, HttpStatus.UNAUTHORIZED);
    }

}
