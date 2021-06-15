/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.util.ArrayList;
import model.Feature;

/**
 *
 * @author Do Duc Duong
 */
public class FeatureDAO extends DBContext {

    public ArrayList<Feature> getFeatures(String username) {
        ArrayList<Feature> list = new ArrayList<>();
        try {
            String sql = "SELECT FEATUREID, URL FROM ACCOUNT\n"
                    + "INNER JOIN [GROUP]\n"
                    + "ON GROUPID = ID\n"
                    + "LEFT JOIN GROUP_FEATURES\n"
                    + "ON ID = GROUP_FEATURES.GROUPID\n"
                    + "INNER JOIN FEATURES \n"
                    + "ON FEATUREID = FEATURES.ID\n"
                    + "WHERE USERNAME = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                int id = resultSet.getInt("FEATUREID");
                String url = resultSet.getString("URL");
                Feature f = new Feature();
                f.setId(id);
                f.setUrl(url);
                list.add(f);
                
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

}
