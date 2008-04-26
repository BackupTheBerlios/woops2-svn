/*
 * IsiMarketClient.java
 */

package isimarketclient;

import isimarket.client.CorbaClient;
import isimarketclient.IsiMarketConnection.UserType;
import javax.swing.JOptionPane;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class IsiMarketClient extends SingleFrameApplication {
    private IsiMarketClientLoginDialog loginFrame = null;
    private IsiMarketClientFrame mainView = null;
    private IsiMarketAdminFrame adminView = null;
    private IsiMarketConnection.UserType session = IsiMarketConnection.UserType.NONE;
    private CorbaClient client = null;
    
    public CorbaClient getCorbaClient(){
        return client;
    }
    
    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        // start client
        client = new CorbaClient();
        try{
            client.startClient();
        }catch(Exception e){
            JOptionPane.showMessageDialog(loginFrame, "Erreur :"+e, "Erreur demarrage client", JOptionPane.ERROR_MESSAGE);
        }
        
        // start gui
        adminView = new IsiMarketAdminFrame();
        
        loginFrame = new IsiMarketClientLoginDialog(adminView);
        show(loginFrame);
        
        while (session == IsiMarketConnection.UserType.NONE) {

        }
        
        if (session == IsiMarketConnection.UserType.ADMIN){
            adminView = new IsiMarketAdminFrame();
            show(adminView);
        }
        else if (session == IsiMarketConnection.UserType.OPERATOR){
            mainView = new IsiMarketClientFrame(this);
            show(mainView);
        }
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

    void setConnectionType(UserType op) {
        session = op;
    }
}
