package model;

import java.util.Objects;
import java.util.UUID;
import org.junit.Test;
import static org.junit.Assert.*;

public class TimPegawaiTest {
    
    public TimPegawaiTest() {
    }

    /**
     * Test of hashCode method, of class TimPegawai.
     */
    @Test
    public void testHashCode() {
        TimPegawai timPegawai = new TimPegawai();
        int expResult = 79 * 5 + Objects.hashCode(timPegawai.getUuidTugas());
        int result = timPegawai.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class TimPegawai.
     */
    @Test
    public void testEquals() {
        TimPegawai timPegawai1 = new TimPegawai();
        TimPegawai timPegawai2 = new TimPegawai();
        boolean expResult = false;
        boolean result = timPegawai1.equals(timPegawai2);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class TimPegawai.
     */
    @Test
    public void testToString() {
        UUID uuidTugas = UUID.randomUUID();
        UUID uuidTim = UUID.randomUUID();
        UUID uuidPegawai = UUID.randomUUID();
        UUID uuidJabatan = UUID.randomUUID();
        TimPegawai timPegawai = new TimPegawai(uuidTugas, uuidTim, uuidPegawai, uuidJabatan);
        String expResult = "TimPegawai{" + "uuidTugas=" + uuidTugas.toString() + ", uuidTim=" + uuidTim.toString() + ", uuidPegawai=" + uuidPegawai.toString() + ", uuidJabatan=" + uuidJabatan.toString() + '}';
        String result = timPegawai.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUuidTugas method, of class TimPegawai.
     */
    @Test
    public void testGetUuidTugas() {
        UUID expResult = UUID.randomUUID();
        TimPegawai timPegawai = new TimPegawai(expResult, UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        UUID result = timPegawai.getUuidTugas();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUuidTim method, of class TimPegawai.
     */
    @Test
    public void testGetUuidTim() {
        UUID expResult = UUID.randomUUID();
        TimPegawai timPegawai = new TimPegawai(UUID.randomUUID(), expResult, UUID.randomUUID(), UUID.randomUUID());
        UUID result = timPegawai.getUuidTim();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUuidTim method, of class TimPegawai.
     */
    @Test
    public void testSetUuidTim() {
        UUID expResult = UUID.randomUUID();
        TimPegawai timPegawai = new TimPegawai();
        timPegawai.setUuidTim(expResult);
        UUID result = timPegawai.getUuidTim();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUuidPegawai method, of class TimPegawai.
     */
    @Test
    public void testGetUuidPegawai() {
        UUID expResult = UUID.randomUUID();
        TimPegawai timPegawai = new TimPegawai(UUID.randomUUID(), UUID.randomUUID(), expResult, UUID.randomUUID());
        UUID result = timPegawai.getUuidPegawai();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUuidPegawai method, of class TimPegawai.
     */
    @Test
    public void testSetUuidPegawai() {
        UUID expResult = UUID.randomUUID();
        TimPegawai timPegawai = new TimPegawai();
        timPegawai.setUuidPegawai(expResult);
        UUID result = timPegawai.getUuidPegawai();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUuid method, of class TimPegawai.
     */
    @Test
    public void testGetUuidJabatan() {
        UUID expResult = UUID.randomUUID();
        TimPegawai timPegawai = new TimPegawai(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), expResult);
        UUID result = timPegawai.getUuidJabatan();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUuid method, of class TimPegawai.
     */
    @Test
    public void testSetUuidJabatan() {
        UUID expResult = UUID.randomUUID();
        TimPegawai timPegawai = new TimPegawai();
        timPegawai.setUuidJabatan(expResult);
        UUID result = timPegawai.getUuidJabatan();
        assertEquals(expResult, result);
    }
}
