/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.MasterProductController;
import java.awt.event.KeyEvent;
import static java.awt.image.ImageObserver.HEIGHT;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import main.Main;
import view.InventoryMasterbarang;

/**
 *
 * @author Dell
 */
public class ProductModel implements MasterProductController {

    protected java.sql.Connection con;
    protected PreparedStatement ps;
    protected ResultSet rs;
    protected Statement st;
    Main main = new Main();
    ImageIcon sucess = new ImageIcon(getClass().getResource("/asset/checked.png"));
    ImageIcon invalid = new ImageIcon(getClass().getResource("/asset/cancel.png"));
    ImageIcon warning = new ImageIcon(getClass().getResource("/asset/warning.png"));

    public void FilterAngka(KeyEvent b) {
        if (Character.isAlphabetic(b.getKeyChar())) {
            b.consume();
            JOptionPane.showMessageDialog(null, "Masukan Hanya Angka", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    public void fun_Clear(InventoryMasterbarang mp) {
        mp.product_nama.setText("");
        mp.product_hb.setText("");
        mp.product_hj.setText("");
        mp.product_qty.setText("");
        mp.product_jenis.setSelectedItem("Pilih");
        mp.product_satuan.setSelectedItem("Pilih");
    }

    @Override
    public void fun_Disable(InventoryMasterbarang mp) {
        mp.product_id.setEnabled(false);
        mp.product_nama.setEnabled(false);
        mp.product_hj.setEnabled(false);
        mp.product_hb.setEnabled(false);
        mp.product_jenis.setEnabled(false);
        mp.product_satuan.setEnabled(false);
        mp.product_qty.setEnabled(false);
        mp.bt_simpan.setEnabled(false);
        mp.bt_batal.setEnabled(false);
        mp.bt_simpan.setVisible(true);
        mp.bt_update.setVisible(false);
    }

    @Override
    public void fun_Enable(InventoryMasterbarang mp) {
        mp.product_nama.requestFocus();
        mp.product_nama.setEnabled(true);
        mp.product_hj.setEnabled(true);
        mp.product_hb.setEnabled(true);
        mp.product_qty.setEnabled(true);
        mp.product_jenis.setEnabled(true);
        mp.product_satuan.setEnabled(true);
        mp.bt_simpan.setEnabled(true);
        mp.bt_batal.setEnabled(true);
    }

    @Override
    public void fun_Cari(InventoryMasterbarang mp) {
        DefaultTableModel model = (DefaultTableModel) mp.table.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        mp.table.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(mp.tf_cari.getText().trim()));
    }

    @Override
    public void fun_Simpan(InventoryMasterbarang mp) throws SQLException {
        try {
            con = koneksi.Server.getConnection();
            String status = "Y";
            String query = "INSERT INTO `product` (`id`, `nama`, `jenis`, `satuan`, `harga_beli`, `harga_jual`, `stok`, `status`, `tanggal`) VALUES (?,?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(query);

            ps.setString(1, mp.product_id.getText());
            ps.setString(2, mp.product_nama.getText());
            ps.setString(3, mp.product_jenis.getSelectedItem().toString());
            ps.setString(4, mp.product_satuan.getSelectedItem().toString());
            ps.setString(5, mp.product_hb.getText());
            ps.setString(6, mp.product_hj.getText());
            ps.setString(7, mp.product_qty.getText());
            ps.setString(8, status);
            ps.setString(9, main.txt_tanggal.getText());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data Tersimpan!", "Berhasil", HEIGHT, sucess);
            ps.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ada Sedikit Masalah! " + e.getMessage().toString(), "Gagal", HEIGHT, invalid);
        } finally {
            fun_Disable(mp);
            fun_Clear(mp);
            fun_Tampil(mp);
            fun_GenID(mp);
        }
    }

    @Override
    public void fun_Update(InventoryMasterbarang mp) throws SQLException {
        try {
            con = koneksi.Server.getConnection();
            String query = "UPDATE product SET nama=?, jenis=?, satuan=?, harga_beli=?, harga_jual=?, tanggal=? WHERE id=?";
            ps = con.prepareStatement(query);

            ps.setString(7, mp.product_id.getText());
            ps.setString(1, mp.product_nama.getText());
            ps.setString(2, mp.product_jenis.getSelectedItem().toString());
            ps.setString(3, mp.product_satuan.getSelectedItem().toString());
            ps.setString(4, mp.product_hb.getText());
            ps.setString(5, mp.product_hj.getText());
            ps.setString(6, main.txt_tanggal.getText());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data Diperbaharui!", "Berhasil", HEIGHT, sucess);
            ps.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ada Sedikit Masalah! " + e.getMessage().toString(), "Gagal", HEIGHT, invalid);
        } finally {
            fun_Tampil(mp);
            fun_Disable(mp);
            fun_Clear(mp);
        }
    }

    @Override
    public void fun_Delete(InventoryMasterbarang mp) throws SQLException {
        try {
            con = koneksi.Server.getConnection();
            String query = "DELETE FROM product WHERE id=?";
            ps = con.prepareStatement(query);

            ps.setString(1, mp.product_id.getText());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data Terhapus!", "Berhasil", HEIGHT, sucess);
            ps.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ada Sedikit Masalah! " + e.getMessage().toString(), "Gagal", HEIGHT, invalid);
        } finally {
            fun_Tampil(mp);
            fun_Disable(mp);
            fun_Clear(mp);
        }
    }

    @Override
    public void fun_Table(InventoryMasterbarang mp) throws SQLException {
        try {
            int pilih = mp.table.getSelectedRow();
            mp.product_id.setText(mp.tblmodel.getValueAt(pilih, 0).toString());
            mp.product_nama.setText(mp.tblmodel.getValueAt(pilih, 1).toString());
            mp.product_jenis.setSelectedItem(mp.tblmodel.getValueAt(pilih, 2).toString());
            mp.product_satuan.setSelectedItem(mp.tblmodel.getValueAt(pilih, 6).toString());
            mp.product_hb.setText(mp.tblmodel.getValueAt(pilih, 3).toString());
            mp.product_hj.setText(mp.tblmodel.getValueAt(pilih, 4).toString());
            mp.product_qty.setText(mp.tblmodel.getValueAt(pilih, 5).toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error, Please Try Again!");
        }
    }

    @Override
    public void fun_Tampil(InventoryMasterbarang mp) throws SQLException {
        Object[] header = {"ID", "Nama", "Jenis", "Harga Beli", "Harga Jual", "Stok", "Satuan", "Last Update"};
        mp.tblmodel = new DefaultTableModel(null, header);
        try {
            con = koneksi.Server.getConnection();
            st = con.createStatement();
            String sql = "select id,nama,jenis, harga_beli, harga_jual, stok, satuan, tanggal from product where status='Y' order by id asc";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                mp.tblmodel.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8)
                });
            }
            mp.table.setModel(mp.tblmodel);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void fun_GenID(InventoryMasterbarang mp) throws SQLException {
        try {
            String query = "select MAX(RIGHT(id,4)) AS NO  from product order by id desc";
            ps = koneksi.Server.getConnection().prepareStatement(query);
            rs = ps.executeQuery(query);
            while (rs.next()) {
                if (rs.first() == false) {
                    mp.product_id.setText("MB100001");
                } else {
                    rs.last();
                    int auto_id = rs.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int Nomor = no.length();
                    //MENGATUR jumlah 0
                    for (int j = 0; j < 8 - Nomor; j++) {
                        no = "0" + no;
                    }
                    mp.product_id.setText("MB" + no);
                }
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();//penanganan masalah
        }
    }

}
