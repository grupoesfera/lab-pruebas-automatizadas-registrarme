package ar.com.grupoesfera.pruebas.repositorios;


import ar.com.grupoesfera.pruebas.modelo.Usuario;

public interface RepositorioUsuario {
	
	Usuario consultarUsuario(Usuario usuario);

    Usuario buscarPor(String email);

    void guardar(Usuario usuario);
}
