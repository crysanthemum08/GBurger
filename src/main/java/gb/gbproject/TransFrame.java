
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

public class TransFrame extends javax.swing.JFrame {
    public int queueNumber = 1;
    UsFrame usFrame;
    public int clicked = 1 ;
    Connection conn;
    ResultSet rs;
    
    
    
    
    
    
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
    
    public TransFrame() {
    initComponents();
    setTitle("Gara's Burger");
    setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\Libraries\\Downloads\\Gblogo.png"));
    SDate();
    STime();
    
    // Fetch and populate the stock data in STable
    populateStocksTable();
}
    
    private void populateStocksTable() {
   try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gb", "root", "");
        Statement stmt = conn.createStatement();

        // Query to retrieve item names and their stocks from the database
        String sql = "SELECT item_name, stocks FROM gb_items";
        ResultSet rs = stmt.executeQuery(sql);

        DefaultTableModel model = (DefaultTableModel) STable.getModel();

        while (rs.next()) {
            String itemName = rs.getString("item_name").trim(); // Trim to remove spaces

            for (int i = 0; i < model.getRowCount(); i++) {
                String tableName = model.getValueAt(i, 0).toString().trim(); // Trim to remove spaces
                if (itemName.equalsIgnoreCase(tableName)) { // Use equalsIgnoreCase for case-insensitive comparison
                    int stocks = rs.getInt("stocks");
                    model.setValueAt(stocks, i, 1);
                    break; // Exit the loop once the row is updated
                }
            }
        }

        conn.close();
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
}
    

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        AdminL = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        logoutbtn = new javax.swing.JButton();
        tbtn = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        stocks = new javax.swing.JPanel();
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
        jScrollPane2 = new javax.swing.JScrollPane();
        STable = new javax.swing.JTable();
        updateBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        t = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableTrn = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

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

