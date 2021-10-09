/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.LoginController;
import static groovy.ui.text.FindReplaceUtility.dispose;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import main.Main;
import view.LoginForm;

/**
 *
 * @author Dell
 */
public class LoginModel implements LoginController {

    Connection conn;
    Statement st;
    ResultSet rs;
    public static String username, nama, jabatan;

    public static String getUsername() {
        return username;
    }

    public static String getNama() {
        return nama;
    }

    public static String getJabatan() {
        return jabatan;
    }

    @Override
    public void fun_Login(LoginForm lg) throws SQLException {
        try {
            String user = lg.tfEmail.getText().toString();
            String pass = lg.tfPassword.getText().toString();

            String sql = "SELECT * FROM user WHERE username= '" + user + "' AND password= '" + pass + "' ";
            conn = koneksi.Server.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            if (rs.next()) {
                username = rs.getString("username");
                nama = rs.getString("nama");
                jabatan = rs.getString("jabatan");
//                System.out.println(jabatan);
                Main main = new Main();
                main.show();
                lg.dispose();
                JOptionPane.showMessageDialog(null, "Berhasil Login");
            } else {
                JOptionPane.showMessageDialog(null, "User atau Password Salah");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Koneksi Error" + e.getMessage());
        }
    }

}
