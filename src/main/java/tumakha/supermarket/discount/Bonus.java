package tumakha.supermarket.discount;

/**
 * @author Yuriy Tumakha
 */
public class Bonus {

    private String code;
    private int count;

    public Bonus(String code, int count) {
        this.code = code;
        this.count = count;
    }

    public String getCode() {
        return code;
    }

    public int getCount() {
        return count;
    }
}
