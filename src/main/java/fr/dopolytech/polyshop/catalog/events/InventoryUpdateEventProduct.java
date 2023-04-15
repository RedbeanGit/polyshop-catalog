package fr.dopolytech.polyshop.catalog.events;

public class InventoryUpdateEventProduct {
    public String productId;
    public int oldQuantity;
    public int newQuantity;
    public boolean isSuccessful;
}
