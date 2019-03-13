
import javax.swing.JTextArea;
import net.java.games.input.Component;
import net.java.games.input.Controller;

//Abdulaziz Waleed Alshememry.
public class PollingData extends Thread {

    Controller c;
    JTextArea output;
    Component[] comps;

    PollingData(Controller c, JTextArea output) {
        this.c = c;
        this.output = output;
        comps = c.getComponents();
    }

    @Override
    public void run() {
        try {
            while (!this.isInterrupted()) {
                c.poll();
                Thread.sleep(500);
                String analogData = "x= " + comps[0].getPollData() + ", y= " + comps[1].getPollData();
                System.out.println(analogData);
                output.setText(analogData); // this is not working for some reason
            }
        } catch (InterruptedException ex) {
            System.out.println("The thread was interrupted while it was in the 500ms sleep.");
        }
    }
}
