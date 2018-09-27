package ar.com.grupoesfera.pruebas.persistencia;

import ar.com.grupoesfera.pruebas.SpringTest;
import ar.com.grupoesfera.pruebas.modelo.Usuario;
import ar.com.grupoesfera.pruebas.repositorios.RepositorioUsuario;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by diego on 9/26/18.
 */
public class RepositorioUsuarioHibernateTest extends SpringTest{

    @Inject
    private RepositorioUsuario repositorio;

    public RepositorioUsuario repositorio() {
        return repositorio;
    }

    @Test
    @Transactional
    @Rollback
    public void guardar(){
        // preparacion
        Usuario seba = new Usuario();
        seba.setEmail("seba@seba.com");
        seba.setPassword("1234");

        // ejecucion
        repositorio().guardar(seba);

        // comprobacion
        assertThat(repositorio().buscarPor(seba.getEmail() ) ).isNotNull();
    }

    @Test
    @Transactional
    @Rollback
    public void buscarPorMail(){
        // preparacion
        Usuario seba = new Usuario();
        seba.setEmail("seba@seba.com");
        seba.setPassword("1234");
        repositorio().guardar(seba);

        // ejecucion
        Usuario buscado = repositorio().buscarPor(seba.getEmail());

        // comprobacion
        assertThat(buscado).isNotNull();
    }

    @Test
    @Transactional
    @Rollback
    public void buscarUsuarioInexistente(){
        // preparacion
        Usuario seba = new Usuario();
        seba.setEmail("seba@seba.com");
        seba.setPassword("1234");
        repositorio().guardar(seba);

        // ejecucion
        Usuario buscado = repositorio().buscarPor( "diego@seba.com" );

        // comprobacion
        assertThat(buscado).isNull();
    }

    @Test
    @Transactional
    @Rollback
    public void consultarUsuarioExistente(){
        // preparacion
        Usuario seba = new Usuario();
        seba.setEmail("seba@seba.com");
        seba.setPassword("1234");
        repositorio().guardar(seba);

        Usuario diego = new Usuario();
        diego.setEmail("diego@seba.com");
        diego.setPassword( "otra");

        // ejecucion
        Usuario buscado = repositorio().consultarUsuario( seba );

        // comprobacion
        assertThat(buscado).isNotNull();
    }

    @Test
    @Transactional
    @Rollback
    public void consultarUsuarioInexistente(){
        // preparacion
        Usuario seba = new Usuario();
        seba.setEmail("seba@seba.com");
        seba.setPassword("1234");
        repositorio().guardar(seba);

        Usuario diego = new Usuario();
        diego.setEmail("diego@seba.com");
        diego.setPassword( "otra");

        // ejecucion
        Usuario buscado = repositorio().consultarUsuario( diego );

        // comprobacion
        assertThat(buscado).isNull();
    }
}
