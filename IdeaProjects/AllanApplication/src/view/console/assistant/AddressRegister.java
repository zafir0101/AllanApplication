package view.console.assistant;

import model.types.Address;

import static view.console.assistant.CommandClass.getCommand;

/**
 * Helper class responsible for collecting and validating address information in the console.
 * <p>
 * It prompts the user sequentially for Street, Number, City, and State, handling
 * type conversions and validations required to instantiate an {@link model.types.Address}.
 *
 * @author zafir
 * @see model.types.Address
 */
public class AddressRegister {
    public static Address registerAddress() {
        System.out.println("\n--- Address Information ---");

        System.out.print("Street: ");
        String street = getCommand();

        System.out.print("Number: ");
        int number;
        try {
            number = Integer.parseInt(CommandClass.getCommand());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Address number must be a valid integer.");
        }

        System.out.print("City: ");
        String city = CommandClass.getCommand();

        System.out.print("State (UF, ex: SP): ");
        String state = CommandClass.getCommand();

        return new Address(street, number, city, state);
    }
}
