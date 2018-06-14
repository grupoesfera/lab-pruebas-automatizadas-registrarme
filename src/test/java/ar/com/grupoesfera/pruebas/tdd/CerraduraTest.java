package ar.com.grupoesfera.pruebas.tdd;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class CerraduraTest {

	@Test
	public void abrirConClaveCorrectaDeberiaAbrirLaCerradura(){
		Cerradura cerradura = new Cerradura(1234, 3);
		assertThat(cerradura.abrir(1234)).isTrue();
	}
	
	@Test
	public void abrirConClaveIncorrectaNoDeberiaAbrirLaCerradura(){
		Cerradura cerradura = new Cerradura(1234, 3);
		assertThat(cerradura.abrir(123)).isFalse();
	}
	
	@Test
	public void alAbrirLaCerraduraDeberiaEstarAbierta(){
		Cerradura cerradura = new Cerradura(1234, 3);
		cerradura.abrir(1234);
		assertThat(cerradura.estaAbierta()).isTrue();
	}
	
	@Test
	public void alCrearLaCerraduraDeberiaEstarAbierta(){
		Cerradura cerradura = new Cerradura(1234, 3);
		assertThat(cerradura.estaAbierta()).isTrue();
	}
	
	@Test
	public void alCerrarLaCerraduraNoDeberiaEstarAbierta(){
		Cerradura cerradura = new Cerradura(1234, 3);
		cerradura.cerrar();
		assertThat(cerradura.estaAbierta()).isFalse();
	}
	
	@Test
	public void alCerrarLaCerraduraDeberiaEstarCerrada(){
		Cerradura cerradura = new Cerradura(1234, 3);
		cerradura.abrir(1234);
		cerradura.cerrar();
		assertThat(cerradura.estaCerrada()).isTrue();
	}
	
	@Test
	public void alIntentarAbrirConClaveIncorrectaMasDeVecesPermitidasBloqueaLaCerradura(){
		Cerradura cerradura = new Cerradura(1234, 3);
		cerradura.abrir(1231);
		cerradura.abrir(1231);
		cerradura.abrir(1231);
		assertThat(cerradura.estaBloqueada()).isTrue();
	}
	
	@Test
	public void alCrearLaCerraduraNoDeberiaEstarBloqueada(){
		Cerradura cerradura = new Cerradura(1234, 3);
		assertThat(cerradura.estaBloqueada()).isFalse();
	}
	
	@Test
	public void alIntentarAbrirCerraduraBloqueadaNoDeberiaAbrirse(){
		Cerradura cerradura = new Cerradura(1234, 3);
		cerradura.abrir(1231);
		cerradura.abrir(1231);
		cerradura.abrir(1231);
		assertThat(cerradura.abrir(1234)).isFalse();
	}
	
	@Test
	public void alAbrirLaCerraduraDeberiaResetearCantidadDeIntentosFallidos(){
		Cerradura cerradura = new Cerradura(1234, 3);
		cerradura.abrir(1231);
		cerradura.abrir(1231);
		cerradura.abrir(1234);
		assertThat(cerradura.cantidadIntentosFallidos()).isEqualTo(0);
	}
	
	@Test
	public void alFallarDosVecesAlAbrirDeberiaDevolverDosDeIntentosFallidos(){
		Cerradura cerradura = new Cerradura(1234, 3);
		cerradura.abrir(1231);
		cerradura.abrir(1231);
		assertThat(cerradura.cantidadIntentosFallidos()).isEqualTo(2);
	}
	
}
