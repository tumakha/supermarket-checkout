package tumakha.supermarket.discount;

import tumakha.supermarket.ReceiptItem;

import java.util.Map;
import java.util.Optional;

import static java.lang.String.format;

/**
 * Buy 2 (equals) items for a special price.
 *
 * @author Yuriy Tumakha
 */
class Buy2 extends Discount {

    private double discountPrice;

    Buy2(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    String getDescription() {
        return format("Buy 2 for %.2f", discountPrice);
    }

    @Override
    Optional<Bonus> apply(ReceiptItem receiptItem, Map<String, ReceiptItem> items) {
        if (receiptItem.getCount() % 2 == 0) {
            receiptItem.setTotal(receiptItem.getTotal() - receiptItem.getItem().getPrice() + 2*discountPrice);
            receiptItem.setDiscount(getDescription());
            return Optional.empty();
        }
        return super.apply(receiptItem, items);
    }

}
