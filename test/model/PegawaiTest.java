package model;

import java.util.Objects;
import java.util.UUID;
import org.junit.Test;
import static org.junit.Assert.*;

public class PegawaiTest {
    
    public PegawaiTest() {
    }

    /**
     * Test of hashCode method, of class Pegawai.
     */
    @Test
    public void testHashCode() {
        Pegawai pegawai = new Pegawai();
        int expResult = 41 * 3 + Objects.hashCode(pegawai.getUuid());
        int result = pegawai.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Pegawai.
     */
    @Test
    public void testEquals() {
        Pegawai pegawai1 = new Pegawai();
        Pegawai pegawai2 = new Pegawai();
        boolean expResult = false;
        boolean result = pegawai1.equals(pegawai2);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Pegawai.
     */
    @Test
    public void testToString() {
        UUID uuid = UUID.randomUUID();
        UUID uuidJabatan = UUID.randomUUID();
        Pegawai pegawai = new Pegawai(uuid, "test", uuidJabatan);
        String expResult = "Pegawai{uuid=" + uuid.toString() + ", nama=test, uuidJabatan=" + uuidJabatan.toString() + '}';
        String result = pegawai.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUuid method, of class Pegawai.
     */
    @Test
    public void testGetUuid() {
        UUID expResult = UUID.randomUUID();
        Pegawai pegawai = new Pegawai(expResult, "test", UUID.randomUUID());
        UUID result = pegawai.getUuid();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNama method, of class Pegawai.
     */
    @Test
    public void testGetNama() {
        String expResult = "test";
        Pegawai pegawai = new Pegawai(UUID.randomUUID(), expResult, UUID.randomUUID());
        String result = pegawai.getNama();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNama method, of class Pegawai.
     */
    @Test
    public void testSetNama() {
        String expResult = "test";
        Pegawai pegawai = new Pegawai();
        pegawai.setNama(expResult);
        String result = pegawai.getNama();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUuid method, of class Pegawai.
     */
    @Test
    public void testGetUuidJabatan() {
        UUID expResult = UUID.randomUUID();
        Pegawai pegawai = new Pegawai(UUID.randomUUID(), "test", expResult);
        UUID result = pegawai.getUuidJabatan();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUuid method, of class Pegawai.
     */
    @Test
    public void testSetUuidJabatan() {
        UUID expResult = UUID.randomUUID();
        Pegawai pegawai = new Pegawai();
        pegawai.setUuidJabatan(expResult);
        UUID result = pegawai.getUuidJabatan();
        assertEquals(expResult, result);
    }
}
