package ar.com.grupoesfera.pruebas.aceptacion.cucumber;

import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;

public class TestDeAceptacionCucumber  {

    @Managed
    protected WebDriver seleniumDriver;
    protected String port = System.getProperty("servlet.port", "8080");
    protected String urlBase = "http://localhost:" + port + "/registrarme";

}
