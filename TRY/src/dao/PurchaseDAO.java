package dao;

//import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import main.Purchase;
import main.Sub_Group;

/**
 * AbstractDAO.java
 * This DAO class provides CRUD database operations for the table 'Purchase'
 * in the database.
 *
 */
public class PurchaseDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public PurchaseDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }
     
    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                                        jdbcURL, jdbcUsername, jdbcPassword);
        }
    }
     
    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
    
// Insert an Entry in 'purchase' Table
    public boolean insertPurchase(Purchase purchase) throws SQLException {
    	String sql = "INSERT INTO purchase (product, purchasedate, subgroup_name, purchase_qty,stock_qty, uom,price_unit,price_total, taxpercent_total ,taxamt_total, comments, changedby, changeddate, changedtime) "
    			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    	connect();
    	
    	PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, purchase.getProduct());
        statement.setDate(2, purchase.getPurchasedate());
        statement.setString(3, purchase.getSubgroup_name());
        statement.setFloat(4, purchase.getPurchase_qty());
        statement.setFloat(5, purchase.getStock_qty());
        statement.setString(6, purchase.getUom());
        statement.setFloat(7, purchase.getPrice_unit());
        statement.setFloat(8, purchase.getPrice_total());
        statement.setFloat(9, purchase.getTaxpercent_total());
        statement.setFloat(10, purchase.getTaxamt_total());
        statement.setString(11, purchase.getComments());
        statement.setString(12, purchase.getChangedby());
        statement.setDate(13, purchase.getChangeddate());
        statement.setTime(14, purchase.getChangedtime());
                 
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

// Get 'All' List Purchase Data
     public List<Purchase> listAllPurchases() throws SQLException{
    	 List<Purchase> listPurchase = new ArrayList<>();
    	 
    	 String sql = "SELECT * FROM purchase order by purchasedate, subgroup_name";
    	 connect();
    	 
    	 Statement statement = jdbcConnection.createStatement();
         ResultSet resultSet = statement.executeQuery(sql);
          
         while (resultSet.next()) {
             String product = resultSet.getString("product");
             Date purchasedate = resultSet.getDate("purchasedate");
             String subgroup_name = resultSet.getString("subgroup_name");
             Float purchase_qty = resultSet.getFloat("purchase_qty");
             Float stock_qty = resultSet.getFloat("stock_qty");             
             String uom = resultSet.getString("uom");
             Float price_unit = resultSet.getFloat("price_unit");
             Float price_total = resultSet.getFloat("price_total");
             Float taxpercent_total = resultSet.getFloat("taxpercent_total");
             Float taxamt_total = resultSet.getFloat("taxamt_total");
             String comments = resultSet.getString("comments");
             String changedby = resultSet.getString("changedby");
             Date changeddate = resultSet.getDate("changeddate");
             Time changedtime = resultSet.getTime("changedtime");            
              
            Purchase purchase = new Purchase(product, purchasedate, subgroup_name, purchase_qty,stock_qty, uom,price_unit,price_total, taxpercent_total,taxamt_total, comments, changedby, changeddate, changedtime);
             listPurchase.add(purchase);
         }
          
         resultSet.close();
         statement.close();
          
         disconnect();
          
         return listPurchase;
     }
     
//List Purchase based on Range
     public List<Purchase> listRangePurchases(String fpsubgroup_name, Date fpurchasedate_from, Date fpurchasedate_to) throws SQLException{
    	 List<Purchase> listPurchase = new ArrayList<>();
    	 String sql = null;
    	 if (fpsubgroup_name.contentEquals("all")) {
    	  sql = "SELECT * FROM purchase WHERE ( purchasedate >= ? and purchasedate <= ? ) order by purchasedate, subgroup_name";
    	 }else {
    	  sql = "SELECT * FROM purchase WHERE subgroup_name = ? and ( purchasedate >= ? and purchasedate <= ? )"
    	 		+ " order by purchasedate, subgroup_name";
    	 }
    	 connect();
    	 
    	 PreparedStatement statement = jdbcConnection.prepareStatement(sql);
    	 if (fpsubgroup_name.contentEquals("all")) {         
         statement.setDate(1, fpurchasedate_from);
         statement.setDate(2, fpurchasedate_to);
    	 }else {
    		 statement.setString(1, fpsubgroup_name);
    		 statement.setDate(2, fpurchasedate_from);
             statement.setDate(3, fpurchasedate_to);
    	 }
         ResultSet resultSet = statement.executeQuery();
          
         while (resultSet.next()) {
             String product = resultSet.getString("product");
             Date purchasedate = resultSet.getDate("purchasedate");
             String subgroup_name = resultSet.getString("subgroup_name");
             Float purchase_qty = resultSet.getFloat("purchase_qty");
             Float stock_qty = resultSet.getFloat("stock_qty");             
             String uom = resultSet.getString("uom");
             Float price_unit = resultSet.getFloat("price_unit");
             Float price_total = resultSet.getFloat("price_total");
             Float taxpercent_total = resultSet.getFloat("taxpercent_total");
             Float taxamt_total = resultSet.getFloat("taxamt_total");
             String comments = resultSet.getString("comments");
             String changedby = resultSet.getString("changedby");
             Date changeddate = resultSet.getDate("changeddate");
             Time changedtime = resultSet.getTime("changedtime");            
              
            Purchase purchase = new Purchase(product, purchasedate, subgroup_name, purchase_qty,stock_qty, uom,price_unit,price_total, taxpercent_total,taxamt_total, comments, changedby, changeddate, changedtime);
             listPurchase.add(purchase);
         }
          
         resultSet.close();
         statement.close();
          
         disconnect();
          
         return listPurchase;
     }
     
  // Get 'Purchase' List 'Sub_Group' Data
     public List<Sub_Group> listPurchaseSub_Groups(String fpgroup) throws SQLException{
    	 List<Sub_Group> listSub_Group = new ArrayList<>();
    	 
    	 String sql = "SELECT * FROM sub_group WHERE fgroup = ?";
    	 connect();
    	 
    	PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, fpgroup);
         ResultSet resultSet = statement.executeQuery();
          
         while (resultSet.next()) {
        	 int subgrp_id = resultSet.getInt("subgrp_id");
             String fsub_group = resultSet.getString("fsub_group");
             String fgroup = resultSet.getString("fgroup");
             String comments = resultSet.getString("comments");
              
            Sub_Group sub_group = new Sub_Group(subgrp_id, fsub_group, fgroup, comments);
             listSub_Group.add(sub_group);
         }
          
         resultSet.close();
         statement.close();
          
         disconnect();
          
         return listSub_Group;
     }


