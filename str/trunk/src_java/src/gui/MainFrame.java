/*
 * MainFrame.java
 *
 * Created on 12 mars 2007, 14:20
 */

package gui;

/**
 *
 * @author  garwind
 */
public class MainFrame extends javax.swing.JFrame {
    
	
	private static final long serialVersionUID = -6776220531901402301L;
	
	/** Creates new form MainFrame */
    public MainFrame() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        menuBar = new javax.swing.JMenuBar();
        applicationMenu = new javax.swing.JMenu();
        exitApplicationItem = new javax.swing.JMenuItem();
        debugMenuItem = new javax.swing.JCheckBoxMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("WISI BUS NAV");
        applicationMenu.setText("Application");
        exitApplicationItem.setText("Exit Application");
        exitApplicationItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitApplicationItemActionPerformed(evt);
            }
        });

        applicationMenu.add(exitApplicationItem);

        debugMenuItem.setText("Show Debug Panel");
        applicationMenu.add(debugMenuItem);

        menuBar.add(applicationMenu);

        setJMenuBar(menuBar);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 557, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 396, Short.MAX_VALUE)
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitApplicationItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitApplicationItemActionPerformed
        System.out.println("exiting ...");
        System.exit(0);
    }//GEN-LAST:event_exitApplicationItemActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu applicationMenu;
    private javax.swing.JCheckBoxMenuItem debugMenuItem;
    private javax.swing.JMenuItem exitApplicationItem;
    private javax.swing.JMenuBar menuBar;
    // End of variables declaration//GEN-END:variables
    
}
