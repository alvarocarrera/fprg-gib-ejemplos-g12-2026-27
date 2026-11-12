package tema4;

/**
 * Ejemplo de un array grande de {@code double}: se rellena con números
 * aleatorios y se suman sus elementos.
 *
 * <p>Compara el recorrido con {@code while} (índice manual) y con {@code for},
 * y muestra el patrón de acumulador.
 */
public class EjemplosArrays {

	/**
	 * Crea el array, lo rellena con valores aleatorios entre 0 y 100, y muestra la
	 * suma de todos ellos.
	 *
	 * @param args argumentos de la línea de comandos (no se usan)
	 */
	public static void main(String[] args) {

		// Diez millones de double ocupan unos 80 MB (8 bytes cada uno).
		double[] temps = new double[10000000];

		// Recorrido con while: el índice se gestiona a mano.
		int contador = 0;
		while(contador < temps.length) {
			// Math.random() devuelve un valor en [0, 1); lo escalamos a [0, 100).
			double random = Math.random() * 100;
			// contador++ usa el valor actual como índice y después lo incrementa.
			temps[contador++] = random;
		}

		// Acumulador: parte de cero y suma cada elemento.
		double suma = 0.0;

		for(int i = 0; i < temps.length; i++) {
			suma += temps[i];
		}

		IO.println("Sumatorio: " + suma);

	}

}
