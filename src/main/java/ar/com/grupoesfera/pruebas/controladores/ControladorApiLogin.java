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
public class ControladorApiLogin  {

    @Inject
    private ServicioUsuario servicioUsuario;

    @RequestMapping(path="/do-login/{usuario}/{clave}", method = RequestMethod.GET)
    public String doLogin(@PathVariable("usuario") String usuario, @PathVariable("clave") String clave){
        Usuario usuarioBuscado = servicioUsuario.consultarUsuario(usuario(usuario, clave));
        if(usuarioBuscado == null){
            return "usuario o clave incorrecta";
        }
        return "login exitoso";
    }

    @RequestMapping(path="/crear-usuario/{usuario}/{clave}", method = RequestMethod.GET)
    public String crearUsuario(@PathVariable("usuario") String usuario, @PathVariable("clave") String clave) throws UsuarioExistente {
        servicioUsuario.registrar(usuario(usuario, clave));
        return "";
    }

    private Usuario usuario(String usuario, String clave){
        Usuario user = new Usuario();
        user.setEmail(usuario);
        user.setPassword(clave);
        return user;
    }
}
