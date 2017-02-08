package tumakha.supermarket.discount;

import tumakha.supermarket.ReceiptItem;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static java.util.Comparator.comparingDouble;
import static java.util.stream.Collectors.toSet;

/**
 * Buy 3 (in a set of items) and the cheapest is free.
 *
 * @author Yuriy Tumakha
 */
class Buy3CheapestFree extends Discount {

    private static final int N = 3;

    private Set<String> itemCodes;

    Buy3CheapestFree(String... itemCodes) {
        this.itemCodes = stream(itemCodes).collect(toSet());
    }

    String getDescription() {
        return "Buy 3 in a set";
    }

    @Override
    Optional<Bonus> apply(ReceiptItem receiptItem, Map<String, ReceiptItem> items) {
        super.apply(receiptItem, items);
        List<ReceiptItem> discountItems = itemCodes.stream().map(code -> items.get(code)).filter(Objects::nonNull)
                .sorted(comparingDouble((item -> item.getItem().getPrice()))).collect(Collectors.toList());
        int count = discountItems.stream().mapToInt(ReceiptItem::getCount).sum();
        if (count == N) {
            ReceiptItem cheapestItem = discountItems.get(0);
            cheapestItem.setTotal(cheapestItem.getTotal() - cheapestItem.getItem().getPrice());
            receiptItem.setDiscount(getDescription());
        } else if (count > N) {
            int freeCount = count / N;
            discountItems.forEach(item -> {
                item.setTotal(item.getCount() * item.getItem().getPrice());
                item.setDiscount(null);
            });
            for (ReceiptItem cheapestItem : discountItems) {
                cheapestItem.setDiscount(getDescription());
                if (cheapestItem.getCount() < freeCount) {
                    cheapestItem.setTotal(0.0);
                    freeCount -= cheapestItem.getCount();
                } else {
                    cheapestItem.setTotal(cheapestItem.getTotal() - freeCount * cheapestItem.getItem().getPrice());
                    break;
                }
            }
        }
        return Optional.empty();
    }

}
