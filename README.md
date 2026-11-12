# Ejemplos de clase — FPRG-GIB, grupo 12

Proyecto Java de Eclipse `EjemplosClaseG12`, con ejemplos introductorios usados en clase durante el curso 2026–2027. No hace falta instalar ni utilizar Git.

## Contenido

- `src/HolaMundoClasico.java`: entrada clásica `main`, salida por pantalla y métodos.
- `src/HolaMundoModerno.java`: archivo fuente compacto, entrada de datos, cálculo del área de un triángulo y excepciones.
- `src/tema3/`: bucles, incrementos y decrementos, arrays y un chatbot sencillo que guarda mensajes (sin inteligencia artificial ni servicios externos).
- `src/tema4/`: clases `Persona` y `Deposito`, constructores, atributos privados, métodos, enumerados (`DiaSemana`), caracteres y un array grande, con programas de prueba.
- `src/tema5/`: tipos de datos, arrays unidimensionales y bidimensionales, y registro de alturas con arrays paralelos.
- `src/tema6/`: interfaces (`Notificable`), clases que la implementan (`Persona`, `AgenteIA`), polimorfismo, `Comparable`, conjuntos (`Set`) y `EmailHelper`, que envía correos con la API de Gmail.
- `.project`, `.classpath` y `.settings/`: configuración necesaria para importar y compilar el proyecto en Eclipse.

Son ejemplos en evolución, no una biblioteca de producción. Algunos fragmentos están comentados para conservar alternativas vistas en clase. Los nombres y edades de prueba son ficticios; el correo usa el dominio reservado `example.com`.

## Requisitos confirmados

- JDK 26: `.classpath` selecciona `JavaSE-26` y las preferencias del compilador fijan fuente, compatibilidad y destino en 26.
- Eclipse con herramientas Java (JDT) y soporte para Java 26. No se puede determinar una versión mínima exacta de Eclipse a partir de los archivos del proyecto.
- Codificación UTF-8. Las características de vista previa están desactivadas; no se necesita activar `--enable-preview`.
- El código usa `java.lang.IO` y un archivo fuente compacto. No se deben tratar como proyectos Java 8/11/17 ni cambiar el nivel de compilación sin revisar los ejemplos.
- No hay dependencias externas, Maven, Gradle ni recursos de datos adicionales en el proyecto.

## Descargar el ZIP e importar en Eclipse, sin Git

1. En la página del repositorio de GitHub, pulsa **Code > Download ZIP**.
2. Descomprime el ZIP en una carpeta donde tengas permisos de escritura. Conserva también los archivos que empiezan por punto (`.project`, `.classpath` y `.settings/`).
3. Abre Eclipse y selecciona tu workspace.
4. Ve a **File > Import… > General > Existing Projects into Workspace** y pulsa **Next**.
5. En **Select root directory**, selecciona la carpeta descomprimida que contiene `.project`. Debe aparecer el proyecto `EjemplosClaseG12`.
6. Selecciona el proyecto. Puedes marcar **Copy projects into workspace** para trabajar con una copia dentro del workspace. Pulsa **Finish**.
7. Comprueba que la biblioteca del proyecto corresponde a **JavaSE-26** y que Eclipse tiene configurado un JDK 26. Si aparece un error de biblioteca o de nivel de Java, revisa **Preferences > Java > Installed JREs**, la asociación del entorno **JavaSE-26** y **Project > Properties > Java Build Path**. No borres la biblioteca para ocultar el error.

Si el workspace ya contiene un proyecto llamado `EjemplosClaseG12`, importa la descarga en otro workspace para evitar sobrescribir trabajos anteriores. Para futuras descargas, repite el procedimiento con una carpeta nueva.

## Ejecutar los ejemplos

Abre un archivo con un método `main` y selecciona **Run As > Java Application**. Los programas ejecutables son `HolaMundoClasico`, `HolaMundoModerno`, `tema3.ChatBotFPRG`, `tema3.EjemplosBucles`, `tema4.PruebasDeposito`, `tema4.PruebasPersona`, `tema4.PruebasPersona2`, `tema4.PruebasDiaSemana`, `tema4.EjemplosArrays` y `tema4.EjemplosChars`. `tema4.EjemplosArrays` reserva unos 80 MB y `tema4.EjemplosChars` imprime 5.000 caracteres. `Persona` y `Deposito` se utilizan desde los programas de prueba, no tienen entrada propia.

