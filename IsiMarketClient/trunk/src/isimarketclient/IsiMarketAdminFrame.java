/*
 * IsiMarketAdminFrame.java
 *
 * Created on 20 avril 2008, 19:11
 */

package isimarketclient;

import javax.swing.JOptionPane;

/**
 *
 * @author  Arno
 */
public class IsiMarketAdminFrame extends javax.swing.JFrame {
    
    /** Creates new form IsiMarketAdminFrame */
    public IsiMarketAdminFrame() {
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(isimarketclient.IsiMarketClient.class).getContext().getResourceMap(IsiMarketAdminFrame.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

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
                    .addComponent(loginField)
                    .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
                .addContainerGap(128, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newOperatorPanelLayout.createSequentialGroup()
                .addContainerGap(251, Short.MAX_VALUE)
                .addComponent(createButton, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGap(18, 18, 18)
                .addComponent(createButton)
                .addContainerGap(37, Short.MAX_VALUE))
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
                .addContainerGap(166, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, operatorUpdateLayout.createSequentialGroup()
                .addContainerGap(251, Short.MAX_VALUE)
                .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(updateButton)
                .addContainerGap())
        );

        tabbedPane.addTab(resourceMap.getString("operatorUpdate.TabConstraints.tabTitle"), operatorUpdate); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(quitButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
        IsiMarketClient.getApplication().getCorbaClient()
                .getAdministrationServantRef().updateCash(updateLoginField.getText(), cash);
        JOptionPane.showMessageDialog(this, "Mise à jour OK !", "IsimarketClient", JOptionPane.INFORMATION_MESSAGE);
        } catch(Exception e){
            e.printStackTrace();
             JOptionPane.showMessageDialog(this, "Erreur :"+e, "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_updateButtonActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IsiMarketAdminFrame().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cashField;
    private javax.swing.JLabel cashLabel;
    private javax.swing.JButton createButton;
    private javax.swing.JTextField loginField;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JPanel newOperatorPanel;
    private javax.swing.JPanel operatorUpdate;
    private javax.swing.JTextField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JButton quitButton;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JButton updateButton;
    private javax.swing.JTextField updateCashField;
    private javax.swing.JLabel updateCashLabel;
    private javax.swing.JTextField updateLoginField;
    private javax.swing.JLabel updateLoginLabel;
    // End of variables declaration//GEN-END:variables
    
}
