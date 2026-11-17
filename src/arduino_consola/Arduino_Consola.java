package arduino_consola;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;


import java.util.List;//Crear lista de los puertos serial
import java.util.Scanner;//Leer en consola datos ingresados





public class Arduino_Consola {
    //Se crea una instancia de la librería PanamaHitek_Arduino
    private static PanamaHitek_Arduino arduino = new PanamaHitek_Arduino();
    public static Scanner entrada = new Scanner(System.in);
    public static String nombre_puerto;
    public static String mensaje;
    
    
    
    public static void main(String[] args) throws ArduinoException, SerialPortException {
   
        System.out.println("\n*********Arduino Interfaz****************\n");
        
        List<String> lista_puertos = listado();
        Integer limite = lista_puertos.size();
        for(int i=0;i<limite;i++)
        {
            System.out.println(i+".-"+lista_puertos.get(i));        
        }
        System.out.println("Seleccione el puerto de la opciones:\n");
        nombre_puerto = lista_puertos.get(entrada.nextInt());
        conectar();  
        Boolean exit_ = true;
        while (exit_) {
            System.out.println("Seleccion una funcion \n 1-Encender\n 2-Apagar\n 3-Blink\n 4-Salir");
            int opc = entrada.nextInt();
            switch (opc) {
                case 1:
                    encender();
                    break;
                case 2:
                    apagar();
                    break;
                case 3:
                    blink();
                  
                    break;
                case 4:
                    cerrar();
                    exit_ = false;
                    break;
                default:
                    break;
            }
        }
    }
    
    public static List<String> listado() {
        List<String> lista = arduino.getSerialPorts() ;
        return lista;
    }
    
    public static void conectar() throws ArduinoException {

        /*
        * Se inicia la comunicación con el Puerto Serie utilizando la función RX,
        * la cual es exclusiva para recibir datos desde el Puerto Serie
        */
        arduino.arduinoRXTX(nombre_puerto, 9600,listener);
        // ino.arduinoTX(nombre_puerto, 38400);
        System.out.println("Conectado");

    }
    
    public static void encender() throws SerialPortException {
        System.out.println("Enciende Arduino");
        try {
            // String mensaje = JOptionPane.showInputDialog("Ingrese codigo");
            arduino.sendData("1");
            arduino.flushSerialPort();
            

        } catch (ArduinoException  ev) {
            System.out.println("Error");
        }
    }

    public static void apagar() throws SerialPortException {
        System.out.println("Apagar Arduino");
        try {
            // String mensaje = JOptionPane.showInputDialog("Ingrese codigo");
            arduino.sendData("2");
            arduino.flushSerialPort();

        } catch (ArduinoException ev) {
            System.out.println("Error");
        }
    }
        
    public static void blink() throws SerialPortException {
        
        try {
            // String mensaje = JOptionPane.showInputDialog("Ingrese codigo");
            arduino.sendData("3");
            arduino.flushSerialPort();
            System.out.println("Funcion Blink");

        } catch (ArduinoException ev) {
            System.out.println("Error");
        }
    }

    public static void cerrar() {
        System.out.println("Desconectar Arduino");
        try {
            // String mensaje = JOptionPane.showInputDialog("Ingrese codigo");
            arduino.killArduinoConnection();

        } catch (ArduinoException ev) {
            System.out.println("Error");
        }
    }
    
static SerialPortEventListener listener = new SerialPortEventListener() {
        @Override
        public void serialEvent(SerialPortEvent serialPortEvent) {
            try {
                /*
                ============ Documentación en Español =====================
                Los datos en el puerto serie se envían carácter por carácter. Para esperar 
                la recepción completa de un mensaje antes de imprimirlo, el método 
                `isMessageAvailable()` devolverá TRUE cuando el mensaje esté disponible.
                
                ============ Documentation in English =====================
                Data on the serial port is sent character by character. To wait for the 
                complete reception of a message before printing it, the `isMessageAvailable()` 
                method will return TRUE when the message is available.
                */
                if (arduino.isMessageAvailable()) {
                    // Se le asigna el mensaje recibido a la variable msg
                    mensaje = arduino.printMessage();
                    arduino.flushSerialPort();
                    // Se imprime el mensaje recibido
                    System.out.println("Mensaje recibido --> " + mensaje);
                }
            } catch (SerialPortException ex) {
                Logger.getLogger(Arduino_Consola.class.getName()).log(Level.SEVERE, "Error en el puerto serie", ex);
            } catch (ArduinoException ex) {
                Logger.getLogger(Arduino_Consola.class.getName()).log(Level.SEVERE, "Error en la comunicación con Arduino", ex);
            }
        }
    };
    
}
