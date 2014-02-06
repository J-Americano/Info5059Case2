/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package case2dtos;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Jacob
 */
public class ProductEJBDTO implements Serializable{
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
    private Serializable qrcode;

    public ProductEJBDTO(){}
    
    public Serializable getQrcode() {
        return qrcode;
    }

    public void setQrcode(Serializable inValue) {
        this.qrcode = inValue;
    }
    
    public String getProdcd() {
        return prodcd;
    }

    public void setProdcd(String inValue) {
        this.prodcd = inValue;
    }

    public int getVendorno() {
        return vendorno;
    }

    public void setVendorno(int inValue) {
        this.vendorno = inValue;
    }

    public String getVendorsku() {
        return vendorsku;
    }

    public void setVendorsku(String inValue) {
        this.vendorsku = inValue;
    }

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String inValue) {
        this.prodname = inValue;
    }

    public BigDecimal getCostprice() {
        return costprice;
    }

    public void setCostprice(BigDecimal inValue) {
        this.costprice = inValue;
    }

    public BigDecimal getMsrp() {
        return msrp;
    }

    public void setMsrp(BigDecimal inValue) {
        this.msrp = inValue;
    }

    public int getRop() {
        return rop;
    }

    public void setRop(int inValue) {
        this.rop = inValue;
    }

    public int getQoh() {
        return qoh;
    }

    public void setQoh(int inValue) {
        this.qoh = inValue;
    }

    public int getEoq() {
        return eoq;
    }

    public void setEoq(int inValue) {
        this.eoq = inValue;
    }

    public int getQoo() {
        return qoo;
    }

    public void setQoo(int inValue) {
        this.qoo = inValue;
    }

}

