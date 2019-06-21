package ar.com.grupoesfera.pruebas.aceptacion;

import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import net.thucydides.core.annotations.Managed;

public abstract class TestDeAceptacion  {

    @Managed
    protected WebDriver seleniumDriver;
    protected String port = System.getProperty("servlet.port", "8080");
    protected String urlBase = "http://localhost:" + port + "/registrarme";

    public TestDeAceptacion() {

        ResourceBundle properties = ResourceBundle.getBundle("test");

        System.setProperty("webdriver.chrome.driver", properties.getString("webdriver.chrome.driver"));
        System.setProperty("webdriver.chrome.silentOutput", properties.getString("webdriver.chrome.silentOutput"));
        Logger.getLogger("org.openqa.selenium").setLevel(Level.WARNING);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        seleniumDriver = new ChromeDriver(options);
        seleniumDriver.manage().window().maximize();
        seleniumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        seleniumDriver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
    }

    @After
    public void tearDown(){
        seleniumDriver.quit();
    }

}