package ar.com.grupoesfera.pruebas.tragamonedas;

public class Tragamonedas {
	
	private Tambor tambor1;
	private Tambor tambor2;
	private Tambor tambor3;

	public Tragamonedas(Tambor tambor1, Tambor tambor2, Tambor tambor3) {
		this.tambor1 = tambor1;
		this.tambor2 = tambor2;
		this.tambor3 = tambor3;
	}

	public void activar() {
		tambor1.girar();
		tambor2.girar();
		tambor3.girar();
	}

	public boolean entregaPremio() {
		return tambor1.obtenerPosicion() == tambor2.obtenerPosicion() && tambor3.obtenerPosicion() == tambor2.obtenerPosicion();
	}

}
