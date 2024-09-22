import java.util.Scanner;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;

public class TShirtStore {
    //private Scanner scanner;
    private int lastOrderNumber = 10000; // Assuming the last order number starts from 10000.

    // Arrays for storing information temporarily
    private Scanner scanner = new Scanner(System.in);
    private String[] orderIds = new String[0];
    private String[] customerNames = new String[0];
    private String[] customerContacts = new String[0];
    private String[] tshirtSizes = new String[0];
    private int[] orderQuantities = new int[0];
    private double[] orderAmounts = new double[0];
    private String[] orderStatuses = new String[0];

    public TShirtStore() {
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        TShirtStore store = new TShirtStore();
        store.run();

        /*RecordManager manager = new RecordManager();
        manager.addCustomer(new Customer("001", "Alice"));
        Customer found = manager.getCustomer("001");

        if (found != null) {
            System.out.println("Customer found: " + found.getName());
        } else {
            System.out.println("Customer not found.");
        }*/
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

            System.out.print("Enter Customer Name: ");
            String customerName = scanner.nextLine();

            String customerContact = getValidContactNumber();
            String tshirtSize = getValidTshirtSize();
            int quantity = getValidQuantity();
            double totalAmount = calculateTotalAmount(tshirtSize, quantity);

            System.out.println("Total Amount: " + totalAmount);
            extendArrays(orderID, customerName, customerContact, tshirtSize, quantity, totalAmount);

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

    private void extendArrays(String orderId, String name, String contact, String size, int quantity, double amount) {
        orderIds = Arrays.copyOf(orderIds, orderIds.length + 1);
        customerNames = Arrays.copyOf(customerNames, customerNames.length + 1);
        customerContacts = Arrays.copyOf(customerContacts, customerContacts.length + 1);
        tshirtSizes = Arrays.copyOf(tshirtSizes, tshirtSizes.length + 1);
        orderQuantities = Arrays.copyOf(orderQuantities, orderQuantities.length + 1);
        orderAmounts = Arrays.copyOf(orderAmounts, orderAmounts.length + 1);

        int newIndex = orderIds.length - 1;
        orderIds[newIndex] = orderId;
        customerNames[newIndex] = name;
        customerContacts[newIndex] = contact;
        tshirtSizes[newIndex] = size;
        orderQuantities[newIndex] = quantity;
        orderAmounts[newIndex] = amount;
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
                System.out.println("Invalid input.");
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
        boolean found = false;
        double totalAmount = 0;
        System.out.println("| Size | QTY | Amount |");
        System.out.println("-----------------------");

        for (int i = 0; i < customerContacts.length; i++) {
            if (customerContacts[i].equals(phoneNumber)) {
                found = true;
                System.out.printf("| %s | %d | %.2f |\n", tshirtSizes[i], orderQuantities[i], orderAmounts[i]);
                totalAmount += orderAmounts[i];
            }
        }

        if (!found) {
            System.out.println("No orders found for this customer.");
        } else {
            System.out.printf("Total Amount: %.2f\n", totalAmount);
        }
    }

    private void searchOrder() {
        boolean continueSearch = false;
        do {
            System.out.print("Enter order ID: ");
            String orderId = scanner.nextLine();

            boolean found = false;
            for (int i = 0; i < orderIds.length; i++) {
                if (orderIds[i].equals(orderId)) {
                    found = true;
                    // Assuming status is also stored in an array or can be derived
                    System.out.println("Phone Number : " + customerContacts[i]);
                    System.out.println("Size         : " + tshirtSizes[i]);
                    System.out.println("QTY          : " + orderQuantities[i]);
                    System.out.println("Amount       : " + orderAmounts[i]);
                    System.out.println("Status       : " + getOrderStatus(i)); // Implement getOrderStatus method based on your application logic
                    break;
                }
            }

            if (!found) {
                System.out.println("Invalid ID..");
            }

            System.out.print("Do you want to search another order? (y/n) : ");
            String response = scanner.nextLine();
            continueSearch = "y".equalsIgnoreCase(response);
        } while (continueSearch);
    }

    private String getOrderStatus(int index) {
        // This method needs to be implemented based on how status is managed in your arrays
        // For simplicity, assuming an array 'orderStatuses' exists
        // return orderStatuses[index];
        return "Processing"; // Placeholder response
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
        boolean continueDeletion = false;
        try {
            if (orderIds.length == 0) {
                System.out.println("No orders available to delete.");
                return;
            }
        
            do {
                System.out.print("Enter order ID: ");
                String orderId = scanner.nextLine();
                int index = findOrderIndexById(orderId);
        
                if (index == -1) {
                    System.out.println("Invalid ID.");
                    System.out.println("No order with ID " + orderId + " found.");
                } else {
                    // Display order details safely after confirming the index is valid
                    System.out.println("Phone Number : " + customerContacts[index]);
                    System.out.println("Size         : " + tshirtSizes[index]);
                    System.out.println("QTY          : " + orderQuantities[index]);
                    System.out.println("Amount       : " + orderAmounts[index]);
                    System.out.println("Status       : " + orderStatuses[index]);
        
                    System.out.print("Do you want to delete this order? (y/n): ");
                    String response = scanner.nextLine();
                    if ("y".equalsIgnoreCase(response)) {
                        try {
                            deleteOrderAtIndex(index);
                            System.out.println("Order Deleted..!");
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("An error occurred during order deletion: " + e.getMessage());
                        }
                    }
                }
        
                if (continueDeletion) {
                    System.out.print("Do you want to delete another order? (y/n): ");
                    continueDeletion = scanner.nextLine().equalsIgnoreCase("y");
                    if (continueDeletion && orderIds.length == 0) {
                        System.out.println("No more orders to delete.");
                        break;
                    }
                }
            } while (continueDeletion);
        } catch (Exception e) {
            System.out.println("An error occurred during order deletion: " + e.getMessage());
        }
    }

    private int findOrderIndexById(String orderId) {
        for (int i = 0; i < orderIds.length; i++) {
            if (orderIds[i].equals(orderId)) {
                return i;
            }
        }
        return -1; // return -1 if order ID is not found
    }

    private void deleteOrderAtIndex(int index) {
        try {
            orderIds = removeElement(orderIds, index);
            customerNames = removeElement(customerNames, index);
            customerContacts = removeElement(customerContacts, index);
            tshirtSizes = removeElement(tshirtSizes, index);
            orderQuantities = removeElement(orderQuantities, index);
            orderAmounts = removeElement(orderAmounts, index);
            orderStatuses = removeElement(orderStatuses, index);
            System.out.println("Order deleted successfully.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Failed to delete order: Invalid order index.");
        }
    }

    private String[] removeElement(String[] array, int index) {
        if (array == null || index < 0 || index >= array.length) {
            return array;
        }
        String[] anotherArray = new String[array.length - 1];
        System.arraycopy(array, 0, anotherArray, 0, index);
        System.arraycopy(array, index + 1, anotherArray, index, array.length - index - 1);
        return anotherArray;
    }
    
    private int[] removeElement(int[] array, int index) {
        if (array == null || index < 0 || index >= array.length) {
            return array;
        }
        int[] anotherArray = new int[array.length - 1];
        System.arraycopy(array, 0, anotherArray, 0, index);
        System.arraycopy(array, index + 1, anotherArray, index, array.length - index - 1);
        return anotherArray;
    }
    
    private double[] removeElement(double[] array, int index) {
        if (array == null || index < 0 || index >= array.length) {
            return array;
        }
        double[] anotherArray = new double[array.length - 1];
        System.arraycopy(array, 0, anotherArray, 0, index);
        System.arraycopy(array, index + 1, anotherArray, index, array.length - index - 1);
        return anotherArray;
    }
}
