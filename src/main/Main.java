
package main;

import view.InventoryOpname;
import view.InventoryReject;
import view.MasterPayment;
import view.MasterUser;
import view.MasterData;
import view.MasterDiscount;
import view.InventoryMasterbarang;
import view.ReportPelanggan;
import view.ReportPenjualan;
import view.ReportStock;
import view.TransaksiPenerimaan;
import view.TransaksiPengeluaran;
import view.TransaksiPenjualan;
import view.UploadEmployee;
import view.UploadPelanggan;
import view.UploadStock;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import menu.MenuItem;

public class Main extends javax.swing.JFrame {
    
    public static String username, tanggal;

    
    public Main() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        execute();
        tanggal();
        jam();
//        TglSekarang();
    }
//    
//    public void TglSekarang() {
//        Date date = new Date();
//        String myFormat = "yyyy-MM-dd";
//        String jam = "hh:mm:ss";
//        SimpleDateFormat sdj = new SimpleDateFormat(jam);
//        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
//        TimeZone tz = TimeZone.getTimeZone("Asia/Jakarta");
//        txt_jam.setText(sdj.format(date));
//        txt_tanggal.setText(sdf.format(date));
//        tanggal = sdf.format(date);
//    }
    
    public void tanggal(){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(d);
        txt_tanggal.setText(date);
    }
    
    public void jam(){
        Timer time = new Timer(0, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Date dt = new Date();
                SimpleDateFormat sdj = new SimpleDateFormat("hh:mm:ss");
                String tt = sdj.format(dt);
                txt_jam.setText(tt);
            }
            
        });
        time.start();
    }
    
     public static String getUsername() {
        return username;
    }

    public static String getTanggal() {
        return tanggal;
    }
   

    private void execute() {
        
        ImageIcon iconDashboard = new ImageIcon(getClass().getResource("/Asset/dashboard24.png"));
        ImageIcon iconGeneral = new ImageIcon(getClass().getResource("/Asset/setting24.png"));
        ImageIcon iconPayment = new ImageIcon(getClass().getResource("/Asset/payment24.png"));
        ImageIcon iconReport = new ImageIcon(getClass().getResource("/Asset/report24.png"));
        ImageIcon iconInventory = new ImageIcon(getClass().getResource("/Asset/inventory24.png"));
        ImageIcon iconUpload = new ImageIcon(getClass().getResource("/Asset/upload24.png"));
        ImageIcon iconSubMenu = new ImageIcon(getClass().getResource("/Asset/submenu14.png"));
        
        //   submenu general master        
        MenuItem user = new MenuItem(iconSubMenu, "User Master", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.removeAll();
                try {
                    panelBody.add(new MasterUser());
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                panelBody.repaint();
                panelBody.revalidate();
            }
        });
        MenuItem data = new MenuItem(iconSubMenu, "Data Master", new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.removeAll();
                panelBody.add(new MasterData());
                panelBody.repaint();
                panelBody.revalidate();
            }
        });
       
        MenuItem discount = new MenuItem(iconSubMenu, "Promo Master", new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.removeAll();
                panelBody.add(new MasterDiscount());
                panelBody.repaint();
                panelBody.revalidate();
            }
        });
        MenuItem payment = new MenuItem(iconSubMenu, "Payment Master", new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.removeAll();
                panelBody.add(new MasterPayment());
                panelBody.repaint();
                panelBody.revalidate();
            }
        });
        
        
        //  submenu inventory
        MenuItem product = new MenuItem(iconSubMenu, "Master Barang", new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.removeAll();
                try {
                    panelBody.add(new InventoryMasterbarang());
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                panelBody.repaint();
                panelBody.revalidate();
            }
        });
        
        MenuItem menuReject = new MenuItem(iconSubMenu, "Stock Reject", new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.removeAll();
                panelBody.add(new InventoryReject());
                panelBody.repaint();
                panelBody.revalidate();
            }
        });
        MenuItem menuOpname = new MenuItem(iconSubMenu, "Stock Opname", new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.removeAll();
                panelBody.add(new InventoryOpname());
                panelBody.repaint();
                panelBody.revalidate();
            } 
        });

        //  submenu transaction 
        MenuItem menuSales = new MenuItem(iconSubMenu, "Penjualan", new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.removeAll();
                try {
                    panelBody.add(new TransaksiPenjualan());
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                panelBody.repaint();
                panelBody.revalidate();
            } 
        });
        MenuItem menuKeluar = new MenuItem(iconSubMenu, "Pengeluaran", new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.removeAll();
                panelBody.add(new TransaksiPengeluaran());
                panelBody.repaint();
                panelBody.revalidate();
            } 
        });
        MenuItem menuMasuk = new MenuItem(iconSubMenu, "Penerimaan", new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.removeAll();
                panelBody.add(new TransaksiPenerimaan());
                panelBody.repaint();
                panelBody.revalidate();
            } 
        });
        
        //  submenu report 
        MenuItem reportPenjualan = new MenuItem(iconSubMenu, "Report Penjualan", new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.removeAll();
                panelBody.add(new ReportPenjualan());
                panelBody.repaint();
                panelBody.revalidate();
            } 
        });
        MenuItem reportStock = new MenuItem(iconSubMenu, "Report Stock", new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.removeAll();
                panelBody.add(new ReportStock());
                panelBody.repaint();
                panelBody.revalidate();
            } 
        });
        MenuItem reportPelanggan = new MenuItem(iconSubMenu, "Report Pelanggan", new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.removeAll();
                panelBody.add(new ReportPelanggan());
                panelBody.repaint();
                panelBody.revalidate();
            } 
        });
        
        //  submenu upload 
        MenuItem uploadBarang = new MenuItem(iconSubMenu, "Upload Data Stock", new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.removeAll();
                panelBody.add(new UploadStock());
                panelBody.repaint();
                panelBody.revalidate();
            } 
        });
        MenuItem uploadUser = new MenuItem(iconSubMenu, "Upload Data Employee", new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.removeAll();
                panelBody.add(new UploadEmployee());
                panelBody.repaint();
                panelBody.revalidate();
            } 
        });
        MenuItem uploadPelanggan = new MenuItem(iconSubMenu, "Upload Data Pelanggan", new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.removeAll();
                panelBody.add(new UploadPelanggan());
                panelBody.repaint();
                panelBody.revalidate();
            } 
        });

        //  main menu
        MenuItem menuDashboard = new MenuItem(iconDashboard, "Home - Dashboard", null);
        MenuItem menuGeneral = new MenuItem(iconGeneral, "General Master", null, user, data, discount, payment);
        MenuItem menuInventory = new MenuItem(iconInventory, "Inventory", null, product, menuReject, menuOpname);
        MenuItem menuTransaction = new MenuItem(iconPayment, "Transaksi", null, menuSales, menuKeluar, menuMasuk);
        MenuItem menuReport = new MenuItem(iconReport, "Report", null, reportPenjualan, reportStock, reportPelanggan);
        MenuItem menuUpload = new MenuItem(iconUpload, "Upload", null, uploadBarang, uploadUser, uploadPelanggan);
        addMenu(menuDashboard, menuGeneral, menuInventory, menuTransaction, menuReport, menuUpload);
    }

    private void addMenu(MenuItem... menu) {
        for (int i = 0; i < menu.length; i++) {
            menus.add(menu[i]);
            ArrayList<MenuItem> subMenu = menu[i].getSubMenu();
            for (MenuItem m : subMenu) {
                addMenu(m);
            }
        }
        menus.revalidate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelHeader = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txt_tanggal = new javax.swing.JLabel();
        txt_username = new javax.swing.JLabel();
        txt_jam = new javax.swing.JLabel();
        panelMenu = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        menus = new javax.swing.JPanel();
        panelBody = new javax.swing.JPanel();
        footer = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelHeader.setBackground(new java.awt.Color(255, 255, 255));
        panelHeader.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelHeader.setForeground(new java.awt.Color(255, 255, 255));
        panelHeader.setPreferredSize(new java.awt.Dimension(561, 50));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Logo");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Hallo,");

        txt_tanggal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txt_tanggal.setText("Tanggal");

        txt_username.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        txt_username.setText("jLabel4");

        txt_jam.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txt_jam.setText("Jam");

        javax.swing.GroupLayout panelHeaderLayout = new javax.swing.GroupLayout(panelHeader);
        panelHeader.setLayout(panelHeaderLayout);
        panelHeaderLayout.setHorizontalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelHeaderLayout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_username)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 509, Short.MAX_VALUE)
                        .addComponent(txt_jam))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHeaderLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_tanggal)))
                .addContainerGap())
        );
        panelHeaderLayout.setVerticalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(txt_username))
                .addContainerGap(13, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHeaderLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txt_jam)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_tanggal)
                .addGap(5, 5, 5))
        );

        getContentPane().add(panelHeader, java.awt.BorderLayout.PAGE_START);

        panelMenu.setBackground(new java.awt.Color(115, 120, 230));
        panelMenu.setPreferredSize(new java.awt.Dimension(225, 384));

        jScrollPane1.setBorder(null);

        menus.setBackground(new java.awt.Color(255, 255, 255));
        menus.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        menus.setLayout(new javax.swing.BoxLayout(menus, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(menus);

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
        );

        getContentPane().add(panelMenu, java.awt.BorderLayout.LINE_START);

        panelBody.setBackground(new java.awt.Color(204, 204, 204));
        panelBody.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelBody.setLayout(new java.awt.BorderLayout());
        getContentPane().add(panelBody, java.awt.BorderLayout.CENTER);

        footer.setBackground(new java.awt.Color(255, 255, 255));
        footer.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        footer.setPreferredSize(new java.awt.Dimension(855, 30));

        javax.swing.GroupLayout footerLayout = new javax.swing.GroupLayout(footer);
        footer.setLayout(footerLayout);
        footerLayout.setHorizontalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 851, Short.MAX_VALUE)
        );
        footerLayout.setVerticalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 26, Short.MAX_VALUE)
        );

        getContentPane().add(footer, java.awt.BorderLayout.PAGE_END);

        setSize(new java.awt.Dimension(871, 473));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel footer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel menus;
    private javax.swing.JPanel panelBody;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel panelMenu;
    public javax.swing.JLabel txt_jam;
    public javax.swing.JLabel txt_tanggal;
    public javax.swing.JLabel txt_username;
    // End of variables declaration//GEN-END:variables
}
