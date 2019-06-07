package Machine;

import Money.Coin;
import Products.Product;

import java.util.ArrayList;

public class VendingMachine {

    private ArrayList<Coin> coins;
    private ArrayList<Drawer> drawers;
    private CoinReturn coinReturn;
    private ArrayList<Coin> coinsPaidIn;

    public VendingMachine() {
        this.coins = new ArrayList<Coin>();
        this.drawers = new ArrayList<Drawer>();
        this.coinReturn = new CoinReturn();
        this.coinsPaidIn = new ArrayList<Coin>();
    }

    public void addDrawer(Drawer drawer) {
        this.drawers.add(drawer);
    }

    public int countDrawers() {
        return this.drawers.size();
    }

    public int countCoins() {
        return this.coins.size();
    }

    public int countCoinsPaidIn() {
        return this.coinsPaidIn.size();
    }

    public int getBalance() {
        int total = 0;
        for (Coin coin : this.coins) {
            total += coin.getCoinValue();
        }
        return total;
    }

    public void addCoin(Coin coin) {
        this.coins.add(coin);
    }

    public int getValueOfCoinsPaidIn() {
        int totalPaidIn = 0;
        for (Coin coin : this.coinsPaidIn) {
            totalPaidIn += coin.getCoinValue();
        }
        return totalPaidIn;
    }


    public boolean checkCoinIsValid(Coin coin) {
        return ((coin.getCoinValue() != 1) && (coin.getCoinValue() != 2));
    }

    public void payInCoin(Coin coin) {
        if (checkCoinIsValid(coin)) {
            this.coinsPaidIn.add(coin);
        }
        this.coinReturn.addCoin(coin);
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
               return (this.getValueOfCoinsPaidIn() >= drawer.getPrice());
            }
        }
        return false;
    }


        public Product buyProduct (DrawerCode drawerCode) {
            Product returnedProduct = null;
        if (this.checkEnoughPaidIn(drawerCode)) {


                for (Drawer drawer : this.drawers) {
                    if (drawer.getDrawerCode() == drawerCode) {
                        returnedProduct = drawer.returnProduct();
                    }

                }

            }
            return returnedProduct;
        }


    public int countCoinsInCoinReturn() {
        return this.coinReturn.countCoins();
    }
}

