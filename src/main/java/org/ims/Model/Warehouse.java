package org.ims.Model;

import java.util.*;

public class Warehouse {
    private final UUID warehouseId;
    private String warehouseName;
    private Map<UUID, Inventory> inventory;

    public Warehouse(String warehouseName) {
        this.warehouseId = UUID.randomUUID();
        this.warehouseName = warehouseName;
        this.inventory = new HashMap<>();
    }

    public UUID getWarehouseId() {
        return warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public void addInventory(Inventory item) {
        Objects.requireNonNull(item, "Inventory cannot be null");

        if (!warehouseId.equals(item.getWarehouseId())) {
            throw new IllegalArgumentException(
                    "Inventory belongs to another warehouse"
            );
        }

        UUID productId = item.getProductId();

        if (inventory.containsKey(productId)) {
            throw new IllegalStateException(
                    "Inventory already exists for this product"
            );
        }

        inventory.put(productId, item);
    }

    public Iterable<Inventory> getInventory(){
         return Collections.unmodifiableCollection(inventory.values());
    }

    public Inventory getInventory(UUID productId) {
        Inventory item = inventory.get(productId);

        if (item == null) {
            throw new NoSuchElementException(
                    "Inventory not found for product"
            );
        }

        return item;
    }
}
