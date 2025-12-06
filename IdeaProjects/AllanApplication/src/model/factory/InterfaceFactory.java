package model.factory;

import model.AllanApplication;
import view.AllanMenuView;

/**
 * Defines the contract for the Abstract Factory responsible for creating the application's User Interface (UI).
 * <p>
 * Implementing classes (such as {@link model.factory.GuiFactory} and {@link model.factory.ConsoleFactory})
 * must provide concrete implementations for creating the Menu, Registration, and Client views.
 * This ensures the application can switch between different interface modes (Terminal and GUI) seamlessly.
 *<p>
 * <i>Note that typically only the Menu creation is invoked explicitly at the application start.
 * The calls to create other interfaces (Registration, Client View) occur internally within the
 * application flow as the user navigates through the system.</i>
 *
 * @author zafir
 * @see model.Factory
 * @see model.factory.GuiFactory
 * @see model.factory.ConsoleFactory
 */
public interface InterfaceFactory {
    AllanMenuView createAllanMenuView(AllanApplication application);
}
