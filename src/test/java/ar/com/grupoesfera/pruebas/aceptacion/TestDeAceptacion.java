package ar.com.grupoesfera.pruebas.aceptacion;

import net.thucydides.core.annotations.Managed;
import net.thucydides.core.pages.PageObject;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public abstract class TestDeAceptacion  {

    @Managed
    protected WebDriver seleniumDriver;
    protected String port = System.getProperty("servlet.port", "8080");
    protected String urlBase = "http://localhost:" + port + "/registrarme";

    public TestDeAceptacion() {

        System.setProperty("webdriver.chrome.driver", "webdrivers/chromedriver-linux");
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
