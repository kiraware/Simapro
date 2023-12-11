package dao;

import db.DBHelper;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Pegawai;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PegawaiDaoTest {
    private static PegawaiDao dao;
    private static final Logger logger = Logger.getLogger(PegawaiDaoTest.class.getName());

    @Before
    public void setUp() {
        Connection conn = DBHelper.getConnection();
        try {
            // set auto commit false so any operation in this test will be discarded.
            conn.setAutoCommit(false);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        dao = new PegawaiDao(conn);
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
     * Test of add method, of class PegawaiDao.
     */
    @Test
    public void testAdd() {
        Pegawai pegawai = new Pegawai(UUID.randomUUID(), null);
        UUID uuid = pegawai.getUuid();
        pegawai.setNama("test");
        dao.add(pegawai);
        Pegawai pegawaiFromDb = dao.get(uuid);
        assertEquals(uuid, pegawaiFromDb.getUuid());
        assertEquals("test", pegawaiFromDb.getNama());
    }

    /**
     * Test of all method, of class PegawaiDao.
     */
    @Test
    public void testAll() {
        Pegawai pegawai1 = new Pegawai(UUID.randomUUID(), "test");
        Pegawai pegawai2 = new Pegawai(UUID.randomUUID(), "test");
        dao.add(pegawai1);
        dao.add(pegawai2);
        List<Pegawai> pegawais = dao.all();
        assertTrue(pegawais.contains(pegawai1));
        assertTrue(pegawais.contains(pegawai2));
    }

    /**
     * Test of edit method, of class PegawaiDao.
     */
    @Test
    public void testEdit() {
        Pegawai pegawai = new Pegawai(UUID.randomUUID(), null);
        pegawai.setNama("test");
        dao.add(pegawai);
        pegawai.setNama("tset");
        dao.edit(pegawai);
        Pegawai pegawaiFromDb = dao.get(pegawai.getUuid());
        assertEquals("tset", pegawaiFromDb.getNama());
    }

    /**
     * Test of delete method, of class PegawaiDao.
     */
    @Test
    public void testDelete() {
        Pegawai pegawai = new Pegawai(UUID.randomUUID(), "test");
        dao.add(pegawai);
        Pegawai pegawaiFromDb = dao.get(pegawai.getUuid());
        assertNotNull(pegawaiFromDb);
        dao.delete(pegawai.getUuid());
        pegawaiFromDb = dao.get(pegawai.getUuid());
        assertNull(pegawaiFromDb);
    }
}
