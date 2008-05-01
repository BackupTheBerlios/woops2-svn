/*
 * IsiMarketClient.java
 */
package isimarketclient;

import isimarket.client.CorbaClient;
import isimarket.model.ActionType;
import isimarket.model.Wallet;
import isimarketclient.IsiMarketConstants.UserType;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class IsiMarketClient extends SingleFrameApplication {

    private ClientLoginDialog loginFrame = null;
    private DisplayActionTypeDialog displayActionTypeDialog = null;
    private IsiMarketClientFrame mainView = null;
    private AdminFrame adminView = null;
    private IsiMarketConstants.UserType session = IsiMarketConstants.UserType.NONE;
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
        adminView = new AdminFrame();

        loginFrame = new ClientLoginDialog(adminView);
        show(loginFrame);

        waitConnectionType();
    }

    private void waitConnectionType() {
        while (session == IsiMarketConstants.UserType.NONE) {

        }

        if (session == IsiMarketConstants.UserType.ADMIN) {
            adminView = new AdminFrame();
            show(adminView);
        } else if (session == IsiMarketConstants.UserType.OPERATOR) {
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
    
    public void displayActionType(int rowNb){
        displayActionTypeDialog = new DisplayActionTypeDialog(mainView.getFrame());
        ActionType at = IsiMarketClientModel.market[rowNb];
        displayActionTypeDialog.codeField.setText(at.code);
        displayActionTypeDialog.labelField.setText(at.label);
        displayActionTypeDialog.introDateField.setText(at.introductionDate);
        displayActionTypeDialog.introPriceLabel.setText(""+at.introductionPrice);
        displayActionTypeDialog.currentPriceField.setText(""+at.currentPrice);
        displayActionTypeDialog.quantityField.setText(""+at.quantity);
        show(displayActionTypeDialog);
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
