package tema4;

/**
 * Programa de prueba del enum {@link DiaSemana}: comparación con {@code ==} y
 * con {@link DiaSemana#esPosterior(DiaSemana, DiaSemana)}.
 */
public class PruebasDiaSemana {

	/**
	 * Compara dos días y muestra si son iguales y cuál es posterior.
	 *
	 * @param args argumentos de la línea de comandos (no se usan)
	 */
	public static void main(String[] args) {

		DiaSemana today = DiaSemana.MIÉRCOLES;

		DiaSemana tomorrow = DiaSemana.JUEVES;

		// Cada constante de un enum es un único objeto: se pueden comparar con ==.
		boolean iguales = today == tomorrow;
		IO.println("¿iguales? " + iguales);
		IO.println("¿diferentes? " + !iguales);

		IO.println("¿es posterior? " + DiaSemana.esPosterior(tomorrow, today));

	}

}
