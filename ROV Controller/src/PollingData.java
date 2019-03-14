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
            slider = comps[12]; 
            button8 = comps[13]; (9)
            button9 = comps[14]; (10)
            button10 = comps[15]; (11)
            button11 = comps[16]; (12)
 */
import javax.swing.JTextArea;
import net.java.games.input.Component;
import net.java.games.input.Controller;

public class PollingData extends Thread {

    Controller c;
    JTextArea movementOutput, buttonOutput;
    Component[] comps;

    PollingData(Controller c, JTextArea movementOutput, JTextArea buttonOutput) {
        this.c = c;
        this.movementOutput = movementOutput;
        this.buttonOutput = buttonOutput;
        comps = c.getComponents();
    }

    @Override
    public void run() {
        boolean moving = false;
        while (!this.isInterrupted()) {
            c.poll();

            //String analogData = "y= " + comps[0].getPollData() + ", x= " + comps[1].getPollData();
            //System.out.println(analogData);
            if (comps[1].getPollData() == -1.0f) {
                movementOutput.setText("It's moving left.");
                moving = true;
            } else if (comps[1].getPollData() == 1.0f) {
                movementOutput.setText("It's moving right.");
                moving = true;
            } else if (comps[0].getPollData() == -1.0f) {
                movementOutput.setText("It's moving forward.");
                moving = true;
            } else if (comps[0].getPollData() == 1.0f) {
                movementOutput.setText("It's moving backward.");
                moving = true;
            }
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
                        }
                        buttonOutput.setText("Button (" + buttonName + ") is pressed.");
                    }
                }
            }

            if (moving) {
                if (comps[0].getPollData() > -0.9 && comps[0].getPollData() < 0.9f && comps[1].getPollData() > -0.9 && comps[1].getPollData() < 0.9f) {
                    movementOutput.setText("It stopped.");
                    moving = false;
                }
            }
            //output.setText(analogData + "\n" + movementOutput.getText());
            //movementOutput.setText(analogData);
        }

    }
}
