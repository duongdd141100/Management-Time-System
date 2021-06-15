/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminController;

import dal.EmployeeDAO;
import employeeCotroller.BaseAuthenticationController;
import employeeCotroller.JavaMailUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Employee;

/**
 *
 * @author Do Duc Duong
 */
public class SendEmailController extends BaseAuthenticationController {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        EmployeeDAO dbE = new EmployeeDAO();
        Employee employee = dbE.getEmployee(username);
        
        request.setAttribute("employee", employee);
        request.getRequestDispatcher("adminView/SendEmail.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");
        JavaMailUtil jmu = new JavaMailUtil();
        try {
            jmu.sendEmail(email, subject, message);
        } catch (Exception ex) {
            Logger.getLogger(SendEmailController.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("manage-employee");
    }

}
