/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.rLaporanStok;
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
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.view.JasperViewer;
import view.ReportStok;

/**
 *
 * @author Dell
 */
public class mReportStok implements rLaporanStok {

    protected java.sql.Connection con;
    protected PreparedStatement ps;
    protected ResultSet rss;
    protected Statement st;

    public ImageIcon sucess = new ImageIcon(getClass().getResource("/asset/checked.png"));
    public ImageIcon invalid = new ImageIcon(getClass().getResource("/asset/cancel.png"));
    public ImageIcon warning = new ImageIcon(getClass().getResource("/asset/warning.png"));

    HashMap param = new HashMap();
    JasperReport jasreport;
    JasperPrint jasprint;
    JasperDesign jasdesign;

    @Override
    public void fun_Clear(ReportStok rs) {
        rs.startDate.setCalendar(null);
        rs.endDate.setCalendar(null);
        rs.cb_produk.setSelectedItem("Pilih");
        rs.submit.setEnabled(false);
        ((DefaultTableModel) rs.tb_stok.getModel()).setNumRows(0);
    }

    @Override
    public void fun_ObjectTable(ReportStok rs) {
        Object[] Baris = {"Tanggal", "Pelanggan/Pengirim", "Nama Barang", "Stok Awal", "Stok Keluar", "Sisa Stok", "Keterangan"};
        rs.dtm = new DefaultTableModel(null, Baris);
        rs.tb_stok.setModel(rs.dtm);
    }
    
    
    @Override
    public void fun_ObjectTableStok(ReportStok rs) {
        Object[] Baris = {"Kode", "Nama Barang", "Jenis", "Satuan", "Harga Beli", "Harga Jual", "Qty"};
        rs.dtm = new DefaultTableModel(null, Baris);
        rs.tb_stok.setModel(rs.dtm);
    }

