package integration;

import java.util.HashMap;
import dto.ItemDTO;

public class Inventory {
    
    private HashMap<String, ItemDTO> inventory = new HashMap<>();

    public Inventory() {
        createItems();
    }

    public boolean isValidItem(String id) {
        return inventory.get(id) != null;
    }

    public ItemDTO getItem(String id) {
        return inventory.get(id);
    }

    private void createItems() {
        inventory.put("abc123", new ItemDTO("abc123", "BigWheel Oatmeal 500g, whole grain oats, high fiber, gluten free", "BigWheel Oatmeal", 29.90f, 6f));
        inventory.put("def456", new ItemDTO("def456", "YouGoGo Blueberry 240g, low sugar youghurt, blueberry flavour", "YouGoGo Blueberry", 14.90f, 6f));
    }
}
