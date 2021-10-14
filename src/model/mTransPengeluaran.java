/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.TransPengeluaran;
import static java.awt.image.ImageObserver.HEIGHT;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import main.Main;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.view.JasperViewer;
import view.TransaksiPengeluaran;

/**
 *
 * @author Dell
 */
public class mTransPengeluaran implements TransPengeluaran {

    protected java.sql.Connection con;
    protected PreparedStatement ps;
    protected ResultSet rs;
    protected Statement st;
    Main main = new Main();
    public ImageIcon sucess = new ImageIcon(getClass().getResource("/asset/checked.png"));
    public ImageIcon invalid = new ImageIcon(getClass().getResource("/asset/cancel.png"));
    public ImageIcon warning = new ImageIcon(getClass().getResource("/asset/warning.png"));
    HashMap param = new HashMap();
    JasperReport jasreport;
    JasperPrint jasprint;
    JasperDesign jasdesign;

    @Override
    public void fun_Clear(TransaksiPengeluaran pengeluaran) {
        pengeluaran.nodok.setText("");
        pengeluaran.cb_tujuan.setSelectedItem("Pilih");
        pengeluaran.cb_jenis.setSelectedItem("Pilih");
        pengeluaran.keterangan.setText("");
        pengeluaran.txt_item.setText("0");
        pengeluaran.txt_qty.setText("0");
        pengeluaran.id.setText("");
        pengeluaran.nama.setText("");
        pengeluaran.qty.setText("");
        pengeluaran.tanggal.setDate(null);
        ((DefaultTableModel) pengeluaran.table.getModel()).setNumRows(0);
    }

