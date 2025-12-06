package model.factory;

import model.AllanApplication;
import model.Factory;
import view.AllanMenuView;
import view.gui.AllanMenuViewGui;

/**
 * Concrete implementation of {@link model.Factory} responsible for creating GUI components.
 * <p>
 * This factory instantiates Swing-based views {@link view.gui.AllanMenuViewGui} for the application.
 *
 * @author zafir
 * @see model.Factory
 */
public class GuiFactory extends Factory {

    @Override
    public AllanMenuView createAllanMenuView(AllanApplication application) {
        return new AllanMenuViewGui(application);
    }
}