/* Buttons according to components:
            yAxis = comps[0];
            xAxis = comps[1];
            pov = comps[2]; (pov to left)
            rz = comps[3];
            button0 = comps[4]; (Trigger)
            button1 = comps[5]; (Thumb)
            button2 = comps[6]; (3)
            button3 = comps[7]; (4)
            button4 = comps[8]; (5)
            button5 = comps[9]; (6)
            button6 = comps[10]; (7)
            button7 = comps[11]; (8)
            slider = comps[12]; ( + and - )
            button8 = comps[13]; (9)
            button9 = comps[14]; (10)
            button10 = comps[15]; (11)
            button11 = comps[16]; (12)
 */
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import net.java.games.input.Component;
import net.java.games.input.Controller;

public class PollingData extends Thread {

    Controller c;
    JTextArea log;
    JSlider slider;
    Component[] comps;
    JLabel sliderValue;
    ArduinoConnection ard = ArduinoConnection.intialization();
    Thread receiver;
    boolean moving;

    PollingData(Controller c, JTextArea log, JSlider slider, JLabel sliderValue) {
        this.c = c;
        this.log = log;
        this.comps = c.getComponents();
        this.slider = slider;
        this.sliderValue = sliderValue;
        this.receiver = new ReceivingArduino(log);
    }

    @Override
    public void run() {
        receiver.start();
        while (!this.isInterrupted()) {
            c.poll();

            //slider.setValue(comps[12].getPollData());
            //Get current axis values
            getAxis();

            //Get current buttons values
            getButtons();

            //Convert the float number to a 180 degree integer number
            int sliderDegree = convertSliderValue(comps[12].getPollData());

            slider.setValue(sliderDegree);
            sliderValue.setText(sliderDegree + "");
            if (comps[16].getPollData() == 1.0f) {
                ard.sendToArduino("pos" + sliderDegree);
                try {
                    Thread.sleep(1000); //Give time to release the button.
                } catch (InterruptedException ex) {
                    System.out.println("The thread was interrupted while sleeping");
                }
            }

        }
        receiver.interrupt();
    }

    public void getAxis() {
        String movement;

        if (comps[1].getPollData() == -1.0f) {
            movement = "left";
            moving = true;
            ard.sendToArduino(movement);
        } else if (comps[1].getPollData() == 1.0f) {
            movement = "right";
            moving = true;
            ard.sendToArduino(movement);
        } else if (comps[0].getPollData() == -1.0f) {
            movement = "front";
            moving = true;
            ard.sendToArduino(movement);
        } else if (comps[0].getPollData() == 1.0f) {
            movement = "back";
            moving = true;
            ard.sendToArduino(movement);
        }
        if (moving) {
            if (comps[0].getPollData() > -0.9f && comps[0].getPollData() < 0.9f && comps[1].getPollData() > -0.9f && comps[1].getPollData() < 0.9f) {
                movement = "stop";
                moving = false;
                ard.sendToArduino(movement);
            }
        }
    }

    public void getButtons() {
        for (int i = 0; i < comps.length; i++) {
            if (!comps[i].isAnalog()) {
                if (comps[i].getPollData() == 1.0f) {
                    if (i == 2) {
                        continue;
                    }
                    String buttonName = Integer.parseInt(comps[i].getIdentifier().getName()) + 1 + "";
                    if (i == 4) {
                        buttonName = "Trigger";
                    } else if (i == 5) {
                        buttonName = "Thumb";
                    } else if (i == 6) {
                        buttonName = "Water";
                    }
                    ard.sendToArduino(buttonName);
                    try {
                        Thread.sleep(1000); //Give time to release the button.
                    } catch (InterruptedException ex) {
                        System.out.println("The thread was interrupted while sleeping");
                    }
                }
            }
        }
    }

    public int convertSliderValue(float num) {
        //Convert it from double to integer + from (-1 -> +1) to (-100 -> 100)
        int returnValue = (int) (comps[12].getPollData() * 100);
        //Convert it from (-100 -> 100) to (0 -> 200)
        returnValue += 100;
        //Convert it from (0 -> 200) to (0 -> 180)
        returnValue *= 0.9;

        return returnValue;
    }
    
    public void checkWater() {
        ard.sendToArduino("Water");
    }
}
