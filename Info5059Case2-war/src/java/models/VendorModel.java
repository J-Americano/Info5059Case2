/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.sql.DataSource;

/**
 * @author Jacob
 * A model backing bean for a Vendor Entity
 */
@Named(value = "vendorModel")
@RequestScoped
public class VendorModel implements Serializable{
    
    public VendorModel() {
    }
    // resource already defined in Glassfish
    @Resource(lookup = "jdbc/Info5059db")
    DataSource ds;
    
    public String dbGetVendorOneName() throws SQLException {
     
        String sql = "SELECT name FROM Vendors WHERE vendorno = 1";
        PreparedStatement stmt;
        ResultSet rs;
        Connection con = null;
        String retName = "not found";
        
        try {
            con = ds.getConnection();
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            rs.next();
            retName = (String) rs.getString("name");
            stmt.close();
            rs.close();
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
        return retName;
    }
   
public String dbGetVendorName(int vendorNo) throws SQLException {
 
    String sql = "SELECT name FROM Vendors WHERE vendorno = " + vendorNo;
    PreparedStatement stmt;
    ResultSet rs;
    Connection con = null;
    String retName = "not found";
        
        try {
            con = ds.getConnection();
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            rs.next();
            retName = (String) rs.getString("name");
            stmt.close();
            rs.close();
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
        return retName;
    }

public int dbAddVendor(String addName, String addAddress, String addCity, String addProvince, String addPostal, String addPhone, String addType, String addEmail) throws SQLException{
    
    int vendorNo = -1;
    String sql = "INSERT INTO VENDORS (Address1, City, Province, PostalCode,"
            + "Phone, VendorType, Name, Email) "
            + " VALUES (?,?,?,?,?,?,?,?)";
    PreparedStatement stmt;
    Connection con = null;
    ResultSet rs;
        
        try{
            con = ds.getConnection();
            stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1,addAddress);
            stmt.setString(2,addCity);
            stmt.setString(3,addProvince);
            stmt.setString(4,addPostal);
            stmt.setString(5,addPhone);
            stmt.setString(6,addType);
            stmt.setString(7,addName);
            stmt.setString(8,addEmail);
            
            stmt.executeUpdate();
            rs=stmt.getGeneratedKeys();
            rs.next();
            vendorNo = rs.getInt(1);
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
        return vendorNo;
    }

    public Map<String,String> dbGetVendor(int vendorno){
        
        String sql = "SELECT Address1, City, Province, PostalCode, Phone, VendorType, Name, Email FROM Vendors WHERE VendorNo = ?";
        Map<String,String> map = new HashMap();
        PreparedStatement stmt;
        ResultSet rs;
        Connection con = null;
        
        try{
            con = ds.getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,vendorno);
            rs = stmt.executeQuery();
            rs.next();
            map.put("Address1", rs.getString(1));
            map.put("City", rs.getString(2));
            map.put("Province", rs.getString(3));
            map.put("PostalCode", rs.getString(4));
            map.put("Phone", rs.getString(5));
            map.put("VendorType", rs.getString(6));
            map.put("Name", rs.getString(7));
            map.put("Email", rs.getString(8));
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
        return map;
}

    public ArrayList<Integer> dbgetVendorNom(){
        String sql = "SELECT vendorno FROM Vendors";
        ArrayList<Integer> vendorNom = new ArrayList();
        Statement stmt;
        Connection con = null;
        
        try{
            con = ds.getConnection();
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                vendorNom.add(rs.getInt(1));
            }
            
            rs.close();
            stmt.close();
            con.close();
        } catch(SQLException se){
            System.out.println("SQL Issue: " + se.getMessage());
        } catch(Exception e){
            System.out.println("Other Issue: " + e.getMessage());
        } finally{
            try{
                if(con != null){
                    con.close();
                }
            }catch(SQLException se){
            System.out.println("SQL Issue: " + se.getMessage());
            }
        }
        return vendorNom;
    }
}
