package dao;

import db.DBHelper;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Proyek;
import model.Tim;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProyekDaoTest {
    private static ProyekDao dao;
    private static Tim tim1, tim2, tim3, tim4, tim5;
    private static final Logger logger = Logger.getLogger(ProyekDaoTest.class.getName());
    
    @BeforeClass
    public static void setUpClass() {
        Connection conn = DBHelper.getConnection();
        try {
            // set auto commit false so any operation in this test will be discarded.
            conn.setAutoCommit(false);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        TimDao timDao = new TimDao(conn);

        tim1 = new Tim();
        tim1.setNama("Test Tim 1");
        timDao.add(tim1);

        tim2 = new Tim();
        tim2.setNama("Test Tim 2");
        timDao.add(tim2);

        tim3 = new Tim();
        tim3.setNama("Test Tim 3");
        timDao.add(tim3);

        tim4 = new Tim();
        tim4.setNama("Test Tim 4");
        timDao.add(tim4);

        tim5 = new Tim();
        tim5.setNama("Test Tim 5");
        timDao.add(tim5);
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
        dao = new ProyekDao(conn);
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
     * Test of add method, of class ProyekDao.
     */
    @Test
    public void testAdd() {
        Proyek proyek = new Proyek(tim1.getUuid(), "", "", null, null, Double.valueOf(0));
        LocalDate tanggalMulai = LocalDate.now();
        LocalDate tanggalSelesai = tanggalMulai.plusDays(1);
        proyek.setNama("test");
        proyek.setDeskripsi("desc");
        proyek.setTanggalMulai(tanggalMulai);
        proyek.setTanggalSelesai(tanggalSelesai);
        proyek.setAnggaran(Double.valueOf(100));
        dao.add(proyek);
        Proyek proyekFromDb = dao.get(proyek.getUuidTim());
        assertEquals("test", proyekFromDb.getNama());
        assertEquals("desc", proyekFromDb.getDeskripsi());
        assertEquals(tanggalMulai, proyekFromDb.getTanggalMulai());
        assertEquals(tanggalSelesai, proyekFromDb.getTanggalSelesai());
    }

    /**
     * Test of all method, of class ProyekDao.
     */
    @Test
    public void testAll() {
        Proyek proyek1 = new Proyek(tim2.getUuid(), "", "", LocalDate.now(), LocalDate.now(), Double.valueOf(0));
        Proyek proyek2 = new Proyek(tim3.getUuid(), "", "", LocalDate.now(), LocalDate.now(), Double.valueOf(0));
        dao.add(proyek1);
        dao.add(proyek2);
        List<Proyek> proyeks = dao.all();
        assertTrue(proyeks.contains(proyek1));
        assertTrue(proyeks.contains(proyek2));
    }

    /**
     * Test of edit method, of class ProyekDao.
     */
    @Test
    public void testEdit() {
        Proyek proyek = new Proyek(tim4.getUuid(), "", "", null, null, Double.valueOf(0));
        LocalDate tanggalMulai = LocalDate.now();
        LocalDate tanggalSelesai = tanggalMulai.plusDays(1);
        proyek.setNama("test");
        proyek.setDeskripsi("desc");
        proyek.setTanggalMulai(tanggalMulai);
        proyek.setTanggalSelesai(tanggalSelesai);
        proyek.setAnggaran(Double.valueOf(100));
        proyek.setNama("test");
        dao.add(proyek);
        proyek.setNama("tset");
        dao.edit(proyek);
        Proyek proyekFromDb = dao.get(proyek.getUuidTim());
        assertEquals("tset", proyekFromDb.getNama());
    }

    /**
     * Test of delete method, of class ProyekDao.
     */
    @Test
    public void testDelete() {
        Proyek proyek = new Proyek(tim4.getUuid(), "", "", LocalDate.now(), LocalDate.now(), Double.valueOf(0));
        dao.add(proyek);
        Proyek proyekFromDb = dao.get(proyek.getUuidTim());
        assertNotNull(proyekFromDb);
        dao.delete(proyek.getUuidTim());
        proyekFromDb = dao.get(proyek.getUuidTim());
        assertNull(proyekFromDb);
    }
}
