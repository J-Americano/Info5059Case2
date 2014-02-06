/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 * @author Jacob
 */
import java.io.Serializable;
import java.math.BigDecimal;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

@Named(value="purchaseOrderDTO")
@SessionScoped
public class PurchaseOrderDTO implements Serializable{
    
    private String prodcd;
    private String prodname;
    private int qty;
    private BigDecimal price;
    private BigDecimal ext;
    private int eoq;

    public int getEoq() {
        return eoq;
    }

    public void setEoq(int eoq) {
        this.eoq = eoq;
    }
    
    public String getProdcd() {
        return prodcd;
    }

    public void setProdcd(String prodcd) {
        this.prodcd = prodcd;
    }

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getExt() {
        return ext;
    }

    public void setExt(BigDecimal ext) {
        this.ext = ext;
    }
}
