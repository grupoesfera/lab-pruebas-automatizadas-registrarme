package ar.com.grupoesfera.pruebas.controladores;

import ar.com.grupoesfera.pruebas.modelo.Usuario;
import ar.com.grupoesfera.pruebas.modelo.UsuarioExistente;
import ar.com.grupoesfera.pruebas.servicios.ServicioUsuario;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
public class ControladorApiLogin {

    @Inject
    private ServicioUsuario servicioUsuario;

    @RequestMapping(path = "/crear-usuario/{usuario}/{clave}", method = RequestMethod.GET)
    public String crearUsuario(@PathVariable("usuario") String usuario, @PathVariable("clave") String clave) throws UsuarioExistente {
        Usuario user = new Usuario();
        user.setEmail(usuario);
        user.setPassword(clave);
        servicioUsuario.registrar(user);
        return "";
    }
}
