package model;

import java.util.Objects;
import java.util.UUID;
import org.junit.Test;
import static org.junit.Assert.*;

public class JabatanTest {
    
    public JabatanTest() {
    }

    /**
     * Test of hashCode method, of class Jabatan.
     */
    @Test
    public void testHashCode() {
        Jabatan jabatan = new Jabatan();
        int expResult = 43 * 3 + Objects.hashCode(jabatan.getUuid());
        int result = jabatan.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Jabatan.
     */
    @Test
    public void testEquals() {
        Jabatan jabatan1 = new Jabatan();
        Jabatan jabatan2 = new Jabatan();
        boolean expResult = false;
        boolean result = jabatan1.equals(jabatan2);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Jabatan.
     */
    @Test
    public void testToString() {
        UUID uuid = UUID.randomUUID();
        Jabatan jabatan = new Jabatan(uuid, "test");
        String expResult = "Jabatan{uuid=" + uuid.toString() + ", nama=test}";
        String result = jabatan.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUuid method, of class Jabatan.
     */
    @Test
    public void testGetUuid() {
        UUID expResult = UUID.randomUUID();
        Jabatan jabatan = new Jabatan(expResult, "test");
        UUID result = jabatan.getUuid();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNama method, of class Jabatan.
     */
    @Test
    public void testGetNama() {
        String expResult = "test";
        Jabatan jabatan = new Jabatan(UUID.randomUUID(), expResult);
        String result = jabatan.getNama();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNama method, of class Jabatan.
     */
    @Test
    public void testSetNama() {
        String expResult = "test";
        Jabatan jabatan = new Jabatan();
        jabatan.setNama(expResult);
        String result = jabatan.getNama();
        assertEquals(expResult, result);
    }
}
