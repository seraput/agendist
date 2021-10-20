package main;

import java.awt.Color;
import view.InventoryOpname;
import view.InventoryStokLainnya;
import view.MasterBank;
import view.MasterUser;
import view.MasterDistributor;
import view.InventoryMasterbarang;
import view.ReportPelanggan;
import view.ReportPenjualan;
import view.ReportStok;
import view.TransaksiPenerimaan;
import view.TransaksiPengeluaran;
import view.TransaksiPenjualan;
import view.UploadEmployee;
import view.UploadPelanggan;
import view.UploadStock;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import model.UserSession;
import model.mLogin;
import view.LoginForm;
import view.OpnameAwal;
import view.UtilityDatabase;

public final class Main extends javax.swing.JFrame {

    public static String username, tanggal;
    public MenuItem menuDashboard, menuGeneral, menuInventory, menuTransaction, menuReport, menuUpload, menuUtility;
    public ImageIcon warning = new ImageIcon(getClass().getResource("/asset/warning.png"));

    public Main() {
        initComponents();
        txt_username.setText(UserSession.getsUsername());
        jabatan.setText(UserSession.getsJabatan());
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        execute();
        cekJabatan();
        tanggal();
        jam();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        initListener();

    }

    private void initListener() {
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                doExit();
            }
        });
    }

    public void doExit() {
        JOptionPane.showMessageDialog(null, "Anda Harus Logout Terlebih Dahulu!", "Peringatan", HEIGHT, warning);
    }

    public void cekJabatan() {
        String jb = jabatan.getText();
        if (jb.equals("Administrator")) {
            menuDashboard.setVisible(true);
            menuGeneral.setVisible(true);
            menuInventory.setVisible(true);
            menuTransaction.setVisible(true);
            menuReport.setVisible(true);
//            menuUpload.setVisible(true);
        } else if (jb.equals("Owner")) {
            menuDashboard.setVisible(true);
            menuGeneral.setVisible(false);
            menuInventory.setVisible(true);
            menuTransaction.setVisible(false);
            menuReport.setVisible(true);
//            menuUpload.setVisible(false);
        } else if (jb.equals("Kasir")) {
            menuDashboard.setVisible(true);
            menuGeneral.setVisible(true);
            menuInventory.setVisible(true);
            menuTransaction.setVisible(true);
            menuReport.setVisible(false);
//            menuUpload.setVisible(true);
        } else {

        }
    }

    public void tanggal() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String date = sdf.format(d);
        txt_tanggal.setText(date);
    }

    public void jam() {
        Timer time = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        ImageIcon iconUtil = new ImageIcon(getClass().getResource("/Asset/set.png"));

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

        MenuItem discount = new MenuItem(iconSubMenu, "Distributor", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.removeAll();
                try {
                    panelBody.add(new MasterDistributor());
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                panelBody.repaint();
                panelBody.revalidate();
            }
        });
        MenuItem bank = new MenuItem(iconSubMenu, "Bank Master", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.removeAll();
                try {
                    panelBody.add(new MasterBank());
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                panelBody.repaint();
                panelBody.revalidate();
            }
        });

        //  submenu inventory
        MenuItem product = new MenuItem(iconSubMenu, "Master Barang", new ActionListener() {
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

        MenuItem menuReject = new MenuItem(iconSubMenu, "Stok Lain-Lain", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.removeAll();
                try {
                    panelBody.add(new InventoryStokLainnya());
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                panelBody.repaint();
                panelBody.revalidate();
            }
        });
        MenuItem menuOpname = new MenuItem(iconSubMenu, "Stock Opname", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.removeAll();
                panelBody.add(new InventoryOpname());
                panelBody.repaint();
                panelBody.revalidate();
            }
        });

        //  submenu transaction 
        MenuItem menuSales = new MenuItem(iconSubMenu, "Penjualan", new ActionListener() {
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
        MenuItem menuKeluar = new MenuItem(iconSubMenu, "Pengeluaran Barang", (ActionEvent ae) -> {
            panelBody.removeAll();
            panelBody.add(new TransaksiPengeluaran());
            panelBody.repaint();
            panelBody.revalidate();
        });
        MenuItem menuMasuk = new MenuItem(iconSubMenu, "Penerimaan Barang", (ActionEvent ae) -> {
            panelBody.removeAll();
            panelBody.add(new TransaksiPenerimaan());
            panelBody.repaint();
            panelBody.revalidate();
        });

        //  submenu report 
        MenuItem reportPenjualan = new MenuItem(iconSubMenu, "Report Penjualan", (ActionEvent ae) -> {
            panelBody.removeAll();
            panelBody.add(new ReportPenjualan());
            panelBody.repaint();
            panelBody.revalidate();
        });
        MenuItem reportStock = new MenuItem(iconSubMenu, "Report Stock", (ActionEvent ae) -> {
            panelBody.removeAll();
            panelBody.add(new ReportStok());
            panelBody.repaint();
            panelBody.revalidate();
        });
        MenuItem reportPelanggan = new MenuItem(iconSubMenu, "Report Pelanggan", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.removeAll();
                panelBody.add(new ReportPelanggan());
                panelBody.repaint();
                panelBody.revalidate();
            }
        });

        //  submenu upload 
        MenuItem uploadBarang = new MenuItem(iconSubMenu, "Upload Data Stock", (ActionEvent ae) -> {
            panelBody.removeAll();
            panelBody.add(new UploadStock());
            panelBody.repaint();
            panelBody.revalidate();
        });
        MenuItem uploadUser = new MenuItem(iconSubMenu, "Upload Data Employee", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.removeAll();
                panelBody.add(new UploadEmployee());
                panelBody.repaint();
                panelBody.revalidate();
            }
        });
        MenuItem uploadPelanggan = new MenuItem(iconSubMenu, "Upload Data Pelanggan", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.removeAll();
                panelBody.add(new UploadPelanggan());
                panelBody.repaint();
                panelBody.revalidate();
            }
        });
        
        MenuItem database = new MenuItem(iconSubMenu, "Database", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.removeAll();
                panelBody.add(new UtilityDatabase());
                panelBody.repaint();
                panelBody.revalidate();
            }
        });
        

        //  main menu
        menuDashboard = new MenuItem(iconDashboard, "Home - Dashboard", null);
        menuGeneral = new MenuItem(iconGeneral, "General Master", null, user, discount, bank);
        menuInventory = new MenuItem(iconInventory, "Inventory", null, product, menuOpname);
        menuTransaction = new MenuItem(iconPayment, "Transaksi", null, menuSales, menuMasuk);
        menuReport = new MenuItem(iconReport, "Report", null, reportPenjualan, reportStock);
        menuUtility = new MenuItem(iconUtil, "Utility", null, database);
