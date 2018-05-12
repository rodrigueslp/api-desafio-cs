package com.luizpaulo.apidesafiocs.exception.login;

import org.springframework.http.HttpStatus;

public class ParametersLoginException extends LoginException {

    private static final String msg = "Usuario e/ou senha invalidos.";

    public ParametersLoginException() {
        super(msg, HttpStatus.UNAUTHORIZED);
    }

}
