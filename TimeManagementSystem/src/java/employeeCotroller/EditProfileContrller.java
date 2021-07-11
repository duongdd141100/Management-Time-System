/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeeCotroller;

import dal.AccountDAO;
import dal.AdminDAO;
import dal.EmployeeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Admin;
import model.Employee;

/**
 *
 * @author Do Duc Duong
 */
public class EditProfileContrller extends BaseAuthenticationController {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountDAO dbAcc = new AccountDAO();
        AdminDAO dbAdmin = new AdminDAO();
        EmployeeDAO dbEmp = new EmployeeDAO();
        Account a = (Account) request.getSession().getAttribute("account");
        
        
        if(dbAcc.isAdmin(a.getUsername())) {
            Admin ad = dbAdmin.getAdmin(a.getUsername());
            request.setAttribute("info", ad);
            request.setAttribute("adminName", ad.getName());
            request.setAttribute("group", "Admin");
        }  else {
            Employee e = dbEmp.getEmployee(a.getUsername());
            request.setAttribute("info", e);
            request.setAttribute("employeeName", e.getName());
            request.setAttribute("group", "Employee");
        }
        request.getRequestDispatcher("employeeView/edit-profile.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminDAO dbAdmin = new AdminDAO();
        EmployeeDAO dbEmp = new EmployeeDAO();
        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String dob = request.getParameter("dob");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        AccountDAO dbAcc = new AccountDAO();
        if(dbAcc.isAdmin(username)) {
            dbAdmin.update(username, name, dob, email, gender);
        } else {
            int timeId = dbEmp.getTimeId(username);
            dbEmp.updateEmployee(username, name, email, dob, Boolean.parseBoolean(gender), timeId);
        }
        response.sendRedirect("edit-profile");
    }
    
}
