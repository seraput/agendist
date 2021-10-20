/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.KeyEvent;
import static java.awt.image.ImageObserver.HEIGHT;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.mTransPenerimaan;
import popup.PopCariPenerimaan;
import popup.PopCariPengeluaranBaik;

/**
 *
 * @author Dell
 */
public class TransaksiPenerimaan extends javax.swing.JPanel {

    mTransPenerimaan tpm = new mTransPenerimaan();
    public String exBarcode, exNama;
    public DefaultTableModel tblmodel;

    /**
     * Creates new form TransaksiPenerimaan
     */
    public TransaksiPenerimaan() {
        initComponents();
    }

    public void FilterAngka(KeyEvent b) {
        if (Character.isAlphabetic(b.getKeyChar())) {
            b.consume();
            JOptionPane.showMessageDialog(null, "Masukan Hanya Angka", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void itemTerpilihBarang() throws SQLException {
        PopCariPenerimaan pcp = new PopCariPenerimaan();
        pcp.tp = this;
        id.setText(exBarcode);
        nama.setText(exNama);
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
        title = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        nodok = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        tanggal = new com.toedter.calendar.JDateChooser();
        jScrollPane4 = new javax.swing.JScrollPane();
        keterangan = new javax.swing.JTextArea();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        cb_tujuan = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        id = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        nama = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        qty = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        bt_tambah = new javax.swing.JButton();
        bt_min = new javax.swing.JButton();
        bt_cari = new javax.swing.JButton();
        bt_simpan = new javax.swing.JButton();
        bt_batal = new javax.swing.JButton();
        txt_item = new javax.swing.JLabel();
        txt_qty = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(0, 153, 204));

        title.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setText("Transaksi - Penerimaan Barang");

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton4.setText("Tambah");
        jButton4.setPreferredSize(new java.awt.Dimension(70, 25));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Form Pengeluaran"));

        jLabel12.setText("No Dokumen");

        nodok.setEnabled(false);

        jLabel13.setText("Tanggal");

        tanggal.setDateFormatString("dd-MM-yyyy");
        tanggal.setEnabled(false);

        keterangan.setColumns(20);
        keterangan.setRows(5);
        keterangan.setEnabled(false);
        jScrollPane4.setViewportView(keterangan);

        jLabel15.setText("Keterangan");

        jLabel16.setText("Dari");

        cb_tujuan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb_tujuan.setEnabled(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel13)
                            .addComponent(tanggal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16)
                            .addComponent(cb_tujuan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 20, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(nodok, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nodok, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_tujuan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(93, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("List Item"));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Produk", "Nama Produk", "Qty"
            }
        ));
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Produk"));

        id.setEnabled(false);

        jLabel17.setText("Produk ID");

        nama.setEnabled(false);

        jLabel18.setText("Nama Produk");

        qty.setEnabled(false);
        qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                qtyKeyPressed(evt);
            }
        });

        jLabel19.setText("Qty");

        bt_tambah.setBackground(new java.awt.Color(0, 255, 0));
        bt_tambah.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bt_tambah.setText("+");
        bt_tambah.setEnabled(false);
        bt_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_tambahActionPerformed(evt);
            }
        });

        bt_min.setBackground(new java.awt.Color(255, 0, 51));
        bt_min.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bt_min.setText("-");
        bt_min.setEnabled(false);
        bt_min.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_minActionPerformed(evt);
            }
        });

        bt_cari.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bt_cari.setText("Cari");
        bt_cari.setEnabled(false);
        bt_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cariActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_cari))
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nama, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(bt_tambah)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_min)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel18))
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(qty, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(nama, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_cari, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(id, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_tambah, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_min, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bt_simpan.setBackground(new java.awt.Color(0, 153, 204));
        bt_simpan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bt_simpan.setForeground(new java.awt.Color(255, 255, 255));
        bt_simpan.setText("Selesai");
        bt_simpan.setEnabled(false);
        bt_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_simpanActionPerformed(evt);
            }
        });

        bt_batal.setBackground(new java.awt.Color(255, 0, 51));
        bt_batal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bt_batal.setForeground(new java.awt.Color(255, 255, 255));
        bt_batal.setText("Batal");
        bt_batal.setEnabled(false);
        bt_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_batalActionPerformed(evt);
            }
        });

        txt_item.setText("0");

        txt_qty.setText("0");

        jLabel1.setText("Total Item :");

        jLabel2.setText("Total Qty :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_item, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bt_batal, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(bt_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(bt_batal, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_item)
                                .addComponent(txt_qty)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2))))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        tpm.fun_Enabled(this);
        try {
            tpm.fun_GetDist(this);
            tpm.fun_GenID(this);
        } catch (SQLException ex) {
            Logger.getLogger(TransaksiPenerimaan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void bt_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_simpanActionPerformed
        String no = nodok.getText();
        String tgl = ((JTextField) tanggal.getDateEditor().getUiComponent()).getText();
        String jenis = cb_tujuan.getSelectedItem().toString();
        String ket = keterangan.getText();
        int row = Integer.parseInt(txt_item.getText());
        if (!no.isEmpty() && !tgl.isEmpty() && !jenis.equals("Pilih") && !ket.isEmpty() && row > 0) {
            try {
                tpm.fun_Simpan(this);
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiPenerimaan.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Data Belum Lengkap!", "Perhatian!", HEIGHT, tpm.warning);
        }
    }//GEN-LAST:event_bt_simpanActionPerformed

    private void bt_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_batalActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "Yakin Mau Membatalkan?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            String query = "DELETE FROM mutasi_stok WHERE notrans=? and id=?";
            try {
                int t = table.getRowCount();
                for (int i = 0; i < t; i++) {
                    String barcode = table.getValueAt(i, 0).toString();
                    PreparedStatement stat2 = koneksi.Server.getConnection().prepareStatement(query);
                    stat2.setString(1, nodok.getText());
                    stat2.setString(2, barcode);
                    stat2.executeUpdate();
                }
                JOptionPane.showMessageDialog(null, "Data Sudah Dibersihkan!", "Berhasil", HEIGHT, tpm.trash);
                tpm.fun_Clear(this);
                tpm.fun_Disable(this);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ada Sedikit Masalah! " + ex.getMessage(), "Gagal", HEIGHT, tpm.invalid);
            }
        }
    }//GEN-LAST:event_bt_batalActionPerformed

    private void bt_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cariActionPerformed
        PopCariPenerimaan pcp = new PopCariPenerimaan();
        pcp.tp = this;
        pcp.setVisible(true);
        pcp.setResizable(false);
    }//GEN-LAST:event_bt_cariActionPerformed

    private void qtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtyKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (qty.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Qty Belum Terisi!", "Perhatian!", HEIGHT, tpm.warning);
                qty.requestFocus();
                qty.setText("");
            } else {
                int input = Integer.parseInt(qty.getText());
                if (input < 1) {
                    JOptionPane.showMessageDialog(null, "Qty Tidak Boleh 0 ", "Perhatian!", HEIGHT, tpm.warning);
                    qty.requestFocus();
                    qty.setText("");
                } else {
                    try {
                        tpm.fun_CekStok(this);
                    } catch (SQLException ex) {
                        Logger.getLogger(TransaksiPenerimaan.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }//GEN-LAST:event_qtyKeyPressed

    private void bt_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_tambahActionPerformed
        if (qty.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Qty Belum Terisi!", "Perhatian!", HEIGHT, tpm.warning);
            qty.requestFocus();
            qty.setText("");
        } else {
            int input = Integer.parseInt(qty.getText());
            if (input < 1) {
                JOptionPane.showMessageDialog(null, "Qty Tidak Boleh 0 ", "Perhatian!", HEIGHT, tpm.warning);
                qty.requestFocus();
                qty.setText("");
            } else {

                try {
                    tpm.fun_CekStok(this);
                } catch (SQLException ex) {
                    Logger.getLogger(TransaksiPenerimaan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_bt_tambahActionPerformed

    private void bt_minActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_minActionPerformed
        try {
            tpm.fun_Delete(this);
        } catch (SQLException ex) {
            Logger.getLogger(TransaksiPenerimaan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_minActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton bt_batal;
    public javax.swing.JButton bt_cari;
    public javax.swing.JButton bt_min;
    public javax.swing.JButton bt_simpan;
    public javax.swing.JButton bt_tambah;
    public javax.swing.JComboBox<String> cb_tujuan;
    public javax.swing.JTextField id;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    public javax.swing.JTextArea keterangan;
    public javax.swing.JTextField nama;
    public javax.swing.JTextField nodok;
    public javax.swing.JTextField qty;
    public javax.swing.JTable table;
    public com.toedter.calendar.JDateChooser tanggal;
    private javax.swing.JLabel title;
    public javax.swing.JLabel txt_item;
    public javax.swing.JLabel txt_qty;
    // End of variables declaration//GEN-END:variables
}
