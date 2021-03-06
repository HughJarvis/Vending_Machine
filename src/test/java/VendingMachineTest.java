import Machine.Drawer;
import Machine.DrawerCode;
import Machine.VendingMachine;
import Money.*;
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

        coin1 = new Penny();
        coin2 = new TwoPence();
        coin5 = new FivePence();
        coin10 = new TenPence();
        coin20 = new TwentyPence();
        coin50 = new FiftyPence();
        coin100 = new OnePound();
        coin200 = new TwoPound();

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
        assertEquals(1, vendingMachine.countCoinsInCoinsCredit());
    }

    @Test
    public void canAddCoinToTakings() {
        vendingMachine.addCoinToTakings(coin100);
        assertEquals(1, vendingMachine.countCoinsInTakings());
        assertEquals(100, vendingMachine.countTakings());
    }

    @Test
    public void canAddCoinToCoinReturn() {
        vendingMachine.addCoinToCoinReturn(coin20);
        assertEquals(1, vendingMachine.countCoinsInCoinReturn());
        assertEquals(20, vendingMachine.getCoinReturnValue());
    }

    @Test
    public void wontPayInCoinToMachineIfNotValid() {
        vendingMachine.payInCoin(coin2);
        assertEquals(0, vendingMachine.countCoinsInCoinsCredit());
    }

    @Test
    public void putsInvalidCoinInCoinReturn() {
        vendingMachine.payInCoin(coin2);
        vendingMachine.payInCoin(coin2);
        assertEquals(2, vendingMachine.countCoinsInCoinReturn());

    }

    @Test
    public void canCountMachineTakings() {
        vendingMachine.addCoinToTakings(coin10);
        vendingMachine.addCoinToTakings(coin20);
        assertEquals(2, vendingMachine.countCoinsInTakings());
        assertEquals(30, vendingMachine.countTakings());
    }

    @Test
    public void canCountCoinsInCoinReturn() {
        assertEquals(0, vendingMachine.countCoinsInCoinReturn());
    }


    @Test
    public void canGetCoinCreditValue() {
        vendingMachine.payInCoin(coin10);
        vendingMachine.payInCoin(coin20);
        vendingMachine.payInCoin(coin50);
        assertEquals(80, vendingMachine.getCoinsCreditValue());
    }

    @Test
    public void canGetCoinReturnValue() {
        vendingMachine.payInCoin(coin2);
        vendingMachine.payInCoin(coin2);
        assertEquals(4, vendingMachine.getCoinReturnValue());
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
    public void canCheckEnoughPaidIn() {
        vendingMachine.payInCoin(coin10);
        vendingMachine.payInCoin(coin20);
        vendingMachine.payInCoin(coin50);
        assertEquals(true, vendingMachine.checkEnoughPaidIn(DrawerCode.A1));
        assertEquals(true, vendingMachine.checkEnoughPaidIn(DrawerCode.A2));
        assertEquals(false, vendingMachine.checkEnoughPaidIn(DrawerCode.A3));
    }

    @Test
    public void canMoveTenFromTakingsToCoinReturn() {
        vendingMachine.addCoinToTakings(coin20);
        vendingMachine.addCoinToTakings(coin50);
        vendingMachine.addCoinToTakings(coin10);
        vendingMachine.addCoinToTakings(coin10);
        vendingMachine.moveTenFromTakingsToCoinReturn();
        assertEquals(1, vendingMachine.countCoinsInCoinReturn());
        assertEquals(10, vendingMachine.getCoinReturnValue());
   }

    @Test
    public void canRemoveCoinFromCoinsCredit() {
        vendingMachine.payInCoin(coin10);
        vendingMachine.payInCoin(coin20);
        vendingMachine.payInCoin(coin50);
        vendingMachine.removeCoinFromCoinsCredit(coin10);
        assertEquals(2, vendingMachine.countCoinsInCoinsCredit());
        assertEquals(70, vendingMachine.getCoinsCreditValue());
    }

        @Test
    public void canMoveCoinFromCoinCreditToTakings() {
        vendingMachine.payInCoin(coin200);
        vendingMachine.addCoinToTakings(coin200);
        assertEquals(1, vendingMachine.countCoinsInTakings());
        assertEquals(200, vendingMachine.countTakings());
    }

    @Test
    public void canMoveCoinFromTakingsToCoinReturn() {
        vendingMachine.addCoinToTakings(coin50);
        vendingMachine.addCoinToTakings(coin20);
        vendingMachine.moveCoinFromTakingsToCoinReturn(CoinType.FIFTY);
        assertEquals(20, vendingMachine.countTakings());
        assertEquals(50, vendingMachine.getCoinReturnValue());
    }

    @Test
    public void canRemoveAllCoinsFromCoinsCredit() {
        vendingMachine.payInCoin(coin10);
        vendingMachine.payInCoin(coin20);
        vendingMachine.payInCoin(coin50);
        vendingMachine.removeCoinsCredit();
        assertEquals(0, vendingMachine.countCoinsInCoinsCredit());
    }

    @Test
    public void canClearCoinCredit() {
        vendingMachine.payInCoin(coin10);
        vendingMachine.payInCoin(coin20);
        vendingMachine.payInCoin(coin50);
        vendingMachine.clearCoinsCredit();
        assertEquals(0, vendingMachine.countCoinsInCoinsCredit());
    }

    @Test
    public void canMoveAllCoinsFromCoinCreditToTakings() {
        vendingMachine.payInCoin(coin10);
        vendingMachine.payInCoin(coin20);
        vendingMachine.payInCoin(coin50);
        vendingMachine.moveCoinsCreditToTakings();
        assertEquals(3, vendingMachine.countCoinsInTakings());
        assertEquals(80, vendingMachine.countTakings());
        assertEquals(0, vendingMachine.countCoinsInCoinsCredit());
    }

    @Test
    public void canCalculateChange() {
        vendingMachine.payInCoin(coin10);
        vendingMachine.payInCoin(coin20);
        vendingMachine.payInCoin(coin50);
        assertEquals(15, vendingMachine.calculateChange(DrawerCode.A2));
    }

    @Test
    public void canBuyProductIfEnoughPaidIn() {
        vendingMachine.addCoinToTakings(coin10);
        vendingMachine.addCoinToTakings(coin10);
        vendingMachine.addCoinToTakings(coin10);
        vendingMachine.addCoinToTakings(coin10);
        vendingMachine.addCoinToTakings(coin20);
        vendingMachine.addCoinToTakings(coin20);
        vendingMachine.addCoinToTakings(coin20);
        vendingMachine.addCoinToTakings(coin20);
        vendingMachine.addCoinToTakings(coin50);
        vendingMachine.addCoinToTakings(coin50);
        vendingMachine.addCoinToTakings(coin50);
        vendingMachine.addCoinToTakings(coin50);
        vendingMachine.addCoinToTakings(coin100);
        vendingMachine.addCoinToTakings(coin100);
        vendingMachine.addCoinToTakings(coin100);
        vendingMachine.addCoinToTakings(coin100);
        vendingMachine.addCoinToTakings(coin5);
        vendingMachine.addCoinToTakings(coin5);
        vendingMachine.addCoinToTakings(coin5);
        vendingMachine.addCoinToTakings(coin5);
        vendingMachine.addCoinToTakings(coin5);
        vendingMachine.payInCoin(coin10);
        vendingMachine.payInCoin(coin20);
        vendingMachine.payInCoin(coin50);
        vendingMachine.buyProduct(DrawerCode.A2);
        assertEquals(8, vendingMachine.countProducts());
        assertEquals(825, vendingMachine.countTakings());
        assertEquals(24, vendingMachine.countCoinsInTakings());
        assertEquals(0, vendingMachine.countCoinsInCoinsCredit());
        //assertEquals(15, vendingMachine.getCoinReturnValue());
    }

    @Test
    public void cantBuyProductIfNotEnoughPaidIn() {
        vendingMachine.payInCoin(coin1);
        vendingMachine.payInCoin(coin2);
        vendingMachine.payInCoin(coin5);
        vendingMachine.buyProduct(DrawerCode.A2);
        assertEquals(9, vendingMachine.countProducts());
    }

        @Test
        public void canGetNumberOf100sInChange () {
            assertEquals(1, vendingMachine.getNumberOf100sInChange(120));
        }

        @Test
        public void canGetNumberOf50sInChange () {
            assertEquals(2, vendingMachine.getNumberOf50sInChange(120));
        }

        @Test
        public void canGetNumberOf20sInChange () {
            assertEquals(6, vendingMachine.getNumberOf20sInChange(120));
        }

        @Test
        public void canGetNumberOf10sInChange () {
            assertEquals(12, vendingMachine.getNumberOf10sInChange(120));
        }

        @Test
        public void canGetNumberOf5sInChange () {
            assertEquals(4, vendingMachine.getNumberOf5sInChange(20));
        }

    @Test
    public void canMoveMultipleCoinsFromTakingsToCoinReturn() {
        vendingMachine.addCoinToTakings(coin10);
        vendingMachine.addCoinToTakings(coin10);
        vendingMachine.addCoinToTakings(coin10);
        vendingMachine.addCoinToTakings(coin20);
        vendingMachine.addCoinToTakings(coin20);
        vendingMachine.addCoinToTakings(coin20);
        vendingMachine.addCoinToTakings(coin50);
        vendingMachine.addCoinToTakings(coin50);
        vendingMachine.addCoinToTakings(coin50);
        vendingMachine.addCoinToTakings(coin100);
        vendingMachine.addCoinToTakings(coin100);
        vendingMachine.addCoinToTakings(coin100);
        vendingMachine.addCoinToTakings(coin5);
        vendingMachine.addCoinToTakings(coin5);
        vendingMachine.addCoinToTakings(coin5);
        vendingMachine.moveMultipleCoinsFromTakingsToCoinReturn(CoinType.FIVE, 2);
        vendingMachine.moveMultipleCoinsFromTakingsToCoinReturn(CoinType.TEN, 2);
        vendingMachine.moveMultipleCoinsFromTakingsToCoinReturn(CoinType.TWENTY, 2);
        vendingMachine.moveMultipleCoinsFromTakingsToCoinReturn(CoinType.FIFTY, 2);
        vendingMachine.moveMultipleCoinsFromTakingsToCoinReturn(CoinType.ONEPOUND, 2);
        assertEquals(185, vendingMachine.countTakings());
        assertEquals(370, vendingMachine.getCoinReturnValue());
    }

    @Test
    public void canDisplayCorrectMessage() {
        assertEquals("Use exact change only", vendingMachine.displayMessage());
    }
}


