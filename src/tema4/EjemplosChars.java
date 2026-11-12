package tema4;

public class EjemplosChars {

	public static void main(String[] args) {

		// Un char es un número (código Unicode); el cast (char) convierte el int en su carácter.
		// Se muestran miles de caracteres: la consola puede no tener fuente para todos.
		for(int i = 10000; i < 15000; i++) {
			char c = (char) i;
			IO.println(c);
		}


	}

}