//        menuUpload = new MenuItem(iconUpload, "Upload", null, uploadBarang, uploadUser, uploadPelanggan);
        addMenu(menuDashboard, menuGeneral, menuInventory, menuTransaction, menuReport, menuUtility);
    }

    private void addMenu(MenuItem... menu) {
        for (MenuItem menu1 : menu) {
            menus.add(menu1);
            ArrayList<MenuItem> subMenu = menu1.getSubMenu();
            subMenu.forEach((m) -> {
                addMenu(m);
            });
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

        panelMenu = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        menus = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jabatan = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        txt_username = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        pk = new javax.swing.JPanel();
        keluar = new javax.swing.JLabel();
        panelHeader = new javax.swing.JPanel();
        txt_tanggal = new javax.swing.JLabel();
        txt_jam = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        panelBody = new javax.swing.JPanel();
        footer = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelMenu.setBackground(new java.awt.Color(255, 255, 255));
        panelMenu.setPreferredSize(new java.awt.Dimension(225, 384));

        jScrollPane1.setBorder(null);

        menus.setBackground(new java.awt.Color(255, 255, 255));
        menus.setLayout(new javax.swing.BoxLayout(menus, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(menus);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/user32.png"))); // NOI18N

        jabatan.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jabatan.setText("jabatan");

        txt_username.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txt_username.setText("username");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jabatan)
                            .addComponent(txt_username))
                        .addGap(0, 99, Short.MAX_VALUE))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_username)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jabatan)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pk.setBackground(new java.awt.Color(255, 255, 255));
        pk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                click(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                masuk(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                move(evt);
            }
        });

        keluar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        keluar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        keluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/logout24.png"))); // NOI18N
        keluar.setText("Keluar");
        keluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                click(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                masuk(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                move(evt);
            }
        });

        javax.swing.GroupLayout pkLayout = new javax.swing.GroupLayout(pk);
        pk.setLayout(pkLayout);
        pkLayout.setHorizontalGroup(
            pkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pkLayout.createSequentialGroup()
                .addComponent(keluar)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pkLayout.setVerticalGroup(
            pkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pkLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(keluar))
        );

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator4)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMenuLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(pk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(panelMenu, java.awt.BorderLayout.LINE_START);

        panelHeader.setBackground(new java.awt.Color(255, 255, 255));
        panelHeader.setForeground(new java.awt.Color(255, 255, 255));
        panelHeader.setPreferredSize(new java.awt.Dimension(561, 50));

        txt_tanggal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txt_tanggal.setText("Tanggal");

        txt_jam.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txt_jam.setText("Jam");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 153));
        jLabel2.setText("WINS WATER");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel4.setText("POS & Inventory");

        javax.swing.GroupLayout panelHeaderLayout = new javax.swing.GroupLayout(panelHeader);
        panelHeader.setLayout(panelHeaderLayout);
        panelHeaderLayout.setHorizontalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2))
                .addGap(128, 128, 128)
                .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelHeaderLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txt_jam))
                    .addGroup(panelHeaderLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 577, Short.MAX_VALUE)
                        .addComponent(txt_tanggal)))
                .addContainerGap())
        );
        panelHeaderLayout.setVerticalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHeaderLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_tanggal)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_jam)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(panelHeader, java.awt.BorderLayout.PAGE_START);

        panelBody.setBackground(new java.awt.Color(0, 102, 153));
        panelBody.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        panelBody.setPreferredSize(new java.awt.Dimension(300, 50));
        panelBody.setLayout(new java.awt.BorderLayout());
        getContentPane().add(panelBody, java.awt.BorderLayout.CENTER);

        footer.setBackground(new java.awt.Color(255, 255, 255));
        footer.setPreferredSize(new java.awt.Dimension(855, 35));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel3.setText("Created @ Sahabatech 2021");

        javax.swing.GroupLayout footerLayout = new javax.swing.GroupLayout(footer);
        footer.setLayout(footerLayout);
        footerLayout.setHorizontalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, footerLayout.createSequentialGroup()
                .addGap(0, 711, Short.MAX_VALUE)
                .addComponent(jLabel3))
        );
        footerLayout.setVerticalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, footerLayout.createSequentialGroup()
                .addGap(0, 22, Short.MAX_VALUE)
                .addComponent(jLabel3))
        );

        getContentPane().add(footer, java.awt.BorderLayout.PAGE_END);

        setSize(new java.awt.Dimension(871, 473));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void masuk(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_masuk
        pk.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_masuk

    private void move(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_move
        pk.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_move

    private void click(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_click
        int ok = JOptionPane.showConfirmDialog(null, "Yakin Mau Keluar?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            this.dispose();
            new LoginForm().show();
        }
    }//GEN-LAST:event_click

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
//                new Main().setVisible(true);
                new LoginForm().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel footer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    public javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    public javax.swing.JLabel jabatan;
    private javax.swing.JLabel keluar;
    private javax.swing.JPanel menus;
    private javax.swing.JPanel panelBody;
    public javax.swing.JPanel panelHeader;
    public javax.swing.JPanel panelMenu;
    private javax.swing.JPanel pk;
    public javax.swing.JLabel txt_jam;
    public javax.swing.JLabel txt_tanggal;
    public javax.swing.JLabel txt_username;
    // End of variables declaration//GEN-END:variables

    private void windowClosing() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
