/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import com.sun.org.apache.xpath.internal.operations.Minus;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import model.WorkTimeReport;
import static java.time.temporal.ChronoUnit.MINUTES;
import static java.time.temporal.ChronoUnit.SECONDS;

/**
 *
 * @author Do Duc Duong
 */
public class WorkTimeReportDAO extends DBContext {

    public ArrayList<WorkTimeReport> getReportTime(String username, int pageIndex, int pageSize) {
        ArrayList<WorkTimeReport> listReport = new ArrayList<>();
        try {
            String sql = "SELECT * FROM\n"
                    + "(SELECT ROW_NUMBER() OVER (ORDER BY [DATE] DESC, [FROM] DESC ) AS ROWID, * FROM\n"
                    + "\n"
                    + "(select * from WORKTIMEREPORT WHERE "
                    + "USERNAME = ?) TBL1) TBL2\n"
                    + "WHERE \n"
                    + "ROWID >= (? - 1) * ? + 1\n"
                    + "AND\n"
                    + "ROWID <= ? * ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            statement.setInt(2, pageIndex);
            statement.setInt(3, pageSize);
            statement.setInt(4, pageIndex);
            statement.setInt(5, pageSize);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Date date = resultSet.getDate("DATE");
                String from = resultSet.getString("FROM");
                String to = resultSet.getString("TO");

                int acceptType = resultSet.getInt("ACCEPT_TYPE");
                String accepter = resultSet.getString("ACCEPTER");
                WorkTimeReport r = new WorkTimeReport();
                r.setDate(date);
                r.setFrom(LocalTime.parse(from));
                r.setTo(LocalTime.parse(to));
                long seconds = SECONDS.between(LocalTime.parse(from), LocalTime.parse(to));
                Time time = new Time(0, 0, Integer.parseInt(String.valueOf(seconds)));
                r.setTotal(time);
                r.setAcceptType(acceptType);
                r.setAccepter(accepter);
                listReport.add(r);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return listReport;
    }

    public ArrayList<WorkTimeReport> getReportTimeWithDate(String username, int pageIndex, int pageSize, String dateFrom, String dateTo) {
        ArrayList<WorkTimeReport> listReport = new ArrayList<>();
        try {
            String sql = "SELECT * FROM\n"
                    + "(SELECT ROW_NUMBER() OVER (ORDER BY DATE DESC, [FROM] DESC) AS ROWID, * FROM\n"
                    + "(SELECT * FROM WORKTIMEREPORT \n"
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
                Date date = resultSet.getDate("DATE");
                String from = resultSet.getString("FROM");
                String to = resultSet.getString("TO");

                int acceptType = resultSet.getInt("ACCEPT_TYPE");
                String accepter = resultSet.getString("ACCEPTER");
                WorkTimeReport r = new WorkTimeReport();
                r.setDate(date);
                r.setFrom(LocalTime.parse(from));
                r.setTo(LocalTime.parse(to));
                long seconds = SECONDS.between(LocalTime.parse(from), LocalTime.parse(to));
                Time time = new Time(0, 0, Integer.parseInt(String.valueOf(seconds)));
                r.setTotal(time);
                r.setAcceptType(acceptType);
                r.setAccepter(accepter);
                listReport.add(r);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return listReport;
    }

    public ArrayList<WorkTimeReport> getReportTimeWithMonth(String username, int monthFind, int pageIndex, int pageSize) {
        ArrayList<WorkTimeReport> listReport = new ArrayList<>();
        try {
            String sql = "SELECT * FROM\n"
                    + "(SELECT ROW_NUMBER() OVER (ORDER BY DATE DESC, [FROM] DESC) AS ROWID, * FROM\n"
                    + "(SELECT * FROM WORKTIMEREPORT \n"
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
                Date date = resultSet.getDate("DATE");
                String from = resultSet.getString("FROM");
                String to = resultSet.getString("TO");

                int acceptType = resultSet.getInt("ACCEPT_TYPE");
                String accepter = resultSet.getString("ACCEPTER");
                WorkTimeReport r = new WorkTimeReport();
                r.setDate(date);
                r.setFrom(LocalTime.parse(from));
                r.setTo(LocalTime.parse(to));
                long seconds = SECONDS.between(LocalTime.parse(from), LocalTime.parse(to));
                Time time = new Time(0, 0, Integer.parseInt(String.valueOf(seconds)));
                r.setTotal(time);
                r.setAcceptType(acceptType);
                r.setAccepter(accepter);
                listReport.add(r);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return listReport;
    }

    public int getTotalPage(String username, int pageSize) {
        try {
            String sql = "SELECT COUNT(*) FROM WORKTIMEREPORT \n"
                    + "WHERE USERNAME = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, username);
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

    public int getTotalPageWithDate(String username, String dateFrom, String dateTo, int pageSize) {
        try {
            String sql = "SELECT COUNT(*) FROM\n"
                    + "(SELECT * FROM WORKTIMEREPORT \n"
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
                    + "(SELECT * FROM WORKTIMEREPORT \n"
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

    public ArrayList<WorkTimeReport> getReportTime(String username) {
        ArrayList<WorkTimeReport> listReport = new ArrayList<>();
        try {
            String sql = "SELECT * FROM WORKTIMEREPORT WHERE USERNAME = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Date date = resultSet.getDate("DATE");
                String from = resultSet.getString("FROM");
                String to = resultSet.getString("TO");

                int acceptType = resultSet.getInt("ACCEPT_TYPE");
                String accepter = resultSet.getString("ACCEPTER");
                WorkTimeReport r = new WorkTimeReport();
                r.setDate(date);
                r.setFrom(LocalTime.parse(from));
                r.setTo(LocalTime.parse(to));
                long seconds = SECONDS.between(LocalTime.parse(from), LocalTime.parse(to));
                Time time = new Time(0, 0, Integer.parseInt(String.valueOf(seconds)));
                r.setTotal(time);
                r.setAcceptType(acceptType);
                r.setAccepter(accepter);
                listReport.add(r);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return listReport;
    }

    public ArrayList<WorkTimeReport> getReportTimeWithDate(String username, String dateFrom, String dateTo) {
        ArrayList<WorkTimeReport> listReport = new ArrayList<>();
        try {
            String sql = "SELECT * FROM WORKTIMEREPORT WHERE USERNAME = ?\n"
                    + "AND [DATE] >= ?\n"
                    + "AND [DATE] <= ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, dateFrom);
            statement.setString(3, dateTo);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Date date = resultSet.getDate("DATE");
                String from = resultSet.getString("FROM");
                String to = resultSet.getString("TO");

                int acceptType = resultSet.getInt("ACCEPT_TYPE");
                String accepter = resultSet.getString("ACCEPTER");
                WorkTimeReport r = new WorkTimeReport();
                r.setDate(date);
                r.setFrom(LocalTime.parse(from));
                r.setTo(LocalTime.parse(to));
                long seconds = SECONDS.between(LocalTime.parse(from), LocalTime.parse(to));
                Time time = new Time(0, 0, Integer.parseInt(String.valueOf(seconds)));
                r.setTotal(time);
                r.setAcceptType(acceptType);
                r.setAccepter(accepter);
                listReport.add(r);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return listReport;
    }

    public ArrayList<WorkTimeReport> getReportTimeWithMonth(String username, int monthFind) {
        ArrayList<WorkTimeReport> listReport = new ArrayList<>();
        try {
            String sql = "SELECT * FROM WORKTIMEREPORT WHERE USERNAME = ?\n"
                    + "AND MONTH(DATE) = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            statement.setInt(2, monthFind);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Date date = resultSet.getDate("DATE");
                String from = resultSet.getString("FROM");
                String to = resultSet.getString("TO");

                int acceptType = resultSet.getInt("ACCEPT_TYPE");
                String accepter = resultSet.getString("ACCEPTER");
                WorkTimeReport r = new WorkTimeReport();
                r.setDate(date);
                r.setFrom(LocalTime.parse(from));
                r.setTo(LocalTime.parse(to));
                long seconds = SECONDS.between(LocalTime.parse(from), LocalTime.parse(to));
                Time time = new Time(0, 0, Integer.parseInt(String.valueOf(seconds)));
                r.setTotal(time);
                r.setAcceptType(acceptType);
                r.setAccepter(accepter);
                listReport.add(r);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return listReport;
    }

    public void insertReport(String username, String date, String from, String to) {
        try {
            String sql = "INSERT INTO WORKTIMEREPORT VALUES (?, ?, ?, 0, ?, NULL)";
            statement = conn.prepareStatement(sql);
            statement.setString(1, date);
            statement.setString(2, from);
            statement.setString(3, to);
            statement.setString(4, username);
            statement.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void update(String username, String date, String from, String to, String accepter, int acceptType) {
        try {
            String sql = "UPDATE WORKTIMEREPORT SET\n"
                    + "ACCEPT_TYPE = ?,\n"
                    + "ACCEPTER = ?\n"
                    + "WHERE [DATE] = ?\n"
                    + "AND [FROM] = ?\n"
                    + "AND [TO] = ?\n"
                    + "AND USERNAME = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, acceptType);
            statement.setString(2, accepter);
            statement.setString(3, date);
            statement.setString(4, from);
            statement.setString(5, to);
            statement.setString(6, username);
            statement.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
