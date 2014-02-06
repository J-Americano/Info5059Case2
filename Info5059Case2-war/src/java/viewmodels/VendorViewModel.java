package viewmodels;


import case2dtos.VendorEJBDTO;
import case2ejbs.VendorBeanFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


/**
 * @author Jacob
 * A Sample backing bean that exposes 2 properties and one method
 * Revision 2
 */
@Named(value = "vendorViewModel")
@SessionScoped
public class VendorViewModel implements Serializable {
    
    
    public VendorViewModel() {
    }
    
    //@Inject
    //VendorModel model;
    
    @EJB
    private VendorBeanFacade vbf;
    
    private int vendorN;
    private String name;
    private String address;
    private String city;
    private String province;
    private String postalCode;
    private String phone;
    private String type;
    private String email;
    private String msg;
    private ArrayList<SelectItem> vendorNos = null;
    
    
    public String getName(){
        return (name);
    }
    
    public void setName(String uName){
        this.name = uName;
    }
    
    public String getAddress(){
        return (address);
    }
    
    public void setAddress(String uAddress){
        this.address = uAddress;
    }
    
    public String getCity(){
        return(city);
    }
    
    public void setCity(String uCity){
        this.city = uCity;
    }
    
    public String getProvince(){
        return (province);
    }
    
    public void setProvince(String uProvince){
        this.province = uProvince;
    }
    
    public String getPostal(){
        return (postalCode);
    }
    
    public void setPostal(String uPostal){
        this.postalCode = uPostal;
    }
    
    public String getPhone(){
        return (phone);
    }
    
    public void setPhone(String uPhone){
        this.phone = uPhone;
    }
    
    public String getType(){
        return (type);
    }
    
    public void setType(String uType){
        this.type = uType;
    }
    
    public String getEmail(){
        return (email);
    }
    
    public void setEmail(String uEmail){
        this.email = uEmail;
    }
    
    public String getMsg(){
        return (msg);
    }
    
    public void setMsg(String m){
        this.msg = m;
    }
    
    public int getVendorN(){
        return (vendorN);
    }
    
    public void setVendorN(int venN){
        this.vendorN = venN;
    }
    
    public void doSomething() {
        try{
            msg = "Hey " + name + " the time is now :"
                    + new java.util.Date().toString();
        }catch(Exception e) {
            msg = "Error: " + e.getMessage();
        }
    }
    
    public void addVendor(){
        vendorN = -1;
        try{
            //vendorN = model.dbAddVendor(name, address, city, province, postalCode, phone, address, email);
            VendorEJBDTO details = new VendorEJBDTO();
            details.setAddress1(address);
            details.setName(name);
            details.setCity(city);
            details.setProvince(province);
            details.setPhone(phone);
            details.setType(type);
            details.setEmail(email);
            details.setPostalCode(postalCode);
            vendorN = vbf.addVendor(details);
            if(vendorN > 0)
            msg = "Vendor " + vendorN + " added.";
            else
               msg = "Vendor not added - check log";
        } catch (Exception e) {
            msg = "Error: " + e.getMessage();
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
    
    public ArrayList<SelectItem> getVendorNos(){
        
        vendorNos = new ArrayList<>();
        
        try{
            List<Integer> nom = vbf.getVendornos();
            for(Integer i : nom){
                SelectItem item = new SelectItem(Integer.toString(i));
                vendorNos.add(item);
            }
        }catch(Exception e){
            System.out.println("Can't get vendorNom " + e.getMessage());
        }
        
        return vendorNos;
    }

    public String getVendor() {
        try{
            VendorEJBDTO details = vbf.getVendor(this.vendorN);
            this.address = details.getAddress1();
            this.city = details.getCity();
            this.postalCode = details.getProvince();
            this.phone = details.getPhone();
            this.type = details.getType();
            this.name = details.getName();
            this.email = details.getEmail();
            
            
        }catch(Exception e){
            System.out.println("Can't get vendors " + e.getMessage());
        }
        return name;
    }
}
