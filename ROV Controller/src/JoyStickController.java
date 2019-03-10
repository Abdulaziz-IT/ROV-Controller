
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import net.java.games.input.*;

// Abdulaziz Waleed Alshememry
public class JoyStickController {

    public static void main(String[] args) throws SQLException {
        //JInputJoystick joystick = new JInputJoystick(Controller.Type.GAMEPAD);
        String URL = "jdbc:mariadb://137.135.109.234:3306/ROV_DB";
        String USER = "ROV";
        String PASS = "ROV123";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            System.out.println("The connection couldn't be established to the database.");
            System.out.println("The reason for that is: " + ex.getLocalizedMessage());
            System.exit(0);
        }

        conn.close();
    }
}
