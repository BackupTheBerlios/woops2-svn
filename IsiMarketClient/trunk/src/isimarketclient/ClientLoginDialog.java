/*
 * IsiMarketClientAboutBox.java
 */

package isimarketclient;

import isimarket.model.Wallet;
import isimarket.servants.walletservant.WalletServantPackage.UnknownOperatorException;
import isimarket.servants.walletservant.WalletServantPackage.WrongPasswordException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jdesktop.application.Action;

public class ClientLoginDialog extends javax.swing.JDialog {
    
    public ClientLoginDialog(java.awt.Frame parent) {
        super(parent);
        initComponents();
        getRootPane().setDefaultButton(connectionButton);
    }

    @Action public void closeAboutBox() {
        setVisible(false);
        this.dispose();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        quitButton = new javax.swing.JButton();
        javax.swing.JLabel titleLabel = new javax.swing.JLabel();
        connectionButton = new javax.swing.JButton();
        loginTextField = new javax.swing.JTextField();
        userLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        logoLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setName("aboutBox"); // NOI18N
        setResizable(false);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(isimarketclient.IsiMarketClient.class).getContext().getActionMap(ClientLoginDialog.class, this);
        quitButton.setAction(actionMap.get("closeAboutBox")); // NOI18N
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(isimarketclient.IsiMarketClient.class).getContext().getResourceMap(ClientLoginDialog.class);
        quitButton.setText(resourceMap.getString("quitButton.text")); // NOI18N
        quitButton.setName("quitButton"); // NOI18N
        quitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitButtonActionPerformed(evt);
            }
        });

        titleLabel.setFont(titleLabel.getFont().deriveFont(titleLabel.getFont().getStyle() | java.awt.Font.BOLD, titleLabel.getFont().getSize()+4));
        titleLabel.setText(resourceMap.getString("Application.title")); // NOI18N
        titleLabel.setName("titleLabel"); // NOI18N

        connectionButton.setText(resourceMap.getString("connectionButton.text")); // NOI18N
        connectionButton.setName("connectionButton"); // NOI18N
        connectionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectionButtonActionPerformed(evt);
            }
        });

        loginTextField.setText(resourceMap.getString("loginTextField.text")); // NOI18N
        loginTextField.setName("loginTextField"); // NOI18N

        userLabel.setText(resourceMap.getString("userLabel.text")); // NOI18N
        userLabel.setName("userLabel"); // NOI18N

        passwordLabel.setText(resourceMap.getString("passwordLabel.text")); // NOI18N
        passwordLabel.setName("passwordLabel"); // NOI18N

        logoLabel.setIcon(resourceMap.getIcon("logoLabel.icon")); // NOI18N
        logoLabel.setText(resourceMap.getString("logoLabel.text")); // NOI18N
        logoLabel.setName("logoLabel"); // NOI18N

        passwordField.setText(resourceMap.getString("passwordField.text")); // NOI18N
        passwordField.setName("passwordField"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(connectionButton)
                        .addGap(2, 2, 2)
                        .addComponent(quitButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(logoLabel)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(passwordLabel)
                                    .addComponent(userLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                                    .addComponent(loginTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(116, 116, 116)
                                .addComponent(titleLabel)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(loginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(userLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(passwordLabel)
                            .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(quitButton)
                            .addComponent(connectionButton)))
                    .addComponent(logoLabel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void connectionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectionButtonActionPerformed
        String login = loginTextField.getText();
        String pwd = passwordField.getText();
        
        IsiMarketClient main = IsiMarketClient.getApplication();
        
        if (login.equals("admin")){
            main.setConnectionType(IsiMarketConstants.UserType.ADMIN);
            this.dispose();
        }
        else {
            try {
                main.setConnectionType(IsiMarketConstants.UserType.OPERATOR);
                Wallet w = main.getCorbaClient().getWalletServant().authentication(login, pwd);
                IsiMarketClientModel.wallet = w;
                IsiMarketClientModel.login = login;
                this.dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erreur :"+e, "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
}//GEN-LAST:event_connectionButtonActionPerformed

    private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_quitButtonActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton connectionButton;
    private javax.swing.JTextField loginTextField;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JButton quitButton;
    private javax.swing.JLabel userLabel;
    // End of variables declaration//GEN-END:variables
    
}
