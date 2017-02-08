package tumakha.supermarket.discount;

import tumakha.supermarket.ReceiptItem;

import java.util.Map;
import java.util.Optional;

/**
 * Default discount = full price
 *
 * @author Yuriy Tumakha
 */
class Discount {

    String getDescription() {
        return "No Discount";
    }

    Optional<Bonus> apply(ReceiptItem receiptItem, Map<String, ReceiptItem> items) {
        receiptItem.setTotal(receiptItem.getTotal() + receiptItem.getItem().getPrice());
        return Optional.empty();
    }

}
