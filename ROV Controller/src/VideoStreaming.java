
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

//Abdulaziz Waleed Alshememry.
public class VideoStreaming extends Thread {

    VideoCapture webSource = new VideoCapture(0); //0 = default
    Mat frame = new Mat();
    MatOfByte mem = new MatOfByte();
    int width, height;
    JPanel panel;

    public VideoStreaming(int width, int height, JPanel panel) {
        this.width = width;
        this.height = height;
        this.panel = panel;
    }

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            if (webSource.grab()) {
                try {
                    webSource.retrieve(frame);
                    Imgcodecs.imencode(".bmp", frame, mem);
                    Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));

                    BufferedImage buff = (BufferedImage) im;
                    Graphics g = panel.getGraphics();
                    g.drawImage(buff, 0, 0, width, height - 150, 0, 0, buff.getWidth(), buff.getHeight(), null);

                } catch (Exception ex) {
                    System.out.println("Error");
                }
            } else {
                System.out.println("The frame couldn't be grabbed.");
            }
        }
    }
}
