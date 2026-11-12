package tema4;

public class PruebasDiaSemana {

	public static void main(String[] args) {

		DiaSemana today = DiaSemana.MIÉRCOLES;

		DiaSemana tomorrow = DiaSemana.JUEVES;

		// Cada constante de un enum es un único objeto: se pueden comparar con ==.
		boolean iguales = today == tomorrow;
		IO.println("¿iguales? " + iguales);
		IO.println("¿diferentes? " + !iguales);

		IO.println("¿es posterior? " + DiaSemana.esPosterior(tomorrow, today));

	}

}