    @Override
    public void fun_GetSales(TransaksiPengeluaran pengeluaran) throws SQLException {
        try {
            String query = "SELECT * FROM distributor ORDER BY nama ASC";
            ps = koneksi.Server.getConnection().prepareStatement(query);
            rs = ps.executeQuery(query);
            pengeluaran.cb_tujuan.removeAllItems();
            pengeluaran.cb_tujuan.addItem("Pilih");
            while (rs.next()) {
                pengeluaran.cb_tujuan.addItem(rs.getString("nama"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Bermasalah Get Sales: " + e.getMessage(), "Alert Message", HEIGHT, warning);
        }
    }

    @Override
    public void fun_Simpan(TransaksiPengeluaran pengeluaran) throws SQLException {
        String query = "INSERT INTO `pengeluaran` (`nodok`, `tanggal`, `jam`, `jenis`, `tujuan`, `keterangan`, `total_item`, `total_qty`, `created`, `status`) VALUES (?,?,?,?,?,?,?,?,?,?)";
        String query2 = "INSERT INTO `pengeluaran_detail`(`nodok`, `id`, `nama`, `qty`) VALUES (?,?,?,?)";
        String tgl = ((JTextField) pengeluaran.tanggal.getDateEditor().getUiComponent()).getText();
        String status = "Y";
        try {
            ps = koneksi.Server.getConnection().prepareStatement(query);
            ps.setString(1, pengeluaran.nodok.getText().trim());
            ps.setString(2, tgl);
            ps.setString(3, main.txt_jam.getText().trim());
            ps.setString(4, pengeluaran.cb_jenis.getSelectedItem().toString());
            ps.setString(5, pengeluaran.cb_tujuan.getSelectedItem().toString());
            ps.setString(6, pengeluaran.keterangan.getText());
            ps.setString(7, pengeluaran.txt_item.getText());
            ps.setString(8, pengeluaran.txt_qty.getText());
            ps.setString(9, main.txt_username.getText().trim());
            ps.setString(10, status);
            ps.executeUpdate();
            int t = pengeluaran.table.getRowCount();
            for (int i = 0; i < t; i++) {
                String id = pengeluaran.table.getValueAt(i, 0).toString();
                String nama = pengeluaran.table.getValueAt(i, 1).toString();
                String qty = pengeluaran.table.getValueAt(i, 2).toString();

                PreparedStatement stat2 = koneksi.Server.getConnection().prepareStatement(query2);
                stat2.setString(1, pengeluaran.nodok.getText());
                stat2.setString(2, id);
                stat2.setString(3, nama);
                stat2.setString(4, qty);
                stat2.executeUpdate();
            }
            int ok = JOptionPane.showConfirmDialog(null, "Cetak Nota Penerimaan ?", "Transaksi Selesai", JOptionPane.YES_NO_OPTION, HEIGHT, sucess);
            if (ok == 0) {
                try {
                    String NamaFile = "src/report/struk.jasper";
                    String URL = "jdbc:mysql://localhost:3306/";
                    String DB = "agendist";
                    String driver = "com.mysql.jdbc.Driver";
                    String user = "root";
                    String pass = "";
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    Connection connect = DriverManager.getConnection(URL + DB, user, pass);
                    HashMap param = new HashMap();
                    param.put("id", pengeluaran.nodok.getText());

                    JasperPrint jPrint = JasperFillManager.fillReport(NamaFile, param, connect);
                    JasperViewer.viewReport(jPrint, false);
                    fun_Clear(pengeluaran);
                    fun_Disable(pengeluaran);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Gagal Cetak" + e.getMessage(), "Alert Message!", HEIGHT);
                }
            } else {
                fun_Clear(pengeluaran);
                fun_Disable(pengeluaran);
            }
            fun_Clear(pengeluaran);
            fun_Disable(pengeluaran);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Bayar " + e.getMessage(), "Alert Message!", HEIGHT);
        }
    }

    @Override
    public void fun_GenID(TransaksiPengeluaran pengeluaran) throws SQLException {
        try {
            String query = "select MAX(RIGHT(nodok,5)) AS NO from pengeluaran order by nodok desc";
            ps = koneksi.Server.getConnection().prepareStatement(query);
            rs = ps.executeQuery(query);
            while (rs.next()) {
                if (rs.first() == false) {
                    pengeluaran.nodok.setText("PL10001");
                } else {
                    rs.last();
                    int auto_id = rs.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int Nomor = no.length();
                    //MENGATUR jumlah 0
                    for (int j = 0; j < 5 - Nomor; j++) {
                        no = "0" + no;
                    }
                    pengeluaran.nodok.setText("PL" + no);
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();//penanganan masalah
        }
    }

    @Override
    public void fun_CekStok(TransaksiPengeluaran pengeluaran) throws SQLException {
        String stokDB = "";
        try {
            String cekStock = "select stok from product where id='" + pengeluaran.id.getText() + "'";
            Statement stat = koneksi.Server.getConnection().createStatement();
            ResultSet hasil = stat.executeQuery(cekStock);
            while (hasil.next()) {
                stokDB = hasil.getString("stok");
            }
            int qtyDB = Integer.parseInt(stokDB);
            int input = Integer.parseInt(pengeluaran.qty.getText());
            if (qtyDB < input) {
                JOptionPane.showMessageDialog(null, "Stok Kurang Dari Permintaan!", "Gagal", HEIGHT, invalid);
                pengeluaran.qty.setText("");
                pengeluaran.qty.requestFocus();
            } else {
                fun_Tambah(pengeluaran);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Bermasalah Cek Stok: " + e.getMessage(), "Alert Message", HEIGHT, warning);
        }
    }

    @Override
    public void fun_Enabled(TransaksiPengeluaran pengeluaran) {
        pengeluaran.tanggal.setEnabled(true);
        pengeluaran.cb_tujuan.setEnabled(true);
        pengeluaran.cb_jenis.setEnabled(true);
        pengeluaran.keterangan.setEnabled(true);
        pengeluaran.bt_cari.setEnabled(true);
        pengeluaran.qty.setEnabled(true);
        pengeluaran.bt_tambah.setEnabled(true);
        pengeluaran.bt_min.setEnabled(true);
        pengeluaran.bt_simpan.setEnabled(true);
        pengeluaran.bt_batal.setEnabled(true);
    }

    @Override
    public void fun_Disable(TransaksiPengeluaran pengeluaran) {
        pengeluaran.tanggal.setEnabled(false);
        pengeluaran.cb_tujuan.setEnabled(false);
        pengeluaran.cb_jenis.setEnabled(false);
        pengeluaran.keterangan.setEnabled(false);
        pengeluaran.id.setEnabled(false);
        pengeluaran.bt_cari.setEnabled(false);
        pengeluaran.nama.setEnabled(false);
        pengeluaran.qty.setEnabled(false);
        pengeluaran.bt_tambah.setEnabled(false);
        pengeluaran.bt_min.setEnabled(false);
        pengeluaran.bt_simpan.setEnabled(false);
        pengeluaran.bt_batal.setEnabled(false);
    }

    @Override
    public void fun_Delete(TransaksiPengeluaran pengeluaran) {
        DefaultTableModel model = (DefaultTableModel) pengeluaran.table.getModel();
        int row = pengeluaran.table.getSelectedRow();
        if (row >= 0) {
            int ok = JOptionPane.showConfirmDialog(null, "Yakin Mau Hapus?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (ok == 0) {
                model.removeRow(row);

                int rows = model.getRowCount();
                pengeluaran.txt_item.setText("" + rows);
                //row
                int total = 0;
                for (int i = 0; i < pengeluaran.table.getRowCount(); i++) {
                    int amount = Integer.parseInt((String) pengeluaran.table.getValueAt(i, 2));
                    total += amount;
                }
                pengeluaran.txt_qty.setText("" + total);

            } else {
                pengeluaran.id.requestFocus();
            }
        }
    }

    @Override
    public void fun_Tambah(TransaksiPengeluaran pengeluaran) throws SQLException {
        pengeluaran.tblmodel = (DefaultTableModel) pengeluaran.table.getModel();
        pengeluaran.tblmodel.addRow(new Object[]{
            pengeluaran.id.getText(), pengeluaran.nama.getText(), pengeluaran.qty.getText()});
        pengeluaran.table.setModel(pengeluaran.tblmodel);
        int row = pengeluaran.tblmodel.getRowCount();
        pengeluaran.txt_item.setText("" + row);
        //row
        int total = 0;
        for (int i = 0; i < pengeluaran.table.getRowCount(); i++) {
            int amount = Integer.parseInt((String) pengeluaran.table.getValueAt(i, 2));
            total += amount;
        }
        pengeluaran.txt_qty.setText("" + total);
        pengeluaran.id.setText("");
        pengeluaran.nama.setText("");
        pengeluaran.qty.setText("");
    }

}
