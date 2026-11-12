package tema6;

/**
 * Persona con nombre, correo y edad, que se puede notificar y ordenar.
 *
 * <p>Evoluciona la clase de {@code tema4}: implementa {@link Notificable}
 * (su destinatario es el correo) y {@link Comparable} (orden por edad, de mayor
 * a menor). Los setters validan los datos y lanzan excepciones.
 */
// Una clase puede implementar varias interfaces: Notificable (contrato propio) y
// Comparable<Persona> (de Java, define el orden natural de las personas).
public class Persona implements Notificable, Comparable<Persona> {

	/**
	 * Edad máxima admitida para una persona.
	 */
	// static comparte la constante entre todas las personas; final impide reasignarla.
	private static final int EDAD_MAXIMA = 200;

	/**
	 * Devuelve la edad máxima admitida.
	 *
	 * @return la edad máxima permitida
	 */
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

	/**
	 * Devuelve el destinatario de las notificaciones: el correo de la persona.
	 *
	 * @return el correo, o {@code null} si no tiene
	 */
	// @Override pide al compilador comprobar que el método existe en la interfaz.
	// Para una persona, el destinatario de la notificación es su correo.
	@Override
	public String getDestinatario() {
		return this.getEmail();
	}

	/**
	 * Notifica a la persona. De momento solo simula el envío escribiendo el
	 * mensaje por consola.
	 *
	 * @param mensaje mensaje que se envía
	 */
	@Override
	public void notifica(String mensaje) {
		// Por ahora solo se simula el envío por consola.
		// Para enviarlo de verdad se podría usar EmailHelper.sendEmail(...).
		IO.println("Enviando una notificación a: " + this.getDestinatario() + " -> " + mensaje);
	}

	/**
	 * Cambia el destinatario, es decir, el correo de la persona.
	 *
	 * @param destinatario nuevo correo, con las mismas reglas que {@link #setEmail(String)}
	 * @throws Exception si el correo no es válido
	 */
	// Reutiliza setEmail para aplicar las mismas validaciones.
	@Override
	public void setDestinatario(String destinatario) throws Exception {
		this.setEmail(destinatario);
	}

	/**
	 * Compara esta persona con otra por edad, de mayor a menor.
	 *
	 * @param otro persona con la que comparar (no debe ser {@code null})
	 * @return negativo si esta persona es mayor, cero si tienen la misma edad y
	 *         positivo si es más joven
	 */
	// compareTo devuelve negativo, cero o positivo según el orden de this respecto a otro.
	// Con otro.edad - this.edad el orden es de mayor a menor edad (descendente).
	// Con this.edad - otro.edad sería ascendente. No contempla que otro sea null.
	@Override
	public int compareTo(Persona otro) {
		return otro.edad - this.edad;
	}
	
}
