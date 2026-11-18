package tema6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 * Tipos de operación que se anotan en el historial.
 */
enum TipoOperaciones {
	/** Alta de un nuevo vendedor. */
	REGISTRO,
	/** Venta realizada por un vendedor. */
	VENTA;
}


/**
 * Gestiona las ventas acumuladas de cada vendedor y reparte un bonus
 * proporcionalmente a ellas.
 * <p>
 * Cada operación (alta o venta) se anota en el fichero {@value #FICHERO_HISTORIAL}.
 * El estado se puede cargar desde un CSV ({@code Vendedor,Ventas}) y guardar
 * como copia de seguridad con {@link #guardaBackup(String)}.
 * </p>
 */
public class GestorBonusVentas extends Object {

	/** Nombre del fichero CSV donde se anota el historial de operaciones. */
	public static final String FICHERO_HISTORIAL = "historial.csv";

	/** Ventas acumuladas por nombre de vendedor. */
	private Map<String, Double> registroVentas;
	/** Escritor del historial de operaciones. */
	private PrintWriter historial;

	/**
	 * Crea un gestor vacío y abre el historial.
	 * <p>
	 * Si el fichero de historial no existe se crea con su cabecera. Si ya existe,
	 * se abre para escritura y su contenido anterior se sobrescribe.
	 * </p>
	 *
	 * @throws FileNotFoundException si no se puede crear o abrir el historial
	 */
	public GestorBonusVentas() throws FileNotFoundException {
		// Map: asocia cada clave (nombre) con un valor (ventas). No hay claves repetidas.
		this.registroVentas = new HashMap<String, Double>();
		File file = new File(FICHERO_HISTORIAL);
		// Hay que mirar si existe ANTES de crear el PrintWriter, porque
		// new PrintWriter(file) crea el fichero (o lo vacía si ya existía).
		if (!file.exists()) {
			this.historial = new PrintWriter(file);
			// La cabecera solo se escribe al crear el fichero.
			historial.println("Tipo operación,Vendedor,Venta,TimeStamp");
			// flush() fuerza a escribir ya en disco lo que está en el búfer.
			historial.flush();
		} else {
			this.historial = new PrintWriter(file);
		}
	}
	
	/**
	 * Crea un gestor cargando el estado desde un fichero CSV.
	 * <p>
	 * El fichero debe tener la cabecera {@code Vendedor,Ventas} y una fila
	 * {@code nombre,ventas} por vendedor.
	 * </p>
	 *
	 * @param estadoPrevio ruta del CSV con las ventas previas
	 * @throws Exception si el fichero no existe, la cabecera no es válida,
	 *                   alguna fila no tiene 2 columnas o las ventas no son numéricas
	 */
	public GestorBonusVentas(String estadoPrevio) throws Exception {
		// this() llama al constructor sin parámetros: deja listos el mapa y el historial.
		this();
		// Scanner sobre un File lee el fichero línea a línea.
		Scanner sc = new Scanner(new File(estadoPrevio));
		// La primera línea es la cabecera: se lee aparte para validar el formato.
		if (sc.hasNextLine()) {
			String cabeceras = sc.nextLine();
			// Para comparar textos se usa equals(), nunca ==.
			if (!cabeceras.equals("Vendedor,Ventas")) {
				// Se cierra el Scanner antes de lanzar la excepción para no dejar el fichero abierto.
				sc.close();
				throw new Exception("Cabecera mal formada. Fichero no compatible");
			}
		}
		// Resto de líneas: una por vendedor, con formato "nombre,ventas".
		while(sc.hasNextLine()) {
			String fila = sc.nextLine();
			// split(",") separa la fila en un array: partes[0] = nombre, partes[1] = ventas.
			String[] partes = fila.split(",");
			if(partes.length != 2) {
				sc.close();
				throw new Exception("La fila debería tener solo 2 partes. Fila mal formada: " + fila);
			}
			String nombre = partes[0];
			// parseDouble convierte el texto en número; lanza NumberFormatException si no lo es.
			double ventas = Double.parseDouble(partes[1]);
			this.registroVentas.put(nombre, ventas);
		}
		sc.close();
		IO.println("Se han cargado correctamente del fichero: "
				+ estadoPrevio + " -> un total de " 
				+ this.registroVentas.size());
	}
	
	/**
	 * Anota una operación en el historial con la marca de tiempo actual.
	 *
	 * @param tipo     tipo de operación
	 * @param vendedor nombre del vendedor
	 * @param cuantia  importe de la operación (0 en los registros)
	 */
	private void registraHistorial(TipoOperaciones tipo, String vendedor, double cuantia) {
		// Milisegundos desde el 1-1-1970: sirve para ordenar las operaciones en el tiempo.
		long timestamp = System.currentTimeMillis();
		// name() devuelve el nombre de la constante del enum ("REGISTRO" o "VENTA").
		// La línea sigue el formato CSV: campos separados por comas.
		String msg = tipo.name()+","+vendedor+","+cuantia+","+timestamp;
		historial.println(msg);
		// Sin flush() la línea podría quedarse en el búfer y perderse si el programa termina antes.
		historial.flush();
	}
	
	/**
	 * Da de alta un vendedor con 0 € en ventas.
	 *
	 * @param nombre nombre del vendedor
	 * @throws Exception si ya existe un vendedor con ese nombre
	 */
	public void registraNuevoVendedor(String nombre) throws Exception {
		// containsKey() comprueba si la clave ya está en el mapa; evita pisar a un vendedor existente.
		if (this.registroVentas.containsKey(nombre)) {
			// get() devuelve un Double; se convierte solo a double (auto-unboxing).
			double ventasRegistradas = this.registroVentas.get(nombre);
			String msg = "Vendedor: " + nombre + " ya existía previamente";
			msg += " y además tiene " + ventasRegistradas + " € en ventas."; 
			throw new Exception(msg);
		}
		this.registroVentas.put(nombre, 0.0);
		this.registraHistorial(TipoOperaciones.REGISTRO, nombre, 0);
	}
	
