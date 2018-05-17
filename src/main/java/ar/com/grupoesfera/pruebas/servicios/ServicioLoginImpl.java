package ar.com.grupoesfera.pruebas.servicios;

import javax.inject.Inject;

import ar.com.grupoesfera.pruebas.modelo.Usuario;
import ar.com.grupoesfera.pruebas.modelo.UsuarioExistente;
import ar.com.grupoesfera.pruebas.repositorios.RepositorioUsuario;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("servicioLogin")
@Transactional
public class ServicioLoginImpl implements ServicioLogin {

	@Inject
	private RepositorioUsuario repositorioUsuario;

	@Inject
	private ServicioMail servicioMail;

	@Override
	public Usuario consultarUsuario (Usuario usuario) {
		return repositorioUsuario.consultarUsuario(usuario);
	}

	@Override
	public void registrar(Usuario usuario) throws UsuarioExistente {
		Usuario usuarioEncontrado = repositorioUsuario.buscarPor(usuario.getEmail());
		if(usuarioEncontrado != null){
			throw new UsuarioExistente();
		}
		repositorioUsuario.guardar(usuario);
		servicioMail.enviarMailDeBienvenida(usuario);
	}

	// IoC setters
	public void setRepositorioUsuario(RepositorioUsuario repositorioUsuario) {
		this.repositorioUsuario = repositorioUsuario;
	}

	public void setServicioMail(ServicioMail servicioMail) {
		this.servicioMail = servicioMail;
	}
}
