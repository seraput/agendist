/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
import view.ReportPenjualan;
import java.text.DecimalFormat;
import javax.swing.table.DefaultTableCellRenderer;
import controller.rLaporanPenjualan;

/**
 *
 * @author Dell
 */
public class mReportPenjualan implements rLaporanPenjualan {

    protected java.sql.Connection con;
    protected PreparedStatement ps;
    protected ResultSet rs;
    protected Statement st;

    public ImageIcon sucess = new ImageIcon(getClass().getResource("/asset/checked.png"));
    public ImageIcon invalid = new ImageIcon(getClass().getResource("/asset/cancel.png"));
    public ImageIcon warning = new ImageIcon(getClass().getResource("/asset/warning.png"));

    HashMap param = new HashMap();
    JasperReport jasreport;
    JasperPrint jasprint;
    JasperDesign jasdesign;
    String n = "";

    @Override
    public void fun_Clear(view.ReportPenjualan rp) {
        rp.start_date.setCalendar(null);
        rp.end_date.setCalendar(null);
        rp.cb_parameter.setSelectedItem("Pilih");
        rp.cb_sales.setSelectedItem("Pilih");
        rp.submit.setEnabled(false);
        rp.dua.setText("0");
        rp.tiga.setText("0");
        rp.empat.setText("0");
        rp.enam.setText("0");
        rp.tujuh.setText("0");
        rp.lapan.setText("0");
        rp.t_netto.setText("0");
        ((DefaultTableModel) rp.tb_report.getModel()).setNumRows(0);
    }

