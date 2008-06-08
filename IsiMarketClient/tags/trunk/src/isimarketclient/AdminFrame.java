/*
 * IsiMarketAdminFrame.java
 *
 * Created on 20 avril 2008, 19:11
 */

package isimarketclient;

import isimarket.server.ServerConstants;
import javax.swing.JOptionPane;


/**
 *
 * @author  Arno
 */
public class AdminFrame extends javax.swing.JFrame {
    
    /** Creates new form IsiMarketAdminFrame */
    public AdminFrame() {
        initComponents();
    }
    
    /** This method is called froam within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        quitButton = new javax.swing.JButton();
        tabbedPane = new javax.swing.JTabbedPane();
        newOperatorPanel = new javax.swing.JPanel();
        loginLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        loginField = new javax.swing.JTextField();
        passwordField = new javax.swing.JTextField();
        createButton = new javax.swing.JButton();
        cashLabel = new javax.swing.JLabel();
        cashField = new javax.swing.JTextField();
        operatorUpdate = new javax.swing.JPanel();
        updateLoginLabel = new javax.swing.JLabel();
        updateLoginField = new javax.swing.JTextField();
        updateCashLabel = new javax.swing.JLabel();
        updateButton = new javax.swing.JButton();
        updateCashField = new javax.swing.JTextField();
        newActionPanel = new javax.swing.JPanel();
        codeLabel = new javax.swing.JLabel();
        labelLabel = new javax.swing.JLabel();
        priceLabel = new javax.swing.JLabel();
        quantityLabel = new javax.swing.JLabel();
        codeField = new javax.swing.JTextField();
        labelField = new javax.swing.JTextField();
        priceField = new javax.swing.JTextField();
        quantityField = new javax.swing.JTextField();
        addActionButton = new javax.swing.JButton();
        updateActionPanel = new javax.swing.JPanel();
        updateCodeLabel = new javax.swing.JLabel();
        updateCodeField = new javax.swing.JTextField();
        updatePriceLabel = new javax.swing.JLabel();
        updatePriceField = new javax.swing.JTextField();
        updateActionButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(isimarketclient.IsiMarketClient.class).getContext().getResourceMap(AdminFrame.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        setResizable(false);

        quitButton.setText(resourceMap.getString("quitButton.text")); // NOI18N
        quitButton.setName("quitButton"); // NOI18N
        quitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitButtonActionPerformed(evt);
            }
        });

        tabbedPane.setName("tabbedPane"); // NOI18N

        newOperatorPanel.setName("newOperatorPanel"); // NOI18N

        loginLabel.setText(resourceMap.getString("loginLabel.text")); // NOI18N
        loginLabel.setName("loginLabel"); // NOI18N

        passwordLabel.setText(resourceMap.getString("passwordLabel.text")); // NOI18N
        passwordLabel.setName("passwordLabel"); // NOI18N

        loginField.setName("loginField"); // NOI18N

        passwordField.setName("passwordField"); // NOI18N

        createButton.setText(resourceMap.getString("createButton.text")); // NOI18N
        createButton.setName("createButton"); // NOI18N
        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonActionPerformed(evt);
            }
        });

        cashLabel.setText(resourceMap.getString("cashLabel.text")); // NOI18N
        cashLabel.setName("cashLabel"); // NOI18N

        cashField.setText(resourceMap.getString("cashField.text")); // NOI18N
        cashField.setName("cashField"); // NOI18N

        javax.swing.GroupLayout newOperatorPanelLayout = new javax.swing.GroupLayout(newOperatorPanel);
        newOperatorPanel.setLayout(newOperatorPanelLayout);
        newOperatorPanelLayout.setHorizontalGroup(
            newOperatorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newOperatorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(newOperatorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(loginLabel)
                    .addComponent(passwordLabel)
                    .addComponent(cashLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(newOperatorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cashField)
                    .addComponent(passwordField)
                    .addComponent(loginField, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
                .addContainerGap(189, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newOperatorPanelLayout.createSequentialGroup()
                .addContainerGap(274, Short.MAX_VALUE)
                .addComponent(createButton)
                .addContainerGap())
        );
        newOperatorPanelLayout.setVerticalGroup(
            newOperatorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newOperatorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(newOperatorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginLabel)
                    .addComponent(loginField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(newOperatorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(newOperatorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cashField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cashLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(createButton)
                .addContainerGap())
        );

        tabbedPane.addTab(resourceMap.getString("newOperatorPanel.TabConstraints.tabTitle"), newOperatorPanel); // NOI18N

        operatorUpdate.setName("operatorUpdate"); // NOI18N

        updateLoginLabel.setText(resourceMap.getString("updateLoginLabel.text")); // NOI18N
        updateLoginLabel.setName("updateLoginLabel"); // NOI18N

        updateLoginField.setName("updateLoginField"); // NOI18N

        updateCashLabel.setText(resourceMap.getString("updateCashLabel.text")); // NOI18N
        updateCashLabel.setName("updateCashLabel"); // NOI18N

        updateButton.setText(resourceMap.getString("updateButton.text")); // NOI18N
        updateButton.setName("updateButton"); // NOI18N
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        updateCashField.setText(resourceMap.getString("updateCashField.text")); // NOI18N
        updateCashField.setName("updateCashField"); // NOI18N

        javax.swing.GroupLayout operatorUpdateLayout = new javax.swing.GroupLayout(operatorUpdate);
        operatorUpdate.setLayout(operatorUpdateLayout);
        operatorUpdateLayout.setHorizontalGroup(
            operatorUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(operatorUpdateLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(operatorUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(updateCashLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateLoginLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(operatorUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(updateLoginField, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateCashField))
                .addContainerGap(188, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, operatorUpdateLayout.createSequentialGroup()
                .addContainerGap(302, Short.MAX_VALUE)
                .addComponent(updateButton)
                .addContainerGap())
        );
        operatorUpdateLayout.setVerticalGroup(
            operatorUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(operatorUpdateLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(operatorUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateLoginLabel)
                    .addComponent(updateLoginField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(operatorUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateCashField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateCashLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(updateButton)
                .addContainerGap())
        );

        tabbedPane.addTab(resourceMap.getString("operatorUpdate.TabConstraints.tabTitle"), operatorUpdate); // NOI18N

        newActionPanel.setName("newActionPanel"); // NOI18N

        codeLabel.setText(resourceMap.getString("codeLabel.text")); // NOI18N
        codeLabel.setName("codeLabel"); // NOI18N

        labelLabel.setText(resourceMap.getString("labelLabel.text")); // NOI18N
        labelLabel.setName("labelLabel"); // NOI18N

        priceLabel.setText(resourceMap.getString("priceLabel.text")); // NOI18N
        priceLabel.setName("priceLabel"); // NOI18N

        quantityLabel.setText(resourceMap.getString("quantityLabel.text")); // NOI18N
        quantityLabel.setName("quantityLabel"); // NOI18N

        codeField.setText(resourceMap.getString("codeField.text")); // NOI18N
        codeField.setName("codeField"); // NOI18N

        labelField.setText(resourceMap.getString("labelField.text")); // NOI18N
        labelField.setName("labelField"); // NOI18N

        priceField.setText(resourceMap.getString("priceField.text")); // NOI18N
        priceField.setName("priceField"); // NOI18N

        quantityField.setText(resourceMap.getString("quantityField.text")); // NOI18N
        quantityField.setName("quantityField"); // NOI18N

        addActionButton.setText(resourceMap.getString("addActionButton.text")); // NOI18N
        addActionButton.setName("addActionButton"); // NOI18N
        addActionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout newActionPanelLayout = new javax.swing.GroupLayout(newActionPanel);
        newActionPanel.setLayout(newActionPanelLayout);
        newActionPanelLayout.setHorizontalGroup(
            newActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newActionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(newActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(codeLabel)
                    .addComponent(quantityLabel)
                    .addComponent(labelLabel)
                    .addComponent(priceLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(newActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelField)
                    .addComponent(priceField)
                    .addComponent(quantityField)
                    .addComponent(codeField, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(237, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newActionPanelLayout.createSequentialGroup()
                .addContainerGap(286, Short.MAX_VALUE)
                .addComponent(addActionButton)
                .addContainerGap())
        );
        newActionPanelLayout.setVerticalGroup(
            newActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newActionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(newActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codeLabel)
                    .addComponent(codeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(newActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelLabel)
                    .addComponent(labelField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(newActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(priceLabel)
                    .addComponent(priceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(newActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quantityLabel)
                    .addComponent(quantityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(addActionButton)
                .addContainerGap())
        );

        tabbedPane.addTab(resourceMap.getString("newActionPanel.TabConstraints.tabTitle"), newActionPanel); // NOI18N

        updateActionPanel.setName("updateActionPanel"); // NOI18N

        updateCodeLabel.setText(resourceMap.getString("updateCodeLabel.text")); // NOI18N
        updateCodeLabel.setName("updateCodeLabel"); // NOI18N

        updateCodeField.setName("updateCodeField"); // NOI18N

        updatePriceLabel.setText(resourceMap.getString("updatePriceLabel.text")); // NOI18N
        updatePriceLabel.setName("updatePriceLabel"); // NOI18N

        updatePriceField.setName("updatePriceField"); // NOI18N

        updateActionButton.setText(resourceMap.getString("updateActionButton.text")); // NOI18N
        updateActionButton.setName("updateActionButton"); // NOI18N
        updateActionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout updateActionPanelLayout = new javax.swing.GroupLayout(updateActionPanel);
        updateActionPanel.setLayout(updateActionPanelLayout);
        updateActionPanelLayout.setHorizontalGroup(
            updateActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateActionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(updateActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updateActionPanelLayout.createSequentialGroup()
                        .addGroup(updateActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(updateActionPanelLayout.createSequentialGroup()
                                .addComponent(updateCodeLabel)
                                .addGap(21, 21, 21)
                                .addComponent(updateCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(updateActionPanelLayout.createSequentialGroup()
                                .addComponent(updatePriceLabel)
                                .addGap(28, 28, 28)
                                .addComponent(updatePriceField, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)))
                        .addGap(191, 191, 191))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updateActionPanelLayout.createSequentialGroup()
                        .addComponent(updateActionButton)
                        .addContainerGap())))
        );
        updateActionPanelLayout.setVerticalGroup(
            updateActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateActionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(updateActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateCodeLabel)
                    .addComponent(updateCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(updateActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updatePriceLabel)
                    .addComponent(updatePriceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(updateActionButton)
                .addContainerGap())
        );

        tabbedPane.addTab(resourceMap.getString("updateActionPanel.TabConstraints.tabTitle"), updateActionPanel); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabbedPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                    .addComponent(quitButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(quitButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_quitButtonActionPerformed

    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonActionPerformed
        try{
        Float cash = new Float(cashField.getText());
        IsiMarketClient.getApplication().getCorbaClient()
                .getAdministrationServantRef().createOperator(loginField.getText(), passwordField.getText(), 
                cash);
        JOptionPane.showMessageDialog(this, "Creation OK !", "IsimarketClient", JOptionPane.INFORMATION_MESSAGE);
        } catch(Exception e){
            e.printStackTrace();
             JOptionPane.showMessageDialog(this, "Erreur :"+e, "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_createButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
       try{
        Float cash = new Float(updateCashField.getText());
//        IsiMarketClient.getApplication().getCorbaClient()
//                .getAdministrationServantRef().updateCash(updateLoginField.getText(), cash);
        
        JOptionPane.showMessageDialog(this, "Mise à jour OK !", "IsimarketClient", JOptionPane.INFORMATION_MESSAGE);
        } catch(Exception e){
            e.printStackTrace();
             JOptionPane.showMessageDialog(this, "Erreur :"+e, "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_updateButtonActionPerformed

    private void addActionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionButtonActionPerformed
       try{
        Float price = new Float(priceField.getText());
        Integer quantity = new Integer(quantityField.getText());
        IsiMarketClient.getApplication().getCorbaClient().getActionTypeServantRef()
                .createActionType(codeField.getText(), labelField.getText(), ServerConstants.now(), price, quantity, price);
        
        JOptionPane.showMessageDialog(this, "Creation Action OK !", "IsimarketClient", JOptionPane.INFORMATION_MESSAGE);
        } catch(Exception e){
            e.printStackTrace();
             JOptionPane.showMessageDialog(this, "Erreur :"+e, "Erreur", JOptionPane.ERROR_MESSAGE);
        }
}//GEN-LAST:event_addActionButtonActionPerformed

    private void updateActionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionButtonActionPerformed
        try{
        Float price = new Float(updatePriceField.getText());
        //Integer quantity = new Integer(updateQuantityField.getText());
//        IsiMarketClient.getApplication().getCorbaClient().getActionTypeServantRef()
//                .updateActionTypeCurrentPrice(updateCodeField.getText(), price);
//        IsiMarketClient.getApplication().getCorbaClient().getActionTypeServantRef()
//                .updateActionTypeQuantity(updateCodeField.getText(), quantity);
//        
        IsiMarketClient.getApplication().updateActionTypeRateViaWS(updateCodeField.getText(), price);
        JOptionPane.showMessageDialog(this, "MAJ Action OK !", "IsimarketClient", JOptionPane.INFORMATION_MESSAGE);
        } catch(Exception e){
            e.printStackTrace();
             JOptionPane.showMessageDialog(this, "Erreur :"+e, "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_updateActionButtonActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminFrame().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addActionButton;
    private javax.swing.JTextField cashField;
    private javax.swing.JLabel cashLabel;
    private javax.swing.JTextField codeField;
    private javax.swing.JLabel codeLabel;
    private javax.swing.JButton createButton;
    private javax.swing.JTextField labelField;
    private javax.swing.JLabel labelLabel;
    private javax.swing.JTextField loginField;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JPanel newActionPanel;
    private javax.swing.JPanel newOperatorPanel;
    private javax.swing.JPanel operatorUpdate;
    private javax.swing.JTextField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JTextField priceField;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JTextField quantityField;
    private javax.swing.JLabel quantityLabel;
    private javax.swing.JButton quitButton;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JButton updateActionButton;
    private javax.swing.JPanel updateActionPanel;
    private javax.swing.JButton updateButton;
    private javax.swing.JTextField updateCashField;
    private javax.swing.JLabel updateCashLabel;
    private javax.swing.JTextField updateCodeField;
    private javax.swing.JLabel updateCodeLabel;
    private javax.swing.JTextField updateLoginField;
    private javax.swing.JLabel updateLoginLabel;
    private javax.swing.JTextField updatePriceField;
    private javax.swing.JLabel updatePriceLabel;
    // End of variables declaration//GEN-END:variables
    
}
