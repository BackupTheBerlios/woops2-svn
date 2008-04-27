/*
 * IsiMarketClient.java
 */
package isimarketclient;

import isimarket.client.CorbaClient;
import isimarket.model.Wallet;
import isimarketclient.IsiMarketConnection.UserType;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
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
    private Wallet currentWallet = null;

    public CorbaClient getCorbaClient() {
        return client;
    }

    public Wallet getWallet() {
        return currentWallet;
    }

    public void setWallet(Wallet w) {
        currentWallet = w;
    }

    /**
     * At startup create and show the main frame of the application.
     */
    @Override
    protected void startup() {
        // start client
        client = new CorbaClient();
        try {
            client.startClient();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(loginFrame, "Erreur :" + e, "Erreur demarrage client", JOptionPane.ERROR_MESSAGE);
        }

        // start gui
        adminView = new IsiMarketAdminFrame();

        loginFrame = new IsiMarketClientLoginDialog(adminView);
        show(loginFrame);

        waitConnectionType();
    }

    private void waitConnectionType() {
        while (session == IsiMarketConnection.UserType.NONE) {

        }

        if (session == IsiMarketConnection.UserType.ADMIN) {
            adminView = new IsiMarketAdminFrame();
            show(adminView);
        } else if (session == IsiMarketConnection.UserType.OPERATOR) {
            if (currentWallet != null) {
                mainView = new IsiMarketClientFrame(this);
                // get infos
                mainView.cashField.setText("" + IsiMarketClientModel.cash);
                mainView.opeatorField.setText(IsiMarketClientModel.login);
                // update actions
                updateMarket(mainView);

                //mainView.marketTable = new JTable(table, columns);
                show(mainView);
            }
        }
    }

    public void updateMarket(IsiMarketClientFrame mainView) {
        IsiMarketClientModel.market = client.getActionTypeServantRef().getActionTypeList();

        TableModel tModel = mainView.marketTable.getModel();
        for (int i = 0; i < IsiMarketClientModel.market.length; i++) {
            tModel.setValueAt(IsiMarketClientModel.market[i].code, i, 0);
            tModel.setValueAt(IsiMarketClientModel.market[i].label, i, 1);
            tModel.setValueAt(IsiMarketClientModel.market[i].currentPrice, i, 2);
            tModel.setValueAt(IsiMarketClientModel.market[i].quantity, i, 3);
        }
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override
    protected void configureWindow(java.awt.Window root) {
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
