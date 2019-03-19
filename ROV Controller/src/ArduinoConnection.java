
import arduino.*;

public class ArduinoConnection {

    static Arduino ard;

    private ArduinoConnection() {
    }

    public static Arduino intialization() {
        if (ard == null) {
            ard = new Arduino("COM3", 9600);
            ard.openConnection();
        }
        return ard;
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
