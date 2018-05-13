package com.luizpaulo.apidesafiocs.service;

import com.luizpaulo.apidesafiocs.entity.Usuario;
import com.luizpaulo.apidesafiocs.exception.authorization.SessionInvalidException;
import com.luizpaulo.apidesafiocs.exception.authorization.TokenInvalidException;
import com.luizpaulo.apidesafiocs.exception.authorization.UUIDInvalidException;
import com.luizpaulo.apidesafiocs.exception.register.EmailExistsException;
import com.luizpaulo.apidesafiocs.repository.UsuarioRepository;
import com.luizpaulo.apidesafiocs.vo.UsuarioResponseVO;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getUsuarioVOPorEmail_comParametroInvalido_deveRetornarNull() {
        UsuarioResponseVO usuarioVO = usuarioService.getUsuarioVOPorEmail(null);
        assertNull(usuarioVO);
    }

    @Test
    public void getUsuarioVOPorEmail_comParametroValido_deveRetornarObjeto() {
        when(usuarioRepository.buscaPorEmail(anyString())).thenReturn(new Usuario());
        UsuarioResponseVO usuarioVO = usuarioService.getUsuarioVOPorEmail("teste@teste.com");
        assertThat(usuarioVO, instanceOf(UsuarioResponseVO.class));
    }

    @Test
    public void getUsuarioVOPorEmailESenha_comParametroInvalido_deveRetornarNull() {
        UsuarioResponseVO usuarioVO = usuarioService.getUsuarioVOPorEmailESenha(null, null);
        assertNull(usuarioVO);
    }

    @Test
    public void getUsuarioVOPorEmailESenha_comParametrosValidos_deveRetornarObjeto() {
        when(usuarioRepository.buscaPorEmailESenha(anyString(), anyString())).thenReturn(new Usuario());
        UsuarioResponseVO usuarioVO = usuarioService.getUsuarioVOPorEmailESenha("teste@teste.com", "123456");
        assertThat(usuarioVO, instanceOf(UsuarioResponseVO.class));
    }

    @Test
    public void getUsuarioValido_comUUIDInvalido_deveInvocarUUIDInvalidException() throws Exception {

        try {
            usuarioService.getUsuarioValido(null, null);
            fail("Teste falhou");
        } catch (UUIDInvalidException e) {
            assertThat(e.getMessage(), is("Não autorizado."));
        }
    }

    @Test
    public void getUsuarioValido_comTokenInvalido_deveInvocarTokenInvalidException() throws Exception {

        try {
            Usuario usuario = createUsuario();
            when(usuarioRepository.buscaPorId(UUID.fromString("1e16f391-2f8b-44b6-89d9-1d744f6f8c81"))).thenReturn(usuario);
            usuarioService.getUsuarioValido(UUID.fromString("1e16f391-2f8b-44b6-89d9-1d744f6f8c81"), null);
            fail("Teste falhou");
        } catch (TokenInvalidException e) {
            assertThat(e.getMessage(), is("Não autorizado."));
        }
    }

    @Test
    public void getUsuarioValido_comLastLoginAcimaDe30Minutos_deveInvocarSessionInvalidException() throws Exception {

        try {
            Usuario usuario = createUsuario();
            usuario.setLastLogin(LocalDateTime.now().minusMinutes(31));
            when(usuarioRepository.buscaPorId(UUID.fromString("1e16f391-2f8b-44b6-89d9-1d744f6f8c81"))).thenReturn(usuario);
            usuarioService.getUsuarioValido(UUID.fromString("1e16f391-2f8b-44b6-89d9-1d744f6f8c81"), usuario.getToken());
            fail("Teste falhou");
        } catch (SessionInvalidException e) {
            assertThat(e.getMessage(), is("Sessão inválida."));
        }

    }

    @Test
    public void getUsuarioValido_comLastLoginAte30Minutos_deveRetornarUsuarioVOValido() throws Exception {

        Usuario usuario = createUsuario();
        usuario.setLastLogin(LocalDateTime.now().minusMinutes(30));
        when(usuarioRepository.buscaPorId(UUID.fromString("1e16f391-2f8b-44b6-89d9-1d744f6f8c81"))).thenReturn(usuario);
        UsuarioResponseVO usuarioValido = usuarioService.getUsuarioValido(UUID.fromString("1e16f391-2f8b-44b6-89d9-1d744f6f8c81"), usuario.getToken());
        assertThat(usuarioValido, instanceOf(UsuarioResponseVO.class));

    }

    @Test
    public void validaUsuario_comEmailJaCadastradoNaBase_deveInvocarEmailExistsException() throws Exception {
        try {
            when(usuarioRepository.buscaPorEmail("teste@teste.com")).thenReturn(new Usuario());
            usuarioService.validaUsuario("teste@teste.com");
            fail("Teste falhou");
        } catch (EmailExistsException e) {
            assertThat(e.getMessage(), is("Este email já esta cadastrado."));
        }

    }

    private Usuario createUsuario() {
        Usuario usuario = new Usuario();
        usuario.setToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJKb8OjbyBkYSBTaWx2YSIsImVtYWlsIjoiam9hb0BzaWx2YS5vcmcifQ." +
                "n-1-mAL8aiv-dj2VPurfTQY4EXCsarp4F1jhI68MMCBhNC_vkM1RlJS4Wmh6uLlPXGxi8x-tojI8afnU54133A");
        return usuario;
    }

}
