
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
        try {
        VideoCapture webSource = new VideoCapture(0);
            if (!webSource.isOpened()) {
                throw new CameraNotFound();
            }
        while (!this.isInterrupted()) {
            if (webSource.grab()) { //Grabbing next frame.
                try {
                    webSource.retrieve(frame); //Decodes and returns the grabbed video frame.
                    Imgcodecs.imencode(".bmp", frame, mem); //compresses the image and stores it in the memory buffer that is resized to fit the result.
                    Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));
                    BufferedImage buff = (BufferedImage) im;
                    Graphics g = panel.getGraphics();
                    if (this.isInterrupted()) {
                        break;
                    }
                    g.drawImage(buff, 0, 0, width, height, 0, 0, buff.getWidth(), buff.getHeight(), null); //This has a problem with interrupt
                } catch (IOException ex) {
                    System.out.println("Couldn't convert from bytes to image.");
                }
            } else {
                System.out.println("The frame couldn't be grabbed.");
            }
        }
        System.out.println("Done");
        } catch (CameraNotFound e) {
            System.out.println(e.getMessage());
        }
    }

}
