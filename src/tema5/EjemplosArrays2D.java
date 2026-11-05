package tema5;

public class EjemplosArrays2D {

	public static void main(String[] args) {
		// Un array bidimensional es un array cuyas posiciones contienen arrays.
		// Aquí creamos 9 filas, cada una con 5 int inicialmente a 0.
		// Filas válidas: 0 a 8; columnas iniciales: 0 a 4.
		int[][] clase = new int[9][5];
		
		// Primer índice: fila; segundo índice: columna (ambos empiezan en 0).
		clase[1][0] = 168;
		
		// fila1 referencia el mismo array que clase[1], no es una copia.
		int[] fila1 = clase[1];
		fila1[1] = 165;
		fila1[2] = 164;

		// Imprime 168, 165 y 164: los cambios a través de fila1 se ven en clase.
		IO.println(clase[1][0]);
		IO.println(clase[1][1]);
		IO.println(clase[1][2]);
		
		// Sustituimos la fila por otra nueva: sus nueve valores empiezan en 0.
		// fila1 sigue apuntando a la fila anterior, que conserva 168, 165 y 164.
		clase[1] = new int[9];
		
		// Ahora imprime 0, 0 y 0: clase[1] apunta a la fila nueva.
		IO.println(clase[1][0]);
		IO.println(clase[1][1]);
		IO.println(clase[1][2]);
		
		
		// clase.length cuenta filas, no el total de elementos. Tras la sustitución,
		// la fila 1 tiene 9 columnas y las demás 5; el total es 8 * 5 + 9 = 49.
		// clase[i].length permite recorrer cada fila completa sin salir de sus límites.
		for(int i = 0; i < clase.length; i++) {
			// Por cada fila i, j recorre sus columnas desde 0 hasta length - 1.
			for(int j = 0; j < clase[i].length; j++) {
				IO.println(i+ "-" + j + " -> " + clase[i][j]);
			}
		}
		
		// El for-each exterior obtiene cada fila; el interior, cada altura.
		// Es cómodo para leer valores, pero aquí no proporciona sus índices.
		for(int[] fila : clase) {
			for(int altura : fila) {
				IO.println(altura);
			}
		}
		
	}
}
