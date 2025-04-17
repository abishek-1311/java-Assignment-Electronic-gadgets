package entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Orders {
    private int orderId;
    private Customer customer;
    private LocalDate orderDate;
    private double totalAmount;
    private String orderStatus;
    private List<OrderDetails> orderDetailsList = new ArrayList<>();

    public Orders(int orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.orderDate = LocalDate.now();
        this.totalAmount = 0.0;
        this.orderStatus = "Processing";
    }

    public Orders(int orderId, Customer customer, LocalDate orderDate, double totalAmount, String orderStatus) {
        this.orderId = orderId;
        this.customer = customer;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
    }

	public int getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public LocalDate getOrderDate() { return orderDate; }
    public double getTotalAmount() { return totalAmount; }
    public String getOrderStatus() { return orderStatus; }

    public void calculateTotalAmount() {
        totalAmount = 0;
        for (OrderDetails detail : orderDetailsList) {
            totalAmount += detail.calculateSubtotal();
        }
    }

    public void getOrderDetails() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer: " + customer.getFirstName() + " " + customer.getLastName());
        System.out.println("Date: " + orderDate);
        System.out.println("Status: " + orderStatus);
        for (OrderDetails detail : orderDetailsList) {
            detail.getOrderDetailInfo();
        }
    }

    public void updateOrderStatus(String status) {
        this.orderStatus = status;
    }

    public void cancelOrder() {
        orderStatus = "Cancelled";
        for (OrderDetails detail : orderDetailsList) {
            detail.getProduct().setPrice(detail.getProduct().getPrice() + detail.calculateSubtotal()); // Example logic
        }
    }

    public void addOrderDetail(OrderDetails detail) {
        orderDetailsList.add(detail);
        calculateTotalAmount();
    }
}
