package Machine;

import Money.Coin;

import java.util.ArrayList;

public class CoinReturn {

    private ArrayList<Coin> coins;

    public CoinReturn() {
        this.coins = new ArrayList<Coin>();
    }

    public ArrayList<Coin> getCoins() {
        return coins;
    }

    public void addCoin(Coin coin) {
        this.coins.add(coin);
    }

    public int countCoins() {
        return this.coins.size();
    }

    public int getTotalAmount() {
        int total = 0;
        for (Coin coin : this.coins){
            total += coin.getCoinValue();
        }
        return total;
    }
}
