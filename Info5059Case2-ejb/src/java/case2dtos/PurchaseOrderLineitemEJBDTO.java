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
public class PurchaseOrderLineitemEJBDTO {
    
    private Integer lineId;
    private Integer poNumber;
    private BigDecimal price;
    private String prodcd;
    private int qty;
    private BigDecimal ext;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public BigDecimal getExt() {
        return ext;
    }

    public void setExt(BigDecimal ext) {
        this.ext = ext;
    }

    public int getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(Integer poNumber) {
        this.poNumber = poNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProdcd() {
        return prodcd;
    }

    public void setProdcd(String prodcd) {
        this.prodcd = prodcd;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
