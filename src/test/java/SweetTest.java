import Products.Sweet;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SweetTest {

    private Sweet sweet;

    @Before
    public void setUp() {
        sweet = new Sweet("Creme Egg", "Cadbury's");
    }

    @Test
    public void canGetName() {
        assertEquals("Creme Egg", sweet.getName());
    }

    @Test
    public void canGetBrand() {
        assertEquals("Cadbury's", sweet.getBrand());
    }
}
