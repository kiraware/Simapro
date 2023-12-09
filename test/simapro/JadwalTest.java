package simapro;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import org.junit.Test;
import static org.junit.Assert.*;

public class JadwalTest {
    
    public JadwalTest() {
    }

    /**
     * Test of hashCode method, of class Jadwal.
     */
    @Test
    public void testHashCode() {
        Jadwal jadwal = new Jadwal();
        int expResult = 19 * 7 + Objects.hashCode(jadwal.getUuid());
        int result = jadwal.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Jadwal.
     */
    @Test
    public void testEquals() {
        Jadwal jadwal1 = new Jadwal();
        Jadwal jadwal2 = new Jadwal();
        boolean expResult = false;
        boolean result = jadwal1.equals(jadwal2);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Jadwal.
     */
    @Test
    public void testToString() {
        UUID uuid = UUID.randomUUID();
        LocalDate tanggalMulai = LocalDate.now();
        LocalDate tanggalSelesai = tanggalMulai.plusDays(1);
        UUID uuidTugas = UUID.randomUUID();
        Jadwal jadwal = new Jadwal(uuid, tanggalMulai, tanggalSelesai, uuidTugas);
        String expResult = "Jadwal{" + "uuid=" + uuid.toString() + ", tanggalMulai=" + tanggalMulai.toString() + ", tanggalSelesai=" + tanggalSelesai.toString() + ", uuidTugas=" + uuidTugas.toString() + '}';
        String result = jadwal.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUuid method, of class Jadwal.
     */
    @Test
    public void testGetUuid() {
        UUID expResult = UUID.randomUUID();
        Jadwal jadwal = new Jadwal(expResult, LocalDate.now(), LocalDate.now(), UUID.randomUUID());
        UUID result = jadwal.getUuid();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUuid method, of class Jadwal.
     */
    @Test
    public void testSetUuid() {
        UUID expResult = UUID.randomUUID();
        Jadwal jadwal = new Jadwal();
        jadwal.setUuid(expResult);
        UUID result = jadwal.getUuid();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTanggalMulai method, of class Jadwal.
     */
    @Test
    public void testGetTanggalMulai() {
        LocalDate expResult = LocalDate.now();
        Jadwal jadwal = new Jadwal(UUID.randomUUID(), expResult, LocalDate.now(), UUID.randomUUID());
        LocalDate result = jadwal.getTanggalMulai();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTanggalMulai method, of class Jadwal.
     */
    @Test
    public void testSetTanggalMulai() {
        LocalDate expResult = LocalDate.now();
        Jadwal jadwal = new Jadwal();
        jadwal.setTanggalMulai(expResult);
        LocalDate result = jadwal.getTanggalMulai();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTanggalSelesai method, of class Jadwal.
     */
    @Test
    public void testGetTanggalSelesai() {
        LocalDate expResult = LocalDate.now();
        Jadwal jadwal = new Jadwal(UUID.randomUUID(), LocalDate.now(), expResult, UUID.randomUUID());
        LocalDate result = jadwal.getTanggalSelesai();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTanggalSelesai method, of class Jadwal.
     */
    @Test
    public void testSetTanggalSelesai() {
        LocalDate expResult = LocalDate.now();
        Jadwal jadwal = new Jadwal();
        jadwal.setTanggalSelesai(expResult);
        LocalDate result = jadwal.getTanggalSelesai();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUuidTugas method, of class Jadwal.
     */
    @Test
    public void testGetUuidTugas() {
        UUID expResult = UUID.randomUUID();
        Jadwal jadwal = new Jadwal(UUID.randomUUID(), LocalDate.now(), LocalDate.now(), expResult);
        UUID result = jadwal.getUuidTugas();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUuidTugas method, of class Jadwal.
     */
    @Test
    public void testSetUuidTugas() {
        UUID expResult = UUID.randomUUID();
        Jadwal jadwal = new Jadwal();
        jadwal.setUuidTugas(expResult);
        UUID result = jadwal.getUuidTugas();
        assertEquals(expResult, result);
    }
}
