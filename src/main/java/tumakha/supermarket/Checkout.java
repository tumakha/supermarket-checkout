package tumakha.supermarket;

import tumakha.supermarket.discount.DiscountRules;
import tumakha.supermarket.item.ItemService;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Yuriy Tumakha
 */
public class Checkout {

    private Map<String, ReceiptItem> items = new LinkedHashMap<>();

    private double total;

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
        return total;
    }

    public void scan(String itemCode) {

    }

}
