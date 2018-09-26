package ar.com.grupoesfera.pruebas.aceptacion.cucumber;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created by diego on 9/25/18.
 */
public interface AdaptadorParaRegistrarme {

    void noExiteUsuario(String usuario);

    void ingresoUsuarioDuplicado(String usuario, String clave);

    void ingresoA(String path);

    void registrarme();

    void ingresoUsuario(String usuario);

    void ingresoClave(String clave);

    void usuarioSeCrea();

    void usuarioNoSeCrea();

    void vuelveARegistro(String mensaje);

    void redirigeA(String vista);
}
