package ar.com.grupoesfera.pruebas.aceptacion.cucumber;

import ar.com.grupoesfera.pruebas.aceptacion.TestDeAceptacion;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

import static org.assertj.core.api.Assertions.assertThat;

public class RegistrarmeStepDef extends TestDeAceptacion {

    private String url = urlBase + "/nuevo-usuario";

    @Given("que ingreso el usuario (.*) con clave (.*)")
    public void ingresoUsuario(String usuario, String clave){
    }

    @When("intento registrarme")
    public void registrarme(){
    }

    @Then("el usuario se crea y me redirige a la vista (.*)")
    public void redirigeA(String vista){
    }

}
