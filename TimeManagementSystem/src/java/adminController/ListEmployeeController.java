/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminController;

import dal.AdminDAO;
import dal.EmployeeDAO;
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

/**
 *
 * @author Do Duc Duong
 */
public class ListEmployeeController extends BaseAuthenticationController {

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
        EmployeeDAO dbE = new EmployeeDAO();
        ArrayList<Employee> listE = dbE.getEmployee();
        Account a = (Account) request.getSession().getAttribute("account");
        AdminDAO dbAdmin = new AdminDAO();
        Admin admin = dbAdmin.getAdmin(a.getUsername());
        
        request.setAttribute("adminName", admin.getName());
        request.setAttribute("listEmployee", listE);
        request.getRequestDispatcher("adminView/List.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
