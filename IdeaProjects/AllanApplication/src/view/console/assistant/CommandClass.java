package view.console.assistant;

import java.util.Scanner;

/**
 * Utility class used to capture user input from the standard console.
 *
 * @author zafir
 */
public class CommandClass {
    public static String getCommand() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
}
