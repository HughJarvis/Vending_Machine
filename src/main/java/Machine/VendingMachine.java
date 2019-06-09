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


    public Coin removeFiveFromTakings() {
        Coin removedCoin = null;
        if (this.fives.size() > 0) {
            removedCoin = this.fives.get(this.fives.size()-1);
            this.fives.remove(this.fives.size()-1);
        }
        return removedCoin;
        }

    public Coin removeTenFromTakings() {
            Coin removedCoin = null;
        if (this.tens.size() > 0) {
            removedCoin = this.tens.get(this.tens.size()-1);
            this.tens.remove(this.tens.size()-1);
        }
        return removedCoin;
    }

    public Coin removeTwentyFromTakings() {
        Coin removedCoin = null;
        if (this.twenties.size() > 0) {
            removedCoin = this.twenties.get(this.twenties.size()-1);
            this.twenties.remove(this.twenties.size()-1);
        }
        return removedCoin;
    }

    public Coin removeFiftyFromTakings() {
        Coin removedCoin = null;
        if (this.fifties.size() > 0) {
            removedCoin = this.fifties.get(this.fifties.size()-1);
            this.fifties.remove(this.fifties.size()-1);
        }
        return removedCoin;
    }

    public Coin removePoundFromTakings() {
    Coin removedCoin = null;
        if (this.pounds.size() > 0) {
        removedCoin = this.pounds.get(this.pounds.size()-1);
        this.pounds.remove(this.pounds.size()-1);
        }
        return removedCoin;
    }

    public Coin removeTwoPoundFromTakings()   {
        Coin removedCoin = null;
        if (this.twoPounds.size() > 0) {
            removedCoin = this.twoPounds.get(this.twoPounds.size()-1);
            this.twoPounds.remove(this.twoPounds.size()-1);
        }
        return removedCoin;
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
        FivePence movedCoin = this.fives.remove(this.fives.size()-1);
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

    public void moveTenFromTakingsToCoinReturn() {
        Coin movedCoin = this.removeTenFromTakings();
        this.addCoinToCoinReturn(movedCoin);
    }

    public void clearCoinsCredit() {
        this.getCoinsCredit().clear();
    }

    public ArrayList<Coin> removeMultipleFivesFromTakings(int numberToRemove) {
        ArrayList<Coin> removedCoins = new ArrayList<Coin>();
        int coinsRemoved = 0;
        while (coinsRemoved < numberToRemove){
            this.removeTwentyFromTakings();
            coinsRemoved += 1;
        }
        return removedCoins;
    }

    public ArrayList<Coin> removeMultipleTensFromTakings(int numberToRemove) {
        ArrayList<Coin> removedCoins = new ArrayList<Coin>();
        int coinsRemoved = 0;
        while (coinsRemoved < numberToRemove){
            this.removeTenFromTakings();
            coinsRemoved += 1;
        }
        return removedCoins;
    }

    public ArrayList<Coin> removeMultipleTwentiesFromTakings(int numberToRemove) {
        ArrayList<Coin> removedCoins = new ArrayList<Coin>();
        int coinsRemoved = 0;
        while (coinsRemoved < numberToRemove){
            removeTwentyFromTakings();
            coinsRemoved += 1;
        }
        return removedCoins;
    }

    public ArrayList<Coin> removeMultipleFiftiesFromTakings(int numberToRemove) {
        ArrayList<Coin> removedCoins = new ArrayList<Coin>();
        int coinsRemoved = 0;
        while (coinsRemoved < numberToRemove){
            this.removeFiftyFromTakings();
            coinsRemoved += 1;
        }
        return removedCoins;
    }

    public ArrayList<Coin> removeMultiplePoundsFromTakings(int numberToRemove) {
        ArrayList<Coin> removedCoins = new ArrayList<Coin>();
        int coinsRemoved = 0;
        while (coinsRemoved < numberToRemove){
            this.removePoundFromTakings();
            coinsRemoved += 1;
        }
        return removedCoins;
    }

    public ArrayList<Coin> moveMultipleTwoPoundsFromTakingsToCoinReturn(int numberToRemove) {
        ArrayList<Coin> removedCoins = new ArrayList<Coin>();
        int coinsRemoved = 0;
        while (coinsRemoved < numberToRemove){
            this.removeTwoPoundFromTakings();
            coinsRemoved += 1;
        }
        return removedCoins;
    }



//    public int putCorrectChangeInCoinReturn(int change) {
//        int numberOfCoins = this.getNumberOf100sInChange(change);
//        int coinsRemoved = 0;
//
//        int changeLeft = change % 100;
//        return changeLeft;
//    }
}

