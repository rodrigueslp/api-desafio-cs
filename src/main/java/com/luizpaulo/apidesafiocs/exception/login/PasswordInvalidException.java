package com.luizpaulo.apidesafiocs.exception.login;

import org.springframework.http.HttpStatus;

public class PasswordInvalidException extends LoginException {

    private static final String msg = "Usuario e/ou senha invalidos.";

    public PasswordInvalidException() {
        super(msg, HttpStatus.UNAUTHORIZED);
    }

}
