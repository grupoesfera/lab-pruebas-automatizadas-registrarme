package ar.com.grupoesfera.pruebas.aceptacion;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.util.concurrent.TimeUnit;

public abstract class TestDeAceptacion {

    protected String port = System.getProperty("servlet.port", "8080");
    protected String urlBase = "http://localhost:" + port + "/sitio";
    protected WebDriver seleniumDriver;

    public TestDeAceptacion() {
        seleniumDriver = new PhantomJSDriver();
        seleniumDriver.manage().window().maximize();
        seleniumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        seleniumDriver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
    }

}
