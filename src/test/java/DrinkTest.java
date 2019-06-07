import Products.Crisps;
import Products.Drink;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DrinkTest {

    private Drink drink;

    @Before
    public void setUp() {
        drink = new Drink ("Irn Bru", "Barr's");
    }

    @Test
    public void canGetName() {
        assertEquals("Irn Bru", drink.getName());
    }

    @Test
    public void canGetBrand() {
        assertEquals("Barr's", drink.getBrand());
    }
}
