package tema4;

/**
 * Persona con nombre, correo electrónico y edad, con validación de datos.
 *
 * <p>Es el ejemplo central de encapsulación del tema: atributos privados,
 * varios constructores, setters que validan y lanzan excepciones, una constante
 * estática, {@code toString} y un {@code equals}.
 *
 * <p>Nota didáctica: {@link #equals(Persona)} es una sobrecarga, no sobrescribe
 * {@code equals(Object)}, y falla si el correo es {@code null}.
 */
public class Persona {

	/**
	 * Edad máxima admitida para una persona.
	 */
	// static comparte la constante entre todas las personas; final impide reasignarla.
	// Es privada: se consulta desde fuera con el método estático getEdadMaxima().
	private static final int EDAD_MAXIMA = 200;

	/**
	 * Devuelve la edad máxima admitida. Al ser estático se llama con el nombre de
	 * la clase: {@code Persona.getEdadMaxima()}.
	 *
	 * @return la edad máxima permitida
	 */
	// Un método static se invoca con el nombre de la clase: Persona.getEdadMaxima().
	public static int getEdadMaxima() {
		return EDAD_MAXIMA;
	}

	/**
	 * Nombre de la persona. Nunca es {@code null} ni vacío.
	 */
	private String nombre;
	/**
	 * Correo electrónico de la persona, o {@code null} si no tiene.
	 */
	private String email;
	/**
	 * Edad en años, entre 0 y {@link #EDAD_MAXIMA}.
	 */
	private int edad;


	/**
	 * Crea una persona con todos sus datos, validándolos con los setters.
	 *
	 * @param nombre nombre, no nulo ni vacío
	 * @param email  correo electrónico, o {@code null} si no tiene
	 * @param edad   edad entre 0 y la edad máxima
	 * @throws Exception si algún dato no es válido
	 */
	public Persona(String nombre, String email, int edad) throws Exception {
		// Reutilizamos los setters para aplicar las mismas comprobaciones al construir.
		this.setNombre(nombre);
		this.setEmail(email);
		this.setEdad(edad);
	}

	/**
	 * Crea una persona sin correo electrónico.
	 *
	 * @param nombre nombre, no nulo ni vacío
	 * @param edad   edad entre 0 y la edad máxima
	 * @throws Exception si algún dato no es válido
	 */
	public Persona(String nombre, int edad) throws Exception {
		// this(...) llama al otro constructor; null representa la ausencia de correo.
		this(nombre, null, edad);
	}

	/**
	 * Crea una persona con nombre «Nombre desconocido», sin correo y con edad 0.
	 *
	 * @throws Exception si algún dato no es válido (no ocurre con estos valores)
	 */
	public Persona() throws Exception {
		this("Nombre desconocido", null, 0);
	}

	/**
	 * Devuelve el nombre de la persona.
	 *
	 * @return el nombre
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * Devuelve el correo electrónico de la persona.
	 *
	 * @return el correo, o {@code null} si no tiene
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Devuelve la edad de la persona.
	 *
	 * @return la edad en años
	 */
	public int getEdad() {
		return this.edad;
	}

	/**
	 * Cambia el nombre de la persona.
	 *
	 * @param nombre nuevo nombre, no nulo ni vacío
	 * @throws Exception si el nombre es {@code null} o está vacío
	 */
	// Los setters validan antes de cambiar el estado del objeto.
	public void setNombre(String nombre) throws Exception {
		if (nombre == null || nombre.length() == 0) {
			throw new Exception("No te voy a dejar que borres el nombre...");
		}
		this.nombre = nombre;
	}

	/**
	 * Cambia el correo electrónico. Admite {@code null} (sin correo); si no es
	 * {@code null}, debe tener contenido y exactamente una {@code @}.
	 *
	 * @param email nuevo correo, o {@code null}
	 * @throws Exception si está vacío, no tiene {@code @} o tiene más de una
	 */
	public void setEmail(String email) throws Exception {
		// Son comprobaciones introductorias, no una validación completa del correo.
		if (email == null) {
			this.email = null;
			return;
		}

		if (email.length() == 0) {
			throw new Exception("No puedes tener un email que no sea null pero tampoco tenga contenido.");
		}
		if (!email.contains("@")) {
			throw new Exception("El email " + email + " no contiene ninguna @");
		}

		if (email.split("@").length != 2) {
			throw new Exception("Email mal formado, no me cuadra el número de @ -> " + email);
		}

		this.email = email;
	}

	/**
	 * Cambia la edad de la persona.
	 *
	 * @param edad nueva edad, entre 0 y la edad máxima
	 * @throws Exception si es negativa o supera la edad máxima
	 */
	public void setEdad(int edad) throws Exception {
		if (edad < 0) {
			throw new Exception("No admito edades negativas");
		}

		if (edad > EDAD_MAXIMA) {
			throw new Exception("No admito edades tan altas...");
		}

		this.edad = edad;
	}

	/**
	 * Devuelve una representación en texto de la persona con todos sus datos.
	 *
	 * @return texto con nombre, correo y edad
	 */
	// Java utiliza esta representación al imprimir la persona.
	public String toString() {
		String msg = "Persona["
	+ "nombre = " + this.nombre
	+ " - email = " + this.email
	+ " - edad = " + this.edad + " ]";
		return msg;
	}

	/**
	 * Compara esta persona con otra por el contenido de sus atributos.
	 *
	 * <p>Es una sobrecarga de {@code equals}, no una sobrescritura de
	 * {@code equals(Object)}. Lanza {@link NullPointerException} si el correo de
	 * esta persona es {@code null}.
	 *
	 * @param p persona con la que comparar
	 * @return {@code true} si nombre, correo y edad coinciden; {@code false} si
	 *         {@code p} es {@code null} o algún dato difiere
	 */
	// Sobrecarga equals con un parámetro Persona, pero no sobrescribe equals(Object).
	// Comprueba p, pero no los atributos null antes de llamar a equals sobre ellos.
	public boolean equals(Persona p) {
		return (p != null
				&& this.nombre.equals(p.nombre)
				&& this.email.equals(p.email)
				&& this.edad == p.edad);
	}

}
