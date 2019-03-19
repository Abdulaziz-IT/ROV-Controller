
import javax.swing.JTextArea;

public class ReceivingArduino extends Thread {

    ArduinoConnection ard;
    JTextArea log;

    public ReceivingArduino(JTextArea log) {
        ard = ArduinoConnection.intialization();
        this.log = log;
    }

    @Override
    public void run() {
        while (!this.isInterrupted()) {

            String data = ard.recieveFromArduino();

            if (data.contains(":")) {
                getWaterState(data.split(":"));
            } else {
                log.append(data);
            }
        }
    }

    public void getWaterState(String[] data) {
        String pH = data[0];
        String tmp = data[1];

        System.out.println("pH is : " + pH);
        System.out.println("tmp is : " + tmp);
        //call the method which will add them to the DB
    }
}
