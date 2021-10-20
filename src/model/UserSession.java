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
public class UserSession {
    private static String sUsername;
    private static String sNama;
    private static String sTelp;
    private static String sAlamat;
    private static String sJabatan;

    public static String getsUsername() {
        return sUsername;
    }

    public static void setsUsername(String sUsername) {
        UserSession.sUsername = sUsername;
    }

    public static String getsNama() {
        return sNama;
    }

    public static void setsNama(String sNama) {
        UserSession.sNama = sNama;
    }

    public static String getsTelp() {
        return sTelp;
    }

    public static void setsTelp(String sTelp) {
        UserSession.sTelp = sTelp;
    }

    public static String getsAlamat() {
        return sAlamat;
    }

    public static void setsAlamat(String sAlamat) {
        UserSession.sAlamat = sAlamat;
    }

    public static String getsJabatan() {
        return sJabatan;
    }

    public static void setsJabatan(String sJabatan) {
        UserSession.sJabatan = sJabatan;
    }
    
    
}
