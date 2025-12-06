package model;
import model.factory.InterfaceFactory;

/**
 * This class holds the instance of the {@link model.factory.GuiFactory} or
 * {@link model.factory.ConsoleFactory}, which are the concrete factories and are instantiated
 * according to the user preference.
 * <p>
 * This module is part of <b>Abstract Factory</b> pattern.
 *
 * @author zafir
 * @see model.factory.ConsoleFactory
 * @see model.factory.GuiFactory
 */
public abstract class Factory implements InterfaceFactory {
    private static Factory concreteFactory;

    public static Factory GetConcreteFactory() {
        return concreteFactory;
    }

    public static void setConcreteFactory(Factory argConcreteFactory) {
        concreteFactory = argConcreteFactory;
    }
}

