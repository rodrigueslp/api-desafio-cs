package com.luizpaulo.apidesafiocs.service;

import com.luizpaulo.apidesafiocs.exception.EmailInvalidException;
import com.luizpaulo.apidesafiocs.exception.PasswordInvalidException;
import com.luizpaulo.apidesafiocs.vo.LoginVO;
import com.luizpaulo.apidesafiocs.vo.UsuarioResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UsuarioService usuarioService;

    public UsuarioResponseVO login(LoginVO loginVO) throws EmailInvalidException, PasswordInvalidException {

        if(usuarioService.getUsuarioVOPorEmail(loginVO.getEmail()) == null)
            throw new EmailInvalidException("Usuario e/ou senha invalidos.");

        UsuarioResponseVO usuarioVO = usuarioService.getUsuarioVOPorEmailESenha(loginVO.getEmail(), loginVO.getPassword());

        if (usuarioVO == null)
            throw new PasswordInvalidException("Usuario e/ou senha invalidos");

        return usuarioVO;

    }
}
