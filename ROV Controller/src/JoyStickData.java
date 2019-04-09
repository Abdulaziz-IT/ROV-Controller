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

public class JoyStickData extends Thread {

    Controller c;
    JTextArea log;
    JSlider slider;
    Component[] comps;
    JLabel sliderValue;
    ArduinoConnection ard = ArduinoConnection.intialization();
    Thread receiver;
    boolean moving;
    int sliderDegree;

    JoyStickData(Controller c, JTextArea log, JSlider slider, JLabel sliderValue) {
        this.c = c;
        this.log = log;
        this.comps = c.getComponents();
        this.slider = slider;
        this.sliderValue = sliderValue;
    }

    @Override
    public void run() {
        //receiver.start();
        while (!this.isInterrupted()) {
            c.poll();

            //Get current axis values
            getAxis();

            //Get current buttons values
            getButtons();

            //Convert the float number to a 180 degree integer number
            sliderDegree = convertSliderValue(comps[12].getPollData());

            slider.setValue(sliderDegree);
            sliderValue.setText(sliderDegree + "");
        }
        receiver.interrupt();
    }

    public void getAxis() {
        String movement = null;
        boolean newMove = false;
        if (!moving) {
            if (comps[1].getPollData() == -1.0f) {
                movement = "left";
                moving = true;
            } else if (comps[1].getPollData() == 1.0f) {
                movement = "right";
                moving = true;
            } else if (comps[0].getPollData() == -1.0f) {
                movement = "front";
                moving = true;
            } else if (comps[0].getPollData() == 1.0f) {
                movement = "back";
                moving = true;
            }
            newMove = moving;
        } else {
            if (comps[0].getPollData() > -0.9f && comps[0].getPollData() < 0.9f && comps[1].getPollData() > -0.9f && comps[1].getPollData() < 0.9f) {
                movement = "stop";
                moving = false;
                newMove = true;                
            }
        }
        if (newMove) {
            ard.sendToArduino(movement);
            log.append("\nSending: " + movement);
            log.append("\nReceiving: " + ard.recieveFromArduino());
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
                    switch (i) {
                        case 4:
                            buttonName = "Trigger";
                            break;
                        case 5:
                            buttonName = "Thumb";
                            break;
                        case 6:
                            buttonName = "Water";
                            break;
                        case 16:
                            buttonName = "pos" + sliderDegree;
                            break;
                        default:
                            break;
                    }
                    log.append("\nSending: " + buttonName);
                    ard.sendToArduino(buttonName);                    
                    try {
                        Thread.sleep(300); //Give time to release the button.
                    } catch (InterruptedException ex) {
                        System.out.println("The thread was interrupted while sleeping");
                    }
                    
                    log.append("\nReceiving: " + ard.recieveFromArduino());
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
}
