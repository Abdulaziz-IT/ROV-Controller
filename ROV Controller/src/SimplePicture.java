
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.*;

/**
 * A class that represents a simple picture. A simple picture may have an
 * associated file name and a title. A simple picture has pixels, width, and
 * height. A simple picture uses a BufferedImage to hold the pixels. You can
 * show a simple picture in a PictureFrame (a JFrame).
 *
 * Copyright Georgia Institute of Technology 2004
 *
 * @author Barb Ericson ericson@cc.gatech.edu
 */
public class SimplePicture implements DigitalPicture {

    /////////////////////// Fields /////////////////////////
    /**
     * the file name associated with the simple picture
     */
    private String fileName;

    /**
     * the title of the simple picture
     */
    private String title;

    /**
     * buffered image to hold pixels for the simple picture
     */
    private BufferedImage bufferedImage;

    /**
     * frame used to display the simple picture
     */
    /**
     * extension for this file (jpg or bmp)
     */
    private String extension;

    public SimplePicture(String fileName) {

        // load the picture into the buffered image 
        load(fileName);

    }

    /**
     * Method to get the file name associated with the picture
     *
     * @return the file name associated with the picture
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Method to get the title of the picture
     *
     * @return the title of the picture
     */
    public String getTitle() {
        return title;
    }

    /**
     * Method to get the width of the picture in pixels
     *
     * @return the width of the picture in pixels
     */
    public int getWidth() {
        return bufferedImage.getWidth();
    }

    /**
     * Method to get the height of the picture in pixels
     *
     * @return the height of the picture in pixels
     */
    public int getHeight() {
        return bufferedImage.getHeight();
    }

    /**
     * Method to get an image from the picture
     *
     * @return the buffered image since it is an image
     */
    public Image getImage() {
        return bufferedImage;
    }

    /**
     * Method to get a pixel object for the given x and y location
     *
     * @param x the x location of the pixel in the picture
     * @param y the y location of the pixel in the picture
     * @return a Pixel object for this location
     */
    public Pixel getPixel(int x, int y) {
        // create the pixel object for this picture and the given x and y location
        Pixel pixel = new Pixel(this, x, y);
        return pixel;
    }

    /**
     * Method to load the picture from the passed file name
     *
     * @param fileName the file name to use to load the picture from
     * @return true if success else false
     */
    public boolean load(String fileName) {
        boolean result = true; // the default is an okay result

        // set the current piture's file name
        this.fileName = fileName;

        // set the extension
        int posDot = fileName.indexOf('.');
        if (posDot >= 0) {
            this.extension = fileName.substring(posDot + 1);
        }

        // if the current title is null use the file name
        if (title == null) {
            title = fileName;
        }

        // try to get the buffered image from the file
        try {
            bufferedImage = ImageIO.read(new File(this.fileName));

            /* if anything goes wrong tell the user on the console, set the
    * image to a black image with a message on it, and return false */
        } catch (Exception ex) {
            System.out.println("Couldn't load the file " + this.fileName);
            bufferedImage = new BufferedImage(600, 200, BufferedImage.TYPE_INT_RGB);
            addMessage("Couldn't load " + fileName, 5, 100);
            result = false;
        }

        return result;
    }

    /**
     * Method to draw a message as a string on the buffered image
     *
     * @param message the message to draw on the buffered image
     * @param xPos the leftmost point of the string in x
     * @param yPos the bottom of the string in y
     */
    public void addMessage(String message, int xPos, int yPos) {
        // get a graphics context to use to draw on the buffered image
        Graphics2D graphics2d = bufferedImage.createGraphics();

        // set the color to white
        graphics2d.setPaint(Color.white);

        // set the font to Helvetica bold style and size 16
        graphics2d.setFont(new Font("Helvetica", Font.BOLD, 16));

        // draw the message
        graphics2d.drawString(message, xPos, yPos);

    }

    /**
     * Method to return the pixel value as an int for the given x and y location
     *
     * @param x the x coordinate of the pixel
     * @param y the y coordinate of the pixel
     * @return the pixel value as an integer (alpha, red, green, blue)
     */
    public int getBasicPixel(int x, int y) {
        return bufferedImage.getRGB(x, y);
    }

    /**
     * Method to set the value of a pixel in the picture from an int
     *
     * @param x the x coordinate of the pixel
     * @param y the y coordinate of the pixel
     * @param rgb the new rgb value of the pixel (alpha, red, green, blue)
     */
    public void setBasicPixel(int x, int y, int rgb) {
        bufferedImage.setRGB(x, y, rgb);
    }

} // end of SimplePicture class
