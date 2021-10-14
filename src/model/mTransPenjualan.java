/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import static java.awt.image.ImageObserver.HEIGHT;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import main.Main;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.view.JasperViewer;
import popup.PopCariBarang;
import view.TransaksiPenjualan;
import controller.TransPenjualan;

/**
 *
 * @author Dell
 */
public class mTransPenjualan implements TransPenjualan {

    protected java.sql.Connection con;
    protected PreparedStatement ps;
    protected ResultSet rs;
    protected Statement st;
    Main main = new Main();
    public ImageIcon sucess = new ImageIcon(getClass().getResource("/asset/checked.png"));
    public ImageIcon invalid = new ImageIcon(getClass().getResource("/asset/cancel.png"));
    public ImageIcon warning = new ImageIcon(getClass().getResource("/asset/warning.png"));
    String tglToday = "";
    HashMap param = new HashMap();
    JasperReport jasreport;
    JasperPrint jasprint;
    JasperDesign jasdesign;
    public String netto = "";
    public String hsl = "";
    public String jml = "";
    public String hj = "";

    @Override
    public void fun_Clear(TransaksiPenjualan tp) {
        tp.tf_barcode.setText("");
        tp.tf_nama.setText("");
        tp.tf_harga.setText("");
        tp.tf_qty.setText("");
        tp.tf_nominal.setText("");
        tp.txt_totalharga.setText("0");
        tp.tf_nomor.setText("-");
        tp.txt_dibayarkan.setText("0");
        tp.txt_item.setText("0");
        tp.txt_kembali.setText("0");
        tp.txt_qty.setText("0");
        tp.cb_tipe.setSelectedItem("Pilih");
        tp.cb_kartu.setSelectedItem("-");
        tp.cb_sales.setSelectedItem("Pilih");
        tp.min.setEnabled(false);
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
        String hartot = tp.harga_total;
        String kembali = tp.kembalian;
        String nominal = tp.nominal_bayar;

        String query = "INSERT INTO `penjualan_header`(`id`, `sales`, `tanggal`, `jam`, `total_barang`, `total_qty`, `total_harga`, `pembayaran`, `bank`, `nomor`, `nominal`, `kembali`, `created`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String query2 = "INSERT INTO `penjualan_detail`(`id`, `barcode`, `nama`, `harga`, `qty`, `netto`) VALUES (?,?,?,?,?,?)";
        try {
            ps = koneksi.Server.getConnection().prepareStatement(query);
            ps.setString(1, tp.tf_idTrans.getText().trim());
            ps.setString(2, tp.cb_sales.getSelectedItem().toString());
            ps.setString(3, main.txt_tanggal.getText().trim());
            ps.setString(4, main.txt_jam.getText().trim());
            ps.setString(5, tp.txt_item.getText());
            ps.setString(6, tp.txt_qty.getText());
            ps.setString(7, hartot);
            ps.setString(8, tp.cb_tipe.getSelectedItem().toString());
            ps.setString(9, tp.cb_kartu.getSelectedItem().toString());
            ps.setString(10, tp.tf_nomor.getText());
            ps.setString(11, nominal);
            ps.setString(12, kembali);
            ps.setString(13, main.txt_username.getText().trim());
            ps.executeUpdate();

            int t = tp.table_penjualan.getRowCount();
            for (int i = 0; i < t; i++) {
                String barcode = tp.table_penjualan.getValueAt(i, 1).toString();
                String nama = tp.table_penjualan.getValueAt(i, 2).toString();
                String harga = tp.table_penjualan.getValueAt(i, 0).toString();
                String qty = tp.table_penjualan.getValueAt(i, 4).toString();
                String net = tp.table_penjualan.getValueAt(i, 6).toString();

                PreparedStatement stat2 = koneksi.Server.getConnection().prepareStatement(query2);
                stat2.setString(1, tp.tf_idTrans.getText());
                stat2.setString(2, barcode);
                stat2.setString(3, nama);
                stat2.setString(4, harga);
                stat2.setString(5, qty);
                stat2.setString(6, net);
                stat2.executeUpdate();
            }
            tp.min.setEnabled(false);
            int ok = JOptionPane.showConfirmDialog(null, "Cetak Struk Pembelian ?", "Transaksi Selesai", JOptionPane.YES_NO_OPTION, HEIGHT, sucess);
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
                    param.put("id", tp.tf_idTrans.getText());

                    JasperPrint jPrint = JasperFillManager.fillReport(NamaFile, param, connect);
                    JasperViewer.viewReport(jPrint, false);
                    fun_Clear(tp);
                    fun_GenID(tp);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Gagal Cetak" + e.getMessage(), "Alert Message!", HEIGHT);
                }
            } else {
                fun_Clear(tp);
                fun_GenID(tp);
            }
            fun_Clear(tp);
            fun_GenID(tp);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Bayar " + e.getMessage(), "Alert Message!", HEIGHT);
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
                
