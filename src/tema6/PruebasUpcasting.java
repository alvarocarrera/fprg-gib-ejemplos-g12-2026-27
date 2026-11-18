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
			Persona p = new Persona("pablo", "pablo@pablo.com", 90);
			IO.println(p);
			AgenteIA a = new AgenteIA("codex", "astra", "miprimo");
			IO.println(a);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
