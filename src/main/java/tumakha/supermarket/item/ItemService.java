package tumakha.supermarket.item;

import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.stream;

/**
 * @author Yuriy Tumakha
 */
public class ItemService {

    Map<String, Item> itemsMap = new HashMap<>();

    public void addItem(Item item) {
        itemsMap.put(item.getCode(), item);
    }

    public void addItems(Item... items) {
        stream(items).forEach(this::addItem);
    }

    public Item getByCode(String code) {
        return itemsMap.get(code);
    }

}
