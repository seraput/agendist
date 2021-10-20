/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koneksi;

import java.sql.DriverManager;

/**
 *
 * @author Dell
 */
public class Server {
    public static java.sql.Connection getConnection(){
        
        java.sql.Connection conn = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/agendist", "exroot", "agendist123");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
