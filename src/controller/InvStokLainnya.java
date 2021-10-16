/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import view.InventoryStokLainnya;

/**
 *
 * @author Dell
 */
public interface InvStokLainnya {
    public void fun_Clear(InventoryStokLainnya isl);
    public void fun_Simpan(InventoryStokLainnya isl) throws SQLException;
    public void fun_Delete(InventoryStokLainnya isl);
    public void fun_Table(InventoryStokLainnya isl);
    public void fun_GenID(InventoryStokLainnya isl) throws SQLException;
    public void fun_CekStok(InventoryStokLainnya isl) throws SQLException;
    public void fun_Tambah(InventoryStokLainnya isl) throws SQLException;
}
