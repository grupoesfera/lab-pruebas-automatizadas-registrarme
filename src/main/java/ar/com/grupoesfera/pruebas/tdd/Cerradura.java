package ar.com.grupoesfera.pruebas.tdd;

public class Cerradura {

	private int clave;
	private int intentosParaBloqueo;
	private boolean estaAbierta = true;
	private boolean estaBloqueada = false;
	private int intentosFallidos = 0;

	public Cerradura(int clave, int intentos) {
		this.clave = clave;
		this.intentosParaBloqueo = intentos;
	}
	
	public boolean abrir(int clave){
		if(estaBloqueada){
			return false;
		}
		if(clave == this.clave){
			this.estaAbierta = true;
			intentosFallidos = 0;
			return true;
		}else{
			intentosFallidos++;
			if(intentosFallidos == intentosParaBloqueo){
				estaBloqueada = true;
			}
			return false;
		}
	}

	public boolean estaAbierta() {
		return estaAbierta;
	}

	public void cerrar() {
		this.estaAbierta = false;
	}

	public boolean estaCerrada() {
		return !estaAbierta;
	}

	public boolean estaBloqueada() {
		return estaBloqueada ;
	}

	public int cantidadIntentosFallidos() {
		return intentosFallidos;
	}
	
}
