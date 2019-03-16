
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
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
            System.out.println("1Intrrupt: " + this.isInterrupted());
            if (webSource.grab()) { //Grabbing next frame.
                try {
                    System.out.println("2Intrrupt: " + this.isInterrupted());
                    webSource.retrieve(frame); //Decodes and returns the grabbed video frame.
                    System.out.println("3Intrrupt: " + this.isInterrupted());
                    Imgcodecs.imencode(".bmp", frame, mem); //compresses the image and stores it in the memory buffer that is resized to fit the result.
                    System.out.println("4Intrrupt: " + this.isInterrupted());
                    Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));
                    System.out.println("5Intrrupt: " + this.isInterrupted());
                    BufferedImage buff = (BufferedImage) im;
                    System.out.println("6Intrrupt: " + this.isInterrupted());
                    Graphics g = panel.getGraphics();
                    System.out.println("7Intrrupt: " + this.isInterrupted());
                    if (this.isInterrupted()) {
                        break;
                    }
                    g.drawImage(buff, 0, 0, width, height, 0, 0, buff.getWidth(), buff.getHeight(), null); //This has a problem with interrupt
                    System.out.println("8Intrrupt: " + this.isInterrupted());
                } catch (IOException ex) {
                    System.out.println("Couldn't convert from bytes to image.");
                }
            } else {
                System.out.println("The frame couldn't be grabbed.");
            }
        }
        System.out.println("Done");
    }

}
