/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Dell
 */
public class Report {
    private String sales, produk;
    private int qty, netto, n8, n2, n7, n3, n6, n4;

    public Report() {
    }
    
    public Report(String sales, String produk, int qty, int netto, int n8, int n2, int n7, int n3, int n6, int n4) {
        this.sales = sales;
        this.produk = produk;
        this.qty = qty;
        this.netto = netto;
        this.n8 = n8;
        this.n2 = n2;
        this.n7 = n7;
        this.n3 = n3;
        this.n6 = n6;
        this.n4 = n4;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public String getProduk() {
        return produk;
    }

    public void setProduk(String produk) {
        this.produk = produk;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getNetto() {
        return netto;
    }

    public void setNetto(int netto) {
        this.netto = netto;
    }

    public int getN8() {
        return n8;
    }

    public void setN8(int n8) {
        this.n8 = n8;
    }

    public int getN2() {
        return n2;
    }

    public void setN2(int n2) {
        this.n2 = n2;
    }

    public int getN7() {
        return n7;
    }

    public void setN7(int n7) {
        this.n7 = n7;
    }

    public int getN3() {
        return n3;
    }

    public void setN3(int n3) {
        this.n3 = n3;
    }

    public int getN6() {
        return n6;
    }

    public void setN6(int n6) {
        this.n6 = n6;
    }

    public int getN4() {
        return n4;
    }

    public void setN4(int n4) {
        this.n4 = n4;
    }
    
    
}
