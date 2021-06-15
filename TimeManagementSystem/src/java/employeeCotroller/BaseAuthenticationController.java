/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeeCotroller;

import dal.FeatureDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Feature;

/**
 *
 * @author Do Duc Duong
 */
@WebServlet(name = "BaseAuthenticationController", urlPatterns = {"/BaseAuthenticationController"})
public abstract class BaseAuthenticationController extends HttpServlet {

    private boolean isValidAuthentication(HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("account");
        if(account == null){ 
            return false;
        }
        String username = account.getUsername();
        FeatureDAO dbF = new FeatureDAO();
        ArrayList<Feature> listFeature = dbF.getFeatures(username);
        String url = request.getServletPath();
        for (int i = 0; i < listFeature.size(); i++) {
            if (listFeature.get(i).getUrl().equals(url)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (isValidAuthentication(request)) {
            processGet(request, response);
        } else {
            response.sendRedirect("login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (isValidAuthentication(request)) {
            processPost(request, response);
        } else {
            response.sendRedirect("login");
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

    protected abstract void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    protected abstract void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

}
