package tema6;

/**
 * Contrato de lo que puede recibir notificaciones.
 *
 * <p>Una interfaz declara qué métodos se ofrecen sin decir cómo se
 * implementan. Cada clase que la implementa ({@link Persona}, {@link AgenteIA})
 * decide cómo hacerlo.
 */
// Una interfaz es un contrato: declara qué métodos ofrece, sin decir cómo se implementan.
// Cada clase que la implemente debe proporcionar el cuerpo de todos ellos.
public interface Notificable {

	/**
	 * Devuelve la dirección o identificador al que se envían las notificaciones.
	 *
	 * @return el destinatario
	 */
	public String getDestinatario();
	/**
	 * Cambia el destinatario de las notificaciones.
	 *
	 * @param destinatario nuevo destinatario
	 * @throws Exception si el destinatario no tiene un formato válido
	 */
	// Un método de interfaz puede declarar throws: las implementaciones pueden lanzar esa excepción.
	public void setDestinatario(String destinatario) throws Exception;
	/**
	 * Envía una notificación con el mensaje indicado.
	 *
	 * @param mensaje texto de la notificación
	 */
	public void notifica(String mensaje);

}
