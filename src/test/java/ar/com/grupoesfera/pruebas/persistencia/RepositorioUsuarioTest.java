package ar.com.grupoesfera.pruebas.persistencia;

import ar.com.grupoesfera.pruebas.SpringTest;
import ar.com.grupoesfera.pruebas.modelo.Usuario;
import ar.com.grupoesfera.pruebas.repositorios.RepositorioUsuario;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.*;

public class RepositorioUsuarioTest extends SpringTest {

    @Inject
    private RepositorioUsuario repositorio;

    @Test
    @Transactional
    @Rollback
    public void guardar(){
        // preparacion
        Usuario seba = new Usuario();
        seba.setEmail("seba@seba.com");
        seba.setPassword("1234");

        // ejecucion
        repositorio.guardar(seba);

         // comprobacion
        assertThat(session().get(Usuario.class, seba.getId())).isNotNull();
    }

    @Test
    @Transactional
    @Rollback
    public void buscarPorMail(){
        // preparacion
        Usuario seba = new Usuario();
        seba.setEmail("seba@seba.com");
        seba.setPassword("1234");
        session().save(seba);

        // ejecucion
        Usuario buscado = repositorio.buscarPor(seba.getEmail());

        // comprobacion
        assertThat(buscado).isNotNull();
    }
}
