import model.AllanApplication;
import model.Factory;
import model.factory.ConsoleFactory;
import model.factory.GuiFactory;
import model.PersistenceService;
import view.AllanMenuView;

/**
 * The Main class instantiates the application and configures the respective factory
 * according to the command-line arguments provided.
 * <p>
 * In addition, this class is responsible for managing the application lifecycle,
 * including loading the initial state using {@link model.PersistenceService}.
 *
 * @author zafir
 * @see model.PersistenceService
 * @see model.Factory
 */
public class Main {

    public static void main(String[] args) {
        if (args.length > 0) {
            for (String arg: args) {
                if (arg.equals("--gui")) {
                    Factory.setConcreteFactory(new GuiFactory());
                } else if (arg.equals("--terminal")) {
                    Factory.setConcreteFactory(new ConsoleFactory());
                }
            }
        }
        AllanApplication application;
        PersistenceService<AllanApplication> loader = new PersistenceService<>();

        try {
            application = loader.load("allan_db.ser");
            System.out.println("Data loaded successfully!");
        } catch (Exception e) {
            System.out.println("No previous data found. Starting new database.");
            application = new AllanApplication();
        }

        AllanMenuView frontEnd = Factory.GetConcreteFactory().createAllanMenuView(application);
        frontEnd.showMenu();
    }
}