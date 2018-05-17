package ar.com.grupoesfera.pruebas.controladores;

import ar.com.grupoesfera.pruebas.modelo.Usuario;
import ar.com.grupoesfera.pruebas.servicios.ServicioUsuario;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ControladorLoginTest {

	private ControladorLogin controlador = new ControladorLogin();
	private Usuario usuarioMock;
	private HttpServletRequest requestMock;
	private HttpSession sessionMock;
	private ServicioUsuario servicioMock;
	
	@Before
	public void init(){
		usuarioMock = mock(Usuario.class);
		when(usuarioMock.getEmail()).thenReturn("user@user.com");
		requestMock = mock(HttpServletRequest.class);
		sessionMock = mock(HttpSession.class);
		servicioMock = mock(ServicioUsuario.class);
		controlador.setServicioLogin(servicioMock);
	}

	@Test
	public void loginConUsuarioYPasswordInorrectosDeberiaLlevarALoginNuevamente(){
		// preparacion
		when(servicioMock.consultarUsuario(any(Usuario.class))).thenReturn(null);

		// ejecucion
		ModelAndView modelAndView = controlador.validarLogin(usuarioMock, requestMock);

		// validacion
		assertThat(modelAndView.getViewName()).isEqualTo("login");
		assertThat(modelAndView.getModel().get("error")).isEqualTo("Usuario o clave incorrecta");
		verify(sessionMock, never()).setAttribute("ROL", "ADMIN");
	}
	
	@Test
	public void loginConUsuarioYPasswordCorrectosDeberiaLLevarAHome(){
		// preparacion
		Usuario usuarioEncontradoMock = mock(Usuario.class);
		when(usuarioEncontradoMock.getRol()).thenReturn("ADMIN");

		when(requestMock.getSession()).thenReturn(sessionMock);
		when(servicioMock.consultarUsuario(any(Usuario.class))).thenReturn(usuarioEncontradoMock);
		
		// ejecucion
		ModelAndView modelAndView = controlador.validarLogin(usuarioMock, requestMock);
		
		// validacion
		assertThat(modelAndView.getViewName()).isEqualTo("redirect:/home");
		verify(sessionMock, times(1)).setAttribute("ROL", usuarioEncontradoMock.getRol());
	}

}
