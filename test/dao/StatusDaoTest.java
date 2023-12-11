package dao;

import db.DBHelper;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Status;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatusDaoTest {
    private static StatusDao dao;
    private static final Logger logger = Logger.getLogger(StatusDaoTest.class.getName());

    @Before
    public void setUp() {
        Connection conn = DBHelper.getConnection();
        try {
            // set auto commit false so any operation in this test will be discarded.
            conn.setAutoCommit(false);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        dao = new StatusDao(conn);
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
     * Test of add method, of class StatusDao.
     */
    @Test
    public void testAdd() {
        Status status = new Status();
        UUID uuid = status.getUuid();
        status.setNama("test");
        dao.add(status);
        Status statusFromDb = dao.get(uuid);
        assertEquals(uuid, statusFromDb.getUuid());
        assertEquals("test", statusFromDb.getNama());
    }

    /**
     * Test of all method, of class StatusDao.
     */
    @Test
    public void testAll() {
        Status status1 = new Status();
        Status status2 = new Status();
        dao.add(status1);
        dao.add(status2);
        List<Status> statuss = dao.all();
        assertTrue(statuss.contains(status1));
        assertTrue(statuss.contains(status2));
    }

    /**
     * Test of edit method, of class StatusDao.
     */
    @Test
    public void testEdit() {
        Status status = new Status();
        status.setNama("test");
        dao.add(status);
        status.setNama("tset");
        dao.edit(status);
        Status statusFromDb = dao.get(status.getUuid());
        assertEquals("tset", statusFromDb.getNama());
    }

    /**
     * Test of delete method, of class StatusDao.
     */
    @Test
    public void testDelete() {
        Status status = new Status();
        dao.add(status);
        Status statusFromDb = dao.get(status.getUuid());
        assertNotNull(statusFromDb);
        dao.delete(status.getUuid());
        statusFromDb = dao.get(status.getUuid());
        assertNull(statusFromDb);
    }
}
