package ar.com.grupoesfera.pruebas.servicios;

import ar.com.grupoesfera.pruebas.modelo.Usuario;
import ar.com.grupoesfera.pruebas.modelo.UsuarioExistente;
import ar.com.grupoesfera.pruebas.repositorios.RepositorioUsuario;
import org.hibernate.HibernateException;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ServicioLoginTest {

    private ServicioLoginImpl servicioLogin;
    private Usuario usuarioMock;
    private RepositorioUsuario repositorioUsuarioMock;
    private ServicioMail servicioMailMock;

    @Before
    public void init(){
        servicioLogin = new ServicioLoginImpl();
        usuarioMock = mock(Usuario.class);

        repositorioUsuarioMock = mock(RepositorioUsuario.class);
        servicioLogin.setRepositorioUsuario(repositorioUsuarioMock);

        servicioMailMock = mock(ServicioMail.class);
        servicioLogin.setServicioMail(servicioMailMock);
    }

    @Test(expected = UsuarioExistente.class)
    public void registrarmeDeberiaLanzarUsuarioExisteSiExisteUnUsuarioConMismoMail() throws UsuarioExistente {

        // preparacion
        when(repositorioUsuarioMock.buscarPor(usuarioMock.getEmail())).thenReturn(new Usuario());

        // ejecucion
        servicioLogin.registrar(usuarioMock);

        // validacion
        verify(servicioMailMock, never()).enviarMailDeBienvenida(usuarioMock);
    }

    @Test(expected = Exception.class)
    public void registrarmeDeberiaLanzarExceptionSiOcurreUnErrorAlGuardarElUsuarioNuevo() throws UsuarioExistente {

        // preparacion
        when(repositorioUsuarioMock.buscarPor(usuarioMock.getEmail())).thenReturn(null);
        doThrow(HibernateException.class).when(repositorioUsuarioMock).guardar(usuarioMock);

        // ejecucion
        servicioLogin.registrar(usuarioMock);

        // validacion
        verify(servicioMailMock, never()).enviarMailDeBienvenida(usuarioMock);
    }

    @Test
    public void registrarmeDeberiaGuardarElNuevoUsuarioSiElUsuarioNoExiste() throws UsuarioExistente {

        // preparacion
        when(repositorioUsuarioMock.buscarPor(usuarioMock.getEmail())).thenReturn(null);

        // ejecucion
        servicioLogin.registrar(usuarioMock);

        // validacion
        verify(servicioMailMock, times(1)).enviarMailDeBienvenida(usuarioMock);
    }
}