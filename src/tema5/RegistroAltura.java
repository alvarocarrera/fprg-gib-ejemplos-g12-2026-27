package tema5;

import java.util.Random;

/**
 * Registro de personas con su altura en centímetros, implementado con dos
 * arrays paralelos (nombres y alturas) que comparten el mismo índice.
 *
 * <p>Incluye métodos resueltos de recorrido (máximo, mínimo, posiciones,
 * contadores) y varios métodos marcados con {@code TODO} que los alumnos deben
 * implementar como ejercicio.
 */
public class RegistroAltura {

	/**
	 * Nombres de las personas; la posición {@code i} se corresponde con {@code alturas[i]}.
	 */
	// Arrays paralelos: nombre y altura de una persona comparten índice.
	private String[] nombres;
	/**
	 * Alturas en centímetros; la posición {@code i} se corresponde con {@code nombres[i]}.
	 */
	private int[] alturas;

	/**
	 * Crea un registro con espacio para {@code size} personas.
	 *
	 * @param size número de personas; debe ser mayor que cero
	 * @throws Exception si {@code size} es cero o negativo
	 */
	public RegistroAltura(int size) throws Exception {
		// No admitimos un registro vacío; ambos arrays tendrán igual longitud.
		if (size <= 0) {
			throw new Exception("No puedo crear arrays negativos y me niego a crear un array de tamaño cero...");
		}
		// Inicialmente los nombres son null y las alturas son 0.
		this.nombres = new String[size];
		this.alturas = new int[size];
	}

	/**
	 * Rellena el registro con nombres «Nombre nº i» y alturas aleatorias entre 150
	 * y 200 cm. La misma semilla produce siempre los mismos datos.
	 *
	 * @param seed semilla del generador aleatorio
	 */
	public void generacionAleatorio(long seed) {
		// La misma semilla produce la misma secuencia de alturas.
		Random r = new Random(seed);

		for (int i = 0; i < nombres.length && i < alturas.length; i++) {
			// nextInt(51) genera de 0 a 50: la altura va de 150 a 200 cm.
			int altura = r.nextInt(51) + 150;
			nombres[i] = "Nombre nº" + i;
			alturas[i] = altura;
		}
	}

	/**
	 * Muestra por consola el nombre y la altura de cada persona.
	 */
	// El mismo índice permite recuperar juntos el nombre y su altura.
	public void listar() {
		for (int i = 0; i < nombres.length && i < alturas.length; i++) {
			String nombre = nombres[i];
			int altura = alturas[i];
			IO.println("Nombre " + i + ": " + nombre);
			IO.println("Altura " + i + ": " + altura);
		}
	}

	/**
	 * Calcula la mayor altura del registro.
	 *
	 * @return la altura máxima en cm
	 */
	public int alturaMaxima() {
		// Partimos del menor int para que cualquier altura pueda superarlo.
		int maximo = Integer.MIN_VALUE;
		for(int i = 0; i < alturas.length; i++) {
			int altura = alturas[i];
			if (altura > maximo) {
				maximo = altura;
			}
		}
		return maximo;
	}
	
	/**
	 * Calcula la menor altura del registro, recorriéndolo con {@code for-each}.
	 *
	 * @return la altura mínima en cm
	 */
	public int alturaMinima() {
		// Recorremos los valores directamente: aquí no necesitamos el índice.
		int minimo = Integer.MAX_VALUE;
		for(int altura : alturas) {
			if (altura < minimo) {
				minimo = altura;
			}
		}
		return minimo;
	}
	
	/**
	 * Busca la posición de la persona más alta. Si hay empate, devuelve la
	 * primera.
	 *
	 * @return la posición de la altura máxima
	 */
	public int getPosMaximo() {
		// Guardamos tanto el mayor valor encontrado como su posición.
		// Con >, si hay empate se conserva la primera posición.
		int maximo = Integer.MIN_VALUE;
		int posMax = -1;
		for(int i = 0; i < alturas.length; i++) {
			int altura = alturas[i];
			if (altura > maximo) {
				maximo = altura;
				posMax = i;
			}
		}
		return posMax;
	}
	
	/**
	 * Ejercicio: debe devolver la posición de la persona más baja (la primera si
	 * hay empate). Aún no está implementado.
	 *
	 * @return la posición de la altura mínima (de momento, siempre -1)
	 */
	public int getPosMinimo() {
		// TODO: hacerlo como ejercicio
		return -1;
	}

	/**
	 * Ejercicio: debe calcular la media de las alturas. Aún no está implementado.
	 *
	 * @return la altura media en cm (de momento, siempre 0.0)
	 */
	// Ejercicio: devuelve la media de las alturas en centímetros.
	// Suma todos los valores y evita perder decimales al dividir.
	public double alturaMedia() {
		// TODO: implementar
		return 0.0;
	}

	/**
	 * Ejercicio: debe buscar la última posición con la altura indicada. Aún no
	 * está implementado.
	 *
	 * @param alturaBuscada altura que se busca, en cm
	 * @return la última posición con esa altura, o -1 si no hay ninguna (de momento, siempre -1)
	 */
	// Ejercicio: busca la última posición cuya altura sea igual a la indicada.
	// Devuelve -1 si ninguna persona tiene esa altura.
	public int getUltimaPosAltura(int alturaBuscada) {
		// TODO: implementar
		return -1;
	}

	/**
	 * Ejercicio: debe contar las alturas comprendidas entre dos límites, ambos
	 * incluidos. Aún no está implementado.
	 *
	 * @param minimo límite inferior, en cm
	 * @param maximo límite superior, en cm
	 * @return cuántas alturas están en el intervalo; 0 si {@code minimo > maximo} (de momento, siempre 0)
	 */
	// Ejercicio: cuenta las alturas que están entre los dos límites, incluidos.
	// Si minimo es mayor que maximo, devuelve 0.
	public int contarAlturasEntre(int minimo, int maximo) {
		// TODO: implementar
		return 0;
	}
	
	/**
	 * Devuelve el nombre de la persona en una posición.
	 *
	 * @param pos posición, entre 0 y el tamaño menos uno
	 * @return el nombre guardado en esa posición (puede ser {@code null} si no se generaron datos)
	 * @throws Exception si la posición está fuera de rango
	 */
	public String getNombrePos(int pos) throws Exception {
		// length no es un índice válido; la última posición es length - 1.
		if (pos < 0 || pos >= this.nombres.length) {
			throw new Exception("Posición no válida. Introduce un número entre: 0 y " + (this.nombres.length - 1));
		}
		return this.nombres[pos];
	}
	
	/**
	 * Cuenta cuántas personas miden al menos el umbral indicado.
	 *
	 * @param umbral altura mínima, en cm (incluida)
	 * @return número de personas con altura mayor o igual que el umbral
	 */
	public int getNumPersonasMasAtlas(int umbral) {
		// Contamos también las alturas que son exactamente iguales al umbral.
		int contador = 0;
		for(int altura : this.alturas) {
			if (altura >= umbral) {
				contador++;
			}
		}
		return contador;
	}
	
	/**
	 * Busca la primera persona cuya altura alcanza el umbral.
	 *
	 * @param umbral altura mínima, en cm (incluida)
	 * @return la primera posición con altura mayor o igual que el umbral, o -1 si no hay ninguna
	 */
	public int getPosAlturaMinima(int umbral) {
		// Devuelve la primera posición que alcanza el umbral, o -1 si no existe.
		for(int i = 0; i < alturas.length; i++) {
			int altura = alturas[i];
			if (altura >= umbral) {
				return i;
			}
		}
		return -1;
	}
}
