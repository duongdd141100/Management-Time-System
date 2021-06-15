/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeeCotroller;

import dal.AccountDAO;
import dal.EmployeeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class ForgetPasswordController extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EmployeeDAO dbEmp = new EmployeeDAO();
        ArrayList<Employee> listE = dbEmp.getEmployee();
        String[] emails = new String[listE.size()];
        for(int i = 0; i < listE.size(); i++) {
            emails[i] = listE.get(i).getEmail();
        }
//        
//        request.setAttribute("emails", String.join("', '", emails));
        request.setAttribute("emails", emails);
//        request.setAttribute("listEmployee", listE

        request.getRequestDispatcher("employeeView/ForgetPasswordForm.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("pass") != null) {
            String password = request.getParameter("pass");
            String email = request.getParameter("email");
            AccountDAO dbAcc = new AccountDAO();
            dbAcc.updatePassword(email, password);
            
            response.sendRedirect("login");
            return;
        }
        String email = request.getParameter("email");
        JavaMailUtil jmu = new JavaMailUtil();
        int code = 0;
        try {
            code = jmu.sendEmail(email);
        } catch (Exception ex) {
            Logger.getLogger(ForgetPasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("email", email);
        request.setAttribute("code", code);
        request.getRequestDispatcher("employeeView/ResetPasswordForm.jsp").forward(request, response);
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
