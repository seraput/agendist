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
import model.LoginModel;
import view.LoginForm;

public class Main extends javax.swing.JFrame {

    public static String username, tanggal;
    public MenuItem menuDashboard, menuGeneral, menuInventory, menuTransaction, menuReport, menuUpload;

    public Main() {
        initComponents();

        txt_username.setText(LoginModel.username);
        jabatan.setText(LoginModel.jabatan);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        execute();
        cekJabatan();
        tanggal();
        jam();
    }

    public void cekJabatan() {
        String jb = jabatan.getText().toString();
        if (jb.equals("Administrator")) {
            menuDashboard.setVisible(true);
            menuGeneral.setVisible(true);
            menuInventory.setVisible(true);
            menuTransaction.setVisible(true);
            menuReport.setVisible(true);
            menuUpload.setVisible(true);
        } else if (jb.equals("Owner")) {
            menuDashboard.setVisible(true);
            menuGeneral.setVisible(false);
            menuInventory.setVisible(true);
            menuTransaction.setVisible(false);
            menuReport.setVisible(true);
            menuUpload.setVisible(false);
        } else if (jb.equals("Kasir")) {
            menuDashboard.setVisible(true);
            menuGeneral.setVisible(true);
            menuInventory.setVisible(true);
            menuTransaction.setVisible(true);
            menuReport.setVisible(false);
            menuUpload.setVisible(true);
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
        MenuItem data = new MenuItem(iconSubMenu, "Data Master", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.removeAll();
                panelBody.add(new MasterData());
                panelBody.repaint();
                panelBody.revalidate();
            }
        });

        MenuItem discount = new MenuItem(iconSubMenu, "Promo Master", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.removeAll();
                panelBody.add(new MasterDiscount());
                panelBody.repaint();
                panelBody.revalidate();
            }
        });
        MenuItem payment = new MenuItem(iconSubMenu, "Payment Master", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.removeAll();
                panelBody.add(new MasterPayment());
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

        MenuItem menuReject = new MenuItem(iconSubMenu, "Stock Reject", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.removeAll();
                panelBody.add(new InventoryReject());
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
        MenuItem menuKeluar = new MenuItem(iconSubMenu, "Pengeluaran", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.removeAll();
                panelBody.add(new TransaksiPengeluaran());
                panelBody.repaint();
                panelBody.revalidate();
            }
        });
        MenuItem menuMasuk = new MenuItem(iconSubMenu, "Penerimaan", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.removeAll();
                panelBody.add(new TransaksiPenerimaan());
                panelBody.repaint();
                panelBody.revalidate();
            }
        });

        //  submenu report 
        MenuItem reportPenjualan = new MenuItem(iconSubMenu, "Report Penjualan", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.removeAll();
                panelBody.add(new ReportPenjualan());
                panelBody.repaint();
                panelBody.revalidate();
            }
        });
        MenuItem reportStock = new MenuItem(iconSubMenu, "Report Stock", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.removeAll();
                panelBody.add(new ReportStock());
                panelBody.repaint();
                panelBody.revalidate();
            }
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
        MenuItem uploadBarang = new MenuItem(iconSubMenu, "Upload Data Stock", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.removeAll();
                panelBody.add(new UploadStock());
                panelBody.repaint();
                panelBody.revalidate();
            }
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

        //  main menu
        menuDashboard = new MenuItem(iconDashboard, "Home - Dashboard", null);
        menuGeneral = new MenuItem(iconGeneral, "General Master", null, user, data, discount, payment);
        menuInventory = new MenuItem(iconInventory, "Inventory", null, product, menuReject, menuOpname);
        menuTransaction = new MenuItem(iconPayment, "Transaksi", null, menuSales, menuKeluar, menuMasuk);
        menuReport = new MenuItem(iconReport, "Report", null, reportPenjualan, reportStock, reportPelanggan);
        menuUpload = new MenuItem(iconUpload, "Upload", null, uploadBarang, uploadUser, uploadPelanggan);
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
        jabatan = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        txt_jam = new javax.swing.JLabel();
        panelMenu = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        menus = new javax.swing.JPanel();
        panelBody = new javax.swing.JPanel();
        footer = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelHeader.setBackground(new java.awt.Color(255, 255, 255));
        panelHeader.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelHeader.setForeground(new java.awt.Color(255, 255, 255));
        panelHeader.setPreferredSize(new java.awt.Dimension(561, 50));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("TOKO AGEN");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Selamat Datang");

        txt_tanggal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txt_tanggal.setText("Tanggal");

        txt_username.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txt_username.setText("username");

        jabatan.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jabatan.setText("jabatan");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        txt_jam.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txt_jam.setText("Jam");

        javax.swing.GroupLayout panelHeaderLayout = new javax.swing.GroupLayout(panelHeader);
        panelHeader.setLayout(panelHeaderLayout);
        panelHeaderLayout.setHorizontalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHeaderLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel2)
                .addGap(55, 55, 55)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelHeaderLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_username, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 326, Short.MAX_VALUE)
                        .addComponent(txt_tanggal))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelHeaderLayout.createSequentialGroup()
                        .addComponent(jabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_jam)))
                .addContainerGap())
        );
        panelHeaderLayout.setVerticalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHeaderLayout.createSequentialGroup()
                .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(panelHeaderLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_username)
                            .addComponent(jLabel1)
                            .addComponent(txt_tanggal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jabatan)
                            .addComponent(txt_jam))
                        .addGap(5, 5, 5)))
                .addGap(2, 2, 2))
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
        );

        getContentPane().add(panelMenu, java.awt.BorderLayout.LINE_START);

        panelBody.setBackground(new java.awt.Color(204, 204, 204));
        panelBody.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelBody.setLayout(new java.awt.BorderLayout());
        getContentPane().add(panelBody, java.awt.BorderLayout.CENTER);

        footer.setBackground(new java.awt.Color(255, 255, 255));
        footer.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        footer.setPreferredSize(new java.awt.Dimension(855, 50));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/logout24.png"))); // NOI18N
        jLabel3.setText("Keluar");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout footerLayout = new javax.swing.GroupLayout(footer);
        footer.setLayout(footerLayout);
        footerLayout.setHorizontalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(footerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(777, Short.MAX_VALUE))
        );
        footerLayout.setVerticalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, footerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(footer, java.awt.BorderLayout.PAGE_END);

        setSize(new java.awt.Dimension(871, 473));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        int ok = JOptionPane.showConfirmDialog(null, "Yakin Mau Keluar?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            this.dispose();
            new LoginForm().show();
        }
    }//GEN-LAST:event_jLabel3MouseClicked

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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    public javax.swing.JLabel jabatan;
    private javax.swing.JPanel menus;
    private javax.swing.JPanel panelBody;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel panelMenu;
    public javax.swing.JLabel txt_jam;
    public javax.swing.JLabel txt_tanggal;
    public javax.swing.JLabel txt_username;
    // End of variables declaration//GEN-END:variables
}
