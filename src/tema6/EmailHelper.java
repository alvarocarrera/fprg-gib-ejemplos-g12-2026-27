package tema6;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Envía correos mediante la API REST de Gmail usando un access token OAuth 2.0.
 *
 * <h2>Uso rápido</h2>
 *
 * <pre>
 * // El remitente es la cuenta de Gmail a la que pertenece el token.
 * EmailHelper.sendEmail("destino@example.com", "Asunto", "Hola,\n\nTexto del mensaje.\n");
 * </pre>
 *
 * El token se lee de la variable de entorno {@code GOOGLE_ACCESS_TOKEN}. Si el
 * envío falla, los métodos lanzan una excepción con el código HTTP y la
 * respuesta de Google.
 *
 * <h2>Cómo obtener o renovar el access token</h2>
 * El access token caduca aproximadamente a la hora. Cuando eso ocurre, la API
 * responde con {@code HTTP 401} y hay que generar uno nuevo:
 * <ol>
 * <li>Abrir <a href="https://developers.google.com/oauthplayground">OAuth 2.0
 * Playground</a>.</li>
 * <li>Paso 1 (Select &amp; authorize APIs): escribir el scope
 * {@code https://www.googleapis.com/auth/gmail.send} en "Input your own
 * scopes" y pulsar <i>Authorize APIs</i>. Iniciar sesión con la cuenta de Gmail
 * que enviará los correos y aceptar los permisos.</li>
 * <li>Paso 2 (Exchange authorization code for tokens): pulsar <i>Exchange
 * authorization code for tokens</i> y copiar el valor de <i>Access token</i>
 * (empieza por {@code ya29.}).</li>
 * <li>Guardarlo en la variable de entorno {@code GOOGLE_ACCESS_TOKEN}:
 * <ul>
 * <li>Terminal: {@code export GOOGLE_ACCESS_TOKEN="ya29...."} y lanzar Java
 * desde esa misma terminal.</li>
 * <li>Eclipse: Run &gt; Run Configurations... &gt; la clase &gt; pestaña
 * <i>Environment</i> &gt; New..., con nombre {@code GOOGLE_ACCESS_TOKEN} y el
 * token como valor.</li>
 * </ul>
 * </li>
 * </ol>
 * También se puede pasar el token directamente como primer parámetro de
 * {@link #sendEmail(String, String, String, String, String)}; si es
 * {@code null}, se lee de la variable de entorno.
 *
 * <h2>Ejecutar el ejemplo {@code main}</h2>
 * Además de {@code GOOGLE_ACCESS_TOKEN}, define la variable de entorno
 * {@code EMAIL_DESTINO} con la dirección que recibirá el correo de prueba. Así
 * ninguna dirección personal queda escrita en el código.
 *
 * <h2>Seguridad</h2>
 * <ul>
 * <li>No escribir el token en el código ni subirlo al repositorio.</li>
 * <li>Las Run Configurations de Eclipse se guardan en el workspace, fuera del
 * proyecto, y por tanto fuera de Git. Solo acabarían en el repositorio si se
 * marcara <i>Shared file</i> en la pestaña <i>Common</i>: no hacerlo si
 * contienen el token.</li>
 * <li>Quien tenga el token puede enviar correos con tu cuenta hasta que caduque.</li>
 * </ul>
 */
public class EmailHelper {

	/**
	 * Envía un correo en texto plano desde la cuenta de Gmail del token.
	 *
	 * El token se lee de la variable de entorno {@code GOOGLE_ACCESS_TOKEN}.
	 * No se indica remitente: Gmail usa la cuenta autenticada.
	 *
	 * @param to      dirección del destinatario
	 * @param subject asunto del mensaje
	 * @param body    cuerpo del mensaje, en texto plano
	 * @throws Exception si falta el token, falla la conexión o Google rechaza el
	 *                   envío (por ejemplo, HTTP 401 con el token caducado)
	 */
	public static void sendEmail(String to, String subject, String body) throws Exception {
		EmailHelper.sendEmail(null, null, to, subject, body);
	}

	/**
	 * Envía un correo en texto plano indicando el token y, opcionalmente, el
	 * remitente.
	 *
	 * @param gmailAccessToken access token OAuth 2.0; si es {@code null}, se lee
	 *                         de la variable de entorno {@code GOOGLE_ACCESS_TOKEN}
	 * @param from             remitente; si es {@code null}, no se escribe la
	 *                         cabecera {@code From} y Gmail usa la cuenta
	 *                         autenticada. Gmail solo acepta la cuenta del token o
	 *                         un alias configurado en ella.
	 * @param to               dirección del destinatario
	 * @param subject          asunto del mensaje
	 * @param body             cuerpo del mensaje, en texto plano
	 * @throws Exception si no hay token, falla la conexión o Google responde con un
	 *                   código distinto de 2xx
	 */
	public static void sendEmail(String gmailAccessToken, String from, String to, String subject, String body)
			throws Exception {
		String accessToken = gmailAccessToken != null ? gmailAccessToken : System.getenv("GOOGLE_ACCESS_TOKEN");
		// Sin esta comprobación se enviaría "Bearer null" y Google respondería con un 401 poco claro.
		if (accessToken == null || accessToken.isBlank()) {
			throw new Exception("Falta el access token: define la variable de entorno GOOGLE_ACCESS_TOKEN "
					+ "o pásalo como primer parámetro. Mira la documentación de la clase EmailHelper.");
		}

		// Un correo es texto con cabeceras (From, To, Subject...), una línea en blanco y el cuerpo.
		// Las cabeceras terminan en \r\n, como exige el formato de los mensajes de correo.
		String mimeMessage = (from != null ? "From: " + from + "\r\n" : "") + "To: " + to + "\r\n" + "Subject: "
				+ subject + "\r\n" + "MIME-Version: 1.0\r\n" + "Content-Type: text/plain; charset=\"UTF-8\"\r\n"
				+ "Content-Transfer-Encoding: 8bit\r\n" + "\r\n" + body;

		// La API pide el mensaje completo codificado en Base64 (variante URL-safe).
		String rawMessage = Base64.getUrlEncoder().withoutPadding()
				.encodeToString(mimeMessage.getBytes(StandardCharsets.UTF_8));

		// Cuerpo JSON de la petición: {"raw":"<mensaje en Base64>"}.
		String jsonBody = "{\"raw\":\"" + rawMessage + "\"}";

		// Petición HTTP POST; el token viaja en la cabecera Authorization.
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://gmail.googleapis.com/gmail/v1/users/me/messages/send"))
				.header("Authorization", "Bearer " + accessToken).header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(jsonBody)).build();

		HttpClient client = HttpClient.newHttpClient();

		// send espera la respuesta antes de continuar; puede lanzar IOException o InterruptedException.
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		// Los códigos 2xx indican éxito; cualquier otro se trata como error.
		if (response.statusCode() < 200 || response.statusCode() >= 300) {
			throw new RuntimeException("Error enviando email. HTTP " + response.statusCode() + ": " + response.body());
		}

		System.out.println("Email enviado correctamente:");
		System.out.println(response.body());
	}

	/**
	 * Ejemplo de uso. Requiere las variables de entorno {@code GOOGLE_ACCESS_TOKEN}
	 * y {@code EMAIL_DESTINO}; envía dos correos de prueba, uno con todos los
	 * parámetros y otro con la versión corta.
	 *
	 * @param args argumentos de la línea de comandos (no se usan)
	 * @throws Exception si falla el envío
	 */
	public static void main(String[] args) throws Exception {
		String destino = System.getenv("EMAIL_DESTINO");
		if (destino == null || destino.isBlank()) {
			System.err.println("Define la variable de entorno EMAIL_DESTINO con la dirección que recibirá el correo.");
			return;
		}

		// Con token explícito (null = leerlo de GOOGLE_ACCESS_TOKEN) y remitente explícito (null = cuenta del token).
		sendEmail(null, null, destino, "Prueba desde Gmail API en la clase", "Hola,\n\nCorreo enviado desde Java.\n");

		// Versión corta: token de la variable de entorno y remitente por defecto.
		sendEmail(destino, "Prueba 2 desde Gmail API en la clase",
				"Hola,\n\nCorreo enviado desde Java con valores por defecto.\n");
	}
}
