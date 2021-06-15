/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.util.ArrayList;
import model.Admin;

/**
 *
 * @author Do Duc Duong
 */
public class AdminDAO extends DBContext{

    public Admin getAdmin(String username) {
        try {
            String sql = "SELECT * FROM ADMIN WHERE USERNAME = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                Admin a = new Admin();
                String name = resultSet.getString("NAME");
                boolean gender = Boolean.parseBoolean(resultSet.getString("GENDER"));
                String dob = resultSet.getString("DOB");
                String email = resultSet.getString("EMAIL");
                a.setName(name);
                a.setEmail(email);
                a.setGender(gender);
                a.setUsername(username);
                a.setDob(dob);
                return a;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<Admin> getAllAdmin() {
        ArrayList<Admin> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM ADMIN";
            statement = conn.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Admin a = new Admin();
                String name = resultSet.getString("NAME");
                boolean gender = Boolean.parseBoolean(resultSet.getString("GENDER"));
                String dob = resultSet.getString("DOB");
                String email = resultSet.getString("EMAIL");
                String username = resultSet.getString("USERNAME");
                a.setName(name);
                a.setEmail(email);
                a.setGender(gender);
                a.setUsername(username);
                a.setDob(dob);
                list.add(a);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
}
