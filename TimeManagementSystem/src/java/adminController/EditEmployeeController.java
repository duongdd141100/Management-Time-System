/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminController;

import dal.EmployeeDAO;
import dal.WorkTimeDAO;
import employeeCotroller.BaseAuthenticationController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Employee;
import model.WorkTime;

/**
 *
 * @author Do Duc Duong
 */
public class EditEmployeeController extends BaseAuthenticationController {

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
        String username = request.getParameter("username");
        EmployeeDAO dbE = new EmployeeDAO();
        Employee e = dbE.getEmployee(username);
        WorkTimeDAO dbTime = new WorkTimeDAO();
        ArrayList<WorkTime> listTime = dbTime.getWorkTime();
        int timeId = dbE.getTimeId(username);
        
        
        request.setAttribute("timeId", timeId);
        request.setAttribute("listTime", listTime);
        request.setAttribute("employee", e);
        request.getRequestDispatcher("adminView/Edit.jsp").forward(request, response);
        
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String dob = request.getParameter("dob");
        boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        int timeId = Integer.parseInt(request.getParameter("timeId"));
        String username = request.getParameter("username");
        EmployeeDAO dbE = new EmployeeDAO();
        dbE.updateEmployee(username, name, email, dob, gender,timeId);
        response.sendRedirect("manage-employee");
    }

}
