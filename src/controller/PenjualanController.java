/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import view.TransaksiPenjualan;

/**
 *
 * @author Dell
 */
public interface PenjualanController {
    public void fun_Clear(TransaksiPenjualan tp);
    public void fun_Tambah(TransaksiPenjualan tp);
    public void fun_Cari(TransaksiPenjualan tp) throws SQLException;
    public void fun_Simpan(TransaksiPenjualan tp) throws SQLException;
    public void fun_Delete(TransaksiPenjualan tp);
    public void fun_Table(TransaksiPenjualan tp) throws SQLException;
    public void fun_Tampil(TransaksiPenjualan tp);
    public void fun_Ubah(TransaksiPenjualan tp);
    public void fun_GenID(TransaksiPenjualan tp) throws SQLException;
    public void fun_GetSales(TransaksiPenjualan tp) throws SQLException;
}
