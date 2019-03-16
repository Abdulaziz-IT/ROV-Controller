
public class CameraNotFound extends Exception {

    public CameraNotFound() {
        super("You don't have any camera running!");
    }

    public CameraNotFound(String msg) {
        super(msg);
    }
}
