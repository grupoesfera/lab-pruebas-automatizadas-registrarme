package ar.com.grupoesfera.pruebas.tragamonedas;

public class Tragamonedas {
	
	private Tambor tambor1 = new Tambor();
	private Tambor tambor2 = new Tambor();
	private Tambor tambor3 = new Tambor();
	
	public void activar() {
		tambor1.girar();
		tambor2.girar();
		tambor3.girar();
	}

	public boolean entregaPremio() {
		return tambor1.obtenerPosicion() == tambor2.obtenerPosicion() && tambor3.obtenerPosicion() == tambor2.obtenerPosicion();
	}

}
