package viewmodels;


import case2dtos.PurchaseOrderEJBDTO;
import case2dtos.PurchaseOrderLineitemEJBDTO;
import case2ejbs.POBeanFacade;
import dto.PurchaseOrderDTO;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;


import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Jacob
 */
@Named(value="generatorViewModel")
@SessionScoped
public class GeneratorViewModel implements Serializable {
    
    @EJB
    POBeanFacade pofb;
    
    PurchaseOrderEJBDTO poEJBDTO = new PurchaseOrderEJBDTO();
    
    @Inject
    VendorViewModel vendorView;
    @Inject
    ProductViewModel productView;
    
    private String msg;
    private boolean pickedVendor = false;
    private String vendorno;
    private String displayName;
    private String prodN;
    private String prodcd;
    private BigDecimal total;
    private boolean pickedItem = false;
    private String qtySelected;
    private ArrayList<PurchaseOrderDTO> itemsDTO = new ArrayList<>();

    public ArrayList<PurchaseOrderDTO> getItemsDTO() {
        return itemsDTO;
    }

    public void setItemsDTO(ArrayList<PurchaseOrderDTO> itemsDTO) {
        this.itemsDTO = itemsDTO;
    }
    
    public String getProdN() {
        return prodN;
    }

    public void setProdN(String prodN) {
        this.prodN = prodN;
    }

    public String getQtySelected() {
        return qtySelected;
    }

    public void setQtySelected(String qtySelected) {
        this.qtySelected = qtySelected;
    }
    
    public VendorViewModel getVendorView(){
        return vendorView;
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isPickedVendor() {
        return pickedVendor;
    }

    public void setPickedVendor(boolean pickedVendor) {
        this.pickedVendor = pickedVendor;
    }

    public String getVendorno() {
        return vendorno;
    }

    public void setVendorno(String vendorno) {
        this.vendorno = vendorno;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getProdcd() {
        return prodcd;
    }

    public void setProdcd(String prodcd) {
        this.prodcd = prodcd;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public boolean getPickedItem() {
        return pickedItem;
    }

    public void setPickedItem(boolean pickedItem) {
        this.pickedItem = pickedItem;
    }

    public void changeVendor(){
         msg = "";
        pickedVendor = true;
        vendorView.setVendorN(Integer.parseInt(vendorno));
        vendorView.getVendor();
        productView.setVendorno(Integer.parseInt(vendorno));
        displayName = vendorView.getName();
        
        prodcd = "";
        total = BigDecimal.ZERO;
        
        productView.getAllProductsForVendor(Integer.parseInt(vendorno));
        itemsDTO = new ArrayList<>();
        pickedItem = false;
    }
    
    public void changeProduct(){
        msg = "";
        pickedVendor = true;
        productView.setProdcd(prodcd);//string form jsf
        productView.getProducts(prodN,Integer.parseInt(vendorno));
        //displayName = productView.getProdname();
        productView.getAllProductsForVendor(Integer.parseInt(vendorno));
        
        
        //prodNum = -1;
        prodcd = "";
        total = BigDecimal.ZERO;
        //items = new ArrayList<>();
        pickedItem = true;
    }
    
    /*public ArrayList<SelectItem> listProductsForSelectedVendor(){

        ArrayList<SelectItem> productNames = new ArrayList();
        
        try{
            for(ProductEJBDTO i : items){
                SelectItem item = new SelectItem(i.getProdname());
                productNames.add(item);
            }
            
        }catch (Exception e){
            System.out.println("Can't create list of items " + e );
        }
        return productNames;
    }*/
    
    public void poAdd()
    {
       try{
            poEJBDTO.setVendorno(vendorView.getVendorN());
            poEJBDTO.setTotal(this.total);
            ArrayList<PurchaseOrderLineitemEJBDTO> newItems = new ArrayList<>();
            for(int i = 0; i != this.itemsDTO.size(); i++){
                PurchaseOrderLineitemEJBDTO poli = new PurchaseOrderLineitemEJBDTO();
                poli.setProdcd(itemsDTO.get(i).getProdcd());
                poli.setExt(itemsDTO.get(i).getExt());
                poli.setPrice(itemsDTO.get(i).getPrice());
                poli.setQty(itemsDTO.get(i).getQty());
                
                newItems.add(poli);
            }
            poEJBDTO.setItems(newItems);
            
            msg = "Purchase Order # " + pofb.addPO(poEJBDTO) + " Added!";
            pickedItem = false;
            itemsDTO = new ArrayList<>();
        } catch(Exception e){
            msg = "Error Occurred: " + e.getMessage();
        }
    }
    
     public void addSelectedItemToPO(){
        
        pickedItem = true;
        productView.getProducts(prodN, Integer.parseInt(vendorno));
        PurchaseOrderDTO prod = new PurchaseOrderDTO();
        for(int i = 0; i < this.itemsDTO.size(); i++){
        if (this.itemsDTO.get(i).getProdcd().equalsIgnoreCase(prodcd)){
            total = total.subtract(this.itemsDTO.get(i).getExt());
         itemsDTO.remove(i);
        }
      }
        prod.setProdname(productView.getProdname());
        prod.setProdcd(productView.getProdcd());
        if("EOQ".equals(qtySelected)){
            prod.setQty(productView.getEoq());
        }
        else{
            prod.setQty(Integer.parseInt(qtySelected));
        }
        prod.setPrice(productView.getCostprice());
        prod.setExt(prod.getPrice().multiply(new BigDecimal(prod.getQty()+"")));
        
        
        
        itemsDTO.add(prod);
        total = total.add(prod.getExt());
    }
        
}
    
    /*public void addSelectedItemToPO(){
        for(int i = 0; i < orderedItems.size(); i++){
        if (orderedItems.get(i).getProdcd().equalsIgnoreCase(prodcd)){
         total -= orderedItems.get(i).getExt();
         orderedItems.remove(i);
        }
       }
        if(!"0".equals(qtySelected)){
            for(int i = 0; i < items.size(); i++){
            if (items.get(i).getProdcd().equalsIgnoreCase(prodcd)){
              ProductEJBDTO item = items.get(i);
              if("EOQ".equals(qtySelected)){
                  item.setQty(item.getEoq());
             }else{
                  item.setQty(Integer.parseInt(qtySelected));
              }
              total += item.getPrice() * item.getQty();
              item.setExt(item.getPrice() * item.getQty());
              orderedItems.add(item);
            }
           }
        }
        
        pickedItem = orderedItems.size()>0;
    }
    
    public void addPurchaseOrder(){
        try{
            msg = "Purchase Order " + model.dbaddOrder(vendorno,total,dt, items) + " has been added.";
            pickedVendor = false;
            pickedItem = false;
        } catch (Exception e){
            msg = e.getMessage();
        }
    }*/
