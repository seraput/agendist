/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.TransPenerimaan;
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
import view.TransaksiPenerimaan;

/**
 *
 * @author Dell
 */
public class mTransPenerimaan implements TransPenerimaan {

    protected java.sql.Connection con;
    protected PreparedStatement ps;
    protected ResultSet rs;
    protected Statement st;
    Main main = new Main();
    public ImageIcon sucess = new ImageIcon(getClass().getResource("/asset/checked.png"));
    public ImageIcon invalid = new ImageIcon(getClass().getResource("/asset/cancel.png"));
    public ImageIcon warning = new ImageIcon(getClass().getResource("/asset/warning.png"));
    public ImageIcon trash = new ImageIcon(getClass().getResource("/asset/trash.png"));
    HashMap param = new HashMap();
    JasperReport jasreport;
    JasperPrint jasprint;
    JasperDesign jasdesign;

    @Override
    public void fun_Clear(TransaksiPenerimaan penerimaan) {
        penerimaan.nodok.setText("");
        penerimaan.cb_tujuan.setSelectedItem("Pilih");
        penerimaan.keterangan.setText("");
        penerimaan.txt_item.setText("0");
        penerimaan.txt_qty.setText("0");
        penerimaan.id.setText("");
        penerimaan.nama.setText("");
        penerimaan.qty.setText("");
        penerimaan.tanggal.setDate(null);
        ((DefaultTableModel) penerimaan.table.getModel()).setNumRows(0);
    }

    @Override
    public void fun_Enabled(TransaksiPenerimaan penerimaan) {
        penerimaan.tanggal.setEnabled(true);
        penerimaan.cb_tujuan.setEnabled(true);
        penerimaan.keterangan.setEnabled(true);
        penerimaan.bt_cari.setEnabled(true);
        penerimaan.qty.setEnabled(true);
        penerimaan.bt_tambah.setEnabled(true);
        penerimaan.bt_min.setEnabled(true);
        penerimaan.bt_simpan.setEnabled(true);
        penerimaan.bt_batal.setEnabled(true);
    }

    @Override
    public void fun_Disable(TransaksiPenerimaan penerimaan) {
        penerimaan.tanggal.setEnabled(false);
        penerimaan.cb_tujuan.setEnabled(false);
        penerimaan.keterangan.setEnabled(false);
        penerimaan.id.setEnabled(false);
        penerimaan.bt_cari.setEnabled(false);
        penerimaan.nama.setEnabled(false);
        penerimaan.qty.setEnabled(false);
        penerimaan.bt_tambah.setEnabled(false);
        penerimaan.bt_min.setEnabled(false);
        penerimaan.bt_simpan.setEnabled(false);
        penerimaan.bt_batal.setEnabled(false);
    }

