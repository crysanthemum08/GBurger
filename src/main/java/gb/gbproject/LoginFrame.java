
package gb.gbproject;


import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;


public class LoginFrame extends javax.swing.JFrame implements ActionListener {

    
    
    public LoginFrame() {
        initComponents();
        setTitle("Gara's Burger");
        setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\Libraries\\Downloads\\Gblogo.png"));
       
        
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        Admin = new javax.swing.JPanel();
        loginlbl = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        user = new javax.swing.JTextField();
        pass = new javax.swing.JPasswordField();
        loginbtn = new javax.swing.JButton();
        userIcon = new javax.swing.JLabel();
        passIcon = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        ScLogo = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanel2.setBackground(new java.awt.Color(255, 135, 13));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Admin.setBackground(new java.awt.Color(255, 255, 255));
        Admin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        loginlbl.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        loginlbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loginlbl.setText("Admin");
        loginlbl.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        Admin.add(loginlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 110, 40));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Username:");
        Admin.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 100, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Password:");
        Admin.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 100, 30));
        Admin.add(user, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 220, 30));
        Admin.add(pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 220, 30));

        loginbtn.setBackground(new java.awt.Color(255, 135, 13));
        loginbtn.setText("Login");
        loginbtn.setBorder(null);
        loginbtn.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        loginbtn.setIconTextGap(10);
        loginbtn.addActionListener(this);
        Admin.add(loginbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 270, 110, 30));
        Admin.add(userIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 30, 30));
        Admin.add(passIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 30, 30));

        jCheckBox1.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jCheckBox1.setText("Show password");
        jCheckBox1.addActionListener(this);
        Admin.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, 110, -1));

        jPanel2.add(Admin, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 390, 360));
        jPanel2.add(ScLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 55, -1, -1));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("GARA's Burger");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 233, 200, 35));

        jLabel9.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("UM Canteen Cashiering System");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 238, -1));
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon("D:\\CCE104_Project\\CS26L_GbProject2024\\GBProject\\src\\main\\java\\gb\\gbproject\\icons\\ScLogo.png")); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 200, 200));

        getContentPane().add(jPanel2, "card3");

        pack();
        setLocationRelativeTo(null);
    }

    // Code for dispatching events from components to event handlers.

    public void actionPerformed(java.awt.event.ActionEvent evt) {
        if (evt.getSource() == loginbtn) {
            LoginFrame.this.loginbtnActionPerformed(evt);
        }
        else if (evt.getSource() == jCheckBox1) {
            LoginFrame.this.jCheckBox1ActionPerformed(evt);
        }
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if(jCheckBox1.isSelected()){
            pass.setEchoChar((char)0);
        }else{
            pass.setEchoChar(('*'));
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void loginbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginbtnActionPerformed

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gb", "root","");
            String sql = "SELECT * FROM gblogin WHERE username=? and password=? and usertype=?";
            //usern: Admin, pass: 1234 , usertype: Admin  - mySQL
            //usern: Admin, pass: 1234 , usertype: User   - mySQL
            PreparedStatement  pr = con.prepareStatement(sql);

            pr.setString(1, user.getText());
            pr.setString(2, pass.getText());
            pr.setString(3,("Admin"));
            ResultSet rs = pr.executeQuery();

            if(rs.next()){
              
                    TransFrame af = new TransFrame();
                    af.setVisible(true);
                    this.setVisible(false);
            }else{
                JOptionPane.showMessageDialog(null, "Invalid username or password! Try again.");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Not Matched!");
        }
        }

        /* if (user.getText().equals("Admin") && pass.getText().equals("12345")) {
            dispose();
             if(select.equals("Admin")) {
                dispose();
                new AdFrame().setVisible(true);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again!");
        }
    }//GEN-LAST:event_loginbtnActionPerformed

      if (user.getText().equals("Admin") && pass.getText().equals("12345")) {
            dispose();
            if(select.equals("Admin")) {
                dispose();
                new AdFrame().setVisible(true);
            }
            }else{
            JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again!");
        }
    }                                        
 
    /**
     * @param args the command line arguments
     */
  
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Admin;
    private javax.swing.JLabel ScLogo;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton loginbtn;
    private javax.swing.JLabel loginlbl;
    private javax.swing.JPasswordField pass;
    private javax.swing.JLabel passIcon;
    private javax.swing.JTextField user;
    private javax.swing.JLabel userIcon;
    // End of variables declaration//GEN-END:variables

   
}
