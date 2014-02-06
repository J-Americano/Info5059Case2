/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import dto.PurchaseOrderDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.sql.DataSource;
/**
 * @author Jacob
 */
@Named(value = "productModel")
@RequestScoped
public class ProductModel implements Serializable
{
    
    public ProductModel(){}
    
    //resource already defined in GlassFish
    @Resource(lookup= "jdbc/Info5059db")
    DataSource ds;
    
   public boolean addProduct(int addVendno, String addProdcd, String addVendorsku, String addProdname, 
                double addCostprice, double addMsrp, int addRop, int addQoh, int addEoq, int addQoo, String addQrcode){
       
       boolean returnValue = false;
        String sql = "INSERT INTO Products (prodcd,vendorno,vensku,prodnam,costprice,msrp,rop,eoq,qoh,qoo,qrcode)"
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stmt;
        Connection con = null;
    
        try{
            con = ds.getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setString(1,addProdcd);
            stmt.setInt(2,addVendno);
            stmt.setString(3,addVendorsku);
            stmt.setString(4,addProdname);
            stmt.setDouble(5,addCostprice);
            stmt.setDouble(6,addMsrp);
            stmt.setInt(7,addRop);
            stmt.setInt(8,addEoq);
            stmt.setInt(9,addQoh);
            stmt.setInt(10,addQoo);
            stmt.setString(11,addQrcode);
            stmt.executeUpdate();
            
            returnValue = true;
            
            stmt.close();
            con.close();
        }catch(SQLException se){
            System.out.println("SQL Issue " + se.getMessage());
        }catch(Exception e){
            System.out.println("Other Issue " + e.getMessage());
        }finally{
            try{
                if(con != null){
                    con.close();
                }
            }catch(SQLException se){
                System.out.println("SQL Issue on close " + se.getMessage());
            }
        }
        return returnValue;
   }
   
   public ArrayList<PurchaseOrderDTO> dbGetProducts(int vendNo){
       String sql = "SELECT prodnam, prodcd, msrp, eoq from PRODUCTS where vendorno = ?";
       Connection con = null;
       PreparedStatement stmt;
       ResultSet rs;
       ArrayList<PurchaseOrderDTO> prodNames = new ArrayList();
       
       try {
            con = ds.getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,vendNo);
            rs = stmt.executeQuery();
            while(rs.next()){
                PurchaseOrderDTO dto = new PurchaseOrderDTO();
                dto.setProdname(rs.getString(1));
                dto.setProdcd(rs.getString(2));
                dto.setPrice(rs.getBigDecimal(3));
                dto.setEoq(rs.getInt(4));
                dto.setQty(0);
                prodNames.add(dto);
            }
             
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException se){
            //Handle errors for JDBC
            System.out.println("SQL issue " + se.getMessage());
        } catch (Exception e) {
            //Handle other errors
            System.out.println("Other issue " + e.getMessage());         
        } finally {
            //finally block used to close resources
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException se) {
                System.out.println("SQL issue on close " + se.getMessage());   
            }//end finally try
        }
       
       return prodNames;
   }
}