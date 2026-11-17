# Arduino Consola (Java)

Programa de consola en Java que controla un Arduino por puerto serial (RXTX / jSSC).

1. Lista los puertos seriales disponibles y eliges uno.
2. Se conecta a 9600 baudios.
3. Menú: **1** encender, **2** apagar, **3** blink, **4** salir. Envía el comando como texto al Arduino y muestra los mensajes que regrese.

## Requisitos
- Java 8 o superior
- Librerías [PanamaHitek_Arduino](https://github.com/PanamaHitek/PanamaHitek_Arduino) y [jSSC](https://github.com/java-native/jssc) en el classpath
- Un Arduino con un sketch que lea `1`, `2` o `3` por serial (el sketch no está incluido en este repo)

## Uso
Compila y ejecuta `arduino_consola.Arduino_Consola`, elige el puerto y usa el menú.
