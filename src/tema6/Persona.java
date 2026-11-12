package tema6;

// Una clase puede implementar varias interfaces: Notificable (contrato propio) y
// Comparable<Persona> (de Java, define el orden natural de las personas).
public class Persona implements Notificable, Comparable<Persona> {

	// static comparte la constante entre todas las personas; final impide reasignarla.
	private static final int EDAD_MAXIMA = 200;

	public static int getEdadMaxima() {
		return EDAD_MAXIMA;
	}
	
	private String nombre;
	private String email;
	private int edad;


	public Persona(String nombre, String email, int edad) throws Exception {
		// Reutilizamos los setters para aplicar las mismas comprobaciones al construir.
		this.setNombre(nombre);
		this.setEmail(email);
		this.setEdad(edad);
	}
	
	public Persona(String nombre, int edad) throws Exception {
		// this(...) llama al otro constructor; null representa la ausencia de correo.
		this(nombre, null, edad);
	}

	public Persona() throws Exception {
		this("Nombre desconocido", null, 0);
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public String getEmail() {
		return this.email;
	}

	public int getEdad() {
		return this.edad;
	}

	// Los setters validan antes de cambiar el estado del objeto.
	public void setNombre(String nombre) throws Exception {
		if (nombre == null || nombre.length() == 0) {
			throw new Exception("No te voy a dejar que borres el nombre...");
		}
		this.nombre = nombre;
	}

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

	public void setEdad(int edad) throws Exception {
		if (edad < 0) {
			throw new Exception("No admito edades negativas");
		}
		
		if (edad > EDAD_MAXIMA) {
			throw new Exception("No admito edades tan altas...");
		}
		
		this.edad = edad;
	}

	// Java utiliza esta representación al imprimir la persona.
	public String toString() {
		String msg = "Persona[" 
	+ "nombre = " + this.nombre 
	+ " - email = " + this.email
	+ " - edad = " + this.edad + " ]";
		return msg;
	}

	// Sobrecarga equals con un parámetro Persona, pero no sobrescribe equals(Object).
	// Comprueba p, pero no los atributos null antes de llamar a equals sobre ellos.
	public boolean equals(Persona p) {
		return (p != null 
				&& this.nombre.equals(p.nombre) 
				&& this.email.equals(p.email) 
				&& this.edad == p.edad);
	}

	// @Override pide al compilador comprobar que el método existe en la interfaz.
	// Para una persona, el destinatario de la notificación es su correo.
	@Override
	public String getDestinatario() {
		return this.getEmail();
	}

	@Override
	public void notifica(String mensaje) {
		// Por ahora solo se simula el envío por consola.
		// Para enviarlo de verdad se podría usar EmailHelper.sendEmail(...).
		IO.println("Enviando una notificación a: " + this.getDestinatario() + " -> " + mensaje);
	}

	// Reutiliza setEmail para aplicar las mismas validaciones.
	@Override
	public void setDestinatario(String destinatario) throws Exception {
		this.setEmail(destinatario);
	}

	// compareTo devuelve negativo, cero o positivo según el orden de this respecto a otro.
	// Con otro.edad - this.edad el orden es de mayor a menor edad (descendente).
	// Con this.edad - otro.edad sería ascendente. No contempla que otro sea null.
	@Override
	public int compareTo(Persona otro) {
		return otro.edad - this.edad;
	}
	
}
