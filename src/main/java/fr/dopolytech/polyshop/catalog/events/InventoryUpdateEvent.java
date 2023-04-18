package fr.dopolytech.polyshop.catalog.events;

public class InventoryUpdateEvent extends Event {
    public String type = "inventory.update";
    public String orderId;
    public InventoryUpdateEventProduct[] products;
}
