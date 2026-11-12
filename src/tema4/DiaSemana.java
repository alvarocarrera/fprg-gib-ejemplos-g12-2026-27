package tema4;

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


	// compareTo compara las posiciones de declaración: positivo si d1 va después de d2.
	public static boolean esPosterior(DiaSemana d1, DiaSemana d2) {
		return d1.compareTo(d2) > 0;
	}

}
