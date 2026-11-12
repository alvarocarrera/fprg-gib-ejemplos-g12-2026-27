package tema4;

/**
 * Prueba principal de {@link Persona}: constructores, setters, referencias,
 * {@code ==} frente a {@code equals}, {@code toString}, constante estática y
 * captura de excepciones con varios {@code catch}.
 */
public class PruebasPersona {

	/**
	 * Crea varias personas, muestra que dos variables pueden referirse al mismo
	 * objeto, compara objetos y captura los errores de validación.
	 *
	 * @param args argumentos de la línea de comandos (no se usan)
	 */
	public static void main(String[] args) {
		IO.println("Comenzamos a crear personas:");

		// La construcción y los setters pueden lanzar excepciones de validación.
		try {
			// Nombres y edades ficticios para probar constructores y setters.
			Persona p1 = new Persona("José María", "jose@example.com", 20);
			// pAux no es un objeto nuevo: es otra referencia al mismo objeto que p1.
			Persona pAux = p1;
			Persona p2 = new Persona("Juan", 21);
			// example.com es un dominio reservado para ejemplos, no un correo real.
			// Al modificar pAux también cambia p1, porque ambas apuntan al mismo objeto.
			pAux.setEmail("hola@example.com");
			pAux.setNombre("Juan Carlos");
			IO.println(p1.toString());
			// Al imprimir un objeto se utiliza su método toString().
			IO.println(p2);

			// == compara referencias (si son el mismo objeto); equals compara el contenido.
			IO.println("¿son el mismo objeto? -> " + (p1 == p2));

			IO.println("¿son iguales? -> " + p1.equals(p2));

			IO.println("EDAD_MAXIMA = " + Persona.getEdadMaxima());


		// Con varios catch se ejecuta el primero compatible: va del tipo más específico al más general.
		} catch (NullPointerException e) {
			// Aquí no salta: los nombres difieren y && corta antes de tocar el email null.
			// Con el mismo nombre, equals llamaría a this.email.equals(...) sobre un null y lanzaría esta excepción.
			System.err.println("HAY ALGÚN NULL!!! -> " + e.getMessage());
			e.printStackTrace();
		} catch (Exception ex) {
			System.err.println("ERROR: " + ex.getMessage());
			ex.printStackTrace();
		}


		// Tras capturar la excepción anterior, el programa continúa con este segundo bloque.
		try {
			Persona p3 = new Persona("MariPili", "mari@example.com", 70);
			IO.println(p3);
		} catch (Exception e) {
			System.err.println("ERROR: " + e.getMessage());
			e.printStackTrace();
		}

		IO.println("El programa TERMINA NORMALMENTE");
	}

}
