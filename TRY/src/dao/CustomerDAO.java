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

import main.Customer;
import main.Sub_Group;

/**
 * AbstractDAO.java
 * This DAO class provides CRUD database operations for the table 'Customer'
 * in the database.
 *
 */
public class CustomerDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public CustomerDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
    
// Insert an Entry in 'customer' Table
    public boolean insertCustomer(Customer customer) throws SQLException {
    	String sql = "INSERT INTO customer (name, group_name,subgroup_name, address, mail, contact1, contact2,idtype1,idnum1,idtype2,idnum2,comments, joindate, lastdate, changedby, changeddate, changedtime, createddate, createdtime) "
    			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?)";
    	connect();
    	
    	PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, customer.getName());
        statement.setString(2, customer.getGroup_name());
        statement.setString(3, customer.getSubgroup_name());
        statement.setString(4, customer.getAddress());
        statement.setString(5, customer.getMail());
        statement.setString(6, customer.getContact1());
        statement.setString(7, customer.getContact2());
        statement.setString(8, customer.getIdtype1());
        statement.setString(9, customer.getIdnum1());
        statement.setString(10, customer.getIdtype2());
        statement.setString(11, customer.getIdnum2());
        statement.setString(12, customer.getComments());
        statement.setDate(13, customer.getJoindate());
        statement.setDate(14, customer.getLastdate());
        statement.setString(15, customer.getChangedby());
        statement.setDate(16, customer.getChangeddate());
        statement.setTime(17, customer.getChangedtime());
        statement.setDate(18, customer.getCreateddate());
        statement.setTime(19, customer.getCreatedtime());
                 
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

// Get 'All' List Customer Data
     public List<Customer> listAllCustomers() throws SQLException{
    	 List<Customer> listCustomer = new ArrayList<>();
    	 
    	 String sql = "SELECT * FROM customer order by name";
    	 connect();
    	 
    	 Statement statement = jdbcConnection.createStatement();
         ResultSet resultSet = statement.executeQuery(sql);
          
         while (resultSet.next()) {
             int customer_id = resultSet.getInt("customer_id");
             String name = resultSet.getString("name");
             String group_name = resultSet.getString("group_name");
             String subgroup_name = resultSet.getString("subgroup_name");
             String address = resultSet.getString("address");
             String mail = resultSet.getString("mail");
             String contact1 = resultSet.getString("contact1");
             String contact2 = resultSet.getString("contact2");
             String idtype1 = resultSet.getString("idtype1");
             String idnum1 = resultSet.getString("idnum1");
             String idtype2 = resultSet.getString("idtype2");
             String idnum2 = resultSet.getString("idnum2");
             String comments = resultSet.getString("comments");
             Date joindate = resultSet.getDate("joindate");
             Date lastdate = resultSet.getDate("lastdate");
             String changedby = resultSet.getString("changedby");
             Date changeddate = resultSet.getDate("changeddate");
             Time changedtime = resultSet.getTime("changedtime");
             Date createddate = resultSet.getDate("createddate");
             Time createdtime = resultSet.getTime("createdtime");
              
            Customer customer = new Customer(customer_id, name, group_name,subgroup_name, address, mail, contact1, contact2, idtype1, idnum1,idtype2, idnum2,
            		comments, joindate, lastdate, changedby, changeddate, changedtime, createddate, createdtime);
             listCustomer.add(customer);
         }
          
         resultSet.close();
         statement.close();
          
         disconnect();
          
         return listCustomer;
     }
  // Get 'Customer' List 'Sub_Group' Data
     public List<Sub_Group> listCustomerSub_Groups(String fpgroup) throws SQLException{
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


// Delete an entry from 'customer' table
     public boolean deleteCustomer(Customer customer) throws SQLException {
         String sql = "DELETE FROM customer where customer_id = ?";
          
         connect();
          
         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         statement.setInt(1, customer.getId());
          
         boolean rowDeleted = statement.executeUpdate() > 0;
         statement.close();
         disconnect();
         return rowDeleted;     
     }
//Update an entry in 'customer' table     
     public boolean updateCustomer(Customer customer) throws SQLException {
         String sql = "UPDATE customer SET name = ?, group_name = ?, subgroup_name = ?, address = ?, mail = ?, contact1 = ?, contact2 = ?, idtype1 = ?, idnum1 = ?, idtype2 = ?, idnum2 = ?, "
         		+ "comments = ?, joindate = ?, lastdate = ?, changedby = ?, changeddate = ?, changedtime = ?, createddate = ?, createdtime = ?";
         sql += " WHERE customer_id = ?";
         connect();
          
         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         statement.setString(1, customer.getName());
         statement.setString(2, customer.getGroup_name());
         statement.setString(3, customer.getSubgroup_name());
         statement.setString(4, customer.getAddress());
         statement.setString(5, customer.getMail());
         statement.setString(6, customer.getContact1());
         statement.setString(7, customer.getContact2());
         statement.setString(8, customer.getIdtype1());
         statement.setString(9, customer.getIdnum1());
         statement.setString(10, customer.getIdtype2());
         statement.setString(11, customer.getIdnum2());
         statement.setString(12, customer.getComments());
         statement.setDate(13, customer.getJoindate());
         statement.setDate(14, customer.getLastdate());
         statement.setString(15, customer.getChangedby());
         statement.setDate(16, customer.getChangeddate());
         statement.setTime(17, customer.getChangedtime());
         statement.setDate(18, customer.getCreateddate());
         statement.setTime(19, customer.getCreatedtime());
         statement.setInt(20, customer.getId());
         
          
         boolean rowUpdated = statement.executeUpdate() > 0;
         statement.close();
         disconnect();
         return rowUpdated;     
     }     
//Read an entry in 'customer' table
     public Customer getCustomer(int customer_id) throws SQLException {
         Customer customer = null;
         String sql = "SELECT * FROM customer WHERE customer_id = ?";
          
         connect();
          
         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         statement.setInt(1, customer_id);
          
         ResultSet resultSet = statement.executeQuery();
          
         if (resultSet.next()) {
             String name = resultSet.getString("name");
             String group_name = resultSet.getString("group_name");
             String subgroup_name = resultSet.getString("subgroup_name");
             String address = resultSet.getString("address");
             String mail = resultSet.getString("mail");
             String contact1 = resultSet.getString("contact1");
             String contact2 = resultSet.getString("contact2");
             String idtype1 = resultSet.getString("idtype1");
             String idnum1 = resultSet.getString("idnum1");
             String idtype2 = resultSet.getString("idtype2");
             String idnum2 = resultSet.getString("idnum2");
             String comments = resultSet.getString("comments");
             Date joindate = resultSet.getDate("joindate");
             Date lastdate = resultSet.getDate("lastdate");
             String changedby = resultSet.getString("changedby");
             Date changeddate = resultSet.getDate("changeddate");
             Time changedtime = resultSet.getTime("changedtime");
             Date createddate = resultSet.getDate("createddate");
             Time createdtime = resultSet.getTime("createdtime");
             
             customer = new Customer(customer_id, name, group_name,subgroup_name, address, mail, contact1, contact2,idtype1, idnum1,idtype2, idnum2,
            		 comments, joindate, lastdate, changedby, changeddate, changedtime, createddate, createdtime);
         }
          
         resultSet.close();
         statement.close();
          
         return customer;
     }     
}



