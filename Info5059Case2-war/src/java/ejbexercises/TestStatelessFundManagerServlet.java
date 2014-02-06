/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbexercises;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Jacob
 */
@WebServlet(name = "TestStatelessFundManagerServlet", urlPatterns = ("/TestStateless"))
public class TestStatelessFundManagerServlet extends HttpServlet {
    
    @EJB(name = "sfm")
    private StatelessFundManagerBean sfm;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    try{
        double balance = 1200.00;
        double amount = 1200.00;
        balance = (double) sfm.addFunds(balance, amount);
        out.println("1st balance = " + balance);
        amount = 100.00;
        balance = (double) sfm.addFunds(balance, amount);
        out.println("2nd balance = " + balance);
        amount = 1200.00;
        balance = (double) sfm.withdrawFunds(balance, amount);
        out.println("final balance = " + balance);
    }catch(Exception e){
        e.getMessage();
    }
    out.close();
}
    
    StatelessFundManagerBean statelessFundManagerBean = lookupStatelessFundManagerBeanBean();

    private StatelessFundManagerBean lookupStatelessFundManagerBeanBean() {
        try {
            Context c = new InitialContext();
            return (StatelessFundManagerBean) c.lookup("java:global/Info5059Case2/Info5059Case2-ejb/StatelessFundManagerBean!ejbexercises.StatelessFundManagerBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
   
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
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
