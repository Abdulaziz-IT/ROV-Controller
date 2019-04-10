
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class ControllerTest {

    Database db;
    int ph;
    int tmp;

    public ControllerTest() {
    }

    @Before
    public void setUp() {
        db = Database.initalization();
        ph = 7;
        tmp = 45;
    }

    @After
    public void tearDown() {
        db.deletePh(ph);
        db.deleteTemperature(tmp);
    }

    /**
     * Test of insertTemp method, of class Database.
     */
    @Test
    public void testInsertPhRecord() {
        db.insertPh(ph);
        assertTrue(db.pHExists(ph));
        
    }
    
    @Test
    public void testInsertTempRecord() {
        db.insertTemp(tmp);
        assertTrue(db.tempExists(tmp));
        
    }

}
