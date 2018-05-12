package com.luizpaulo.apidesafiocs.exception.authorization;

import org.springframework.http.HttpStatus;

public class UUIDInvalidException extends AuthorizationException {

    private static final String msg = "Não autorizado.";

    public UUIDInvalidException() {
        super(msg, HttpStatus.UNAUTHORIZED);
    }

}
