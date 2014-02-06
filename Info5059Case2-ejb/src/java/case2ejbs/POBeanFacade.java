/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package case2ejbs;

import case2dtos.PurchaseOrderEJBDTO;
import case2dtos.PurchaseOrderLineitemEJBDTO;
import case2models.ProductsModel;
import case2models.PurchaseOrderLineitemsModel;
import case2models.PurchaseOrdersModel;
import case2models.VendorsModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Jacob
 */
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
@Stateless
@LocalBean
public class POBeanFacade {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @PersistenceContext
    private EntityManager em;
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public int addPO (PurchaseOrderEJBDTO poDTO)
    {
        
        PurchaseOrdersModel pm;
        int poRowID = -1;
        Date poDate = new java.util.Date();
        
        try{
            //vm = em.find(VendorsModel.class, poDTO.getVendorno());
            pm = new PurchaseOrdersModel(null, poDTO.getTotal(), poDate);
            pm.setVendorno(poDTO.getVendorno());
            em.persist(pm);
            em.flush();
            
            for(PurchaseOrderLineitemEJBDTO line : poDTO.getItems()){
                if(line.getQty() != 0)
                    addPOLine(line, pm);
        }
            poRowID = pm.getPonumber().intValue();
            
    }catch(Exception e){
        System.out.println(e.getMessage());
    }
        return poRowID;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addPOLine(PurchaseOrderLineitemEJBDTO line, PurchaseOrdersModel pm)
    {
        PurchaseOrderLineitemsModel polm;
               
        try{
            polm = new PurchaseOrderLineitemsModel(0, line.getProdcd(), line.getQty(), line.getPrice());
            polm.setPonumber(pm.getPonumber());
            em.persist(polm);
            em.flush();
        }catch( Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public List<Integer> getVendorPOS(int vendorno){
        List<Integer> nos = null;
        try{
            Query qry = em.createNamedQuery("PurchaseOrdersModel.findAllVendorPOS").setParameter("vendorno", vendorno);
            nos = qry.getResultList();
        }catch(Exception e){
            System.out.println("Error getting Purchase Orders for Vendor " + vendorno + " - " + e.getMessage());
        }
        return nos;
    }
    
    public PurchaseOrderEJBDTO getPurchase(int pono){
        PurchaseOrderEJBDTO poNum = new PurchaseOrderEJBDTO();
        try{
            PurchaseOrdersModel pom = em.find(PurchaseOrdersModel.class, pono);
            poNum.setAmount(pom.getAmount());
            poNum.setPoDate(SimpleDateFormat.getDateInstance().format(pom.getPodate()));
            poNum.setPoNumber(pom.getPonumber());
            poNum.setTotal(pom.getAmount());
            
            Query qr = em.createNamedQuery("PurchaseOrderLineitemsModel.findByPONumber").setParameter("ponumber", pom.getPonumber());
            
            List<PurchaseOrderLineitemsModel> mItems = qr.getResultList();
            
            ArrayList<PurchaseOrderLineitemEJBDTO> ejbItems = new ArrayList<>();
            
            for(int i = 0; i != mItems.size(); i++){
                PurchaseOrderLineitemEJBDTO item = new PurchaseOrderLineitemEJBDTO();
                item.setLineId(mItems.get(i).getLineid());
                item.setPrice(mItems.get(i).getPrice());
                item.setProdcd(mItems.get(i).getProdcd());
                item.setQty(mItems.get(i).getQty());
                item.setPoNumber(mItems.get(i).getPonumber());
                
                ProductsModel pm = em.find(ProductsModel.class, item.getProdcd());
                item.setName(pm.getProdnam());
                ejbItems.add(item);
            }
            poNum.setItems(ejbItems);            
            
        }catch(Exception e){
            System.out.println("Error getting Purchase");
        }
        return poNum;
    }
} 
