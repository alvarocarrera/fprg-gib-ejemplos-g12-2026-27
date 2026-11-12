package tema5;

/**
 * Programa de prueba de {@link RegistroAltura}: genera datos, los lista, busca
 * la altura máxima y provoca un acceso fuera de rango controlado.
 */
public class PruebasRegistroAltura {

	/**
	 * Crea un registro, lo rellena, muestra la altura máxima y pide el nombre de
	 * una posición inexistente para ver cómo se captura la excepción.
	 *
	 * @param args argumentos de la línea de comandos (no se usan)
	 */
	public static void main(String[] args) {

		// El constructor y getNombrePos pueden lanzar excepciones: las capturamos.
		try {
			RegistroAltura reg = new RegistroAltura(20);
			reg.generacionAleatorio(10);
			reg.listar();
			
			int alturaMaxima = reg.alturaMaxima();
			IO.println("La altura máxima es: " + alturaMaxima);
			
			// posMax sería una posición válida para recuperar el nombre de la persona.
			int posMax = reg.getPosMaximo();
			// Ejemplo de acceso fuera de rango: provoca la excepción del catch.
			IO.println(reg.getNombrePos(50));
		} catch (Exception e) {
			// Al saltar al catch no se ejecuta el resto del bloque try.
			IO.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
