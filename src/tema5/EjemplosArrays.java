package tema5;

import java.util.Random;

public class EjemplosArrays {

	public static void main(String[] args) {

		// Cada array tiene un tamaño fijo, indicado al crearlo.
		// Reduce cantidad si quieres obtener menos líneas en la consola.
		int cantidad = 20000;
		
		// La posición i de ambos arrays describe a la misma persona.
		// Inicialmente los double valen 0.0 y las referencias String, null.
		double[] alturas = new double[cantidad];
		String[] nombres = new String[cantidad];
		
		// Con la misma semilla se obtiene la misma secuencia pseudoaleatoria.
		long semilla = -457;
		IO.println("Seed: " + semilla);
		Random r = new Random(semilla);
		
		// length es el número de elementos; el último índice es length - 1.
		// La condición doble evita salir de cualquiera de los dos arrays.
		for(int i = 0; i < nombres.length && i < alturas.length; i++) {
			// nextDouble() devuelve un valor en [0, 1): altura en [1,50, 2,00) metros.
			double altura = (r.nextDouble() * (100.0/2.0) + 150.0)/100.0;
			nombres[i] = "Nombre nº" + i;
			alturas[i] = altura;
		}
		
		
		
		// Recorremos de nuevo los mismos índices para mostrar cada pareja.
		for(int i = 0; i < nombres.length && i < alturas.length; i++) {
			String nombre = nombres[i];
			double altura = alturas[i];
			IO.println("Nombre " + i + ": " + nombre);
			IO.println("Altura " + i + ": " + altura);	
		}
	}
}
