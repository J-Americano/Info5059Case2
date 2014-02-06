/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * @author Jacob
 */
@WebServlet(name = "Case2Controller", urlPatterns = {"/C2Control"}, initParams = {@WebInitParam(name="WebPages", value="/WebPages.xml")})
public class Case2Controller extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()){ 
            String jsf = request.getParameter("jsf");
            
            QName qNode = new QName("webpage");
            QName qid = new QName("id");
            QName qRedirect = new QName("url");
            
            String fileName = getServletConfig().getInitParameter("WebPages");
            ServletContext application = getServletConfig().getServletContext();
            InputStream in = application.getResourceAsStream(fileName);
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            XMLStreamReader parser = inputFactory.createXMLStreamReader(in);
            
            while (true) {
                int event = parser.next();
                if (event == XMLStreamConstants.END_DOCUMENT) {
                    parser.close();
                    break;
                }

                if (event == XMLStreamConstants.START_ELEMENT) {
                    if (parser.getName().getLocalPart().equals(qNode.getLocalPart())) {
                       String nodeJSF = parser.getAttributeValue(null, qid.getLocalPart());
                       if(nodeJSF.equals(jsf)){
                           String recodeURL = parser.getAttributeValue(null, qRedirect.getLocalPart());
                           response.sendRedirect(response.encodeRedirectURL(recodeURL));
                           break;
                       }
                    }
                }
            }

            parser.close();
            
            out.close();
        }
        catch(XMLStreamException ex){
           //this is just here to stop the page from complaining. i could modify the lower portions to throw the XMLStreamException error but I don;t know what will happen so I wish to avoid that for now.
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
