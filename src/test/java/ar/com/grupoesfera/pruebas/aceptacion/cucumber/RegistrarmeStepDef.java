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
        seleniumDriver.get(url);
        seleniumDriver.findElement(By.id("email")).sendKeys(usuario);
        seleniumDriver.findElement(By.id("password")).sendKeys(clave);
    }

    @Given("que ya existe el usuario (.*) con clave (.*)")
    public void ingresoUsuarioDuplicado(String usuario, String clave){
        seleniumDriver.get(url);
        seleniumDriver.findElement(By.id("email")).sendKeys(usuario);
        seleniumDriver.findElement(By.id("password")).sendKeys(clave);
        seleniumDriver.findElement(By.id("btn-registrarme")).click();

        seleniumDriver.get(url);
        seleniumDriver.findElement(By.id("email")).sendKeys(usuario);
        seleniumDriver.findElement(By.id("password")).sendKeys(clave);
    }

    @When("intento registrarme")
    public void registrarme(){
        seleniumDriver.findElement(By.id("btn-registrarme")).click();
    }

    @Then("el usuario se crea y me redirige a la vista (.*)")
    public void redirigeA(String vista){
        assertThat(seleniumDriver.getCurrentUrl()).contains(vista);
        seleniumDriver.quit();
    }

    @Then("el usuario NO se crea y me redirige a la vista (.*) y muestra el mensaje '(.*)'")
    public void vuelveARegistro(String vista, String mensaje){
        assertThat(seleniumDriver.getPageSource()).contains(mensaje);
        assertThat(seleniumDriver.getCurrentUrl()).contains(vista);
        seleniumDriver.quit();
    }

}
