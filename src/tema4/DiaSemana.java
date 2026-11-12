package tema4;

/**
 * Días de la semana, de lunes a domingo.
 *
 * <p>Un {@code enum} es un tipo con un conjunto cerrado de valores. Aquí el orden
 * de declaración marca el orden natural de los días.
 */
// Un enum define un conjunto cerrado de valores constantes.
public enum DiaSemana {

	// El orden de declaración es el orden natural: LUNES es el primero (ordinal 0).
	LUNES,
	MARTES,
	MIÉRCOLES,
	JUEVES,
	VIERNES,
	SÁBADO,
	DOMINGO;


	/**
	 * Indica si un día va después de otro dentro de la semana.
	 *
	 * @param d1 primer día
	 * @param d2 segundo día
	 * @return {@code true} si {@code d1} es posterior a {@code d2}
	 */
	// compareTo compara las posiciones de declaración: positivo si d1 va después de d2.
	public static boolean esPosterior(DiaSemana d1, DiaSemana d2) {
		return d1.compareTo(d2) > 0;
	}

}
