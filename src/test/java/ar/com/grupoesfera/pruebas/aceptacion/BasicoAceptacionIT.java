package ar.com.grupoesfera.pruebas.aceptacion;

import org.junit.Test;

import java.net.HttpURLConnection;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

public class BasicoAceptacionIT extends TestDeAceptacion {

	@Test
	public void smoke() throws Exception {
		URL url = new URL(urlBase + "/login");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.connect();
		assertThat(connection.getResponseCode()).isEqualTo(200);
	}

}
