package tumakha.supermarket.discount;

import tumakha.supermarket.ReceiptItem;

import java.util.Map;
import java.util.Optional;

import static java.util.Optional.of;

/**
 * for each N (equals) items X, you get K items Y for free.
 *
 * @author Yuriy Tumakha
 */
class BuyItemsGetBonus extends Discount {

    private int itemCount;
    private Optional<Bonus> bonus;

    BuyItemsGetBonus(int itemCount, String bonusCode, int bonusCount) {
        this.itemCount = itemCount;
        this.bonus = of(new Bonus(bonusCode, bonusCount));
    }

    String getDescription() {
        return "Get bonus";
    }

    @Override
    Optional<Bonus> apply(ReceiptItem receiptItem, Map<String, ReceiptItem> items) {
        super.apply(receiptItem, items);
        if (receiptItem.getCount() % itemCount == 0) {
            receiptItem.setDiscount(getDescription());
            return bonus;
        } else {
            return Optional.empty();
        }
    }

}
