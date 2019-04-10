
import arduino.*;

public class ArduinoConnection {

    private Arduino ard;
    private static ArduinoConnection con;

    private ArduinoConnection() { //Opening a connection with specified ports
        ard = new Arduino("COM6", 9600);
        ard.openConnection();
    }

    public static ArduinoConnection intialization() {
        if (con == null) {
            con = new ArduinoConnection();
        }
        return con;
    }
    
    public boolean checkConnection() {
        return con != null;
    }

    public void sendToArduino(String command) { //Write to the serial port.
        ard.serialWrite(command);
    }

    public String recieveFromArduino() { //Read from the serial
        String result = ard.serialRead();
        result = result.replace("\n", " "); //Replace all the nextlines with a space
        result = result.trim();
        
        return result;
    }

    public void closeConnection() {
        ard.closeConnection();
    }

}