    @Override
    public void fun_GetDist(TransaksiPenerimaan penerimaan) throws SQLException {
        try {
            String query = "SELECT * FROM distributor ORDER BY nama ASC";
            ps = koneksi.Server.getConnection().prepareStatement(query);
            rs = ps.executeQuery(query);
            penerimaan.cb_tujuan.removeAllItems();
            penerimaan.cb_tujuan.addItem("Pilih");
            while (rs.next()) {
                penerimaan.cb_tujuan.addItem(rs.getString("nama"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Bermasalah Get Sales: " + e.getMessage(), "Alert Message", HEIGHT, warning);
        }
    }

    @Override
    public void fun_Simpan(TransaksiPenerimaan penerimaan) throws SQLException {
        String query = "INSERT INTO `penerimaan` (`nodok`, `tanggal`, `jam`, `dist`, `keterangan`, `total_item`, `total_qty`, `created`, `status`) VALUES (?,?,?,?,?,?,?,?,?)";
        String query2 = "INSERT INTO `penerimaan_detail`(`nodok`, `id`, `nama`, `qty`) VALUES (?,?,?,?)";
        String tgl = ((JTextField) penerimaan.tanggal.getDateEditor().getUiComponent()).getText();
        String status = "Y";
        try {
            ps = koneksi.Server.getConnection().prepareStatement(query);
            ps.setString(1, penerimaan.nodok.getText().trim());
            ps.setString(2, tgl);
            ps.setString(3, main.txt_jam.getText().trim());
            ps.setString(4, penerimaan.cb_tujuan.getSelectedItem().toString());
            ps.setString(5, penerimaan.keterangan.getText());
            ps.setString(6, penerimaan.txt_item.getText());
            ps.setString(7, penerimaan.txt_qty.getText());
            ps.setString(8, main.txt_username.getText().trim());
            ps.setString(9, status);
            ps.executeUpdate();
            ps.close();
            int t = penerimaan.table.getRowCount();
            for (int i = 0; i < t; i++) {
                String id = penerimaan.table.getValueAt(i, 0).toString();
                String nama = penerimaan.table.getValueAt(i, 1).toString();
                String qty = penerimaan.table.getValueAt(i, 2).toString();

                PreparedStatement stat2 = koneksi.Server.getConnection().prepareStatement(query2);
                stat2.setString(1, penerimaan.nodok.getText());
                stat2.setString(2, id);
                stat2.setString(3, nama);
                stat2.setString(4, qty);
                stat2.executeUpdate();
                stat2.close();
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
                        param.put("id", penerimaan.nodok.getText());

                        JasperPrint jPrint = JasperFillManager.fillReport(NamaFile, param, connect);
                        JasperViewer.viewReport(jPrint, false);
                        fun_Clear(penerimaan);
                        fun_Disable(penerimaan);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Gagal Cetak" + e.getMessage(), "Alert Message!", HEIGHT);
                    }
                } else {
                    fun_Clear(penerimaan);
                    fun_Disable(penerimaan);
                }
            }

            fun_Clear(penerimaan);
            fun_Disable(penerimaan);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Menyimpan " + e.getMessage(), "Alert Message!", HEIGHT);
        }
    }

    @Override
    public void fun_GenID(TransaksiPenerimaan penerimaan) throws SQLException {
        try {
            String query = "select MAX(RIGHT(nodok,5)) AS NO from penerimaan order by nodok desc";
            ps = koneksi.Server.getConnection().prepareStatement(query);
            rs = ps.executeQuery(query);
            while (rs.next()) {
                if (rs.first() == false) {
                    penerimaan.nodok.setText("PN10001");
                } else {
                    rs.last();
                    int auto_id = rs.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int Nomor = no.length();
                    //MENGATUR jumlah 0
                    for (int j = 0; j < 5 - Nomor; j++) {
                        no = "0" + no;
                    }
                    penerimaan.nodok.setText("PN" + no);
                }
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();//penanganan masalah
        }
    }

    @Override
    public void fun_CekStok(TransaksiPenerimaan penerimaan) throws SQLException {
        String stokDB = "";
        int input = Integer.parseInt(penerimaan.qty.getText());
        try {
            String cekStock = "select qty from produk_baik where id='" + penerimaan.id.getText() + "'";
            String query3 = "INSERT INTO `mutasi_stok`(`notrans`, `tanggal`, `nama`, `id`, `stok`, `qty`, `sisa`, `keterangan`) VALUES (?,?,?,?,?,?,?,?)";
            Statement stat = koneksi.Server.getConnection().createStatement();
            ResultSet hasil = stat.executeQuery(cekStock);
            String ket = "Masuk";
            while (hasil.next()) {
                stokDB = hasil.getString("qty");
            }
            int qtyDB = Integer.parseInt(stokDB);
            int h = qtyDB + input;
            ps = koneksi.Server.getConnection().prepareStatement(query3);
            ps.setString(1, penerimaan.nodok.getText().trim());
            ps.setString(2, main.txt_tanggal.getText());
            ps.setString(3, penerimaan.cb_tujuan.getSelectedItem().toString());
            ps.setString(4, penerimaan.id.getText());
            ps.setString(5, stokDB);
            ps.setString(6, String.valueOf(input));
            ps.setString(7, String.valueOf(h));
            ps.setString(8, ket);
            ps.executeUpdate();
            fun_Tambah(penerimaan);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Bermasalah Cek Stok: " + e.getMessage(), "Alert Message", HEIGHT, warning);
        }
    }

    @Override
    public void fun_Delete(TransaksiPenerimaan penerimaan) throws SQLException {
        DefaultTableModel model = (DefaultTableModel) penerimaan.table.getModel();
        int row = penerimaan.table.getSelectedRow();
        if (row >= 0) {
            int ok = JOptionPane.showConfirmDialog(null, "Yakin Mau Hapus?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (ok == 0) {
                try {
                    int pilih = penerimaan.table.getSelectedRow();
                    String bar = "";
                    bar = (penerimaan.tblmodel.getValueAt(pilih, 0).toString());
                    con = koneksi.Server.getConnection();
                    String query = "DELETE FROM mutasi_stok WHERE notrans=? and id=?";
                    ps = con.prepareStatement(query);

                    ps.setString(1, penerimaan.nodok.getText());
                    ps.setString(2, bar);
                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Data Terhapus!", "Berhasil", HEIGHT, sucess);
                    ps.close();
                    model.removeRow(row);

                    int rows = model.getRowCount();
                    penerimaan.txt_item.setText("" + rows);
                    //row
                    int total = 0;
                    for (int i = 0; i < penerimaan.table.getRowCount(); i++) {
                        int amount = Integer.parseInt((String) penerimaan.table.getValueAt(i, 2));
                        total += amount;
                    }
                    penerimaan.txt_qty.setText("" + total);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Ada Sedikit Masalah! " + e.getMessage(), "Gagal", HEIGHT, invalid);
                }

            } else {
                penerimaan.id.requestFocus();
            }
        }
    }

    @Override
    public void fun_Tambah(TransaksiPenerimaan penerimaan) throws SQLException {
        penerimaan.tblmodel = (DefaultTableModel) penerimaan.table.getModel();
        penerimaan.tblmodel.addRow(new Object[]{
            penerimaan.id.getText(), penerimaan.nama.getText(), penerimaan.qty.getText()});
        penerimaan.table.setModel(penerimaan.tblmodel);
        int row = penerimaan.tblmodel.getRowCount();
        penerimaan.txt_item.setText("" + row);
        //row
        int total = 0;
        for (int i = 0; i < penerimaan.table.getRowCount(); i++) {
            int amount = Integer.parseInt((String) penerimaan.table.getValueAt(i, 2));
            total += amount;
        }
        penerimaan.txt_qty.setText("" + total);
        penerimaan.id.setText("");
        penerimaan.nama.setText("");
        penerimaan.qty.setText("");

    }

}
