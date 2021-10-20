package dao;

import java.sql.CallableStatement;
//import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import main.Sales;
import main.Sub_Group;

/**
 * AbstractDAO.java
 * This DAO class provides CRUD database operations for the table 'Sales'
 * in the database.
 *
 */
public class SalesDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public SalesDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
    
// Insert an Entry in 'sales' Table
    public boolean insertSales(Sales sales) throws SQLException {
    	String sql = "INSERT INTO sales (product,salesdate,labour_shift,sales_priceunit,purchase_priceunit,purchasedate,subgroup_name, sales_qty, total_sales_price, uom, taxpercent_total ,taxamt_total, comments, changedby, changeddate, changedtime) "
    			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    	connect();
    	
    	PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, sales.getProduct());
        statement.setDate(2, sales.getSalesdate());
        statement.setString(3, sales.getLabour_shift());
        statement.setFloat(4, sales.getSales_priceunit());
        statement.setFloat(5, sales.getPurchase_priceunit());
        statement.setDate(6, sales.getPurchasedate());
        statement.setString(7, sales.getSubgroup_name());
        statement.setFloat(8, sales.getSales_qty());        
        statement.setFloat(9, sales.getTotal_sales_price());
        statement.setString(10, sales.getUom());
        statement.setFloat(11, sales.getTaxpercent_total());
        statement.setFloat(12, sales.getTaxamt_total());
        statement.setString(13, sales.getComments());
        statement.setString(14, sales.getChangedby());
        statement.setDate(15, sales.getChangeddate());
        statement.setTime(16, sales.getChangedtime());
                 
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

// Get 'All' List Sales Data
     public List<Sales> listAllSaless() throws SQLException{
    	 List<Sales> listSales = new ArrayList<>();
    	 
    	 String sql = "SELECT * FROM sales order by salesdate, subgroup_name";
    	 connect();
    	 
    	 Statement statement = jdbcConnection.createStatement();
         ResultSet resultSet = statement.executeQuery(sql);
          
         while (resultSet.next()) {
             String product = resultSet.getString("product");
             Date salesdate = resultSet.getDate("salesdate");
             String labour_shift = resultSet.getString("labour_shift");
             Float sales_priceunit = resultSet.getFloat("sales_priceunit");
             Float purchase_priceunit = resultSet.getFloat("purchase_priceunit");  
             Date purchasedate = resultSet.getDate("purchasedate");
             String subgroup_name = resultSet.getString("subgroup_name");             
             Float sales_qty = resultSet.getFloat("sales_qty");
             Float total_sales_price = resultSet.getFloat("total_sales_price");
             String uom = resultSet.getString("uom");                          
             Float taxpercent_total = resultSet.getFloat("taxpercent_total");
             Float taxamt_total = resultSet.getFloat("taxamt_total");
             String comments = resultSet.getString("comments");
             String changedby = resultSet.getString("changedby");
             Date changeddate = resultSet.getDate("changeddate");
             Time changedtime = resultSet.getTime("changedtime");            
              
            Sales sales = new Sales(product,salesdate,labour_shift,sales_priceunit,purchase_priceunit,purchasedate,subgroup_name, sales_qty, total_sales_price, uom, taxpercent_total ,taxamt_total, comments, changedby, changeddate, changedtime);
             listSales.add(sales);
         }
          
         resultSet.close();
         statement.close();
          
         disconnect();
          
         return listSales;
     }
     
