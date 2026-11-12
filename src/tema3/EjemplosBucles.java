package tema3;

/**
 * Colección de ejemplos de bucles y de operadores de incremento y decremento.
 *
 * <p>Muestra {@code while}, {@code for}, la diferencia entre los operadores
 * prefijo ({@code ++x}) y postfijo ({@code x++}), un {@code for} con dos
 * variables y la salida anticipada con {@code break}.
 */
public class EjemplosBucles {

	/**
	 * Ejecuta uno tras otro los ejemplos de bucles; cada uno imprime sus
	 * iteraciones por consola. Conviene predecir qué se imprimirá antes de ejecutarlo,
	 * sobre todo en el ejemplo de {@code x}, {@code y} y {@code z}.
	 *
	 * @param args argumentos de la línea de comandos (no se usan)
	 */
	public static void main(String[] args) {

		int contador = 0;
		// while repite el bloque mientras la condición sea verdadera.
		while (contador < 5) {
			IO.println("WHILE -> Iteración nº: " + contador);
			contador++;
		}

		// for reúne la inicialización, la condición y la actualización del contador.
		for (int i = 1; i < 5; i++) {
			IO.println("FOR -> Iteración nº: " + i);
		}

		// --contador decrementa antes de usar el valor en la expresión.
		while (contador > 0) {
			IO.println("Decremento -> Iteración nº: " + --contador);
		}

		int x = 3;
		// x++ devuelve el valor anterior; --y y ++x modifican antes de devolverlo.
		int y = x++;
		int z = --y + ++x;
		IO.println("x = " + x);
		IO.println("y = " + y);
		IO.println("z = " + z);

		// Se actualizan dos variables; && exige que ambas condiciones sean ciertas.
		for (int i = 0, j = 10; i < 100 && j > -50; i = i + 7, j--) {
			IO.println("i = " + i);
			IO.println("j = " + j);
			// break termina el bucle aunque su condición siga siendo verdadera.
			if (i == 14) {
				break;
			}
		}
	}
	
}
