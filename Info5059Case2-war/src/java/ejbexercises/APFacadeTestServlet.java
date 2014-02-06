/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbexercises;

import case2dtos.VendorEJBDTO;
import case2ejbs.VendorBeanFacade;
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
@WebServlet(name = "APFacadeTestServlet", urlPatterns = ("/APFacade"))
public class APFacadeTestServlet extends HttpServlet{
    
    @EJB(name = "apf")
    private VendorBeanFacade apf;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        
    try(PrintWriter out = response.getWriter()){
        VendorEJBDTO vd = new VendorEJBDTO();
        vd.setAddress1("123 Here");
        vd.setCity("Nowhere");
        vd.setEmail("apdept@gadgethut.com");
        vd.setName("Gadget Hut");
        vd.setPhone("555-5555");
        vd.setPostalCode("n1n-1n1");
        vd.setProvince("ON");
        vd.setType("trusted");
        int newID = apf.addVendor(vd);
        out.println("New vendor row added via Facade - " + newID);
    }
}
    
    VendorBeanFacade vendorBeanFacade = lookupVendorBeanFacadeBean();

    private VendorBeanFacade lookupVendorBeanFacadeBean() {
        try {
            Context c = new InitialContext();
            return (VendorBeanFacade) c.lookup("java:global/Info5059Case2/Info5059Case2-ejb/VendorBeanFacade!case2ejbs.VendorBeanFacade");
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
