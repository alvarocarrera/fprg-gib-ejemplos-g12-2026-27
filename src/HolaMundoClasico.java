public class HolaMundoClasico {

	// Punto de entrada clásico: Java empieza a ejecutar el programa aquí.
	public static void main(String[] args) {
		System.out.println("Hola Mundo");

		// Guardamos el valor devuelto por el método para mostrarlo después.
		double x = areaTriangulo(4, 6);
		System.out.println(x);
	}

	// Los parámetros reciben copias de los valores 4 y 6 de la llamada.
	// return entrega el resultado al código que invocó el método.
	static double areaTriangulo(double base, double altura) {
		return (base * altura) / 2.0;
	}

}
