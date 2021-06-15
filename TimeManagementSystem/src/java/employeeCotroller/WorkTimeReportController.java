/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeeCotroller;

import dal.WorkTimeReportDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.WorkTimeReport;
import org.apache.jasper.tagplugins.jstl.ForEach;

/**
 *
 * @author Do Duc Duong
 */
public class WorkTimeReportController extends BaseAuthenticationController {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Cookie[] c = request.getCookies();
//        for(int i = 0; i < c.length; i++) {
//            if(c[i].getName().equals("monthFind") || c[i].getName().equals("dateFrom") || c[i].getName().equals("dateTo")) {
//                c[i].setValue("");
//                c[i].setMaxAge(0);
//                response.addCookie(c[i]);
//            }
//        }
        int pageIndex = 1;
        if (request.getParameter("page") != null) {
            pageIndex = Integer.parseInt(request.getParameter("page"));
        }
        int pageSize = 15;
        Account account = (Account) request.getSession().getAttribute("account");
        WorkTimeReportDAO dbReport = new WorkTimeReportDAO();
        ArrayList<WorkTimeReport> listReportInPage = dbReport.getReportTime(account.getUsername(), pageIndex, pageSize);
        ArrayList<WorkTimeReport> listReport = dbReport.getReportTime(account.getUsername());
        int totalPage = dbReport.getTotalPage(account.getUsername(), pageSize);

        if (request.getParameter("dateFrom") != null) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();

            String dateFrom = request.getParameter("dateFrom");
            boolean isDateFromValid = false;
            if (dateFrom.length() > 0) {
                isDateFromValid = true;
            }
            if (isDateFromValid) {
                String dateTo = dtf.format(now);
                if (request.getParameter("dateTo").length() > 0) {
                    dateTo = request.getParameter("dateTo");
                }
                Cookie[] cookies = request.getCookies();
                for(int i = 0; i < cookies.length; i++) {
                    if(cookies[i].getName().equals("monthFind")) {
                        cookies[i].setValue("");
                        cookies[i].setMaxAge(0);
                        response.addCookie(cookies[i]);
                        break;
                    }
                }
                Cookie ccDateFrom = new Cookie("dateFrom", dateFrom);
                ccDateFrom.setMaxAge(999999999);
                Cookie ccDateTo = new Cookie("dateTo", dateTo);
                ccDateTo.setMaxAge(999999999);
                response.addCookie(ccDateFrom);
                response.addCookie(ccDateTo);
                totalPage = dbReport.getTotalPageWithDate(account.getUsername(), dateFrom, dateTo, pageSize);
                listReportInPage = dbReport.getReportTimeWithDate(account.getUsername(), pageIndex, pageSize, dateFrom, dateTo);
                listReport = dbReport.getReportTimeWithDate(account.getUsername(), dateFrom, dateTo);
            } else {
                Cookie[] cookies = request.getCookies();
                for (int i = 0; i < cookies.length; i++) {
                    if (!cookies[i].getName().equalsIgnoreCase("JSESSIONID")) {
                        cookies[i].setValue("");
                        cookies[i].setMaxAge(0);
                        response.addCookie(cookies[i]);
                    }
                }
            }

        } else {
            Cookie[] cookies = request.getCookies();
            String dateFrom = "";
            String dateTo = "";
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equalsIgnoreCase("dateFrom")) {
                    dateFrom = cookies[i].getValue();
                }
                if (cookies[i].getName().equalsIgnoreCase("dateTo")) {
                    dateTo = cookies[i].getValue();
                }
            }
            if (!dateFrom.equalsIgnoreCase("")) {
                totalPage = dbReport.getTotalPageWithDate(account.getUsername(), dateFrom, dateTo, pageSize);
                listReportInPage = dbReport.getReportTimeWithDate(account.getUsername(), pageIndex, pageSize, dateFrom, dateTo);
                listReport = dbReport.getReportTimeWithDate(account.getUsername(), dateFrom, dateTo);
            }
        }

        if (request.getParameter("thisMonth") != null) {
            boolean isFindThisMonth = Boolean.parseBoolean(request.getParameter("thisMonth"));
            LocalDateTime now = LocalDateTime.now();
            int monthFind = now.getMonthValue();
            if (!isFindThisMonth) {
                monthFind -= 1;
            }
            listReportInPage = dbReport.getReportTimeWithMonth(account.getUsername(), monthFind, pageIndex, pageSize);
            listReport = dbReport.getReportTimeWithMonth(account.getUsername(), monthFind);
            totalPage = dbReport.getTotalPageWithMonth(account.getUsername(), monthFind, pageSize);
            Cookie[] cookies = request.getCookies();
            for(int i = 0; i < cookies.length; i++ ){
                if(cookies[i].getName().equals("dateFrom") || cookies[i].getName().equals("dateTo")) {
                    cookies[i].setValue("");
                    cookies[i].setMaxAge(0);
                    response.addCookie(cookies[i]);
                }
            }
            Cookie ccMonthFind = new Cookie("monthFind", String.valueOf(monthFind));
            response.addCookie(ccMonthFind);
        } else {
            Cookie[] cookies = request.getCookies();
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equalsIgnoreCase("monthFind")) {
                    if (cookies[i].getValue().length() > 0) {
                        int monthFind = Integer.parseInt(cookies[i].getValue());
                        listReportInPage = dbReport.getReportTimeWithMonth(account.getUsername(), monthFind, pageIndex, pageSize);
                        listReport = dbReport.getReportTimeWithMonth(account.getUsername(), monthFind);
                        totalPage = dbReport.getTotalPageWithMonth(account.getUsername(), monthFind, pageSize);
                        
                    }
                }
            }
        }
        int housAccepted = 0;
        int minsAccepted = 0;
        int secsAccepted = 0;
        int housNotAccepted = 0;
        int minsNotAccepted = 0;
        int secsNotAccepted = 0;
        int housReject = 0;
        int minsReject = 0;
        int secsReject = 0;
        for(int i = 0; i < listReport.size(); i++) {
            int hou = listReport.get(i).getTotal().getHours();
            int min = listReport.get(i).getTotal().getMinutes();
            int sec = listReport.get(i).getTotal().getSeconds();
            if(listReport.get(i).getAcceptType() == 1) {
                housAccepted += hou;
                minsAccepted += min;
                secsAccepted += sec;
            } else if(listReport.get(i).getAcceptType() == 0){
                housNotAccepted += hou;
                minsNotAccepted += min;
                secsNotAccepted += sec;
            } else {
                housReject += hou;
                minsReject += min;
                secsReject += sec;
            }
        }
        minsAccepted += secsAccepted / 60;
        minsNotAccepted += secsNotAccepted / 60;
        minsReject += secsReject / 60;
        secsAccepted %= 60;
        secsNotAccepted %= 60;
        secsReject %= 60;
        housAccepted += minsAccepted / 60;
        housNotAccepted += minsNotAccepted / 60;
        housReject += minsReject / 60;
        minsAccepted %= 60;
        minsNotAccepted %= 60;
        minsReject %= 60;
        
        String url = request.getServletPath();
        url = url.substring(1, url.length());
        request.setAttribute("url", url);
        request.setAttribute("timeAccepted", housAccepted + ":" + minsAccepted + ":" + secsAccepted);
        request.setAttribute("timeNotAccepted", housNotAccepted + ":" + minsNotAccepted + ":" + secsNotAccepted);
        request.setAttribute("timeReject", housReject + ":" + minsReject + ":" + secsReject);
        
        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("listReport", listReportInPage);
        request.getRequestDispatcher("employeeView/WorkTimeReportForm.jsp").forward(request, response);

    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
