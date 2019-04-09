
import arduino.*;

public class ArduinoConnection {

    private Arduino ard;
    private static ArduinoConnection con;

    private ArduinoConnection() {
        ard = new Arduino("COM3", 9600);
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

    public void sendToArduino(String command) {
        ard.serialWrite(command);
    }

    public String recieveFromArduino() {
        String result = ard.serialRead();
        result = result.replace("\n", " ");
        result = result.trim();
        
        return result;
    }

    public void closeConnection() {
        ard.closeConnection();
    }

}
