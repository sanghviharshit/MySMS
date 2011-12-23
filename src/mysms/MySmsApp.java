/*
 * MySmsApp.java
 */

package mysms;

import mysms.DAO.OptionsDAO;
import mysms.MySmsMain;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class MySmsApp extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        System.out.println("Inside startup of MySmsApp");
        show(new MySmsMain());

    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of MySmsApp
     */
    public static MySmsApp getApplication() {
        return Application.getInstance(MySmsApp.class);
    }

    /**
     * MySmsMain method launching the application.
     */
    public static void main(String[] args) {
        System.out.println("Inside main of MySms");
        launch(MySmsApp.class, args);

    }
}
