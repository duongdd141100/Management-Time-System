/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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

    public ArrayList<Account> getAllAccount() {
        ArrayList<Account> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM ACCOUNT";
            statement = conn.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Account account = new Account();
                account.setUsername(resultSet.getString("username"));
                account.setPassword(resultSet.getString("password"));
                account.setGroupId(resultSet.getInt("GROUPID"));
                list.add(account);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public void insertAccount(String user, String pass, int groupId) {
        try {
            String sql = "insert into account values(?, ?, ?)";
            statement = conn.prepareStatement(sql);
            statement.setString(1, user);
            statement.setString(2, pass);
            statement.setInt(3, groupId);
            statement.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean isAdmin(String username) {
        try {
            String sql = "select [group].Name from [group] inner join account on account.GROUPID = [GROUP].ID where username = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                if(resultSet.getString("name").equals("Admin")) {
                    return true;
                }  else {
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

}
