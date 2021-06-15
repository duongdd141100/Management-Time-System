/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.util.ArrayList;
import model.Request;

/**
 *
 * @author Do Duc Duong
 */
public class RequestDAO extends DBContext{

    public ArrayList<Request> getRequest() {
        ArrayList<Request> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM REQUEST";
            statement = conn.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
                Request r = new Request();
                r.setId(id);
                r.setName(name);
                list.add(r);
                
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
}
