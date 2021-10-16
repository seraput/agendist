/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.InvStokOpname;
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
import view.InventoryOpname;
import view.InventoryStokLainnya;
import view.OpnameTambah;
import view.TransaksiPenjualan;

/**
 *
 * @author Dell
 */
public class mInvStokOpname implements InvStokOpname{

    protected java.sql.Connection con;
    protected PreparedStatement ps;
    protected ResultSet rs;
    protected Statement st;
    Main main = new Main();
    public ImageIcon sucess = new ImageIcon(getClass().getResource("/asset/checked.png"));
    public ImageIcon invalid = new ImageIcon(getClass().getResource("/asset/cancel.png"));
    public ImageIcon warning = new ImageIcon(getClass().getResource("/asset/warning.png"));
    
    @Override
    public void fun_Clear(OpnameTambah ot) {
        ot.nama.setText("");
        ot.kode.setText("");
        ot.qty_baik.setText("");
        ot.qty_bad.setText("");
        ot.txt_item.setText("0");
        ((DefaultTableModel) ot.table.getModel()).setNumRows(0);
    }

    @Override
    public void fun_Simpan(OpnameTambah ot) throws SQLException {
        String query = "INSERT INTO `opname` (`nodok`, `bulan`, `tahun`, `item`, `created`, `tanggal`, `jam`, `status`) VALUES (?,?,?,?,?,?,?,?)";
        String query2 = "INSERT INTO `opname_detail`(`nodok`, `id`, `nama`, `qty_sistem`, `qty_baik`, `qty_bad`, `status`) VALUES (?,?,?,?,?,?,?)";
        int tahun = ot.tahun.getYear();
        String status1 = "W";
        try {
            ps = koneksi.Server.getConnection().prepareStatement(query);
            ps.setString(1, ot.nodok.getText().trim());
            ps.setString(2, ot.bulan.getSelectedItem().toString());
            ps.setString(3, String.valueOf(tahun));
            ps.setString(4, ot.txt_item.getText());
            ps.setString(5, main.txt_username.getText().trim());
            ps.setString(6, main.txt_tanggal.getText());
            ps.setString(7, main.txt_jam.getText().trim());
            ps.setString(8, status1);
            ps.executeUpdate();
            ps.close();
            int t = ot.table.getRowCount();
            for (int i = 0; i < t; i++) {
                String id = ot.table.getValueAt(i, 0).toString();
                String nama = ot.table.getValueAt(i, 1).toString();
                String sistem = ot.table.getValueAt(i, 2).toString();
                String baik = ot.table.getValueAt(i, 3).toString();
                String bad = ot.table.getValueAt(i, 4).toString();

                PreparedStatement stat2 = koneksi.Server.getConnection().prepareStatement(query2);
                stat2.setString(1, ot.nodok.getText());
                stat2.setString(2, id);
                stat2.setString(3, nama);
                stat2.setString(4, sistem);
                stat2.setString(5, baik);
                stat2.setString(6, bad);
                stat2.setString(7, status1);
                stat2.executeUpdate();
            }
            JOptionPane.showMessageDialog(null, "Stok Opname Tersimpan!", "Berhasil!", HEIGHT, sucess);
            fun_Clear(ot);
            fun_GenID(ot);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Menyimpan " + e.getMessage(), "Alert Message!", HEIGHT);
        }
    }

    @Override
    public void fun_Delete(OpnameTambah ot) {
        DefaultTableModel model = (DefaultTableModel) ot.table.getModel();
        int row = ot.table.getSelectedRow();
        if (row >= 0) {
            int ok = JOptionPane.showConfirmDialog(null, "Yakin Mau Hapus?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (ok == 0) {
                model.removeRow(row);

                int rows = model.getRowCount();
                ot.txt_item.setText("" + rows);
                ot.kode.setText("");
                ot.nama.setText("");
                ot.qty_baik.setText("");
                ot.qty_bad.setText("");
                ot.qty_sistem.setText("");

            } else {
                ot.kode.requestFocus();
            }
        }
    }

    @Override
    public void fun_Table(OpnameTambah ot) {
        try {
            int pilih = ot.table.getSelectedRow();
            ot.kode.setText(ot.tblModel.getValueAt(pilih, 0).toString());
            ot.nama.setText(ot.tblModel.getValueAt(pilih, 1).toString());
            ot.qty_sistem.setText(ot.tblModel.getValueAt(pilih, 2).toString());
            ot.qty_baik.setText(ot.tblModel.getValueAt(pilih, 3).toString());
            ot.qty_bad.setText(ot.tblModel.getValueAt(pilih, 4).toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error, Please Try Again!");
        }
    }

    @Override
    public void fun_GenID(OpnameTambah ot) throws SQLException {
        try {
            String query = "select MAX(RIGHT(nodok,5)) AS NO from opname order by nodok desc";
            ps = koneksi.Server.getConnection().prepareStatement(query);
            rs = ps.executeQuery(query);
            while (rs.next()) {
                if (rs.first() == false) {
                    ot.nodok.setText("SO10001");
                } else {
                    rs.last();
                    int auto_id = rs.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int Nomor = no.length();
                    //MENGATUR jumlah 0
                    for (int j = 0; j < 5 - Nomor; j++) {
                        no = "0" + no;
                    }
                    ot.nodok.setText("SO" + no);
                }
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();//penanganan masalah
        }
    }


    @Override
    public void fun_Tambah(OpnameTambah ot){
        ot.tblModel = (DefaultTableModel) ot.table.getModel();
        ot.tblModel.addRow(new Object[]{
            ot.kode.getText(),
            ot.nama.getText(),
            ot.qty_sistem.getText(),
            ot.qty_baik.getText(),
            ot.qty_bad.getText()
        });
        ot.table.setModel(ot.tblModel);
        int row = ot.tblModel.getRowCount();
        ot.txt_item.setText(""+row);
        ot.kode.setText("");
        ot.nama.setText("");
        ot.qty_sistem.setText("");
        ot.qty_baik.setText("");
        ot.qty_bad.setText("");
        
    }
    
    public void objectTable(OpnameTambah ot) {
        Object[] Baris = {"Kode Barang", "Nama Barang", "Qty Sistem", "Qty Baik", "Qty Bad"};
        ot.tblModel = new DefaultTableModel(null, Baris);
        ot.table.setModel(ot.tblModel);
        ot.table.getColumn("Qty Sistem").setMaxWidth(0);
        ot.table.getColumn("Qty Sistem").setMinWidth(0);
        ot.table.getColumn("Qty Sistem").setWidth(0);
    }

}
