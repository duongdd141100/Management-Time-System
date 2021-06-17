/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.util.ArrayList;
import model.Group;

/**
 *
 * @author Do Duc Duong
 */
public class GroupDAO extends DBContext{

    public String getGroupName(int groupId) {
        try {
            String sql = "SELECT * FROM [GROUP] WHERE ID = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, groupId);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return resultSet.getString("NAME");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<Group> getAllGroup() {
        ArrayList<Group> list = new ArrayList<>();
         try {
            String sql = "SELECT * FROM [GROUP]";
            statement = conn.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Group g = new Group();
                g.setId(resultSet.getInt("id"));
                g.setName(resultSet.getString("name"));
                list.add(g);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
}
