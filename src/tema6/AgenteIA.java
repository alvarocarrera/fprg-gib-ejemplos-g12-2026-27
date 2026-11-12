package tema6;

/**
 * Agente de inteligencia artificial que se puede notificar.
 *
 * <p>Implementa la interfaz {@link Notificable}. Su destinatario tiene el
 * formato {@code <harness>-<nombre>}. Junto con {@link Persona} sirve para ver
 * polimorfismo: dos clases distintas que responden al mismo contrato.
 */
// implements: AgenteIA se compromete a cumplir el contrato de Notificable.
public class AgenteIA implements Notificable {

	/**
	 * Programa o entorno desde el que se ejecuta el agente (por ejemplo, {@code claudecode}).
	 */
	private String harness;
	/**
	 * Modelo de lenguaje que usa el agente.
	 */
	private String modelo;
	/**
	 * Nombre del agente.
	 */
	private String nombre;




	/**
	 * Crea un agente con los datos indicados.
	 *
	 * @param harness entorno desde el que se ejecuta
	 * @param modelo  modelo de lenguaje que usa
	 * @param nombre  nombre del agente
	 */
	public AgenteIA(String harness, String modelo, String nombre) {
		super();
		this.harness = harness;
		this.modelo = modelo;
		this.nombre = nombre;
	}



	/**
	 * Devuelve una representación en texto del agente.
	 *
	 * @return texto con harness, modelo y nombre
	 */
	@Override
	public String toString() {
		return "AgenteIA [harness=" + harness + ", modelo=" + modelo + ", nombre=" + nombre + "]";
	}



	/**
	 * Devuelve el harness del agente.
	 *
	 * @return el harness
	 */
	public String getHarness() {
		return harness;
	}



	/**
	 * Cambia el harness del agente.
	 *
	 * @param harness nuevo harness
	 */
	public void setHarness(String harness) {
		this.harness = harness;
	}



	/**
	 * Devuelve el modelo del agente.
	 *
	 * @return el modelo
	 */
	public String getModelo() {
		return modelo;
	}



	/**
	 * Cambia el modelo del agente.
	 *
	 * @param modelo nuevo modelo
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}



	/**
	 * Devuelve el nombre del agente.
	 *
	 * @return el nombre
	 */
	public String getNombre() {
		return nombre;
	}



	/**
	 * Cambia el nombre del agente.
	 *
	 * @param nombre nuevo nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	/**
	 * Devuelve el destinatario del agente, formado por harness y nombre.
	 *
	 * @return texto con el formato {@code <harness>-<nombre>}
	 */
	// @Override pide al compilador comprobar que el método existe en la interfaz.
	// El destinatario de un agente es <harness>-<nombre>, el mismo formato que acepta setDestinatario.
	@Override
	public String getDestinatario() {
		return this.getHarness()+"-"+this.getNombre();
	}

	/**
	 * Cambia harness y nombre a partir de un texto {@code <harness>-<nombre>}.
	 *
	 * @param destinatario texto con exactamente un guion y ambas partes no vacías
	 * @throws Exception si el formato no es válido
	 */
	@Override
	public void setDestinatario(String destinatario) throws Exception {
		// Validamos el formato antes de modificar el estado del agente.
		if(!destinatario.contains("-")) {
			throw new Exception("Destinatario mal formado. Un agente IA espera un formato: <harness>-<nombre>");
		}
		// split("-") separa el texto en las partes situadas entre guiones.
		String[] partes = destinatario.split("-");
		if(partes.length != 2) {
			throw new Exception("Destinatario mal formado. Un agente IA espera un formato: <harness>-<nombre> y al procesarlo, no tiene 2 partes.");
		}
		if (partes[0].length() == 0 || partes[1].length() == 0) {
			throw new Exception("Destinatario mal formado. Un agente IA espera un formato: <harness>-<nombre> y al procesarlo, alguna de las partes está vacía.");
		}
		this.setHarness(partes[0]);
		this.setNombre(partes[1]);
	}

	/**
	 * Notifica al agente mostrando el mensaje por consola.
	 *
	 * @param mensaje mensaje que se envía
	 */
	// Cada clase implementa notifica a su manera: Persona escribiría un correo, el agente avisa por consola.
	@Override
	public void notifica(String mensaje) {
		IO.println("Notificando al agente: " + this.nombre + " usando el harness: " + this.harness + " y el modelo: " + this.modelo + " -> " + mensaje);
	}

}
