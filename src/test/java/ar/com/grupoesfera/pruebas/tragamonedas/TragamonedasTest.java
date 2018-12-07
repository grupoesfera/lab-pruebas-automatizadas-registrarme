package ar.com.grupoesfera.pruebas.tragamonedas;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class TragamonedasTest {

	@Test
	public void siLosTresTamboresTienenElMismoValorDeberiaDarPremio(){

		// preparación
		Tambor tambor1Mock = mock(Tambor.class);
		Tambor tambor2Mock = mock(Tambor.class);
		Tambor tambor3Mock = mock(Tambor.class);
		
		when(tambor1Mock.obtenerPosicion()).thenReturn(3);
		when(tambor2Mock.obtenerPosicion()).thenReturn(3);
		when(tambor3Mock.obtenerPosicion()).thenReturn(3);
		
		Tragamonedas maquinita = new Tragamonedas(tambor1Mock, tambor2Mock, tambor3Mock);

		// ejecución
		maquinita.activar();

		// validación
		assertThat(maquinita.entregaPremio()).isTrue();
	}
	
	@Test
	public void siLosTresTamboresTienenDistintoValorNoDeberiaDarPremio(){

		// preparación
		Tambor tambor1Mock = mock(Tambor.class);
		Tambor tambor2Mock = mock(Tambor.class);
		Tambor tambor3Mock = mock(Tambor.class);
		
		when(tambor1Mock.obtenerPosicion()).thenReturn(3);
		when(tambor2Mock.obtenerPosicion()).thenReturn(4);
		when(tambor3Mock.obtenerPosicion()).thenReturn(1);

		Tragamonedas maquinita = new Tragamonedas(tambor1Mock, tambor2Mock, tambor3Mock);

		// ejecución
		maquinita.activar();

		// validación
		assertThat(maquinita.entregaPremio()).isFalse();
	}
	
	@Test
	public void verificarQueAlActivarElTragamonedasGirenLosTresTambores(){

		// preparación
		Tambor tambor1Mock = mock(Tambor.class);
		Tambor tambor2Mock = mock(Tambor.class);
		Tambor tambor3Mock = mock(Tambor.class);
		
		when(tambor1Mock.obtenerPosicion()).thenReturn(3);
		when(tambor2Mock.obtenerPosicion()).thenReturn(3);
		when(tambor3Mock.obtenerPosicion()).thenReturn(3);

		Tragamonedas maquinita = new Tragamonedas(tambor1Mock, tambor2Mock, tambor3Mock);

		// ejecución
		maquinita.activar();

			// validación
        verify(tambor1Mock, times(1)).girar();
		verify(tambor2Mock, times(1)).girar();
		verify(tambor3Mock, times(1)).girar();
	}
	
	@Test
	@SuppressWarnings("unused")
	public void verificarQueSiNoSeActivaElTragamonedasNoGirenLosTambores(){

		// preparación
		Tambor tambor1Mock = mock(Tambor.class);
		Tambor tambor2Mock = mock(Tambor.class);
		Tambor tambor3Mock = mock(Tambor.class);
		
		when(tambor1Mock.obtenerPosicion()).thenReturn(3);
		when(tambor2Mock.obtenerPosicion()).thenReturn(3);
		when(tambor3Mock.obtenerPosicion()).thenReturn(3);

		Tragamonedas maquinita = new Tragamonedas(tambor1Mock, tambor2Mock, tambor3Mock);

		// ejecución

		// validación
		verify(tambor1Mock, never()).girar();
		verify(tambor2Mock, never()).girar();
		verify(tambor3Mock, never()).girar();
	}
}
