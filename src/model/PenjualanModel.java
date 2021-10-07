/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.PenjualanController;
import java.awt.event.KeyEvent;
import static java.awt.image.ImageObserver.HEIGHT;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import main.Main;
import popup.PopCariBarang;
import view.TransaksiPenjualan;

/**
 *
 * @author Dell
 */
public class PenjualanModel implements PenjualanController {

    protected java.sql.Connection con;
    protected PreparedStatement ps;
    protected ResultSet rs;
    protected Statement st;
    Main main = new Main();
    ImageIcon sucess = new ImageIcon(getClass().getResource("/asset/checked.png"));
    ImageIcon invalid = new ImageIcon(getClass().getResource("/asset/cancel.png"));
    ImageIcon warning = new ImageIcon(getClass().getResource("/asset/warning.png"));
    String tglToday = "";
    
    
    @Override
    public void fun_Clear(TransaksiPenjualan tp) {
        tp.tf_barcode.setText("");
        tp.tf_nama.setText("");
        tp.tf_harga.setText("");
        tp.tf_qty.setText("");
        tp.tf_nominal.setText("");
        tp.tf_total.setText("0");
        tp.txt_dibayarkan.setText("0");
        tp.txt_item.setText("0");
        tp.txt_kembali.setText("0");
        tp.txt_qty.setText("0");
        tp.cb_tipe.setSelectedItem("Pilih");
        tp.cb_kartu.setSelectedItem("-");
        ((DefaultTableModel) tp.table_penjualan.getModel()).setNumRows(0);
    }

