package tema5;

/**
 * Muestra los rangos de los tipos primitivos numéricos de Java.
 *
 * <p>Destaca que {@code MIN_VALUE} es el valor más negativo en los enteros pero
 * el menor valor positivo en {@code float} y {@code double}.
 */
public class EjemplosTiposDatos {

	/**
	 * Imprime los valores mínimo y máximo de cada tipo y algunas comparaciones
	 * con cero.
	 *
	 * @param args argumentos de la línea de comandos (no se usan)
	 */
	public static void main(String[] args) {

		// Tipos enteros con signo, de menor a mayor tamaño: 8, 16, 32 y 64 bits.
		byte b = 0;
		short s = 0;
		int i = 0;
		long l = 0;
		
		// Tipos de coma flotante; el sufijo f indica un literal float.
		double d = 0.0;
		float f = 0.0f;
		
		// En los tipos enteros con signo, MIN_VALUE es el valor más negativo
		// representable y MAX_VALUE el más positivo. Los límites son inclusivos.
		IO.println("byte:  " + Byte.MIN_VALUE + " a " + Byte.MAX_VALUE);
		IO.println("short: " + Short.MIN_VALUE + " a " + Short.MAX_VALUE);
		IO.println("int:   " + Integer.MIN_VALUE + " a " + Integer.MAX_VALUE);
		IO.println("long:  " + Long.MIN_VALUE + " a " + Long.MAX_VALUE);

		// char representa una unidad de código UTF-16 sin signo: sus límites
		// son 0 y 65535. El cast a int muestra los números en vez de caracteres.
		IO.println("char:  " + (int) Character.MIN_VALUE + " a " + (int) Character.MAX_VALUE);

		// En float y double, MIN_VALUE significa otra cosa: es el menor valor
		// POSITIVO distinto de cero que se puede representar, muy próximo a 0.
		// MAX_VALUE es el mayor valor finito positivo; el más negativo es
		// -MAX_VALUE. También existen -Infinity, Infinity y NaN.
		IO.println("float: mínimo finito = " + -Float.MAX_VALUE);
		IO.println("float: menor positivo distinto de cero = " + Float.MIN_VALUE);
		IO.println("float: máximo finito = " + Float.MAX_VALUE);
		IO.println("double: mínimo finito = " + -Double.MAX_VALUE);
		IO.println("double: menor positivo distinto de cero = " + Double.MIN_VALUE);
		IO.println("double: máximo finito = " + Double.MAX_VALUE);

		// Comparamos explícitamente con cero para destacar la diferencia.
		IO.println("Integer.MIN_VALUE < 0: " + (Integer.MIN_VALUE < 0));
		IO.println("Float.MIN_VALUE > 0: " + (Float.MIN_VALUE > 0));
		IO.println("Double.MIN_VALUE > 0: " + (Double.MIN_VALUE > 0));
	}

}
