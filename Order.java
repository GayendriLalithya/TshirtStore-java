public class Order {
    private String orderID;
    private String customerID;
    private String size;
    private int quantity;
    private double amount;
    private String status;

    // Constructor
    public Order(String orderID, String customerID, String size, int quantity, double amount, String status) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.size = size;
        this.quantity = quantity;
        this.amount = amount;
        this.status = status;
    }

    // Getters
    public String getOrderID() { return orderID; }
    public String getCustomerID() { return customerID; }
    public String getSize() { return size; }
    public int getQuantity() { return quantity; }
    public double getAmount() { return amount; }
    public String getStatus() { return status; }
}
