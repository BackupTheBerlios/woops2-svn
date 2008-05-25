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
import javax.swing.ComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListModel;
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
    private AddAlarmFrame addAlarmFrame = null;
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
                getAlarmTypes();
                updateData();
                //mainView.marketTable = new JTable(table, columns);
                show(mainView);
            }
        }
    }

    public void updateData() {
        // update actions
        updateMarket();
        updateWalletActions();
        // update events
        updateEvents();
        // update alarms
        updateAlarms();
        
        // check alarms
        checkAlarms();
    }

    public void updateMarket() {
        IsiMarketClientModel.market = client.getActionTypeServantRef().getActionTypeList();
        DefaultTableModel tModel = (DefaultTableModel) mainView.marketTable.getModel();
        
        // remove old rows
        for (int i = 0; i < tModel.getRowCount(); i++){
            tModel.removeRow(i);
        }
        // new content
        for (int i = 0; i < IsiMarketClientModel.market.length; i++) {
            tModel.addRow(new Object[]{"","","",""});
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
        mainView.marketTable.repaint();
        tModel.fireTableDataChanged();
        
    }

    public void updateWalletActions() {
        IsiMarketClientModel.actions = client.getWalletServantRef().getActionListFromWallet(IsiMarketClientModel.wallet.walletId);
        float walletValue = 0.0f;
        DefaultTableModel tModel = (DefaultTableModel) mainView.walletTable.getModel();
        
        // remove old rows
        for (int i = 0; i < tModel.getRowCount(); i++){
            tModel.removeRow(i);
        }
        // content
        for (int i = 0; i < IsiMarketClientModel.actions.length; i++) {
            tModel.addRow(new Object[]{"","","","",""});
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
    
    public void updateAlarms(){
        IsiMarketClientModel.alarms = client.getAlarmServantRef().getAlarmListFromWallet(IsiMarketClientModel.wallet.walletId);
        
        DefaultTableModel tModel = (DefaultTableModel) mainView.alarmTable.getModel();
        
        // remove old rows
        for (int i = 0; i < tModel.getRowCount(); i++){
            tModel.removeRow(i);
        }
        // adding new content
        for (int i = 0; i < IsiMarketClientModel.alarms.length; i++) {
            tModel.addRow(new Object[]{"","",""});
            if (i < IsiMarketClientModel.alarms.length) {
                tModel.setValueAt(IsiMarketClientModel.alarms[i].actionType.label, i, 0);
                tModel.setValueAt(IsiMarketClientModel.alarms[i].type.label, i, 1);
                tModel.setValueAt(IsiMarketClientModel.alarms[i].value, i, 2);
            } else {
                tModel.setValueAt(null, i, 0);
                tModel.setValueAt(null, i, 1);
                tModel.setValueAt(null, i, 2);
            }
        }
        // fire update !
        tModel.fireTableDataChanged();
    }
    
    public void checkAlarms(){
        
    }
    
    public void getAlarmTypes(){
        IsiMarketClientModel.alarmTypes = client.getAlarmServantRef().getAlarmTypeList();
    }
    
    public void updateEvents(){
        String logText = "";
        for (int i = 0; i < IsiMarketClientModel.market.length ; i++) {
            Event[] events = null;
            
            try {
                events = client.getEventServantRef().getEventsForActionType("" + IsiMarketClientModel.market[i].code);
            } catch (Exception e) {
                e.printStackTrace();
            }
            for(int j = 0; j < events.length; j++ ){
                String eventType;
                Float price;
                if (events[j].price > 0){ 
                    eventType = "vente";
                    price = events[j].price;
                }
                else{
                    eventType = "achat";
                    price = events[j].price * -1;
                }
                //logText += "["+events[j].date+"]@"+IsiMarketClientModel.market[i].label+" : "+eventType+" pour "+events[j].price+"\n";
                logText += "["+events[j].date+"] "+eventType+" de "+price+" € d'actions "+IsiMarketClientModel.market[i].label+"\n";
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
    
     public void displayAddAlarm(int rowNb) {
        addAlarmFrame = new AddAlarmFrame();
        String label = IsiMarketClientModel.market[mainView.marketTable.getSelectedRow()].label;
        float value = IsiMarketClientModel.market[mainView.marketTable.getSelectedRow()].currentPrice;
        addAlarmFrame.actionLabelField.setText(label);
        addAlarmFrame.valueField.setText(""+value);
        
        for (int i = 0; i < IsiMarketClientModel.alarmTypes.length; i++){
            addAlarmFrame.alarmTypesComboBox.addItem(IsiMarketClientModel.alarmTypes[i].label);
        }
        
        show(addAlarmFrame);
    }
    
     public void displayActionTypeFromWallet(int rowNb) {
         
        Action a = IsiMarketClientModel.actions[rowNb];
        int i = 0;
        while(i < IsiMarketClientModel.market.length){
            if (a.actiontype.code.equals(IsiMarketClientModel.market[i].code))
                break;
            i++;
        }
        displayActionType(i);
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
        client.getWalletServantRef().buyAction(IsiMarketClientModel.wallet.walletId, "" + buyActionTypeDialog.at.code, qt);
        float cash = buyActionTypeDialog.at.currentPrice * qt * -1;
        addToWalletCash(cash);
        //client.getEventServant().createEvent(ServerConstants.now(), cash, buyActionTypeDialog.at.code);
        
        updateData();
    }

    public void SellAction(int qt) throws NotEnoughAvailableActionsException {
        client.getWalletServantRef().sellAction(IsiMarketClientModel.wallet.walletId, "" + sellActionDialog.currentAction.actiontype.code, sellActionDialog.currentAction.actionId, qt);
        float cash = sellActionDialog.currentAction.actiontype.currentPrice * qt;
        addToWalletCash(cash);
        //send event
        //client.getEventServant().createEvent(ServerConstants.now(), cash, sellActionDialog.currentAction.actiontype.code);
        
        updateData();
    }

    private void addToWalletCash(float c) {
        float totalCash = new Float(mainView.cashField.getText());
        totalCash += c;
        mainView.cashField.setText("" + totalCash);
    }
    
    public void addAlarm( int alarmNb, float value ) {
        String actionTypeCode = IsiMarketClientModel.market[mainView.marketTable.getSelectedRow()].code;
        
        client.getAlarmServantRef().createAlarm(IsiMarketClientModel.alarmTypes[alarmNb].label+" "+IsiMarketClientModel.market[mainView.marketTable.getSelectedRow()].label+" : "+value, 
                value,
                IsiMarketClientModel.wallet.walletId, 
                IsiMarketClientModel.alarmTypes[alarmNb].alarmTypeId, 
                actionTypeCode);
        
        updateData();
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
