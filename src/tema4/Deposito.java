package tema4;

public class Deposito {
	
	// Los atributos privados se consultan o modifican mediante los métodos públicos.
	private int capacidad;
	private int numLitros;
	
	public Deposito(int capacidad) {
		// numLitros empieza en cero, el valor inicial de un atributo int.
		this.capacidad = capacidad;
	}
	
	public int getCapacidad() {
		return this.capacidad;
	}
	
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	
	public void llena(int litros) {
		// El ejemplo usa <, no <=: no añade litros si se alcanza exactamente la capacidad.
		if (numLitros + litros < capacidad) {
			this.numLitros += litros;
		}
	}

}
