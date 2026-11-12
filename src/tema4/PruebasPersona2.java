package tema4;

public class PruebasPersona2 {

	public static void main(String[] args) {

		try {
			Persona p1 = new Persona("María", "maria@example.com", 25);
			Persona p2 = new Persona("Laura", "laura@example.com", 20);
			int edad = 10;
			System.out.println("edad antes:" + edad);
			// Paso por valor: setEdad recibe una copia de edad, que en main no cambia.
			p1.setEdad(edad);
			System.out.println("edad después:" + edad);
			System.out.println("¿iguales? " + p1.equals(p2));
			System.out.println(p1);
			System.out.println(p2);

		} catch(Exception e) {
			// Catch vacío: oculta cualquier error. Mejor mostrar al menos el mensaje.
		}

	}

}
