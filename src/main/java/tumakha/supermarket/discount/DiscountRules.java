package tumakha.supermarket.discount;

import tumakha.supermarket.ReceiptItem;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

    private static final Discount DEFAULT_DISCOUNT = new Discount();
    private static final Discount BUY3_FOR2_DISCOUNT = new Buy3For2();
    private Map<String, Discount> discounts;

    private DiscountRules(Builder builder) {
        this.discounts = builder.discounts;
    }

    public Optional<Bonus> addItem(ReceiptItem receiptItem, Map<String, ReceiptItem> items) {
        receiptItem.setCount(receiptItem.getCount() + 1);
        Discount discount = discounts.getOrDefault(receiptItem.getItem().getCode(), DEFAULT_DISCOUNT);
        return discount.apply(receiptItem, items);
    }

    public static class Builder {

        private Map<String, Discount> discounts = new HashMap<>();

        public Builder buy3For2(String itemCode) {
            discounts.put(itemCode, BUY3_FOR2_DISCOUNT);
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
