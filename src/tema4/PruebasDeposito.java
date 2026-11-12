package tema4;

/**
 * Programa de prueba de {@link Deposito}: lo crea, cambia su capacidad y
 * añade litros.
 */
public class PruebasDeposito {

	/**
	 * Crea un depósito, le asigna capacidad 1000, intenta llenarlo con 10 litros y
	 * muestra su capacidad.
	 *
	 * @param args argumentos de la línea de comandos (no se usan)
	 */
	public static void main(String[] args) {
//		Esto ya no se puede hacer porque hay un constructor en la clase Deposito,
//		por lo tanto, el constructor por defecto deja de estar disponible.
		
//		Deposito d = new Deposito();
		
		// new reserva un objeto y ejecuta el constructor con capacidad inicial 0.
		Deposito d = new Deposito(0);
		d.setCapacidad(1000);
		d.llena(10);
		// Se muestra la capacidad del depósito, no los litros que contiene.
		IO.println(d.getCapacidad());
	}

}