// Delete an entry from 'purchase' table
     public boolean deletePurchase(Purchase purchase) throws SQLException {
         String sql = "DELETE FROM purchase where product = ? and purchasedate = ?";
          
         connect();
          
         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         statement.setString(1, purchase.getProduct());
         statement.setDate(2, purchase.getPurchasedate());
          
         boolean rowDeleted = statement.executeUpdate() > 0;
         statement.close();
         disconnect();
         return rowDeleted;     
     }
//Update an entry in 'purchase' table     
     public boolean updatePurchase(Purchase purchase) throws SQLException {
         String sql = "UPDATE purchase SET subgroup_name = ?, purchase_qty = ?, stock_qty = ?, uom = ?, price_unit = ?, price_total = ?,"
         		+ "taxpercent_total = ?, taxamt_total = ?, comments = ?, changedby = ?, changeddate = ?, changedtime = ?";
         sql += " WHERE product = ? and purchasedate = ?";
         connect();
          
         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         
         statement.setString(1, purchase.getSubgroup_name());
         statement.setFloat(2, purchase.getPurchase_qty());
         statement.setFloat(3, purchase.getStock_qty());
         statement.setString(4, purchase.getUom());
         statement.setFloat(5, purchase.getPrice_unit());
         statement.setFloat(6, purchase.getPrice_total());
         statement.setFloat(7, purchase.getTaxpercent_total());
         statement.setFloat(8, purchase.getTaxamt_total());
         statement.setString(9, purchase.getComments());
         statement.setString(10, purchase.getChangedby());
         statement.setDate(11, purchase.getChangeddate());
         statement.setTime(12, purchase.getChangedtime());
         statement.setString(13, purchase.getProduct());
         statement.setDate(14, purchase.getPurchasedate());
                   
         boolean rowUpdated = statement.executeUpdate() > 0;
         statement.close();
         disconnect();
         return rowUpdated;     
     }     
//Read an entry in 'purchase' table
     public Purchase getPurchase(String fp_product, Date fp_purchasedate) throws SQLException {
         Purchase purchase = null;
         String sql = "SELECT * FROM purchase WHERE product = ? and purchasedate = ?";
          
         connect();
          
         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         statement.setString(1, fp_product);
         statement.setDate(2, fp_purchasedate);
          
         ResultSet resultSet = statement.executeQuery();
          
         if (resultSet.next()) {
        	 String product = resultSet.getString("product");
             Date purchasedate = resultSet.getDate("purchasedate");
             String subgroup_name = resultSet.getString("subgroup_name");
             Float purchase_qty = resultSet.getFloat("purchase_qty");
             Float stock_qty = resultSet.getFloat("stock_qty");             
             String uom = resultSet.getString("uom");
             Float price_unit = resultSet.getFloat("price_unit");
             Float price_total = resultSet.getFloat("price_total");
             Float taxpercent_total = resultSet.getFloat("taxpercent_total");
             Float taxamt_total = resultSet.getFloat("taxamt_total");
             String comments = resultSet.getString("comments");
             String changedby = resultSet.getString("changedby");
             Date changeddate = resultSet.getDate("changeddate");
             Time changedtime = resultSet.getTime("changedtime");
             
             purchase = new Purchase( product, purchasedate,subgroup_name,purchase_qty,stock_qty, uom,price_unit,price_total, taxpercent_total,taxamt_total, comments, changedby, changeddate, changedtime);
         }
          
         resultSet.close();
         statement.close();
          
         return purchase;
     }     

}



