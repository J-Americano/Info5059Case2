/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package case2dtos;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author Jacob
 */
public class PurchaseOrderEJBDTO {
    
    private int vendorno;
    private Integer poNumber;
    private BigDecimal total;
    private String poDate;
    private BigDecimal amount;
    private ArrayList<PurchaseOrderLineitemEJBDTO> items;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(Integer poNumber) {
        this.poNumber = poNumber;
    }

    public String getPoDate() {
        return poDate;
    }

    public void setPoDate(String poDate) {
        this.poDate = poDate;
    }

    public ArrayList<PurchaseOrderLineitemEJBDTO> getItems() {
        return items;
    }

    public void setItems(ArrayList<PurchaseOrderLineitemEJBDTO> items) {
        this.items = items;
    }

    public int getVendorno() {
        return vendorno;
    }

    public void setVendorno(int vendorno) {
        this.vendorno = vendorno;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }  
}

