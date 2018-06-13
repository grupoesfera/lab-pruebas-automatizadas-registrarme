package ar.com.grupoesfera.pruebas.tragamonedas;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class TamborTest {

	@Test
	public void obtenerValorSinGirarElTamborDeveriaDevolverCero(){
		Tambor tambor = new Tambor();
		assertThat(tambor.obtenerPosicion()).isEqualTo(0);
	}
	
	@Test
	public void obtenerValorDespuesDeGirarElTamborDeveriaDevolverEntreUnoYOcho(){
		Tambor tambor = new Tambor();
		tambor.girar();
		assertThat(tambor.obtenerPosicion()).isBetween(1, 8);
	}
}
