import java.util.HashMap;
import java.util.Map;

public class RecordManager {
    private Map<String, Customer> customers = new HashMap<>();

    public void addCustomer(Customer customer) {
        customers.put(customer.getId(), customer);
    }

    public Customer getCustomer(String customerId) {
        return customers.get(customerId);
    }
}
