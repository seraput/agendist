/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import main.Main;
import model.UserSession;

/**
 *
 * @author Dell
 */
public final class OpnameAwal extends javax.swing.JPanel {

    /**
     * Creates new form OpnameAwal
     */
    PreparedStatement ps;
    ResultSet rs;
    protected java.sql.Connection con;
    private DefaultTableModel dtOpname;
    private DefaultTableModel dtOpnameDetail;
    ImageIcon sucess = new ImageIcon(getClass().getResource("/asset/checked.png"));
    ImageIcon invalid = new ImageIcon(getClass().getResource("/asset/cancel.png"));
    ImageIcon warning = new ImageIcon(getClass().getResource("/asset/warning.png"));
    final String level = UserSession.getsJabatan();

    public OpnameAwal() {
        initComponents();
        permission();
        TableOpname();
        txt_item.setVisible(false);
    }

    private void permission() {
        if (level.equals("Kasir") || level.equals("Sales")) {
            simpan.setVisible(false);
            tarik.setVisible(false);
        } else {
            simpan.setVisible(true);
            status.setVisible(true);
            tarik.setVisible(true);
        }
    }

    private void Default() {
        nodok.setText("");
        bulan.setText("");
        tanggal.setText("");
        status.setSelectedItem("Pilih");
        status.setEnabled(false);
        simpan.setEnabled(false);
        TableOpname();
        ((DefaultTableModel) tb_detail.getModel()).setNumRows(0);
    }

    private void ApprovedOpname() {
        String query = "UPDATE opname SET status=? WHERE nodok=?";
        String query1 = "UPDATE opname_detail SET status=? WHERE nodok=?";
        String query2 = "INSERT INTO `opname_ok`(`nodok`, `id`, `qty_sistem`, `qty_baik`, qty_bad, selisih) VALUES (?,?,?,?,?,?)";
        String s = "A";
        try {
            ps = koneksi.Server.getConnection().prepareStatement(query);
            ps.setString(2, nodok.getText().trim());
            ps.setString(1, s);
            ps.executeUpdate();
            ps.close();

            PreparedStatement ps1 = koneksi.Server.getConnection().prepareStatement(query1);
            ps1.setString(2, nodok.getText().trim());
            ps1.setString(1, s);
            ps1.executeUpdate();
            ps1.close();

            int t = tb_detail.getRowCount();
            for (int i = 0; i < t; i++) {
                String no = tb_detail.getValueAt(i, 0).toString();
                String id = tb_detail.getValueAt(i, 1).toString();
                String sistem = tb_detail.getValueAt(i, 3).toString();
                String baik = tb_detail.getValueAt(i, 4).toString();
                String bad = tb_detail.getValueAt(i, 5).toString();
                String selisih = tb_detail.getValueAt(i, 6).toString();

                PreparedStatement stat = koneksi.Server.getConnection().prepareStatement(query2);
                stat.setString(1, no);
                stat.setString(2, id);
                stat.setString(3, sistem);
                stat.setString(4, baik);
                stat.setString(5, bad);
                stat.setString(6, selisih);
                stat.executeUpdate();
                stat.close();

            }
            Default();
            JOptionPane.showMessageDialog(null, "Tersimpan", "Berhasil", HEIGHT, sucess);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ada Sedikit Masalah! " + e.getMessage().toString(), "Gagal", HEIGHT, invalid);
        }
    }

    private void RejectOpname() {
        String query = "UPDATE opname SET status=? WHERE nodok=?";
        String query1 = "UPDATE opname_detail SET status=? WHERE nodok=?";
        String s = "R";
        try {
            ps = koneksi.Server.getConnection().prepareStatement(query);
            ps.setString(2, nodok.getText().trim());
            ps.setString(1, s);
            ps.executeUpdate();
            ps.close();

            PreparedStatement ps1 = koneksi.Server.getConnection().prepareStatement(query1);
            ps1.setString(2, nodok.getText().trim());
            ps1.setString(1, s);
            ps1.executeUpdate();
            ps1.close();
            Default();
            JOptionPane.showMessageDialog(null, "Tersimpan", "Berhasil", HEIGHT, sucess);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ada Sedikit Masalah! " + e.getMessage().toString(), "Gagal", HEIGHT, invalid);
        }
    }

