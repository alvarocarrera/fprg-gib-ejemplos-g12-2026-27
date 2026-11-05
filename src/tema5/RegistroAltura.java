package tema5;

import java.util.Random;

public class RegistroAltura {

	// Arrays paralelos: nombre y altura de una persona comparten índice.
	private String[] nombres;
	private int[] alturas;

	public RegistroAltura(int size) throws Exception {
		// No admitimos un registro vacío; ambos arrays tendrán igual longitud.
		if (size <= 0) {
			throw new Exception("No puedo crear arrays negativos y me niego a crear un array de tamaño cero...");
		}
		// Inicialmente los nombres son null y las alturas son 0.
		this.nombres = new String[size];
		this.alturas = new int[size];
	}

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

	// El mismo índice permite recuperar juntos el nombre y su altura.
	public void listar() {
		for (int i = 0; i < nombres.length && i < alturas.length; i++) {
			String nombre = nombres[i];
			int altura = alturas[i];
			IO.println("Nombre " + i + ": " + nombre);
			IO.println("Altura " + i + ": " + altura);
		}
	}

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
	
	public int getPosMinimo() {
		// TODO: hacerlo como ejercicio
		return -1;
	}

	// Ejercicio: devuelve la media de las alturas en centímetros.
	// Suma todos los valores y evita perder decimales al dividir.
	public double alturaMedia() {
		// TODO: implementar
		return 0.0;
	}

	// Ejercicio: busca la última posición cuya altura sea igual a la indicada.
	// Devuelve -1 si ninguna persona tiene esa altura.
	public int getUltimaPosAltura(int alturaBuscada) {
		// TODO: implementar
		return -1;
	}

	// Ejercicio: cuenta las alturas que están entre los dos límites, incluidos.
	// Si minimo es mayor que maximo, devuelve 0.
	public int contarAlturasEntre(int minimo, int maximo) {
		// TODO: implementar
		return 0;
	}
	
	public String getNombrePos(int pos) throws Exception {
		// length no es un índice válido; la última posición es length - 1.
		if (pos < 0 || pos >= this.nombres.length) {
			throw new Exception("Posición no válida. Introduce un número entre: 0 y " + (this.nombres.length - 1));
		}
		return this.nombres[pos];
	}
	
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
