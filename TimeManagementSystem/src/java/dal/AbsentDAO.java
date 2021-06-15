/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import static java.time.temporal.ChronoUnit.MINUTES;
import java.util.ArrayList;
import model.Absent;
import model.Request;
import model.WorkTimeReport;

/**
 *
 * @author Do Duc Duong
 */
public class AbsentDAO extends DBContext {

    public ArrayList<Absent> getListAbsent(String username, int pageIndex, int pageSize) {
        ArrayList<Absent> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM\n"
                    + "(SELECT ROW_NUMBER() OVER (ORDER BY [DATE] DESC) AS ROWID, * FROM\n"
                    + "(SELECT * FROM ABSENTHISTORY \n"
                    + "WHERE USERNAME = ?) TBL1) TBL2\n"
                    + "WHERE ROWID > (? - 1) * ?\n"
                    + "AND ROWID < ? * ? + 1\n"
                    + "";
            statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            statement.setInt(2, pageIndex);
            statement.setInt(3, pageSize);
            statement.setInt(4, pageIndex);
            statement.setInt(5, pageSize);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int rid = resultSet.getInt("REQUESTID");
                String date = resultSet.getString("DATE");
                String reason = resultSet.getString("REASON");
                int acceptType = resultSet.getInt("ACCEPT_TYPE");
                String accepter = resultSet.getString("ACCEPTER");
                Request r = new Request();
                r.setId(rid);
                Absent a = new Absent();
                a.setRequest(r);
                a.setDate(date);
                a.setReason(reason);
                a.setAcceptType(acceptType);
                a.setAccepter(accepter);
                a.setUsername(username);
                list.add(a);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public int getTotalPage(String username, int pageSize) {
        try {
            String sql = "SELECT COUNT(*) FROM (SELECT * FROM ABSENTHISTORY WHERE USERNAME = ?) TBL";
            statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return (count % pageSize == 0)
                        ? count / pageSize : count / pageSize + 1;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public ArrayList<Absent> getListAbsentWithDate(String username, int pageIndex, int pageSize, String dateFrom, String dateTo) {
        ArrayList<Absent> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM\n"
                    + "(SELECT ROW_NUMBER() OVER (ORDER BY DATE DESC) AS ROWID, * FROM\n"
                    + "(SELECT * FROM ABSENTHISTORY \n"
                    + "WHERE USERNAME = ?\n"
                    + "AND DATE >= ?\n"
                    + "AND DATE <= ?) TBL) TBL2\n"
                    + "WHERE ROWID > (? - 1) * ?\n"
                    + "AND ROWID < ? * ? + 1";
            statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, dateFrom);
            statement.setString(3, dateTo);
            statement.setInt(4, pageIndex);
            statement.setInt(5, pageSize);
            statement.setInt(6, pageIndex);
            statement.setInt(7, pageSize);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int rid = resultSet.getInt("REQUESTID");
                String date = resultSet.getString("DATE");
                String reason = resultSet.getString("REASON");
                int acceptType = resultSet.getInt("ACCEPT_TYPE");
                String accepter = resultSet.getString("ACCEPTER");
                Request r = new Request();
                r.setId(rid);
                Absent a = new Absent();
                a.setRequest(r);
                a.setDate(date);
                a.setReason(reason);
                a.setAcceptType(acceptType);
                a.setAccepter(accepter);
                a.setUsername(username);
                list.add(a);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public ArrayList<Absent> getListAbsentWithMonth(String username, int monthFind, int pageIndex, int pageSize) {
        ArrayList<Absent> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM\n"
                    + "(SELECT ROW_NUMBER() OVER (ORDER BY DATE DESC) AS ROWID, * FROM\n"
                    + "(SELECT * FROM ABSENTHISTORY \n"
                    + "WHERE USERNAME = ?\n"
                    + "AND\n"
                    + "MONTH(DATE) = ?) TBL1) TBL2\n"
                    + "WHERE ROWID > (? - 1) * ?\n"
                    + "AND ROWID < ? * ? + 1";
            statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            statement.setInt(2, monthFind);
            statement.setInt(3, pageIndex);
            statement.setInt(4, pageSize);
            statement.setInt(5, pageIndex);
            statement.setInt(6, pageSize);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int rid = resultSet.getInt("REQUESTID");
                String date = resultSet.getString("DATE");
                String reason = resultSet.getString("REASON");
                int acceptType = resultSet.getInt("ACCEPT_TYPE");
                String accepter = resultSet.getString("ACCEPTER");
                Request r = new Request();
                r.setId(rid);
                Absent a = new Absent();
                a.setRequest(r);
                a.setDate(date);
                a.setReason(reason);
                a.setAcceptType(acceptType);
                a.setAccepter(accepter);
                a.setUsername(username);
                list.add(a);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public int getTotalPageWithDate(String username, String dateFrom, String dateTo, int pageSize) {
        try {
            String sql = "SELECT COUNT(*) FROM\n"
                    + "(SELECT * FROM ABSENTHISTORY \n"
                    + "WHERE USERNAME = ?\n"
                    + "AND DATE >= ?\n"
                    + "AND DATE <= ?) TBL";
            statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, dateFrom);
            statement.setString(3, dateTo);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int totalReport = resultSet.getInt(1);
                int totalPage = totalReport / pageSize;
                return (totalReport % pageSize == 0)
                        ? totalPage : totalPage + 1;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public int getTotalPageWithMonth(String username, int monthFind, int pageSize) {
        try {
            String sql = "SELECT COUNT(*) FROM\n"
                    + "(SELECT * FROM ABSENTHISTORY \n"
                    + "WHERE USERNAME = ?\n"
                    + "AND\n"
                    + "MONTH(DATE) = ?) TBL";
            statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            statement.setInt(2, monthFind);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int totalReport = resultSet.getInt(1);
                int totalPage = totalReport / pageSize;
                return (totalReport % pageSize == 0)
                        ? totalPage : totalPage + 1;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public void insert(int requestId, String date, String reason, String username) {
        try {
            String sql = "INSERT INTO ABSENTHISTORY VALUES (?, ?, ?, 0, ?, NULL)";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, requestId);
            statement.setString(2, date);
            statement.setString(3, reason);
            statement.setString(4, username);
            statement.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ArrayList<Absent> getAllRequest(int pageIndex, int pageSize) {
        ArrayList<Absent> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM\n"
                    + "(SELECT ROW_NUMBER() OVER (ORDER BY [DATE] DESC) AS ROWID, * \n"
                    + "FROM ABSENTHISTORY) TBL1\n"
                    + "WHERE ROWID > (? - 1) * ?\n"
                    + "AND ROWID < ? * ? + 1";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, pageIndex);
            statement.setInt(2, pageSize);
            statement.setInt(3, pageIndex);
            statement.setInt(4, pageSize);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int rid = resultSet.getInt("REQUESTID");
                String date = resultSet.getString("DATE");
                String reason = resultSet.getString("REASON");
                int acceptType = resultSet.getInt("ACCEPT_TYPE");
                String accepter = resultSet.getString("ACCEPTER");
                String username = resultSet.getString("USERNAME");
                Request r = new Request();
                r.setId(rid);
                Absent a = new Absent();
                a.setRequest(r);
                a.setDate(date);
                a.setReason(reason);
                a.setAcceptType(acceptType);
                a.setAccepter(accepter);
                a.setUsername(username);
                list.add(a);

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public int getTotalPageOfAll(int pageSize) {
        try {
            String sql = "SELECT COUNT(*) FROM ABSENTHISTORY";
            statement = conn.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int totalRecord = resultSet.getInt(1);
                return (totalRecord % pageSize == 0)
                        ? totalRecord / pageSize : totalRecord / pageSize + 1;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public void updateRecord(String username, String accepter, String date, String reason, String requestId, String acceptType) {
        try {
            String sql = "UPDATE ABSENTHISTORY SET ACCEPT_TYPE = ?, ACCEPTER = ?\n"
                    + "WHERE USERNAME = ?\n"
                    + "AND [DATE] = ?\n"
                    + "AND REASON = ?\n"
                    + "AND REQUESTID = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, acceptType);
            statement.setString(2, accepter);
            statement.setString(3, username);
            statement.setString(4, date);
            statement.setString(5, reason);
            statement.setString(6, requestId);
            statement.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
