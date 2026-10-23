package org.ims.Model;

import org.ims.Utils.Validate;

import java.util.UUID;

public class Inventory {
    private final UUID inventoryId;
    private final UUID productId;
    private final UUID warehouseId;
    private int onHandQuantity;
    private int reservedQuantity;

    public Inventory(UUID productId, UUID warehouseId, int onHandQuantity) {
        this.inventoryId = UUID.randomUUID();
        //TODO: Add validation for below ids
        if(Validate.validateUUID(productId)) this.productId = productId;
        else throw  new IllegalArgumentException("Invalid product id");
        if (Validate.validateUUID(warehouseId)) this.warehouseId = warehouseId;
        else throw  new IllegalArgumentException("Invalid warehouse id");
        if(onHandQuantity >= 0) this.onHandQuantity = onHandQuantity;
        else throw  new IllegalArgumentException("Invalid onHandQuantity");
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

    public synchronized void reserveStock(int quantity) {
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

    public void recieveStock(int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive");
        onHandQuantity+=quantity;
    }

    public synchronized void shipStock(int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive");
        if (quantity > reservedQuantity) throw new IllegalArgumentException("Please reserve product quantity");
        reservedQuantity -= quantity;
        onHandQuantity -= quantity;
    }

    public void cancelStock(int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive");
        reservedQuantity -= quantity;
        onHandQuantity+=quantity;
    }
}
