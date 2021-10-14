/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import view.TransaksiPenerimaan;

/**
 *
 * @author Dell
 */
public interface TransPenerimaan {
    public void fun_Clear(TransaksiPenerimaan penerimaan);
    public void fun_Enabled(TransaksiPenerimaan penerimaan);
    public void fun_Disable(TransaksiPenerimaan penerimaan);
    public void fun_Delete(TransaksiPenerimaan penerimaan);
    public void fun_GetDist(TransaksiPenerimaan penerimaan) throws SQLException;
    public void fun_Simpan(TransaksiPenerimaan penerimaan) throws SQLException;
    public void fun_GenID(TransaksiPenerimaan penerimaan) throws SQLException;
    public void fun_CekStok(TransaksiPenerimaan penerimaan) throws SQLException;
    public void fun_Tambah(TransaksiPenerimaan penerimaan) throws SQLException;
}
