
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    Connection conn;

    public void openConnection() {
        //JInputJoystick joystick = new JInputJoystick(Controller.Type.GAMEPAD);
        String URL = "jdbc:mariadb://137.135.109.234:3306/ROV_DB";
        String USER = "ROV";
        String PASS = "ROV123";
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            System.out.println("The connection couldn't be established to the database.");
            System.out.println("The reason for that is: " + ex.getLocalizedMessage());
            System.exit(0);
        }

    }
    
    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println("The connection couldn't be closed.");
                System.out.println(ex.getLocalizedMessage());
            }
        }
    }
}
