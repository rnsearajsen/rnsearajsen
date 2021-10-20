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

import main.Saleprice;
import main.Sub_Group;

/**
 * AbstractDAO.java
 * This DAO class provides CRUD database operations for the table 'Saleprice'
 * in the database.
 *
 */
public class SalepriceDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public SalepriceDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
    
// Insert an Entry in 'saleprice' Table
    public boolean insertSaleprice(Saleprice saleprice) throws SQLException {
    	String sql = "INSERT INTO saleprice (date, product, subgroup_name, sell_price, uom, taxpercent_total ,taxamt_total, comments, changedby, changeddate, changedtime) "
    			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    	connect();
    	
    	PreparedStatement statement = jdbcConnection.prepareStatement(sql);
    	statement.setDate(1, saleprice.getDate());
    	statement.setString(2, saleprice.getProduct());        
        statement.setString(3, saleprice.getSubgroup_name());
        statement.setFloat(4, saleprice.getSell_price());
        statement.setString(5, saleprice.getUom());
        statement.setFloat(6, saleprice.getTaxpercent_total());
        statement.setFloat(7, saleprice.getTaxamt_total());
        statement.setString(8, saleprice.getComments());
        statement.setString(9, saleprice.getChangedby());
        statement.setDate(10, saleprice.getChangeddate());
        statement.setTime(11, saleprice.getChangedtime());
                 
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

// Get 'All' List Saleprice Data
     public List<Saleprice> listAllSaleprices() throws SQLException{
    	 List<Saleprice> listSaleprice = new ArrayList<>();
    	 
    	 String sql = "SELECT * FROM saleprice order by date, subgroup_name";
    	 connect();
    	 
    	 Statement statement = jdbcConnection.createStatement();
         ResultSet resultSet = statement.executeQuery(sql);
          
         while (resultSet.next()) {
             Date date = resultSet.getDate("date");        	 
             String product = resultSet.getString("product");
             String subgroup_name = resultSet.getString("subgroup_name");
             Float sell_price = resultSet.getFloat("sell_price");             
             String uom = resultSet.getString("uom");
             Float taxpercent_total = resultSet.getFloat("taxpercent_total");
             Float taxamt_total = resultSet.getFloat("taxamt_total");
             String comments = resultSet.getString("comments");
             String changedby = resultSet.getString("changedby");
             Date changeddate = resultSet.getDate("changeddate");
             Time changedtime = resultSet.getTime("changedtime");            
              
            Saleprice saleprice = new Saleprice(date, product, subgroup_name, sell_price, uom, taxpercent_total,taxamt_total, comments, changedby, changeddate, changedtime);
             listSaleprice.add(saleprice);
         }
          
         resultSet.close();
         statement.close();
          
         disconnect();
          
         return listSaleprice;
     }
     
//List Saleprice based on Range
     public List<Saleprice> listRangeSaleprices(String fpsubgroup_name, Date fdate_from, Date fdate_to) throws SQLException{
    	 List<Saleprice> listSaleprice = new ArrayList<>();
    	 String sql = null;
    	 if (fpsubgroup_name.contentEquals("all")) {
    	  sql = "SELECT * FROM saleprice WHERE ( date >= ? and date <= ? ) order by date, subgroup_name";
    	 }else {
    	  sql = "SELECT * FROM saleprice WHERE ( date >= ? and date <= ? ) and subgroup_name = ?"
    	 		+ " order by date, subgroup_name";
    	 }
    	 connect();
    	 
    	 PreparedStatement statement = jdbcConnection.prepareStatement(sql);
    	 if (fpsubgroup_name.contentEquals("all")) {         
         statement.setDate(1, fdate_from);
         statement.setDate(2, fdate_to);
    	 }else {    		 
    		 statement.setDate(1, fdate_from);
             statement.setDate(2, fdate_to);
             statement.setString(3, fpsubgroup_name);
    	 }
         ResultSet resultSet = statement.executeQuery();
          
         while (resultSet.next()) {
        	 Date date = resultSet.getDate("date");
             String product = resultSet.getString("product");             
             String subgroup_name = resultSet.getString("subgroup_name");
             Float sell_price = resultSet.getFloat("sell_price");             
             String uom = resultSet.getString("uom");
             Float taxpercent_total = resultSet.getFloat("taxpercent_total");
             Float taxamt_total = resultSet.getFloat("taxamt_total");
             String comments = resultSet.getString("comments");
             String changedby = resultSet.getString("changedby");
             Date changeddate = resultSet.getDate("changeddate");
             Time changedtime = resultSet.getTime("changedtime");            
              
            Saleprice saleprice = new Saleprice(date, product, subgroup_name, sell_price, uom, taxpercent_total,taxamt_total, comments, changedby, changeddate, changedtime);
             listSaleprice.add(saleprice);
         }
          
         resultSet.close();
         statement.close();
          
         disconnect();
          
         return listSaleprice;
     }
     
  // Get 'Saleprice' List 'Sub_Group' Data
     public List<Sub_Group> listSalepriceSub_Groups(String fpgroup) throws SQLException{
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


// Delete an entry from 'saleprice' table
     public boolean deleteSaleprice(Saleprice saleprice) throws SQLException {
         String sql = "DELETE FROM saleprice where date = ? and product = ?";
          
         connect();
          
         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         statement.setDate(1, saleprice.getDate());         
         statement.setString(2, saleprice.getProduct());
          
         boolean rowDeleted = statement.executeUpdate() > 0;
         statement.close();
         disconnect();
         return rowDeleted;     
     }
//Update an entry in 'saleprice' table     
     public boolean updateSaleprice(Saleprice saleprice) throws SQLException {
         String sql = "UPDATE saleprice SET subgroup_name = ?, sell_price = ?, uom = ?, "
         		+ "taxpercent_total = ?, taxamt_total = ?, comments = ?, changedby = ?, changeddate = ?, changedtime = ?";
         sql += " WHERE date = ? and product = ? ";
         connect();
          
         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         
         statement.setString(1, saleprice.getSubgroup_name());
         statement.setFloat(2, saleprice.getSell_price());
         statement.setString(3, saleprice.getUom());
         statement.setFloat(4, saleprice.getTaxpercent_total());
         statement.setFloat(5, saleprice.getTaxamt_total());
         statement.setString(6, saleprice.getComments());
         statement.setString(7, saleprice.getChangedby());
         statement.setDate(8, saleprice.getChangeddate());
         statement.setTime(9, saleprice.getChangedtime());
         statement.setDate(10, saleprice.getDate());         
         statement.setString(11, saleprice.getProduct());
                   
         boolean rowUpdated = statement.executeUpdate() > 0;
         statement.close();
         disconnect();
         return rowUpdated;     
     }     
//Read an entry in 'saleprice' table
     public Saleprice getSaleprice( Date fp_date, String fp_product) throws SQLException {
         Saleprice saleprice = null;
         String sql = "SELECT * FROM saleprice WHERE date = ? and product = ?";
          
         connect();
          
         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         statement.setDate(1, fp_date);
         statement.setString(2, fp_product);         
          
         ResultSet resultSet = statement.executeQuery();
          
         if (resultSet.next()) {
             Date date = resultSet.getDate("date");        	 
        	 String product = resultSet.getString("product");
             String subgroup_name = resultSet.getString("subgroup_name");
             Float sell_price = resultSet.getFloat("sell_price");             
             String uom = resultSet.getString("uom");
             Float taxpercent_total = resultSet.getFloat("taxpercent_total");
             Float taxamt_total = resultSet.getFloat("taxamt_total");
             String comments = resultSet.getString("comments");
             String changedby = resultSet.getString("changedby");
             Date changeddate = resultSet.getDate("changeddate");
             Time changedtime = resultSet.getTime("changedtime");
             
             saleprice = new Saleprice( date, product,subgroup_name,sell_price, uom, taxpercent_total,taxamt_total, comments, changedby, changeddate, changedtime);
         }
          
         resultSet.close();
         statement.close();
          
         return saleprice;
     }     
//Get Fuel SalePrice
     public List<Saleprice> listfuelSaleprices(Date fpdate, Date fpnextdate, String fpproduct) throws SQLException{
    	 List<Saleprice> listSaleprice = new ArrayList<>();
    	 String sql = null;
//Get Sales 'Price maintained' for given date or before given date     	 
    	  sql = "select date, product, sell_price from saleprice where product = ? and ( date <= ? ) order by date desc limit 1";
    	  
    	  Saleprice saleprice = wafuelSaleprices(sql, fpdate, fpproduct); 
    	  if (saleprice != null) {
             listSaleprice.add(saleprice);
    	  }
//Get Sales 'Price maintained' the next day if maintained
            sql = "select date, product, sell_price from saleprice where product = ? and ( date = ? ) limit 1";
            saleprice = wafuelSaleprices(sql, fpnextdate, fpproduct); 
            if (saleprice != null) {
            listSaleprice.add(saleprice);
            }
         return listSaleprice;
     }
//Work Area - Process 
     public Saleprice wafuelSaleprices(String fpsql, Date fpdate, String fpproduct) throws SQLException {
    	 Saleprice saleprice = null;
    	 connect();
    	 
    	 PreparedStatement statement = jdbcConnection.prepareStatement(fpsql);   
             statement.setString(1, fpproduct);
             statement.setDate(2, fpdate);

         ResultSet resultSet = statement.executeQuery();
          
         while (resultSet.next()) {
        	 Date date = resultSet.getDate("date");
             String product = resultSet.getString("product");             
//             String subgroup_name = resultSet.getString("subgroup_name");
             Float sell_price = resultSet.getFloat("sell_price");             
              
            saleprice = new Saleprice(date, product, sell_price);
         }
          
         resultSet.close();
         statement.close();
          
         disconnect();
return saleprice;
     }
}



