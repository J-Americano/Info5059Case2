/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package case2models;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jacob
 */
@Entity
@Table(name = "PRODUCTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductsModel.findByProdnameAndVendorNo", query = "SELECT p FROM ProductsModel p WHERE p.prodnam = :prodnam AND p.vendorno = :vendorno"),
    @NamedQuery(name = "ProductsModel.findAllProdNames", query = "SELECT p.prodnam FROM ProductsModel p WHERE p.vendorno = :vendorno"),
    @NamedQuery(name = "ProductsModel.findAll", query = "SELECT p FROM ProductsModel p"),
    @NamedQuery(name = "ProductsModel.findByProdcd", query = "SELECT p FROM ProductsModel p WHERE p.prodcd = :prodcd"),
    @NamedQuery(name = "ProductsModel.findByVensku", query = "SELECT p FROM ProductsModel p WHERE p.vensku = :vensku"),
    @NamedQuery(name = "ProductsModel.findByProdnam", query = "SELECT p FROM ProductsModel p WHERE p.prodnam = :prodnam"),
    @NamedQuery(name = "ProductsModel.findByCostprice", query = "SELECT p FROM ProductsModel p WHERE p.costprice = :costprice"),
    @NamedQuery(name = "ProductsModel.findByMsrp", query = "SELECT p FROM ProductsModel p WHERE p.msrp = :msrp"),
    @NamedQuery(name = "ProductsModel.findByRop", query = "SELECT p FROM ProductsModel p WHERE p.rop = :rop"),
    @NamedQuery(name = "ProductsModel.findByEoq", query = "SELECT p FROM ProductsModel p WHERE p.eoq = :eoq"),
    @NamedQuery(name = "ProductsModel.findByQoh", query = "SELECT p FROM ProductsModel p WHERE p.qoh = :qoh"),
    @NamedQuery(name = "ProductsModel.findByVendorno", query = "SELECT p.prodnam FROM ProductsModel p WHERE p.vendorno = :vendorno"),  
    @NamedQuery(name = "ProductsModel.findByQoo", query = "SELECT p FROM ProductsModel p WHERE p.qoo = :qoo")})
public class ProductsModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "PRODCD")
    private String prodcd;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "VENSKU")
    private String vensku;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PRODNAM")
    private String prodnam;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTPRICE")
    private BigDecimal costprice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MSRP")
    private BigDecimal msrp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ROP")
    private int rop;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EOQ")
    private int eoq;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QOH")
    private int qoh;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QOO")
    private int qoo;
    @Lob
    @Column(name = "QRCODE")
    private Serializable qrcode;
    @JoinColumn(name = "VENDORNO", referencedColumnName = "VENDORNO")
    @ManyToOne(optional = false)
    private VendorsModel vendorno;

    public ProductsModel() {
    }

    public ProductsModel(String prodcd) {
        this.prodcd = prodcd;
    }

    public ProductsModel(String prodcd, String vensku, String prodnam, BigDecimal costprice, BigDecimal msrp, int rop, int eoq, int qoh, int qoo) {
        this.prodcd = prodcd;
        this.vensku = vensku;
        this.prodnam = prodnam;
        this.costprice = costprice;
        this.msrp = msrp;
        this.rop = rop;
        this.eoq = eoq;
        this.qoh = qoh;
        this.qoo = qoo;
    }

    public String getProdcd() {
        return prodcd;
    }

    public void setProdcd(String prodcd) {
        this.prodcd = prodcd;
    }

    public String getVensku() {
        return vensku;
    }

    public void setVensku(String vensku) {
        this.vensku = vensku;
    }

    public String getProdnam() {
        return prodnam;
    }

    public void setProdnam(String prodnam) {
        this.prodnam = prodnam;
    }

    public BigDecimal getCostprice() {
        return costprice;
    }

    public void setCostprice(BigDecimal costprice) {
        this.costprice = costprice;
    }

    public BigDecimal getMsrp() {
        return msrp;
    }

    public void setMsrp(BigDecimal msrp) {
        this.msrp = msrp;
    }

    public int getRop() {
        return rop;
    }

    public void setRop(int rop) {
        this.rop = rop;
    }

    public int getEoq() {
        return eoq;
    }

    public void setEoq(int eoq) {
        this.eoq = eoq;
    }

    public int getQoh() {
        return qoh;
    }

    public void setQoh(int qoh) {
        this.qoh = qoh;
    }

    public int getQoo() {
        return qoo;
    }

    public void setQoo(int qoo) {
        this.qoo = qoo;
    }
    
    public Serializable getQrcode() {
        return qrcode;
    }
    
    public void setQrcode(Serializable qrcode){
        this.qrcode = qrcode;
    }

    public VendorsModel getVendorno() {
        return vendorno;
    }

    public void setVendorno(VendorsModel vendorno) {
        this.vendorno = vendorno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prodcd != null ? prodcd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductsModel)) {
            return false;
        }
        ProductsModel other = (ProductsModel) object;
        if ((this.prodcd == null && other.prodcd != null) || (this.prodcd != null && !this.prodcd.equals(other.prodcd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "case2models.ProductsModel[ prodcd=" + prodcd + " ]";
    }
    
}
