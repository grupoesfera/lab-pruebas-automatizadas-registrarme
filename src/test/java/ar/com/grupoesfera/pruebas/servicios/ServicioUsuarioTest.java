package ar.com.grupoesfera.pruebas.servicios;

import ar.com.grupoesfera.pruebas.modelo.Usuario;
import ar.com.grupoesfera.pruebas.modelo.UsuarioExistente;
import ar.com.grupoesfera.pruebas.repositorios.RepositorioUsuario;
import org.hibernate.HibernateException;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ServicioUsuarioTest {

    private ServicioUsuarioImpl servicio;
    private Usuario usuarioMock;
    private RepositorioUsuario repositorioMock;
    private ServicioMail servicioMailMock;

    @Before
    public void init(){
        servicio = new ServicioUsuarioImpl();
        usuarioMock = mock(Usuario.class);

        repositorioMock = mock(RepositorioUsuario.class);
        servicio.setRepositorioUsuario(repositorioMock);

        servicioMailMock = mock(ServicioMail.class);
        servicio.setServicioMail(servicioMailMock);
    }

    @Test(expected = UsuarioExistente.class)
    public void registrarmeDeberiaLanzarUsuarioExisteSiExisteUnUsuarioConMismoMail() throws UsuarioExistente {

        // preparacion
        when(repositorioMock.buscarPor(usuarioMock.getEmail())).thenReturn(new Usuario());

        // ejecucion
        servicio.registrar(usuarioMock);

        // validacion
        verify(servicioMailMock, never()).enviarMailDeBienvenida(usuarioMock);
    }

    @Test(expected = Exception.class)
    public void registrarmeDeberiaLanzarExceptionSiOcurreUnErrorAlGuardarElUsuarioNuevo() throws UsuarioExistente {

        // preparacion
        when(repositorioMock.buscarPor(usuarioMock.getEmail())).thenReturn(null);
        doThrow(HibernateException.class).when(repositorioMock).guardar(usuarioMock);

        // ejecucion
        servicio.registrar(usuarioMock);

        // validacion
        verify(servicioMailMock, never()).enviarMailDeBienvenida(usuarioMock);
    }

    @Test
    public void registrarmeDeberiaGuardarElNuevoUsuarioSiElUsuarioNoExiste() throws UsuarioExistente {

        // preparacion
        when(repositorioMock.buscarPor(usuarioMock.getEmail())).thenReturn(null);

        // ejecucion
        servicio.registrar(usuarioMock);

        // validacion
        verify(servicioMailMock, times(1)).enviarMailDeBienvenida(usuarioMock);
    }
}