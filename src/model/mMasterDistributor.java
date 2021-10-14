/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.MsDistributor;
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
import view.MasterDistributor;

/**
 *
 * @author Dell
 */
public class mMasterDistributor implements MsDistributor {

    protected java.sql.Connection con;
    protected PreparedStatement ps;
    protected ResultSet rs;
    protected Statement st;
    Main m = new Main();
    ImageIcon sucess = new ImageIcon(getClass().getResource("/asset/checked.png"));
    ImageIcon invalid = new ImageIcon(getClass().getResource("/asset/cancel.png"));
    ImageIcon warning = new ImageIcon(getClass().getResource("/asset/warning.png"));

    @Override
    public void fun_Clear(MasterDistributor mb) {
        mb.dist_id.setText("");
        mb.dist_nama.setText("");
        mb.dist_telp.setText("");
        mb.dist_alamat.setText("");
    }

    @Override
    public void fun_Disable(MasterDistributor mb) {
        mb.dist_id.setEnabled(false);
        mb.dist_nama.setEnabled(false);
        mb.dist_telp.setEnabled(false);
        mb.dist_alamat.setEnabled(false);
        mb.bt_simpan.setEnabled(false);
        mb.bt_batal.setEnabled(false);
    }

    @Override
    public void fun_Enable(MasterDistributor mb) {
        mb.dist_nama.setEnabled(true);
        mb.dist_telp.setEnabled(true);
        mb.dist_alamat.setEnabled(true);
        mb.bt_simpan.setEnabled(true);
        mb.bt_batal.setEnabled(true);
    }

    @Override
    public void fun_Cari(MasterDistributor mb) {
        DefaultTableModel model = (DefaultTableModel) mb.dist_table.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        mb.dist_table.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(mb.dist_cari.getText().trim()));
    }

    @Override
    public void fun_Simpan(MasterDistributor mb) throws SQLException {
        try {
            String status = "Y";
            con = koneksi.Server.getConnection();
            String query = "INSERT INTO `distributor` (`id`, `nama`, `telp`, `alamat`, created, date, status) VALUES (?,?,?,?,?,?,?)";
            ps = con.prepareStatement(query);

            ps.setString(1, mb.dist_id.getText());
            ps.setString(2, mb.dist_nama.getText());
            ps.setString(3, mb.dist_telp.getText());
            ps.setString(4, mb.dist_alamat.getText());
            ps.setString(5, m.txt_username.getText());
            ps.setString(6, m.txt_tanggal.getText());
            ps.setString(7, status);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data Tersimpan!", "Berhasil", HEIGHT, sucess);
            ps.close();
            fun_Disable(mb);
            fun_Clear(mb);
            fun_Tampil(mb);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ada Sedikit Masalah! " + e.getMessage().toString(), "Gagal", HEIGHT, invalid);
        }
    }

    @Override
    public void fun_Update(MasterDistributor mb) throws SQLException {
        try {
            con = koneksi.Server.getConnection();
            String status = "Y";
            String query = "UPDATE distributor SET nama=?, telp=?, alamat=?, created=?, date=?, status=? WHERE id=?";
            ps = con.prepareStatement(query);

            ps.setString(7, mb.dist_id.getText());
            ps.setString(1, mb.dist_nama.getText());
            ps.setString(2, mb.dist_telp.getText());
            ps.setString(3, mb.dist_alamat.getText());
            ps.setString(4, m.txt_username.getText());
            ps.setString(5, m.txt_tanggal.getText());
            ps.setString(6, status);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data Diperbaharui!", "Berhasil", HEIGHT, sucess);
            ps.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ada Sedikit Masalah! " + e.getMessage(), "Gagal", HEIGHT, invalid);
        } finally {
            fun_Tampil(mb);
            fun_Disable(mb);
            fun_Clear(mb);
        }
    }

    @Override
    public void fun_Delete(MasterDistributor mb) throws SQLException {
        try{
            con = koneksi.Server.getConnection();
            String query = "DELETE FROM distributor WHERE id=?";
            ps = con.prepareStatement(query);
            
            ps.setString(1, mb.dist_id.getText());
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Terhapus!", "Berhasil", HEIGHT, sucess);
            ps.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ada Sedikit Masalah! "+ e.getMessage().toString(), "Gagal", HEIGHT, invalid);
        }
        finally{
            fun_Tampil(mb);
            fun_Disable(mb);
            fun_Clear(mb);
        }
    }

    @Override
    public void fun_Table(MasterDistributor mb) throws SQLException {
        try{
            int pilih = mb.dist_table.getSelectedRow();
            mb.dist_telp.setText(mb.tblModel.getValueAt(pilih,2).toString());
            mb.dist_id.setText(mb.tblModel.getValueAt(pilih,0).toString());
            mb.dist_nama.setText(mb.tblModel.getValueAt(pilih,1).toString());
            mb.dist_alamat.setText(mb.tblModel.getValueAt(pilih,3).toString());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error, Please Try Again!");
        }
    }

    @Override
    public void fun_Tampil(MasterDistributor mb) throws SQLException {
        Object[] header ={"Kode", "Nama Distributor", "No Telp", "Alamat", "Author", "Last Update", "Active"};
        mb.tblModel = new DefaultTableModel(null, header);
        try {
            con = koneksi.Server.getConnection();
            st = con.createStatement();
            String sql = "select * from distributor where status='Y' order by id asc";
            rs = st.executeQuery(sql);
            while(rs.next())
            {
                mb.tblModel.addRow(new Object[]{
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getString(6),
                rs.getString(7)
                });
            } 
            mb.dist_table.setModel(mb.tblModel);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void fun_GenID(MasterDistributor mb) throws SQLException {
        try {
            String query = "select MAX(RIGHT(id,5)) AS NO from distributor order by id desc";
            ps = koneksi.Server.getConnection().prepareStatement(query);
            rs = ps.executeQuery(query);
            while (rs.next()) {
                if (rs.first() == false) {
                    mb.dist_id.setText("D10001");
                } else {
                    rs.last();
                    int auto_id = rs.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int Nomor = no.length();
                    //MENGATUR jumlah 0
                    for (int j = 0; j < 4 - Nomor; j++) {
                        no = "0" + no;
                    }
                    mb.dist_id.setText("D" + no);
                }
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();//penanganan masalah
        }
    }

    @Override
    public void fun_FunCekNama(MasterDistributor mb) throws SQLException {
//        String stokDB = "";
//        try {
//            String cekStock = "select stok from product where nama like '"+mb.dist_nama.getText()+"'";
//            Statement stat = koneksi.Server.getConnection().createStatement();
//            ResultSet hasil = stat.executeQuery(cekStock);
//            while (hasil.next()) {
//                stokDB = hasil.getString("stok");
//            }
//            int qtyDB = Integer.parseInt(stokDB);
//            int input = Integer.parseInt(tp.tf_qty.getText());
//            if (qtyDB < input) {
//                JOptionPane.showMessageDialog(null, "Stok Kurang Dari Permintaan!", "Gagal", HEIGHT, invalid);
//                tp.tf_qty.setText("");
//                tp.tf_qty.requestFocus();
//            } else {
//                fun_Tambah(tp);
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Bermasalah Cek Stok: " + e.getMessage(), "Alert Message", HEIGHT, warning);
//        }
    }

}
