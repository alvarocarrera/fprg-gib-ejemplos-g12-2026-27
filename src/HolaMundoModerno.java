// Archivo fuente compacto: no hace falta escribir la declaración de la clase.
void main() throws Exception {	
	
//	IO.println("Hola Mundo Moderno!");
//	String nombre = IO.readln("¿Cómo te llamas? ");
//	String msg = "¡Hola " + nombre + "! Encantado de conocerte.";
//	int longitudNombre = nombre.length();
//	IO.println(msg);
//	IO.println("¿Sabías que tu nombre tiene " + longitudNombre + " caracteres?");

	IO.println("¡Vamos a calcular el área de un triángulo!");
	String baseString = IO.readln("Dime la base del triángulo: ");
	String alturaString = IO.readln("Ahora dime su altura: ");
	// Convertimos el texto introducido a números; para decimales se usa un punto.
	// Estas conversiones están fuera del try: una entrada no numérica no se captura aquí.
	double base = Double.parseDouble(baseString);
	double altura = Double.parseDouble(alturaString);

	// try captura las excepciones lanzadas por las instrucciones de su bloque.
	try {
		double resultado = areaTriangulo(base, altura);
		IO.println("El área es: " + resultado);
	} catch (Exception e) {
		IO.println("No se ha podido completar la operación: " + e.getMessage());
//		e.printStackTrace();
//		throw e;
	}
	// Comprobación sencilla de un resultado conocido: (1 * 2) / 2 = 1.
	double a = areaTriangulo(1.0, 2.0);
	boolean resultado = (a == 1.0);
	System.out.println("¿correcto? -> " + resultado);

	// Probamos también un caso inválido para comprobar que se lanza una excepción.
	IO.println("Debería lanzar una excepción.");
	try {
		double area = areaTriangulo(-5.7, 34);
		IO.println("MAL -> Debería haber lanzado la excepción y no la ha lanzado!");
	} catch (Exception e) {
		IO.println("Prueba OK");
	}
	
	
	IO.println("El programa terminó sin problemas.");

	// % es el resto de la división, también con decimales: 5 = 2 * 2.5 + 0.0.
	double x = 5 % 2.5;
	IO.println(x);

//	int edad = Integer.parseInt(IO.readln("¿Qué edad tienes? "));
//	boolean mayorDeEdad = edad >= 18;
//	boolean menorDeEdad = !mayorDeEdad;
//	
//	IO.println("¿Mayor de edad? " + mayorDeEdad);
//	IO.println("¿Menor de edad? " + menorDeEdad);

}

/**
 * Calcula el área de un triángulo con base y altura positivas.
 * 
 * 
 * @param base   es la base del triangulo
 * @param altura es la altura del triangulo
 * @return es el area
 * @throws Exception si la base o la altura son menores o iguales que cero
 */
double areaTriangulo(double base, double altura) throws Exception {
	// throw interrumpe el método y entrega la excepción a quien lo llamó.
	if (base <= 0.0) {
		Exception e = new Exception("No puedo calcular el área de un triángulo con medida negativa en el parámetro \"base\".");
		
		throw e;
	}
	if (altura <= 0.0) {
		throw new Exception("No puedo calcular el área de un triángulo con medida negativa en el parámetro \"altura\".");
	}
	
	return (base * altura) / 2.0;
}
