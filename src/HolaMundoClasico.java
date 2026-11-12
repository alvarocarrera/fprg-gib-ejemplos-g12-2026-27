public class HolaMundoClasico {

	// Punto de entrada clásico: Java empieza a ejecutar el programa aquí.
	public static void main(String[] args) {
		String saludo = "Hola Mundo";
		System.out.println(saludo);

		// Guardamos el valor devuelto por el método para mostrarlo después.
		double base = 4;
		double altura = 6;
		// Ojo: esta línea cambia el valor de base antes del cálculo, así que el área mostrada es la de otro triángulo.
		base = base * altura;
		double areaCalculada = calcularAreaTriangulo(base, altura);
		System.out.println(areaCalculada);
	}

	// Los parámetros reciben copias de los valores de la llamada.
	// return entrega el resultado al código que invocó el método.
	static double calcularAreaTriangulo(double base, double altura) {
		return (base * altura) / 2.0;
	}

}