    @Override
    public void fun_TarikAll(ReportStok rs) throws SQLException {
        fun_ObjectTable(rs);
        String startDate = ((JTextField) rs.startDate.getDateEditor().getUiComponent()).getText();
        String endDate = ((JTextField) rs.endDate.getDateEditor().getUiComponent()).getText();
        try {
            con = koneksi.Server.getConnection();
            st = con.createStatement();
            String sql = "SELECT mutasi_stok.tanggal, mutasi_stok.nama, product.nama, mutasi_stok.stok, mutasi_stok.qty, mutasi_stok.sisa, mutasi_stok.keterangan FROM mutasi_stok JOIN product ON mutasi_stok.`id`=product.id WHERE mutasi_stok.tanggal BETWEEN '"+startDate+"' AND '"+endDate+"' ORDER BY mutasi_stok.`tanggal` ASC;";
            rss = st.executeQuery(sql);
            while (rss.next()) {
                rs.dtm = (DefaultTableModel) rs.tb_stok.getModel();
                rs.dtm.addRow(new Object[]{
                    rss.getString(1),
                    rss.getString(2),
                    rss.getString(3),
                    rss.getString(4),
                    rss.getString(5),
                    rss.getString(6),
                    rss.getString(7)
                });
            }
            rs.tb_stok.setModel(rs.dtm);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void fun_TarikProduk(ReportStok rs) throws SQLException {
        fun_ObjectTable(rs);
        String startDate = ((JTextField) rs.startDate.getDateEditor().getUiComponent()).getText();
        String endDate = ((JTextField) rs.endDate.getDateEditor().getUiComponent()).getText();
        String produk = rs.cb_produk.getSelectedItem().toString();
        try {
            con = koneksi.Server.getConnection();
            st = con.createStatement();
            String sql = "SELECT mutasi_stok.tanggal, mutasi_stok.nama, product.nama, mutasi_stok.stok, mutasi_stok.qty, mutasi_stok.sisa, mutasi_stok.keterangan FROM mutasi_stok JOIN product ON mutasi_stok.`id`=product.id WHERE product.nama='"+produk+"' AND mutasi_stok.tanggal BETWEEN '"+startDate+"' AND '"+endDate+"' ORDER BY mutasi_stok.`tanggal` ASC;";
            rss = st.executeQuery(sql);
            while (rss.next()) {
                rs.dtm = (DefaultTableModel) rs.tb_stok.getModel();
                rs.dtm.addRow(new Object[]{
                    rss.getString(1),
                    rss.getString(2),
                    rss.getString(3),
                    rss.getString(4),
                    rss.getString(5),
                    rss.getString(6),
                    rss.getString(7)
                });
            }
            rs.tb_stok.setModel(rs.dtm);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void fun_TarikStok(ReportStok rs) throws SQLException {
        fun_ObjectTableStok(rs);
        String startDate = ((JTextField) rs.startDate.getDateEditor().getUiComponent()).getText();
        String endDate = ((JTextField) rs.endDate.getDateEditor().getUiComponent()).getText();
        String sales = rs.cb_produk.getSelectedItem().toString();
        try {
            con = koneksi.Server.getConnection();
            st = con.createStatement();
            String sql = "SELECT product.id, product.nama, product.jenis, product.satuan, product.harga_beli, product.harga_jual, produk_baik.qty FROM product JOIN produk_baik ON product.id=produk_baik.id ORDER BY product.nama ASC;";
            rss = st.executeQuery(sql);
            while (rss.next()) {
                rs.dtm = (DefaultTableModel) rs.tb_stok.getModel();
                rs.dtm.addRow(new Object[]{
                    rss.getString(1),
                    rss.getString(2),
                    rss.getString(3),
                    rss.getString(4),
                    rss.getString(5),
                    rss.getString(6),
                    rss.getString(7)
                });
            }
            rs.tb_stok.setModel(rs.dtm);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void fun_GetProduk(ReportStok rs) throws SQLException {
        try {
            String query = "SELECT * FROM product WHERE status='Y' ORDER BY nama ASC";
            ps = koneksi.Server.getConnection().prepareStatement(query);
            rss = ps.executeQuery(query);
            rs.cb_produk.removeAllItems();
            rs.cb_produk.addItem("Semua");
            while (rss.next()) {
                rs.cb_produk.addItem(rss.getString("nama"));
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void fun_cetakStok(ReportStok rs) throws SQLException {
        int ok = JOptionPane.showConfirmDialog(null, "Jangan Berpindah Halaman Selama Proses Cetak!", "Peringatan!", JOptionPane.YES_NO_OPTION, HEIGHT, warning);
        if (ok == 0) {
            String startDate = ((JTextField) rs.startDate.getDateEditor().getUiComponent()).getText();
            String endDate = ((JTextField) rs.endDate.getDateEditor().getUiComponent()).getText();
            try {
                String NamaFile = "src/report/datastok.jasper";
                String URL = "jdbc:mysql://localhost:3306/";
                String DB = "agendist";
                String driver = "com.mysql.jdbc.Driver";
                String user = "root";
                String pass = "";
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection connect = DriverManager.getConnection(URL + DB, user, pass);
                HashMap param = new HashMap();

                JasperPrint jPrint = JasperFillManager.fillReport(NamaFile, param, connect);
                JasperViewer.viewReport(jPrint, false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Gagal Cetak" + e.getMessage(), "Alert Message!", HEIGHT);
            }
        }
    }

    @Override
    public void fun_cetakProduk(ReportStok rs) throws SQLException {
        int ok = JOptionPane.showConfirmDialog(null, "Jangan Berpindah Halaman Selama Proses Cetak!", "Peringatan!", JOptionPane.YES_NO_OPTION, HEIGHT, warning);
        if (ok == 0) {
            String startDate = ((JTextField) rs.startDate.getDateEditor().getUiComponent()).getText();
            String endDate = ((JTextField) rs.endDate.getDateEditor().getUiComponent()).getText();
            try {
                String NamaFile = "src/report/penjualan.jasper";
                String URL = "jdbc:mysql://localhost:3306/";
                String DB = "agendist";
                String driver = "com.mysql.jdbc.Driver";
                String user = "root";
                String pass = "";
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection connect = DriverManager.getConnection(URL + DB, user, pass);
                HashMap param = new HashMap();
                param.put("produk", rs.cb_produk.getSelectedItem().toString());
                param.put("tglstart", startDate);
                param.put("tglend", endDate);

                JasperPrint jPrint = JasperFillManager.fillReport(NamaFile, param, connect);
                JasperViewer.viewReport(jPrint, false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Gagal Cetak" + e.getMessage(), "Alert Message!", HEIGHT);
            }
        }
    }

    @Override
    public void fun_cetakAll(ReportStok rs) throws SQLException {
        int ok = JOptionPane.showConfirmDialog(null, "Jangan Berpindah Halaman Selama Proses Cetak!", "Peringatan!", JOptionPane.YES_NO_OPTION, HEIGHT, warning);
        if (ok == 0) {
            String startDate = ((JTextField) rs.startDate.getDateEditor().getUiComponent()).getText();
            String endDate = ((JTextField) rs.endDate.getDateEditor().getUiComponent()).getText();
            try {
                String NamaFile = "src/report/mutasiall.jasper";
                String URL = "jdbc:mysql://localhost:3306/";
                String DB = "agendist";
                String driver = "com.mysql.jdbc.Driver";
                String user = "root";
                String pass = "";
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection connect = DriverManager.getConnection(URL + DB, user, pass);
                HashMap param = new HashMap();
                param.put("tglstart", startDate);
                param.put("tglend", endDate);

                JasperPrint jPrint = JasperFillManager.fillReport(NamaFile, param, connect);
                JasperViewer.viewReport(jPrint, false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Gagal Cetak" + e.getMessage(), "Alert Message!", HEIGHT);
            }
        }
    }

}
