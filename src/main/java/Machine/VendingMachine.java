package Machine;

import Money.*;
import Products.Product;

import java.util.ArrayList;

public class VendingMachine {

    private ArrayList<FivePence> fives;
    private ArrayList<TenPence> tens;
    private ArrayList<TwentyPence> twenties;
    private ArrayList<FiftyPence> fifties;
    private ArrayList<OnePound> pounds;
    private ArrayList<TwoPound> twoPounds;
    private ArrayList<Drawer> drawers;
    private CoinReturn coinReturn;
    private ArrayList<Coin> coinsCredit;

    public VendingMachine() {
        this.fives = new ArrayList<FivePence>();
        this.tens = new ArrayList<TenPence>();
        this.twenties = new ArrayList<TwentyPence>();
        this.fifties = new ArrayList<FiftyPence>();
        this.pounds = new ArrayList<OnePound>();
        this.twoPounds = new ArrayList<TwoPound>();
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

    public ArrayList<Coin> getCoinsCredit() {
        return coinsCredit;
    }

    public ArrayList<FivePence> getFives() {
        return fives;
    }

    public ArrayList<TenPence> getTens() {
        return tens;
    }

    public ArrayList<TwentyPence> getTwenties() {
        return twenties;
    }

    public ArrayList<FiftyPence> getFifties() {
        return fifties;
    }

    public ArrayList<OnePound> getPounds() {
        return pounds;
    }

    public ArrayList<TwoPound> getTwoPounds() {
        return twoPounds;
    }

    public ArrayList<Coin> getCoinReturn() {
        return this.coinReturn.getCoins();
    }

    public int countCoinsInCoinsCredit() {
        return this.coinsCredit.size();
    }

    public int countCoins(ArrayList<Coin> coins){
        return coins.size();
    }

    public int countCoinsInCoinReturn() {
        return this.getCoinReturn().size();
    }

    public int getBalance(ArrayList<Coin> coins) {
        int total = 0;
        for (Coin coin : coins) {
            total += coin.getCoinValue();
        }
        return total;
    }

    public void addToFives(FivePence fivePence) {
        this.fives.add(fivePence);
    }

    public void addToTens(TenPence tenPence) {
        this.tens.add(tenPence);
    }

    public void addToTwenties(TwentyPence twentyPence) {
        this.twenties.add(twentyPence);
    }

    public int getCoinsCreditValue() {
        return getBalance(this.getCoinsCredit());
    }


    public int getCoinReturnValue() {
        return getBalance(this.getCoinReturn());
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
            this.moveCoinsCreditToTakings();
            for (Drawer drawer : this.drawers) {
                if (drawer.getDrawerCode() == drawerCode) {
                        returnedProduct = drawer.returnProduct();
                }
            }
        }
         //works down here
            return returnedProduct;
    }


    public void addCoinToCoinReturn(Coin coin) {
        this.coinReturn.addCoin(coin);
    }

    public void addCoinToTakings(Coin coin) {
        if (coin.getCoinType() == CoinType.FIVE) {
            this.fives.add((FivePence) coin);
        } else if (coin.getCoinType() == CoinType.TEN) {
            this.tens.add((TenPence) coin);
        } else if (coin.getCoinType() == CoinType.TWENTY) {
            this.twenties.add((TwentyPence) coin);
        } else if (coin.getCoinType() == CoinType.FIFTY) {
            this.fifties.add((FiftyPence) coin);
        } else if (coin.getCoinType() == CoinType.ONEPOUND) {
            this.pounds.add((OnePound) coin);
        } else if (coin.getCoinType() == CoinType.TWOPOUND) {
            this.twoPounds.add((TwoPound) coin);
        }
    }


    public Coin removeCoinFromTakings(Coin coin) {
        if (coin.getCoinType() == CoinType.FIVE) {
           return this.fives.remove(0);
        } else if (coin.getCoinType() == CoinType.TEN) {
            return this.tens.remove(0);
        } else if (coin.getCoinType() == CoinType.TWENTY) {
            return this.twenties.remove(0);
        } else if (coin.getCoinType() == CoinType.FIFTY) {
            return this.fifties.remove(0);
        } else if (coin.getCoinType() == CoinType.ONEPOUND) {
            return this.pounds.remove(0);
        }
        return this.twoPounds.remove(0);
    }


    public ArrayList<Coin> removeCoinsCredit() {
        ArrayList<Coin> removedCoins = (this.coinsCredit);
        this.coinsCredit.clear();
        return removedCoins;
    }

    public void moveCoinsCreditToTakings() {
        for (Coin coin : this.getCoinsCredit()){
            addCoinToTakings(coin);
        }
        this.getCoinsCredit().clear();
    }

    public void moveFiveToCoinReturn() {
        FivePence movedCoin = this.fives.remove(0);
        this.addCoinToCoinReturn(movedCoin);
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

    public int countCoinsInTakings() {
        return this.fives.size() +
                this.tens.size() +
                this.twenties.size() +
                this.fifties.size() +
                this.pounds.size() +
                this.twoPounds.size();
    }


    public int countTakings() {
       return this.fives.size()*5 +
        this.tens.size()*10 +
        this.twenties.size()*20 +
        this.fifties.size()*50 +
        this.pounds.size()*100 +
        this.twoPounds.size()*200;
    }

    public void moveCoinFromTakingsToCoinReturn(Coin coin) {
        Coin movedCoin = this.removeCoinFromTakings(coin);
        this.addCoinToCoinReturn(movedCoin);
    }

    public void clearCoinsCredit() {
        this.getCoinsCredit().clear();
    }
}

