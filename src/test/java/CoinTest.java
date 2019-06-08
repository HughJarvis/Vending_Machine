import Money.Coin;
import Money.CoinType;
import Money.FiftyPence;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CoinTest {

    private Coin coin;

    @Before
    public void setUp() {
        coin = new FiftyPence();
    }

        @Test
        public void canGetValue() {
            assertEquals(50, coin.getCoinValue());
        }
    }

