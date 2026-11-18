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
		GestorBonusVentas gestor = null;
		
		try {
			gestor = new GestorBonusVentas("gestor.csv");
		} catch (Exception e) {
			System.err.println("ERROR: " + e.getMessage());
			System.out.println("Continuamos con un gestor vacío...");
			try {
				gestor = new GestorBonusVentas();
			} catch (FileNotFoundException e1) {
				System.err.println("No se ha podido crear el historial");
				System.exit(1);
			}
		}

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

		try {
			gestor.registraNuevoVendedor("Manolo");
			IO.println("ERROR: debería haber lanzado una excepción....");
		} catch (Exception e) {
			IO.println("Test 2: OK");
		}

		try {
			gestor.registraVenta("Manuel", 200);
			IO.println("ERROR: debería haber lanzado una excepción....");
		} catch (Exception e) {
			IO.println("Test 3: OK");
		}

		if (gestor.getVentas("Manolo") == 100) {
			IO.println("Test 4: OK");
		} else {
			IO.println("Test 4: ERROR");
		}

		try {
			gestor.registraVenta("María", 50);
			gestor.registraNuevoVendedor("Enrique");
			gestor.registraNuevoVendedor("Luisa");
			gestor.registraVenta("Luisa", 500);
			gestor.registraVenta("Enrique", 5000);
			
			if (gestor.getVentas("María") == 200) {
				IO.println("Test 5: OK");
			} else {
				IO.println("Test 6: ERROR");
			}
		} catch (Exception e) {
			IO.println("ERROR al registrar venta que no debería haber saltado");
		}
		
		Map<String, Double> bonusParaRepartir = gestor.reparteBonus(1000);
		for(Entry<String, Double> entrada : bonusParaRepartir.entrySet()) {
			IO.println(entrada.getKey() + " -> " + entrada.getValue() + " €");
		}
		
		
		
		try {
			gestor.guardaBackup("gestor");
		} catch (Exception e) {
			System.err.println("No se pudo guardar el backup...");
			e.printStackTrace();
		}
		
	}

}
