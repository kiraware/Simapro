package dao;

import db.DBHelper;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Jabatan;
import model.Tim;
import model.TimPegawai;
import model.Tugas;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TimPegawaiDaoTest {
    private static Tim tim;
    private static TimPegawaiDao dao;
    private static Tugas tugas1, tugas2, tugas3, tugas4, tugas5;
    private static final Logger logger = Logger.getLogger(TimPegawaiDaoTest.class.getName());
    
    @BeforeClass
    public static void setUpClass() {
        Connection conn = DBHelper.getConnection();
        try {
            // set auto commit false so any operation in this test will be discarded.
            conn.setAutoCommit(false);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        TugasDao tugasDao = new TugasDao(conn);
        TimDao timDao = new TimDao(conn);

        tugas1 = new Tugas(UUID.randomUUID(), "test", "desc", null);
        tugasDao.add(tugas1);

        tugas2 = new Tugas(UUID.randomUUID(), "test", "desc", null);
        tugasDao.add(tugas2);

        tugas3 = new Tugas(UUID.randomUUID(), "test", "desc", null);
        tugasDao.add(tugas3);

        tugas4 = new Tugas(UUID.randomUUID(), "test", "desc", null);
        tugasDao.add(tugas4);

        tugas5 = new Tugas(UUID.randomUUID(), "test", "desc", null);
        tugasDao.add(tugas5);
        
        tim = new Tim(UUID.randomUUID(), "test");
        timDao.add(tim);
    }
    
    @AfterClass
    public static void tearDownClass() {
        Connection conn = DBHelper.getConnection();
        try {
            conn.setAutoCommit(true);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Before
    public void setUp() {
        Connection conn = DBHelper.getConnection();
        try {
            // set auto commit false so any operation in this test will be discarded.
            conn.setAutoCommit(false);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        dao = new TimPegawaiDao(conn);
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
     * Test of add method, of class TimPegawaiDao.
     */
    @Test
    public void testAdd() {
        TimPegawai timPegawai = new TimPegawai(tugas1.getUuid(), null, null, null);
        dao.add(timPegawai);
        TimPegawai timPegawaiFromDb = dao.get(timPegawai.getUuidTugas());
        assertEquals(timPegawai.getUuidTugas(), timPegawaiFromDb.getUuidTugas());
    }

    /**
     * Test of all method, of class TimPegawaiDao.
     */
    @Test
    public void testAll() {
        TimPegawai timPegawai1 = new TimPegawai(tugas2.getUuid(), null, null, null);
        TimPegawai timPegawai2 = new TimPegawai(tugas3.getUuid(), null, null, null);
        dao.add(timPegawai1);
        dao.add(timPegawai2);
        List<TimPegawai> timPegawais = dao.all();
        assertTrue(timPegawais.contains(timPegawai1));
        assertTrue(timPegawais.contains(timPegawai2));
    }

    /**
     * Test of edit method, of class TimPegawaiDao.
     */
    @Test
    public void testEdit() {
        TimPegawai timPegawai = new TimPegawai(tugas4.getUuid(), null, null, null);
        UUID expResult = tim.getUuid();
        dao.add(timPegawai);
        timPegawai.setUuidTim(expResult);
        dao.edit(timPegawai);
        TimPegawai timPegawaiFromDb = dao.get(timPegawai.getUuidTugas());
        assertEquals(expResult, timPegawaiFromDb.getUuidTim());
    }

    /**
     * Test of delete method, of class TimPegawaiDao.
     */
    @Test
    public void testDelete() {
        TimPegawai timPegawai = new TimPegawai(tugas5.getUuid(), null, null, null);
        dao.add(timPegawai);
        TimPegawai timPegawaiFromDb = dao.get(timPegawai.getUuidTugas());
        assertNotNull(timPegawaiFromDb);
        dao.delete(timPegawai.getUuidTugas());
        timPegawaiFromDb = dao.get(timPegawai.getUuidTugas());
        assertNull(timPegawaiFromDb);
    }
}
