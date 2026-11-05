package tema5;

public class PruebasRegistroAltura {

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
