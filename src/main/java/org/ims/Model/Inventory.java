package org.ims.Model;

import java.util.UUID;

public class Inventory {
    private final UUID inventoryId;
    private final UUID productId;
    private final UUID warehouseId;
    private int onHandQuantity;
    private int reservedQuantity;

    public Inventory(UUID inventoryId, UUID productId, UUID warehouseId, int onHandQuantity) {
        this.inventoryId = UUID.randomUUID();
        //TODO: Add validation for below ids
        this.productId = productId;
        this.warehouseId = warehouseId;
        this.onHandQuantity = onHandQuantity;
        this.reservedQuantity = 0;
    }

    public UUID getInventoryId() {
        return inventoryId;
    }

    public UUID getProductId() {
        return productId;
    }

    public UUID getWarehouseId() {
        return warehouseId;
    }

    public int getOnHandQuantity() {
        return onHandQuantity;
    }

    public synchronized void reserveQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Quantity must be positive"
            );
        }

        if (quantity > getAvailableQuantity()) {
            throw new IllegalArgumentException(
                    "Insufficient available stock"
            );
        }

        reservedQuantity += quantity;
    }

    public synchronized int getAvailableQuantity() {
        return onHandQuantity - reservedQuantity;
    }
}
