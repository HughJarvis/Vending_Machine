package Machine;

import Products.Product;
import Products.Sweet;

import java.util.ArrayList;

public class Drawer {

    private DrawerCode drawerCode;
    private int price;
    private ArrayList<Product> products;

    public Drawer(DrawerCode drawerCode, int price) {
        this.drawerCode = drawerCode;
        this.price = price;
        this.products = new ArrayList<Product>();
    }

    public DrawerCode getDrawerCode() {
        return drawerCode;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void addProduct(Sweet sweet) {
        this.products.add(sweet);
    }

    public int countProducts() {
        return this.products.size();
    }

    public Product returnProduct() {
        return this.products.remove(0);
    }
}
