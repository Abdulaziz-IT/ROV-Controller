
import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;

public class Picture extends SimplePicture {

    public Picture() {
        /* not needed but use it to show students the implicit call to 
     * super() child constructors always call a parent constructor 
         */
        super();
    }

    public Picture(int width, int height) {
        // let the parent class handle this width and height
        super(width, height);
    }

    public Picture(String fileName) {
        // let the parent class handle this fileName
        super(fileName);
    }

    public Picture(Picture copyPicture) {
        // let the parent class do the copy
        super(copyPicture);
    }

    public Picture(int width, int height, Color theColor) {
        // let the parent class handle these parameters
        super(width, height, theColor);
    }

    public static Picture getPicture(String fileName) {
        Picture p = new Picture(fileName);
        return p;
    }

    public void removeNotBlack(double thresholding) {
        for (int i = 0; i < this.getWidth(); i++) {
            for (int j = 0; j < this.getHeight(); j++) {
                if (this.getPixel(i, j).colorDistance(Color.BLACK) > thresholding) {
                    this.getPixel(i, j).setColor(Color.WHITE);
                } else {
                    this.getPixel(i, j).setColor(Color.BLACK);
                }
            }
        }
    }
}
