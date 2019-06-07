package Machine;

import Money.Coin;

import java.util.ArrayList;

public class VendingMachine {

    private ArrayList<Coin> coins;
    private ArrayList<Drawer> drawers;
    private CoinReturn coinReturn;

    public VendingMachine() {
        this.coins = new ArrayList<Coin>();
        this.drawers = new ArrayList<Drawer>();
        this.coinReturn = new CoinReturn();
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

    public int getBalance() {
        int total = 0;
        for (Coin coin : this.coins){
            total += coin.getCoinValue();
        }
        return total;
    }

    public void addCoin(Coin coin) {
        if (checkCoinIsValid(coin)) {
            this.coins.add(coin);
        }
    }

    public boolean checkCoinIsValid(Coin coin){
        return ((coin.getCoinValue() != 1) && (coin.getCoinValue() != 2));
    }
}
