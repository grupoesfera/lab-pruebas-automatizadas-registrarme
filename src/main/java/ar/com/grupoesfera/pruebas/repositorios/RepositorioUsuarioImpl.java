package ar.com.grupoesfera.pruebas.repositorios;

import ar.com.grupoesfera.pruebas.modelo.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

@Repository("usuarioDao")
public class RepositorioUsuarioImpl implements RepositorioUsuario {

	@Inject
    private SessionFactory sessionFactory;

	public Usuario consultarUsuario(Usuario usuario) {

		final Session session = sessionFactory.getCurrentSession();
		return (Usuario) session.createCriteria(Usuario.class)
				.add(Restrictions.eq("email", usuario.getEmail()))
				.add(Restrictions.eq("password", usuario.getPassword()))
				.uniqueResult();
	}

	@Override
	public Usuario buscarPor(String email) {
		final Session session = sessionFactory.getCurrentSession();
		return (Usuario) session.createCriteria(Usuario.class)
								.add(Restrictions.eq("email", email))
								.uniqueResult();
	}

	@Override
	public void guardar(Usuario usuario) {
		sessionFactory.getCurrentSession().save(usuario);
	}

}
