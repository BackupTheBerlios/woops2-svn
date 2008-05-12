/*
 * IsiMarketClient.java
 */
package isimarketclient;

import isimarket.client.CorbaClient;
import isimarket.model.Action;
import isimarket.model.ActionType;
import isimarket.model.Event;
import isimarket.servants.walletservant.WalletServantPackage.BadQuantityException;
import isimarket.servants.walletservant.WalletServantPackage.NotEnoughAvailableActionsException;
import isimarket.servants.walletservant.WalletServantPackage.NotEnoughCashException;
import isimarket.server.ServerConstants;
import isimarketclient.IsiMarketConstants.UserType;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class IsiMarketClient extends SingleFrameApplication {

    private ClientLoginDialog loginFrame = null;
    private DisplayActionTypeDialog displayActionTypeDialog = null;
    private BuyActionTypeDialog buyActionTypeDialog = null;
    private SellActionDialog sellActionDialog = null;
    private IsiMarketClientFrame mainView = null;
    private AdminFrame adminView = null;
    private IsiMarketConstants.UserType session = IsiMarketConstants.UserType.NONE;
    private CorbaClient client = null;

    public CorbaClient getCorbaClient() {
        return client;
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
            if (IsiMarketClientModel.wallet != null) {
                mainView = new IsiMarketClientFrame(this);
                // get infos
                mainView.cashField.setText("" + IsiMarketClientModel.wallet.cash);
                mainView.opeatorField.setText(IsiMarketClientModel.login);
                updateDataTables();
                //mainView.marketTable = new JTable(table, columns);
                show(mainView);
            }
        }
    }

    public void updateDataTables() {
        // update actions
        updateMarket();
        updateWalletActions();
        // update events
        updateEvents();
    }

    public void updateMarket() {
        IsiMarketClientModel.market = client.getActionTypeServantRef().getActionTypeList();

        DefaultTableModel tModel = (DefaultTableModel) mainView.marketTable.getModel();
        for (int i = 0; i < tModel.getRowCount(); i++) {
            if (i < IsiMarketClientModel.market.length) {
                tModel.setValueAt(IsiMarketClientModel.market[i].code, i, 0);
                tModel.setValueAt(IsiMarketClientModel.market[i].label, i, 1);
                tModel.setValueAt(IsiMarketClientModel.market[i].currentPrice, i, 2);
                tModel.setValueAt(IsiMarketClientModel.market[i].quantity, i, 3);
            } else {
                tModel.setValueAt(null, i, 0);
                tModel.setValueAt(null, i, 1);
                tModel.setValueAt(null, i, 2);
                tModel.setValueAt(null, i, 3);

            }
        }

        tModel.fireTableDataChanged();
    }

    public void updateWalletActions() {
        IsiMarketClientModel.actions = client.getWalletServant().getActionListFromWallet(IsiMarketClientModel.wallet.walletId);
        float walletValue = 0.0f;
        DefaultTableModel tModel = (DefaultTableModel) mainView.walletTable.getModel();

        for (int i = 0; i < tModel.getRowCount(); i++) {
            if (i < IsiMarketClientModel.actions.length) {
                tModel.setValueAt(IsiMarketClientModel.actions[i].actiontype.code, i, 0);
                tModel.setValueAt(IsiMarketClientModel.actions[i].buyPrice, i, 1);
                tModel.setValueAt(IsiMarketClientModel.actions[i].quantity, i, 2);
                float tmpValue = IsiMarketClientModel.actions[i].quantity * IsiMarketClientModel.actions[i].buyPrice;
                walletValue += tmpValue;
                tModel.setValueAt(tmpValue, i, 3);
                tModel.setValueAt(IsiMarketClientModel.actions[i].buyDate, i, 4);
            } else {
                tModel.setValueAt(null, i, 0);
                tModel.setValueAt(null, i, 1);
                tModel.setValueAt(null, i, 2);
                tModel.setValueAt(null, i, 3);
                tModel.setValueAt(null, i, 4);
            }
        }
        tModel.fireTableDataChanged();
        mainView.walletValueField.setText("" + walletValue);
    }
    
    public void updateEvents(){
        String logText = "";
        for (int i = 0; i < IsiMarketClientModel.market.length ; i++) {
            Event[] events = null;
            
            try {
                events = client.getEventServant().getEventListFromActionType("" + IsiMarketClientModel.market[i].code);
            } catch (Exception e) {
                e.printStackTrace();
            }
            for(int j = 0; j < events.length; j++ ){
                logText += "["+events[j].date+"]@"+IsiMarketClientModel.market[i].label+" : "+events[j].price+"\n";
            } 
        }
        mainView.logArea.setText(logText);
    }

    public void displayActionType(int rowNb) {
        displayActionTypeDialog = new DisplayActionTypeDialog(mainView.getFrame());
        ActionType at = IsiMarketClientModel.market[rowNb];
        displayActionTypeDialog.codeField.setText(at.code);
        displayActionTypeDialog.labelField.setText(at.label);
        displayActionTypeDialog.introDateField.setText(at.introductionDate);
        displayActionTypeDialog.introPriceLabel.setText("" + at.introductionPrice);
        displayActionTypeDialog.currentPriceField.setText("" + at.currentPrice);
        displayActionTypeDialog.quantityField.setText("" + at.quantity);
        show(displayActionTypeDialog);
    }

    public void showBuyActionType(int rowNb) {
        buyActionTypeDialog = new BuyActionTypeDialog(mainView.getFrame());
        ActionType at = IsiMarketClientModel.market[rowNb];
        buyActionTypeDialog.at = at;
        buyActionTypeDialog.labelField.setText(at.label);
        buyActionTypeDialog.priceField.setText("" + at.currentPrice);
        buyActionTypeDialog.totalQuantityField.setText("" + at.quantity);
        show(buyActionTypeDialog);
    }

    public void showSellActionType(int rowNb) {

        sellActionDialog = new SellActionDialog(mainView.getFrame());
        Action action = IsiMarketClientModel.actions[rowNb];
        sellActionDialog.currentAction = action;
        sellActionDialog.labelField.setText(action.actiontype.label);
        sellActionDialog.currentPriceField.setText("" + action.actiontype.currentPrice);
        sellActionDialog.buyPriceField.setText("" + action.buyPrice);
        sellActionDialog.quantityField.setText("" + action.quantity);
        show(sellActionDialog);
    }

    public void buyActionType(int qt) throws BadQuantityException, NotEnoughCashException {
        client.getWalletServant().buyAction(IsiMarketClientModel.wallet.walletId, "" + buyActionTypeDialog.at.code, qt);
        float cash = buyActionTypeDialog.at.currentPrice * qt * -1;
        addToWalletCash(cash);
        client.getEventServant().createEvent(ServerConstants.now(), cash, buyActionTypeDialog.at.code);
        
        updateDataTables();
    }

    public void SellAction(int qt) throws NotEnoughAvailableActionsException {
        client.getWalletServant().sellAction(IsiMarketClientModel.wallet.walletId, "" + sellActionDialog.currentAction.actiontype.code, sellActionDialog.currentAction.actionId, qt);
        float cash = sellActionDialog.currentAction.actiontype.currentPrice * qt;
        addToWalletCash(cash);
        //send event
        client.getEventServant().createEvent(ServerConstants.now(), cash, sellActionDialog.currentAction.actiontype.code);
        
        updateDataTables();
    }

    private void addToWalletCash(float c) {
        float totalCash = new Float(mainView.cashField.getText());
        totalCash += c;
        mainView.cashField.setText("" + totalCash);
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
