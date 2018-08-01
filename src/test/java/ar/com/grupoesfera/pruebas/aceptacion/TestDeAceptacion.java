package ar.com.grupoesfera.pruebas.aceptacion;

import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.util.concurrent.TimeUnit;

public abstract class TestDeAceptacion {

    protected String port = System.getProperty("servlet.port", "8080");
    protected String urlBase = "http://localhost:" + port + "/sitio";
    protected WebDriver seleniumDriver;

    public TestDeAceptacion() {

//        usarChrome();
        usarPhantomJS();

        seleniumDriver.manage().window().maximize();
        seleniumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        seleniumDriver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
    }

    @After
    public void tearDown(){
        seleniumDriver.close();
    }

    private void usarPhantomJS(){
    	//System.setProperty("phantomjs.binary.path", "RUTA");
        seleniumDriver = new PhantomJSDriver();
    }

    private void usarChrome(){
        System.setProperty("webdriver.chrome.driver", "/usr/bin/selenium-drivers/chromedriver");
        seleniumDriver = new ChromeDriver();
    }

}
