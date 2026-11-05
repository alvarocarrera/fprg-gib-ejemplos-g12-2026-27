package tema5;

import java.util.Arrays;

public class MasEjemplosArrays {

	public static void main(String[] args) {
		// Inicialización literal: entre llaves escribimos los valores separados
		// por comas. Java crea un array de int con tantas posiciones como valores:
		// aquí length es 4. No hace falta indicar el tamaño con new int[4].
		// Los valores se guardan en orden: notas[0] es 7 y notas[3] es 6.
		// Los índices empiezan en 0 y llegan hasta length - 1; notas[4] falla.
		// Esta forma abreviada con llaves se usa al declarar la variable. En una
		// asignación posterior se escribe, por ejemplo: notas = new int[] {7, 5};
		int[] notas = {7, 5, 9, 6};
		// Acumulamos las notas antes de dividir entre la cantidad de elementos.
		int suma = 0;
		for (int i = 0; i < notas.length; i++) {
			suma += notas[i];
		}
		// La conversión evita que la división entre enteros descarte decimales.
		double media = (double) suma / notas.length;
		// toString representa los contenidos, no solo la referencia del array.
		IO.println("Notas: " + Arrays.toString(notas));
		IO.println("Media: " + media);

		// Una asignación comparte el array; clone crea otro independiente.
		int[] referencia = notas;
		// En un array de int, clone copia los valores a un array nuevo.
		int[] copia = notas.clone();
		referencia[0] = 8;
		IO.println("Original tras modificar la referencia: " + Arrays.toString(notas));
		IO.println("Copia sin modificar: " + Arrays.toString(copia));

		// Una matriz puede tener filas de diferente longitud: 3, 2 y 1 aquí.
		int[][] grupos = {
			{1, 2, 3}, 
			{4, 5},
			{6}
			};
		for (int fila = 0; fila < grupos.length; fila++) {
			for (int columna = 0; columna < grupos[fila].length; columna++) {
				IO.println("Grupo " + fila + ", posición " + columna + ": " + grupos[fila][columna]);
			}
		}
	}
}