        AdminL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AdminL.setIcon(new javax.swing.ImageIcon("D:\\CCE104_Project\\CS26L_GbProject2024\\GBProject\\src\\main\\java\\gb\\gbproject\\icons\\AdminL.png")); // NOI18N
        jPanel1.add(AdminL, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 108, 100));

        jLabel2.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Admin");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 108, -1));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 108, 10));

        logoutbtn.setBackground(new java.awt.Color(255, 0, 0));
        logoutbtn.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        logoutbtn.setForeground(new java.awt.Color(255, 255, 255));
        logoutbtn.setText("Logout");
        logoutbtn.setBorder(null);
        logoutbtn.setIconTextGap(5);
        logoutbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutbtnActionPerformed(evt);
            }
        });
        jPanel1.add(logoutbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 90, 28));

        tbtn.setBackground(new java.awt.Color(51, 51, 51));
        tbtn.setForeground(new java.awt.Color(255, 255, 255));
        tbtn.setText("Transactions");
        tbtn.setBorder(null);
        tbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbtnActionPerformed(evt);
            }
        });
        jPanel1.add(tbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 90, 28));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 140, 580));

        jTabbedPane1.setBackground(new java.awt.Color(255, 102, 0));

        stocks.setBackground(new java.awt.Color(255, 255, 255));
        stocks.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
                    .addComponent(Instagram, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Facebook, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(datetxt)
        );

        stocks.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 539, 1080, 40));

        STable.setBackground(new java.awt.Color(255, 255, 255));
        STable.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        STable.setForeground(new java.awt.Color(0, 0, 0));
        STable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Chili Burger w/ Pepper Relish", null},
                {"Lamb and Tomato Stuff Burger", null},
                {"Crunchy Chicken and Fish Burger", null},
                {"Chicken Feta Cheese Burger", null},
                {"Lentil and Mushroom Burger", null},
                {"Stuffed Bean Burger", null},
                {"Lamb Burger w/ Radish Slaw", null},
                {"Potato Corn Burger", null},
                {"Supreme Veggie Burger", null},
                {"Butter Chicken Twin Burgers", null},
                {"Pizza Burger", null},
                {"Whoppie Burger", null},
                {"Coke", null},
                {"Sprite", null},
                {"Water", null},
                {"Nestea", null}
            },
            new String [] {
                "Item Name", "Stocks"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        STable.setToolTipText("");
        STable.setAlignmentX(2.0F);
        STable.setAlignmentY(2.0F);
        STable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        STable.setRowHeight(30);
        STable.setShowGrid(true);
        STable.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(STable);
        if (STable.getColumnModel().getColumnCount() > 0) {
            STable.getColumnModel().getColumn(0).setResizable(false);
            STable.getColumnModel().getColumn(0).setPreferredWidth(800);
            STable.getColumnModel().getColumn(1).setPreferredWidth(5);
        }

        stocks.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 990, 380));

        updateBtn.setBackground(new java.awt.Color(255, 153, 0));
        updateBtn.setForeground(new java.awt.Color(255, 255, 255));
        updateBtn.setText("Update Stock");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });
        stocks.add(updateBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 460, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setText("Stock Management");
        stocks.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, 220, -1));

        jTabbedPane1.addTab("Queue", stocks);

        t.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        TableTrn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        TableTrn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Receipt Number", "Date", "Time", "Items", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        TableTrn.setEnabled(false);
        TableTrn.setSelectionBackground(new java.awt.Color(255, 135, 13));
        jScrollPane1.setViewportView(TableTrn);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI Semilight", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("List of Transactions");

        javax.swing.GroupLayout tLayout = new javax.swing.GroupLayout(t);
        t.setLayout(tLayout);
        tLayout.setHorizontalGroup(
            tLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1068, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(tLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1030, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tLayout.setVerticalGroup(
            tLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        jTabbedPane1.addTab("tab2", t);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 1080, 610));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
 
    private void logoutbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutbtnActionPerformed
     ChooseFrame log=new ChooseFrame();
     log.setVisible(true); 
     dispose();
    }//GEN-LAST:event_logoutbtnActionPerformed

    private void tbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbtnActionPerformed
        jTabbedPane1.setSelectedIndex(1);
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gb", "root", "");
            Statement sm = conn.createStatement();
            String str = "SELECT * FROM transactions";
            rs = sm.executeQuery(str);
            ResultSetMetaData RSMD = rs.getMetaData();
            DefaultTableModel dtm = (DefaultTableModel)TableTrn.getModel();
            
            int cols = RSMD.getColumnCount();
            String[] coln = new String[cols];
            for(int i=0; i<cols; i++)
                coln[i] = RSMD.getColumnName(i+1);
                dtm.setColumnIdentifiers(coln);
            
                String rctnm,dte,tme,itm,tot;
                while(rs.next()){
                    rctnm=rs.getString(1);
                    dte=rs.getString(2);
                    tme=rs.getString(3);
                    itm=rs.getString(4);
                    tot=rs.getString(5);
                    String[] row ={rctnm,dte,tme,itm,tot};
                    dtm.addRow(row);
                }
                sm.close();
                conn.close();
            
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println("ERROR: "+ex.getMessage());
        }
    }//GEN-LAST:event_tbtnActionPerformed
    
    
    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gb", "root", "");

        DefaultTableModel model = (DefaultTableModel) STable.getModel();

        for (int row = 0; row < model.getRowCount(); row++) {
            String itemName = (String) model.getValueAt(row, 0);
            Integer stocksValue = (Integer) model.getValueAt(row, 1);

            // Check for null values and set them to zero
            int newStocks = (stocksValue != null) ? stocksValue : 0;

            // Update the stock quantity in the database
            String updateSql = "UPDATE gb_items SET stocks = ? WHERE item_name = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateSql);
            updateStmt.setInt(1, newStocks);
            updateStmt.setString(2, itemName);
            updateStmt.executeUpdate();
        }

        conn.close();

        JOptionPane.showMessageDialog(null, "Stocks updated successfully.");
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
    }
    }//GEN-LAST:event_updateBtnActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TransFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AdminL;
    private javax.swing.JLabel Facebook;
    private javax.swing.JLabel Instagram;
    private javax.swing.JTable STable;
    private javax.swing.JTable TableTrn;
    private javax.swing.JLabel Twitter;
    private javax.swing.JLabel date;
    private javax.swing.JTextField datetxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton logoutbtn;
    private javax.swing.JPanel stocks;
    private javax.swing.JPanel t;
    private javax.swing.JButton tbtn;
    private javax.swing.JLabel teln;
    private javax.swing.JLabel teln1;
    private javax.swing.JLabel time;
    private javax.swing.JTextField timetxt;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables


   
}
