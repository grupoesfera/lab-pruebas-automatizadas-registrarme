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

    @Given("que ya existe el usuario (.*) con clave (.*)")
    public void ingresoUsuarioDuplicado(String usuario, String clave){
        seleniumDriver.get(urlBase + "/nuevo-usuario");
        seleniumDriver.findElement(By.id("email")).sendKeys(usuario);
        seleniumDriver.findElement(By.id("password")).sendKeys(clave);
        seleniumDriver.findElement(By.id("btn-registrarme")).click();
    }

    @When("ingreso a (.*)")
    public void ingresoA(String path){
        seleniumDriver.get(urlBase + "/" + path);
    }

    @When("intento registrarme")
    public void registrarme(){
        seleniumDriver.findElement(By.id("btn-registrarme")).click();
    }

    @And("ingreso el usuario (.*)")
    public void ingresoUsuario(String usuario){
        seleniumDriver.findElement(By.id("email")).sendKeys(usuario);
    }

    @And("ingreso la clave (.*)")
    public void ingresoClave(String clave){
        seleniumDriver.findElement(By.id("password")).sendKeys(clave);
    }

    @And ("el usuario (.*) con clave (.*) se crea")
    public void creacionDeUsuario(String usuario, String clave) {
        seleniumDriver.get(urlBase + "/login");
        seleniumDriver.findElement(By.id("email")).sendKeys(usuario);
        seleniumDriver.findElement(By.id("password")).sendKeys(clave);
        seleniumDriver.findElement(By.id("btn-login")).click();
        assertThat(seleniumDriver.getPageSource()).contains("Bienvenidos!!");
    }

    @And ("no se crea el usuario (.*) con clave (.*)")
    public void usuarioNoSeCrea(String usuario, String clave) {
        seleniumDriver.get(urlBase + "/login");
        seleniumDriver.findElement(By.id("email")).sendKeys(usuario);
        seleniumDriver.findElement(By.id("password")).sendKeys(clave);
        seleniumDriver.findElement(By.id("btn-login")).click();
        assertThat(seleniumDriver.getPageSource()).contains("Usuario o clave incorrecta");
    }

    @And("muestra el mensaje '(.*)'")
    public void vuelveARegistro(String mensaje){
        assertThat(seleniumDriver.getPageSource()).contains(mensaje);
    }

    @Then("me redirige a la vista (.*)")
    public void redirigeA(String vista){
        assertThat(seleniumDriver.getCurrentUrl()).contains(vista);
    }

    @After
    public void fin(){
        seleniumDriver.quit();
    }
}
