package dao;

import db.DBHelper;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Tim;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TimDaoTest {
    private static TimDao dao;
    private static final Logger logger = Logger.getLogger(TimDaoTest.class.getName());

    @Before
    public void setUp() {
        Connection conn = DBHelper.getConnection();
        try {
            // set auto commit false so any operation in this test will be discarded.
            conn.setAutoCommit(false);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        dao = new TimDao(conn);
    }
    
    @After
    public void tearDown() {
        Connection conn = DBHelper.getConnection();
        try {
            conn.setAutoCommit(true);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    /**
     * Test of add method, of class TimDao.
     */
    @Test
    public void testAdd() {
        Tim tim = new Tim();
        UUID uuid = tim.getUuid();
        tim.setNama("test");
        dao.add(tim);
        Tim timFromDb = dao.get(uuid);
        assertEquals(uuid, timFromDb.getUuid());
        assertEquals("test", timFromDb.getNama());
    }

    /**
     * Test of all method, of class TimDao.
     */
    @Test
    public void testAll() {
        Tim tim1 = new Tim();
        Tim tim2 = new Tim();
        dao.add(tim1);
        dao.add(tim2);
        List<Tim> tims = dao.all();
        assertTrue(tims.contains(tim1));
        assertTrue(tims.contains(tim2));
    }

    /**
     * Test of edit method, of class TimDao.
     */
    @Test
    public void testEdit() {
        Tim tim = new Tim();
        tim.setNama("test");
        dao.add(tim);
        tim.setNama("tset");
        dao.edit(tim);
        Tim timFromDb = dao.get(tim.getUuid());
        assertEquals("tset", timFromDb.getNama());
    }

    /**
     * Test of delete method, of class TimDao.
     */
    @Test
    public void testDelete() {
        Tim tim = new Tim();
        dao.add(tim);
        Tim timFromDb = dao.get(tim.getUuid());
        assertNotNull(timFromDb);
        dao.delete(tim.getUuid());
        timFromDb = dao.get(tim.getUuid());
        assertNull(timFromDb);
    }
}
