/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeeCotroller;

import dal.EmployeeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Employee;

/**
 *
 * @author Do Duc Duong
 */
public class TimeKeepingController extends BaseAuthenticationController {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");
        EmployeeDAO dbEmployee = new EmployeeDAO();
        Employee e = dbEmployee.getEmployee(account.getUsername());
        
      
        
        LocalTime time = LocalTime.now();
        boolean inWorkingTime = false;
        if(e.getFrom().compareTo(time) == -1 && time.compareTo(e.getTo()) == -1) {
            inWorkingTime = true;
        }
        
        request.setAttribute("employeeName", e.getName());
        request.setAttribute("inWorkingTime", inWorkingTime);
        request.getRequestDispatcher("employeeView/TimeKeepingForm.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
