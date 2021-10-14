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
import main.Main;
import view.MasterBank;
import controller.MsBank;

/**
 *
 * @author Dell
 */
public class mMasterBank implements MsBank{

    protected java.sql.Connection con;
    protected PreparedStatement ps;
    protected ResultSet rs;
    protected Statement st;
    Main m = new Main();
    ImageIcon sucess = new ImageIcon(getClass().getResource("/asset/checked.png"));
    ImageIcon invalid = new ImageIcon(getClass().getResource("/asset/cancel.png"));
    ImageIcon warning = new ImageIcon(getClass().getResource("/asset/warning.png"));
    
    @Override
    public void fun_Clear(MasterBank mb) {
        mb.kode_bank.setText("");
        mb.nama_bank.setText("");
        mb.txt_cari.setText("");
        mb.cb_jenis.setSelectedItem("Pilih");
    }

    @Override
    public void fun_Disable(MasterBank mb) {
        mb.cb_status.setVisible(false);
        mb.kode_bank.setEnabled(false);
        mb.nama_bank.setEnabled(false);
        mb.cb_jenis.setEnabled(false);
        mb.bt_simpan.setEnabled(false);
        mb.bt_batal.setEnabled(false);
        mb.bt_simpan.setVisible(true);
        mb.bt_update.setVisible(false);
    }

    @Override
    public void fun_Enable(MasterBank mb) {
        mb.nama_bank.setEnabled(true);
        mb.cb_jenis.setEnabled(true);
        mb.bt_simpan.setEnabled(true);
        mb.bt_batal.setEnabled(true);
    }

    @Override
    public void fun_Cari(MasterBank mb) {
        DefaultTableModel model = (DefaultTableModel) mb.table_bank.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        mb.table_bank.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(mb.txt_cari.getText().trim()));
    }

    @Override
    public void fun_Simpan(MasterBank mb) throws SQLException {
        try{
            String status = "Y";
            con = koneksi.Server.getConnection();
            String query = "INSERT INTO `bank` (`kdbank`, `namabank`, `pembayaran`, `status`, created_by, created_date) VALUES (?,?,?,?,?,?)";
            ps = con.prepareStatement(query);
            
            ps.setString(1, mb.kode_bank.getText());
            ps.setString(2, mb.nama_bank.getText());
            ps.setString(3, mb.cb_jenis.getSelectedItem().toString());
            ps.setString(4, status);
            ps.setString(5, m.txt_username.getText());
            ps.setString(6, m.txt_tanggal.getText());
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Tersimpan!", "Berhasil", HEIGHT, sucess);
            ps.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ada Sedikit Masalah! "+ e.getMessage().toString(), "Gagal", HEIGHT, invalid);
        }
        finally{
            fun_Disable(mb);
            fun_Clear(mb);
            fun_Tampil(mb);
        }    
    }

    @Override
    public void fun_Update(MasterBank mb) throws SQLException {
        try{
            con = koneksi.Server.getConnection();
            String query = "UPDATE bank SET namabank=?, pembayaran=?, status=?, created_by=?, created_date=? WHERE kdbank=?";
            ps = con.prepareStatement(query);
            
            ps.setString(6, mb.kode_bank.getText());
            ps.setString(1, mb.nama_bank.getText());
            ps.setString(2, mb.cb_jenis.getSelectedItem().toString());
            ps.setString(3, mb.cb_status.getSelectedItem().toString());
            ps.setString(4, m.txt_username.getText());
            ps.setString(5, m.txt_tanggal.getText());
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Diperbaharui!", "Berhasil", HEIGHT, sucess);
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
    public void fun_Delete(MasterBank mb) throws SQLException {
        try{
            con = koneksi.Server.getConnection();
            String query = "DELETE FROM bank WHERE kdbank=?";
            ps = con.prepareStatement(query);
            
            ps.setString(1, mb.kode_bank.getText());
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
    public void fun_Table(MasterBank mb) throws SQLException {
        try{
            int pilih = mb.table_bank.getSelectedRow();
            mb.cb_jenis.setSelectedItem(mb.tblModel.getValueAt(pilih, 2).toString());
            mb.kode_bank.setText(mb.tblModel.getValueAt(pilih,0).toString());
            mb.nama_bank.setText(mb.tblModel.getValueAt(pilih,1).toString());
            mb.cb_status.setSelectedItem(mb.tblModel.getValueAt(pilih, 3).toString());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error, Please Try Again!");
        }
    }

    @Override
    public void fun_Tampil(MasterBank mb) throws SQLException {
        Object[] header ={"Kode Bank", "Nama Bank", "Jenis Pembayaran", "Active", "Author", "Last Update"};
        mb.tblModel = new DefaultTableModel(null, header);
        try {
            con = koneksi.Server.getConnection();
            st = con.createStatement();
            String sql = "select * from bank order by kdbank asc";
            rs = st.executeQuery(sql);
            while(rs.next())
            {
                mb.tblModel.addRow(new Object[]{
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getString(6)
                });
            } 
            mb.table_bank.setModel(mb.tblModel);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void fun_GenID(MasterBank mb) throws SQLException {
        try {
            String query = "select MAX(RIGHT(kdbank,5)) AS NO from bank order by kdbank desc";
            ps = koneksi.Server.getConnection().prepareStatement(query);
            rs = ps.executeQuery(query);
            while (rs.next()) {
                if (rs.first() == false) {
                    mb.kode_bank.setText("B10001");
                } else {
                    rs.last();
                    int auto_id = rs.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int Nomor = no.length();
                    //MENGATUR jumlah 0
                    for (int j = 0; j < 4 - Nomor; j++) {
                        no = "0" + no;
                    }
                    mb.kode_bank.setText("B" + no);
                }
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();//penanganan masalah
        }
    }
    
}
