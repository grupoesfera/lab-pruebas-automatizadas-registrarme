package ar.com.grupoesfera.pruebas.aceptacion;


import org.junit.Test;
import org.openqa.selenium.By;

import static org.assertj.core.api.Assertions.assertThat;

public class RegistrarmeAceptacionIT extends TestDeAceptacion {

    @Test
    public void siElResgistroEsExitosoDeberiaLlevarAlLogin() {

        // preparacion
        String url = urlBase + "/nuevo-usuario";

        // ejecucion
        seleniumDriver.get(url);
        seleniumDriver.findElement(By.id("email")).sendKeys("seba@seba.com");
        seleniumDriver.findElement(By.id("password")).sendKeys("1234");
        seleniumDriver.findElement(By.id("btn-registrarme")).click();

        // validacion
        assertThat(seleniumDriver.getCurrentUrl()).contains("login");
    }

    @Test
    public void siElUsuarioYaExisteDeberiaVolverARegistrarmeYMostrarError() {
        // preparacion
        String url = urlBase + "/nuevo-usuario";
        seleniumDriver.get(url);
        seleniumDriver.findElement(By.id("email")).sendKeys("juan@juan.com");
        seleniumDriver.findElement(By.id("password")).sendKeys("1234");
        seleniumDriver.findElement(By.id("btn-registrarme")).click();

        // ejecucion
        seleniumDriver.get(url);
        seleniumDriver.findElement(By.id("email")).sendKeys("juan@juan.com");
        seleniumDriver.findElement(By.id("password")).sendKeys("1234");
        seleniumDriver.findElement(By.id("btn-registrarme")).click();

        // validacion
        assertThat(seleniumDriver.getPageSource()).contains("El usuario ya existe");
        assertThat(seleniumDriver.getCurrentUrl()).contains("registrarme");
    }

}

