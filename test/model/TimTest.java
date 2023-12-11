package model;

import java.util.Objects;
import java.util.UUID;
import org.junit.Test;
import static org.junit.Assert.*;

public class TimTest {
    
    public TimTest() {
    }

    /**
     * Test of hashCode method, of class Tim.
     */
    @Test
    public void testHashCode() {
        Tim tim = new Tim();
        int expResult = 59 * 5 + Objects.hashCode(tim.getUuid());
        int result = tim.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Tim.
     */
    @Test
    public void testEquals() {
        Tim tim1 = new Tim();
        Tim tim2 = new Tim();
        boolean expResult = false;
        boolean result = tim1.equals(tim2);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Tim.
     */
    @Test
    public void testToString() {
        UUID uuid = UUID.randomUUID();
        Tim tim = new Tim(uuid, "test");
        String expResult = "Tim{uuid=" + uuid.toString() + ", nama=test}";
        String result = tim.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUuid method, of class Tim.
     */
    @Test
    public void testGetUuid() {
        UUID expResult = UUID.randomUUID();
        Tim tim = new Tim(expResult, "test");
        UUID result = tim.getUuid();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNama method, of class Tim.
     */
    @Test
    public void testGetNama() {
        String expResult = "test";
        Tim tim = new Tim(UUID.randomUUID(), expResult);
        String result = tim.getNama();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNama method, of class Tim.
     */
    @Test
    public void testSetNama() {
        String expResult = "test";
        Tim tim = new Tim();
        tim.setNama(expResult);
        String result = tim.getNama();
        assertEquals(expResult, result);
    }
}
