package tumakha.supermarket;

import tumakha.supermarket.item.Item;

/**
 * @author Yuriy Tumakha
 */
public class ReceiptItem {

    private Item item;

    private int count;

    private double total;

    private String discount;

    public ReceiptItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

}
