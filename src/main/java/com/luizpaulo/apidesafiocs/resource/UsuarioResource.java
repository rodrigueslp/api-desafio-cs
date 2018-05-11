package com.luizpaulo.apidesafiocs.resource;

import com.luizpaulo.apidesafiocs.entity.Usuario;
import com.luizpaulo.apidesafiocs.service.UsuarioService;
import com.luizpaulo.apidesafiocs.util.JwtUtil;
import com.luizpaulo.apidesafiocs.vo.MensagemVO;
import com.luizpaulo.apidesafiocs.vo.UsuarioResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity listar() {

        List<UsuarioResponseVO> listaUsuarioVO = usuarioService.getUsuariosVO();

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
    public ResponseEntity getPerfilUsuario(@PathParam("id") String id, HttpServletResponse response) {
        String token = response.getHeader("Authorization");
        return null;

    }


}
