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
public class ChagePasswordController extends BaseAuthenticationController {

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
        Account account = (Account) request.getSession().getAttribute("account");
        String username = account.getUsername();
        EmployeeDAO dbEmployee = new EmployeeDAO();
        Employee e = dbEmployee.getEmployee(username);
        
        request.setAttribute("account", account);
        request.getRequestDispatcher("employeeView/ChangePasswordForm.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newPass = request.getParameter("newPass");
        Account a = (Account) request.getSession().getAttribute("account");
        AccountDAO dbAcc = new AccountDAO();
        dbAcc.chagePassword(a.getUsername(), newPass);
        a = dbAcc.getAccount(a.getUsername(), newPass);
        request.getSession().removeAttribute("account");
        request.getSession().setAttribute("account", a);
        String pathTrace = request.getHeader("referer");
        for (int i = 0; i < pathTrace.length(); i++) {
            if (pathTrace.charAt(i) == '/') {
                pathTrace = pathTrace.substring(i + 1, pathTrace.length());
            }
        }
        response.sendRedirect(pathTrace);
    }

}
