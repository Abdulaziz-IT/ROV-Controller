
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

    public void sendToArduino(String command) {
        ard.serialWrite(command);
    }

    public void sendToArduino(String movementCommand, String ButtonCommand) throws InterruptedException {
        ard.serialWrite(movementCommand);
        Thread.sleep(500);
        ard.serialWrite(ButtonCommand);
    }

    public String recieveFromArduino() {
        String result = ard.serialRead();

        while (result.isEmpty()) {
            result = ard.serialRead();
        }
        result = result.replace("\n", " ");
        result = result.trim();
        return result;
    }

    public void closeConnection() {
        ard.closeConnection();
    }

}
