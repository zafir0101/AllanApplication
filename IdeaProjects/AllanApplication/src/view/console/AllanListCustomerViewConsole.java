package view.console;

import model.AllanApplication;
import model.customer.Customer;
import view.AllanListCustomerView;

/**
 * Console-based implementation for listing registered customers.
 * <p>
 * This view iterates through the customer list managed by {@link model.AllanApplication}
 * and prints their summary to the standard output.
 *
 * @author zafir
 * @see view.AllanListCustomerView
 */
public class AllanListCustomerViewConsole implements AllanListCustomerView {
    private final AllanApplication application;

    public AllanListCustomerViewConsole(AllanApplication application) {
        this.application = application;
    }

    @Override
    public void showClientList() {
        System.out.println("=== Client List ===");
        if (application.getCustomers().isEmpty()) {
            System.out.println("No customers registered.");
        } else {
            for (Customer customer : application.getCustomers()) {
                System.out.println(customer);
            }
        }
    }
}
