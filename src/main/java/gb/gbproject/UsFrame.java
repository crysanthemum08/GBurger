
package gb.gbproject;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;



public class UsFrame extends javax.swing.JFrame {
    
    QFrame adFrame;
    public int queueNumber = 0;
    private String date;
    private String time;
    Connection conn;
    Statement sm;
    PreparedStatement ps;
    ResultSet rs;
    
    String dbItem;
    String itemName;
    int dbStocks;
    double itemPrice;
    double dbPrice;
  
    
    public void updateQueueNumber(int newQueueNumber) {
        this.queueNumber = newQueueNumber;
        txtQueue.setText(Integer.toString(queueNumber)); // Update the queue number in QFrame
    }
   int min = 50000;
    int max = 100000;
    int random_int;  //= (int)Math.floor(Math.random()*(max-min+1)+min);
   public UsFrame(int queueNumber, String date, String time, QFrame adFrame) {
        initComponents();
        this.queueNumber = queueNumber; // Store the passed queue number
        this.adFrame = adFrame;
        txtQueue.setText(String.valueOf(queueNumber));   
        dateni.setText(date);
        timeni.setText(time);
    }

   
    
   
    public UsFrame() {
        initComponents();
        SDate();
        STime();
        setTitle("Gara's Burger");
        setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\Libraries\\Downloads\\Gblogo.png"));
        table1.getColumnModel().getColumn(0).setPreferredWidth(200);
    
    }

    public void addTable(String Name, double Price, int quantity ){
        
    double totprc = Price * quantity;
    System.out.println(totprc);

    // Add to cart
    DefaultTableModel tm = (DefaultTableModel) table1.getModel();
    Vector v = new Vector();
    v.add(Name);
    v.add(quantity);
    v.add(totprc);
    tm.addRow(v);

    sub_total();
            
    }
    
    public void sub_total(){
        
        int numbrow= table1.getRowCount();
        double total= 0;
        
        for(int i=0; i<numbrow; i++){
            double value=Double.valueOf(table1.getValueAt(i,2).toString());
            total += value;
        }
        DecimalFormat df= new DecimalFormat("000.00");
        String d1=df.format(total);
    //sub total
        subtxt.setText(d1); 
    //total
        double dsc=Double.valueOf(discounttxt.getText());
        double tax=Double.valueOf(taxtxt.getText());
        double tot_value= total + tax - dsc;
        
        DecimalFormat dft= new DecimalFormat("000.00");
        String d2=dft.format(tot_value);
        
        totaltxt.setText(d2);     


        
       


    }
    
    public void receipt_bill(){
        
    //Data & Time
        Date dt=new Date();
            SimpleDateFormat dtf=new SimpleDateFormat("MM/dd/Y");
            SimpleDateFormat tmf=new SimpleDateFormat("hh:mm a");
                String date=dtf.format(dt);
                String time=tmf.format(dt);
        
        String num = txtQueue.getText();
        Integer number =Integer.parseInt(num);
        number=number+1;
        String s = number.toString();
        
        int min = 50000;
        int max = 100000;
        random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
        
        jTextPane1.setText("\n\t\t GARA'S Burger Reciept \n");
        jTextPane1.setText(jTextPane1.getText() + "\t                 Agila Street, Davao City \n");
        jTextPane1.setText(jTextPane1.getText() + "\t                      Tel. #: 225-09-36 \n\n");
        jTextPane1.setText(jTextPane1.getText() + "\n Receipt No.: " +random_int);
        jTextPane1.setText(jTextPane1.getText() + "\n Date: " +date+ "\t\t         Queue #: " + s);
        jTextPane1.setText(jTextPane1.getText() + "\n Time: " +time+ "\n" );
        
        jTextPane1.setText(jTextPane1.getText() + " ------------------------------------------------------------------------------------ \n ");
        jTextPane1.setText(jTextPane1.getText() + " Item \t\t\t Qty \t Price ");
        jTextPane1.setText(jTextPane1.getText() + " ------------------------------------------------------------------------------------ \n ");
        
        //Add JTable Product List
        DefaultTableModel tm= (DefaultTableModel) table1.getModel();
            for(int i=0; i<table1.getRowCount(); i++){
                
                String itm=tm.getValueAt(i, 0).toString();  //item
                String qt=tm.getValueAt(i, 1).toString();   //Qty
                String prc=tm.getValueAt(i, 2).toString();  //Price
              
            jTextPane1.setText(jTextPane1.getText() +itm+ "\t" +qt+ "\t" +prc+ "\n" );  
            }
        //End of loop
        jTextPane1.setText(jTextPane1.getText() + " ------------------------------------------------------------------------------------ \n ");
        jTextPane1.setText(jTextPane1.getText() + "Sub Total  \t\t\t\t" + "₱ " + subtxt.getText() + "\n");
        jTextPane1.setText(jTextPane1.getText() + " Tax             \t\t\t\t" + "%"+ taxtxt.getText() + "\n");    
        jTextPane1.setText(jTextPane1.getText() + " Discount   \t\t\t\t" + "%" + discounttxt.getText());
        jTextPane1.setText(jTextPane1.getText() + " ------------------------------------------------------------------------------------ \n ");
        jTextPane1.setText(jTextPane1.getText() + "Total Amount Due \t\t\t" + "₱ " + totaltxt.getText());
        jTextPane1.setText(jTextPane1.getText() + "\n\n Change  \t\t\t\t" + "₱ " +changetxt.getText());
        jTextPane1.setText(jTextPane1.getText() + "\n CASH   \t\t\t\t" + "₱ " + cashtxt.getText());
        jTextPane1.setText(jTextPane1.getText() + "\n\n********************************************************************  \n ");
        jTextPane1.setText(jTextPane1.getText() + "\t          THANK YOU COME AGAIN ");
        jTextPane1.setText(jTextPane1.getText() + "\n********************************************************************  \n ");

    }
   
    public void SDate(){
        Date d= new Date();
        SimpleDateFormat sdf= new SimpleDateFormat("MM/dd/YY");
        String dt= sdf.format(d);
        dateni.setText(dt);
    }
    
