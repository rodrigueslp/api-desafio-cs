package com.luizpaulo.apidesafiocs.resource;

import com.luizpaulo.apidesafiocs.exception.EmailInvalidException;
import com.luizpaulo.apidesafiocs.exception.PasswordInvalidException;
import com.luizpaulo.apidesafiocs.service.LoginService;
import com.luizpaulo.apidesafiocs.vo.LoginVO;
import com.luizpaulo.apidesafiocs.vo.MensagemVO;
import com.luizpaulo.apidesafiocs.vo.UsuarioResponseVO;
import org.hibernate.NonUniqueResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginResource {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity login(@RequestBody LoginVO loginVO) {

        try {

            UsuarioResponseVO usuarioVO = loginService.login(loginVO);

            return (usuarioVO != null)
                    ? ResponseEntity.status(HttpStatus.OK).body(usuarioVO)
                    : ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        } catch (EmailInvalidException | PasswordInvalidException e) {
            return ResponseEntity.status(e.getStatus()).body(new MensagemVO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MensagemVO("Ocorreu um erro interno, estamos trabalhando para restabelecer o mais rápido possível."));
        }


    }

}
