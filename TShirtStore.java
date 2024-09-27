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
    /*private String[] orderIds = new String[0];
    private String[] customerNames = new String[0];
    private String[] customerContacts = new String[0];
    private String[] tshirtSizes = new String[0];
    private int[] orderQuantities = new int[0];
    private double[] orderAmounts = new double[0];
    private String[] orderStatuses = new String[0];*/

    private static String[] orderIds = new String[0];
    private static String[] customerNames = new String[0];
    private static String[] customerContacts = new String[0];
    private static String[] tshirtSizes = new String[0];
    private static int[] orderQuantities = new int[0];
    private static double[] orderAmounts = new double[0];
    private static String[] orderStatuses = new String[0];
    //private List<Order> orders = new ArrayList<>();

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

    /*private void extendArrays(String orderId, String name, String contact, String size, int quantity, double amount) {
        orderIds = Arrays.copyOf(orderIds, orderIds.length + 1);
        customerNames = Arrays.copyOf(customerNames, customerNames.length + 1);
        customerContacts = Arrays.copyOf(customerContacts, customerContacts.length + 1);
        tshirtSizes = Arrays.copyOf(tshirtSizes, tshirtSizes.length + 1);
        orderQuantities = Arrays.copyOf(orderQuantities, orderQuantities.length + 1);
        orderAmounts = Arrays.copyOf(orderAmounts, orderAmounts.length + 1);
        orderStatuses = Arrays.copyOf(orderStatuses, orderStatuses.length + 1);

        int newIndex = orderIds.length - 1;
        orderIds[newIndex] = orderId;
        customerNames[newIndex] = name;
        customerContacts[newIndex] = contact;
        tshirtSizes[newIndex] = size;
        orderQuantities[newIndex] = quantity;
        orderAmounts[newIndex] = amount;
        orderStatuses[newIndex] = "processing";
    }*/

   private void extendArrays(String orderId, String name, String contact, String size, int quantity, double amount) {
        int newIndex = orderIds.length; // Declare newIndex at the beginning of the method
        orderIds = Arrays.copyOf(orderIds, newIndex + 1);
        customerNames = Arrays.copyOf(customerNames, newIndex + 1);
        customerContacts = Arrays.copyOf(customerContacts, newIndex + 1);
        tshirtSizes = Arrays.copyOf(tshirtSizes, newIndex + 1);
        orderQuantities = Arrays.copyOf(orderQuantities, newIndex + 1);
        orderAmounts = Arrays.copyOf(orderAmounts, newIndex + 1);
        orderStatuses = Arrays.copyOf(orderStatuses, newIndex + 1); // Use newIndex after it's declared

        // Assign values to the new index in each array
        orderIds[newIndex] = orderId;
        customerNames[newIndex] = name;
        customerContacts[newIndex] = contact;
        tshirtSizes[newIndex] = size;
        orderQuantities[newIndex] = quantity;
        orderAmounts[newIndex] = amount;
        orderStatuses[newIndex] = "processing"; // Set initial status for the new order
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
    
    /*private void bestInCustomers() {
        // Map to store customer data with total quantities and amounts
        Map<String, Integer> customerData = new HashMap<>();

        // Aggregate data from the order arrays
        for (int i = 0; i < customerContacts.length; i++) {
            String customerId = customerContacts[i];
            int quantity = orderQuantities[i];
            double pricePerUnit = getPricePerSize(tshirtSizes[i]);
            int amount = (int) (quantity * pricePerUnit);  // Cast to int, assuming amount is handled as integer

            customerData.put(customerId, customerData.getOrDefault(customerId, 0) + amount);
        }

        // Convert map to a list of entries
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(customerData.entrySet());

        // Bubble sort by value in descending order
        boolean sorted;
        do {
            sorted = true;
            for (int i = 0; i < entries.size() - 1; i++) {
                if (entries.get(i).getValue() < entries.get(i + 1).getValue()) {
                    // Swap entries
                    Map.Entry<String, Integer> temp = entries.get(i);
                    entries.set(i, entries.get(i + 1));
                    entries.set(i + 1, temp);
                    sorted = false;
                }
            }
        } while (!sorted);

        // Printing the header
        System.out.println("+---------------+----------------+");
        System.out.println("| Customer ID   | Total Amount   |");
        System.out.println("+---------------+----------------+");

        // Print each customer's aggregated data
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.printf("| %-13s | %14d |\n", entry.getKey(), entry.getValue());
        }

        System.out.println("+---------------+----------------+");

        // Option to return to the main menu
        System.out.print("To access the Main Menu, please enter 0: ");
        scanner.nextLine(); // Consume the next line to pause the display
    }

    private double getPricePerSize(String size) {
        switch (size) {
            case "XS": return 600;
            case "S": return 800;
            case "M": return 900;
            case "L": return 1000;
            case "XL": return 1100;
            case "XXL": return 1200;
            default: return 0; // Default price if size is unrecognized
        }
    }*/

   private void bestInCustomers() {
        // Map to store customer data with total quantities and amounts
        Map<String, int[]> customerData = new HashMap<>();

        // Aggregate data from the order arrays
        for (int i = 0; i < customerContacts.length; i++) {
            String customerId = customerContacts[i];
            int quantity = orderQuantities[i];
            double pricePerUnit = getPricePerSize(tshirtSizes[i]);
            int amount = (int) (quantity * pricePerUnit);  // Cast to int for simplicity

            if (!customerData.containsKey(customerId)) {
                customerData.put(customerId, new int[]{quantity, amount});
            } else {
                int[] existingData = customerData.get(customerId);
                existingData[0] += quantity;  // Increment the quantity
                existingData[1] += amount;    // Add to the amount
                customerData.put(customerId, existingData);
            }
        }

        // Convert map to a list of entries
        List<Map.Entry<String, int[]>> entries = new ArrayList<>(customerData.entrySet());

        // Bubble sort by total amount in descending order
        boolean sorted;
        do {
            sorted = true;
            for (int i = 0; i < entries.size() - 1; i++) {
                if (entries.get(i).getValue()[1] < entries.get(i + 1).getValue()[1]) {
                    // Swap entries
                    Map.Entry<String, int[]> temp = entries.get(i);
                    entries.set(i, entries.get(i + 1));
                    entries.set(i + 1, temp);
                    sorted = false;
                }
            }
        } while (!sorted);

        // Printing the header
        System.out.println("+---------------+---------+--------------+");
        System.out.println("| Customer ID   | All QTY | Total Amount |");
        System.out.println("+---------------+---------+--------------+");

        // Print each customer's aggregated data
        for (Map.Entry<String, int[]> entry : entries) {
            System.out.printf("| %-13s | %7d | %12d |\n", entry.getKey(), entry.getValue()[0], entry.getValue()[1]);
        }

        System.out.println("+---------------+---------+--------------+");

        // Option to return to the main menu
        System.out.print("To access the Main Menu, please enter 0: ");
        scanner.nextLine(); // Consume the next line to pause the display
    }

    private double getPricePerSize(String size) {
        switch (size) {
            case "XS": return 600;
            case "S": return 800;
            case "M": return 900;
            case "L": return 1000;
            case "XL": return 1100;
            case "XXL": return 1200;
            default: return 0; // Default price if size is unrecognized
        }
    }

    private void viewCustomers() {
        // Map to store customer data with total quantities and amounts
        Map<String, int[]> customerData = new HashMap<>();

        // Aggregate data from the order arrays
        for (int i = 0; i < customerContacts.length; i++) {
            String customerId = customerContacts[i];
            int quantity = orderQuantities[i];
            double amount = orderAmounts[i];

            if (!customerData.containsKey(customerId)) {
                customerData.put(customerId, new int[]{quantity, (int) amount}); // Cast to int for amount if needed or use double
            } else {
                int[] existingData = customerData.get(customerId);
                existingData[0] += quantity;
                existingData[1] += amount;
                customerData.put(customerId, existingData);
            }
        }

        // Printing the header
        System.out.println("+---------------+---------+--------------+");
        System.out.println("| Customer ID   | All QTY | Total Amount |");
        System.out.println("+---------------+---------+--------------+");

        // Print each customer's aggregated data
        for (Map.Entry<String, int[]> entry : customerData.entrySet()) {
            System.out.printf("| %-13s | %7d | %12d |\n", entry.getKey(), entry.getValue()[0], entry.getValue()[1]);
        }

        System.out.println("+---------------+---------+--------------+");

        // Option to return to the main menu
        System.out.print("To access the Main Menu, please enter 0: ");
        scanner.nextLine(); // Consume the next line to pause the display
    }
    
    private void allCustomerReport() {
        // Map to hold customer data, with nested maps for sizes and their quantities
        Map<String, Map<String, Integer>> customerSizes = new HashMap<>();
        Map<String, Double> customerTotals = new HashMap<>();

        // Process each order to populate the maps
        for (int i = 0; i < customerContacts.length; i++) {
            String customerId = customerContacts[i];
            String size = tshirtSizes[i];
            int quantity = orderQuantities[i];
            double pricePerUnit = getPricePerSize(size);
            double total = quantity * pricePerUnit;

            customerSizes.putIfAbsent(customerId, new HashMap<>());
            Map<String, Integer> sizes = customerSizes.get(customerId);
            sizes.put(size, sizes.getOrDefault(size, 0) + quantity);

            customerTotals.put(customerId, customerTotals.getOrDefault(customerId, 0.0) + total);
        }

        // Print the header
        System.out.println("+---------------+----+----+----+----+----+----+---------+");
        System.out.println("| Phone Number  | XS | S  | M  | L  | XL | XXL| Total   |");
        System.out.println("+---------------+----+----+----+----+----+----+---------+");

        // Print each customer's data
        for (String customerId : customerSizes.keySet()) {
            Map<String, Integer> sizes = customerSizes.get(customerId);
            System.out.printf("| %-13s | %2d | %2d | %2d | %2d | %2d | %2d | %7.2f |\n",
                customerId,
                sizes.getOrDefault("XS", 0),
                sizes.getOrDefault("S", 0),
                sizes.getOrDefault("M", 0),
                sizes.getOrDefault("L", 0),
                sizes.getOrDefault("XL", 0),
                sizes.getOrDefault("XXL", 0),
                customerTotals.get(customerId));
        }

        System.out.println("+---------------+----+----+----+----+----+----+---------+");
        System.out.print("To access the Main Menu, please enter 0: ");
        scanner.nextLine(); // To pause the display
    }

    /*private double getPricePerSize(String size) {
        switch (size) {
            case "XS": return 600;
            case "S": return 800;
            case "M": return 900;
            case "L": return 1000;
            case "XL": return 1100;
            case "XXL": return 1200;
            default: return 0; // Default price if size is unrecognized
        }
    }*/

    private void viewItemReports() {
        System.out.println("[1] Best Selling Categories Sorted by QTY");
        System.out.println("[2] Best Selling Categories Sorted by Amount");
        System.out.print("Enter option: ");
        String option = scanner.nextLine();

        switch (option) {
            case "1":
                sortCategoriesByQuantity();
                break;
            case "2":
                sortCategoriesByAmount();
                break;
            default:
                System.out.println("Invalid option, please try again.");
                viewItemReports(); // Recursive call to re-display options if invalid input
        }
    }

    /*private void sortCategoriesByQuantity() {
        // Collect and sort data by quantity
        Map<String, Integer> categoryData = new HashMap<>();
        for (int i = 0; i < tshirtSizes.length; i++) {
            categoryData.merge(tshirtSizes[i], orderQuantities[i], Integer::sum);
        }
        // Print sorted data by quantity
        categoryData.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .forEach(e -> System.out.println("Category: " + e.getKey() + ", QTY: " + e.getValue()));
    }

    private void sortCategoriesByAmount() {
        // Collect and sort data by total amount
        Map<String, Double> categoryData = new HashMap<>();
        for (int i = 0; i < tshirtSizes.length; i++) {
            double amount = orderQuantities[i] * orderAmounts[i]; // Assuming orderAmounts holds the price per unit
            categoryData.merge(tshirtSizes[i], amount, Double::sum);
        }
        // Print sorted data by total amount
        categoryData.entrySet().stream()
            .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
            .forEach(e -> System.out.println("Category: " + e.getKey() + ", Total: " + String.format("%.2f", e.getValue())));
    }*/

    private void sortCategoriesByQuantity() {
        // Collect data
        Map<String, Integer> categoryData = new HashMap<>();
        Map<String, Double> categoryAmounts = new HashMap<>();
        for (int i = 0; i < tshirtSizes.length; i++) {
            categoryData.merge(tshirtSizes[i], orderQuantities[i], Integer::sum);
            categoryAmounts.merge(tshirtSizes[i], orderQuantities[i] * orderAmounts[i], Double::sum);
        }

        // Print header
        System.out.println("+------+-----+------------+");
        System.out.println("| Size | QTY | Total Amount|");
        System.out.println("+------+-----+------------+");

        // Print sorted data by quantity
        categoryData.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .forEach(e -> System.out.printf("| %-4s | %3d | %10.2f |\n", e.getKey(), e.getValue(), categoryAmounts.get(e.getKey())));

        System.out.println("+------+-----+------------+");
        System.out.print("To access the Main Menu, please enter 0: ");
    }

    private void sortCategoriesByAmount() {
        // Collect data
        Map<String, Double> categoryAmounts = new HashMap<>();
        Map<String, Integer> categoryData = new HashMap<>();
        for (int i = 0; i < tshirtSizes.length; i++) {
            categoryData.merge(tshirtSizes[i], orderQuantities[i], Integer::sum);
            categoryAmounts.merge(tshirtSizes[i], orderQuantities[i] * orderAmounts[i], Double::sum);
        }

        // Print header
        System.out.println("+------+-----+------------+");
        System.out.println("| Size | QTY | Total Amount|");
        System.out.println("+------+-----+------------+");

        // Print sorted data by total amount
        categoryAmounts.entrySet().stream()
            .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
            .forEach(e -> System.out.printf("| %-4s | %3d | %10.2f |\n", e.getKey(), categoryData.get(e.getKey()), e.getValue()));

        System.out.println("+------+-----+------------+");
        System.out.print("To access the Main Menu, please enter 0: ");
    }

    private void viewOrdersReports() {
        System.out.println("Select report type:");
        System.out.println("[1] All Orders");
        System.out.println("[2] Orders By Amount");
        System.out.print("Enter option: ");
        String option = scanner.nextLine();

        switch (option) {
            case "1":
                displayAllOrders();
                break;
            case "2":
                displayOrdersByAmount();
                break;
            default:
                System.out.println("Invalid option, please try again.");
        }
    }

    private void displayAllOrders() {
        // Sorting orders by ID in descending order using a simple selection sort for demonstration
        for (int i = 0; i < orderIds.length - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < orderIds.length; j++) {
                if (orderIds[j].compareTo(orderIds[maxIndex]) > 0) {
                    maxIndex = j;
                }
            }
            swapOrders(i, maxIndex);
        }
        printOrders();
    }

    private void displayOrdersByAmount() {
        // Sorting orders by amount in descending order
        for (int i = 0; i < orderAmounts.length - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < orderAmounts.length; j++) {
                if (orderAmounts[j] > orderAmounts[maxIndex]) {
                    maxIndex = j;
                }
            }
            swapOrders(i, maxIndex);
        }
        printOrders();
    }

    private void swapOrders(int i, int j) {
        // Swapping all related order information
        String tempId = orderIds[i];
        orderIds[i] = orderIds[j];
        orderIds[j] = tempId;

        String tempName = customerNames[i];
        customerNames[i] = customerNames[j];
        customerNames[j] = tempName;

        String tempContact = customerContacts[i];
        customerContacts[i] = customerContacts[j];
        customerContacts[j] = tempContact;

        String tempSize = tshirtSizes[i];
        tshirtSizes[i] = tshirtSizes[j];
        tshirtSizes[j] = tempSize;

        int tempQty = orderQuantities[i];
        orderQuantities[i] = orderQuantities[j];
        orderQuantities[j] = tempQty;

        double tempAmt = orderAmounts[i];
        orderAmounts[i] = orderAmounts[j];
        orderAmounts[j] = tempAmt;
    }

    private void printOrders() {
        System.out.println("+---------+--------------+------+-----+--------+");
        System.out.println("| Order ID | Customer ID | Size | QTY | Amount |");
        System.out.println("+---------+--------------+------+-----+--------+");
        for (int i = 0; i < orderIds.length; i++) {
            System.out.printf("| %8s | %12s | %4s | %3d | %6.2f |\n",
                    orderIds[i], customerContacts[i],
                    tshirtSizes[i], orderQuantities[i], 
                    orderAmounts[i]);
        }
        System.out.println("+---------+--------------+------+-----+--------+");
        System.out.print("To access the Main Menu, please enter 0: ");
        scanner.nextLine();
    }

    /*private void displayAllOrders() {
        orders.sort((a, b) -> b.getOrderID().compareTo(a.getOrderID())); // Sort by Order ID in descending order
        printOrders("All Orders Sorted by ID");
    }

    private void displayOrdersByAmount() {
        orders.sort((a, b) -> Double.compare(b.getAmount(), a.getAmount())); // Sort by Amount in descending order
        printOrders("All Orders Sorted by Amount");
    }

    private void printOrders(String title) {
        System.out.println(title);
        System.out.println("+---------+--------------+------+-----+--------+--------+");
        System.out.println("| Order ID | Customer ID | Size | QTY | Amount | Status |");
        System.out.println("+---------+--------------+------+-----+--------+--------+");
        for (Order order : orders) {
            System.out.printf("| %8s | %12s | %4s | %3d | %6.2f | %6s |\n",
                    order.getOrderID(), order.getCustomerID(),
                    order.getSize(), order.getQuantity(), 
                    order.getAmount(), order.getStatus());
        }
        System.out.println("+---------+--------------+------+-----+--------+--------+");
        System.out.print("To access the Main Menu, please enter 0: ");
        scanner.nextLine();
    }*/

    private void setOrderStatus() {
        System.out.print("Enter Order ID: ");
        String orderId = scanner.nextLine();
        int index = searchbyorderid(orderId);

        if (index == -1) {
            System.out.println("Order not found.");
        } else {
            System.out.println("Phone Number : " + customerContacts[index]);
            System.out.println("Size         : " + tshirtSizes[index]);
            System.out.println("QTY          : " + orderQuantities[index]);
            System.out.println("Amount       : " + orderAmounts[index]);
            System.out.println("Status       : " + orderStatuses[index]);

            if (!orderStatuses[index].equals("delivered")) {
                System.out.print("Do you want to change this order status? (y/n): ");
                String confirm = scanner.nextLine();
                if (confirm.equalsIgnoreCase("y")) {
                    updateOrderStatus(index);
                }
            } else {
                System.out.println("Can't change order status, Order already delivered!");
            }
        }

        System.out.print("Do you want to change another order status? (y/n): ");
        String anotherChange = scanner.nextLine();
        if (anotherChange.equalsIgnoreCase("y")) {
            setOrderStatus(); // Recursive call to handle another status change
        }
    }

    private void updateOrderStatus(int index) {
        System.out.println("Select the new status:");
        System.out.println("[1] Order Delivering");
        System.out.println("[2] Order Delivered");
        System.out.print("Enter option: ");
        String option = scanner.nextLine();

        switch (option) {
            case "1":
                orderStatuses[index] = "delivering";
                break;
            case "2":
                orderStatuses[index] = "delivered";
                break;
            default:
                System.out.println("Invalid option. No changes made.");
                break;
        }

        System.out.println("Updated Order Status: " + orderStatuses[index]);
    }

    public void deleteOrder() {
        boolean continueDeletion;
        char choice;

        do {
            System.out.print("\nEnter Order ID to delete: ");
            String orderId = scanner.next();

            int index = searchbyorderid(orderId);
            if (index == -1) {
                System.out.println("\nOrder Not found.");
            } else {
                printorder(index);

                System.out.print("\nDo you really want to delete this order? (Y/N): ");
                choice = scanner.next().charAt(0);

                if (Character.toUpperCase(choice) == 'Y') {
                    removeorder(index);
                    System.out.println("\nOrder Deleted Successfully.");
                }
            }

            System.out.print("\nDo you want to delete another Order? (Y/N): ");
            choice = scanner.next().charAt(0);

            if (Character.toUpperCase(choice) == 'N') {
                break;
            }
        } while (true);
    }

    private int searchbyorderid(String orderId) {
        for (int i = 0; i < orderIds.length; i++) {
            if (orderIds[i].equals(orderId)) {
                return i;
            }
        }
        return -1;  // Return -1 if the order ID is not found
    }

    private void printorder(int index) {
        if (index >= 0 && index < orderIds.length) {
            System.out.println("Order ID: " + orderIds[index]);
            System.out.println("Customer Contact: " + customerContacts[index]);
            System.out.println("T-Shirt Size: " + tshirtSizes[index]);
            System.out.println("Quantity: " + orderQuantities[index]);
            System.out.println("Amount: " + orderAmounts[index]);
        } else {
            System.out.println("Invalid index provided for printing order.");
        }
    }

    public static void removeorder(int index) {
        String[] temporderIds = new String[orderIds.length - 1];
        String[] tempcustomerContacts = new String[customerContacts.length - 1];
        String[] temptshirtSizes = new String[tshirtSizes.length - 1];
        int[] temporderQuantities = new int[orderQuantities.length - 1];
        double[] temporderAmounts = new double[orderAmounts.length - 1];

        System.arraycopy(orderIds, 0, temporderIds, 0, index);
        System.arraycopy(customerContacts, 0, tempcustomerContacts, 0, index);
        System.arraycopy(tshirtSizes, 0, temptshirtSizes, 0, index);
        System.arraycopy(orderQuantities, 0, temporderQuantities, 0, index);
        System.arraycopy(orderAmounts, 0, temporderAmounts, 0, index);

        if (index < orderIds.length - 1) {
            System.arraycopy(orderIds, index + 1, temporderIds, index, orderIds.length - index - 1);
            System.arraycopy(customerContacts, index + 1, tempcustomerContacts, index, customerContacts.length - index - 1);
            System.arraycopy(tshirtSizes, index + 1, temptshirtSizes, index, tshirtSizes.length - index - 1);
            System.arraycopy(orderQuantities, index + 1, temporderQuantities, index, orderQuantities.length - index - 1);
            System.arraycopy(orderAmounts, index + 1, temporderAmounts, index, orderAmounts.length - index - 1);
        }

        orderIds = temporderIds;
        customerContacts = tempcustomerContacts;
        tshirtSizes = temptshirtSizes;
        orderQuantities = temporderQuantities;
        orderAmounts = temporderAmounts;
    }
}
