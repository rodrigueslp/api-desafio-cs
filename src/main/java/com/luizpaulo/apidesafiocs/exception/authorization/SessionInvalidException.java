package com.luizpaulo.apidesafiocs.exception.authorization;


import org.springframework.http.HttpStatus;

public class SessionInvalidException extends AuthorizationException {

    private static final String msg = "Sessão inválida.";

    public SessionInvalidException() {
        super(msg, HttpStatus.UNAUTHORIZED);
    }

}
