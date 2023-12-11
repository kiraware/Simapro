package dao;

import db.DBHelper;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Jadwal;
import model.Status;
import model.Tugas;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class JadwalDaoTest {
    private static JadwalDao dao;
    private static Tugas tugas1, tugas2, tugas3, tugas4, tugas5;
    private static final Logger logger = Logger.getLogger(JadwalDaoTest.class.getName());
    
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
        StatusDao statusDao = new StatusDao(conn);

        Status status = new Status();
        status.setNama("On Test");
        statusDao.add(status);

        tugas1 = new Tugas();
        tugas1.setNama("Test Tugas 1");
        tugas1.setUuidStatus(status.getUuid());
        tugasDao.add(tugas1);

        tugas2 = new Tugas();
        tugas2.setNama("Test Tugas 2");
        tugas2.setUuidStatus(status.getUuid());
        tugasDao.add(tugas2);

        tugas3 = new Tugas();
        tugas3.setNama("Test Tugas 3");
        tugas3.setUuidStatus(status.getUuid());
        tugasDao.add(tugas3);

        tugas4 = new Tugas();
        tugas4.setNama("Test Tugas 4");
        tugas4.setUuidStatus(status.getUuid());
        tugasDao.add(tugas4);

        tugas5 = new Tugas();
        tugas5.setNama("Test Tugas 5");
        tugas5.setUuidStatus(status.getUuid());
        tugasDao.add(tugas5);
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
        dao = new JadwalDao(conn);
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
     * Test of add method, of class JadwalDao.
     */
    @Test
    public void testAdd() {
        Jadwal jadwal = new Jadwal(tugas1.getUuid(), null, null);
        UUID uuidTugas = jadwal.getUuidTugas();
        LocalDate tanggalMulai = LocalDate.now();
        LocalDate tanggalSelesai = tanggalMulai.plusDays(1);
        jadwal.setTanggalMulai(tanggalMulai);
        jadwal.setTanggalSelesai(tanggalSelesai);
        dao.add(jadwal);
        Jadwal jadwalFromDb = dao.get(uuidTugas);
        assertEquals(uuidTugas, jadwalFromDb.getUuidTugas());
        assertEquals(tanggalMulai, jadwalFromDb.getTanggalMulai());
        assertEquals(tanggalSelesai, jadwalFromDb.getTanggalSelesai());
    }

    /**
     * Test of all method, of class JadwalDao.
     */
    @Test
    public void testAll() {
        Jadwal jadwal1 = new Jadwal(tugas2.getUuid(), LocalDate.now(), LocalDate.now());
        Jadwal jadwal2 = new Jadwal(tugas3.getUuid(), LocalDate.now(), LocalDate.now());
        dao.add(jadwal1);
        dao.add(jadwal2);
        List<Jadwal> jadwals = dao.all();
        assertTrue(jadwals.contains(jadwal1));
        assertTrue(jadwals.contains(jadwal2));
    }

    /**
     * Test of edit method, of class JadwalDao.
     */
    @Test
    public void testEdit() {
        Jadwal jadwal = new Jadwal(tugas4.getUuid(), LocalDate.now(), LocalDate.now());
        LocalDate tanggalMulai = LocalDate.now();
        LocalDate tanggalSelesai = tanggalMulai.plusDays(1);
        jadwal.setTanggalMulai(tanggalMulai);
        jadwal.setTanggalSelesai(tanggalSelesai);
        dao.add(jadwal);
        tanggalSelesai = tanggalMulai.plusDays(2);
        jadwal.setTanggalSelesai(tanggalSelesai);
        dao.edit(jadwal);
        Jadwal jadwalFromDb = dao.get(jadwal.getUuidTugas());
        assertEquals(tanggalSelesai, jadwalFromDb.getTanggalSelesai());
    }

    /**
     * Test of delete method, of class JadwalDao.
     */
    @Test
    public void testDelete() {
        Jadwal jadwal = new Jadwal(tugas4.getUuid(), LocalDate.now(), LocalDate.now());
        dao.add(jadwal);
        Jadwal jadwalFromDb = dao.get(jadwal.getUuidTugas());
        assertNotNull(jadwalFromDb);
        dao.delete(jadwal.getUuidTugas());
        jadwalFromDb = dao.get(jadwal.getUuidTugas());
        assertNull(jadwalFromDb);
    }
}