    @Override
    public void fun_Cari(TransaksiPenjualan tp) throws SQLException {
        try {
            String str = tp.tf_barcode.getText();
            con = koneksi.Server.getConnection();
            ps = con.prepareStatement("select * from product where id=?");
            ps.setString(1, str);
            //Excuting Query  
            rs = ps.executeQuery();
            if (rs.next()) {
                String s = rs.getString(2);
                String s1 = rs.getString(6);
                //Sets Records in TextFields.  
                tp.tf_nama.setText(s);
                tp.tf_harga.setText(s1);
                tp.tf_qty.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "not Found");
            }
            //Create Exception Handler  
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void fun_Simpan(TransaksiPenjualan tp) throws SQLException {
        String query = "INSERT INTO `penjualan_header`(`id`, `kasir`, `tanggal`, `jam`, `total_barang`, `total_qty`, `total_harga`, `pembayaran`, `bank`, `nomor`, `nominal`, `kembali`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        String query2 = "INSERT INTO `penjualan_detail`(`id`, `barcode`, `nama`, `harga`, `qty`, `netto`) VALUES (?,?,?,?,?,?)";
        try {
            ps = koneksi.Server.getConnection().prepareStatement(query);
            ps.setString(1, tp.tf_idTrans.getText().trim());
            ps.setString(2, main.txt_username.getText().trim());
            ps.setString(3, main.txt_tanggal.getText().trim());
            ps.setString(4, main.txt_jam.getText().trim());
            ps.setString(5, tp.txt_item.getText());
            ps.setString(6, tp.txt_qty.getText());
            ps.setString(7, tp.tf_total.getText());
            ps.setString(8, tp.cb_tipe.getSelectedItem().toString());
            ps.setString(9, tp.cb_kartu.getSelectedItem().toString());
            ps.setString(10, tp.tf_nomor.getText());
            ps.setString(11, tp.tf_nominal.getText());
            ps.setString(12, tp.txt_kembali.getText());
            ps.executeUpdate();

            int t = tp.table_penjualan.getRowCount();
            for (int i = 0; i < t; i++) {
                String barcode = tp.table_penjualan.getValueAt(i, 0).toString();
                String nama = tp.table_penjualan.getValueAt(i, 1).toString();
                String harga = tp.table_penjualan.getValueAt(i, 2).toString();
                String qty = tp.table_penjualan.getValueAt(i, 3).toString();
                String netto = tp.table_penjualan.getValueAt(i, 4).toString();

                PreparedStatement stat2 = koneksi.Server.getConnection().prepareStatement(query2);
                stat2.setString(1, tp.tf_idTrans.getText());
                stat2.setString(2, barcode);
                stat2.setString(3, nama);
                stat2.setString(4, harga);
                stat2.setString(5, qty);
                stat2.setString(6, netto);
                stat2.executeUpdate();
            }
            JOptionPane.showMessageDialog(null, "Transaksi Tersimpan", "Berhasil!", HEIGHT, sucess);
            fun_Clear(tp);
            fun_GenID(tp);
            main.TglSekarang();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal" + e.getMessage(), "Alert Message!", HEIGHT);
        }
    }
    

    @Override
    public void fun_Delete(TransaksiPenjualan tp) {
        DefaultTableModel model = (DefaultTableModel) tp.table_penjualan.getModel();
        int row = tp.table_penjualan.getSelectedRow();
        if (row >= 0) {
            int ok = JOptionPane.showConfirmDialog(null, "Yakin Mau Hapus?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (ok == 0) {
                model.removeRow(row);

                int rows = model.getRowCount();
                tp.txt_item.setText("" + rows);

                int total = 0;
                for (int i = 0; i < tp.table_penjualan.getRowCount(); i++) {
                    int amount = Integer.parseInt((String) tp.table_penjualan.getValueAt(i, 3));
                    total += amount;
                }
                tp.txt_qty.setText("" + total);
                
                int harga = 0;
                int jumlah = 0;
                for (int i = 0; i < tp.table_penjualan.getRowCount(); i++) {
                int amount1 = Integer.parseInt((String) tp.table_penjualan.getValueAt(i, 2));
                harga += amount1;
                int sum = harga * total;
                jumlah += sum;
            }
            tp.tf_total.setText("" + jumlah);
                }
        }
    }

    @Override
    public void fun_Table(TransaksiPenjualan tp) throws SQLException{
        try{
            int pilih = tp.table_penjualan.getSelectedRow();
            tp.tf_barcode.setText(tp.tblmodel.getValueAt(pilih,0).toString());
            tp.tf_nama.setText(tp.tblmodel.getValueAt(pilih,1).toString());
            tp.tf_harga.setText(tp.tblmodel.getValueAt(pilih,2).toString());
            tp.tf_qty.setText(tp.tblmodel.getValueAt(pilih,3).toString());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error, Please Try Again!");
        }
    }

    @Override
    public void fun_Tampil(TransaksiPenjualan tp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void fun_Tambah(TransaksiPenjualan tp) {
        //cek netto
        int netto = 0;
        int qty = Integer.parseInt(tp.tf_qty.getText());
        int harjual = Integer.parseInt(tp.tf_harga.getText());
        int net = harjual * qty;
        netto += net;
        String jml = String.valueOf(netto);
        
        tp.tblmodel = (DefaultTableModel) tp.table_penjualan.getModel();
        tp.tblmodel.addRow(new Object[]{
            tp.tf_barcode.getText(), tp.tf_nama.getText(), tp.tf_harga.getText(), tp.tf_qty.getText(), jml});
        tp.table_penjualan.setModel(tp.tblmodel);
        int row = tp.tblmodel.getRowCount();
        tp.txt_item.setText("" + row);
        tp.tf_barcode.setText("");
        tp.tf_nama.setText("");
        tp.tf_harga.setText("");
        tp.tf_qty.setText("");
        //cek row
        int total = 0;
        for (int i = 0; i < tp.table_penjualan.getRowCount(); i++) {
            int amount = Integer.parseInt((String) tp.table_penjualan.getValueAt(i, 3));
            total += amount;
        }
        tp.txt_qty.setText("" + total);
        
        //cek total
        int harga = 0;
        for (int i = 0; i < tp.table_penjualan.getRowCount(); i++) {
            int amount1 = Integer.parseInt((String) tp.table_penjualan.getValueAt(i, 4));
            harga += amount1;
        }
        main.TglSekarang();
        tp.tf_total.setText(String.valueOf(harga));
    }

    @Override
    public void fun_Ubah(TransaksiPenjualan tp) {
        DefaultTableModel model = (DefaultTableModel) tp.table_penjualan.getModel();
        int row = tp.table_penjualan.getSelectedRow();
        if (row >= 0) {
            int ok = JOptionPane.showConfirmDialog(null, "Apa Mau Merubah Data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (ok == 0) {
                model.removeRow(row);

                int rows = model.getRowCount();
                tp.txt_item.setText("" + rows);

                int total = 0;
                for (int i = 0; i < tp.table_penjualan.getRowCount(); i++) {
                    int amount = Integer.parseInt((String) tp.table_penjualan.getValueAt(i, 3));
                    total += amount;
                }
                tp.txt_qty.setText("" + total);
                
                int harga = 0;
                int jumlah = 0;
                for (int i = 0; i < tp.table_penjualan.getRowCount(); i++) {
                int amount1 = Integer.parseInt((String) tp.table_penjualan.getValueAt(i, 2));
                harga += amount1;
                int sum = harga * total;
                jumlah += sum;
                tp.tf_qty.requestFocus();
            }
            tp.tf_total.setText("" + jumlah);
                }else{
            tp.tf_barcode.setText("");
            tp.tf_nama.setText("");
            tp.tf_harga.setText("");
            tp.tf_qty.setText("");
            tp.tf_barcode.requestFocus();
        }
        }
        
    }
    

    @Override
    public void fun_GenID(TransaksiPenjualan tp) throws SQLException {
        try {
            String query = "select MAX(RIGHT(id,4)) AS NO  from penjualan_header order by id desc";
            ps = koneksi.Server.getConnection().prepareStatement(query);
            rs = ps.executeQuery(query);
            while (rs.next()) {
                if (rs.first() == false) {
                    tp.tf_idTrans.setText("PJ100001");
                } else {
                    rs.last();
                    int auto_id = rs.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int Nomor = no.length();
                    //MENGATUR jumlah 0
                    for (int j = 0; j < 8 - Nomor; j++) {
                        no = "0" + no;
                    }
                    tp.tf_idTrans.setText("PJ" + no);
                }
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();//penanganan masalah
        }
    }

}
