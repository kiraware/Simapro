package model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProyekTest {
    
    public ProyekTest() {
    }

    /**
     * Test of hashCode method, of class Proyek.
     */
    @Test
    public void testHashCode() {
        Proyek proyek = new Proyek();
        int expResult = 43 * 5 + Objects.hashCode(proyek.getUuid());
        int result = proyek.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Proyek.
     */
    @Test
    public void testEquals() {
        Proyek proyek1 = new Proyek();
        Proyek proyek2 = new Proyek();
        boolean expResult = false;
        boolean result = proyek1.equals(proyek2);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Proyek.
     */
    @Test
    public void testToString() {
        UUID uuid = UUID.randomUUID();
        String nama = "test";
        String deskripsi = "desc";
        LocalDate tanggalMulai = LocalDate.now();
        LocalDate tanggalSelesai = tanggalMulai.plusDays(1);
        double anggaran = 100;
        UUID uuidTim = UUID.randomUUID();
        Proyek proyek = new Proyek(uuid, nama, deskripsi, tanggalMulai, tanggalSelesai, anggaran, uuidTim);
        String expResult = "Proyek{" + "uuid=" + uuid + ", nama=" + nama + ", deskripsi=" + deskripsi + ", tanggalMulai=" + tanggalMulai + ", tanggalSelesai=" + tanggalSelesai + ", anggaran=" + anggaran + ", uuidTim=" + uuidTim + '}';
        String result = proyek.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUuid method, of class Proyek.
     */
    @Test
    public void testGetUuid() {
        UUID expResult = UUID.randomUUID();
        Proyek proyek = new Proyek(expResult, "test", "desc", LocalDate.now(), LocalDate.now(), 100, UUID.randomUUID());
        UUID result = proyek.getUuid();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNama method, of class Proyek.
     */
    @Test
    public void testGetNama() {
        String expResult = "test";
        Proyek proyek = new Proyek(UUID.randomUUID(), expResult, "desc", LocalDate.now(), LocalDate.now(), 100, UUID.randomUUID());
        String result = proyek.getNama();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNama method, of class Proyek.
     */
    @Test
    public void testSetNama() {
        String expResult = "test";
        Proyek proyek = new Proyek();
        proyek.setNama(expResult);
        String result = proyek.getNama();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDeskripsi method, of class Proyek.
     */
    @Test
    public void testGetDeskripsi() {
        String expResult = "desc";
        Proyek proyek = new Proyek(UUID.randomUUID(), "test", expResult, LocalDate.now(), LocalDate.now(), 100, UUID.randomUUID());
        String result = proyek.getDeskripsi();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDeskripsi method, of class Proyek.
     */
    @Test
    public void testSetDeskripsi() {
        String expResult = "desc";
        Proyek proyek = new Proyek();
        proyek.setDeskripsi(expResult);
        String result = proyek.getDeskripsi();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTanggalMulai method, of class Proyek.
     */
    @Test
    public void testGetTanggalMulai() {
        LocalDate expResult = LocalDate.now();
        Proyek proyek = new Proyek(UUID.randomUUID(), "test", "desc", expResult, LocalDate.now(), 100, UUID.randomUUID());
        LocalDate result = proyek.getTanggalMulai();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTanggalMulai method, of class Proyek.
     */
    @Test
    public void testSetTanggalMulai() {
        LocalDate expResult = LocalDate.now();
        Proyek proyek = new Proyek();
        proyek.setTanggalMulai(expResult);
        LocalDate result = proyek.getTanggalMulai();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTanggalSelesai method, of class Proyek.
     */
    @Test
    public void testGetTanggalSelesai() {
        LocalDate expResult = LocalDate.now();
        Proyek proyek = new Proyek(UUID.randomUUID(), "test", "desc", LocalDate.now(), expResult, 100, UUID.randomUUID());
        LocalDate result = proyek.getTanggalSelesai();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTanggalSelesai method, of class Proyek.
     */
    @Test
    public void testSetTanggalSelesai() {
        LocalDate expResult = LocalDate.now();
        Proyek proyek = new Proyek();
        proyek.setTanggalSelesai(expResult);
        LocalDate result = proyek.getTanggalSelesai();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAnggaran method, of class Proyek.
     */
    @Test
    public void testGetAnggaran() {
        double expResult = 100;
        Proyek proyek = new Proyek(UUID.randomUUID(), "test", "desc", LocalDate.now(), LocalDate.now(), expResult, UUID.randomUUID());
        double result = proyek.getAnggaran();
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of setAnggaran method, of class Proyek.
     */
    @Test
    public void testSetAnggaran() {
        double expResult = 100;
        Proyek proyek = new Proyek();
        proyek.setAnggaran(expResult);
        double result = proyek.getAnggaran();
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of getUuidTim method, of class Proyek.
     */
    @Test
    public void testGetUuidTim() {
        UUID expResult = UUID.randomUUID();
        Proyek proyek = new Proyek(UUID.randomUUID(), "test", "desc", LocalDate.now(), LocalDate.now(), 100, expResult);
        UUID result = proyek.getUuidTim();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUuidTim method, of class Proyek.
     */
    @Test
    public void testSetUuidTim() {
        UUID expResult = UUID.randomUUID();
        Proyek proyek = new Proyek();
        proyek.setUuidTim(expResult);
        UUID result = proyek.getUuidTim();
        assertEquals(expResult, result);
    }
}
