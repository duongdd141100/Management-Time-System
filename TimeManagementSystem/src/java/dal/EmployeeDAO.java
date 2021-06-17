/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import model.Employee;

/**
 *
 * @author Do Duc Duong
 */
public class EmployeeDAO extends DBContext {

    public Employee getEmployee(String username) {
        try {
            String sql = "SELECT * FROM EMPLOYEE\n"
                    + "INNER JOIN WORKTIME\n"
                    + "ON TIMEID = ID\n"
                    + "WHERE USERNAME = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("NAME");
                boolean gender = resultSet.getBoolean("GENDER");
                String dob = resultSet.getString("DOB");
                String email = resultSet.getString("EMAIL");
                String from = resultSet.getString("FROM");
                String to = resultSet.getString("TO");
                Employee e = new Employee();
                e.setName(name);
                e.setGender(gender);
                e.setDob(dob);
                e.setEmail(email);
                e.setFrom(LocalTime.parse(from));
                e.setTo(LocalTime.parse(to));
                e.setUsername(username);
                return e;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<Employee> getEmployee() {
        ArrayList<Employee> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM EMPLOYEE\n"
                    + "INNER JOIN WORKTIME\n"
                    + "ON TIMEID = ID\n";
            statement = conn.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                String name = resultSet.getString("NAME");
                boolean gender = resultSet.getBoolean("GENDER");
                String dob = resultSet.getString("DOB");
                String email = resultSet.getString("EMAIL");
                String from = resultSet.getString("FROM");
                String to = resultSet.getString("TO");
                String username = resultSet.getString("USERNAME");
                Employee e = new Employee();
                e.setName(name);
                e.setGender(gender);
                e.setDob(dob);
                e.setEmail(email);
                e.setFrom(LocalTime.parse(from));
                e.setTo(LocalTime.parse(to));
                e.setUsername(username);
                list.add(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public int getTimeId(String username) {
        try {
            String sql = "SELECT TIMEID FROM EMPLOYEE WHERE USERNAME = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public void updateEmployee(String username, String name, String email, String dob, boolean gender, int timeId) {
        try {
            String sql = "UPDATE EMPLOYEE SET NAME = ?, EMAIL = ?, DOB = ?, GENDER = ?, TIMEID = ? WHERE USERNAME = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, dob);
            statement.setBoolean(4, gender);
            statement.setInt(5, timeId);
            statement.setString(6, username);
            statement.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteEmployee(String username) {
        try {
            String sql = "DELETE FROM EMPLOYEE WHERE USERNAME = ?; "
                    + "DELETE FROM ACCOUNT WHERE USERNAME = ?; "
                    + "DELETE FROM ABSENTHISTORY WHERE USERNAME = ?; "
                    + "DELETE FROM WORKTIMEREPORT WHERE USERNAME = ?; ";
            String sql1 = "DELETE FROM EMPLOYEE WHERE USERNAME = ?";
            String sql2 = "DELETE FROM ACCOUNT WHERE USERNAME = ?";
            String sql3 = "DELETE FROM ABSENTHISTORY WHERE USERNAME = ?";
            String sql4 = "DELETE FROM WORKTIMEREPORT WHERE USERNAME = ?";
            statement = conn.prepareStatement(sql1);
            statement.setString(1, username);
            statement.execute();
            statement = conn.prepareStatement(sql2);
            statement.setString(1, username);
            statement.execute();
            statement = conn.prepareStatement(sql3);
            statement.setString(1, username);
            statement.execute();
            statement = conn.prepareStatement(sql4);
            statement.setString(1, username);
            statement.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void insertEmployee(String name, String gender, String dob, String email, int timeId, String user) {
        try {
            String sql = "insert into employee values (?, ?, ?, ?, ?, ?)";
            statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, gender);
            statement.setString(3, dob);
            statement.setString(4, email);
            statement.setInt(5, timeId);
            statement.setString(6, user);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
