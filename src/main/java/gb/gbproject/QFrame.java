
package gb.gbproject;


import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

public class QFrame extends javax.swing.JFrame {
    public int queueNumber;
    UsFrame usFrame;
    Connection conn;
    ResultSet rs;
    
    public QFrame() {
        initComponents();
        setTitle("Gara's Burger");
        setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\Libraries\\Downloads\\Gblogo.png"));
        SDate();
        STime();
        
    }
    
    
    public void SDate(){
        
        Date d= new Date();
        SimpleDateFormat sdf= new SimpleDateFormat("MM/dd/YY");
        String dt= sdf.format(d);
        datetxt.setText(dt);
    }
    
    public void STime(){
        
        new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                Date d= new Date();
                SimpleDateFormat s= new SimpleDateFormat("hh:mm a");
                String tm= s.format(d);
                
                timetxt.setText(tm);
            }
        }).start();
    }
     private String getDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        return sdf.format(date);
    }

    private String getTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        return sdf.format(date);
    }


    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        q = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        time = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        datetxt = new javax.swing.JTextField();
        timetxt = new javax.swing.JTextField();
        teln = new javax.swing.JLabel();
        teln1 = new javax.swing.JLabel();
        Instagram = new javax.swing.JLabel();
        Twitter = new javax.swing.JLabel();
        Facebook = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel5 = new javax.swing.JLabel();
        servingQ = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        ScLogo = new javax.swing.JLabel();
        NewOrderbtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("frame");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jLabel3.setFont(new java.awt.Font("Segoe UI Historic", 1, 26)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Welcome to G A R A's Burger ");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 80));

        jPanel1.setBackground(new java.awt.Color(255, 135, 13));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 100, 570));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 120, 580));

        jTabbedPane1.setBackground(new java.awt.Color(255, 102, 0));

        q.setBackground(new java.awt.Color(255, 255, 255));
        q.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));

        time.setFont(new java.awt.Font("Square721 BT", 0, 18)); // NOI18N
        time.setForeground(new java.awt.Color(255, 255, 255));
        time.setText("Time:");

        date.setFont(new java.awt.Font("Square721 BT", 0, 18)); // NOI18N
        date.setForeground(new java.awt.Color(255, 255, 255));
        date.setText("Date:");

        datetxt.setBackground(new java.awt.Color(51, 51, 51));
        datetxt.setFont(new java.awt.Font("Square721 BT", 0, 18)); // NOI18N
        datetxt.setForeground(new java.awt.Color(255, 255, 255));
        datetxt.setBorder(null);

        timetxt.setBackground(new java.awt.Color(51, 51, 51));
        timetxt.setFont(new java.awt.Font("Square721 BT", 0, 18)); // NOI18N
        timetxt.setForeground(new java.awt.Color(255, 255, 255));
        timetxt.setBorder(null);

        teln.setFont(new java.awt.Font("Square721 BT", 0, 18)); // NOI18N
        teln.setForeground(new java.awt.Color(255, 255, 255));
        teln.setText("225 - 09 - 36");

        teln1.setFont(new java.awt.Font("Square721 BT", 0, 18)); // NOI18N
        teln1.setForeground(new java.awt.Color(255, 255, 255));
        teln1.setText("Telephone Number:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(date)
                .addGap(18, 18, 18)
                .addComponent(datetxt, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(time)
                .addGap(18, 18, 18)
                .addComponent(timetxt, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(teln1)
                .addGap(18, 18, 18)
                .addComponent(teln)
                .addGap(64, 64, 64)
                .addComponent(Instagram)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Facebook)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Twitter)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(teln1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(timetxt)
            .addComponent(teln, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Twitter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Instagram, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(Facebook, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(datetxt)
        );

        q.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 1040, -1));

        jDesktopPane1.setBackground(new java.awt.Color(51, 51, 51));
        jDesktopPane1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Swis721 Blk BT", 3, 48)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 204, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Preparing to serve");
        jDesktopPane1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 470, 66));

        servingQ.setFont(new java.awt.Font("Rockwell Extra Bold", 1, 89)); // NOI18N
        servingQ.setForeground(new java.awt.Color(255, 255, 255));
        servingQ.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jDesktopPane1.add(servingQ, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 210, 151));

        q.add(jDesktopPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 90, 500, 400));

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 1, 36)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText(" Queuing Area");
        q.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 320, 60));

        ScLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ScLogo.setIcon(new javax.swing.ImageIcon("D:\\CCE104_Project\\CS26L_GbProject2024\\GBProject\\src\\main\\java\\gb\\gbproject\\icons\\ScLogo.png")); // NOI18N
        ScLogo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        q.add(ScLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-1, 89, 510, 349));

        NewOrderbtn.setBackground(new java.awt.Color(255, 135, 13));
        NewOrderbtn.setFont(new java.awt.Font("Segoe UI Historic", 1, 16)); // NOI18N
        NewOrderbtn.setForeground(new java.awt.Color(255, 255, 255));
        NewOrderbtn.setText("New order");
        NewOrderbtn.setBorder(null);
        NewOrderbtn.setIconTextGap(5);
        NewOrderbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewOrderbtnActionPerformed(evt);
            }
        });
        q.add(NewOrderbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 530, 50));

        jTabbedPane1.addTab("Queue", q);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 1080, 610));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    public void updateQueueNumber(int newQueueNumber) {
        this.queueNumber = newQueueNumber;
        servingQ.setText(Integer.toString(queueNumber)); // Update the queue number in QFrame
    }
   
    private void NewOrderbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewOrderbtnActionPerformed
    
        UsFrame frame1 = new UsFrame();
        // Set the count in Frame1 to the current count in Frame2
        frame1.setCount(queueNumber);
        updateQueueNumber(queueNumber);
        // Make Frame1 visible
        frame1.setVisible(true);
    }//GEN-LAST:event_NewOrderbtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Facebook;
    private javax.swing.JLabel Instagram;
    private javax.swing.JButton NewOrderbtn;
    private javax.swing.JLabel ScLogo;
    private javax.swing.JLabel Twitter;
    private javax.swing.JLabel date;
    private javax.swing.JTextField datetxt;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel q;
    private javax.swing.JLabel servingQ;
    private javax.swing.JLabel teln;
    private javax.swing.JLabel teln1;
    private javax.swing.JLabel time;
    private javax.swing.JTextField timetxt;
    // End of variables declaration//GEN-END:variables


   
}
