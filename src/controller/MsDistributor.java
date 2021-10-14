/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import view.MasterDistributor;

/**
 *
 * @author Dell
 */
public interface MsDistributor {
    public void fun_Clear(MasterDistributor mb);
    public void fun_Disable(MasterDistributor mb);
    public void fun_Enable(MasterDistributor mb);
    public void fun_Cari(MasterDistributor mb);
    public void fun_Simpan(MasterDistributor mb) throws SQLException;
    public void fun_Update(MasterDistributor mb) throws SQLException;
    public void fun_Delete(MasterDistributor mb) throws SQLException;
    public void fun_Table(MasterDistributor mb) throws SQLException;
    public void fun_Tampil(MasterDistributor mb) throws SQLException;
    public void fun_GenID(MasterDistributor mb) throws SQLException;
    public void fun_FunCekNama(MasterDistributor mb) throws SQLException;
}
