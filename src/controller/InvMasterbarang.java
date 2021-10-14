/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.sql.SQLException;
import view.InventoryMasterbarang;
/**
 *
 * @author Dell
 */
public interface InvMasterbarang {
    public void fun_Clear(InventoryMasterbarang mp);
    public void fun_Disable(InventoryMasterbarang mp);
    public void fun_Enable(InventoryMasterbarang mp);
    public void fun_Cari(InventoryMasterbarang mp);
    public void fun_Simpan(InventoryMasterbarang mp) throws SQLException;
    public void fun_Update(InventoryMasterbarang mp) throws SQLException;
    public void fun_Delete(InventoryMasterbarang mp) throws SQLException;
    public void fun_Table(InventoryMasterbarang mp) throws SQLException;
    public void fun_Tampil(InventoryMasterbarang mp) throws SQLException;
    public void fun_GenID(InventoryMasterbarang mp) throws SQLException;
}
