package model;

import java.util.Objects;
import java.util.UUID;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatusTest {
    
    public StatusTest() {
    }

    /**
     * Test of hashCode method, of class Status.
     */
    @Test
    public void testHashCode() {
        Status status = new Status();
        int expResult = 59 * 7 + Objects.hashCode(status.getUuid());
        int result = status.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Status.
     */
    @Test
    public void testEquals() {
        Status status1 = new Status();
        Status status2 = new Status();
        boolean expResult = false;
        boolean result = status1.equals(status2);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Status.
     */
    @Test
    public void testToString() {
        UUID uuid = UUID.randomUUID();
        Status status = new Status(uuid, "test");
        String expResult = "Status{uuid=" + uuid.toString() + ", nama=test}";
        String result = status.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUuid method, of class Status.
     */
    @Test
    public void testGetUuid() {
        UUID expResult = UUID.randomUUID();
        Status status = new Status(expResult, "test");
        UUID result = status.getUuid();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNama method, of class Status.
     */
    @Test
    public void testGetNama() {
        String expResult = "test";
        Status status = new Status(UUID.randomUUID(), expResult);
        String result = status.getNama();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNama method, of class Status.
     */
    @Test
    public void testSetNama() {
        String expResult = "test";
        Status status = new Status();
        status.setNama(expResult);
        String result = status.getNama();
        assertEquals(expResult, result);
    }
}
