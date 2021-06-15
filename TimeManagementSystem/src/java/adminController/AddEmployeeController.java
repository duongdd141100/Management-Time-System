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
public class AddEmployeeController extends BaseAuthenticationController {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WorkTimeDAO dbWorktime = new WorkTimeDAO();
        ArrayList<WorkTime> listTime = dbWorktime.getWorkTime();
        EmployeeDAO dbEmp = new EmployeeDAO();
        ArrayList<Employee> list = dbEmp.getEmployee();
        
        request.setAttribute("list", list);
        request.setAttribute("listTime", listTime);
        request.getRequestDispatcher("adminView/add-employee.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
