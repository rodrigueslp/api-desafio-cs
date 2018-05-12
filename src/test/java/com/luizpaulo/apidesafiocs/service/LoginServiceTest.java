package com.luizpaulo.apidesafiocs.service;

import com.luizpaulo.apidesafiocs.exception.login.EmailInvalidException;
import com.luizpaulo.apidesafiocs.exception.login.ParametersLoginException;
import com.luizpaulo.apidesafiocs.exception.login.PasswordInvalidException;
import com.luizpaulo.apidesafiocs.repository.UsuarioRepository;
import com.luizpaulo.apidesafiocs.vo.LoginVO;
import com.luizpaulo.apidesafiocs.vo.UsuarioResponseVO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

public class LoginServiceTest {

    @InjectMocks
    private LoginService loginService;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void login_comParametroInvalido_deveInvocarParameterLoginException() throws Exception {

        try {
            loginService.login(null);
            fail("Teste falhou");
        } catch (ParametersLoginException e) {
            assertThat(e.getMessage(), is("Usuario e/ou senha invalidos."));
        }
    }

    @Test
    public void login_comUsuarioNaoEncontradoPeloEmail_deveInvocarEmailInvalidException() throws Exception {

        try {
            when(usuarioService.getUsuarioVOPorEmail(anyString())).thenReturn(null);

            loginService.login(new LoginVO("teste@teste.com.br", "123456"));
            fail("Teste falhou");
        } catch (EmailInvalidException e) {
            assertThat(e.getMessage(), is("Usuario e/ou senha invalidos."));
        }

    }

    @Test
    public void login_comPasswordInvalido_deveInvocarPasswordInvalidException() throws Exception {

        try {
            UsuarioResponseVO usuarioVO = createUsuarioVO();
            when(usuarioService.getUsuarioVOPorEmail(anyString())).thenReturn(usuarioVO);
            when(usuarioService.getUsuarioVOPorEmailESenha(anyString(), anyString())).thenReturn(null);

            loginService.login(new LoginVO("teste@teste.com", "1234"));
            fail("Teste falhou");

        } catch (PasswordInvalidException e) {
            assertThat(e.getMessage(), is("Usuario e/ou senha invalidos."));
        }

    }

    @Test
    public void login_comPasswordValido_deveRetornarUsuarioVO() throws Exception {

        UsuarioResponseVO usuarioVO = createUsuarioVO();
        when(usuarioService.getUsuarioVOPorEmail(anyString())).thenReturn(usuarioVO);
        when(usuarioService.getUsuarioVOPorEmailESenha(anyString(), anyString())).thenReturn(usuarioVO);

        UsuarioResponseVO usuarioVOLogado = loginService.login(new LoginVO("teste@teste.com", "1234"));

        assertThat(usuarioVOLogado, instanceOf(UsuarioResponseVO.class));

    }

    private UsuarioResponseVO createUsuarioVO() {
        return new UsuarioResponseVO(
                        "1e16f391-2f8b-44b6-89d9-1d744f6f8c81",
                        "12/05/2018 14:34",
                        "",
                        "12/05/2018 14:34",
                        "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJKb8OjbyBkYSBTaWx2YSIsImVtYWlsIjoiam9hb0BzaWx2YS5vcmcifQ");
    }

}