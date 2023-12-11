package model;

import java.util.Objects;
import java.util.UUID;
import org.junit.Test;
import static org.junit.Assert.*;

public class TugasTest {
    
    public TugasTest() {
    }

    /**
     * Test of hashCode method, of class Tugas.
     */
    @Test
    public void testHashCode() {
        Tugas tugas = new Tugas();
        int expResult = 23 * 5 + Objects.hashCode(tugas.getUuid());
        int result = tugas.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Tugas.
     */
    @Test
    public void testEquals() {
        Tugas tugas1 = new Tugas();
        Tugas tugas2 = new Tugas();
        boolean expResult = false;
        boolean result = tugas1.equals(tugas2);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Tugas.
     */
    @Test
    public void testToString() {
        UUID uuid = UUID.randomUUID();
        String nama = "test";
        String deskripsi = "desc";
        UUID uuidStatus = UUID.randomUUID();
        Tugas tugas = new Tugas(uuid, nama, deskripsi, uuidStatus);
        String expResult = "Tugas{" + "uuid=" + uuid.toString() + ", nama=test, deskripsi=desc, uuidStatus=" + uuidStatus.toString() + '}';
        String result = tugas.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUuid method, of class Tugas.
     */
    @Test
    public void testGetUuid() {
        UUID expResult = UUID.randomUUID();
        Tugas tugas = new Tugas(expResult, "test", "desc", UUID.randomUUID());
        UUID result = tugas.getUuid();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNama method, of class Tugas.
     */
    @Test
    public void testGetNama() {
        String expResult = "test";
        Tugas tugas = new Tugas(UUID.randomUUID(), expResult, "desc", UUID.randomUUID());
        String result = tugas.getNama();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNama method, of class Tugas.
     */
    @Test
    public void testSetNama() {
        String expResult = "test";
        Tugas tugas = new Tugas();
        tugas.setNama(expResult);
        String result = tugas.getNama();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDeskripsi method, of class Tugas.
     */
    @Test
    public void testGetDeskripsi() {
        String expResult = "desc";
        Tugas tugas = new Tugas(UUID.randomUUID(), "test", expResult, UUID.randomUUID());
        String result = tugas.getDeskripsi();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDeskripsi method, of class Tugas.
     */
    @Test
    public void testSetDeskripsi() {
        String expResult = "desc";
        Tugas tugas = new Tugas();
        tugas.setDeskripsi(expResult);
        String result = tugas.getDeskripsi();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUuidStatus method, of class Tugas.
     */
    @Test
    public void testGetUuidStatus() {
        UUID expResult = UUID.randomUUID();
        Tugas tugas = new Tugas(UUID.randomUUID(), "test", "desc", expResult);
        UUID result = tugas.getUuidStatus();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUuidStatus method, of class Tugas.
     */
    @Test
    public void testSetUuidStatus() {
        UUID expResult = UUID.randomUUID();
        Tugas tugas = new Tugas();
        tugas.setUuidStatus(expResult);
        UUID result = tugas.getUuidStatus();
        assertEquals(expResult, result);
    }
}
