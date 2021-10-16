/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import view.TransaksiPengeluaran;

/**
 *
 * @author Dell
 */
public interface TransPengeluaran {
    public void fun_Clear(TransaksiPengeluaran pengeluaran);
    public void fun_Enabled(TransaksiPengeluaran pengeluaran);
    public void fun_Disable(TransaksiPengeluaran pengeluaran);
    public void fun_Delete(TransaksiPengeluaran pengeluaran);
    public void fun_GetSales(TransaksiPengeluaran pengeluaran) throws SQLException;
    public void fun_Simpan(TransaksiPengeluaran pengeluaran) throws SQLException;
    public void fun_SimpanBad(TransaksiPengeluaran pengeluaran) throws SQLException;                                            
    public void fun_GenID(TransaksiPengeluaran pengeluaran) throws SQLException;
    public void fun_CekStok(TransaksiPengeluaran pengeluaran) throws SQLException;
    public void fun_CekStokBad(TransaksiPengeluaran pengeluaran) throws SQLException;
    public void fun_Tambah(TransaksiPengeluaran pengeluaran) throws SQLException;
}