	/**
	 * Suma una venta al acumulado de un vendedor.
	 *
	 * @param vendedor nombre del vendedor
	 * @param montante importe de la venta en euros
	 * @throws Exception si el vendedor no está registrado
	 */
	public void registraVenta(String vendedor, double montante) throws Exception {
		if(!this.registroVentas.containsKey(vendedor)) {
			throw new Exception(vendedor + " no está registrado. La venta no se procesa.");
		}
		
		double ventasPrevias = this.registroVentas.get(vendedor);
		double ventasNuevas = ventasPrevias + montante;
		// put() con una clave existente reemplaza el valor anterior: así se actualiza el acumulado.
		this.registroVentas.put(vendedor, ventasNuevas);
		this.registraHistorial(TipoOperaciones.VENTA, vendedor, montante);
	}
	
	/**
	 * Devuelve las ventas acumuladas de un vendedor.
	 *
	 * @param vendedor nombre del vendedor
	 * @return ventas acumuladas, o 0.0 si el vendedor no existe
	 */
	public double getVentas(String vendedor) {
		if (this.registroVentas.containsKey(vendedor)) {
			return this.registroVentas.get(vendedor);
		} else {
			return 0.0;
		}
	}
	
	/**
	 * Elimina un vendedor del registro.
	 *
	 * @param vendedor nombre del vendedor
	 * @return ventas que tenía acumuladas
	 * @throws Exception si el vendedor no está registrado
	 */
	public double despide(String vendedor) throws Exception {
		if (!this.registroVentas.containsKey(vendedor)) {
			throw new Exception(vendedor + " no está registrado. No se puede despedir.");
		}
		// remove() borra la entrada del mapa y devuelve el valor que tenía.
		return this.registroVentas.remove(vendedor);
	}
	
	/**
	 * Reparte un bonus entre los vendedores proporcionalmente a sus ventas.
	 * <p>
	 * Si las ventas totales son 0, cada vendedor recibe 0.0 de bonus.
	 * </p>
	 *
	 * @param bonus importe total a repartir
	 * @return bonus asignado a cada vendedor, por nombre
	 */
	public Map<String, Double> reparteBonus(double bonus) {
		// Idea: cada vendedor recibe un porcentaje del bonus igual a su porcentaje de las ventas totales.
		Map<String, Double> resultado = new HashMap<String, Double>();
		// Paso 1: sumar las ventas de todos. values() recorre solo los valores del mapa.
		double ventasTotales = 0.0;
		for(double ventasVendedor : this.registroVentas.values()) {
			ventasTotales += ventasVendedor;
//			ventasTotales = ventasTotales + ventasVendedor;
		}
		// Paso 2: calcular el bonus de cada vendedor.
		// Versión alternativa recorriendo las claves (hace un get() por cada una):
//		for(String nombre : this.registroVentas.keySet()) {
//			double ventaVendedor = this.registroVentas.get(nombre);
//			double pct = ventaVendedor / ventasTotales;
//			double bonusVendedor = pct * bonus;
//			resultado.put(nombre, bonusVendedor);
//		}
		// Versión usada: entrySet() da cada par clave-valor (Entry), sin buscar de nuevo en el mapa.
		for(Entry<String, Double> par : this.registroVentas.entrySet()) {
			String nombre = par.getKey();
			double ventaVendedor = par.getValue();
			// Operador ternario: condición ? valor_si_true : valor_si_false.
			// Evita dividir 0/0, que daría NaN (Not a Number).
			double pct = ventasTotales == 0 ? 0.0 : ventaVendedor / ventasTotales;
			double bonusVendedor = pct * bonus;
			resultado.put(nombre, bonusVendedor);
		}
		return resultado;
	}
	
	
	/**
	 * Guarda el estado actual en un CSV con el formato {@code Vendedor,Ventas}.
	 * <p>
	 * Al nombre indicado se le añade {@code -<milisegundos>.bak}, por ejemplo
	 * {@code gestor-1700000000000.bak}.
	 * </p>
	 *
	 * @param ficheroDestino prefijo del nombre del fichero de backup
	 * @throws Exception si el fichero resultante ya existe o no se puede escribir
	 */
	public void guardaBackup(String ficheroDestino) throws Exception {
		// Se añade la hora al nombre para que cada backup tenga un nombre distinto.
		ficheroDestino += "-"+System.currentTimeMillis() + ".bak";
		File fichero = new File(ficheroDestino);
		// Si ya existe, new PrintWriter lo sobrescribiría: mejor avisar con una excepción.
		if(fichero.exists()) {
			throw new Exception("El fichero ya existe: " + ficheroDestino + " Elige otro nombre.");
		}
		PrintWriter pw = new PrintWriter(fichero);
		// Misma cabecera que espera el constructor con fichero: así el backup se puede volver a cargar.
		String cabeceras = "Vendedor,Ventas";
		pw.println(cabeceras);
		// Una línea "nombre,ventas" por cada vendedor.
		for(Entry<String,Double> entry : this.registroVentas.entrySet()) {
			String linea = entry.getKey()+","+entry.getValue();
			pw.println(linea);
		}
		// close() vacía el búfer y libera el fichero; sin él podrían faltar datos.
		pw.close();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
