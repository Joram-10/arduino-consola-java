# Arduino Consola (Java)

Programa de consola en Java que controla un Arduino por puerto serial (RXTX / jSSC), más el sketch de Arduino que recibe los comandos.

## Cómo funciona
1. Lista los puertos seriales disponibles y eliges uno.
2. Se conecta a 9600 baudios.
3. Menú: **1** encender, **2** apagar, **3** blink, **4** salir. Envía el comando como texto al Arduino y muestra los mensajes que regrese.

## Estructura
- `src/arduino_consola/Arduino_Consola.java`: programa de consola en Java.
- `arduino/led_serial/led_serial.ino`: sketch de Arduino que lee `1`, `2` o `3` por serial y controla un LED (pin 13 en Uno/Nano, pin 2 en ESP32/ESP8266).

## Requisitos
- Java 8 o superior
- Librerías [PanamaHitek_Arduino](https://github.com/PanamaHitek/PanamaHitek_Arduino) y [jSSC](https://github.com/java-native/jssc) en el classpath
- Un Arduino con el sketch cargado

## Uso
1. Carga `led_serial.ino` en tu Arduino desde el IDE de Arduino.
2. Compila y ejecuta `arduino_consola.Arduino_Consola`, elige el puerto y usa el menú.
