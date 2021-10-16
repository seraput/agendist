/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.InvStokLainnya;
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
import net.sf.jasperreports.view.JasperViewer;
import view.InventoryStokLainnya;

/**
 *
 * @author Dell
 */
public class mInvStokLainnya implements InvStokLainnya {

    protected java.sql.Connection con;
    protected PreparedStatement ps;
    protected ResultSet rs;
    protected Statement st;
    Main main = new Main();
    public ImageIcon sucess = new ImageIcon(getClass().getResource("/asset/checked.png"));
    public ImageIcon invalid = new ImageIcon(getClass().getResource("/asset/cancel.png"));
    public ImageIcon warning = new ImageIcon(getClass().getResource("/asset/warning.png"));

    @Override
    public void fun_Clear(InventoryStokLainnya isl) {
        isl.jenis.setSelectedItem("Pilih");
        isl.keterangan.setText("");
        isl.kode.setText("");
        isl.nama.setText("");
        isl.kode.setText("");
        isl.txt_item.setText("0");
        isl.txt_qty.setText("0");
        ((DefaultTableModel) isl.table.getModel()).setNumRows(0);
    }

    @Override
    public void fun_Simpan(InventoryStokLainnya isl) throws SQLException {
        String query = "INSERT INTO `bad_stok` (`nodok`, `jenis`, `keterangan`, `item`, `qty`, `created`, `tanggal`, `jam`) VALUES (?,?,?,?,?,?,?,?)";
        String query2 = "INSERT INTO `bad_stok_detail`(`nodok`, `id`, `nama`, `qty`) VALUES (?,?,?,?)";
        try {
            ps = koneksi.Server.getConnection().prepareStatement(query);
            ps.setString(1, isl.nodok.getText().trim());
            ps.setString(2, isl.jenis.getSelectedItem().toString());
            ps.setString(3, isl.keterangan.getText());
            ps.setString(4, isl.txt_item.getText());
            ps.setString(5, isl.txt_qty.getText());
            ps.setString(6, main.txt_username.getText().trim());
            ps.setString(7, main.txt_tanggal.getText());
            ps.setString(8, main.txt_jam.getText().trim());
            ps.executeUpdate();
            ps.close();
            int t = isl.table.getRowCount();
            for (int i = 0; i < t; i++) {
                String id = isl.table.getValueAt(i, 0).toString();
                String nama = isl.table.getValueAt(i, 1).toString();
                String qty = isl.table.getValueAt(i, 2).toString();

                PreparedStatement stat2 = koneksi.Server.getConnection().prepareStatement(query2);
                stat2.setString(1, isl.nodok.getText());
                stat2.setString(2, id);
                stat2.setString(3, nama);
                stat2.setString(4, qty);
                stat2.executeUpdate();
                stat2.close();
            }
            int ok = JOptionPane.showConfirmDialog(null, "Cetak Penambahan Stok ?", "Proses Selesai", JOptionPane.YES_NO_OPTION, HEIGHT, sucess);
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
                    param.put("id", isl.nodok.getText());

                    JasperPrint jPrint = JasperFillManager.fillReport(NamaFile, param, connect);
                    JasperViewer.viewReport(jPrint, false);
                    fun_Clear(isl);
                    fun_GenID(isl);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Gagal Cetak" + e.getMessage(), "Alert Message!", HEIGHT);
                }
            } else {
                fun_Clear(isl);
                fun_GenID(isl);
            }
            fun_Clear(isl);
            fun_GenID(isl);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Bayar " + e.getMessage(), "Alert Message!", HEIGHT);
        }
    }

    @Override
    public void fun_Delete(InventoryStokLainnya isl) {
        DefaultTableModel model = (DefaultTableModel) isl.table.getModel();
        int row = isl.table.getSelectedRow();
        if (row >= 0) {
            int ok = JOptionPane.showConfirmDialog(null, "Yakin Mau Hapus?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (ok == 0) {
                model.removeRow(row);

                int rows = model.getRowCount();
                isl.txt_item.setText("" + rows);
                isl.kode.setText("");
                isl.nama.setText("");
                isl.qty.setText("");

            } else {
                isl.kode.requestFocus();
            }
        }
    }

    @Override
    public void fun_GenID(InventoryStokLainnya isl) throws SQLException {
        try {
            String query = "select MAX(RIGHT(nodok,5)) AS NO from bad_stok order by nodok desc";
            ps = koneksi.Server.getConnection().prepareStatement(query);
            rs = ps.executeQuery(query);
            while (rs.next()) {
                if (rs.first() == false) {
                    isl.nodok.setText("SL10001");
                } else {
                    rs.last();
                    int auto_id = rs.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int Nomor = no.length();
                    //MENGATUR jumlah 0
                    for (int j = 0; j < 5 - Nomor; j++) {
                        no = "0" + no;
                    }
                    isl.nodok.setText("SL" + no);
                }
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();//penanganan masalah
        }
    }

    @Override
    public void fun_CekStok(InventoryStokLainnya isl) throws SQLException {
        String stokDB = "";
        try {
            String cekStock = "select qty from produk_baik where id='" + isl.kode.getText() + "'";
            Statement stat = koneksi.Server.getConnection().createStatement();
            ResultSet hasil = stat.executeQuery(cekStock);
            while (hasil.next()) {
                stokDB = hasil.getString("qty");
            }
            int qtyDB = Integer.parseInt(stokDB);
            int input = Integer.parseInt(isl.qty.getText());
            if (qtyDB < input) {
                JOptionPane.showMessageDialog(null, "Stok Kurang Dari Permintaan!", "Gagal", HEIGHT, invalid);
                isl.qty.setText("");
                isl.qty.requestFocus();
            } else {
                fun_Tambah(isl);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Bermasalah Cek Stok: " + e.getMessage(), "Alert Message", HEIGHT, warning);
        }
    }

    @Override
    public void fun_Tambah(InventoryStokLainnya isl) throws SQLException {
        isl.tblmodel = (DefaultTableModel) isl.table.getModel();
        isl.tblmodel.addRow(new Object[]{
            isl.kode.getText(), isl.nama.getText(), isl.qty.getText()});
        isl.table.setModel(isl.tblmodel);
        int row = isl.tblmodel.getRowCount();
        isl.txt_item.setText("" + row);
        isl.kode.setText("");
        isl.nama.setText("");
        isl.qty.setText("");
        fun_Hitung(isl);
    }

    public void fun_Hitung(InventoryStokLainnya isl) {
        int total = 0;
        for (int i = 0; i < isl.table.getRowCount(); i++) {
            int amount = Integer.parseInt((String) isl.table.getValueAt(i, 2));
            total += amount;
        }
        isl.txt_qty.setText("" + total);
    }

    @Override
    public void fun_Table(InventoryStokLainnya isl) {
        try {
            int pilih = isl.table.getSelectedRow();
            isl.kode.setText(isl.tblmodel.getValueAt(pilih, 0).toString());
            isl.nama.setText(isl.tblmodel.getValueAt(pilih, 1).toString());
            isl.qty.setText(isl.tblmodel.getValueAt(pilih, 2).toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error, Please Try Again!");
        }
    }

}
