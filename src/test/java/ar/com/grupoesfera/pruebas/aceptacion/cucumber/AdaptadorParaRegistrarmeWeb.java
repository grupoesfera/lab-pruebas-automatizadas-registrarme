package ar.com.grupoesfera.pruebas.aceptacion.cucumber;

import ar.com.grupoesfera.pruebas.aceptacion.TestDeAceptacion;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

import static org.assertj.core.api.Assertions.assertThat;

public class AdaptadorParaRegistrarmeWeb extends TestDeAceptacion implements AdaptadorParaRegistrarme {

    public void noExiteUsuario(String usuario){
        // no hace nada
    }

    public void ingresoUsuarioDuplicado(String usuario, String clave){
        seleniumDriver.get(urlBase + "/nuevo-usuario");
        seleniumDriver.findElement(By.id("email")).sendKeys(usuario);
        seleniumDriver.findElement(By.id("password")).sendKeys(clave);
        seleniumDriver.findElement(By.id("btn-registrarme")).click();
    }

    public void ingresoA(String path){
        seleniumDriver.get(urlBase + "/" + path);
    }

    public void registrarme(){
        seleniumDriver.findElement(By.id("btn-registrarme")).click();
    }

    public void ingresoUsuario(String usuario){
        seleniumDriver.findElement(By.id("email")).sendKeys(usuario);
    }

    public void ingresoClave(String clave){
        seleniumDriver.findElement(By.id("password")).sendKeys(clave);
    }

    public void usuarioSeCrea(){}

    public void usuarioNoSeCrea(){}

    public void vuelveARegistro(String mensaje){
        assertThat(seleniumDriver.getPageSource()).contains(mensaje);
    }

    public void redirigeA(String vista){
        assertThat(seleniumDriver.getCurrentUrl()).contains(vista);
    }
}
