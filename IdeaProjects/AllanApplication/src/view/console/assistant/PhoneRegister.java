package view.console.assistant;

import model.types.PhoneNumber;

/**
 * Helper class responsible for collecting and validating phone contact information in the console.
 * <p>
 * It prompts the user for the Area Code (DDD) and the Phone Number separately
 * to instantiate a {@link model.types.PhoneNumber}.
 *
 * @author zafir
 * @see model.types.PhoneNumber
 */
public class PhoneRegister {

    public static PhoneNumber registerPhone() {
        System.out.println("\n--- Contact Information ---");
                System.out.print("DDD (2 digits): ");
        String ddd = CommandClass.getCommand();

                System.out.print("Phone Number (8 or 9 digits): ");
        String phoneNumberStr = CommandClass.getCommand();
        return new PhoneNumber(ddd, phoneNumberStr);}
    }
