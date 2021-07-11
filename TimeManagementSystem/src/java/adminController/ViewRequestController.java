/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminController;

import dal.AbsentDAO;
import dal.AdminDAO;
import dal.EmployeeDAO;
import dal.RequestDAO;
import employeeCotroller.BaseAuthenticationController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Absent;
import model.Account;
import model.Admin;
import model.Employee;
import model.Request;

/**
 *
 * @author Do Duc Duong
 */
public class ViewRequestController extends BaseAuthenticationController {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = "all";
        int pageIndex = 1;
        if (request.getParameter("page") != null) {
            pageIndex = Integer.parseInt(request.getParameter("page"));
        }
        int pageSize = 15;
        AbsentDAO dbAbsent = new AbsentDAO();
        ArrayList<Absent> listAbsent = dbAbsent.getAllRequest(pageIndex, pageSize);
        int totalPage = dbAbsent.getTotalPageOfAll(pageSize);
        if (request.getParameter("username") != null) {
            username = request.getParameter("username");
            if (!username.equals("all")) {
                listAbsent = dbAbsent.getListAbsent(username, pageIndex, pageSize);
                totalPage = dbAbsent.getTotalPage(username, pageSize);
                Cookie c = new Cookie("username", username);
                response.addCookie(c);
            }else {
                Cookie[] cookies = request.getCookies();
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("username")) {
                    cookies[i].setValue("");
                    cookies[i].setMaxAge(0);
                    response.addCookie(cookies[i]);
                    break;
                }
            }
            }
        } else {
            Cookie[] cookies = request.getCookies();
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("username")) {
                    username = cookies[i].getValue();
                    listAbsent = dbAbsent.getListAbsent(username, pageIndex, pageSize);
                    totalPage = totalPage = dbAbsent.getTotalPage(username, pageSize);
                    break;
                }
            }
        }
        RequestDAO dbRequest = new RequestDAO();
        ArrayList<Request> listRequest = dbRequest.getRequest();

        for (int i = 0; i < listAbsent.size(); i++) {
            for (int j = 0; j < listRequest.size(); j++) {
                if (listAbsent.get(i).getRequest().getId() == listRequest.get(j).getId()) {
                    listAbsent.get(i).getRequest().setName(listRequest.get(j).getName());
                    break;
                }
            }
        }

        Account account = (Account) request.getSession().getAttribute("account");
        String usernameAdmin = account.getUsername();
        AdminDAO dbAdmin = new AdminDAO();
        ArrayList<Admin> listAdmin = dbAdmin.getAllAdmin();
        EmployeeDAO dbE = new EmployeeDAO();
        ArrayList<Employee> listEmployee = dbE.getEmployee();
        Admin admin = dbAdmin.getAdmin(account.getUsername());
        
        request.setAttribute("adminName", admin.getName());
        request.setAttribute("adminName", admin.getName());
        request.setAttribute("username", username);
        String url = request.getServletPath();
        request.setAttribute("url", url.substring(1, url.length()));
        request.setAttribute("listAdmin", listAdmin);
        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("usernameAdmin", usernameAdmin);
        request.setAttribute("listEmployee", listEmployee);
        request.setAttribute("listAbsent", listAbsent);
        request.getRequestDispatcher("adminView/ViewRequest.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String accepter = request.getParameter("accepter");
        String date = request.getParameter("date");
        String reason = request.getParameter("reason");
        String requestId = request.getParameter("requestId");
        String acceptType = request.getParameter("acceptType");
        AbsentDAO dbAbsent = new AbsentDAO();
        dbAbsent.updateRecord(username, accepter, date, reason, requestId, acceptType);
        response.sendRedirect("view-request");
    }

}
