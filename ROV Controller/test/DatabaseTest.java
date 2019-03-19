
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class DatabaseTest {

    Database db;
    int ph;
    int tmp;

    public DatabaseTest() {
    }

    @Before
    public void setUp() {
        db = Database.initalization();
        ph = 7;
        tmp = 45;
    }

    @After
    public void tearDown() {
        db.deleteRecord(ph);
    }

    /**
     * Test of insertRecord method, of class Database.
     */
    @Test
    public void testInsertRecord() {
        System.out.println("insertRecord");

        db.insertRecord(ph, tmp);
        assertTrue(db.recordExists(ph));
        
    }

}
