/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import dto.PurchaseOrderDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
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
@Named(value = "generatorModel")
@RequestScoped
public class GeneratorModel implements Serializable {
    
    public GeneratorModel(){}
    
    @Resource(lookup="jdbc/Info5059db")
    DataSource ds;
    
    public ArrayList<String> getVendorProdCodes(int vendNo){
       
       String sql = "SELECT prodcd from PRODUCTS where vendorno = ?";
       Connection con = null;
       PreparedStatement stmt;
       ResultSet rs;
       ArrayList<String> prodCodes = new ArrayList();
       
       try {
            con = ds.getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,vendNo);
            rs = stmt.executeQuery();
            while(rs.next()){
                prodCodes.add(rs.getString(1));
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
       
       return prodCodes;
   }
    
    public String dbaddOrder(String vendorNo, double amount, Date time, ArrayList<PurchaseOrderDTO>items){
        
        int poNum = -1;
        String msg = "";
        String sql = "INSERT INTO PURCHASEORDERS (Vendorno, Amount, Podate)"
                + " VALUES (?,?,?)";
        PreparedStatement stmt;
        Connection con = null;
        ResultSet rs;
        try{
            con = ds.getConnection();
            con.setAutoCommit(false);
            stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, vendorNo);
            stmt.setDouble(2, amount);
            stmt.setDate(3, time);
            
            stmt.execute();
            rs = stmt.getGeneratedKeys();
            rs.next();
            poNum = rs.getInt(1);
            msg += poNum;
            rs.close();
            stmt.close();
            
            for(PurchaseOrderDTO item : items) {
                
                if(item.getQty() > 0) {
                    sql = "INSERT INTO PurchaseOrderLineItems (PONumber, Prodcd, Qty, Price)"
                            + " VALUES (?,?,?,?)";
                    stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                    stmt.setInt(1, poNum);
                    stmt.setString(2, item.getProdcd());
                    stmt.setInt(3, item.getQty());
                    stmt.setBigDecimal(4, item.getPrice());
                    stmt.execute();
                    rs = stmt.getGeneratedKeys();
                    rs.next();
                    rs.close();
                    stmt.close();
                }
            }
            con.commit();
            con.close(); 
        } catch (SQLException se){
            //Handle errors for JDBC
            System.out.println("SQL issue " + se.getMessage());
            msg = "PO not added ' - " + se.getMessage();
            try{
                con.rollback();
            }catch (SQLException sqx){
                System.out.println("Rollback failed - " + sqx.getMessage());
            }
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
        return msg;
    }
}
