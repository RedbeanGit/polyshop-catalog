package fr.dopolytech.polyshop.catalog.events;

public class InventoryUpdateEvent {
    public String orderId;
    public InventoryUpdateEventProduct[] products;
}
