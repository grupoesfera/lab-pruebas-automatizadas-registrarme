package ar.com.grupoesfera.pruebas.repositorios;

import ar.com.grupoesfera.pruebas.modelo.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

@Repository("usuarioRepository")
public class RepositorioUsuarioImpl implements RepositorioUsuario {

	@Inject
    private SessionFactory sessionFactory;

	public Usuario consultarUsuario(Usuario usuario) {

		final Session session = sessionFactory.getCurrentSession();
		final Query<Usuario> query = session.createQuery("from Usuario where email=:email and password=:password", Usuario.class);
		query.setParameter("email", usuario.getEmail());
		query.setParameter("password", usuario.getPassword());
		return query.uniqueResult();
	}

	@Override
	public Usuario buscarPor(String email) {
		final Session session = sessionFactory.getCurrentSession();
		final Query<Usuario> query = session.createQuery("from Usuario where email=:email", Usuario.class);
		query.setParameter("email", email);
		return query.uniqueResult();
	}

	@Override
	public void guardar(Usuario usuario) {
		sessionFactory.getCurrentSession().save(usuario);
	}

}
