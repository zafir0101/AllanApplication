package view.console;

import model.AllanApplication;
import model.customer.*;
import model.types.Address;
import model.customer.taxregime.*;
import model.types.PhoneNumber;
import view.AllanRegisterCustomerView;
import view.console.assistant.AddressRegister;
import view.console.assistant.PhoneRegister;

import static view.console.assistant.CommandClass.getCommand;

/**
 * Console-based implementation of the customer registration form.
 * <p>
 * Collects data step-by-step using text prompts and validates input immediately.
 * Uses helper classes like {@link view.console.assistant.AddressRegister} for complex fields.
 *
 * @author zafir
 * @see view.console.assistant.AddressRegister
 */
public class AllanRegisterCustomerViewConsole implements AllanRegisterCustomerView {
    private final AllanApplication application;

    public AllanRegisterCustomerViewConsole(AllanApplication application) {
        this.application = application;
    }

    @Override
    public void showRegisterCustomerForm() {
        System.out.println("=== Register Client View ===");

        try {
            System.out.print("Enter client name: ");
            String name = getCommand();

            Address address = AddressRegister.registerAddress();

            PhoneNumber phone = PhoneRegister.registerPhone();

            System.out.println("\n--- Tax Regime Selection ---");
            System.out.println("1 - Simples Nacional");
            System.out.println("2 - Lucro Presumido");
            System.out.println("3 - Lucro Real");
            System.out.print("Select option: ");
            String regimeOption = getCommand();

            TaxRegime regime = switch (regimeOption) {
                case "1" -> new SimplesNacional();
                case "2" -> new LucroPresumido();
                case "3" -> new LucroReal();
                default -> throw new IllegalArgumentException("\nInvalid Tax Regime option.");
            };

            System.out.println("\n--- Customer Type ---");
            System.out.println("1 - Business (Comércio/Revenda)");
            System.out.println("2 - Industry (Indústria/Fábrica)");
            System.out.print("Select option: ");
            String typeOption = getCommand();

            Customer newCustomer = switch (typeOption) {
                case "1" -> new BusinessCustomer(name, address, phone, regime);
                case "2" -> new IndustryCustomer(name, address, phone, regime);
                default -> throw new IllegalArgumentException("\nInvalid Customer Type option.");
            };

            System.out.println("\nCustomer Registered Successfully!");
            System.out.println(newCustomer);

            application.addCustomer(newCustomer);

        } catch (IllegalArgumentException e) {

            System.out.println("\nError registering customer: " + e.getMessage());
            System.out.println("Please try again.");

        } catch (Exception e) {
            System.out.println("\nUnexpected error: " + e.getMessage());
        }
    }
}