                fun_Total(tp);
                tp.min.setEnabled(false);
            }
            else{
                tp.tf_barcode.requestFocus();
                tp.min.setEnabled(false);
            }
        }
    }

    @Override
    public void fun_Table(TransaksiPenjualan tp) throws SQLException {
        try {
            int pilih = tp.table_penjualan.getSelectedRow();
            tp.tf_barcode.setText(tp.tblmodel.getValueAt(pilih, 1).toString());
            tp.tf_nama.setText(tp.tblmodel.getValueAt(pilih, 2).toString());
            tp.tf_harga.setText(tp.tblmodel.getValueAt(pilih, 3).toString());
            tp.tf_qty.setText(tp.tblmodel.getValueAt(pilih, 4).toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error, Please Try Again!");
        }
    }

    @Override
    public void fun_Tambah(TransaksiPenjualan tp) {
        fun_Hitung(tp);
        tp.tblmodel = (DefaultTableModel) tp.table_penjualan.getModel();
        tp.tblmodel.addRow(new Object[]{
            hj, tp.tf_barcode.getText(), tp.tf_nama.getText(), tp.tf_harga.getText(), tp.tf_qty.getText(), jml, hsl});
        tp.table_penjualan.setModel(tp.tblmodel);
        int row = tp.tblmodel.getRowCount();
        tp.txt_item.setText("" + row);
        tp.tf_barcode.setText("");
        tp.tf_nama.setText("");
        tp.tf_harga.setText("");
        tp.tf_qty.setText("");
        fun_Total(tp);
    }

    @Override
    public void fun_Ubah(TransaksiPenjualan tp) {
        DefaultTableModel model = (DefaultTableModel) tp.table_penjualan.getModel();
        int row = tp.table_penjualan.getSelectedRow();
        if (row >= 0) {
            int ok = JOptionPane.showConfirmDialog(null, "Apa Mau Merubah Data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (ok == 0) {
                
                try {
                    fun_Table(tp);
                } catch (SQLException ex) {
                    Logger.getLogger(mTransPenjualan.class.getName()).log(Level.SEVERE, null, ex);
                }
                model.removeRow(row);

                int rows = model.getRowCount();
                tp.txt_item.setText("" + rows);

                int total = 0;
                for (int i = 0; i < tp.table_penjualan.getRowCount(); i++) {
                    int amount = Integer.parseInt((String) tp.table_penjualan.getValueAt(i, 4));
                    total += amount;
                }
                tp.txt_qty.setText("" + total);

                int harga = 0;
                int jumlah = 0;
                for (int i = 0; i < tp.table_penjualan.getRowCount(); i++) {
                    int amount1 = Integer.parseInt((String) tp.table_penjualan.getValueAt(i, 3));
                    harga += amount1;
                    int sum = harga * total;
                    jumlah += sum;
                    tp.tf_qty.requestFocus();
                }
                tp.txt_totalharga.setText("" + jumlah);
            } else {
                tp.tf_barcode.setText("");
                tp.tf_nama.setText("");
                tp.tf_harga.setText("");
                tp.tf_qty.setText("");
                tp.tf_barcode.requestFocus();
                tp.min.setEnabled(true);
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
            JOptionPane.showMessageDialog(null, "Bermasalah Gen ID: " + e.getMessage(), "Alert Message", HEIGHT, warning);
        }
    }

    @Override
    public void fun_GetSales(TransaksiPenjualan tp) throws SQLException {
        try {
            String query = "SELECT * FROM user WHERE jabatan='Sales' ORDER BY username ASC";
            ps = koneksi.Server.getConnection().prepareStatement(query);
            rs = ps.executeQuery(query);
            tp.cb_sales.removeAllItems();
            tp.cb_sales.addItem("Pilih");
            while (rs.next()) {
                tp.cb_sales.addItem(rs.getString("username"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Bermasalah Get Sales: " + e.getMessage(), "Alert Message", HEIGHT, warning);
        }
    }

    public void objectTable(TransaksiPenjualan tp) {
        Object[] Baris = {"Hj", "ID", "Nama", "Harga", "Qty", "Netto", "Jml"};
        tp.tblmodel = new DefaultTableModel(null, Baris);
        tp.table_penjualan.setModel(tp.tblmodel);
        tp.table_penjualan.getColumn("Jml").setMaxWidth(0);
        tp.table_penjualan.getColumn("Jml").setMinWidth(0);
        tp.table_penjualan.getColumn("Jml").setWidth(0);
        tp.table_penjualan.getColumn("Hj").setMaxWidth(0);
        tp.table_penjualan.getColumn("Hj").setMinWidth(0);
        tp.table_penjualan.getColumn("Hj").setWidth(0);
        tp.table_penjualan.getColumn("Qty").setMaxWidth(50);
        tp.table_penjualan.getColumn("Qty").setMinWidth(5);
        tp.table_penjualan.getColumn("Qty").setWidth(30);
        tp.table_penjualan.getColumn("Harga").setMaxWidth(100);
        tp.table_penjualan.getColumn("Harga").setMinWidth(100);
        tp.table_penjualan.getColumn("Harga").setWidth(100);
        tp.table_penjualan.getColumn("Netto").setMaxWidth(150);
        tp.table_penjualan.getColumn("Netto").setMinWidth(150);
        tp.table_penjualan.getColumn("Netto").setWidth(150);
        tp.table_penjualan.getColumn("ID").setMaxWidth(150);
        tp.table_penjualan.getColumn("ID").setMinWidth(150);
        tp.table_penjualan.getColumn("ID").setWidth(150);
    }

    @Override
    public void fun_GetKredit(TransaksiPenjualan tp) throws SQLException {
        try {
            String query = "SELECT * FROM bank WHERE pembayaran='" + tp.cb_tipe.getSelectedItem().toString() + "' ORDER BY namabank ASC";
            ps = koneksi.Server.getConnection().prepareStatement(query);
            rs = ps.executeQuery(query);
            tp.cb_kartu.removeAllItems();
            tp.cb_kartu.addItem("Pilih");
            while (rs.next()) {
                tp.cb_kartu.addItem(rs.getString("namabank"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Bermasalah GetBank: " + e.getMessage(), "Alert Message", HEIGHT, warning);
        }
    }

    @Override
    public void fun_Hitung(TransaksiPenjualan tp) {
        //netto
        int qty = Integer.parseInt(tp.tf_qty.getText());
        String h = tp.tf_harga.getText().replaceAll("\\,", "");
        int harjual = Integer.parseInt(h);
        int net = harjual * qty;
        hsl = String.valueOf(net);
        String har1 = String.valueOf(net).replaceAll("\\,", "");
        double hargad1 = Double.parseDouble(har1);
        DecimalFormat df1 = new DecimalFormat("#,###,##0");

        if (hargad1 > 999) {
            netto = df1.format(hargad1);
        }
        hj = String.valueOf(harjual);
        jml = String.valueOf(netto);
    }

    @Override
    public void fun_Total(TransaksiPenjualan tp) {
        //row
        int total = 0;
        for (int i = 0; i < tp.table_penjualan.getRowCount(); i++) {
            int amount = Integer.parseInt((String) tp.table_penjualan.getValueAt(i, 4));
            total += amount;
        }
        tp.txt_qty.setText("" + total);
        
        //total
        int harga = 0;
        for (int i = 0; i < tp.table_penjualan.getRowCount(); i++) {
            int amount = Integer.parseInt((String) tp.table_penjualan.getValueAt(i, 6));

            harga += amount;

        }
        String har = String.valueOf(harga).replaceAll("\\,", "");
        double hargad = Double.parseDouble(har);
        DecimalFormat df = new DecimalFormat("#,###,##0");

        if (hargad > 999) {
            tp.txt_totalharga.setText(df.format(hargad));
        }

        tp.tf_barcode.requestFocus();
    }

    @Override
    public void fun_CekStok(TransaksiPenjualan tp) throws SQLException {
        String stokDB = "";
        try {
            String cekStock = "select stok from product where id='" + tp.tf_barcode.getText() + "'";
            Statement stat = koneksi.Server.getConnection().createStatement();
            ResultSet hasil = stat.executeQuery(cekStock);
            while (hasil.next()) {
                stokDB = hasil.getString("stok");
            }
            int qtyDB = Integer.parseInt(stokDB);
            int input = Integer.parseInt(tp.tf_qty.getText());
            if (qtyDB < input) {
                JOptionPane.showMessageDialog(null, "Stok Kurang Dari Permintaan!", "Gagal", HEIGHT, invalid);
                tp.tf_qty.setText("");
                tp.tf_qty.requestFocus();
            } else {
                fun_Tambah(tp);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Bermasalah Cek Stok: " + e.getMessage(), "Alert Message", HEIGHT, warning);
        }
    }

    @Override
    public void fun_AlertStok(TransaksiPenjualan tp) throws SQLException {
        String stokDB = "";
        try {
            String cekStock = "select stok from product where id='" + tp.tf_barcode.getText() + "'";
            Statement stat = koneksi.Server.getConnection().createStatement();
            ResultSet hasil = stat.executeQuery(cekStock);
            while (hasil.next()) {
                stokDB = hasil.getString("stok");
            }
            int qtyDB = Integer.parseInt(stokDB);
            if (qtyDB < 5) {
                JOptionPane.showMessageDialog(null, "Stok Barang Menipis Kurang dari 5", "Alert Message", HEIGHT, warning);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Bermasalah Alert Stok: " + e.getMessage(), "Alert Message", HEIGHT, warning);
        }
    }

    @Override
    public void fun_bayar(TransaksiPenjualan tp) {
        String har = tp.tf_nominal.getText().replaceAll("\\,", "");
        double harga = Double.parseDouble(har);
        DecimalFormat df1 = new DecimalFormat("#,###,##0");
        if (harga > 999) {
            tp.tf_nominal.setText(df1.format(harga));
        } else {
            tp.tf_nominal.setText(har);
        }
        String S_angka_normal = tp.tf_nominal.getText().replaceAll("\\,", "");
        double D_angka_Normal = Double.parseDouble(S_angka_normal);
        DecimalFormat DF = new DecimalFormat("#,###,##0");
        tp.txt_dibayarkan.setText(DF.format(D_angka_Normal));

        int t = Integer.parseInt(tp.txt_totalharga.getText().replaceAll("\\,", ""));
        int b = Integer.parseInt(tp.txt_dibayarkan.getText().replaceAll("\\,", ""));

        int tot = b - t;
        tp.txt_kembali.setText(String.valueOf(tot));
    }

}
