/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.util.ArrayList;
import model.WorkTime;

/**
 *
 * @author Do Duc Duong
 */
public class WorkTimeDAO extends DBContext{

    public ArrayList<WorkTime> getWorkTime() {
        ArrayList<WorkTime> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM WORKTIME";
            statement = conn.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                int id = resultSet.getInt("ID");
                String from = resultSet.getString("FROM");
                String to = resultSet.getString("TO");
                WorkTime w = new WorkTime();
                w.setId(id);
                w.setFrom(from.substring(0, 8));
                w.setTo(to.substring(0, 8));
                list.add(w);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public void deleteRecord(int id) {
        try {
            String sql = "DELETE FROM WORKTIME WHERE ID = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateRecord(int id, String from, String to) {
        try {
            String sql = "UPDATE WORKTIME SET [FROM] = ?, [TO] = ? WHERE ID = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, from);
            statement.setString(2, to);
            statement.setInt(3, id);
            statement.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void insertRecord(int id, String from, String to) {
        try {
            String sql = "INSERT INTO WORKTIME VALUES(?, ?, ?)";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2, from);
            statement.setString(3, to);
            statement.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
