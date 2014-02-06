package case2ejbs;

import case2dtos.VendorEJBDTO;
import case2models.VendorsModel;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Evan
 *  VendorFacadeBean - stateless EJB to act as a facade to the JPA Vendor Model
 *  Create - 10/18/2013
 *  Revised - Original Code
 */
@Stateless
@LocalBean
public class VendorBeanFacade {

    @PersistenceContext
    private EntityManager em;

    public int addVendor(VendorEJBDTO ven) {

        VendorsModel vm;
        int retVal = -1;

        try {
            vm = new VendorsModel(null, ven.getAddress1(), ven.getCity(),
                    ven.getProvince(), ven.getPostalCode(),
                    ven.getPhone(), ven.getType(), ven.getName(),
                    ven.getEmail());
            em.persist(vm);
            em.flush();
            retVal = vm.getVendorno().intValue();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return retVal;
    }
    
    public List<Integer> getVendornos()
    {
      List<Integer> nos = null;
      try
      {
          Query qry = em.createNamedQuery("VendorsModel.findAllVendorNos");
          nos = qry.getResultList();
      } catch(Exception e)
      {
          System.out.println("Error getting Vendornos from Facade -" + e.getMessage());
      }
      return nos;
    }
    
    public VendorEJBDTO getVendor(int vendorno) {

     VendorsModel vm;
     VendorEJBDTO dto = new VendorEJBDTO();
      try
      {
         vm = em.find(VendorsModel.class, vendorno);
         dto.setAddress1(vm.getAddress1());
         dto.setCity(vm.getCity());
         dto.setProvince(vm.getProvince());
         dto.setPostalCode(vm.getPostalcode());
         dto.setPhone(vm.getPhone());
         dto.setType(vm.getVendortype());
         dto.setName(vm.getName());
         dto.setEmail(vm.getEmail());
         em.persist(vm);
         em.flush();
      } catch(Exception e)
      {
          System.out.println("Error getting Vendornos from Facade -" + e.getMessage());
      }
      return dto;
    }
    
}
 