import Machine.CoinReturn;
import Machine.Drawer;
import Machine.DrawerCode;
import Machine.VendingMachine;
import Money.Coin;
import Money.CoinType;
import Products.Crisps;
import Products.Drink;
import Products.Sweet;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VendingMachineTest {

    private Drawer drawerA1;
    private Drawer drawerA2;
    private Drawer drawerA3;
    private Drawer drawerA4;
    private Sweet sweet;
    private Drink drink;
    private Crisps crisps;
    private CoinReturn coinReturn;
    private VendingMachine vendingMachine;
    private Coin coin1;
    private Coin coin2;
    private Coin coin5;
    private Coin coin10;
    private Coin coin20;
    private Coin coin50;
    private Coin coin100;
    private Coin coin200;

    @Before
    public void setUp() {
        drawerA1 = new Drawer(DrawerCode.A1, 50);
        drawerA2 = new Drawer(DrawerCode.A2, 65);
        drawerA3 = new Drawer(DrawerCode.A3, 100);
        drawerA4 = new Drawer(DrawerCode.A4, 100);

        drawerA1.addProduct(crisps);
        drawerA1.addProduct(crisps);
        drawerA1.addProduct(crisps);
        drawerA2.addProduct(sweet);
        drawerA2.addProduct(sweet);
        drawerA2.addProduct(sweet);
        drawerA3.addProduct(drink);
        drawerA3.addProduct(drink);
        drawerA3.addProduct(drink);

        vendingMachine = new VendingMachine();
        vendingMachine.addDrawer(drawerA1);
        vendingMachine.addDrawer(drawerA2);
        vendingMachine.addDrawer(drawerA3);

        coin1 = new Coin(CoinType.ONE);
        coin2 = new Coin(CoinType.TWO);
        coin5 = new Coin(CoinType.FIVE);
        coin10 = new Coin(CoinType.TEN);
        coin20 = new Coin(CoinType.TWENTY);
        coin50 = new Coin(CoinType.FIFTY);
        coin100 = new Coin(CoinType.ONEPOUND);
        coin200 = new Coin(CoinType.TWOPOUND);

    }

    @Test
    public void canCountDrawers() {
        assertEquals(3, vendingMachine.countDrawers());
    }

    @Test
    public void canAddDrawer() {
        vendingMachine.addDrawer(drawerA4);
        assertEquals(4, vendingMachine.countDrawers());
    }

    @Test
    public void canPayInCoinToMachineifCoinValid() {
        vendingMachine.payInCoin(coin50);
        assertEquals(1, vendingMachine.countCoinsPaidIn());
    }

    @Test
    public void wontPayInCoinToMachineIfNotValid() {
        vendingMachine.payInCoin(coin2);
        assertEquals(0, vendingMachine.countCoinsPaidIn());
    }

    @Test
    public void putsInvalidCoinInCoinReturn() {
        vendingMachine.payInCoin(coin2);
        vendingMachine.payInCoin(coin2);
        assertEquals(2, vendingMachine.countCoinsInCoinReturn());

    }

    @Test
    public void canCountCoinsInMachine() {
        vendingMachine.addCoin(coin10);
        vendingMachine.addCoin(coin20);
        assertEquals(2, vendingMachine.countCoins());
    }

    @Test
    public void canCountCoinsInCoinReturn() {
        assertEquals(0, vendingMachine.countCoinsInCoinReturn());
    }

    @Test
    public void canGetBalance() {
        vendingMachine.addCoin(coin10);
        vendingMachine.addCoin(coin20);
        vendingMachine.addCoin(coin50);
        assertEquals(80, vendingMachine.getBalance());
    }

    @Test
    public void canCheckCoinIsValid() {
        assertEquals(false, vendingMachine.checkCoinIsValid(coin1));
    }


    @Test
    public void canCountProductsInMachine() {
        assertEquals(9, vendingMachine.countProducts());
    }

    @Test
    public void canCalculateChange() {
    }

    @Test
    public void canBuyProductIfEnoughPaidIn() {
        vendingMachine.payInCoin(coin10);
        vendingMachine.payInCoin(coin20);
        vendingMachine.payInCoin(coin50);
        vendingMachine.buyProduct(DrawerCode.A2);
        assertEquals(8, vendingMachine.countProducts());
    }

    @Test
    public void cantBuyProductIfNotEnoughPaidIn() {
        vendingMachine.payInCoin(coin1);
        vendingMachine.payInCoin(coin2);
        vendingMachine.payInCoin(coin5);
        vendingMachine.buyProduct(DrawerCode.A2);
        assertEquals(9, vendingMachine.countProducts());
    }
}

