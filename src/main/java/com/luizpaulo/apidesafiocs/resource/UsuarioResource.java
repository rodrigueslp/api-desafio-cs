package com.luizpaulo.apidesafiocs.resource;

import com.luizpaulo.apidesafiocs.entity.Usuario;
import com.luizpaulo.apidesafiocs.exception.authorization.SessionInvalidException;
import com.luizpaulo.apidesafiocs.exception.authorization.TokenInvalidException;
import com.luizpaulo.apidesafiocs.exception.authorization.UUIDInvalidException;
import com.luizpaulo.apidesafiocs.service.UsuarioService;
import com.luizpaulo.apidesafiocs.util.JwtUtil;
import com.luizpaulo.apidesafiocs.vo.MensagemVO;
import com.luizpaulo.apidesafiocs.vo.UsuarioResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity listar() {

        List<UsuarioResponseVO> listaUsuarioVO = usuarioService.getListaUsuarioVO();

        return (listaUsuarioVO != null && !listaUsuarioVO.isEmpty())
                ? ResponseEntity.status(HttpStatus.OK).body(listaUsuarioVO)
                : ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody Usuario usuario) {

        try {

            usuario.setToken(JwtUtil.getToken(usuario.getName(), usuario.getEmail()));
            Usuario usuarioSalvo = usuarioService.save(usuario);

            UsuarioResponseVO usuarioVO = usuarioService.converteUsuarioParaUsuarioResponseVO(usuarioSalvo);

            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioVO);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MensagemVO("Ocorreu um erro interno, estamos trabalhando para restabelecer o mais rápido possível."));
        }

    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity getPerfilUsuario(@PathVariable("id") String id, HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        try {

            usuarioService.validaToken(token);
            final UUID uuidUsuario = usuarioService.getUuidUsuario(id);

            UsuarioResponseVO usuarioVO = usuarioService.getUsuarioValido(uuidUsuario, token);
            return ResponseEntity.status(HttpStatus.OK).body(usuarioVO);

        } catch (TokenInvalidException | UUIDInvalidException | SessionInvalidException e) {
            return ResponseEntity.status(e.getStatus()).body(new MensagemVO(e.getMessage()));
        }

    }


}
