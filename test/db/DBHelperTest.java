/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package db;

import java.sql.Connection;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class DBHelperTest {
    
    public DBHelperTest() {
    }

    /**
     * Test of getProperty method, of class DBHelper.
     */
    @Test
    public void testGetProperty() {
        String driver = DBHelper.getProperty("driver");
        assertEquals("com.mysql.cj.jdbc.Driver", driver);
    }

    /**
     * Test of getConnection method, of class DBHelper.
     */
    @Test
    public void testGetConnection() {
        Connection dbConnection = DBHelper.getConnection();
        assertNotNull(dbConnection);
    }
}
