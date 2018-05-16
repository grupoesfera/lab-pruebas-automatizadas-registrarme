package ar.edu.grupoesfera.cursospring.controladores;

import ar.com.grupoesfera.pruebas.controladores.ControladorLogin;
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

public class ControladorLoginTest {

	private ControladorLogin controladorLogin = new ControladorLogin();
	private Usuario usuarioMock;
	private HttpServletRequest requestMock;
	private HttpSession sessionMock;
	private ServicioLogin servicioLoginMock;
	
	@Before
	public void init(){
		usuarioMock = mock(Usuario.class);
		when(usuarioMock.getEmail()).thenReturn("seba@seba.com");
		requestMock = mock(HttpServletRequest.class);
		sessionMock = mock(HttpSession.class);
		servicioLoginMock = mock(ServicioLogin.class);
		controladorLogin.setServicioLogin(servicioLoginMock);
	}

	@Test
	public void loginConUsuarioYPasswordInorrectosDeberiaLlevarALoginNuevamente(){
		// preparacion
		when(servicioLoginMock.consultarUsuario(any(Usuario.class))).thenReturn(null);

		// ejecucion
		ModelAndView modelAndView = controladorLogin.validarLogin(usuarioMock, requestMock);

		// validacion
		assertThat(modelAndView.getViewName()).isEqualTo("login");
		assertThat(modelAndView.getModel().get("error")).isEqualTo("Usuario o clave incorrecta");
		verify(sessionMock, times(0)).setAttribute("ROL", "ADMIN");
	}
	
	@Test
	public void loginConUsuarioYPasswordCorrectosDeberiaLLevarAHome(){
		// preparacion
		Usuario usuarioEncontradoMock = mock(Usuario.class);
		when(usuarioEncontradoMock.getRol()).thenReturn("ADMIN");

		when(requestMock.getSession()).thenReturn(sessionMock);
		when(servicioLoginMock.consultarUsuario(any(Usuario.class))).thenReturn(usuarioEncontradoMock);
		
		// ejecucion
		ModelAndView modelAndView = controladorLogin.validarLogin(usuarioMock, requestMock);
		
		// validacion
		assertThat(modelAndView.getViewName()).isEqualTo("redirect:/home");
		verify(sessionMock, times(1)).setAttribute("ROL", usuarioEncontradoMock.getRol());
	}

	@Test
	public void registrameSiUsuarioNoExisteDeberiaCrearUsuarioYVolverAlLogin() throws UsuarioExistente {

		// ejecucion
		ModelAndView modelAndView = controladorLogin.registrarme(usuarioMock);

		// validacion
		assertThat(modelAndView.getViewName()).isEqualTo("redirect:/login");
		verify(servicioLoginMock, times(1)).registrar(usuarioMock);
	}

	@Test
	public void registrarmeSiUsuarioExisteDeberiaVolverAFormularioYMostrarError() throws UsuarioExistente {
		// preparacion
		doThrow(UsuarioExistente.class).when(servicioLoginMock).registrar(usuarioMock);

		// ejecucion
		ModelAndView modelAndView = controladorLogin.registrarme(usuarioMock);

		// validacion
		assertThat(modelAndView.getViewName()).isEqualTo("nuevo-usuario");
		assertThat(modelAndView.getModel().get("error")).isEqualTo("El usuario ya existe");
	}

	@Test
	public void errorEnRegistrarmeDeberiaVolverAFormularioYMostrarError() throws UsuarioExistente {
		// preparacion
		doThrow(Exception.class).when(servicioLoginMock).registrar(usuarioMock);

		// ejecucion
		ModelAndView modelAndView = controladorLogin.registrarme(usuarioMock);

		// validacion
		assertThat(modelAndView.getViewName()).isEqualTo("nuevo-usuario");
		assertThat(modelAndView.getModel().get("error")).isEqualTo("Error al registrar el nuevo usuario");
	}

}
