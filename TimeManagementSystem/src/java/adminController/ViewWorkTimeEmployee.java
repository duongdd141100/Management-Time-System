/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminController;

import dal.AdminDAO;
import dal.EmployeeDAO;
import dal.WorkTimeDAO;
import dal.WorkTimeReportDAO;
import employeeCotroller.BaseAuthenticationController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Admin;
import model.Employee;
import model.WorkTimeReport;

/**
 *
 * @author Do Duc Duong
 */
public class ViewWorkTimeEmployee extends BaseAuthenticationController {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account a = (Account) request.getSession().getAttribute("account");
        String usernameAdmin = a.getUsername();
        String username = request.getParameter("username");
        WorkTimeReportDAO dbWorkTime = new WorkTimeReportDAO();
        int pageIndex = 1;
        int pageSize = 15;
        if(request.getParameter("page") != null) {
            pageIndex = Integer.parseInt(request.getParameter("page"));
        }
        int totalPage = dbWorkTime.getTotalPage(username, pageSize);
        ArrayList<WorkTimeReport> listReport = dbWorkTime.getReportTime(username, pageIndex, pageSize);
        EmployeeDAO dbE = new EmployeeDAO();
        Employee e = dbE.getEmployee(username);
        AdminDAO dbAdmin = new AdminDAO();
        ArrayList<Admin> listAdmin = dbAdmin.getAllAdmin();
        
        
        String url = request.getServletPath();
        request.setAttribute("listAdmin", listAdmin);
        request.setAttribute("url", url.substring(1, url.length()));
        request.setAttribute("usernameAdmin", usernameAdmin);
        request.setAttribute("employee", e);
        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("listReport", listReport);
        request.getRequestDispatcher("adminView/ViewWorkTime.jsp").forward(request, response);
        
        
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
            String username = request.getParameter("username");
        String date = request.getParameter("date");
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String accepter = request.getParameter("accepter");
        int acceptType = Integer.parseInt(request.getParameter("acceptType"));
        
        WorkTimeReportDAO dbWorkTime = new WorkTimeReportDAO();
        response.getWriter().print(from + "   " + to);
        dbWorkTime.update(username, date, from, to, accepter, acceptType);
        
        response.sendRedirect("view-work-time-employee?username="+username+"");
    }

}
