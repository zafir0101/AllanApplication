package view.console;

import model.customer.Customer;
import model.stock.Product;
import model.stock.ProductWithQuantity;
import view.AllanCustomerView;
import view.console.assistant.AddressRegister;
import view.console.assistant.PhoneRegister;
import java.util.List;

import static view.console.assistant.CommandClass.getCommand;

/**
 * Console-based implementation of the specific Customer Management Menu.
 * <p>
 * This view provides a sub-menu for a specific {@link model.customer.Customer}, allowing operations
 * such as:
 * <ul>
 * <li>Viewing and adding products to the customer's stock.</li>
 * <li>Analyzing financial data (costs and estimated profit).</li>
 * <li>Updating profile information.</li>
 * </ul>
 *
 * @author zafir
 * @see view.AllanCustomerView
 * @see model.customer.Customer
 */
public class AllanCustomerViewConsole implements AllanCustomerView {
    private final Customer customer;

    public AllanCustomerViewConsole(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void showCustomerOptions() {
        boolean running = true;

        while (running) {
            System.out.println("\n=== Customer Options: " + customer.getName() + " ===");
            System.out.println("1. View Stock Products");
            System.out.println("2. View Costumer Financials");
            System.out.println("3. Add Product");
            System.out.println("4. Remove Product");
            System.out.println("5. Update Profile");
            System.out.println("6. View Profile");
            System.out.println("7. Logout");
            System.out.print("Select an option: ");

            String command = getCommand();

            switch (command) {
                case "1":
                    viewProducts();
                    break;
                case "2":
                    viewCustomerFinancials();
                    break;
                case "3":
                    addProduct();
                    break;
                case "4":
                    removeProduct();
                    break;
                case "5":
                    updateProfile();
                    break;
                case "6":
                    viewProfile();
                    break;
                case "7":
                    System.out.println("\nLogging out...");
                    running = false;
                    break;
                default:
                    System.out.println("\nInvalid option. Please try again.");
                    break;
            }
        }
    }

    private void viewProducts() {
        System.out.println("\n--- Products List ---");
        List<ProductWithQuantity> stockList = customer.getStock().getProductWithQuantityList(); //

        if (stockList.isEmpty()) {
            System.out.println("No products found in stock.");
        } else {
            for (ProductWithQuantity item : stockList) {
                System.out.println(item); //
            }
        }
    }

    private void viewCustomerFinancials() {
        System.out.println("\n--- Stock Financials ---");
        System.out.printf("Total Stock Price: %.2f%n", customer.getStock().getTotalStockPrice());
        System.out.println("Total Stock Cost: " + customer.getStock().getTotalStockCost());
        System.out.printf("Total Costumer Cost: %.2f%n", customer.getTotalCost());
        System.out.println("Total Costumer Profit: " + customer.getProfit());

    }

    private void addProduct() {
        try {
            System.out.println("\n--- Add New Product ---");
            System.out.print("Name: ");
            String name = getCommand();

            System.out.print("Description: ");
            String desc = getCommand();

            System.out.print("Price (Sell Price): ");
            double price = Double.parseDouble(getCommand());

            System.out.print("Cost (Buy Price): ");
            double cost = Double.parseDouble(getCommand());

            System.out.print("Quantity: ");
            int quantity = Integer.parseInt(getCommand());

            Product product = new Product(name, desc, price, cost); //
            ProductWithQuantity item = new ProductWithQuantity(product, quantity); //

            customer.addProductToStock(item); //
            System.out.println("\nProduct added successfully!");

        } catch (NumberFormatException e) {
            System.out.println("\nError: Invalid number format.");
        } catch (IllegalArgumentException e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }

    private void removeProduct() {
        System.out.println("\n--- Remove Product ---");
        if (customer.getStock().getProductWithQuantityList().isEmpty()) {
            System.out.println("\nStock is empty.");
            return;
        }

        System.out.print("Enter the exact name of the product to remove: ");
        String nameToRemove = getCommand();

        ProductWithQuantity foundItem = null;
        for (ProductWithQuantity item : customer.getStock().getProductWithQuantityList()) {
            if (item.getProduct().getName().equalsIgnoreCase(nameToRemove)) {
                foundItem = item;
                break;
            }
        }

        if (foundItem != null) {
            customer.removeProductFromStock(foundItem);
            System.out.println("\nProduct removed successfully.");
        } else {
            System.out.println("\nProduct not found.");
        }
    }

    private void viewProfit() {
        System.out.println("\n--- Profit Analysis ---");
        System.out.printf("Current Estimated Profit: %.2f%n", customer.getProfit()); //
    }

    private void updateProfile() {
        System.out.println("\n--- Update Profile ---");
        System.out.println("1. Change Name");
        System.out.println("2. Change Address");
        System.out.println("3. Change Phone");
        System.out.print("Select: ");
        String op = getCommand();

        switch (op) {
            case "1":
                System.out.print("New Name: ");
                customer.setName(getCommand());
                System.out.println("\nName updated.");
                break;
            case "2":
                try {
                    customer.setAddress(AddressRegister.registerAddress()); //
                    System.out.println("\nAddress updated.");
                } catch (Exception e) {
                    System.out.println("\nError updating address: " + e.getMessage());
                }
                break;
            case "3":
                try {
                    customer.setPhoneNumber(PhoneRegister.registerPhone()); //
                    System.out.println("\nPhone updated.");
                } catch (Exception e) {
                    System.out.println("\nError updating phone: " + e.getMessage());
                }
                break;
            default:
                System.out.println("\nInvalid option.");
        }
    }

    public void viewProfile() {
        System.out.println("\n--- Customer Profile ---");
        System.out.println("Name: " + customer.getName());
        System.out.println("Address: " + customer.getAddress());
        System.out.println("Phone: " + customer.getPhoneNumber());
        System.out.println("Tax Regime: " + customer.getTaxRegime());
    }

}