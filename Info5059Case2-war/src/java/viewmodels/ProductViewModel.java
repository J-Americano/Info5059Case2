/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viewmodels;
import case2dtos.ProductEJBDTO;
import case2ejbs.ProductBeanFacade;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
/**
 * @author Jacob
 */
@Named(value = "productViewModel")
@SessionScoped
public class ProductViewModel implements Serializable{
    
    public ProductViewModel(){}
    
    //@Inject
    //ProductModel model; 
    
    
    @EJB
    private ProductBeanFacade pbf;
    
    private String prodcd;
    private int vendorno;
    private String vendorsku;
    private String prodname;
    private BigDecimal costprice;
    private BigDecimal msrp;
    private int rop;
    private int qoh;
    private int eoq;
    private int qoo;
    private String msg;
    private String qrcode;
    private byte[] qrcodebin;
    private ArrayList<String> vendorProducts;

    public ArrayList<String> getVendorProducts() {
        return vendorProducts;
    }

    public void setVendorProducts(ArrayList<String> vendorProducts) {
        this.vendorProducts = vendorProducts;
    }    
    
    public byte[] getQrcodebin(){
        byte[] qrcodebinV = QRCode.from(this.qrcode).to(ImageType.PNG).stream().toByteArray();
        return qrcodebinV;
    }
    
    public void setQrcodebin(byte[] inValue){
        this.qrcodebin = inValue;
    }
    
    public String getQrcode(){
        return qrcode;
    }
    
    public void setQrcode(String inValue){
        this.qrcode = inValue;
    }

    public String getProdcd(){
        return prodcd;
    }

    public void setProdcd(String inValue){
        this.prodcd = inValue;
    }

    public int getVendorno(){
        return vendorno;
    }

    public void setVendorno(int uvendorno){
        this.vendorno = uvendorno;
    }

    public String getVendorsku(){
        return vendorsku;
    }

    public void setVendorsku(String uvendorsku){
        this.vendorsku = uvendorsku;
    }

    public String getProdname(){
        return prodname;
    }

    public void setProdname(String uprodname){
        this.prodname = uprodname;
    }

    public BigDecimal getCostprice(){
        return costprice;
    }

    public void setCostprice(BigDecimal ucostprice){
        this.costprice = ucostprice;
    }

    public BigDecimal getMsrp(){
        return msrp;
    }

    public void setMsrp(BigDecimal umsrp){
        this.msrp = umsrp;
    }

    public int getRop(){
        return rop;
    }

    public void setRop(int urop){
        this.rop = urop;
    }

    public int getQoh(){
        return qoh;
    }

    public void setQoh(int uqoh){
        this.qoh = uqoh;
    }

    public int getEoq(){
        return eoq;
    }

    public void setEoq(int ueoq){
        this.eoq = ueoq;
    }

    public int getQoo(){
        return qoo;
    }

    public void setQoo(int uqoo){
        this.qoo = uqoo;
    }

    public String getMsg(){
        return msg;
    }

    public void setMsg(String umsg){
        this.msg = umsg;
    }
    
    
    
    public void addProduct(){
        
         boolean add = false;
        try{
            
            //Serialize the data
            ProductEJBDTO details = new ProductEJBDTO();
            details.setProdcd(prodcd);
            details.setVendorno(vendorno);
            details.setVendorsku(vendorsku);
            details.setProdname(prodname);
            details.setCostprice(costprice);
            details.setMsrp(msrp);
            details.setRop(rop);
            details.setEoq(eoq);
            details.setQoh(qoh);
            details.setQoo(qoo);
            qrcodebin = QRCode.from(qrcode).to(ImageType.PNG).stream().toByteArray();
            details.setQrcode(qrcodebin);
            
            add = pbf.addProduct(details); //call to facade
            if (add == true){
                msg = "Product " + prodname + " Added!";
                
            } else {
                msg = "Product not added - check log";
            }
        }catch(Exception e){
            msg = e.getMessage();
        }
        
    }
    
    /*
    public void addProduct(){
     try{
         model.addProduct(vendorno, prodcd,vendorsku,prodname,costprice,msrp,rop,qoh,eoq,qoo);
         msg = "Product " + prodcd + " added!";
       
         }catch(Exception e){
        msg = "Error: " + e.getMessage();
     }
    }
    */
    public void getAllProductsForVendor(int vendorno){
        //ProductEJBDTO vendorProducts = new ProductEJBDTO();
        try{
                //setVendorProducts(pbf.getAllProductsForVendor(vendorno));
                vendorProducts = pbf.getAllProductsForVendor(vendorno);
           }
      catch(Exception e)
     {
        msg = "Error: " + e.getMessage();
     }
     
    }
    
    public void getProducts(String prodN, int vendorNo){
        ProductEJBDTO detail = pbf.getProducts(prodN, vendorNo);
        
        try{
            this.prodcd = detail.getProdcd();
            this.vendorno = detail.getVendorno();
            this.vendorsku = detail.getVendorsku();
            this.prodname = detail.getProdname();
            this.costprice = detail.getCostprice();
            this.msrp = detail.getMsrp();
            this.rop = detail.getRop();
            this.eoq = detail.getEoq();
            this.qoh = detail.getQoh();
            this.qoo = detail.getQoo();
            
        } catch (Exception e){
            msg = e.getMessage();
        }
    }
    
}
