/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
import koneksi.Connection;
import view.MasterUser;
import controller.MsUser;

/**
 *
 * @author Dell
 */
public class mMasterUser implements MsUser {
    
    protected java.sql.Connection con;
    protected PreparedStatement ps;
    protected ResultSet rs;
    protected Statement st;
    ImageIcon sucess = new ImageIcon(getClass().getResource("/asset/checked.png"));
    ImageIcon invalid = new ImageIcon(getClass().getResource("/asset/cancel.png"));
    ImageIcon warning = new ImageIcon(getClass().getResource("/asset/warning.png"));

    @Override
    public void fun_Simpan(MasterUser mu) throws SQLException {
        try{
            con = koneksi.Server.getConnection();
            String pass = "123456";
            String status = "Y";
            String query = "INSERT INTO `user` (`username`, `nama`, `telp`, `alamat`, `jabatan`, `password`, `status`) VALUES (?,?,?,?,?,?,?)";
            ps = con.prepareStatement(query);
            
            ps.setString(1, mu.tf_username.getText());
            ps.setString(2, mu.tf_nama.getText());
            ps.setString(3, mu.tf_telp.getText());
            ps.setString(4, mu.tf_alamat.getText());
            ps.setString(5, mu.cb_jabatan.getSelectedItem().toString());
            ps.setString(6, pass);
            ps.setString(7, status);
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Tersimpan!", "Berhasil", HEIGHT, sucess);
            ps.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ada Sedikit Masalah! "+ e.getMessage().toString(), "Gagal", HEIGHT, invalid);
        }
        finally{
            fun_Disable(mu);
            fun_Clear(mu);
            fun_Tampil(mu);
        }    
    }

    @Override
    public void fun_Update(MasterUser mu) throws SQLException {
        try{
            con = koneksi.Server.getConnection();
            String query = "UPDATE user SET nama=?, telp=?, alamat=?, jabatan=? WHERE username=?";
            ps = con.prepareStatement(query);
            
            ps.setString(5, mu.tf_username.getText());
            ps.setString(1, mu.tf_nama.getText());
            ps.setString(2, mu.tf_telp.getText());
            ps.setString(3, mu.tf_alamat.getText());
            ps.setString(4, mu.cb_jabatan.getSelectedItem().toString());
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Diperbaharui!", "Berhasil", HEIGHT, sucess);
            ps.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ada Sedikit Masalah! "+ e.getMessage().toString(), "Gagal", HEIGHT, invalid);
        }
        finally{
            fun_Tampil(mu);
            fun_Disable(mu);
            fun_Clear(mu);
        }
    }

    @Override
    public void fun_Delete(MasterUser mu) throws SQLException {
        try{
            con = koneksi.Server.getConnection();
            String query = "DELETE FROM user WHERE username=?";
            ps = con.prepareStatement(query);
            
            ps.setString(1, mu.tf_username.getText());
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Terhapus!", "Berhasil", HEIGHT, sucess);
            ps.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ada Sedikit Masalah! "+ e.getMessage().toString(), "Gagal", HEIGHT, invalid);
        }
        finally{
            fun_Tampil(mu);
            fun_Disable(mu);
            fun_Clear(mu);
        }
    }

    @Override
    public void fun_Table(MasterUser mu) throws SQLException {
        try{
            int pilih = mu.table_user.getSelectedRow();
            mu.tf_username.setText(mu.tblmodel.getValueAt(pilih,0).toString());
            mu.tf_nama.setText(mu.tblmodel.getValueAt(pilih,1).toString());
            mu.tf_telp.setText(mu.tblmodel.getValueAt(pilih,2).toString());
            mu.tf_alamat.setText(mu.tblmodel.getValueAt(pilih,3).toString());
            mu.cb_jabatan.setSelectedItem(mu.tblmodel.getValueAt(pilih,4).toString());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error, Please Try Again!");
        }
    }

    @Override
    public void fun_Tampil(MasterUser mu) throws SQLException {
        Object[] header ={"Username", "Nama", "Telp", "Alamat", "Status"};
        mu.tblmodel = new DefaultTableModel(null, header);
        try {
            con = koneksi.Server.getConnection();
            st = con.createStatement();
            String sql = "select * from user where status='Y' order by username asc";
            rs = st.executeQuery(sql);
            while(rs.next())
            {
                mu.tblmodel.addRow(new Object[]{
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5)
                });
            } 
            mu.table_user.setModel(mu.tblmodel);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @Override
    public void fun_Cari(MasterUser mu) {
        DefaultTableModel model = (DefaultTableModel) mu.table_user.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        mu.table_user.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(mu.tf_cari.getText().trim()));
    }

    @Override
    public void fun_Disable(MasterUser mu) {
        mu.tf_username.setEnabled(false);
        mu.tf_nama.setEnabled(false);
        mu.tf_telp.setEnabled(false);
        mu.tf_alamat.setEnabled(false);
        mu.cb_jabatan.setEnabled(false);
        mu.bt_simpan.setEnabled(false);
        mu.bt_batal.setEnabled(false);
        mu.bt_simpan.setVisible(true);
        mu.bt_update.setVisible(false);
    }

    @Override
    public void fun_Enable(MasterUser mu) {
        mu.tf_username.requestFocus();
        mu.tf_username.setEnabled(true);
        mu.tf_nama.setEnabled(true);
        mu.tf_telp.setEnabled(true);
        mu.tf_alamat.setEnabled(true);
        mu.cb_jabatan.setEnabled(true);
        mu.bt_simpan.setEnabled(true);
        mu.bt_batal.setEnabled(true);
    }

    @Override
    public void fun_Clear(MasterUser mu) {
        mu.tf_username.setText("");
        mu.tf_nama.setText("");
        mu.tf_telp.setText("");
        mu.tf_alamat.setText("");
        mu.cb_jabatan.setSelectedItem("Pilih");
    }


}