    public void STime(){
        new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                Date d= new Date();
                SimpleDateFormat s= new SimpleDateFormat("hh:mm a");
                String tm= s.format(d);
                
                timeni.setText(tm);
            }
        }).start();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        userL = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        logoutbtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        dateni = new javax.swing.JTextField();
        timeni = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        b1 = new javax.swing.JButton();
        b2 = new javax.swing.JButton();
        b3 = new javax.swing.JButton();
        b4 = new javax.swing.JButton();
        b5 = new javax.swing.JButton();
        b6 = new javax.swing.JButton();
        b7 = new javax.swing.JButton();
        b8 = new javax.swing.JButton();
        b9 = new javax.swing.JButton();
        b10 = new javax.swing.JButton();
        b11 = new javax.swing.JButton();
        b12 = new javax.swing.JButton();
        d1 = new javax.swing.JButton();
        d2 = new javax.swing.JButton();
        d3 = new javax.swing.JButton();
        d4 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        subtxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        totaltxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cashtxt = new javax.swing.JTextField();
        paybtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        taxtxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        discounttxt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        changetxt = new javax.swing.JTextField();
        printbtn = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtQueue = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        removebtn = new javax.swing.JButton();
        usLogo = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        JPanel9 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 135, 13));
        jPanel1.setPreferredSize(new java.awt.Dimension(100, 600));

        jLabel10.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("User");

        userL.setForeground(new java.awt.Color(255, 255, 255));
        userL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userL.setIcon(new javax.swing.ImageIcon("D:\\CCE104_Project\\CS26L_GbProject2024\\GBProject\\src\\main\\java\\gb\\gbproject\\icons\\userL.png")); // NOI18N

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        logoutbtn.setBackground(new java.awt.Color(255, 0, 0));
        logoutbtn.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        logoutbtn.setForeground(new java.awt.Color(255, 255, 255));
        logoutbtn.setText("Logout");
        logoutbtn.setBorder(null);
        logoutbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addComponent(userL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 15, Short.MAX_VALUE)
                        .addComponent(logoutbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(userL, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(logoutbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(430, 430, 430))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 120, 580));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setFocusCycleRoot(true);
        jPanel2.setPreferredSize(new java.awt.Dimension(1050, 100));

        jLabel12.setFont(new java.awt.Font("Segoe UI Historic", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Welcome to G A R A's Burger");
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel12.setIconTextGap(5);

        dateni.setBackground(new java.awt.Color(51, 51, 51));
        dateni.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        dateni.setForeground(new java.awt.Color(255, 255, 255));
        dateni.setBorder(null);

        timeni.setBackground(new java.awt.Color(51, 51, 51));
        timeni.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        timeni.setForeground(new java.awt.Color(255, 255, 255));
        timeni.setBorder(null);
        timeni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeniActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Date:");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Time:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(127, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(564, 564, 564)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(timeni, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateni, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dateni)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(timeni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 12, Short.MAX_VALUE)))
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 70));

        jTabbedPane2.setBackground(new java.awt.Color(255, 153, 51));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        b1.setIcon(new javax.swing.ImageIcon("D:\\CCE104_Project\\CS26L_GbProject2024\\GBProject\\src\\main\\java\\gb\\gbproject\\menu\\1.png")); // NOI18N
        b1.setPreferredSize(new java.awt.Dimension(80, 100));
        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b1ActionPerformed(evt);
            }
        });

        b2.setIcon(new javax.swing.ImageIcon("D:\\CCE104_Project\\CS26L_GbProject2024\\GBProject\\src\\main\\java\\gb\\gbproject\\menu\\2.png")); // NOI18N
        b2.setPreferredSize(new java.awt.Dimension(80, 100));
        b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });

        b3.setIcon(new javax.swing.ImageIcon("D:\\CCE104_Project\\CS26L_GbProject2024\\GBProject\\src\\main\\java\\gb\\gbproject\\menu\\3.png")); // NOI18N
        b3.setPreferredSize(new java.awt.Dimension(80, 100));
        b3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b3ActionPerformed(evt);
            }
        });

        b4.setIcon(new javax.swing.ImageIcon("D:\\CCE104_Project\\CS26L_GbProject2024\\GBProject\\src\\main\\java\\gb\\gbproject\\menu\\4.png")); // NOI18N
        b4.setPreferredSize(new java.awt.Dimension(80, 100));
        b4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b4ActionPerformed(evt);
            }
        });

        b5.setIcon(new javax.swing.ImageIcon("D:\\CCE104_Project\\CS26L_GbProject2024\\GBProject\\src\\main\\java\\gb\\gbproject\\menu\\5.png")); // NOI18N
        b5.setPreferredSize(new java.awt.Dimension(80, 100));
        b5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b5ActionPerformed(evt);
            }
        });

        b6.setIcon(new javax.swing.ImageIcon("D:\\CCE104_Project\\CS26L_GbProject2024\\GBProject\\src\\main\\java\\gb\\gbproject\\menu\\6.png")); // NOI18N
        b6.setPreferredSize(new java.awt.Dimension(80, 100));
        b6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b6ActionPerformed(evt);
            }
        });

        b7.setIcon(new javax.swing.ImageIcon("D:\\CCE104_Project\\CS26L_GbProject2024\\GBProject\\src\\main\\java\\gb\\gbproject\\menu\\7.png")); // NOI18N
        b7.setPreferredSize(new java.awt.Dimension(80, 100));
        b7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b7ActionPerformed(evt);
            }
        });

        b8.setIcon(new javax.swing.ImageIcon("D:\\CCE104_Project\\CS26L_GbProject2024\\GBProject\\src\\main\\java\\gb\\gbproject\\menu\\8.png")); // NOI18N
        b8.setPreferredSize(new java.awt.Dimension(80, 100));
        b8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b8ActionPerformed(evt);
            }
        });

        b9.setIcon(new javax.swing.ImageIcon("D:\\CCE104_Project\\CS26L_GbProject2024\\GBProject\\src\\main\\java\\gb\\gbproject\\menu\\9.png")); // NOI18N
        b9.setPreferredSize(new java.awt.Dimension(80, 100));
        b9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b9ActionPerformed(evt);
            }
        });

        b10.setIcon(new javax.swing.ImageIcon("D:\\CCE104_Project\\CS26L_GbProject2024\\GBProject\\src\\main\\java\\gb\\gbproject\\menu\\10.png")); // NOI18N
        b10.setPreferredSize(new java.awt.Dimension(80, 100));
        b10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b10ActionPerformed(evt);
            }
        });

        b11.setIcon(new javax.swing.ImageIcon("D:\\CCE104_Project\\CS26L_GbProject2024\\GBProject\\src\\main\\java\\gb\\gbproject\\menu\\11.png")); // NOI18N
        b11.setPreferredSize(new java.awt.Dimension(80, 100));
        b11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b11ActionPerformed(evt);
            }
        });

        b12.setIcon(new javax.swing.ImageIcon("D:\\CCE104_Project\\CS26L_GbProject2024\\GBProject\\src\\main\\java\\gb\\gbproject\\menu\\12.png")); // NOI18N
        b12.setPreferredSize(new java.awt.Dimension(80, 100));
        b12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b12ActionPerformed(evt);
            }
        });

        d1.setIcon(new javax.swing.ImageIcon("D:\\CCE104_Project\\CS26L_GbProject2024\\GBProject\\src\\main\\java\\gb\\gbproject\\menu\\coke.png")); // NOI18N
        d1.setPreferredSize(new java.awt.Dimension(80, 100));
        d1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                d1ActionPerformed(evt);
            }
        });

        d2.setIcon(new javax.swing.ImageIcon("D:\\CCE104_Project\\CS26L_GbProject2024\\GBProject\\src\\main\\java\\gb\\gbproject\\menu\\Sprite.png")); // NOI18N
        d2.setPreferredSize(new java.awt.Dimension(80, 100));
        d2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                d2ActionPerformed(evt);
            }
        });

        d3.setIcon(new javax.swing.ImageIcon("D:\\Libraries\\Downloads\\water.png")); // NOI18N
        d3.setPreferredSize(new java.awt.Dimension(80, 100));
        d3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                d3ActionPerformed(evt);
            }
        });

        d4.setIcon(new javax.swing.ImageIcon("D:\\CCE104_Project\\CS26L_GbProject2024\\GBProject\\src\\main\\java\\gb\\gbproject\\menu\\nestea.png")); // NOI18N
        d4.setPreferredSize(new java.awt.Dimension(80, 100));
        d4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                d4ActionPerformed(evt);
            }
        });

        table1.setAutoCreateRowSorter(true);
        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item", "Qty", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        table1.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(table1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanel7.setBackground(new java.awt.Color(51, 51, 51));
        jPanel7.setForeground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sub Total:");
        jPanel7.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, 27));

        subtxt.setEditable(false);
        subtxt.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        subtxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        subtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subtxtActionPerformed(evt);
            }
        });
        jPanel7.add(subtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 6, 130, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Total:");
        jPanel7.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 52, 80, -1));

        totaltxt.setEditable(false);
        totaltxt.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        totaltxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        totaltxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totaltxtActionPerformed(evt);
            }
        });
        jPanel7.add(totaltxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 49, 130, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Cash:");
        jPanel7.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 90, 80, 30));

        cashtxt.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        cashtxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel7.add(cashtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 130, 30));

        paybtn.setBackground(new java.awt.Color(0, 204, 0));
        paybtn.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        paybtn.setForeground(new java.awt.Color(255, 255, 255));
        paybtn.setText("Pay");
        paybtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        paybtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                paybtnMouseReleased(evt);
            }
        });
        paybtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paybtnActionPerformed(evt);
            }
        });
        paybtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                paybtnKeyReleased(evt);
            }
        });
        jPanel7.add(paybtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 81, 110, 40));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("Tax:");
        jLabel4.setFocusable(false);
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel7.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, -1, 30));

        taxtxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        taxtxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        taxtxt.setText("0");
        taxtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                taxtxtActionPerformed(evt);
            }
        });
        taxtxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                taxtxtKeyReleased(evt);
            }
        });
        jPanel7.add(taxtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, 100, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setText("Senior Discount:");
        jPanel7.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 120, 20));

        discounttxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        discounttxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        discounttxt.setText("0");
        discounttxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                discounttxtKeyReleased(evt);
            }
        });
        jPanel7.add(discounttxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 80, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("₱");
        jPanel7.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, 20, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setText("Change:");
        jPanel7.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, -1, 30));

        changetxt.setEditable(false);
        changetxt.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        changetxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel7.add(changetxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, 100, 30));

        printbtn.setBackground(new java.awt.Color(255, 135, 13));
        printbtn.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        printbtn.setForeground(new java.awt.Color(255, 255, 255));
        printbtn.setText("Print");
        printbtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        printbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printbtnActionPerformed(evt);
            }
        });
        jPanel7.add(printbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 81, 100, 40));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 135, 13));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Queue Number :");
        jPanel7.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 220, 20));

        txtQueue.setEditable(false);
        txtQueue.setBackground(new java.awt.Color(51, 51, 51));
        txtQueue.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        txtQueue.setForeground(new java.awt.Color(255, 255, 255));
        txtQueue.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtQueue.setText("0");
        txtQueue.setBorder(null);
        jPanel7.add(txtQueue, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, 220, 30));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("%");
        jPanel7.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 32, 30));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel7.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 30, 30));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("₱");
        jPanel7.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 20, 20));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("₱");
        jPanel7.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, 20, 20));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("₱");
        jPanel7.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, 20, 30));

        jPanel8.setBackground(new java.awt.Color(51, 51, 51));

        removebtn.setBackground(new java.awt.Color(255, 0, 0));
        removebtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        removebtn.setForeground(new java.awt.Color(255, 255, 255));
        removebtn.setText("Remove");
        removebtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        removebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removebtnActionPerformed(evt);
            }
        });

        jLabel19.setIcon(new javax.swing.ImageIcon("D:\\CCE104_Project\\CS26L_GbProject2024\\GBProject\\src\\main\\java\\gb\\gbproject\\icons\\usLogo.png")); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(usLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addComponent(removebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(removebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(usLogo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setViewportView(jTextPane1);

        javax.swing.GroupLayout JPanel9Layout = new javax.swing.GroupLayout(JPanel9);
        JPanel9.setLayout(JPanel9Layout);
        JPanel9Layout.setHorizontalGroup(
            JPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
        );
        JPanel9Layout.setVerticalGroup(
            JPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(b5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(b9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(d1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(d2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(d3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(d4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(b4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(b5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(b9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b12, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(d1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(d2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(d3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(d4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(JPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 137, Short.MAX_VALUE)))
        );

        jTabbedPane2.addTab("tab1", jPanel3);

        getContentPane().add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 1080, 610));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gb", "root", "");
        String sql = "SELECT * FROM gb_items WHERE item_name = 'Chili Burger w/ Pepper Relish' AND price = 59.00";
        PreparedStatement pr = conn.prepareStatement(sql);
        rs = pr.executeQuery();

        if (rs.next()) {
        dbItem = rs.getString("item_name");
        dbPrice = rs.getInt("price");
        dbStocks = rs.getInt("stocks");

        itemName = dbItem;
        itemPrice = dbPrice;

        // Check if there are enough stocks
        if (dbStocks > 0) {
            // Get the quantity to order (Qty) from user input
           String quantityInput = JOptionPane.showInputDialog(null, "Chili Burger w/ Pepper Relish\n(Available: " + dbStocks + ")", "Add Order", JOptionPane.PLAIN_MESSAGE);

            // Check if the user canceled the input
            if (quantityInput == null) {
                return;
            }

            int quantityToOrder = Integer.parseInt(quantityInput);

            // Check if there are enough stocks
            if (dbStocks >= quantityToOrder) {
                // Deduct the ordered quantity from the stocks
                int updatedStocks = dbStocks - quantityToOrder;

                // Update the stock quantity in the database
                String updateSql = "UPDATE gb_items SET stocks = ? WHERE item_name = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setInt(1, updatedStocks);
                updateStmt.setString(2, itemName);
                updateStmt.executeUpdate();

                // Add the item to the cart
                addTable(itemName, itemPrice, quantityToOrder);
            } else {
                JOptionPane.showMessageDialog(null, "Not enough stock available.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Sorry this item is not available");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Item not found");
    }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
    }//GEN-LAST:event_b1ActionPerformed

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gb", "root", "");
            String sql = "SELECT * FROM gb_items WHERE item_name = 'Lamb and Tomato Stuff Burger' AND price = '69.00'";
            PreparedStatement pr = conn.prepareStatement(sql);
            rs = pr.executeQuery();
            
           if (rs.next()) {
        dbItem = rs.getString("item_name");
        dbPrice = rs.getInt("price");
        dbStocks = rs.getInt("stocks");

        itemName = dbItem;
        itemPrice = dbPrice;

        // Check if there are enough stocks
        if (dbStocks > 0) {
            // Get the quantity to order (Qty) from user input
            String quantityInput = JOptionPane.showInputDialog(null, "Lamb and Tomato Stuff Burger\n (Available: " + dbStocks + ")", "1");

            // Check if the user canceled the input
            if (quantityInput == null) {
                return;
            }

            int quantityToOrder = Integer.parseInt(quantityInput);

            // Check if there are enough stocks
            if (dbStocks >= quantityToOrder) {
                // Deduct the ordered quantity from the stocks
                int updatedStocks = dbStocks - quantityToOrder;

                // Update the stock quantity in the database
                String updateSql = "UPDATE gb_items SET stocks = ? WHERE item_name = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setInt(1, updatedStocks);
                updateStmt.setString(2, itemName);
                updateStmt.executeUpdate();

                // Add the item to the cart
                addTable(itemName, itemPrice, quantityToOrder);
            } else {
                JOptionPane.showMessageDialog(null, "Not enough stock available.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Sorry this item is not available");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Item not found");
    }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
    }//GEN-LAST:event_b2ActionPerformed

    private void b3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b3ActionPerformed
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gb", "root", "");
            String sql = "SELECT * FROM gb_items WHERE item_name = 'Crunchy Chicken and Fish Burger' AND price = '49.00'";
            PreparedStatement pr = conn.prepareStatement(sql);
            rs = pr.executeQuery();
            
          if (rs.next()) {
        dbItem = rs.getString("item_name");
        dbPrice = rs.getInt("price");
        dbStocks = rs.getInt("stocks");

        itemName = dbItem;
        itemPrice = dbPrice;

        // Check if there are enough stocks
        if (dbStocks > 0) {
            // Get the quantity to order (Qty) from user input
            String quantityInput = JOptionPane.showInputDialog(null, "Crunchy Chicken and Fish Burger\n (Available: " + dbStocks + ")", "1");

            // Check if the user canceled the input
            if (quantityInput == null) {
                return;
            }

            int quantityToOrder = Integer.parseInt(quantityInput);

            // Check if there are enough stocks
            if (dbStocks >= quantityToOrder) {
                // Deduct the ordered quantity from the stocks
                int updatedStocks = dbStocks - quantityToOrder;

                // Update the stock quantity in the database
                String updateSql = "UPDATE gb_items SET stocks = ? WHERE item_name = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setInt(1, updatedStocks);
                updateStmt.setString(2, itemName);
                updateStmt.executeUpdate();

                // Add the item to the cart
                addTable(itemName, itemPrice, quantityToOrder);
            } else {
                JOptionPane.showMessageDialog(null, "Not enough stock available.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Sorry this item is not available");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Item not found");
    }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
    }//GEN-LAST:event_b3ActionPerformed

    private void b4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b4ActionPerformed
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gb", "root", "");
            String sql = "SELECT * FROM gb_items WHERE item_name = 'Chicken Feta Cheese Burger' AND price = '69.00'";
            PreparedStatement pr = conn.prepareStatement(sql);
            rs = pr.executeQuery();
            
           if (rs.next()) {
        dbItem = rs.getString("item_name");
        dbPrice = rs.getInt("price");
        dbStocks = rs.getInt("stocks");

        itemName = dbItem;
        itemPrice = dbPrice;

        // Check if there are enough stocks
        if (dbStocks > 0) {
            // Get the quantity to order (Qty) from user input
            String quantityInput = JOptionPane.showInputDialog(null, "Chicken Feta Cheese Burger\n (Available: " + dbStocks + ")", "1");

            // Check if the user canceled the input
            if (quantityInput == null) {
                return;
            }

            int quantityToOrder = Integer.parseInt(quantityInput);

            // Check if there are enough stocks
            if (dbStocks >= quantityToOrder) {
                // Deduct the ordered quantity from the stocks
                int updatedStocks = dbStocks - quantityToOrder;

                // Update the stock quantity in the database
                String updateSql = "UPDATE gb_items SET stocks = ? WHERE item_name = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setInt(1, updatedStocks);
                updateStmt.setString(2, itemName);
                updateStmt.executeUpdate();

                // Add the item to the cart
                addTable(itemName, itemPrice, quantityToOrder);
            } else {
                JOptionPane.showMessageDialog(null, "Not enough stock available.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Sorry this item is not available");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Item not found");
    }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
    }//GEN-LAST:event_b4ActionPerformed

    private void b5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b5ActionPerformed
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gb", "root", "");
            String sql = "SELECT * FROM gb_items WHERE item_name = 'Lentil and Mushroom Burger' AND price = '59.00'";
            PreparedStatement pr = conn.prepareStatement(sql);
            rs = pr.executeQuery();
            
            if (rs.next()) {
        dbItem = rs.getString("item_name");
        dbPrice = rs.getInt("price");
        dbStocks = rs.getInt("stocks");

        itemName = dbItem;
        itemPrice = dbPrice;

        // Check if there are enough stocks
        if (dbStocks > 0) {
            // Get the quantity to order (Qty) from user input
            String quantityInput = JOptionPane.showInputDialog(null, "Lentil and Mushroom Burger\n (Available: " + dbStocks + ")", "1");

            // Check if the user canceled the input
            if (quantityInput == null) {
                return;
            }

            int quantityToOrder = Integer.parseInt(quantityInput);

            // Check if there are enough stocks
            if (dbStocks >= quantityToOrder) {
                // Deduct the ordered quantity from the stocks
                int updatedStocks = dbStocks - quantityToOrder;

                // Update the stock quantity in the database
                String updateSql = "UPDATE gb_items SET stocks = ? WHERE item_name = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setInt(1, updatedStocks);
                updateStmt.setString(2, itemName);
                updateStmt.executeUpdate();

                // Add the item to the cart
                addTable(itemName, itemPrice, quantityToOrder);
            } else {
                JOptionPane.showMessageDialog(null, "Not enough stock available.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Sorry this item is not available");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Item not found");
    }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
    }//GEN-LAST:event_b5ActionPerformed

    private void b6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b6ActionPerformed
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gb", "root", "");
            String sql = "SELECT * FROM gb_items WHERE item_name = 'Stuffed Bean Burger' AND price = '59.00'";
            PreparedStatement pr = conn.prepareStatement(sql);
            rs = pr.executeQuery();
            
            if (rs.next()) {
        dbItem = rs.getString("item_name");
        dbPrice = rs.getInt("price");
        dbStocks = rs.getInt("stocks");

        itemName = dbItem;
        itemPrice = dbPrice;

        // Check if there are enough stocks
        if (dbStocks > 0) {
            // Get the quantity to order (Qty) from user input
            String quantityInput = JOptionPane.showInputDialog(null, "Stuffed Bean Burger\n (Available: " + dbStocks + ")", "1");

            // Check if the user canceled the input
            if (quantityInput == null) {
                return;
            }

            int quantityToOrder = Integer.parseInt(quantityInput);

            // Check if there are enough stocks
            if (dbStocks >= quantityToOrder) {
                // Deduct the ordered quantity from the stocks
                int updatedStocks = dbStocks - quantityToOrder;

                // Update the stock quantity in the database
                String updateSql = "UPDATE gb_items SET stocks = ? WHERE item_name = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setInt(1, updatedStocks);
                updateStmt.setString(2, itemName);
                updateStmt.executeUpdate();

                // Add the item to the cart
                addTable(itemName, itemPrice, quantityToOrder);
            } else {
                JOptionPane.showMessageDialog(null, "Not enough stock available.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Sorry this item is not available");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Item not found");
    }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
    }//GEN-LAST:event_b6ActionPerformed

    private void b7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b7ActionPerformed
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gb", "root", "");
            String sql = "SELECT * FROM gb_items WHERE item_name = 'Lamb Burger w/ Radish Slaw' AND price = '89.00'";
            PreparedStatement pr = conn.prepareStatement(sql);
            rs = pr.executeQuery();
            
          if (rs.next()) {
        dbItem = rs.getString("item_name");
        dbPrice = rs.getInt("price");
        dbStocks = rs.getInt("stocks");

        itemName = dbItem;
        itemPrice = dbPrice;

        // Check if there are enough stocks
        if (dbStocks > 0) {
            // Get the quantity to order (Qty) from user input
            String quantityInput = JOptionPane.showInputDialog(null, "Lamb Burger w/ Radish Slaw\n (Available: " + dbStocks + ")", "1");

            // Check if the user canceled the input
            if (quantityInput == null) {
                return;
            }

            int quantityToOrder = Integer.parseInt(quantityInput);

            // Check if there are enough stocks
            if (dbStocks >= quantityToOrder) {
                // Deduct the ordered quantity from the stocks
                int updatedStocks = dbStocks - quantityToOrder;

                // Update the stock quantity in the database
                String updateSql = "UPDATE gb_items SET stocks = ? WHERE item_name = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setInt(1, updatedStocks);
                updateStmt.setString(2, itemName);
                updateStmt.executeUpdate();

                // Add the item to the cart
                addTable(itemName, itemPrice, quantityToOrder);
            } else {
                JOptionPane.showMessageDialog(null, "Not enough stock available.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Sorry this item is not available");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Item not found");
    }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
    }//GEN-LAST:event_b7ActionPerformed

    private void b8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b8ActionPerformed
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gb", "root", "");
            String sql = "SELECT * FROM gb_items WHERE item_name = 'Potato Corn Burger' AND price = '59.00'";
            PreparedStatement pr = conn.prepareStatement(sql);
            rs = pr.executeQuery();
            
            if (rs.next()) {
        dbItem = rs.getString("item_name");
        dbPrice = rs.getInt("price");
        dbStocks = rs.getInt("stocks");

        itemName = dbItem;
        itemPrice = dbPrice;

        // Check if there are enough stocks
        if (dbStocks > 0) {
            // Get the quantity to order (Qty) from user input
            String quantityInput = JOptionPane.showInputDialog(null, "Potato Corn Burger\n (Available: " + dbStocks + ")", "1");

            // Check if the user canceled the input
            if (quantityInput == null) {
                return;
            }

            int quantityToOrder = Integer.parseInt(quantityInput);

            // Check if there are enough stocks
            if (dbStocks >= quantityToOrder) {
                // Deduct the ordered quantity from the stocks
                int updatedStocks = dbStocks - quantityToOrder;

                // Update the stock quantity in the database
                String updateSql = "UPDATE gb_items SET stocks = ? WHERE item_name = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setInt(1, updatedStocks);
                updateStmt.setString(2, itemName);
                updateStmt.executeUpdate();

                // Add the item to the cart
                addTable(itemName, itemPrice, quantityToOrder);
            } else {
                JOptionPane.showMessageDialog(null, "Not enough stock available.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Sorry this item is not available");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Item not found");
    }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
    }//GEN-LAST:event_b8ActionPerformed

    private void b9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b9ActionPerformed
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gb", "root", "");
            String sql = "SELECT * FROM gb_items WHERE item_name = 'Supreme Veggie Burger' AND price = '59.00'";
            PreparedStatement pr = conn.prepareStatement(sql);
            rs = pr.executeQuery();
            
           if (rs.next()) {
        dbItem = rs.getString("item_name");
        dbPrice = rs.getInt("price");
        dbStocks = rs.getInt("stocks");

        itemName = dbItem;
        itemPrice = dbPrice;

        // Check if there are enough stocks
        if (dbStocks > 0) {
            // Get the quantity to order (Qty) from user input
            String quantityInput = JOptionPane.showInputDialog(null, "Supreme Veggie Burger\n (Available: " + dbStocks + ")", "1");

            // Check if the user canceled the input
            if (quantityInput == null) {
                return;
            }

            int quantityToOrder = Integer.parseInt(quantityInput);

            // Check if there are enough stocks
            if (dbStocks >= quantityToOrder) {
                // Deduct the ordered quantity from the stocks
                int updatedStocks = dbStocks - quantityToOrder;

                // Update the stock quantity in the database
                String updateSql = "UPDATE gb_items SET stocks = ? WHERE item_name = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setInt(1, updatedStocks);
                updateStmt.setString(2, itemName);
                updateStmt.executeUpdate();

                // Add the item to the cart
                addTable(itemName, itemPrice, quantityToOrder);
            } else {
                JOptionPane.showMessageDialog(null, "Not enough stock available.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Sorry this item is not available");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Item not found");
    }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
    }//GEN-LAST:event_b9ActionPerformed

    private void b10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b10ActionPerformed
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gb", "root", "");
            String sql = "SELECT * FROM gb_items WHERE item_name = 'Butter Chicken Twin Burgers' AND price = '79.00'";
            PreparedStatement pr = conn.prepareStatement(sql);
            rs = pr.executeQuery();
            
            if (rs.next()) {
        dbItem = rs.getString("item_name");
        dbPrice = rs.getInt("price");
        dbStocks = rs.getInt("stocks");

        itemName = dbItem;
        itemPrice = dbPrice;

        // Check if there are enough stocks
        if (dbStocks > 0) {
            // Get the quantity to order (Qty) from user input
            String quantityInput = JOptionPane.showInputDialog(null, "Butter Chicken Twin Burgers\n (Available: " + dbStocks + ")", "1");

            // Check if the user canceled the input
            if (quantityInput == null) {
                return;
            }

            int quantityToOrder = Integer.parseInt(quantityInput);

            // Check if there are enough stocks
            if (dbStocks >= quantityToOrder) {
                // Deduct the ordered quantity from the stocks
                int updatedStocks = dbStocks - quantityToOrder;

                // Update the stock quantity in the database
                String updateSql = "UPDATE gb_items SET stocks = ? WHERE item_name = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setInt(1, updatedStocks);
                updateStmt.setString(2, itemName);
                updateStmt.executeUpdate();

                // Add the item to the cart
                addTable(itemName, itemPrice, quantityToOrder);
            } else {
                JOptionPane.showMessageDialog(null, "Not enough stock available.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Sorry this item is not available");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Item not found");
    }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
    }//GEN-LAST:event_b10ActionPerformed

    private void b11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b11ActionPerformed
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gb", "root", "");
            String sql = "SELECT * FROM gb_items WHERE item_name = 'Pizza Burger' AND price = '99.00'";
            PreparedStatement pr = conn.prepareStatement(sql);
            rs = pr.executeQuery();
            
            if (rs.next()) {
        dbItem = rs.getString("item_name");
        dbPrice = rs.getInt("price");
        dbStocks = rs.getInt("stocks");

        itemName = dbItem;
        itemPrice = dbPrice;

        // Check if there are enough stocks
        if (dbStocks > 0) {
            // Get the quantity to order (Qty) from user input
            String quantityInput = JOptionPane.showInputDialog(null, "Pizza Burger\n (Available: " + dbStocks + ")", "1");

            // Check if the user canceled the input
            if (quantityInput == null) {
                return;
            }

            int quantityToOrder = Integer.parseInt(quantityInput);

            // Check if there are enough stocks
            if (dbStocks >= quantityToOrder) {
                // Deduct the ordered quantity from the stocks
                int updatedStocks = dbStocks - quantityToOrder;

                // Update the stock quantity in the database
                String updateSql = "UPDATE gb_items SET stocks = ? WHERE item_name = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setInt(1, updatedStocks);
                updateStmt.setString(2, itemName);
                updateStmt.executeUpdate();

                // Add the item to the cart
                addTable(itemName, itemPrice, quantityToOrder);
            } else {
                JOptionPane.showMessageDialog(null, "Not enough stock available.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Sorry this item is not available");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Item not found");
    }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
    }//GEN-LAST:event_b11ActionPerformed

    private void d1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_d1ActionPerformed
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gb", "root", "");
            String sql = "SELECT * FROM gb_items WHERE item_name = 'Coke' AND price = '30.00'";
            PreparedStatement pr = conn.prepareStatement(sql);
            rs = pr.executeQuery();
            
           if (rs.next()) {
        dbItem = rs.getString("item_name");
        dbPrice = rs.getInt("price");
        dbStocks = rs.getInt("stocks");

        itemName = dbItem;
        itemPrice = dbPrice;

        // Check if there are enough stocks
        if (dbStocks > 0) {
            // Get the quantity to order (Qty) from user input
            String quantityInput = JOptionPane.showInputDialog(null, "Coke\n (Available: " + dbStocks + ")", "1");

            // Check if the user canceled the input
            if (quantityInput == null) {
                return;
            }

            int quantityToOrder = Integer.parseInt(quantityInput);

            // Check if there are enough stocks
            if (dbStocks >= quantityToOrder) {
                // Deduct the ordered quantity from the stocks
                int updatedStocks = dbStocks - quantityToOrder;

                // Update the stock quantity in the database
                String updateSql = "UPDATE gb_items SET stocks = ? WHERE item_name = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setInt(1, updatedStocks);
                updateStmt.setString(2, itemName);
                updateStmt.executeUpdate();

                // Add the item to the cart
                addTable(itemName, itemPrice, quantityToOrder);
            } else {
                JOptionPane.showMessageDialog(null, "Not enough stock available.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Sorry this item is not available");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Item not found");
    }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
    }//GEN-LAST:event_d1ActionPerformed

    private void d2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_d2ActionPerformed
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gb", "root", "");
            String sql = "SELECT * FROM gb_items WHERE item_name = 'Sprite' AND price = '30.00'";
            PreparedStatement pr = conn.prepareStatement(sql);
            rs = pr.executeQuery();
            
           if (rs.next()) {
        dbItem = rs.getString("item_name");
        dbPrice = rs.getInt("price");
        dbStocks = rs.getInt("stocks");

        itemName = dbItem;
        itemPrice = dbPrice;

        // Check if there are enough stocks
        if (dbStocks > 0) {
            // Get the quantity to order (Qty) from user input
            String quantityInput = JOptionPane.showInputDialog(null, "Sprite\n (Available: " + dbStocks + ")", "1");

            // Check if the user canceled the input
            if (quantityInput == null) {
                return;
            }

            int quantityToOrder = Integer.parseInt(quantityInput);

            // Check if there are enough stocks
            if (dbStocks >= quantityToOrder) {
                // Deduct the ordered quantity from the stocks
                int updatedStocks = dbStocks - quantityToOrder;

                // Update the stock quantity in the database
                String updateSql = "UPDATE gb_items SET stocks = ? WHERE item_name = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setInt(1, updatedStocks);
                updateStmt.setString(2, itemName);
                updateStmt.executeUpdate();

                // Add the item to the cart
                addTable(itemName, itemPrice, quantityToOrder);
            } else {
                JOptionPane.showMessageDialog(null, "Not enough stock available.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Sorry this item is not available");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Item not found");
    }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
    }//GEN-LAST:event_d2ActionPerformed

    private void d3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_d3ActionPerformed
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gb", "root", "");
            String sql = "SELECT * FROM gb_items WHERE item_name = 'Water' AND price = '25.00'";
            PreparedStatement pr = conn.prepareStatement(sql);
            rs = pr.executeQuery();
            
         if (rs.next()) {
        dbItem = rs.getString("item_name");
        dbPrice = rs.getInt("price");
        dbStocks = rs.getInt("stocks");

        itemName = dbItem;
        itemPrice = dbPrice;

        // Check if there are enough stocks
        if (dbStocks > 0) {
            // Get the quantity to order (Qty) from user input
            String quantityInput = JOptionPane.showInputDialog(null, "Water\n (Available: " + dbStocks + ")", "1");

            // Check if the user canceled the input
            if (quantityInput == null) {
                return;
            }

            int quantityToOrder = Integer.parseInt(quantityInput);

            // Check if there are enough stocks
            if (dbStocks >= quantityToOrder) {
                // Deduct the ordered quantity from the stocks
                int updatedStocks = dbStocks - quantityToOrder;

                // Update the stock quantity in the database
                String updateSql = "UPDATE gb_items SET stocks = ? WHERE item_name = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setInt(1, updatedStocks);
                updateStmt.setString(2, itemName);
                updateStmt.executeUpdate();

                // Add the item to the cart
                addTable(itemName, itemPrice, quantityToOrder);
            } else {
                JOptionPane.showMessageDialog(null, "Not enough stock available.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Sorry this item is not available");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Item not found");
    }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
    }//GEN-LAST:event_d3ActionPerformed

    private void totaltxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totaltxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totaltxtActionPerformed
   private int incrementQueueNumber() {
        queueNumber++; // Increment the queue number
        return queueNumber; // Return the updated queue number
    }
    private void paybtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paybtnActionPerformed
// Pay
String totalText = totaltxt.getText();
String cashText = cashtxt.getText();
if (totalText.isEmpty() || cashText.isEmpty()) {
    JOptionPane.showMessageDialog(null, "ERROR! Please enter the Total and Cash amounts.");
} else {
    try {
        double tot = Double.parseDouble(totalText);
        double cash = Double.parseDouble(cashText);
        
        boolean transactedSuccessfully = false;
        boolean addAnotherOrder = true; // Initialize to true to enter the loop
        
        do {
            if (tot > cash) {
                JOptionPane.showMessageDialog(null, "ERROR! Insufficient Cash. Please enter a sufficient amount.");
                cashtxt.setText("");
                break;
            } else {
                double change = cash - tot;
                DecimalFormat dft = new DecimalFormat("00.00");
                String d2 = dft.format(change);
        
                changetxt.setText(d2);
                receipt_bill();
                

        
                // Queue Numbering and Transactions
                String qNum = txtQueue.getText();
                
                // Queue Numbering and Transactions
                try {
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gb", "root", "");
        
                    // Get the maximum existing Receipt_Number
                    PreparedStatement getMaxReceiptNumber = conn.prepareStatement("SELECT MAX(Receipt_Number) FROM transactions");
                    ResultSet rs = getMaxReceiptNumber.executeQuery();
                    int maxReceiptNumber = 0;
                    if (rs.next()) {
                        maxReceiptNumber = rs.getInt(1);
                    }
                    // Increment to generate a new unique Receipt_Number
                    int newReceiptNumber = maxReceiptNumber + 1;
        
                    // Create a PreparedStatement with all columns, including Receipt_Number
                    PreparedStatement st = conn.prepareStatement("INSERT INTO transactions (Receipt_Number, Items, Total) VALUES (?, ?, ?)");
                    sm = conn.createStatement();
                    int receipt_Num = random_int;
                    DefaultTableModel tm = (DefaultTableModel) table1.getModel();
        
                    for (int i = 0; i < table1.getRowCount(); i++) {
                        String itm = tm.getValueAt(i, 0).toString();
        
                        // Set the new unique Receipt_Number
                        st.setInt(1, receipt_Num);
                        st.setString(2, itm);
                        st.setString(3, totalText);
                        st.executeUpdate();
                        
                    }
                     
                    // Display a confirmation dialog
                    int choice = JOptionPane.showOptionDialog(
                        this, 
                        "Would you like to add some changes to your order?", 
                        "Confirmation", 
                        JOptionPane.YES_NO_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, 
                        null, 
                        new String[] {"Yes", "No"}, 
                        "Yes");
        
                    if (choice == JOptionPane.YES_OPTION) {
                        cashtxt.setText("");
                        jTextPane1.setText("");
                        
                    } else if (choice == JOptionPane.NO_OPTION) {
                        addAnotherOrder = false;

                        // Update and store the queue number
                        int updatedQueueNumber = incrementQueueNumber();
                        JOptionPane.showMessageDialog(this,"Order sucessful\n" + "\nYou order number is: "+random_int);
                        
                        // Set the txtQueue with the updated queue number
                        txtQueue.setText(Integer.toString(updatedQueueNumber));
                        
                        // Open adFrame
                        if (adFrame == null) {
                            adFrame = new QFrame(); // Create and configure your QFrame here
                        }
                        adFrame.updateQueueNumber(updatedQueueNumber); // Pass the updated queue number to adFrame
                        adFrame.setVisible(true);
                        break;
                        
                    }
                           
                    transactedSuccessfully = true; // Exit the loop when the transaction is successful
        
                    if (!addAnotherOrder) {
                        // Continue with adFrame as before
                        if (adFrame == null) {
                            adFrame = new QFrame(); // Create and configure your QFrame here
                        }
                        adFrame.setVisible(true);   
                    }
                } catch (HeadlessException | SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        } while (!transactedSuccessfully);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "ERROR! Please enter valid numeric values for Total and Cash.");
    }
}

    }//GEN-LAST:event_paybtnActionPerformed

    private void d4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_d4ActionPerformed
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gb", "root", "");
            String sql = "SELECT * FROM gb_items WHERE item_name = 'Nestea' AND price = '25.00'";
            PreparedStatement pr = conn.prepareStatement(sql);
            rs = pr.executeQuery();
            
           if (rs.next()) {
        dbItem = rs.getString("item_name");
        dbPrice = rs.getInt("price");
        dbStocks = rs.getInt("stocks");

        itemName = dbItem;
        itemPrice = dbPrice;

        // Check if there are enough stocks
        if (dbStocks > 0) {
            // Get the quantity to order (Qty) from user input
            String quantityInput = JOptionPane.showInputDialog(null, "Nestea\n (Available: " + dbStocks + ")", "1");

            // Check if the user canceled the input
            if (quantityInput == null) {
                return;
            }

            int quantityToOrder = Integer.parseInt(quantityInput);

            // Check if there are enough stocks
            if (dbStocks >= quantityToOrder) {
                // Deduct the ordered quantity from the stocks
                int updatedStocks = dbStocks - quantityToOrder;

                // Update the stock quantity in the database
                String updateSql = "UPDATE gb_items SET stocks = ? WHERE item_name = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setInt(1, updatedStocks);
                updateStmt.setString(2, itemName);
                updateStmt.executeUpdate();

                // Add the item to the cart
                addTable(itemName, itemPrice, quantityToOrder);
            } else {
                JOptionPane.showMessageDialog(null, "Not enough stock available.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Sorry this item is not available");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Item not found");
    }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
    }//GEN-LAST:event_d4ActionPerformed

    private void printbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printbtnActionPerformed
        
        try{
            jTextPane1.print(); 
        }catch(Exception e){
        }
        
    }//GEN-LAST:event_printbtnActionPerformed

    private void removebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removebtnActionPerformed
       
        DefaultTableModel tm = (DefaultTableModel) table1.getModel();

    int remove = table1.getSelectedRow();
    if (remove >= 0) {
        int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this order?", "Confirm Removal", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            tm.removeRow(remove);
            sub_total();
            JOptionPane.showMessageDialog(null, "Your order has been successfully removed.");
        }}

    }//GEN-LAST:event_removebtnActionPerformed

    private void subtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subtxtActionPerformed

    private void timeniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_timeniActionPerformed

    private void paybtnKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paybtnKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_paybtnKeyReleased

    private void b12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b12ActionPerformed
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gb", "root", "");
            String sql = "SELECT * FROM gb_items WHERE item_name = 'Whoppie Burger' AND price = '89.00'";
            PreparedStatement pr = conn.prepareStatement(sql);
            rs = pr.executeQuery();

           if (rs.next()) {
        dbItem = rs.getString("item_name");
        dbPrice = rs.getInt("price");
        dbStocks = rs.getInt("stocks");

        itemName = dbItem;
        itemPrice = dbPrice;

        // Check if there are enough stocks
        if (dbStocks > 0) {
            // Get the quantity to order (Qty) from user input
            String quantityInput = JOptionPane.showInputDialog(null, "Whoppie Burger\n (Available: " + dbStocks + ")", "1");

            // Check if the user canceled the input
            if (quantityInput == null) {
                return;
            }

            int quantityToOrder = Integer.parseInt(quantityInput);

            // Check if there are enough stocks
            if (dbStocks >= quantityToOrder) {
                // Deduct the ordered quantity from the stocks
                int updatedStocks = dbStocks - quantityToOrder;

                // Update the stock quantity in the database
                String updateSql = "UPDATE gb_items SET stocks = ? WHERE item_name = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setInt(1, updatedStocks);
                updateStmt.setString(2, itemName);
                updateStmt.executeUpdate();

                // Add the item to the cart
                addTable(itemName, itemPrice, quantityToOrder);
            } else {
                JOptionPane.showMessageDialog(null, "Not enough stock available.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Sorry this item is not available");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Item not found");
    }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
    }//GEN-LAST:event_b12ActionPerformed

    private void paybtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paybtnMouseReleased
   
    }//GEN-LAST:event_paybtnMouseReleased

    private void logoutbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutbtnActionPerformed
        new ChooseFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logoutbtnActionPerformed

    private void discounttxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_discounttxtKeyReleased
        sub_total();
    }//GEN-LAST:event_discounttxtKeyReleased

    private void taxtxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_taxtxtKeyReleased
        sub_total();
    }//GEN-LAST:event_taxtxtKeyReleased

    private void taxtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_taxtxtActionPerformed

    }//GEN-LAST:event_taxtxtActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UsFrame().setVisible(true);
                
            }
        });
    }
 void setCount(int queueNumber) {
    queueNumber++;
    this.queueNumber = queueNumber;
    txtQueue.setText(Integer.toString(queueNumber));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPanel9;
    private javax.swing.JButton b1;
    private javax.swing.JButton b10;
    private javax.swing.JButton b11;
    private javax.swing.JButton b12;
    private javax.swing.JButton b2;
    private javax.swing.JButton b3;
    private javax.swing.JButton b4;
    private javax.swing.JButton b5;
    private javax.swing.JButton b6;
    private javax.swing.JButton b7;
    private javax.swing.JButton b8;
    private javax.swing.JButton b9;
    private javax.swing.JTextField cashtxt;
    private javax.swing.JTextField changetxt;
    private javax.swing.JButton d1;
    private javax.swing.JButton d2;
    private javax.swing.JButton d3;
    private javax.swing.JButton d4;
    private javax.swing.JTextField dateni;
    private javax.swing.JTextField discounttxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JButton logoutbtn;
    private javax.swing.JButton paybtn;
    private javax.swing.JButton printbtn;
    private javax.swing.JButton removebtn;
    private javax.swing.JTextField subtxt;
    private javax.swing.JTable table1;
    private javax.swing.JTextField taxtxt;
    private javax.swing.JTextField timeni;
    private javax.swing.JTextField totaltxt;
    private javax.swing.JTextField txtQueue;
    private javax.swing.JLabel usLogo;
    private javax.swing.JLabel userL;
    // End of variables declaration//GEN-END:variables


   

   
}
