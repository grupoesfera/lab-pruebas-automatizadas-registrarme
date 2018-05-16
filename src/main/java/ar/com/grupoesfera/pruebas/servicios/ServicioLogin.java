package ar.com.grupoesfera.pruebas.servicios;


import ar.com.grupoesfera.pruebas.modelo.Usuario;
import ar.com.grupoesfera.pruebas.modelo.UsuarioExistente;

public interface ServicioLogin {

	Usuario consultarUsuario(Usuario usuario);

    void registrar(Usuario usuario) throws UsuarioExistente;
}
