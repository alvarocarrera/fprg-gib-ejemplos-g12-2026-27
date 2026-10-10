package tema4;

public class PruebasDeposito {

	public static void main(String[] args) {
//		Esto ya no se puede hacer porque hay un constructor en la clase Deposito,
//		por lo tanto, el constructor por defecto deja de estar disponible.
		
//		Deposito d = new Deposito();
		
		Deposito d = new Deposito(0);
		d.setCapacidad(1000);
		d.llena(10);
		// Se muestra la capacidad del depósito, no los litros que contiene.
		IO.println(d.getCapacidad());
	}

}
