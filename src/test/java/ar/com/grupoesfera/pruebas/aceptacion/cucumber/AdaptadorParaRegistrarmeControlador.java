package ar.com.grupoesfera.pruebas.aceptacion.cucumber;

import ar.com.grupoesfera.pruebas.controladores.ControladorRegistrarme;
import ar.com.grupoesfera.pruebas.modelo.Usuario;
import ar.com.grupoesfera.pruebas.modelo.UsuarioExistente;
import ar.com.grupoesfera.pruebas.repositorios.RepositorioUsuario;
import ar.com.grupoesfera.pruebas.repositorios.RepositorioUsuarioImpl;
import ar.com.grupoesfera.pruebas.servicios.ServicioMail;
import ar.com.grupoesfera.pruebas.servicios.ServicioMailImpl;
import ar.com.grupoesfera.pruebas.servicios.ServicioUsuarioImpl;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class AdaptadorParaRegistrarmeControlador implements AdaptadorParaRegistrarme {

    private ControladorRegistrarme controlador = new ControladorRegistrarme();

    private ServicioUsuarioImpl servicio = new ServicioUsuarioImpl();

    private Usuario usuario;

    private ModelAndView modelAndView;

    public AdaptadorParaRegistrarmeControlador(){
        RepositorioUsuario repositorioUsuario = new RepositorioUsuarioEnMemoria();
        servicio.setRepositorioUsuario( repositorioUsuario );
        servicio.setServicioMail(new ServicioMailImpl());
        controlador.setServicio( servicio );

        usuario = new Usuario();
        usuario.setEmail("mail@usuario.com");
    }

    public void noExiteUsuario(String usuario){
        // no hace nada
    }

    public void ingresoUsuarioDuplicado(String email, String clave){

        this.usuario.setEmail(email);
        this.usuario.setPassword(clave);
        controlador.registrarme(this.usuario);
    }

    public void ingresoA(String path){

        if ( path.equals( "nuevo-usuario") ){
            controlador.nuevoUsuario();
        }
//        seleniumDriver.get(urlBase + "/" + path);
    }

    public void registrarme(){
        modelAndView = controlador.registrarme(usuario);
    }

    public void ingresoUsuario(String email){
        this.usuario.setEmail(email);
    }

    public void ingresoClave(String clave){
        this.usuario.setPassword(clave);
    }

    public void usuarioSeCrea() {
        assertThat(servicio.consultarUsuario(usuario).getEmail()).isEqualTo(usuario.getEmail());
    }

    public void usuarioNoSeCrea(){}

    public void vuelveARegistro(String mensaje){
        assertThat(modelAndView.getModel().get("error")).isEqualTo(mensaje);
    }

    public void redirigeA(String vista){
        System.out.println( "Error: " + modelAndView.getModel().get("error") );
        assertThat(modelAndView.getViewName()).contains(vista);
    }
}
