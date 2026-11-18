package tema6;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Pruebas manuales de {@link GestorBonusVentas}: altas, ventas, errores
 * esperados, reparto de bonus y backup.
 */
public class PruebasGestorVentas {

	/**
	 * Ejecuta las pruebas e imprime el resultado de cada una por consola.
	 *
	 * @param args no se usa
	 */
	public static void main(String[] args) {
		// Se declara fuera del try para poder usar la variable después del bloque.
		GestorBonusVentas gestor = null;

		// Intento 1: cargar el estado previo desde gestor.csv.
		try {
			gestor = new GestorBonusVentas("gestor.csv");
		} catch (Exception e) {
			// Si falla (fichero inexistente, formato incorrecto...) se informa y se sigue con un gestor vacío.
			System.err.println("ERROR: " + e.getMessage());
			System.out.println("Continuamos con un gestor vacío...");
			try {
				gestor = new GestorBonusVentas();
			} catch (FileNotFoundException e1) {
				System.err.println("No se ha podido crear el historial");
				// Sin historial no se puede continuar: exit(1) termina el programa (código != 0 = error).
				System.exit(1);
			}
		}

		// Test 1: caso normal. No debe lanzar ninguna excepción.
		try {

			gestor.registraNuevoVendedor("Manolo");
			gestor.registraNuevoVendedor("María");

			gestor.registraVenta("Manolo", 100);
			gestor.registraVenta("María", 150);

			IO.println("Test 1: OK");
		} catch (Exception e) {
			IO.println("FALLO: " + e.getMessage());
			e.printStackTrace();
			
		}

		// Test 2: registrar dos veces al mismo vendedor DEBE lanzar excepción.
		// Aquí el test pasa cuando salta el catch; si llega al println de ERROR, el gestor falla.
		try {
			gestor.registraNuevoVendedor("Manolo");
			IO.println("ERROR: debería haber lanzado una excepción....");
		} catch (Exception e) {
			IO.println("Test 2: OK");
		}

		// Test 3: vender con un vendedor no registrado ("Manuel", no "Manolo") DEBE lanzar excepción.
		try {
			gestor.registraVenta("Manuel", 200);
			IO.println("ERROR: debería haber lanzado una excepción....");
		} catch (Exception e) {
			IO.println("Test 3: OK");
		}

		// Test 4: Manolo vendió 100 en el Test 1. Comparar doubles con == vale aquí porque son valores exactos.
		if (gestor.getVentas("Manolo") == 100) {
			IO.println("Test 4: OK");
		} else {
			IO.println("Test 4: ERROR");
		}

		// Test 5: las ventas se acumulan. María: 150 (Test 1) + 50 = 200.
		// De paso se dan de alta más vendedores para tener datos en el reparto del bonus.
		try {
			gestor.registraVenta("María", 50);
			gestor.registraNuevoVendedor("Enrique");
			gestor.registraNuevoVendedor("Luisa");
			gestor.registraVenta("Luisa", 500);
			gestor.registraVenta("Enrique", 5000);
			
			if (gestor.getVentas("María") == 200) {
				IO.println("Test 5: OK");
			} else {
				IO.println("Test 5: ERROR");
			}
		} catch (Exception e) {
			IO.println("ERROR al registrar venta que no debería haber saltado");
		}
		
		// Reparto de 1000 € de bonus. Se recorre el mapa devuelto con entrySet(): clave = vendedor, valor = bonus.
		// Un HashMap no garantiza orden, así que el orden de salida puede variar.
		Map<String, Double> bonusParaRepartir = gestor.reparteBonus(1000);
		for(Entry<String, Double> entrada : bonusParaRepartir.entrySet()) {
			IO.println(entrada.getKey() + " -> " + entrada.getValue() + " €");
		}
		
		
		
		// Guarda una copia del estado en gestor-<milisegundos>.bak.
		try {
			gestor.guardaBackup("gestor");
		} catch (Exception e) {
			System.err.println("No se pudo guardar el backup...");
			e.printStackTrace();
		}
		
	}

}
