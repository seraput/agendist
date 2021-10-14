/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import view.ReportPenjualan;

/**
 *
 * @author Dell
 */
public interface RepPenjualanController {
    public void fun_Clear(ReportPenjualan rp);
    public void fun_TarikBySales(ReportPenjualan rp) throws SQLException;
    public void fun_TarikDetail(ReportPenjualan rp) throws SQLException;
    public void fun_TarikDetailSemua(ReportPenjualan rp) throws SQLException;
    public void fun_TarikByProduct(ReportPenjualan rp) throws SQLException;
    public void fun_GetSales(ReportPenjualan rp) throws SQLException;
    public void fun_GetSalesDtl(ReportPenjualan rp) throws SQLException;
    public void fun_ObjectTable(ReportPenjualan rp);
    public void fun_ObjectTableDetail(ReportPenjualan rp);
    public void fun_ObjectTableDefault(ReportPenjualan rp);
    public void fun_cetakSales(ReportPenjualan rp) throws SQLException;
    public void fun_cetakProduk(ReportPenjualan rp) throws SQLException;
}
