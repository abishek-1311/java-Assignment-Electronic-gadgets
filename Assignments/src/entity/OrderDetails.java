package entity;

public class OrderDetails {
    private int orderDetailId;
    private Orders order;
    private Product product;
    private int quantity;
    private double discount = 0;

    public OrderDetails(int orderDetailId, Orders order, Product product, int quantity) {
        this.orderDetailId = orderDetailId;
        this.order = order;
        this.product = product;
        setQuantity(quantity);
    }

    public int getOrderDetailId() { return orderDetailId; }
    public Orders getOrder() { return order; }
    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be greater than 0.");
        this.quantity = quantity;
    }

    public double calculateSubtotal() {
        return (product.getPrice() * quantity) * (1 - discount);
    }

    public void getOrderDetailInfo() {
        System.out.println("Product: " + product.getProductName() + " | Quantity: " + quantity + " | Subtotal: " + calculateSubtotal());
    }

    public void addDiscount(double discount) {
        if (discount < 0 || discount >= 1) throw new IllegalArgumentException("Discount must be between 0 and 1.");
        this.discount = discount;
    }
}
