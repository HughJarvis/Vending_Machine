package Money;

public enum CoinType {

    ONE (1),
    TWO (2),
    FIVE (5),
    TEN (10),
    TWENTY (20),
    FIFTY (50),
    ONEPOUND (100),
    TWOPOUND (200);

    private final int value;

    CoinType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
