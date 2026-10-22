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

    public void addInventory(Inventory inventory){
        if(inventory!=null && inventory.getInventoryId()!=null) this.inventory.put(inventory.getProductId(), inventory);
    }

    public Iterable<Inventory> getInventory(){
         return Collections.unmodifiableCollection(inventory.values());
    }
}
