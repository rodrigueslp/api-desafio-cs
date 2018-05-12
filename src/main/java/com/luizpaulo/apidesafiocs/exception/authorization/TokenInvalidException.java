package com.luizpaulo.apidesafiocs.exception.authorization;

import org.springframework.http.HttpStatus;

public class TokenInvalidException extends AuthorizationException {

    private static final String msg = "Não autorizado.";

    public TokenInvalidException() {
        super(msg, HttpStatus.UNAUTHORIZED);
    }

}
