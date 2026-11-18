package tema6;

/**
 * Ejemplo de upcasting: muestra con {@code toString()} una {@link Persona} y un
 * {@link AgenteIA}.
 */
public class PruebasUpcasting {

	/**
	 * Crea una persona y un agente e imprime ambos.
	 *
	 * @param args no se usa
	 */
	public static void main(String[] args) {

		try {
			// El constructor de Persona valida los datos y puede lanzar una excepción, por eso va en un try.
			Persona p = new Persona("pablo", "pablo@pablo.com", 90);
			// println llama a toString() del objeto. Si la clase no lo redefine, se usa el de Object
			// (nombre de la clase + @ + código hash).
			IO.println(p);
			AgenteIA a = new AgenteIA("codex", "astra", "miprimo");
			IO.println(a);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
