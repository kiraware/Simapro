package dao;

import db.DBHelper;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Tugas;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TugasDaoTest {
    private static TugasDao dao;
    private static final Logger logger = Logger.getLogger(TugasDaoTest.class.getName());

    @Before
    public void setUp() {
        Connection conn = DBHelper.getConnection();
        try {
            // set auto commit false so any operation in this test will be discarded.
            conn.setAutoCommit(false);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        dao = new TugasDao(conn);
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
     * Test of add method, of class TugasDao.
     */
    @Test
    public void testAdd() {
        Tugas tugas = new Tugas(UUID.randomUUID(), null, null, null);
        UUID uuid = tugas.getUuid();
        tugas.setNama("test");
        tugas.setDeskripsi("desc");
        dao.add(tugas);
        Tugas tugasFromDb = dao.get(uuid);
        assertEquals(uuid, tugasFromDb.getUuid());
        assertEquals("test", tugasFromDb.getNama());
        assertEquals("desc", tugasFromDb.getDeskripsi());
    }

    /**
     * Test of all method, of class TugasDao.
     */
    @Test
    public void testAll() {
        Tugas tugas1 = new Tugas(UUID.randomUUID(), "", "", null);
        Tugas tugas2 = new Tugas(UUID.randomUUID(), "", "", null);
        dao.add(tugas1);
        dao.add(tugas2);
        List<Tugas> tugass = dao.all();
        assertTrue(tugass.contains(tugas1));
        assertTrue(tugass.contains(tugas2));
    }

    /**
     * Test of edit method, of class TugasDao.
     */
    @Test
    public void testEdit() {
        Tugas tugas = new Tugas(UUID.randomUUID(), null, null, null);
        tugas.setNama("test");
        tugas.setDeskripsi("desc");
        dao.add(tugas);
        tugas.setNama("tset");
        dao.edit(tugas);
        Tugas tugasFromDb = dao.get(tugas.getUuid());
        assertEquals("tset", tugasFromDb.getNama());
    }

    /**
     * Test of delete method, of class TugasDao.
     */
    @Test
    public void testDelete() {
        Tugas tugas = new Tugas(UUID.randomUUID(), "", "", null);
        dao.add(tugas);
        Tugas tugasFromDb = dao.get(tugas.getUuid());
        assertNotNull(tugasFromDb);
        dao.delete(tugas.getUuid());
        tugasFromDb = dao.get(tugas.getUuid());
        assertNull(tugasFromDb);
    }
}
