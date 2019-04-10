
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

    private void openConnection() { //Initializing the connection to the database
        String URL = "jdbc:mariadb://40.87.4.132:3306/ROV_DB";
        String USER = "ROV";
        String PASS = "ROV123";
        try {
            conn = DriverManager.getConnection(URL, USER, PASS); //Get the connection variable after passing these arguments
        } catch (SQLException ex) {
            System.out.println("The connection couldn't be established to the database.");
            System.out.println("The reason for that is: " + ex.getLocalizedMessage());
            System.exit(0);
        }

    }

    public void insertTemp(int tmp) { //Insert to the TempSheet table with the given value
        try {
            Statement s = conn.createStatement();
            Timestamp d = new Timestamp(System.currentTimeMillis()); //Get the current timestamp

            String q = "INSERT INTO TempSheet VALUES (  " + tmp + ", '" + d + "') "; //Insert the value to the table.
            s.execute(q);
        } catch (SQLException e) {
            System.out.println("The insert couldn't be completed due to:");
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    public void insertPh(double ph) { //Insert to the pHSheet table with the given value
        try {
            Statement s = conn.createStatement();
            Timestamp d = new Timestamp(System.currentTimeMillis()); //Get the current timestamp

            String q = "INSERT INTO pHSheet VALUES (  " + ph + ", '" + d + "') "; //Insert the value to the table.
            s.execute(q);
        } catch (SQLException e) {
            System.out.println("The insert couldn't be completed due to:");
            System.out.println(e.getLocalizedMessage());
        }
    }

    public void deletePh(double ph) {
        try {
            Statement s = conn.createStatement();
            String q = "DELETE FROM pHSheet WHERE pH = " + ph;
            s.execute(q);
        } catch (SQLException ex) {
            System.out.println("The removing couldn't be completed due to:");
            System.out.println(ex.getLocalizedMessage());
        }
    }
    
    public void deleteTemperature(double temp) {
        try {
            Statement s = conn.createStatement();
            String q = "DELETE FROM TempSheet WHERE Temperature = " + temp;
            s.execute(q);
        } catch (SQLException ex) {
            System.out.println("The removing couldn't be completed due to:");
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public boolean pHExists(double ph) {
        try {
            Statement s = conn.createStatement();
            String q = "SELECT * FROM pHSheet WHERE pH = " + ph;
            ResultSet rs = s.executeQuery(q);
            return rs.next();
        } catch (SQLException ex) {
            System.out.println("The result couldn't be completed due to:");
            System.out.println(ex.getLocalizedMessage());
        }
        return true;
    }
    
    public boolean tempExists(double ph) {
        try {
            Statement s = conn.createStatement();
            String q = "SELECT * FROM TempSheet WHERE Temperature = " + ph;
            ResultSet rs = s.executeQuery(q);
            return rs.next();
        } catch (SQLException ex) {
            System.out.println("The result couldn't be completed due to:");
            System.out.println(ex.getLocalizedMessage());
        }
        return true;
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
