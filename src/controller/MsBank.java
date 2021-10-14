/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import view.MasterBank;

/**
 *
 * @author Dell
 */
public interface MsBank {
    public void fun_Clear(MasterBank mb);
    public void fun_Disable(MasterBank mb);
    public void fun_Enable(MasterBank mb);
    public void fun_Cari(MasterBank mb);
    public void fun_Simpan(MasterBank mb) throws SQLException;
    public void fun_Update(MasterBank mb) throws SQLException;
    public void fun_Delete(MasterBank mb) throws SQLException;
    public void fun_Table(MasterBank mb) throws SQLException;
    public void fun_Tampil(MasterBank mb) throws SQLException;
    public void fun_GenID(MasterBank mb) throws SQLException;
}
