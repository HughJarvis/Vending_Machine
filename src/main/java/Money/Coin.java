package Money;

public abstract class Coin {

    private CoinType coinType;

    public Coin(CoinType coinType) {
        this.coinType = coinType;
    }

    public CoinType getCoinType() {
        return coinType;
    }

    public int getCoinValue(){
        return this.coinType.getValue();
    }
}
