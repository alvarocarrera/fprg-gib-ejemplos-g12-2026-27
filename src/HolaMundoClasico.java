/**
 * Ejemplo mínimo de un programa Java con la estructura clásica: una clase con
 * un método {@code main} y un método auxiliar estático.
 *
 * <p>Qué se aprende aquí:
 * <ul>
 * <li>El programa empieza en {@code main}.</li>
 * <li>Declarar variables ({@code String}, {@code double}) y mostrarlas por consola.</li>
 * <li>Escribir y llamar a un método con parámetros y valor de retorno.</li>
 * </ul>
 *
 * <p>Ojo: dentro de {@code main} la variable {@code base} se modifica antes de
 * calcular el área, así que el resultado impreso no es el del triángulo de base 4.
 * Sirve para ver cómo un cambio previo afecta al cálculo.
 */
public class HolaMundoClasico {

	/**
	 * Punto de entrada del programa: saluda, calcula un área y la muestra.
	 *
	 * @param args argumentos de la línea de comandos (no se usan)
	 */
	// Punto de entrada clásico: Java empieza a ejecutar el programa aquí.
	public static void main(String[] args) {
		String saludo = "Hola Mundo";
		System.out.println(saludo);

		// Guardamos el valor devuelto por el método para mostrarlo después.
		double base = 4;
		double altura = 6;
		// Ojo: esta línea cambia el valor de base antes del cálculo, así que el área mostrada es la de otro triángulo.
		base = base * altura;
		double areaCalculada = calcularAreaTriangulo(base, altura);
		System.out.println(areaCalculada);
	}

	/**
	 * Calcula el área de un triángulo con la fórmula {@code base * altura / 2}.
	 *
	 * <p>Los parámetros reciben copias de los valores con los que se llama al
	 * método; modificarlos aquí no cambia las variables del código que llama.
	 *
	 * @param base   base del triángulo
	 * @param altura altura del triángulo
	 * @return el área del triángulo
	 */
	// Los parámetros reciben copias de los valores de la llamada.
	// return entrega el resultado al código que invocó el método.
	static double calcularAreaTriangulo(double base, double altura) {
		return (base * altura) / 2.0;
	}

}
