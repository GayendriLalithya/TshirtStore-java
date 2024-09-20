import java.util.Scanner;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;

public class TShirtStore {
    private Scanner scanner;
    private int lastOrderNumber = 10000; // Assuming the last order number starts from 10000.

    public TShirtStore() {
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        TShirtStore store = new TShirtStore();
        store.run();
    }

    public void run() {
        boolean running = true;
        while (running) {
            displayMenu();
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    placeOrder();
                    break;
                case "2":
                    searchCustomer();
                    break;
                case "3":
                    searchOrder();
                    break;
                case "4":
                    viewReports();
                    break;
                case "5":
                    setOrderStatus();
                    break;
                case "6":
                    deleteOrder();
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("[1] Place Order");
        System.out.println("[2] Search Customer");
        System.out.println("[3] Search Order");
        System.out.println("[4] View Reports");
        System.out.println("[5] Set Order Status");
        System.out.println("[6] Delete Order");
        System.out.println("[0] Exit");
        System.out.print("Input Option : ");
    }

    /*private void placeOrder() {
        String orderID = generateOrderID();
        System.out.println("Generated Order ID: " + orderID);

        String customerContact = getValidContactNumber();
        String tshirtSize = getValidTshirtSize();
        int quantity = getValidQuantity();
        double totalAmount = calculateTotalAmount(tshirtSize, quantity);

        System.out.println("Total Amount: " + totalAmount);

        // Ask to confirm the order placement
        System.out.println("Do you want to place this order (Y/N)?");
        String confirmation = scanner.nextLine();
        if ("Y".equalsIgnoreCase(confirmation)) {
            System.out.println("Order placed successfully!");
        } else {
            System.out.println("Order cancelled.");
        }
    }*/

    private void placeOrder() {
        do {
            String orderID = generateOrderID();
            System.out.println("Generated Order ID: " + orderID);

            String customerContact = getValidContactNumber();
            String tshirtSize = getValidTshirtSize();
            int quantity = getValidQuantity();
            double totalAmount = calculateTotalAmount(tshirtSize, quantity);

            System.out.println("Total Amount: " + totalAmount);

            // Ask to confirm the order placement
            System.out.println("Do you want to place this order (Y/N)?");
            String confirmation = scanner.nextLine();
            if ("Y".equalsIgnoreCase(confirmation)) {
                System.out.println("Order placed..!");
            } else {
                System.out.println("Order cancelled.");
                return; // Exit the method if order not confirmed
            }

            // Ask if user wants to place another order
            while (true) {
                System.out.println("Do you want to place another order (Y/N)?");
                String anotherOrder = scanner.nextLine();
                if ("Y".equalsIgnoreCase(anotherOrder)) {
                    break; // Break the loop to start another order
                } else if ("N".equalsIgnoreCase(anotherOrder)) {
                    return; // Exit the method if no more orders
                } else {
                    System.out.println("Invalid input. Please enter 'Y' for Yes or 'N' for No.");
                }
            }
        } while (true); // Continue the loop if another order is to be placed
    }

    private String generateOrderID() {
        lastOrderNumber++;
        return "ODR#" + lastOrderNumber;
    }

    private String getValidContactNumber() {
        String contactNumber;
        while (true) {
            System.out.println("Enter Customer Contact Number:");
            contactNumber = scanner.nextLine();
            if (contactNumber.matches("0\\d{9}")) {
                break;
            } else {
                System.out.println("Invalid contact number. Please try again.");
            }
        }
        return contactNumber;
    }

    private String getValidTshirtSize() {
        String size;
        while (true) {
            System.out.println("Enter T-Shirt Size (XS, S, M, L, XL, XXL):");
            size = scanner.nextLine().toUpperCase();
            if (size.matches("XS|S|M|L|XL|XXL")) {
                break;
            } else {
                System.out.println("Invalid T-Shirt size. Please try again.");
            }
        }
        return size;
    }

    private int getValidQuantity() {
        int quantity;
        while (true) {
            System.out.println("Enter Quantity:");
            if (scanner.hasNextInt()) {
                quantity = scanner.nextInt();
                scanner.nextLine(); // consume newline left-over
                if (quantity > 0) {
                    break;
                } else {
                    System.out.println("Quantity must be greater than zero. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // consume the invalid input
            }
        }
        return quantity;
    }

    private double calculateTotalAmount(String size, int quantity) {
        double price;
        switch (size) {
            case "XS":
                price = 600;
                break;
            case "S":
                price = 800;
                break;
            case "M":
                price = 900;
                break;
            case "L":
                price = 1000;
                break;
            case "XL":
                price = 1100;
                break;
            case "XXL":
                price = 1200;
                break;
            default:
                price = 0; // this should never happen due to prior validation
                break;
        }
        return price * quantity;
    }


    // Placeholder methods for other features
    private void searchCustomer() {
        boolean continueSearch = false;
        do {
            System.out.print("Enter Customer Phone Number: ");
            String phone = scanner.nextLine();

            if (!isValidPhone(phone)) {
                System.out.println("Invalid input..");
            } else {
                displayCustomerOrders(phone);
            }

            System.out.println("Do you want to search another customer report? (y/n): ");
            String response = scanner.nextLine();
            continueSearch = "y".equalsIgnoreCase(response);
        } while (continueSearch);
    }

    private boolean isValidPhone(String phone) {
        return phone.matches("0\\d{9}");
    }

    private void displayCustomerOrders(String phoneNumber) {
        // Example data structure for storing orders, consider retrieving this from a database or file
        Map<String, List<Order>> orders = new HashMap<>();
        orders.put("0783589852", Arrays.asList(new Order("M", 6, 5400.00),
                                               new Order("XL", 4, 4400.00),
                                               new Order("XXL", 1, 1200.00)));

        List<Order> customerOrders = orders.getOrDefault(phoneNumber, new ArrayList<>());

        if (customerOrders.isEmpty()) {
            System.out.println("No orders found for this customer.");
        } else {
            double totalAmount = 0;
            System.out.println("| Size | QTY | Amount |");
            System.out.println("-----------------------");
            for (Order order : customerOrders) {
                System.out.printf("| %s | %d | %.2f |\n", order.size, order.quantity, order.amount);
                totalAmount += order.amount;
            }
            System.out.printf("Total Amount: %.2f\n", totalAmount);
        }
    }

    // You would need an Order class to use in your mock data structure
    class Order {
        String size;
        int quantity;
        double amount;

        public Order(String size, int quantity, double amount) {
            this.size = size;
            this.quantity = quantity;
            this.amount = amount;
        }
    }

    private void searchOrder() {
        boolean continueSearch = false;
        do {
            System.out.print("Enter order ID: ");
            String orderId = scanner.nextLine();

            // Mock data for demonstration. Replace this with your data retrieval logic.
            Map<String, OrderDetails> orders = new HashMap<>();
            orders.put("ODR#00004", new OrderDetails("0776198410", "M", 3, 2700.00, "delivering"));

            if (orders.containsKey(orderId)) {
                OrderDetails order = orders.get(orderId);
                System.out.println("Phone Number : " + order.phoneNumber);
                System.out.println("Size         : " + order.size);
                System.out.println("QTY          : " + order.quantity);
                System.out.println("Amount       : " + order.amount);
                System.out.println("Status       : " + order.status);
            } else {
                System.out.println("Invalid ID..");
            }

            System.out.print("Do you want to search another order? (y/n) : ");
            String response = scanner.nextLine();
            continueSearch = "y".equalsIgnoreCase(response);
        } while (continueSearch);
    }

    class OrderDetails {
        String phoneNumber;
        String size;
        int quantity;
        double amount;
        String status;

        OrderDetails(String phoneNumber, String size, int quantity, double amount, String status) {
            this.phoneNumber = phoneNumber;
            this.size = size;
            this.quantity = quantity;
            this.amount = amount;
            this.status = status;
        }
    }

    private void viewReports() {
        boolean continueViewing = true;
        while (continueViewing) {
            System.out.println("[1] Customer Reports");
            System.out.println("[2] Item Reports");
            System.out.println("[3] Orders Reports");
            System.out.print("Enter option: ");

            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    viewCustomerReports();
                    break;
                case "2":
                    viewItemReports();
                    break;
                case "3":
                    viewOrdersReports();
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
                    continue;
            }

            System.out.print("Do you want to view another report type? (y/n) : ");
            String response = scanner.nextLine();
            continueViewing = "y".equalsIgnoreCase(response);
        }
    }

    private void viewCustomerReports() {
        boolean continueViewing = true;
        while (continueViewing) {
            System.out.println("[1] Best in Customers");
            System.out.println("[2] View Customers");
            System.out.println("[3] All Customer Report");
            System.out.print("Enter option: ");
            
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    bestInCustomers();
                    break;
                case "2":
                    viewCustomers();
                    break;
                case "3":
                    allCustomerReport();
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
                    continue;
            }
    
            System.out.print("Do you want to view another customer report? (y/n): ");
            String response = scanner.nextLine();
            continueViewing = "y".equalsIgnoreCase(response);
        }
    }
    
    private void bestInCustomers() {
        System.out.println("Best in Customers Report Functionality Coming Soon...");
        // Logic for calculating and displaying the best customers based on some criteria
    }
    
    private void viewCustomers() {
        System.out.println("View Customers Functionality Coming Soon...");
        // Logic for displaying a list of all customers
    }
    
    private void allCustomerReport() {
        System.out.println("All Customer Report Functionality Coming Soon...");
        // Logic for displaying a comprehensive report on all customers
    }

    private void viewItemReports() {
        System.out.println("Item Reports Functionality Coming Soon...");
        // Here you would add the logic for viewing item-specific reports.
    }

    private void viewOrdersReports() {
        System.out.println("Orders Reports Functionality Coming Soon...");
        // Here you would add the logic for viewing order-specific reports.
    }

    private void setOrderStatus() {
        // Implement status update logic
    }

    private void deleteOrder() {
        // Implement delete logic
    }
}
