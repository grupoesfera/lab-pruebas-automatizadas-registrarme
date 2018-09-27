package ar.com.grupoesfera.pruebas.persistencia;

import ar.com.grupoesfera.pruebas.aceptacion.cucumber.RepositorioUsuarioEnMemoria;
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
public class RepositorioUsuarioEnMemoriaTest extends RepositorioUsuarioHibernateTest {

    private RepositorioUsuario repositorio = new RepositorioUsuarioEnMemoria();

    @Override
    public RepositorioUsuario repositorio() {
        return repositorio;
    }
}
