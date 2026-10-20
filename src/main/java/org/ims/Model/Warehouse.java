package org.ims.Model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Warehouse {
    private UUID warehouseId;
    private String warehouseName;
    private Map<UUID,Inventory> inventoryMap;
}
