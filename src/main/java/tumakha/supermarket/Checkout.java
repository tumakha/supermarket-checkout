package tumakha.supermarket;

import tumakha.supermarket.discount.Bonus;
import tumakha.supermarket.discount.DiscountRules;
import tumakha.supermarket.item.Item;
import tumakha.supermarket.item.ItemService;

import java.util.LinkedHashMap;
import java.util.Map;

import static java.lang.String.format;

/**
 * @author Yuriy Tumakha
 */
public class Checkout {

    private Map<String, ReceiptItem> items = new LinkedHashMap<>();

    private ItemService itemService;

    private DiscountRules discountRules;

    public Checkout(ItemService itemService, DiscountRules discountRules) {
        this.itemService = itemService;
        this.discountRules = discountRules;
    }

    public Map<String, ReceiptItem> getItems() {
        return items;
    }

    public double getTotal() {
        return items.values().stream().mapToDouble(ReceiptItem::getTotal).sum();
    }

    public void scan(String itemCode) {
        Item item = itemService.getByCode(itemCode).orElseThrow(() -> new RuntimeException("Item not found"));
        ReceiptItem receiptItem = getReceiptItem(item);

        discountRules.addItem(receiptItem, items)
                .ifPresent(bonus -> itemService.getByCode(bonus.getCode()).map(this::getReceiptItem)
                        .ifPresent(bonusItem -> addBonus(bonus, bonusItem)));

    }

    private ReceiptItem getReceiptItem(Item item) {
        ReceiptItem receiptItem = items.getOrDefault(item.getCode(), new ReceiptItem(item));
        items.putIfAbsent(receiptItem.getItem().getCode(), receiptItem);
        return receiptItem;
    }

    private void addBonus(Bonus bonus, ReceiptItem bonusItem) {
        bonusItem.setTotal(bonusItem.getTotal() - bonusItem.getItem().getPrice() * bonus.getCount());
        bonusItem.setDiscount("Bonus");
    }

    public void printReceipt() {
        System.out.println("\n=========== Receipt ===========");
        items.values().stream().map(item -> format("%-13s - %.2f x %d = %.2f%s",
                item.getItem().getName(), item.getItem().getPrice(), item.getCount(), item.getTotal(),
                item.getDiscount() == null ? "" : format(" [%s]", item.getDiscount())))
                .forEach(System.out::println);
        System.out.println(format("Grand Total:    %.2f", getTotal()));
    }

}
