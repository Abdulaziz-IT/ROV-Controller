
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class Database {

    private Connection conn;
    private static Database db;

    private Database() {
        openConnection();
    }

    public static Database initalization() {
        if (db == null) {
            db = new Database();
        }
        return db;
    }

    private void openConnection() {
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

    public void insertRecord(int ph, int tmp) {
        try {
            Statement s = conn.createStatement();
            Timestamp d = new Timestamp(System.currentTimeMillis());

            String q = "INSERT INTO WaterQuality VALUES (  " + ph + ", " + tmp + ", '" + d + "') ";
            s.execute(q);
        } catch (SQLException e) {
            System.out.println("The insert couldn't be completed due to:");
            System.out.println(e.getLocalizedMessage());
        }
    }

    public void deleteRecord(int ph) {
        try {
            Statement s = conn.createStatement();
            String q = "DELETE FROM WaterQuality WHERE pH = " + ph;
            s.execute(q);
        } catch (SQLException ex) {
            System.out.println("The removing couldn't be completed due to:");
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public boolean recordExists(int ph) {
        try {
            Statement s = conn.createStatement();
            String q = "SELECT * FROM WaterQuality WHERE pH = " + ph;
            ResultSet rs = s.executeQuery(q);
            return rs.next();
        } catch (SQLException ex) {
            System.out.println("The result couldn't be completed due to:");
            System.out.println(ex.getLocalizedMessage());
        }
        return false;
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
