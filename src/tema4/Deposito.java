package tema4;

/**
 * Depósito con una capacidad máxima y una cantidad de litros almacenados.
 *
 * <p>Es el primer ejemplo de clase con atributos privados, constructor, getter,
 * setter y un método que modifica el estado del objeto.
 */
public class Deposito {
	
	/**
	 * Capacidad máxima del depósito, en litros.
	 */
	// Los atributos privados se consultan o modifican mediante los métodos públicos.
	private int capacidad;
	/**
	 * Litros que contiene el depósito en este momento. Empieza en 0.
	 */
	private int numLitros;
	
	/**
	 * Crea un depósito vacío con la capacidad indicada.
	 *
	 * @param capacidad capacidad máxima en litros
	 */
	// El constructor prepara el estado de cada nuevo depósito.
	public Deposito(int capacidad) {
		// numLitros empieza en cero, el valor inicial de un atributo int.
		this.capacidad = capacidad;
	}
	
	/**
	 * Devuelve la capacidad máxima del depósito.
	 *
	 * @return la capacidad en litros
	 */
	public int getCapacidad() {
		return this.capacidad;
	}
	
	/**
	 * Cambia la capacidad máxima del depósito.
	 *
	 * @param capacidad nueva capacidad en litros
	 */
	// this.capacidad es el atributo; capacidad es el parámetro recibido.
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	
	/**
	 * Intenta añadir litros al depósito. Solo los añade si el total resultante
	 * queda por debajo de la capacidad; si no, no hace nada.
	 *
	 * @param litros litros que se quieren añadir
	 */
	public void llena(int litros) {
		// El ejemplo usa <, no <=: no añade litros si se alcanza exactamente la capacidad.
		if (numLitros + litros < capacidad) {
			this.numLitros += litros;
		}
	}

}
