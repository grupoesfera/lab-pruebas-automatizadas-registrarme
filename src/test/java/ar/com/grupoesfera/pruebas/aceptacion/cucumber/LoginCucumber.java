package ar.com.grupoesfera.pruebas.aceptacion.cucumber;

import com.mashape.unirest.http.HttpResponse;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.mashape.unirest.http.Unirest.get;
import static org.assertj.core.api.Assertions.*;

public class LoginCucumber extends TestDeAceptacionCucumber {

    private HttpResponse<String> httpResponse;

    @Given ("existe el usuario (.*) con clave (.*)")
    public void crearUsuario(String usuario, String clave) throws Exception{
        get(urlBase + "/crear-usuario/" + usuario + "/" + clave).asString();
    }

    @When("intento loguearme con usuario (.*) y clave (.*)")
    public void login(String usuario, String clave) throws Exception{
        httpResponse = get(urlBase + "/do-login/" + usuario + "/" + clave).asString();
    }

    @Then("se retorna el mensaje '(.*)'")
    public void obtenerMensaje(String mensaje){
        assertThat(httpResponse.getBody()).contains(mensaje);
    }
}