//List Sales based on Range
     public List<Sales> listRangeSaless(String fpsubgroup_name, Date fsalesdate_from, Date fsalesdate_to) throws SQLException{
    	 List<Sales> listSales = new ArrayList<>();
    	 String sql = null;
    	 if (fpsubgroup_name.contentEquals("all")) {
    	  sql = "SELECT * FROM sales WHERE ( salesdate >= ? and salesdate <= ? ) order by salesdate, subgroup_name";
    	 }else {
    	  sql = "SELECT * FROM sales WHERE ( salesdate >= ? and salesdate <= ? ) and subgroup_name = ?"
    	 		+ " order by salesdate, subgroup_name";
    	 }
    	 connect();
    	 
    	 PreparedStatement statement = jdbcConnection.prepareStatement(sql);
    	 if (fpsubgroup_name.contentEquals("all")) {         
         statement.setDate(1, fsalesdate_from);
         statement.setDate(2, fsalesdate_to);
    	 }else {
    		 statement.setDate(1, fsalesdate_from);
             statement.setDate(2, fsalesdate_to);
    		 statement.setString(3, fpsubgroup_name);             
    	 }
         ResultSet resultSet = statement.executeQuery();
          
         while (resultSet.next()) {
             String product = resultSet.getString("product");
             Date salesdate = resultSet.getDate("salesdate");
             String labour_shift = resultSet.getString("labour_shift");
             Float sales_priceunit = resultSet.getFloat("sales_priceunit");
             Float purchase_priceunit = resultSet.getFloat("purchase_priceunit");   
             Date purchasedate = resultSet.getDate("purchasedate");
             String subgroup_name = resultSet.getString("subgroup_name");
             Float sales_qty = resultSet.getFloat("sales_qty");
             Float total_sales_price = resultSet.getFloat("total_sales_price");
             String uom = resultSet.getString("uom");                          
             Float taxpercent_total = resultSet.getFloat("taxpercent_total");
             Float taxamt_total = resultSet.getFloat("taxamt_total");
             String comments = resultSet.getString("comments");
             String changedby = resultSet.getString("changedby");
             Date changeddate = resultSet.getDate("changeddate");
             Time changedtime = resultSet.getTime("changedtime"); 
             
            Sales sales = new Sales(product,salesdate,labour_shift,sales_priceunit, purchase_priceunit,purchasedate,subgroup_name, sales_qty, total_sales_price, uom, taxpercent_total ,taxamt_total, comments, changedby, changeddate, changedtime);
             listSales.add(sales);
         }
          
         resultSet.close();
         statement.close();
          
         disconnect();
          
         return listSales;
     }
     
  // Get 'Sales' List 'Sub_Group' Data
     public List<Sub_Group> listSalesSub_Groups(String fpgroup) throws SQLException{
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


// Delete an entry from 'sales' table
     public boolean deleteSales(Sales sales) throws SQLException {
         String sql = "DELETE FROM sales where product = ? and salesdate = ? and labour_shift = ? and sales_priceunit = ?"
         		+ "and purchase_priceunit = ?";
          
         connect();
          
         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         statement.setString(1, sales.getProduct());
         statement.setDate(2, sales.getSalesdate());
         statement.setString(3, sales.getLabour_shift());
         statement.setFloat(4, sales.getSales_priceunit());
         statement.setFloat(5, sales.getPurchase_priceunit());
          
         boolean rowDeleted = statement.executeUpdate() > 0;
         statement.close();
         disconnect();
         return rowDeleted;     
     }
//Update an entry in 'sales' table     
     public boolean updateSales(Sales sales) throws SQLException {
         String sql = "UPDATE sales SET purchasedate = ?, subgroup_name = ?, sales_qty = ?,  total_sales_price = ?, uom = ?,"
         		+ "taxpercent_total = ?, taxamt_total = ?, comments = ?, changedby = ?, changeddate = ?, changedtime = ?";
         sql += " WHERE product = ? and salesdate = ? and labour_shift = ? and sales_priceunit = ? and purchase_priceunit = ?";
         connect();
          
         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         
         statement.setDate(1, sales.getPurchasedate());
         statement.setString(2, sales.getSubgroup_name());
         statement.setFloat(3, sales.getSales_qty());
         statement.setFloat(4, sales.getTotal_sales_price());
         statement.setString(5, sales.getUom());
         statement.setFloat(6, sales.getTaxpercent_total());
         statement.setFloat(7, sales.getTaxamt_total());
         statement.setString(8, sales.getComments());
         statement.setString(9, sales.getChangedby());
         statement.setDate(10, sales.getChangeddate());
         statement.setTime(11, sales.getChangedtime());
         statement.setString(12, sales.getProduct());
         statement.setDate(13, sales.getSalesdate());
         statement.setString(14, sales.getLabour_shift());
         statement.setFloat(15, sales.getSales_priceunit());
         statement.setFloat(15, sales.getPurchase_priceunit());
                   
         boolean rowUpdated = statement.executeUpdate() > 0;
         statement.close();
         disconnect();
         return rowUpdated;     
     }  
//Get last Sales Date
     public Date getlastsalesdate() throws SQLException {
    	 Date date = null;
    	 String sql = "SELECT max(salesdate) salesdate FROM sales";
    	 connect();
    	 PreparedStatement statement = jdbcConnection.prepareStatement(sql);
    	 ResultSet resultSet = statement.executeQuery();
    	 if (resultSet.next()) {
    		 date = resultSet.getDate("salesdate");
    	 }
         resultSet.close();
         statement.close();    	 
    	 return date;
     }
//Read an entry in 'sales' table
     public Sales getSales(String fp_product, Date fp_salesdate) throws SQLException {
         Sales sales = null;
         String sql = "SELECT * FROM sales WHERE product = ? and salesdate = ?";
          
         connect();
          
         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         statement.setString(1, fp_product);
         statement.setDate(2, fp_salesdate);
          
         ResultSet resultSet = statement.executeQuery();
        
         if (resultSet.next()) {
        	 String product = resultSet.getString("product");
             Date salesdate = resultSet.getDate("salesdate");
             Date purchasedate = resultSet.getDate("purchasedate");
             String labour_shift = resultSet.getString("labour_shift");
             String subgroup_name = resultSet.getString("subgroup_name");
             Float sales_priceunit = resultSet.getFloat("sales_priceunit");
             Float sales_qty = resultSet.getFloat("sales_qty");
             Float purchase_priceunit = resultSet.getFloat("purchase_priceunit");
             Float total_sales_price = resultSet.getFloat("total_sales_price");
             String uom = resultSet.getString("uom");                          
             Float taxpercent_total = resultSet.getFloat("taxpercent_total");
             Float taxamt_total = resultSet.getFloat("taxamt_total");
             String comments = resultSet.getString("comments");
             String changedby = resultSet.getString("changedby");
             Date changeddate = resultSet.getDate("changeddate");
             Time changedtime = resultSet.getTime("changedtime"); 
             
             sales = new Sales( product,salesdate,labour_shift,sales_priceunit,purchase_priceunit,purchasedate,subgroup_name, sales_qty, total_sales_price, uom, taxpercent_total ,taxamt_total, comments, changedby, changeddate, changedtime);
         }
          
         resultSet.close();
         statement.close();
          
         return sales;
     }  
//Packet Oil Sales
     public List<Sales> listRangePktoil(String fpsubgroup_name, String fpshift, Date fpsdate) throws SQLException{
    	 List<Sales> listPktoil = new ArrayList<>();
    	 boolean flag_fetch = false;
    	 String product;
    	 Float sales_priceunit,stock_qty, sales_qty, total_sales_price, taxpercent_total;
    	 Sales sales = null;
    	 String sql = null;
//Get Packet Oil Sales based on Salesdate & Shift    	 
    	 sql = "SELECT product, sales_priceunit, sales_qty, total_sales_price, taxpercent_total FROM sales WHERE salesdate = ? and labour_shift = ? and subgroup_name = ? order by product";
    	 connect();
       	 
       	 PreparedStatement statement = jdbcConnection.prepareStatement(sql);

       		 statement.setDate(1, fpsdate);
       		statement.setString(2, fpshift);
       		statement.setString(3, fpsubgroup_name);
    	 
       		ResultSet resultSet = statement.executeQuery();
//Packet_oil Sales read
       		while (resultSet.next()) {
       			product = resultSet.getString("product");
       			sales_priceunit = resultSet.getFloat("sales_priceunit");
       			stock_qty = null;
       			sales_qty = resultSet.getFloat("sales_qty");
       			total_sales_price = resultSet.getFloat("total_sales_price");
       			taxpercent_total = resultSet.getFloat("taxpercent_total");
       			sales = new Sales(product, sales_priceunit, stock_qty, sales_qty, total_sales_price, taxpercent_total);
       			listPktoil.add(sales);
       			flag_fetch = true;
       		}
       		resultSet.close();
            statement.close();
//If No Packet Oil Sales data obtained above for Sales date & Shift,
//then get all Product of Packet Oil with Selling Price and Available Stock Qty
            if	(flag_fetch == false) {
            	sql = "select product.name, saleprice.sell_price, saleprice.taxpercent_total, sum(purchase.stock_qty) as stock_qty " + 
            		  "from product left outer join saleprice on product.name = saleprice.product left outer join purchase on purchase.product = saleprice.product " + 
            		  "where product.subgroup_name = ? " + 
            		  "and saleprice.date = (select max(date) from saleprice where product = product.name)" + 
            		  "group by product.name order by product.name";
            	
            	statement = jdbcConnection.prepareStatement(sql);
            	statement.setString(1, fpsubgroup_name);
            	
            	resultSet = statement.executeQuery();
//Packet_oil Selling Price & Stock Qty read
            	       		while (resultSet.next()) {
            	       			product = resultSet.getString("name");
            	       			sales_priceunit = resultSet.getFloat("sell_price");            	       			
            	       			stock_qty = resultSet.getFloat("stock_qty"); //Passing Stock Qty
            	       			sales_qty = null;
            	       			total_sales_price = null;
            	       			taxpercent_total = resultSet.getFloat("taxpercent_total");
            	       			sales = new Sales(product, sales_priceunit, stock_qty, sales_qty, total_sales_price, taxpercent_total);
            	       			listPktoil.add(sales);
            	       			flag_fetch = true;
            	       		}
            	       		resultSet.close();
            	            statement.close();
            	
            }
            disconnect();
    	 return listPktoil;
     }     

   //Engine Oil Pending product, based on Sales Date & Product
     public List<Sales> listRangeEngpendoil(String fpsubgroup_name, String fpshift, Date fpsdate) throws SQLException{
    	 List<Sales> listEngoil = new ArrayList<>();
    	 String product;
    	 Float sales_priceunit,stock_qty, sales_qty, total_sales_price, taxpercent_total;
    	 Sales sales = null;
    	 String sql = null;

    	//Get Packet Oil Sales based on Salesdate & Shift    	 
    	 sql = "Select product.name, saleprice.sell_price, saleprice.taxpercent_total, sum(purchase.stock_qty) as stock_qty " + 
    	 		"from product left outer join saleprice on product.name = saleprice.product left outer join purchase on purchase.product = saleprice.product " + 
    	 		"where product.name not in " + 
    	 		"(select product from sales where salesdate = ? and labour_shift = ? and subgroup_name = ? order by product) " + 
    	 		"and product.subgroup_name = ? and " + 
    	 		"saleprice.date = (select max(date) from saleprice where product = product.name) " + 
    	 		"group by product.name order by product.name";
    	 connect();
       	 
       	 PreparedStatement statement = jdbcConnection.prepareStatement(sql);

       		statement.setDate(1, fpsdate);
       		statement.setString(2, fpshift);
       		statement.setString(3, fpsubgroup_name);
       		statement.setString(4, fpsubgroup_name);
    	 
       		ResultSet resultSet = statement.executeQuery();
//Packet_oil Sales read
       		while (resultSet.next()) {
       			product = resultSet.getString("name");
       			sales_priceunit = resultSet.getFloat("sell_price");
       			stock_qty = resultSet.getFloat("stock_qty"); //Passing Stock Qty;
       			sales_qty = null;
       			total_sales_price = null;
       			taxpercent_total = resultSet.getFloat("taxpercent_total");
       			sales = new Sales(product, sales_priceunit, stock_qty, sales_qty, total_sales_price, taxpercent_total);
       			listEngoil.add(sales);
       		}
       		resultSet.close();
            statement.close();    	 
    	 
    	 disconnect();
    	 return listEngoil;
     }
     //Save Fuel  
     public String saveSalesfuel(Sales sales) throws SQLException {
    	 String upd_type = null;
    	 connect();
     	CallableStatement statement = jdbcConnection.prepareCall("{call fuel_salesave(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");    
     	{
 //OUT Parameters
    	statement.registerOutParameter(13, Types.VARCHAR);
//IN Parameters
    	//statement.setString(1, "");
        statement.setString(1, sales.getProduct());
        statement.setDate(2, sales.getSalesdate());
        statement.setString(3, sales.getLabour_shift());
        statement.setFloat(4, sales.getSales_priceunit());
        statement.setString(5, sales.getSubgroup_name());
        statement.setFloat(6, sales.getSales_qty());
        statement.setFloat(7, sales.getTestqty());
        statement.setFloat(8, sales.getTaxpercent_total());
        statement.setString(9, sales.getComments());
        statement.setString(10, sales.getChangedby());
        statement.setDate(11, sales.getChangeddate());
        statement.setTime(12, sales.getChangedtime());
		statement.execute();
        upd_type = statement.getString(13);
        statement.close();
        disconnect();
        return upd_type;
     	}
     }
}



