package tema6;

/**
 * Programa de prueba de {@link Notificable}: usa personas y agentes a través
 * de la interfaz para ver polimorfismo, {@code instanceof} y casts.
 */
public class PruebasNotificable {

	/**
	 * Crea una persona y varios agentes, los notifica y los recorre guardados en
	 * un array de {@code Notificable}, mostrando su tipo real.
	 *
	 * @param args argumentos de la línea de comandos (no se usan)
	 */
	public static void main(String[] args) {

		try {
			Persona p = new Persona("Pablo", "pablo@example.com", 20);
			AgenteIA a = new AgenteIA("claudecode", "fable-5.2", "hermesnesildo");

			IO.println(p.getDestinatario());
			IO.println(a.getDestinatario());
			// Mismo método, comportamiento distinto según la clase del objeto.
			p.notifica("Enhorabuena! Aprobaste el examen.");
			a.notifica("Gracias por arreglar el bug del código");

			// Una variable del tipo de la interfaz puede guardar cualquier objeto que la implemente.
			Notificable n1 = p;
			Notificable n2 = a;
			Notificable n3 = new AgenteIA("codex", "gpt-6.1-sol", "margarito");

			// Un array de la interfaz mezcla personas y agentes (polimorfismo).
			// Las posiciones sin asignar valen null.
			Notificable[] notificables = new Notificable[10];
			notificables[0] = n1;
			notificables[1] = n2;
			notificables[2] = n3;
			notificables[4] = new Persona("Marcos", "marcos@example.com", 30);

			for (Notificable n : notificables) {
				// Evita NullPointerException en las posiciones vacías.
				if (n == null) {
					continue;
				}
				// Con el tipo Notificable solo se pueden llamar los métodos de la interfaz.
				IO.println("Destinatario: " + n.getDestinatario());
				// instanceof comprueba el tipo real del objeto; el cast permite usar sus métodos propios.
				if (n instanceof Persona) {
					IO.println("Tipo: Persona");
					Persona persona = (Persona) n;
					IO.println(persona.getEdad());
				} else if(n instanceof AgenteIA) {
					IO.println("Tipo: AgenteIA");
					AgenteIA agente = (AgenteIA) n;
					IO.println(agente.getModelo());
				} else {
					IO.println("Tipo desconocido");
				}
				IO.println("******");
			}

		} catch (Exception e) {
			// El constructor de Persona puede lanzar una excepción de validación.
			e.printStackTrace();
		}
	}

}
