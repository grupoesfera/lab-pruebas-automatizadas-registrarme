package ar.com.grupoesfera.pruebas.aceptacion.cucumber;


import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(plugin = {"pretty", "html:target/cucumber"})
public class RunTestAceptacionCucumberIT {

}
