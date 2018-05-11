package com.luizpaulo.apidesafiocs.service;

import com.luizpaulo.apidesafiocs.entity.Usuario;
import com.luizpaulo.apidesafiocs.repository.UsuarioRepository;
import com.luizpaulo.apidesafiocs.util.DataUtil;
import com.luizpaulo.apidesafiocs.vo.LoginVO;
import com.luizpaulo.apidesafiocs.vo.UsuarioResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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


}
