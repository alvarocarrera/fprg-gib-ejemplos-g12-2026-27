package tema3;

/**
 * Chatbot sencillo que guarda hasta cinco mensajes escritos por el usuario.
 *
 * <p>Practica un bucle {@code while (true)} con {@code break}, un array de
 * {@code String} con un contador de posiciones ocupadas y el recorrido posterior
 * del array ignorando las posiciones vacías ({@code null}). El usuario escribe
 * {@code FIN} (en mayúsculas o minúsculas) para terminar.
 */
public class ChatBotFPRG {
	
	/**
	 * Lee mensajes hasta que se escribe {@code FIN} o el historial se llena, y
	 * después muestra los mensajes guardados con su posición.
	 *
	 * @param args argumentos de la línea de comandos (no se usan)
	 */
	public static void main(String[] args) {

		IO.println("Comienza el chatbot: ");

		// El array tiene cinco posiciones; contador indica cuántas están ocupadas.
		String[] historial = new String[5];
		int contador = 0;
		// El bucle es indefinido: sale mediante break al cumplirse alguna condición.
		while (true) {
			// Se lee antes de comprobar el límite: con el historial lleno se pide otro mensaje.
			String msg = IO.readln("> ");
			// equalsIgnoreCase compara el contenido sin distinguir mayúsculas.
			if (msg.equalsIgnoreCase("FIN") || contador == historial.length) {
				break;
			} else {
				// Primero se usa la posición actual y después se incrementa contador.
				historial[contador++] = msg;
				IO.println("Mensaje guardado");
			}
		}

		// Las posiciones que no se han rellenado conservan el valor null.
		for (int i = 0; i < historial.length; i++) {
			if (historial[i] != null) {
				IO.println("Mensaje nº: " + i + " -> " + historial[i]);
			}
		}

		IO.println("Termina el chatbot FPRG");

	}
	
}
