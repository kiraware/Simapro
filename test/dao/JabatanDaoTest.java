package dao;

import db.DBHelper;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Jabatan;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class JabatanDaoTest {
    private static JabatanDao dao;
    private static final Logger logger = Logger.getLogger(JabatanDaoTest.class.getName());

    @Before
    public void setUp() {
        Connection conn = DBHelper.getConnection();
        try {
            // set auto commit false so any operation in this test will be discarded.
            conn.setAutoCommit(false);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        dao = new JabatanDao(conn);
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
     * Test of add method, of class JabatanDao.
     */
    @Test
    public void testAdd() {
        Jabatan jabatan = new Jabatan();
        UUID uuid = jabatan.getUuid();
        jabatan.setNama("test");
        dao.add(jabatan);
        Jabatan jabatanFromDb = dao.get(uuid);
        assertEquals(uuid, jabatanFromDb.getUuid());
        assertEquals("test", jabatanFromDb.getNama());
    }

    /**
     * Test of all method, of class JabatanDao.
     */
    @Test
    public void testAll() {
        Jabatan jabatan1 = new Jabatan();
        Jabatan jabatan2 = new Jabatan();
        dao.add(jabatan1);
        dao.add(jabatan2);
        List<Jabatan> jabatans = dao.all();
        assertTrue(jabatans.contains(jabatan1));
        assertTrue(jabatans.contains(jabatan2));
    }

    /**
     * Test of edit method, of class JabatanDao.
     */
    @Test
    public void testEdit() {
        Jabatan jabatan = new Jabatan();
        jabatan.setNama("test");
        dao.add(jabatan);
        jabatan.setNama("tset");
        dao.edit(jabatan);
        Jabatan jabatanFromDb = dao.get(jabatan.getUuid());
        assertEquals("tset", jabatanFromDb.getNama());
    }

    /**
     * Test of delete method, of class JabatanDao.
     */
    @Test
    public void testDelete() {
        Jabatan jabatan = new Jabatan();
        dao.add(jabatan);
        Jabatan jabatanFromDb = dao.get(jabatan.getUuid());
        assertNotNull(jabatanFromDb);
        dao.delete(jabatan.getUuid());
        jabatanFromDb = dao.get(jabatan.getUuid());
        assertNull(jabatanFromDb);
    }
}
