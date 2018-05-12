package com.luizpaulo.apidesafiocs.service;

import com.luizpaulo.apidesafiocs.entity.Usuario;
import com.luizpaulo.apidesafiocs.exception.authorization.SessionInvalidException;
import com.luizpaulo.apidesafiocs.exception.authorization.TokenInvalidException;
import com.luizpaulo.apidesafiocs.exception.authorization.UUIDInvalidException;
import com.luizpaulo.apidesafiocs.repository.UsuarioRepository;
import com.luizpaulo.apidesafiocs.util.DataUtil;
import com.luizpaulo.apidesafiocs.vo.LoginVO;
import com.luizpaulo.apidesafiocs.vo.UsuarioResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listAll() {
        return usuarioRepository.findAll();
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<UsuarioResponseVO> getUsuariosVO() {

        List<Usuario> usuarios = this.listAll();

        List<UsuarioResponseVO> listaUsuarioVO = new ArrayList<UsuarioResponseVO>();

        if(validaListaUsuarios(usuarios)) {

            for (Usuario usuario : usuarios) {
                listaUsuarioVO.add(converteUsuarioParaUsuarioResponseVO(usuario));
            }

        }

        return listaUsuarioVO;

    }

    public UsuarioResponseVO getUsuarioVOPorEmail(String email) {
        Usuario usuario = usuarioRepository.buscaPorEmail(email);
        return (usuario != null) ? converteUsuarioParaUsuarioResponseVO(usuario) : null;
    }

    public UsuarioResponseVO getUsuarioVOPorEmailESenha(String email, String senha) {
        Usuario usuario = usuarioRepository.buscaPorEmailESenha(email, senha);
        return (usuario != null) ? converteUsuarioParaUsuarioResponseVO(usuario) : null;
    }

    public UsuarioResponseVO getUsuarioValido(UUID uuid, String token) throws TokenInvalidException, SessionInvalidException {
        Usuario usuario = getUsuarioPorId(uuid);

        if (!usuario.getToken().equals(token)) throw new TokenInvalidException();

        final long diferencaMinutos = DataUtil.diferencaMinutos(usuario.getLastLogin(), LocalDateTime.now());
        if (diferencaMinutos > 1) throw new SessionInvalidException();

        return converteUsuarioParaUsuarioResponseVO(usuario);

    }

    public Usuario getUsuarioPorId(UUID uuid) {
        return usuarioRepository.buscaPorId(uuid);
    }

    private boolean validaListaUsuarios(List<Usuario> usuarios) {
        return usuarios != null && !usuarios.isEmpty();
    }

    public UsuarioResponseVO converteUsuarioParaUsuarioResponseVO(Usuario usuario) {
        UsuarioResponseVO usuarioVO = new UsuarioResponseVO(
                usuario.getId().toString(),
                DataUtil.converteDateParaString(usuario.getCreated()),
                DataUtil.converteDateParaString(usuario.getModified()),
                DataUtil.converteDateParaString(usuario.getLastLogin()),
                usuario.getToken());

        return usuarioVO;
    }


    public void validaToken(String token) throws TokenInvalidException {

        if(token == null || token.equals("")) throw new TokenInvalidException();

    }

    public UUID getUuidUsuario(String id) throws UUIDInvalidException {

        if(id == null || id.equals("")) throw new UUIDInvalidException();

        return UUID.fromString(id);

    }
}
