package entity;

import java.time.LocalDate;

public class Inventory {
    private int inventoryId;
    private Product product;
    private int quantityInStock;
    private LocalDate lastStockUpdate;

    public Inventory(int inventoryId, Product product, int quantityInStock) {
        this.inventoryId = inventoryId;
        this.product = product;
        this.quantityInStock = quantityInStock;
        this.lastStockUpdate = LocalDate.now();
    }

    
    public Inventory(int inventoryId, Product product, int quantityInStock, LocalDate lastStockUpdate) {
        this.inventoryId = inventoryId;
        this.product = product;
        this.quantityInStock = quantityInStock;
        this.lastStockUpdate = lastStockUpdate;
    }


    public int getInventoryId() {
        return inventoryId;
    }

    public LocalDate getLastStockUpdate() {
        return lastStockUpdate;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void addToInventory(int quantity) {
        quantityInStock += quantity;
        lastStockUpdate = LocalDate.now();
    }

    public void removeFromInventory(int quantity) {
        if (quantity > quantityInStock) {
            throw new IllegalArgumentException("Not enough stock to remove.");
        }
        quantityInStock -= quantity;
        lastStockUpdate = LocalDate.now();
    }

    public void updateStockQuantity(int newQuantity) {
        quantityInStock = newQuantity;
        lastStockUpdate = LocalDate.now();
    }

    public boolean isProductAvailable(int quantityToCheck) {
        return quantityInStock >= quantityToCheck;
    }

    public double getInventoryValue() {
        return quantityInStock * product.getPrice();
    }

    public void listProduct() {
        System.out.println("Product ID: " + product.getProductId());
        System.out.println("Stock: " + quantityInStock);
    }
}
