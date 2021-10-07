/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.sql.SQLException;
import view.MasterData;
/**
 *
 * @author Dell
 */
public interface MasterDataController {
    public void fun_Clear(MasterData md);
    public void fun_Disable(MasterData md);
    public void fun_Enable(MasterData md);
    public void fun_Cari(MasterData md);
    public void fun_Simpan(MasterData md) throws SQLException;
    public void fun_Update(MasterData md) throws SQLException;
    public void fun_Delete(MasterData md) throws SQLException;
    public void fun_Table(MasterData md) throws SQLException;
    public void fun_Tampil(MasterData md) throws SQLException;
}
