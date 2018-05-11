package com.luizpaulo.apidesafiocs.exception;

import org.springframework.http.HttpStatus;

public class PasswordInvalidException extends LoginException {

    public PasswordInvalidException(String msg) {
        super(msg, HttpStatus.UNAUTHORIZED);
    }

}