    private void TableSelect() {
        try {
            int pilih = tb_data.getSelectedRow();
            nodok.setText(dtOpname.getValueAt(pilih, 0).toString());
            bulan.setText(dtOpname.getValueAt(pilih, 1).toString());
            tanggal.setText(dtOpname.getValueAt(pilih, 5).toString());
            String stat = dtOpname.getValueAt(pilih, 7).toString();
            if (stat.equals("W")) {
                status.setSelectedItem("Waiting");
                status.setEnabled(true);
            } else if (stat.equals("A")) {
                status.setSelectedItem("Approved");
                status.setEnabled(false);
                simpan.setEnabled(false);
            }
            else if (stat.equals("R")) {
                status.setSelectedItem("Reject");
                status.setEnabled(false);
                simpan.setEnabled(false);
            }
            else {
                status.setSelectedItem("Pilih");
                status.setEnabled(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error, Please Try Again!");
        }
    }

    protected void TableOpname() {
        Object[] Baris = {"NoDok", "Bulan", "Tahun", "Total Barang", "Author", "Tanggal", "Jam", "Status"};
        dtOpname = new DefaultTableModel(null, Baris);

        try {
            String sql = "SELECT * FROM opname order by tanggal desc";
            Statement stat = koneksi.Server.getConnection().createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                dtOpname.addRow(new Object[]{
                    hasil.getString(1),
                    hasil.getString(2),
                    hasil.getString(3),
                    hasil.getString(4),
                    hasil.getString(5),
                    hasil.getString(6),
                    hasil.getString(7),
                    hasil.getString(8)
                });
            }
            tb_data.setModel(dtOpname);
            hasil.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Memuat: " + e);
        }

    }

    protected void TableOpnameDetail() {
        Object[] Baris = {"NoDok", "ID Barang", "Nama Barang", "Qty Sistem", "Qty Baik", "Qty Reject", "Selisih"};
        dtOpnameDetail = new DefaultTableModel(null, Baris);

        try {
            String sql = "SELECT opname_detail.`nodok`, opname_detail.`id`, opname_detail.`nama`, opname_detail.`qty_sistem`, opname_detail.`qty_baik`, opname_detail.`qty_bad`, opname_detail.`qty_bad`+opname_detail.`qty_baik`-opname_detail.`qty_sistem` AS 'selisih' FROM opname_detail WHERE nodok='" + nodok.getText() + "' ORDER BY id ASC;";
            Statement stat = koneksi.Server.getConnection().createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                dtOpnameDetail.addRow(new Object[]{
                    hasil.getString(1),
                    hasil.getString(2),
                    hasil.getString(3),
                    hasil.getString(4),
                    hasil.getString(5),
                    hasil.getString(6),
                    hasil.getString(7)
                });
                int rows = dtOpnameDetail.getRowCount();
                txt_item.setText("" + rows);
            }
            tb_detail.setModel(dtOpnameDetail);
            hasil.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Memuat: " + e);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_data = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_detail = new javax.swing.JTable();
        tarik = new javax.swing.JButton();
        txt_item = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nodok = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tanggal = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        bulan = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        status = new javax.swing.JComboBox<>();
        simpan = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Opname Data"));

        tb_data.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tb_data.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_dataMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_data);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Detail Barang"));

        tb_detail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tb_detail);

        tarik.setText("Tarik Data");
        tarik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tarikActionPerformed(evt);
            }
        });

        txt_item.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(tarik)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_item)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tarik)
                    .addComponent(txt_item))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel2.setText("No Dokumen");

        nodok.setEnabled(false);

        jLabel3.setText("Tanggal Buat");

        tanggal.setEnabled(false);

        jLabel4.setText("Opname Bulan");

        bulan.setEnabled(false);

        jLabel6.setText("Status Opname");

        status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "Approved", "Waiting", "Reject" }));
        status.setEnabled(false);
        status.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                statusItemStateChanged(evt);
            }
        });

        simpan.setText("Simpan");
        simpan.setEnabled(false);
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nodok, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bulan, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1))
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nodok, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bulan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tb_dataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_dataMouseClicked
        TableSelect();
    }//GEN-LAST:event_tb_dataMouseClicked

    private void tarikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tarikActionPerformed
        if (!nodok.getText().isEmpty()) {
            TableOpnameDetail();
        } else {
            JOptionPane.showMessageDialog(null, "Gagal Memuat, Belum Ada Data yang Terpilih!");
        }
    }//GEN-LAST:event_tarikActionPerformed

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        int i = Integer.parseInt(txt_item.getText());
        if (!status.getSelectedItem().equals("Pilih") && i > 0) {
            if (status.getSelectedItem().equals("Approved") && i > 0) {
                ApprovedOpname();
//                JOptionPane.showMessageDialog(null, "Approved Jalan!");
            } else if (status.getSelectedItem().equals("Reject") && i > 0) {
                RejectOpname();
//                JOptionPane.showMessageDialog(null, "Reject Jalan");
            } else {
                JOptionPane.showMessageDialog(null, "Tentukan Aksi!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Gagal Memuat, Belum Ada Data");
        }
    }//GEN-LAST:event_simpanActionPerformed

    private void statusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_statusItemStateChanged
        if (status.getSelectedItem().equals("Waiting")) {
            simpan.setEnabled(false);
            ((DefaultTableModel) tb_detail.getModel()).setNumRows(0);
        } else if (status.getSelectedItem().equals("Approved") || status.getSelectedItem().equals("Reject")) {
            simpan.setEnabled(true);
            ((DefaultTableModel) tb_detail.getModel()).setNumRows(0);
        } else {
            simpan.setEnabled(false);
            ((DefaultTableModel) tb_detail.getModel()).setNumRows(0);
        }
    }//GEN-LAST:event_statusItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bulan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField nodok;
    public javax.swing.JButton simpan;
    private javax.swing.JComboBox<String> status;
    private javax.swing.JTextField tanggal;
    private javax.swing.JButton tarik;
    private javax.swing.JTable tb_data;
    private javax.swing.JTable tb_detail;
    public javax.swing.JLabel txt_item;
    // End of variables declaration//GEN-END:variables
}
