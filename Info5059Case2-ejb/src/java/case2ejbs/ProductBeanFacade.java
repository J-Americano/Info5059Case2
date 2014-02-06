package case2ejbs;

import case2dtos.ProductEJBDTO;
import case2models.ProductsModel;
import case2models.VendorsModel;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Jacob
 */
@Stateless
@LocalBean
public class ProductBeanFacade {
    @PersistenceContext
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public boolean addProduct (ProductEJBDTO prod)
    {
        ProductsModel pm;
        VendorsModel vm;
        boolean addOk = false;
        
        try{
            pm = new ProductsModel(prod.getProdcd(), prod.getVendorsku(),
                    prod.getProdname(),prod.getCostprice(), prod.getMsrp(),
                    prod.getRop(), prod.getEoq(), prod.getQoh(), prod.getQoo());
            vm = em.find(VendorsModel.class, prod.getVendorno());
            pm.setVendorno(vm);
            pm.setQrcode(prod.getQrcode());
            em.persist(pm);
            em.flush();
            addOk = true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        
        return addOk;
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
    public ArrayList<String> getAllProductsForVendor(int vendorno)
    {
      ArrayList<String> products = new ArrayList<>();
      List<String> prods = null;
      VendorsModel vm;
      try
      {
          vm = em.find(VendorsModel.class, vendorno);
          Query qry = em.createNamedQuery("ProductsModel.findByVendorno");
          qry.setParameter("vendorno", vm);
          prods = qry.getResultList();
          
          for(int i = 0; i != prods.size(); i++)
          {
              products.add(prods.get(i));
          }
          
      } catch(Exception e)
      {
          System.out.println("Error getting AllProductsForVendor from Facade -" + e.getMessage());
      }
      return products;
    }
    
    public ProductEJBDTO getProducts(String prodN, int vendorNo){
        ProductEJBDTO detail = new ProductEJBDTO();
        try{
            VendorsModel vm = em.find(VendorsModel.class,vendorNo);
            //prod = em.createNamedQuery("ProductModel.findByProdnameAndVendorNo").setParameter("vendno", vm).setParameter("prodname",prodName).getResultList();
            
            Query qry = em.createNamedQuery(("ProductsModel.findByProdnameAndVendorNo"));
            qry.setParameter("vendorno", vm);
            qry.setParameter("prodnam",prodN);
            List<ProductsModel> test = qry.getResultList();
            
            detail.setProdcd(test.get(0).getProdcd());
            detail.setProdname(test.get(0).getProdnam());
            detail.setVendorno(test.get(0).getVendorno().getVendorno());
            detail.setVendorsku(test.get(0).getVensku());
            detail.setCostprice(test.get(0).getCostprice());
            detail.setEoq(test.get(0).getEoq());
            detail.setMsrp(test.get(0).getMsrp());
            detail.setQoh(test.get(0).getQoh());
            detail.setQoo(test.get(0).getQoo());
            detail.setRop(test.get(0).getRop());

        } catch (Exception e){
            System.out.println("Other issue " + e.getMessage());
        }
        return detail;
    }
}
