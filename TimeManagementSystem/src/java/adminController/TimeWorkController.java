/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminController;

import dal.WorkTimeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.WorkTime;

/**
 *
 * @author Do Duc Duong
 */
public class TimeWorkController extends HttpServlet {

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
        WorkTimeDAO dbWorkTime = new WorkTimeDAO();
        ArrayList<WorkTime> listWorkTime = dbWorkTime.getWorkTime();

        request.setAttribute("listWorkTime", listWorkTime);
        request.getRequestDispatcher("adminView/WorkTime.jsp").forward(request, response);
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
        int id = Integer.parseInt(request.getParameter("id"));
        WorkTimeDAO dbWorkTime = new WorkTimeDAO();
        ArrayList<WorkTime> list = dbWorkTime.getWorkTime();
        boolean isAdd = true;
        if (request.getParameter("from") == null) {
            dbWorkTime.deleteRecord(id);
            response.getWriter().print(id);
        } else {
            String from = request.getParameter("from");
            String to = request.getParameter("to");
            if(request.getParameter("isAdd") == null) {
                dbWorkTime.updateRecord(id, from, to);
                response.getWriter().print(id + " " + from + " " + to);
            } else {
                for(int i = 0; i < list.size(); i++) {
                    if(list.get(i).getId() == id) {
                        isAdd = false;
                        break;
                    }
                }
                if(isAdd) {
                    dbWorkTime.insertRecord(id, from, to);
                } else {
                    request.setAttribute("isAdd", isAdd);
                }
            }
            request.setAttribute("listWorkTime", dbWorkTime.getWorkTime());
        request.getRequestDispatcher("adminView/WorkTime.jsp").forward(request, response);

        }

        response.sendRedirect("time-work");
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
