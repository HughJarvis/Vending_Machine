import Products.Crisps;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CrispsTest {

    private Crisps crisps;

    @Before
    public void setUp() {
        crisps = new Crisps ("Quavers", "Walkers");
    }

    @Test
    public void canGetName() {
        assertEquals("Quavers", crisps.getName());
    }

    @Test
    public void canGetBrand() {
        assertEquals("Walkers", crisps.getBrand());
    }
}

