package ar.com.grupoesfera.pruebas.controladores;

import ar.com.grupoesfera.pruebas.modelo.Usuario;
import ar.com.grupoesfera.pruebas.modelo.UsuarioExistente;
import ar.com.grupoesfera.pruebas.servicios.ServicioUsuario;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ControladorRegistrarmeTest {

    private ControladorRegistrarme controlador = new ControladorRegistrarme();
    private Usuario usuarioMock;
    private ServicioUsuario servicioMock;

    @Before
    public void init(){
        usuarioMock = mock(Usuario.class);
        when(usuarioMock.getEmail()).thenReturn("mail@usuario.com");
        servicioMock = mock(ServicioUsuario.class);
        controlador.setServicioLogin(servicioMock);
    }

    @Test
    public void registrameSiUsuarioNoExisteDeberiaCrearUsuarioYVolverAlLogin() throws UsuarioExistente {

        // ejecucion
        ModelAndView modelAndView = controlador.registrarme(usuarioMock);

        // validacion
        assertThat(modelAndView.getViewName()).isEqualTo("redirect:/login");
        verify(servicioMock, times(1)).registrar(usuarioMock);
    }

    @Test
    public void registrarmeSiUsuarioExisteDeberiaVolverAFormularioYMostrarError() throws UsuarioExistente {
        // preparacion
        doThrow(UsuarioExistente.class).when(servicioMock).registrar(usuarioMock);

        // ejecucion
        ModelAndView modelAndView = controlador.registrarme(usuarioMock);

        // validacion
        assertThat(modelAndView.getViewName()).isEqualTo("nuevo-usuario");
        assertThat(modelAndView.getModel().get("error")).isEqualTo("El usuario ya existe");
    }

    @Test
    public void errorEnRegistrarmeDeberiaVolverAFormularioYMostrarError() throws UsuarioExistente {
        // preparacion
        doThrow(Exception.class).when(servicioMock).registrar(usuarioMock);

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
