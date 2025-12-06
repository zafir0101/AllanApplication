package model.factory;

import model.AllanApplication;
import model.Factory;
import view.AllanMenuView;
import view.console.AllanMenuViewConsole;

/**
 * Concrete implementation of {@link model.Factory} responsible for creating Command Line Interface (CLI) components.
 * <p>
 * This factory instantiates console-based views {@link view.console.AllanMenuViewConsole} for the application.
 *
 * @author zafir
 * @see model.Factory
 */
public class ConsoleFactory extends Factory {

    @Override
    public AllanMenuView createAllanMenuView(AllanApplication application) {
        return new AllanMenuViewConsole(application);
    }
}