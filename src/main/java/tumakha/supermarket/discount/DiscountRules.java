package tumakha.supermarket.discount;

import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.stream;

/**
 * Discount Rules configuration.
 *
 * <p>
 * Note: Only one discount for one item is supported.
 * </p>
 * @author Yuriy Tumakha
 */
public class DiscountRules {

    private Map<String, Discount> discounts;

    private DiscountRules(Builder builder) {
        this.discounts = builder.discounts;
    }

    private Discount getByItemCode(String itemCode) {
        return discounts.get(itemCode);
    }

    public static class Builder {

        private Map<String, Discount> discounts = new HashMap<>();

        public Builder buy3For2(String itemCode) {
            discounts.put(itemCode, new Buy3For2());
            return this;
        }

        public Builder buy2(String itemCode, double discountPrice) {
            discounts.put(itemCode, new Buy2(discountPrice));
            return this;
        }

        public Builder buy3CheapestFree(String... itemCodes) {
            final Buy3CheapestFree buy3CheapestFree = new Buy3CheapestFree(itemCodes);
            stream(itemCodes).forEach(itemCode -> discounts.put(itemCode, buy3CheapestFree));
            return this;
        }

        public Builder buyItemsGetBonus(String itemCode, int itemCount, String bonusCode, int bonusCount) {
            discounts.put(itemCode, new BuyItemsGetBonus(itemCount, bonusCode, bonusCount));
            return this;
        }

        public DiscountRules build() {
            return new DiscountRules(this);
        }
    }

}
