
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.*;
import java.awt.geom.*;

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

    /////////////////////// Constructors /////////////////////////
    /**
     * A Constructor that takes no arguments. All fields will be null. A
     * no-argument constructor must be given in order for a class to be able to
     * be subclassed. By default all subclasses will implicitly call this in
     * their parent's no argument constructor unless a different call to super()
     * is explicitly made as the first line of code in a constructor.
     */
    public SimplePicture() {
        this(200, 100);
    }

    /**
     * A Constructor that takes a file name and uses the file to create a
     * picture
     *
     * @param fileName the file name to use in creating the picture
     */
    public SimplePicture(String fileName) {

        // load the picture into the buffered image 
        load(fileName);

    }

    /**
     * A constructor that takes the width and height desired for a picture and
     * creates a buffered image of that size. This constructor doesn't show the
     * picture.
     *
     * @param width the desired width
     * @param height the desired height
     * @param theColor the desired background color
     */
    public SimplePicture(int width, int height, Color theColor) {
        bufferedImage
                = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        title = "New Picture";
        fileName = "New Picture";
        extension = "jpg";
        setAllPixelsToAColor(theColor);
    }

    /**
     * A constructor that takes the width and height desired for a picture and
     * creates a buffered image of that size with a background of white. This
     * constructor doesn't show the picture.
     *
     * @param width the desired width
     * @param height the desired height
     */
    public SimplePicture(int width, int height) {
        this(width, height, Color.WHITE);
    }

    /**
     * A Constructor that takes a picture to copy information from
     *
     * @param copyPicture the picture to copy from
     */
    public SimplePicture(SimplePicture copyPicture) {
        if (copyPicture.fileName != null) {
            this.fileName = new String(copyPicture.fileName);
            this.extension = copyPicture.extension;
        }
        if (copyPicture.title != null) {
            this.title = new String(copyPicture.title);
        }
        if (copyPicture.bufferedImage != null) {
            this.bufferedImage = new BufferedImage(copyPicture.getWidth(),
                    copyPicture.getHeight(), BufferedImage.TYPE_INT_RGB);
            this.copyPicture(copyPicture);
        }
    }

    ////////////////////////// Methods //////////////////////////////////
    /**
     * Method to get the extension for this picture
     *
     * @return the extendsion (jpg or bmp)
     */
    public String getExtension() {
        return extension;
    }

    /**
     * Method that will copy all of the passed source picture into the current
     * picture object
     *
     * @param sourcePicture the picture object to copy
     */
    public void copyPicture(SimplePicture sourcePicture) {
        Pixel sourcePixel = null;
        Pixel targetPixel = null;

        // loop through the columns
        for (int sourceX = 0, targetX = 0;
                sourceX < sourcePicture.getWidth()
                && targetX < this.getWidth();
                sourceX++, targetX++) {
            // loop through the rows
            for (int sourceY = 0, targetY = 0;
                    sourceY < sourcePicture.getHeight()
                    && targetY < this.getHeight();
                    sourceY++, targetY++) {
                sourcePixel = sourcePicture.getPixel(sourceX, sourceY);
                targetPixel = this.getPixel(targetX, targetY);
                targetPixel.setColor(sourcePixel.getColor());
            }
        }

    }

    /**
     * Method to set the color in the picture to the passed color
     *
     * @param color the color to set to
     */
    public void setAllPixelsToAColor(Color color) {
        // loop through all x
        for (int x = 0; x < this.getWidth(); x++) {
            // loop through all y
            for (int y = 0; y < this.getHeight(); y++) {
                getPixel(x, y).setColor(color);
            }
        }
    }

    /**
     * Method to get the buffered image
     *
     * @return the buffered image
     */
    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    /**
     * Method to get a graphics object for this picture to use to draw on
     *
     * @return a graphics object to use for drawing
     */
    public Graphics getGraphics() {
        return bufferedImage.getGraphics();
    }

    /**
     * Method to get a Graphics2D object for this picture which can be used to
     * do 2D drawing on the picture
     */
    public Graphics2D createGraphics() {
        return bufferedImage.createGraphics();
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
     * Method to get a one-dimensional array of Pixels for this simple picture
     *
     * @return a one-dimensional array of Pixel objects starting with y=0 to
     * y=height-1 and x=0 to x=width-1.
     */
    public Pixel[] getPixels() {
        int width = getWidth();
        int height = getHeight();
        Pixel[] pixelArray = new Pixel[width * height];

        // loop through height rows from top to bottom
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                pixelArray[row * width + col] = new Pixel(this, col, row);
            }
        }

        return pixelArray;
    }

    /**
     * Method to load the buffered image with the passed image
     *
     * @param image the image to use
     */
    
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
     * Method to write the contents of the picture to a file with the passed
     * name
     *
     * @param fileName the name of the file to write the picture to
     */
    public void write(String fileName) {
        String extension = this.extension; // the default is current

        try {
            // create the file object
            File file = new File(fileName);

            // get the extension
            int posDot = fileName.indexOf('.');
            if (posDot >= 0) {
                extension = fileName.substring(posDot + 1);
            }

            // write the contents of the buffered image to the file as jpeg
            ImageIO.write(bufferedImage, extension, file);

        } catch (Exception ex) {
            System.out.println("Couldn't write out the picture to the file " + fileName);
        }

    }


} // end of SimplePicture class