    @Override
    public void fun_TarikBySales(view.ReportPenjualan rp) throws SQLException {
        fun_ObjectPersales(rp);
        atur_tableSales(rp);
        String sales = rp.cb_sales.getSelectedItem().toString();
        String startDate = ((JTextField) rp.start_date.getDateEditor().getUiComponent()).getText();
        String endDate = ((JTextField) rp.end_date.getDateEditor().getUiComponent()).getText();
        try {
            con = koneksi.Server.getConnection();
            st = con.createStatement();
            String sql
                    = "SELECT penjualan_header.`sales` AS 'Salesman', product.`nama` AS 'Nama Produk', penjualan_detail.`qty` AS 'Total Qty',\n"
                    + "FLOOR(penjualan_detail.`netto`) AS 'netto',FLOOR(penjualan_detail.`netto`*0.8) AS '80',\n"
                    + "FLOOR(penjualan_detail.`netto`*0.2) AS '20',FLOOR(penjualan_detail.`netto`*0.7) AS '70',\n"
                    + "FLOOR(penjualan_detail.`netto`*0.3) AS '30',FLOOR(penjualan_detail.`netto`*0.6) AS '60',\n"
                    + "FLOOR(penjualan_detail.`netto`*0.4) AS '40' \n"
                    + "FROM penjualan_header JOIN penjualan_detail ON penjualan_header.`id`=penjualan_detail.`id` JOIN product ON penjualan_detail.`barcode`= product.`id` WHERE penjualan_header.`sales`='" + sales + "' AND penjualan_header.`tanggal` BETWEEN '" + startDate + "' AND '" + endDate + "' ORDER BY penjualan_header.`sales` ASC;";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                rp.dtm.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9),
                    rs.getString(10)
                });
            }
            rp.tb_report.setModel(rp.dtm);
            cekTotal(rp);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void fun_TarikDetail(view.ReportPenjualan rp) throws SQLException {
        fun_ObjectTableDetail(rp);
        String startDate = ((JTextField) rp.start_date.getDateEditor().getUiComponent()).getText();
        String endDate = ((JTextField) rp.end_date.getDateEditor().getUiComponent()).getText();
        String sales = rp.cb_sales.getSelectedItem().toString();
        try {
            con = koneksi.Server.getConnection();
            st = con.createStatement();
            String sql
                    = "SELECT penjualan_header.`tanggal` AS 'Tanggal', penjualan_header.`sales` AS 'Salesman', penjualan_header.`pembeli` AS 'Pembeli', product.`nama` AS 'Nama Produk', penjualan_detail.`qty` AS 'Total Qty', \n"
                    + "FLOOR(penjualan_detail.`netto`) AS 'netto',FLOOR(penjualan_detail.`netto`*0.8) AS '80',\n"
                    + "FLOOR(penjualan_detail.`netto`*0.2) AS '20',FLOOR(penjualan_detail.`netto`*0.7) AS '70',\n"
                    + "FLOOR(penjualan_detail.`netto`*0.3) AS '30',FLOOR(penjualan_detail.`netto`*0.6) AS '60',\n"
                    + "FLOOR(penjualan_detail.`netto`*0.4) AS '40' \n"
                    + "FROM penjualan_header JOIN penjualan_detail ON penjualan_header.`id`=penjualan_detail.`id` \n"
                    + "JOIN product ON penjualan_detail.`barcode`= product.`id` \n"
                    + "WHERE penjualan_header.`sales`='"+sales+"' AND penjualan_header.`tanggal` BETWEEN '"+startDate+"' AND '"+endDate+"';";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                rp.dtm = (DefaultTableModel) rp.tb_report.getModel();
                rp.dtm.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9),
                    rs.getString(10),
                    rs.getString(11),
                    rs.getString(12)
                });
            }
            rp.tb_report.setModel(rp.dtm);
            cekTotalDtl(rp);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void cekTotal(view.ReportPenjualan rp) {
        //total
        int harga = 0;
        int harga8 = 0;
        int harga2 = 0;
        int harga7 = 0;
        int harga3 = 0;
        int harga6 = 0;
        int harga4 = 0;
        for (int i = 0; i < rp.tb_report.getRowCount(); i++) {
            int amount = Integer.parseInt((String) rp.tb_report.getValueAt(i, 3));
            int amount8 = Integer.parseInt((String) rp.tb_report.getValueAt(i, 4));
            int amount2 = Integer.parseInt((String) rp.tb_report.getValueAt(i, 5));
            int amount7 = Integer.parseInt((String) rp.tb_report.getValueAt(i, 6));
            int amount3 = Integer.parseInt((String) rp.tb_report.getValueAt(i, 7));
            int amount6 = Integer.parseInt((String) rp.tb_report.getValueAt(i, 8));
            int amount4 = Integer.parseInt((String) rp.tb_report.getValueAt(i, 9));

            harga += amount;
            harga8 += amount8;
            harga2 += amount2;
            harga7 += amount7;
            harga3 += amount3;
            harga6 += amount6;
            harga4 += amount4;

        }
        String har = String.valueOf(harga).replaceAll("\\,", "");
        double hargad = Double.parseDouble(har);
        DecimalFormat df = new DecimalFormat("#,###,##0");

        String har8 = String.valueOf(harga8).replaceAll("\\,", "");
        double hargad8 = Double.parseDouble(har8);
        DecimalFormat df8 = new DecimalFormat("#,###,##0");

        String har2 = String.valueOf(harga2).replaceAll("\\,", "");
        double hargad2 = Double.parseDouble(har2);
        DecimalFormat df2 = new DecimalFormat("#,###,##0");

        String har7 = String.valueOf(harga7).replaceAll("\\,", "");
        double hargad7 = Double.parseDouble(har7);
        DecimalFormat df7 = new DecimalFormat("#,###,##0");

        String har3 = String.valueOf(harga3).replaceAll("\\,", "");
        double hargad3 = Double.parseDouble(har3);
        DecimalFormat df3 = new DecimalFormat("#,###,##0");

        String har6 = String.valueOf(harga6).replaceAll("\\,", "");
        double hargad6 = Double.parseDouble(har6);
        DecimalFormat df6 = new DecimalFormat("#,###,##0");

        String har4 = String.valueOf(harga4).replaceAll("\\,", "");
        double hargad4 = Double.parseDouble(har4);
        DecimalFormat df4 = new DecimalFormat("#,###,##0");

        if (hargad > 999) {
            rp.t_netto.setText(df.format(hargad));
        }

        if (hargad8 > 999) {
            rp.lapan.setText(df8.format(harga8));
        }

        if (hargad2 > 999) {
            rp.dua.setText(df2.format(hargad2));
        }

        if (hargad7 > 999) {
            rp.tujuh.setText(df7.format(hargad7));
        }

        if (hargad3 > 999) {
            rp.tiga.setText(df3.format(hargad3));
        }

        if (hargad6 > 999) {
            rp.enam.setText(df6.format(hargad6));
        }
        if (hargad4 > 999) {
            rp.empat.setText(df4.format(hargad4));
        }
    }

    public void cekTotalDtl(view.ReportPenjualan rp) {
        //total
        int harga = 0;
        int harga8 = 0;
        int harga2 = 0;
        int harga7 = 0;
        int harga3 = 0;
        int harga6 = 0;
        int harga4 = 0;
        for (int i = 0; i < rp.tb_report.getRowCount(); i++) {
            int amount = Integer.parseInt((String) rp.tb_report.getValueAt(i, 5));
            int amount8 = Integer.parseInt((String) rp.tb_report.getValueAt(i, 6));
            int amount2 = Integer.parseInt((String) rp.tb_report.getValueAt(i, 7));
            int amount7 = Integer.parseInt((String) rp.tb_report.getValueAt(i, 8));
            int amount3 = Integer.parseInt((String) rp.tb_report.getValueAt(i, 9));
            int amount6 = Integer.parseInt((String) rp.tb_report.getValueAt(i, 10));
            int amount4 = Integer.parseInt((String) rp.tb_report.getValueAt(i, 11));

            harga += amount;
            harga8 += amount8;
            harga2 += amount2;
            harga7 += amount7;
            harga3 += amount3;
            harga6 += amount6;
            harga4 += amount4;

        }
        String har = String.valueOf(harga).replaceAll("\\,", "");
        double hargad = Double.parseDouble(har);
        DecimalFormat df = new DecimalFormat("#,###,##0");

        String har8 = String.valueOf(harga8).replaceAll("\\,", "");
        double hargad8 = Double.parseDouble(har8);
        DecimalFormat df8 = new DecimalFormat("#,###,##0");

        String har2 = String.valueOf(harga2).replaceAll("\\,", "");
        double hargad2 = Double.parseDouble(har2);
        DecimalFormat df2 = new DecimalFormat("#,###,##0");

        String har7 = String.valueOf(harga7).replaceAll("\\,", "");
        double hargad7 = Double.parseDouble(har7);
        DecimalFormat df7 = new DecimalFormat("#,###,##0");

        String har3 = String.valueOf(harga3).replaceAll("\\,", "");
        double hargad3 = Double.parseDouble(har3);
        DecimalFormat df3 = new DecimalFormat("#,###,##0");

        String har6 = String.valueOf(harga6).replaceAll("\\,", "");
        double hargad6 = Double.parseDouble(har6);
        DecimalFormat df6 = new DecimalFormat("#,###,##0");

        String har4 = String.valueOf(harga4).replaceAll("\\,", "");
        double hargad4 = Double.parseDouble(har4);
        DecimalFormat df4 = new DecimalFormat("#,###,##0");

        if (hargad > 999) {
            rp.t_netto.setText(df.format(hargad));
        }

        if (hargad8 > 999) {
            rp.lapan.setText(df8.format(harga8));
        }

        if (hargad2 > 999) {
            rp.dua.setText(df2.format(hargad2));
        }

        if (hargad7 > 999) {
            rp.tujuh.setText(df7.format(hargad7));
        }

        if (hargad3 > 999) {
            rp.tiga.setText(df3.format(hargad3));
        }

        if (hargad6 > 999) {
            rp.enam.setText(df6.format(hargad6));
        }
        if (hargad4 > 999) {
            rp.empat.setText(df4.format(hargad4));
        }
    }

    @Override
    public void fun_TarikDetailSemua(view.ReportPenjualan rp) throws SQLException {
        fun_ObjectTableDetail(rp);
        String startDate = ((JTextField) rp.start_date.getDateEditor().getUiComponent()).getText();
        String endDate = ((JTextField) rp.end_date.getDateEditor().getUiComponent()).getText();
        try {
            con = koneksi.Server.getConnection();
            st = con.createStatement();
            String sql
                    = "SELECT penjualan_header.`tanggal` AS 'Tanggal', penjualan_header.`sales` AS 'Salesman', product.`nama` AS 'Nama Produk', penjualan_detail.`qty` AS 'Total Qty',\n"
                    + "FLOOR(penjualan_detail.`netto`) AS 'netto',FLOOR(penjualan_detail.`netto`*0.8) AS '80',\n"
                    + "FLOOR(penjualan_detail.`netto`*0.2) AS '20',FLOOR(penjualan_detail.`netto`*0.7) AS '70',\n"
                    + "FLOOR(penjualan_detail.`netto`*0.3) AS '30',FLOOR(penjualan_detail.`netto`*0.6) AS '60',\n"
                    + "FLOOR(penjualan_detail.`netto`*0.4) AS '40'\n"
                    + "FROM penjualan_header JOIN penjualan_detail ON penjualan_header.`id`=penjualan_detail.`id` JOIN product ON penjualan_detail.`barcode`= product.`id` WHERE penjualan_header.`tanggal` BETWEEN '" + startDate + "' AND '" + endDate + "' ORDER BY penjualan_header.`tanggal` ASC;";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                rp.dtm = (DefaultTableModel) rp.tb_report.getModel();
                rp.dtm.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9),
                    rs.getString(10),
                    rs.getString(11)
                });
            }
            rp.tb_report.setModel(rp.dtm);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void fun_TarikByProduct(view.ReportPenjualan rp) throws SQLException {
        fun_ObjectTable(rp);
        atur_tableBrand(rp);
        String startDate = ((JTextField) rp.start_date.getDateEditor().getUiComponent()).getText();
        String endDate = ((JTextField) rp.end_date.getDateEditor().getUiComponent()).getText();
        try {
            con = koneksi.Server.getConnection();
            st = con.createStatement();
            String sql
                    = "SELECT penjualan_header.`sales` AS 'Salesman', product.`nama` AS 'Nama Produk', penjualan_detail.`qty` AS 'Total Qty',\n"
                    + "FLOOR(penjualan_detail.`netto`) AS 'netto',FLOOR(penjualan_detail.`netto`*0.8) AS '80',\n"
                    + "FLOOR(penjualan_detail.`netto`*0.2) AS '20',FLOOR(penjualan_detail.`netto`*0.7) AS '70',\n"
                    + "FLOOR(penjualan_detail.`netto`*0.3) AS '30',FLOOR(penjualan_detail.`netto`*0.6) AS '60',\n"
                    + "FLOOR(penjualan_detail.`netto`*0.4) AS '40'\n"
                    + "FROM penjualan_header JOIN penjualan_detail ON penjualan_header.`id`=penjualan_detail.`id` JOIN product ON penjualan_detail.`barcode`= product.`id` WHERE penjualan_header.`tanggal` BETWEEN '" + startDate + "' AND '" + endDate + "' ORDER BY penjualan_header.`sales` ASC;";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                rp.dtm = (DefaultTableModel) rp.tb_report.getModel();
                rp.dtm.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9),
                    rs.getString(10)
                });
            }
            rp.tb_report.setModel(rp.dtm);
            cekTotal(rp);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void fun_GetSales(view.ReportPenjualan rp) throws SQLException {
        try {
            String query = "SELECT * FROM user WHERE jabatan='Sales' ORDER BY username ASC";
            ps = koneksi.Server.getConnection().prepareStatement(query);
            rs = ps.executeQuery(query);
            rp.cb_sales.removeAllItems();
            rp.cb_sales.addItem("Pilih");
            while (rs.next()) {
                rp.cb_sales.addItem(rs.getString("username"));
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void fun_GetSalesDtl(view.ReportPenjualan rp) throws SQLException {
        try {
            String query = "SELECT * FROM user WHERE jabatan='Sales' ORDER BY username ASC";
            ps = koneksi.Server.getConnection().prepareStatement(query);
            rs = ps.executeQuery(query);
            rp.cb_sales.removeAllItems();
            rp.cb_sales.addItem("Pilih");
            while (rs.next()) {
                rp.cb_sales.addItem(rs.getString("username"));
            }
            rp.cb_sales.addItem("Semua");
        } catch (Exception e) {

        }
    }

    @Override
    public void fun_ObjectTable(ReportPenjualan rp) {
        Object[] Baris = {"Sales", "Nama Barang", "Total Qty", "Netto", "80%", "20%", "70%", "30%", "60%", "40%"};
        rp.dtm = new DefaultTableModel(null, Baris);
        rp.tb_report.setModel(rp.dtm);
    }

    public void fun_ObjectPersales(ReportPenjualan rp) {
        Object[] Baris = {"Sales", "Nama Barang", "Total Qty", "Netto", "80%", "20%", "70%", "30%", "60%", "40%"};
        rp.dtm = new DefaultTableModel(null, Baris);
        rp.tb_report.setModel(rp.dtm);
    }

    @Override
    public void fun_ObjectTableDetail(ReportPenjualan rp) {
        Object[] Baris = {"Tanggal", "Sales", "Pembeli", "Nama Barang", "Total Qty", "Netto", "80%", "20%", "70%", "30%", "60%", "40%"};
        rp.dtm = new DefaultTableModel(null, Baris);
        rp.tb_report.setModel(rp.dtm);
    }

    @Override
    public void fun_ObjectTableDefault(ReportPenjualan rp) {
        Object[] Baris = {"Default", "Default", "Default", "Default", "Default", "Default"};
        rp.dtm = new DefaultTableModel(null, Baris);
        rp.tb_report.setModel(rp.dtm);
    }

    @Override
    public void fun_cetakSales(ReportPenjualan rp) throws SQLException {
        int ok = JOptionPane.showConfirmDialog(null, "Jangan Berpindah Halaman Selama Proses Cetak!", "Peringatan!", JOptionPane.YES_NO_OPTION, HEIGHT, warning);
        if (ok == 0) {
            String startDate = ((JTextField) rp.start_date.getDateEditor().getUiComponent()).getText();
            String endDate = ((JTextField) rp.end_date.getDateEditor().getUiComponent()).getText();
            try {
                String NamaFile = "src/report/penjualansales.jasper";
                String URL = "jdbc:mysql://localhost:3306/";
                String DB = "agendist";
                String driver = "com.mysql.jdbc.Driver";
                String user = "root";
                String pass = "";
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection connect = DriverManager.getConnection(URL + DB, user, pass);
                HashMap param = new HashMap();
                param.put("sales", rp.cb_sales.getSelectedItem().toString());
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
    public void fun_cetakProduk(ReportPenjualan rp) throws SQLException {
//        int ok = JOptionPane.showConfirmDialog(null, "Jangan Berpindah Halaman Selama Proses Cetak!", "Peringatan!", JOptionPane.YES_NO_OPTION, HEIGHT, warning);
//        if (ok == 0) {
//            String startDate = ((JTextField) rp.start_date.getDateEditor().getUiComponent()).getText();
//            String endDate = ((JTextField) rp.end_date.getDateEditor().getUiComponent()).getText();
//            try {
//                String NamaFile = "src/report/penjualanproduk.jasper";
//                String URL = "jdbc:mysql://localhost:3306/";
//                String DB = "agendist";
//                String driver = "com.mysql.jdbc.Driver";
//                String user = "root";
//                String pass = "";
//                Class.forName("com.mysql.jdbc.Driver").newInstance();
//                Connection connect = DriverManager.getConnection(URL + DB, user, pass);
//                HashMap param = new HashMap();
//                param.put("tglstart", startDate);
//                param.put("tglend", endDate);
//
//                JasperPrint jPrint = JasperFillManager.fillReport(NamaFile, param, connect);
//                JasperViewer.viewReport(jPrint, false);
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, "Gagal Cetak" + e.getMessage(), "Alert Message!", HEIGHT);
//            }
//        }
    }

    public void atur_tableSales(ReportPenjualan rp) {
        rp.tb_report.getColumn("Sales").setMaxWidth(100);
        rp.tb_report.getColumn("Sales").setMinWidth(50);
        rp.tb_report.getColumn("Sales").setWidth(50);
    }

    public void atur_tableBrand(ReportPenjualan rp) {
        rp.tb_report.getColumn("Sales").setMaxWidth(100);
        rp.tb_report.getColumn("Sales").setMinWidth(50);
        rp.tb_report.getColumn("Sales").setWidth(50);
    }

}
