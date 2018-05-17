package ar.com.grupoesfera.pruebas.controladores;

import ar.com.grupoesfera.pruebas.modelo.Usuario;
import ar.com.grupoesfera.pruebas.modelo.UsuarioExistente;
import ar.com.grupoesfera.pruebas.servicios.ServicioLogin;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doThrow;

public class ControladorRegistrarmeTest {

    private ControladorRegistrarme controlador = new ControladorRegistrarme();
    private Usuario usuarioMock;
    private HttpServletRequest requestMock;
    private HttpSession sessionMock;
    private ServicioLogin servicioLoginMock;

    @Before
    public void init(){
        usuarioMock = mock(Usuario.class);
        when(usuarioMock.getEmail()).thenReturn("mail@usuario.com");
        requestMock = mock(HttpServletRequest.class);
        sessionMock = mock(HttpSession.class);
        servicioLoginMock = mock(ServicioLogin.class);
        controlador.setServicioLogin(servicioLoginMock);
    }

    @Test
    public void registrameSiUsuarioNoExisteDeberiaCrearUsuarioYVolverAlLogin() throws UsuarioExistente {

        // ejecucion
        ModelAndView modelAndView = controlador.registrarme(usuarioMock);

        // validacion
        assertThat(modelAndView.getViewName()).isEqualTo("redirect:/login");
        verify(servicioLoginMock, times(1)).registrar(usuarioMock);
    }

    @Test
    public void registrarmeSiUsuarioExisteDeberiaVolverAFormularioYMostrarError() throws UsuarioExistente {
        // preparacion
        doThrow(UsuarioExistente.class).when(servicioLoginMock).registrar(usuarioMock);

        // ejecucion
        ModelAndView modelAndView = controlador.registrarme(usuarioMock);

        // validacion
        assertThat(modelAndView.getViewName()).isEqualTo("nuevo-usuario");
        assertThat(modelAndView.getModel().get("error")).isEqualTo("El usuario ya existe");
    }

    @Test
    public void errorEnRegistrarmeDeberiaVolverAFormularioYMostrarError() throws UsuarioExistente {
        // preparacion
        doThrow(Exception.class).when(servicioLoginMock).registrar(usuarioMock);

        // ejecucion
        ModelAndView modelAndView = controlador.registrarme(usuarioMock);

        // validacion
        assertThat(modelAndView.getViewName()).isEqualTo("nuevo-usuario");
        assertThat(modelAndView.getModel().get("error")).isEqualTo("Error al registrar el nuevo usuario");
    }

    @Test
    public void registrarmeConMailIncorrectoDeberiaVolverAFormularioYMostrarError() throws UsuarioExistente {
        // preparacion
        when(usuarioMock.getEmail()).thenReturn("mail@usuario");

        // ejecucion
        ModelAndView modelAndView = controlador.registrarme(usuarioMock);

        // validacion
        assertThat(modelAndView.getViewName()).isEqualTo("nuevo-usuario");
        assertThat(modelAndView.getModel().get("error")).isEqualTo("El formato del usuario no es una direccion de email válida");
    }
}
