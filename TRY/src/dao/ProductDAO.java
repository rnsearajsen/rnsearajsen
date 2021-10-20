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

import main.Product;
import main.Sub_Group;

/**
 * AbstractDAO.java
 * This DAO class provides CRUD database operations for the table 'Product'
 * in the database.
 *
 */
public class ProductDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public ProductDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
    
// Insert an Entry in 'product' Table
    public boolean insertProduct(Product product) throws SQLException {
    	String sql = "INSERT INTO product (name, group_name,subgroup_name, uom, comments, changedby, changeddate, changedtime, createddate) "
    			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    	connect();
    	
    	PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, product.getName());
        statement.setString(2, product.getGroup_name());
        statement.setString(3, product.getSubgroup_name());
        statement.setString(4, product.getUom());
        statement.setString(5, product.getComments());
        statement.setString(6, product.getChangedby());
        statement.setDate(7, product.getChangeddate());
        statement.setTime(8, product.getChangedtime());
        statement.setDate(9, product.getCreateddate());
                 
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

// Get 'All' List Product Data
     public List<Product> listAllProducts() throws SQLException{
    	 List<Product> listProduct = new ArrayList<>();
    	 
    	 String sql = "SELECT * FROM product order by name";
    	 connect();
    	 
    	 Statement statement = jdbcConnection.createStatement();
         ResultSet resultSet = statement.executeQuery(sql);
          
         while (resultSet.next()) {
             int product_id = resultSet.getInt("product_id");
             String name = resultSet.getString("name");
             String group_name = resultSet.getString("group_name");
             String subgroup_name = resultSet.getString("subgroup_name");
             String uom = resultSet.getString("uom");
             String comments = resultSet.getString("comments");
             String changedby = resultSet.getString("changedby");
             Date changeddate = resultSet.getDate("changeddate");
             Time changedtime = resultSet.getTime("changedtime");
             Date createddate = resultSet.getDate("createddate");
              
            Product product = new Product(product_id, name, group_name,subgroup_name, uom, comments, changedby, changeddate, changedtime, createddate);
             listProduct.add(product);
         }
          
         resultSet.close();
         statement.close();
          
         disconnect();
          
         return listProduct;
     }
  // Get 'Product' List 'Sub_Group' Data
     public List<Sub_Group> listProductSub_Groups(String fpgroup) throws SQLException{
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


// Delete an entry from 'product' table
     public boolean deleteProduct(Product product) throws SQLException {
         String sql = "DELETE FROM product where product_id = ?";
          
         connect();
          
         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         statement.setInt(1, product.getId());
          
         boolean rowDeleted = statement.executeUpdate() > 0;
         statement.close();
         disconnect();
         return rowDeleted;     
     }
//Update an entry in 'product' table     
     public boolean updateProduct(Product product) throws SQLException {
         String sql = "UPDATE product SET name = ?, group_name = ?, subgroup_name = ?, uom = ?,"
         		+ "comments = ?, changedby = ?, changeddate = ?, changedtime = ?, createddate = ?";
         sql += " WHERE product_id = ?";
         connect();
          
         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         statement.setString(1, product.getName());
         statement.setString(2, product.getGroup_name());
         statement.setString(3, product.getSubgroup_name());
         statement.setString(4, product.getUom());
         statement.setString(5, product.getComments());
         statement.setString(6, product.getChangedby());
         statement.setDate(7, product.getChangeddate());
         statement.setTime(8, product.getChangedtime());
         statement.setDate(9, product.getCreateddate());
         statement.setInt(10, product.getId());
                   
         boolean rowUpdated = statement.executeUpdate() > 0;
         statement.close();
         disconnect();
         return rowUpdated;     
     }     
//Read an entry in 'product' table
     public Product getProduct(int product_id) throws SQLException {
         Product product = null;
         String sql = "SELECT * FROM product WHERE product_id = ?";
          
         connect();
          
         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         statement.setInt(1, product_id);
          
         ResultSet resultSet = statement.executeQuery();
          
         if (resultSet.next()) {
             String name = resultSet.getString("name");
             String group_name = resultSet.getString("group_name");
             String subgroup_name = resultSet.getString("subgroup_name");
             String uom = resultSet.getString("uom");
             String comments = resultSet.getString("comments");
             String changedby = resultSet.getString("changedby");
             Date changeddate = resultSet.getDate("changeddate");
             Time changedtime = resultSet.getTime("changedtime");
             Date createddate = resultSet.getDate("createddate");
             
             product = new Product(product_id, name, group_name,subgroup_name, uom, comments, changedby, changeddate, changedtime, createddate);
         }
          
         resultSet.close();
         statement.close();
          
         return product;
     }     
}



