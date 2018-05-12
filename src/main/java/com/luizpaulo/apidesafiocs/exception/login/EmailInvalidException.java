package com.luizpaulo.apidesafiocs.exception.login;

import org.springframework.http.HttpStatus;

public class EmailInvalidException extends LoginException {

    private static final String msg = "Usuario e/ou senha invalidos.";

    public EmailInvalidException() {
        super(msg, HttpStatus.UNAUTHORIZED);
    }

}
