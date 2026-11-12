package tema6;

// Una interfaz es un contrato: declara qué métodos ofrece, sin decir cómo se implementan.
// Cada clase que la implemente debe proporcionar el cuerpo de todos ellos.
public interface Notificable {

	public String getDestinatario();
	// Un método de interfaz puede declarar throws: las implementaciones pueden lanzar esa excepción.
	public void setDestinatario(String destinatario) throws Exception;
	public void notifica(String mensaje);

}
