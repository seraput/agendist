/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import view.LoginForm;

/**
 *
 * @author Dell
 */
public interface LoginController {
    public void fun_Login(LoginForm lf) throws SQLException;
}
