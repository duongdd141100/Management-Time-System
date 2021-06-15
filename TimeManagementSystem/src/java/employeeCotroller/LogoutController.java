/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeeCotroller;

import dal.WorkTimeReportDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author Do Duc Duong
 */
public class LogoutController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

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
        Account a = (Account) request.getSession().getAttribute("account");
        if(a == null) {
            response.sendRedirect("login");
        }
        String username = a.getUsername();
        String from = "";
        String date = "";
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        String to = formatTime.format(now);
        
        
        Cookie[] cookies = request.getCookies();
        for(int i = 0; i < cookies.length; i++) {
            if(cookies[i].getName().equals("timeStart")) {
                from = cookies[i].getValue();
            }
            if(cookies[i].getName().equals("date")) {
                date = cookies[i].getValue();
            }
            cookies[i].setValue("");
            cookies[i].setMaxAge(0);
            response.addCookie(cookies[i]);
            
        }
        WorkTimeReportDAO dbReport = new WorkTimeReportDAO();
        dbReport.insertReport(username, date, from, to);

        
        response.sendRedirect("login");
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
        processRequest(request, response);
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
