
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

public class VideoStreaming extends Thread {

    Mat frame = new Mat();
    MatOfByte mem = new MatOfByte();
    int width, height;
    JPanel panel;
    JButton start;
    JButton stop;
    BufferedImage image;
    int camera;

    public VideoStreaming(int width, int height, JPanel panel, JButton start, JButton stop, int camera) {
        this.width = width;
        this.height = height;
        this.panel = panel;
        this.start = start;
        this.stop = stop;
        this.camera = camera;
    }

    @Override
    public void run() {
        VideoCapture webSource = new VideoCapture(camera);
        try {
            if (!webSource.isOpened()) {
                throw new CameraNotFound();
            }
            while (!this.isInterrupted()) {
                if (webSource.grab()) { //Grabbing next frame.
                    try {
                        webSource.retrieve(frame); //Decodes and returns the grabbed video frame.
                        Imgcodecs.imencode(".jpg", frame, mem); //compresses the image and stores it in the memory buffer that is resized to fit the result.
                        image = ImageIO.read(new ByteArrayInputStream(mem.toArray()));
                        Graphics g = panel.getGraphics();
                        if (this.isInterrupted()) {
                            break;
                        }
                        g.drawImage(image, 0, 0, panel.getWidth(), panel.getHeight(), 0, 0, image.getWidth(), image.getHeight(), null); //This has a problem with interrupt                        
                    } catch (IOException ex) { //if an error occurs during reading.
                        System.out.println("Couldn't convert from bytes to image.");
                    }
                } else {
                    System.out.println("The frame couldn't be grabbed.");
                }
            }
            System.out.println("Done");
        } catch (CameraNotFound e) {
            System.out.println(e.getMessage());
            start.setEnabled(true);
            stop.setEnabled(false);
        }
        webSource.release();
        panel.updateUI();
    }

    public BufferedImage getImage() {
        return image;
    }

}
