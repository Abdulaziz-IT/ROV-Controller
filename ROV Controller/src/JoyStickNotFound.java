
public class JoyStickNotFound extends NullPointerException {

    JoyStickNotFound() {
        super("You don't have any controller with the type 'STICK'");
    }

}
