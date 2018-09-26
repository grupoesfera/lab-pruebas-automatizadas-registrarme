package ar.com.grupoesfera.pruebas.aceptacion.cucumber;

import ar.com.grupoesfera.pruebas.aceptacion.TestDeAceptacion;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

import static org.assertj.core.api.Assertions.assertThat;

public class RegistrarmeCucumber extends TestDeAceptacion implements AdaptadorParaRegistrarme {

    private final AdaptadorParaRegistrarme adaptador;

    public RegistrarmeCucumber(AdaptadorParaRegistrarme adaptador ){
        this.adaptador = adaptador;
    }

    @Given("que no existe el usuario (.*)")
    public void noExiteUsuario(String usuario){
        adaptador.noExiteUsuario(usuario);
    }

    @Given("que ya existe el usuario (.*) con clave (.*)")
    public void ingresoUsuarioDuplicado(String usuario, String clave){
        adaptador.ingresoUsuarioDuplicado(usuario,clave);
    }

    @When("ingreso a (.*)")
    public void ingresoA(String path){  adaptador.ingresoA(path);    }

    @When("intento registrarme")
    public void registrarme(){
        adaptador.registrarme();
    }

    @And("ingreso el usuario (.*)")
    public void ingresoUsuario(String usuario){
        adaptador.ingresoUsuario(usuario);
    }

    @And("ingreso la clave (.*)")
    public void ingresoClave(String clave){
        adaptador.ingresoClave(clave);
    }

    @Then("el usuario se crea")
    public void usuarioSeCrea(){ adaptador.usuarioSeCrea();     }

    @Then("el usuario NO se crea")
    public void usuarioNoSeCrea(){ adaptador.usuarioNoSeCrea();     }

    @And("muestra el mensaje '(.*)'")
    public void vuelveARegistro(String mensaje){
        adaptador.vuelveARegistro(mensaje);
    }

    @And("me redirige a la vista (.*)")
    public void redirigeA(String vista){
        adaptador.redirigeA(vista);
    }

}
