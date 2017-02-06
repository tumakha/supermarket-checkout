package tumakha.supermarket;

import org.junit.BeforeClass;
import org.junit.Test;
import tumakha.supermarket.discount.DiscountRules;
import tumakha.supermarket.item.Item;
import tumakha.supermarket.item.ItemService;

import static org.junit.Assert.assertEquals;

/**
 * @author Yuriy Tumakha
 */
public class CheckoutTest {

    private static final String FR1 = "FR1";
    private static final String SR1 = "SR1";
    private static final String CF1 = "CF1";
    private static final String JC1 = "JC1";
    private static final String BR1 = "BR1";
    private static final String PN1 = "PN1";
    private static final ItemService itemService = new ItemService();

    @BeforeClass
    public static void init() {
        itemService.addItems(
                new Item(FR1, "Fruit tea", 3.11),
                new Item(SR1, "Strawberries", 5.00),
                new Item(CF1, "Coffee", 11.23),
                new Item(JC1, "Juice", 2.5),
                new Item(BR1, "Beer", 1.75),
                new Item(PN1, "Peanuts", 1.99));
    }

    @Test
    public void testBuy3For2() {
        DiscountRules discountRules = new DiscountRules.Builder().buy3For2(FR1).build();
        Checkout checkout = new Checkout(itemService, discountRules);
        checkout.scan(FR1);
        checkout.scan(FR1);
        checkout.scan(FR1);
        checkout.scan(SR1);
        assertEquals("Wrong checkout total", 11.22, checkout.getTotal(), 0.001);
        checkout.scan(FR1);
        checkout.scan(FR1);
        checkout.scan(CF1);
        assertEquals("Wrong checkout total", 28.67, checkout.getTotal(), 0.001);
        checkout.scan(FR1);
        assertEquals("Wrong checkout total", 28.67, checkout.getTotal(), 0.001);
    }

    @Test
    public void testBuy2() {
        DiscountRules discountRules = new DiscountRules.Builder().buy2(SR1, 4.5).build();
        Checkout checkout = new Checkout(itemService, discountRules);
        checkout.scan(FR1);
        checkout.scan(FR1);
        checkout.scan(FR1);
        checkout.scan(SR1);
        assertEquals("Wrong checkout total", 11.22, checkout.getTotal(), 0.001);
        checkout.scan(FR1);
        checkout.scan(FR1);
        checkout.scan(CF1);
        assertEquals("Wrong checkout total", 28.67, checkout.getTotal(), 0.001);
        checkout.scan(FR1);
        assertEquals("Wrong checkout total", 28.67, checkout.getTotal(), 0.001);
    }

    @Test
    public void testBuy3CheapestFree() {
        DiscountRules discountRules = new DiscountRules.Builder().buy3CheapestFree(FR1, SR1, CF1, JC1, PN1).build();
        Checkout checkout = new Checkout(itemService, discountRules);
        checkout.scan(FR1);
        checkout.scan(SR1);
        assertEquals("Wrong checkout total", 8.11, checkout.getTotal(), 0.001);
        checkout.scan(BR1);
        assertEquals("Wrong checkout total", 9.86, checkout.getTotal(), 0.001);
        checkout.scan(CF1);
        assertEquals("Wrong checkout total", 17.98, checkout.getTotal(), 0.001);
        checkout.scan(PN1);
        assertEquals("Wrong checkout total", 21.09, checkout.getTotal(), 0.001);
    }

    @Test
    public void testBuyItemsGetBonus() {
        DiscountRules discountRules = new DiscountRules.Builder().buyItemsGetBonus(BR1, 5, PN1, 3).build();
        Checkout checkout = new Checkout(itemService, discountRules);
        checkout.scan(SR1);
        checkout.scan(BR1);
        checkout.scan(BR1);
        checkout.scan(BR1);
        checkout.scan(BR1);
        assertEquals("Wrong checkout total", 12.0, checkout.getTotal(), 0.001);
        assertEquals("Wrong items count", 2, checkout.getItems().size());
        checkout.scan(BR1);
        assertEquals("Wrong checkout total", 13.75, checkout.getTotal(), 0.001);
        assertEquals("Wrong items count", 3, checkout.getItems().size());
    }

}
