/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import view.ReportPenjualan;
import view.ReportStok;

/**
 *
 * @author Dell
 */
public interface rLaporanStok {
    public void fun_Clear(ReportStok rs);
    public void fun_TarikProduk(ReportStok rs) throws SQLException;
    public void fun_TarikAll(ReportStok rs) throws SQLException;
    public void fun_TarikStok(ReportStok rs) throws SQLException;
    public void fun_GetProduk(ReportStok rs) throws SQLException;
    public void fun_ObjectTable(ReportStok rs);
    public void fun_ObjectTableStok(ReportStok rs);
    public void fun_cetakStok(ReportStok rs) throws SQLException;
    public void fun_cetakProduk(ReportStok rs) throws SQLException;
    public void fun_cetakAll(ReportStok rs) throws SQLException;
}
