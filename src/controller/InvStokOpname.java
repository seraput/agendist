/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import view.InventoryOpname;
import view.OpnameTambah;

/**
 *
 * @author Dell
 */
public interface InvStokOpname {
    public void fun_Clear(OpnameTambah ot);
    public void fun_Simpan(OpnameTambah ot) throws SQLException;
    public void fun_Delete(OpnameTambah ot);
    public void fun_Table(OpnameTambah ot);
    public void fun_GenID(OpnameTambah ot) throws SQLException;
    public void fun_Tambah(OpnameTambah ot);
}
