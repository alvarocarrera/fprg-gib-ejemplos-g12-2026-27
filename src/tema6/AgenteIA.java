package tema6;

// implements: AgenteIA se compromete a cumplir el contrato de Notificable.
public class AgenteIA implements Notificable {

	private String harness;
	private String modelo;
	private String nombre;




	public AgenteIA(String harness, String modelo, String nombre) {
		super();
		this.harness = harness;
		this.modelo = modelo;
		this.nombre = nombre;
	}



	@Override
	public String toString() {
		return "AgenteIA [harness=" + harness + ", modelo=" + modelo + ", nombre=" + nombre + "]";
	}



	public String getHarness() {
		return harness;
	}



	public void setHarness(String harness) {
		this.harness = harness;
	}



	public String getModelo() {
		return modelo;
	}



	public void setModelo(String modelo) {
		this.modelo = modelo;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	// @Override pide al compilador comprobar que el método existe en la interfaz.
	// El destinatario de un agente es <harness>-<nombre>, el mismo formato que acepta setDestinatario.
	@Override
	public String getDestinatario() {
		return this.getHarness()+"-"+this.getNombre();
	}

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

	// Cada clase implementa notifica a su manera: Persona escribiría un correo, el agente avisa por consola.
	@Override
	public void notifica(String mensaje) {
		IO.println("Notificando al agente: " + this.nombre + " usando el harness: " + this.harness + " y el modelo: " + this.modelo + " -> " + mensaje);
	}

}
