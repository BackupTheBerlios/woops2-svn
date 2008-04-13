/*
 * IsiMarketClient.java
 */

package isimarketclient;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class IsiMarketClient extends SingleFrameApplication {
    private IsiMarketClientLoginBox loginFrame = null;
    private IsiMarketClientView mainView = null;
    private String login = "";
    
    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        mainView = new IsiMarketClientView(this);
        //show(mainView);
        
        loginFrame = new IsiMarketClientLoginBox(mainView.getFrame());
        show(loginFrame);
        while (login.equals("")) {
            login = loginFrame.getLogin();
        }
        
        System.out.println("login is " + login);
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
     * @return the instance of IsiMarketClient
     */
    public static IsiMarketClient getApplication() {
        return Application.getInstance(IsiMarketClient.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(IsiMarketClient.class, args);
    }
}
