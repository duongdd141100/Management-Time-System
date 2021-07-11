/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeeCotroller;

import dal.AbsentDAO;
import dal.EmployeeDAO;
import dal.RequestDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Employee;
import model.Request;

/**
 *
 * @author Do Duc Duong
 */
public class SendRequestController extends BaseAuthenticationController {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SendRequestController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SendRequestController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDAO dbRequest = new RequestDAO();
        ArrayList<Request> listRequest = dbRequest.getRequest();
        Account account = (Account) request.getSession().getAttribute("account");
        EmployeeDAO dbEmployee = new EmployeeDAO();
        Employee e = dbEmployee.getEmployee(account.getUsername());
        request.setAttribute("employeeName", e.getName());
        
        request.setAttribute("isSubmit", false);
        request.setAttribute("listRequest", listRequest);
        request.getRequestDispatcher("employeeView/SendRequestForm.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account a = (Account) request.getSession().getAttribute("account");
        String username = a.getUsername();
        String date = request.getParameter("date");
        int requestId = Integer.parseInt(request.getParameter("requestId"));
        String reason = request.getParameter("reason");
        AbsentDAO dbAbsent = new AbsentDAO();
        dbAbsent.insert(requestId, date, reason, username);
        RequestDAO dbRequest = new RequestDAO();
        ArrayList<Request> listRequest = dbRequest.getRequest();
        request.setAttribute("isSubmit", true);
        request.setAttribute("listRequest", listRequest);
        request.getRequestDispatcher("employeeView/SendRequestForm.jsp").forward(request, response);
        
    }

}
