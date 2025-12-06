package model;
import java.io.Serializable;
import java.util.List;
import java.util.Collections;
import model.customer.Customer;

/**
 * This class represents the application.
 * <p>
 * The AllanApplication is responsible for managing the registry of {@link model.customer.Customer}
 * objects. It serves as the root object for serialization and persistence.
 *
 * @author zafir
 * @see model.customer.Customer
 */
public class AllanApplication implements Serializable {
    private List<Customer> customers;

    public AllanApplication() {
        this.customers = new java.util.ArrayList<>();
    }

    public List<Customer> getCustomers() {
        return Collections.unmodifiableList(customers);
    }

    public Customer findCustomerByName(String name) {
        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(name)) {
                return customer;
            }
        }
        return null;
    }

    public void addCustomer(Customer customer) {
        this.customers.add(customer);
    }

    public void removeCustomer(Customer customer) {
        if (!this.customers.remove(customer))
            throw new IllegalArgumentException("Customer not found.");
    }
}
