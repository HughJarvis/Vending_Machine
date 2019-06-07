import Machine.CoinReturn;
import Money.Coin;
import Money.CoinType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CoinReturnTest {

    private Coin coin1;
    private Coin coin2;
    private Coin coin3;
    private CoinReturn coinReturn;

    @Before
    public void setUp(){
        coin1 = new Coin(CoinType.TEN);
        coin2 = new Coin(CoinType.TWENTY);
        coin3 = new Coin(CoinType.FIFTY);
        coinReturn = new CoinReturn();
    }

    @Test
    public void canAddCoin() {
        coinReturn.addCoin(coin1);
        assertEquals(1, coinReturn.countCoins());
    }

    @Test
    public void canGetTotalAmount() {
        coinReturn.addCoin(coin1);
        coinReturn.addCoin(coin2);
        coinReturn.addCoin(coin3);
        assertEquals(80, coinReturn.getTotalAmount());
    }
}
