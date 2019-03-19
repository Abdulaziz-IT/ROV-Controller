
import java.sql.Connection;
import java.sql.DriverManager;
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
    
    public void insertRecord(String ph, String tmp) throws SQLException{
        
        Statement s = conn.createStatement();
        Timestamp d = new Timestamp(System.currentTimeMillis());
        
        
        String q = "INSERT INTO WaterQuality VALUES (  "+ph+", "+tmp+", '"+d+"') ";
        s.execute(q);
        
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
