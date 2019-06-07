import Machine.Drawer;
import Machine.DrawerCode;
import Products.Product;
import Products.Sweet;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class DrawerTest {

    private Drawer drawer;
    private DrawerCode drawerCode;
    private ArrayList<Product> products;
    private Sweet sweet;

    @Before
    public void setUp() {
        drawer = new Drawer(DrawerCode.A1, 65);
        sweet = new Sweet("Dairy Milk", "Cadbury's");
    }

    @Test
    public void canGetPrice() {
        assertEquals(65, drawer.getPrice());
    }

    @Test
    public void canSetPrice() {
        drawer.setPrice(100);
        assertEquals(100, drawer.getPrice());
    }

    @Test
    public void canAddProduct() {
        drawer.addProduct(sweet);
        assertEquals(1, drawer.countProducts());
    }

    @Test
    public void canReturnProduct() {
        drawer.addProduct(sweet);
        drawer.addProduct(sweet);
        drawer.returnProduct();
        assertEquals(1, drawer.countProducts());
        assertEquals(sweet, drawer.returnProduct());
    }
}
