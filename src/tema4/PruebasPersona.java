package tema4;

public class PruebasPersona {

	public static void main(String[] args) {
		IO.println("Comenzamos a crear personas:");

		try {

			// Nombres y edades ficticios para probar constructores y setters.
			Persona p1 = new Persona("María", 20);
			Persona p2 = new Persona("Juan", 21);
			// example.com es un dominio reservado para ejemplos, no un correo real.
			p1.setEmail("hola@example.com");
			p1.setNombre("Juan Carlos");
			IO.println(p1.toString());
			// Al imprimir un objeto se utiliza su método toString().
			IO.println(p2);
			
			IO.println("¿son iguales? -> " + p1.equals(p2));
			
			IO.println("EDAD_MAXIMA = " + Persona.EDAD_MAXIMA);
			
			
			
//		IO.println("Nombre de p1: " + p1.getNombre());
//		IO.println("Email de p1: " + p1.getEmail());
//		IO.println("Edad de p1: " + p1.getEdad());
//		
//		IO.println("Nombre de p2: " + p2.getNombre());

//	p1.edad = -50;
//	p2.edad = 5067;

		} catch (Exception e) {
			System.err.println("ERROR: " + e.getMessage());
			e.printStackTrace();
		}
	}

}