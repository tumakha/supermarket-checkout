package tumakha.supermarket.discount;

import tumakha.supermarket.ReceiptItem;

import java.util.Map;
import java.util.Optional;

/**
 * Buy 3 (equals) items and pay for 2.
 *
 * @author Yuriy Tumakha
 */
class Buy3For2 extends Discount {

    String getDescription() {
        return "Buy 3 for 2";
    }

    @Override
    Optional<Bonus> apply(ReceiptItem receiptItem, Map<String, ReceiptItem> items) {
        if (receiptItem.getCount() % 3 == 0) {
            receiptItem.setDiscount(getDescription());
            return Optional.empty();
        }
        return super.apply(receiptItem, items);
    }

}
