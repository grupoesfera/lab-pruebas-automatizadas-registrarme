package ar.com.grupoesfera.pruebas.aceptacion.cucumber;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

import static org.assertj.core.api.Assertions.assertThat;

public class RegistrarmeCucumber extends TestDeAceptacionCucumber {

    @Given("que no existe el usuario (.*)")
    public void noExiteUsuario(String usuario){
        // no hace nada
    }

    @When("ingreso a (.*)")
    public void ingresoA(String path){
        seleniumDriver.get(urlBase + "/" + path);
    }

    @And("ingreso el usuario (.*)")
    public void ingresoUsuario(String usuario){
        seleniumDriver.findElement(By.id("email")).sendKeys(usuario);
    }

    @And("ingreso la clave (.*)")
    public void ingresoClave(String clave){
        seleniumDriver.findElement(By.id("password")).sendKeys(clave);
    }

    @When("intento registrarme")
    public void registrarme(){
        seleniumDriver.findElement(By.id("btn-registrarme")).click();
    }

    @Then("me redirige a la vista (.*)")
    public void redirigeA(String vista){
        assertThat(seleniumDriver.getCurrentUrl()).contains(vista);
    }

    @And ("el usuario (.*) con clave (.*) se crea")
    public void creacionDeUsuario(String usuario, String clave) {
        seleniumDriver.get(urlBase + "/login");
        seleniumDriver.findElement(By.id("email")).sendKeys(usuario);
        seleniumDriver.findElement(By.id("password")).sendKeys(clave);
        seleniumDriver.findElement(By.id("btn-login")).click();
        assertThat(seleniumDriver.getPageSource()).contains("Bienvenidos!!");
    }

    @After
    public void fin(){
        seleniumDriver.quit();
    }
}
