
import java.awt.Color;

public class Picture extends SimplePicture {

    public Picture(String fileName) {
        // let the parent class handle this fileName
        super(fileName);
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
