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

    public void addCoin(Coin coin) {
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



}

