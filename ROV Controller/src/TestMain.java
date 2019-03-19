
import java.sql.DatabaseMetaData;
import java.sql.SQLException;


public class TestMain {
    
    public static void main(String[] args) throws SQLException{
        
        
      Database db = Database.initalization();
      db.insertRecord("6", "1");
        
        
    }
}
