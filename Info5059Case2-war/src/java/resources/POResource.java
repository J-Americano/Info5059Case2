/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import case2dtos.PurchaseOrderEJBDTO;
import case2ejbs.POBeanFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.enterprise.context.RequestScoped;

/**
 * REST Web Service
 *
 * @author Jacob
 */
@Path("PO")
@RequestScoped
public class POResource {
    
    @EJB
    private POBeanFacade pobf;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of POResource
     */
    public POResource() {
    }

    @GET
    @Path("getVendorPOS/{vendorno}")
    @Produces("application/json")
    public List<Integer> getVendorPOS(@PathParam("vendorno") int vendorno){
        List<Integer> items = pobf.getVendorPOS(vendorno);
        return items;
    }
    
    @GET
    @Path("getPO/{pono}")
    @Produces("application/json")
    public PurchaseOrderEJBDTO getPO(@PathParam("pono") int pono){
        PurchaseOrderEJBDTO item = pobf.getPurchase(pono);
        return item;
    }
}
