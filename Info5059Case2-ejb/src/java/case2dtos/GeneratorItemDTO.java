/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package case2dtos;

import java.math.BigDecimal;

/**
 *
 * @author Jacob
 */
public class GeneratorItemDTO {
    
    private String prodcd;
    private String prodname;
    private int qty;
    private BigDecimal price;
    private BigDecimal ext;
    
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

