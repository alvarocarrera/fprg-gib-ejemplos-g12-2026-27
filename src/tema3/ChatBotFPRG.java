package tema3;

public class ChatBotFPRG {
	
	public static void main(String[] args) {

		IO.println("Comienza el chatbot: ");

		// El array tiene cinco posiciones; contador indica cuántas están ocupadas.
		String[] historial = new String[5];
		int contador = 0;
		while (true) {
			// Se lee antes de comprobar el límite: con el historial lleno se pide otro mensaje.
			String msg = IO.readln("> ");
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