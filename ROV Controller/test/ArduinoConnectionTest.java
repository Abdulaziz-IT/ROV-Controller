/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author azzoz
 */
public class ArduinoConnectionTest {

    ArduinoConnection conn;

    public ArduinoConnectionTest() {
    }

    @Before
    public void setUp() {
        conn = ArduinoConnection.intialization();
    }

    /**
     * Test of intialization method, of class ArduinoConnection.
     */
    @Test
    public void testIntialization() {
        assertTrue(conn.checkConnection());
    }

}