En el tema 5 también se pueden ejecutar `EjemplosTiposDatos`, `EjemplosArrays`, `EjemplosArrays2D`, `MasEjemplosArrays` y `PruebasRegistroAltura`. `EjemplosArrays` muestra 20.000 nombres y alturas; se puede reducir `cantidad` para obtener menos salida. `EjemplosArrays2D` muestra referencias a filas y recorridos de una matriz. La prueba del registro incluye a propósito un acceso fuera de rango para mostrar el tratamiento de excepciones; `RegistroAltura` es la clase utilizada por la prueba.

En el tema 6 se ejecutan `tema6.PruebasNotificable`, `tema6.EjemploListas` y, con la configuración descrita más abajo, `tema6.EmailHelper`. `EjemploListas` usa un `Set` aunque su nombre sugiera una lista.

La entrada y la salida aparecen en la consola de Eclipse. En el ejemplo del triángulo escribe números y usa un punto como separador decimal (por ejemplo, `5.7`). En el chatbot escribe `FIN` para terminar; se guardan como máximo cinco mensajes. Tal como está escrito, después del quinto mensaje se pide una entrada más antes de terminar.

## Enviar correos con EmailHelper

`tema6.EmailHelper` envía un correo de texto plano con la API REST de Gmail. Es un ejemplo didáctico: usa un access token de OAuth 2.0 que caduca aproximadamente a la hora.

1. Genera el token en el [OAuth 2.0 Playground](https://developers.google.com/oauthplayground) con el scope `https://www.googleapis.com/auth/gmail.send` (los pasos detallados están en el Javadoc de la clase).
2. Define las variables de entorno `GOOGLE_ACCESS_TOKEN` (el token) y `EMAIL_DESTINO` (quien recibe el correo de prueba). En Eclipse: **Run > Run Configurations… > la clase > Environment > New…**
3. Ejecuta `tema6.EmailHelper`, o llama a `EmailHelper.sendEmail(destino, asunto, cuerpo)` desde tu código.

**No escribas el token ni tu dirección en el código, ni subas el token al repositorio.** Las Run Configurations de Eclipse quedan fuera del proyecto, salvo que marques *Shared file* en la pestaña *Common*: no lo hagas si contienen el token. Si Google responde `HTTP 401`, el token ha caducado: genera uno nuevo.

## Alcance de los ejemplos

- Las conversiones de texto a número en `HolaMundoModerno` están fuera del `try`: una entrada no numérica puede terminar el programa con una excepción.
- `Persona` contiene una validación de correo simplificada. Su método `equals(Persona)` no sobrescribe `equals(Object)` y no contempla todos los casos con `null`.
- `Deposito.llena` usa `<`, no `<=`: no permite alcanzar exactamente la capacidad. No valida todas las cantidades ni los cambios de capacidad.
- En `RegistroAltura`, `getPosMinimo`, `alturaMedia`, `getUltimaPosAltura` y `contarAlturasEntre` quedan como ejercicios y devuelven valores provisionales; antes de generar datos, las alturas contienen el valor inicial `0`.

- En `tema6.Persona`, `equals(Persona)` y `compareTo` no contemplan valores `null`; `compareTo` ordena de mayor a menor edad.
- `tema6.EmailHelper` no valida las direcciones ni escapa las cabeceras del mensaje, y envía solo texto plano.

Estos comportamientos se conservan para no alterar los ejemplos de clase.

## Archivos generados

`bin/` y `*.class` son resultados de compilación y no se distribuyen: Eclipse los vuelve a crear. `doc/` contenía Javadoc generado y no es necesario para ejecutar ni importar los ejemplos; los comentarios explicativos permanecen en los fuentes. No se distribuye ninguna configuración del workspace.

Referencias del procedimiento y de la API, consultadas el 10 de octubre de 2026:

- https://help.eclipse.org/latest/topic/org.eclipse.platform.doc.user/tasks/tasks-importproject.htm
- https://docs.oracle.com/en/java/javase/26/docs/api/java.base/java/lang/IO.html
