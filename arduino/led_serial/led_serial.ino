int led = 13;  // Arduino Uno/Nano = 13 | ESP32/ESP8266 = 2

void setup() {
  pinMode(led, OUTPUT);
  Serial.begin(9600);  // mismos baudios que la app de Java
}

void loop() {
  if (Serial.available() > 0) {
    int input = Serial.read();
    // Comandos: 1 = LED ON, 2 = LED OFF, 3 = LED BLINK
    switch (input) {
      case '1':
        digitalWrite(led, HIGH);
        break;
      case '2':
        digitalWrite(led, LOW);
        break;
      case '3':
        for (int i = 0; i < 2; i++) {   // parpadea 2 veces
          digitalWrite(led, HIGH);
          delay(500);
          digitalWrite(led, LOW);
          delay(500);
        }
        break;
      case '\n':   // ignora saltos de línea por si se envían
      case '\r':
        break;
      default:     // cualquier otro valor apaga el LED
        digitalWrite(led, LOW);
        break;
    }
  }
}
