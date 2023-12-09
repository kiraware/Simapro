package simapro;

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
        int expResult = 79 * 5 + Objects.hashCode(timPegawai.getUuid());
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
        UUID uuid = UUID.randomUUID();
        UUID uuidTim = UUID.randomUUID();
        UUID uuidPegawai = UUID.randomUUID();
        TimPegawai timPegawai = new TimPegawai(uuid, uuidTim, uuidPegawai);
        String expResult = "TimPegawai{" + "uuid=" + uuid.toString() + ", uuidTim=" + uuidTim.toString() + ", uuidPegawai=" + uuidPegawai.toString() + '}';
        String result = timPegawai.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUuid method, of class TimPegawai.
     */
    @Test
    public void testGetUuid() {
        UUID expResult = UUID.randomUUID();
        TimPegawai timPegawai = new TimPegawai(expResult, UUID.randomUUID(), UUID.randomUUID());
        UUID result = timPegawai.getUuid();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUuid method, of class TimPegawai.
     */
    @Test
    public void testSetUuid() {
        UUID expResult = UUID.randomUUID();
        TimPegawai timPegawai = new TimPegawai();
        timPegawai.setUuid(expResult);
        UUID result = timPegawai.getUuid();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUuidTim method, of class TimPegawai.
     */
    @Test
    public void testGetUuidTim() {
        UUID expResult = UUID.randomUUID();
        TimPegawai timPegawai = new TimPegawai(UUID.randomUUID(), expResult, UUID.randomUUID());
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
        TimPegawai timPegawai = new TimPegawai(UUID.randomUUID(), UUID.randomUUID(), expResult);
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
}
