/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import view.MasterUser;

/**
 *
 * @author Dell
 */
public interface MasterUserController {
    public void fun_Clear(MasterUser mu);
    public void fun_Disable(MasterUser mu);
    public void fun_Enable(MasterUser mu);
    public void fun_Cari(MasterUser mu);
    public void fun_Simpan(MasterUser mu) throws SQLException;
    public void fun_Update(MasterUser mu) throws SQLException;
    public void fun_Delete(MasterUser mu) throws SQLException;
    public void fun_Table(MasterUser mu) throws SQLException;
    public void fun_Tampil(MasterUser mu) throws SQLException;
}
