/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeeCotroller;

import dal.AccountDAO;
import dal.GroupDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author Do Duc Duong
 */
public class LoginController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        if (request.getSession().getAttribute("account") != null) {
            Account a = (Account) request.getSession().getAttribute("account");
            int groupId = a.getGroupId();
            
            GroupDAO dbGroup = new GroupDAO();
            String groupName = dbGroup.getGroupName(groupId);
            if(groupName.equals("Employee")) {
                response.sendRedirect("time-keeping");
            } else {
                response.sendRedirect("manage-employee");
            }
            return;
        }
        request.setAttribute("isWrong", false);
        request.getRequestDispatcher("employeeView/LoginForm.jsp").forward(request, response);
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

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        AccountDAO dbAccount = new AccountDAO();
        Account account = new Account();
        account = dbAccount.getAccount(username, password);
        if (account != null) {
            GroupDAO dbGroup = new GroupDAO();
            String groupName = dbGroup.getGroupName(account.getGroupId());
            request.getSession().setAttribute("account", account);
            if (groupName.equals("Employee")) {
                DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm:ss");
                Cookie date = new Cookie("date", formatDate.format(now));
                Cookie time = new Cookie("timeStart", formatTime.format(now));
                response.addCookie(time);
                response.addCookie(date);
                response.sendRedirect("time-keeping");
            }else {
                response.sendRedirect("manage-employee");
            }

        } else {
//            response.getWriter().print("<script>alert('Username or Password is invalid!')</script>");
            request.setAttribute("isWrong", true);
            request.getRequestDispatcher("employeeView/LoginForm.jsp").forward(request, response);
        }
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
