/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plurker.ui;

import plurker.source.PlurkSourcer;
import com.google.jplurk_oauth.skeleton.PlurkOAuthConfig;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.scribe.model.OAuthConfig;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuth10aServiceImpl;

/**
 *
 * @author skyforce
 */
public class OAuthDialog extends javax.swing.JDialog {

    /**
     * Creates new form OAuthDialog
     */
    public OAuthDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Plurk授權");
        setUndecorated(true);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));

        jLabel1.setText("取得Plurk授權需要三個步驟:");
        getContentPane().add(jLabel1);

        jLabel2.setText("1. 登入Plurk(若已登入則可略過)");
        getContentPane().add(jLabel2);

        jButton1.setText("登入Plurk");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);

        jLabel3.setText("2. 授權 \"衝噗浪\" 應用服務存取你的噗浪帳號");
        getContentPane().add(jLabel3);

        jButton2.setText("同意授權");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);

        jLabel4.setText("3. 輸入認證碼");
        getContentPane().add(jLabel4);

        jTextField1.setPreferredSize(new java.awt.Dimension(100, 25));
        jPanel1.add(jTextField1);

        jButton3.setText("確定");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            URI uri = new URI("http://www.plurk.com/Users/showLogin?login_return_url=/t/Taiwan/");
            Desktop.getDesktop().browse(uri);
        } catch (IOException | URISyntaxException ex) {
            Logger.getLogger(OAuthDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    private OAuth10aServiceImpl service;
    private Token requestToken;
    public Token accessToken;
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        PlurkOAuthConfig api = new PlurkOAuthConfig();
        OAuthConfig config = new OAuthConfig(PlurkSourcer.API_KEY, PlurkSourcer.APP_SECRET);
        service = new OAuth10aServiceImpl(api, config);
        requestToken = service.getRequestToken();
        String authorizationUrl = service.getAuthorizationUrl(requestToken);

        try {
            URI uri = new URI(authorizationUrl);
            Desktop.getDesktop().browse(uri);
        } catch (IOException | URISyntaxException ex) {
            Logger.getLogger(OAuthDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String code = jTextField1.getText();
        Verifier verifier = new Verifier(code);
        if (null != service) {
            accessToken = service.getAccessToken(requestToken, verifier);
            this.setVisible(false);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OAuthDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OAuthDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OAuthDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OAuthDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the dialog
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                OAuthDialog dialog = new OAuthDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
