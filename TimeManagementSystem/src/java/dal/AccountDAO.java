/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Account;

/**
 *
 * @author Do Duc Duong
 */
public class AccountDAO extends DBContext {

    public Account getAccount(String username, String password) {
        try {
            String sql = "SELECT * FROM ACCOUNT WHERE USERNAME = ? AND PASSWORD = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Account account = new Account();
                account.setUsername(username);
                account.setPassword(password);
                account.setGroupId(resultSet.getInt("GROUPID"));
                return account;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void chagePassword(String username, String newPass) {
        try {
            String sql = "UPDATE ACCOUNT SET PASSWORD = ? WHERE USERNAME = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, newPass);
            statement.setString(2, username);
            statement.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updatePassword(String email, String password) {
        try {
            String sql = "UPDATE ACCOUNT SET PASSWORD = ? \n"
                    + "WHERE USERNAME = (SELECT USERNAME FROM EMPLOYEE WHERE EMAIL = ?) ";
            statement = conn.prepareStatement(sql);
            statement.setString(1, password);
            statement.setString(2, email);
            statement.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
