package tema6;

import java.util.HashSet;
import java.util.Set;

// Pese al nombre del archivo, aquí se usa un Set (conjunto), no una lista.
public class EjemploListas {

	public static void main(String[] args) {
		// Set es la interfaz; HashSet, la implementación elegida. Programar contra la interfaz permite cambiarla.
		Set<String> conjunto = new HashSet<String>();
		conjunto.add("hola");
		conjunto.add("adiós");
		// Un conjunto no admite duplicados: las repeticiones de "hola" se ignoran.
		conjunto.add("hola");
		conjunto.add("hola");
		conjunto.add("hola");
		conjunto.add("hola");
		conjunto.add("hola");
		conjunto.add("hola");
		conjunto.add("hola");
		conjunto.add("hola");


		// Eliminar un elemento que no está no falla: remove devuelve false.
		conjunto.remove("hasta luego");
		// size() cuenta elementos distintos: aquí 2.
		IO.println(conjunto.size());
		// HashSet no garantiza el orden de recorrido.
		for (String n : conjunto) {
			IO.println(n);
		}
		IO.println();
	}

}
