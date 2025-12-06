package view.console;

import model.PersistenceService;
import model.customer.Customer;
import model.AllanApplication;
import view.AllanCustomerView;
import view.AllanListCustomerView;
import view.AllanMenuView;
import java.io.IOException;

import static view.console.assistant.CommandClass.getCommand;

/**
 * Console-based implementation of the {@link view.AllanMenuView}.
 * <p>
 * This class handles the main menu interaction using standard input/output.
 * It displays options as text and captures user commands via {@link view.console.assistant.CommandClass}.
 *
 * @author zafir
 * @see view.AllanMenuView
 * @see view.console.assistant.CommandClass
 */
public class AllanMenuViewConsole implements AllanMenuView {
    private final AllanApplication application;
    private static final PersistenceService<AllanApplication> saver = new PersistenceService<>();

    public AllanMenuViewConsole(AllanApplication application) {
        this.application = application;
    }

        @Override
        public void showMenu () {
        boolean running = true;
        while (running) {
            System.out.println("");
            System.out.println("=== Allan Menu ===");
            System.out.println("1. Register Customer");
            System.out.println("2. Remove Customer");
            System.out.println("3. View Customers");
            System.out.println("4. Select Customer");
            System.out.println("5. Save Data");
            System.out.println("6. Save and Exit");
            System.out.print("Please select an option: ");

            String command = getCommand();
            System.out.println("");

            switch (command) {
                case "1":
                    AllanRegisterCustomerViewConsole registerCustomerView = new AllanRegisterCustomerViewConsole(application);
                    registerCustomerView.showRegisterCustomerForm();
                    break;

                case "2":
                    System.out.print("Enter Customer name to remove: ");
                    String customerName = getCommand();
                    Customer customer = application.findCustomerByName(customerName);
                    if (customer == null)
                        System.out.println("Customer name invalid");
                    else {
                        application.removeCustomer(customer);
                        System.out.println("Customer " + customerName + " removed successfully.");
                    }

                case "3":
                    AllanListCustomerView listCustomerView = new AllanListCustomerViewConsole(application);
                    listCustomerView.showClientList();
                    break;

                case "4":
                    System.out.print("Enter Customer name to select: ");
                    customerName = getCommand();
                    customer = application.findCustomerByName(customerName);
                    if (customer == null)
                        System.out.println("Customer name invalid");
                    else {
                        AllanCustomerView customerView = new AllanCustomerViewConsole(customer);
                        customerView.showCustomerOptions();
                    }
                    break;

                case "5":

                    try {
                        saver.save(application, "allan_db.ser");
                        System.out.println("Data saved successfully!");
                    } catch (IOException e) {
                        System.out.println("Error saving data: " + e.getMessage());
                    }
                    break;

                case "6":
                    try {
                        saver.save(application, "allan_db.ser");
                        System.out.println("Data saved successfully!");
                    } catch (IOException e) {
                        System.out.println("Error saving data: " + e.getMessage());
                    }
                    System.out.println("Exiting the application. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
}
