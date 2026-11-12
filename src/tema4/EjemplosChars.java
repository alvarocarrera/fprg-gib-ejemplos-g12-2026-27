package tema4;

/**
 * Ejemplo que muestra que un {@code char} es un número (código Unicode).
 *
 * <p>Recorre un rango de números enteros y los convierte en caracteres con un
 * cast, para ver símbolos de otros alfabetos.
 */
public class EjemplosChars {

	/**
	 * Imprime los caracteres Unicode con códigos del 10000 al 14999.
	 *
	 * @param args argumentos de la línea de comandos (no se usan)
	 */
	public static void main(String[] args) {

		// Un char es un número (código Unicode); el cast (char) convierte el int en su carácter.
		// Se muestran miles de caracteres: la consola puede no tener fuente para todos.
		for(int i = 10000; i < 15000; i++) {
			char c = (char) i;
			IO.println(c);
		}


	}

}
