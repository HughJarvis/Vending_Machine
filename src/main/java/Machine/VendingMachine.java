package Machine;

import Money.Coin;
import Products.Product;

import java.util.ArrayList;

public class VendingMachine {

    private ArrayList<Coin> takings;
    private ArrayList<Drawer> drawers;
    private CoinReturn coinReturn;
    private ArrayList<Coin> coinsCredit;

    public VendingMachine() {
        this.takings = new ArrayList<Coin>();
        this.drawers = new ArrayList<Drawer>();
        this.coinReturn = new CoinReturn();
        this.coinsCredit = new ArrayList<Coin>();
    }

    public void addDrawer(Drawer drawer) {
        this.drawers.add(drawer);
    }

    public int countDrawers() {
        return this.drawers.size();
    }

    public int countCoinsInTakings() {
        return this.takings.size();
    }

    public int countCoinsInCoinsCredit() {
        return this.coinsCredit.size();
    }

    public int countCoinsInCoinReturn() {
        return this.coinReturn.countCoins();
    }

    public int getBalance(ArrayList<Coin> coins) {
        int total = 0;
        for (Coin coin : coins) {
            total += coin.getCoinValue();
        }
        return total;
    }

    public void addCoinToTakings(Coin coin) {
        this.takings.add(coin);
    }

    public int getCoinsCreditValue() {
        return getBalance(this.coinsCredit);
    }

    public int getTakingsValue() {
        return getBalance(this.takings);
    }

    public int getCoinReturnValue() {
        return getBalance(this.coinReturn.getCoins());
    }


    public boolean checkCoinIsValid(Coin coin) {
        return ((coin.getCoinValue() != 1) && (coin.getCoinValue() != 2));
    }

    public void payInCoin(Coin coin) {
        if (checkCoinIsValid(coin)) {
            this.coinsCredit.add(coin);
        }
        this.coinReturn.addCoin(coin);
    }

    public Coin removeCoinFromCoinsCredit(Coin coin){
        int index = this.coinsCredit.indexOf(coin);
        return this.coinsCredit.remove(index);
    }

    public int countProducts() {
        int totalProducts = 0;
        for (Drawer drawer : this.drawers) {
            totalProducts += drawer.countProducts();
        }
        return totalProducts;
    }

    public boolean checkEnoughPaidIn(DrawerCode drawerCode) {
        for (Drawer drawer : this.drawers) {
            if (drawer.getDrawerCode() == drawerCode) {
               return (this.getCoinsCreditValue() >= drawer.getPrice());
            }
        }
        return false;
    }

    public Product buyProduct (DrawerCode drawerCode) {
// tried putting this.moveCoinsCreditToTakings(); in here but test failed on number of products check???????
        Product returnedProduct = null;
        if (this.checkEnoughPaidIn(drawerCode)) {
            for (Drawer drawer : this.drawers) {
                if (drawer.getDrawerCode() == drawerCode) {
                        returnedProduct = drawer.returnProduct();
                }
            }
        }
        this.moveCoinsCreditToTakings();  //works down here
            return returnedProduct;
    }


    public void addCoinToCoinReturn(Coin coin) {
        this.coinReturn.addCoin(coin);
    }

    public void moveCoinToTakings(Coin coin) {
        Coin movedCoin = this.removeCoinFromCoinsCredit(coin);
        this.addCoinToTakings(movedCoin);
    }

    public ArrayList<Coin> removeCoinsCredit() {
        ArrayList<Coin> removedCoins = ((ArrayList<Coin>) this.coinsCredit.clone());
        this.coinsCredit.clear();
        return removedCoins;
    }

    public void moveCoinsCreditToTakings() {
        this.takings.addAll(this.removeCoinsCredit());
    }

    public void moveCoinToCoinReturn(Coin coin) {
        Coin movedCoin = this.removeCoinFromTakings(coin);
        this.addCoinToCoinReturn(movedCoin);
    }

    private Coin removeCoinFromTakings(Coin coin) {
        int index = this.takings.indexOf(coin);
        return this.takings.remove(index);
    }

    public int calculateChange(DrawerCode drawerCode) {
        int change = 0;
        for (Drawer drawer : this.drawers){
            if (drawer.getDrawerCode() == drawerCode)
                change = getCoinsCreditValue() - drawer.getPrice();
        }
        return change;
    }


    public int getNumberOf100sInChange(int change) {
        return change / 100;
    }

    public int getNumberOf50sInChange(int change) {
        return change / 50;
    }

    public int getNumberOf20sInChange(int change) {
        return change / 20;
    }

    public int getNumberOf10sInChange(int change) {
        return change / 10;
    }

    public int getNumberOf5sInChange(int change) {
        return change / 5;
    }
}

