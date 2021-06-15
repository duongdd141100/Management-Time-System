/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeeCotroller;

import dal.AbsentDAO;
import dal.RequestDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Absent;
import model.Account;
import model.Request;

/**
 *
 * @author Do Duc Duong
 */
public class AbsentHistoryController extends BaseAuthenticationController {

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
        int pageSize = 15;
        int pageIndex = 1;
        if(request.getParameter("page") != null) {
            pageIndex = Integer.parseInt(request.getParameter("page"));
        }
        Account a = (Account) request.getSession().getAttribute("account");
        String username = a.getUsername();
        RequestDAO dbRequest = new RequestDAO();
        ArrayList<Request> listRequest = dbRequest.getRequest();
        AbsentDAO dbAbsent = new AbsentDAO();
        ArrayList<Absent> listAbsent = dbAbsent.getListAbsent(username, pageIndex, pageSize);
        int totalPage = dbAbsent.getTotalPage(username, pageSize);
        
        
        
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
                totalPage = dbAbsent.getTotalPageWithDate(username, dateFrom, dateTo, pageSize);
                listAbsent = dbAbsent.getListAbsentWithDate(username, pageIndex, pageSize, dateFrom, dateTo);
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
                totalPage = dbAbsent.getTotalPageWithDate(username, dateFrom, dateTo, pageSize);
                listAbsent = dbAbsent.getListAbsentWithDate(username, pageIndex, pageSize, dateFrom, dateTo);
            }
        }
        
        
        
        
        if (request.getParameter("thisMonth") != null) {
            boolean isFindThisMonth = Boolean.parseBoolean(request.getParameter("thisMonth"));
            LocalDateTime now = LocalDateTime.now();
            int monthFind = now.getMonthValue();
            if (!isFindThisMonth) {
                monthFind -= 1;
            }
            listAbsent = dbAbsent.getListAbsentWithMonth(username, monthFind, pageIndex, pageSize);
            totalPage = dbAbsent.getTotalPageWithMonth(username, monthFind, pageSize);
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
                        listAbsent = dbAbsent.getListAbsentWithMonth(username, monthFind, pageIndex, pageSize);
                        totalPage = dbAbsent.getTotalPageWithMonth(username, monthFind, pageSize);
                        
                    }
                }
            }
        }
        
        
        
        
        for(int i = 0; i < listAbsent.size(); i++) {
            for(int j = 0; j < listRequest.size(); j++) {
                if(listAbsent.get(i).getRequest().getId() == listRequest.get(j).getId()){
                    listAbsent.get(i).getRequest().setName(listRequest.get(j).getName());
                }
            }
        }
        String url = request.getServletPath();
        url = url.substring(1, url.length());
        request.setAttribute("url", url);
        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("listAbsent", listAbsent);                
        request.getRequestDispatcher("employeeView/AbsentHistoryForm.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
