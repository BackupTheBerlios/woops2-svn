/*
 * IsiMarketClientFrame.java
 */

package isimarketclient;

import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * The application's main frame.
 */
public class IsiMarketClientFrame extends FrameView {
    
    public IsiMarketClientFrame(SingleFrameApplication app) {
        super(app);
        
        initComponents();

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = IsiMarketClient.getApplication().getMainFrame();
            aboutBox = new IsiMarketClientAboutDialog(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        IsiMarketClient.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        tabbedPane = new javax.swing.JTabbedPane();
        marketPanel = new javax.swing.JPanel();
        scrollMarketPanel = new javax.swing.JScrollPane();
        marketTable = new javax.swing.JTable();
        buyActionTypeButton = new javax.swing.JButton();
        showActionTypeButton = new javax.swing.JButton();
        refreshActionTypeButton1 = new javax.swing.JButton();
        refreshActionTypeButton4 = new javax.swing.JButton();
        operatorPanel = new javax.swing.JPanel();
        jscrollPaneOperatorPanel = new javax.swing.JScrollPane();
        walletTable = new javax.swing.JTable();
        buyActionTypeButton1 = new javax.swing.JButton();
        showActionTypeButton1 = new javax.swing.JButton();
        refreshActionTypeButton2 = new javax.swing.JButton();
        alarmPanel = new javax.swing.JPanel();
        eventPanel = new javax.swing.JPanel();
        operatorLabel = new javax.swing.JLabel();
        cashLabel = new javax.swing.JLabel();
        opeatorField = new javax.swing.JTextField();
        cashField = new javax.swing.JTextField();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        mainPanel.setName("mainPanel"); // NOI18N

        tabbedPane.setName("tabbedPane"); // NOI18N

        marketPanel.setName("marketPanel"); // NOI18N

        scrollMarketPanel.setName("scrollMarketPanel"); // NOI18N

        marketTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Code", "Label", "Price", "Quantity"
            }
        ));
        marketTable.setName("marketTable"); // NOI18N
        scrollMarketPanel.setViewportView(marketTable);

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(isimarketclient.IsiMarketClient.class).getContext().getResourceMap(IsiMarketClientFrame.class);
        buyActionTypeButton.setIcon(resourceMap.getIcon("buyActionTypeButton.icon")); // NOI18N
        buyActionTypeButton.setText(resourceMap.getString("buyActionTypeButton.text")); // NOI18N
        buyActionTypeButton.setName("buyActionTypeButton"); // NOI18N

        showActionTypeButton.setIcon(resourceMap.getIcon("showActionTypeButton.icon")); // NOI18N
        showActionTypeButton.setText(resourceMap.getString("showActionTypeButton.text")); // NOI18N
        showActionTypeButton.setName("showActionTypeButton"); // NOI18N

        refreshActionTypeButton1.setIcon(resourceMap.getIcon("refreshActionTypeButton1.icon")); // NOI18N
        refreshActionTypeButton1.setText(resourceMap.getString("refreshActionTypeButton1.text")); // NOI18N
        refreshActionTypeButton1.setName("refreshActionTypeButton1"); // NOI18N

        refreshActionTypeButton4.setIcon(resourceMap.getIcon("refreshActionTypeButton4.icon")); // NOI18N
        refreshActionTypeButton4.setText(resourceMap.getString("refreshActionTypeButton4.text")); // NOI18N
        refreshActionTypeButton4.setName("refreshActionTypeButton4"); // NOI18N
        refreshActionTypeButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionTypeButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout marketPanelLayout = new javax.swing.GroupLayout(marketPanel);
        marketPanel.setLayout(marketPanelLayout);
        marketPanelLayout.setHorizontalGroup(
            marketPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(marketPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollMarketPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(marketPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(refreshActionTypeButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                    .addComponent(showActionTypeButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                    .addComponent(buyActionTypeButton, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                    .addComponent(refreshActionTypeButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
                .addContainerGap())
        );
        marketPanelLayout.setVerticalGroup(
            marketPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(marketPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(marketPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrollMarketPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, marketPanelLayout.createSequentialGroup()
                        .addComponent(buyActionTypeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(showActionTypeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(refreshActionTypeButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(refreshActionTypeButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        tabbedPane.addTab(resourceMap.getString("marketPanel.TabConstraints.tabTitle"), marketPanel); // NOI18N

        operatorPanel.setName("operatorPanel"); // NOI18N

        jscrollPaneOperatorPanel.setName("jscrollPaneOperatorPanel"); // NOI18N

        walletTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Code", "Prix d'achat", "Quantité", "Date"
            }
        ));
        walletTable.setName("walletTable"); // NOI18N
        jscrollPaneOperatorPanel.setViewportView(walletTable);

        buyActionTypeButton1.setIcon(resourceMap.getIcon("buyActionTypeButton1.icon")); // NOI18N
        buyActionTypeButton1.setText(resourceMap.getString("buyActionTypeButton1.text")); // NOI18N
        buyActionTypeButton1.setName("buyActionTypeButton1"); // NOI18N

        showActionTypeButton1.setIcon(resourceMap.getIcon("showActionTypeButton1.icon")); // NOI18N
        showActionTypeButton1.setText(resourceMap.getString("showActionTypeButton1.text")); // NOI18N
        showActionTypeButton1.setName("showActionTypeButton1"); // NOI18N

        refreshActionTypeButton2.setIcon(resourceMap.getIcon("refreshActionTypeButton2.icon")); // NOI18N
        refreshActionTypeButton2.setText(resourceMap.getString("refreshActionTypeButton2.text")); // NOI18N
        refreshActionTypeButton2.setName("refreshActionTypeButton2"); // NOI18N

        javax.swing.GroupLayout operatorPanelLayout = new javax.swing.GroupLayout(operatorPanel);
        operatorPanel.setLayout(operatorPanelLayout);
        operatorPanelLayout.setHorizontalGroup(
            operatorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(operatorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jscrollPaneOperatorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(operatorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buyActionTypeButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addGroup(operatorPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(showActionTypeButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
                    .addComponent(refreshActionTypeButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
                .addContainerGap())
        );
        operatorPanelLayout.setVerticalGroup(
            operatorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(operatorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(operatorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jscrollPaneOperatorPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, operatorPanelLayout.createSequentialGroup()
                        .addComponent(buyActionTypeButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(showActionTypeButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(refreshActionTypeButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        tabbedPane.addTab(resourceMap.getString("operatorPanel.TabConstraints.tabTitle"), operatorPanel); // NOI18N

        alarmPanel.setName("alarmPanel"); // NOI18N

        javax.swing.GroupLayout alarmPanelLayout = new javax.swing.GroupLayout(alarmPanel);
        alarmPanel.setLayout(alarmPanelLayout);
        alarmPanelLayout.setHorizontalGroup(
            alarmPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 714, Short.MAX_VALUE)
        );
        alarmPanelLayout.setVerticalGroup(
            alarmPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 293, Short.MAX_VALUE)
        );

        tabbedPane.addTab(resourceMap.getString("alarmPanel.TabConstraints.tabTitle"), alarmPanel); // NOI18N

        eventPanel.setName("eventPanel"); // NOI18N

        javax.swing.GroupLayout eventPanelLayout = new javax.swing.GroupLayout(eventPanel);
        eventPanel.setLayout(eventPanelLayout);
        eventPanelLayout.setHorizontalGroup(
            eventPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 714, Short.MAX_VALUE)
        );
        eventPanelLayout.setVerticalGroup(
            eventPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 293, Short.MAX_VALUE)
        );

        tabbedPane.addTab(resourceMap.getString("eventPanel.TabConstraints.tabTitle"), eventPanel); // NOI18N

        operatorLabel.setText(resourceMap.getString("operatorLabel.text")); // NOI18N
        operatorLabel.setName("operatorLabel"); // NOI18N

        cashLabel.setText(resourceMap.getString("cashLabel.text")); // NOI18N
        cashLabel.setName("cashLabel"); // NOI18N

        opeatorField.setEditable(false);
        opeatorField.setName("opeatorField"); // NOI18N

        cashField.setEditable(false);
        cashField.setText(resourceMap.getString("cashField.text")); // NOI18N
        cashField.setName("cashField"); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 719, Short.MAX_VALUE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cashLabel)
                            .addComponent(operatorLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(opeatorField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cashField, javax.swing.GroupLayout.Alignment.LEADING))))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(operatorLabel)
                    .addComponent(opeatorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cashLabel)
                    .addComponent(cashField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE))
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(isimarketclient.IsiMarketClient.class).getContext().getActionMap(IsiMarketClientFrame.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setText(resourceMap.getString("exitMenuItem.text")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setText(resourceMap.getString("aboutMenuItem.text")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 739, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 569, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void refreshActionTypeButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionTypeButton4ActionPerformed
        IsiMarketClient.getApplication().updateMarket(this);
    }//GEN-LAST:event_refreshActionTypeButton4ActionPerformed

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel alarmPanel;
    private javax.swing.JButton buyActionTypeButton;
    private javax.swing.JButton buyActionTypeButton1;
    public javax.swing.JTextField cashField;
    private javax.swing.JLabel cashLabel;
    private javax.swing.JPanel eventPanel;
    private javax.swing.JScrollPane jscrollPaneOperatorPanel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel marketPanel;
    public javax.swing.JTable marketTable;
    private javax.swing.JMenuBar menuBar;
    public javax.swing.JTextField opeatorField;
    private javax.swing.JLabel operatorLabel;
    private javax.swing.JPanel operatorPanel;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JButton refreshActionTypeButton1;
    private javax.swing.JButton refreshActionTypeButton2;
    private javax.swing.JButton refreshActionTypeButton4;
    private javax.swing.JScrollPane scrollMarketPanel;
    private javax.swing.JButton showActionTypeButton;
    private javax.swing.JButton showActionTypeButton1;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JTable walletTable;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
}
