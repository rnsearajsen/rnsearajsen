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

import main.Vendor;
import main.Sub_Group;

/**
 * AbstractDAO.java
 * This DAO class provides CRUD database operations for the table 'Vendor'
 * in the database.
 *
 */
public class VendorDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public VendorDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
    
// Insert an Entry in 'vendor' Table
    public boolean insertVendor(Vendor vendor) throws SQLException {
    	String sql = "INSERT INTO vendor (name, group_name,subgroup_name, address, mail, contact1, contact2,idtype1,idnum1,idtype2,idnum2,comments, joindate, lastdate, changedby, changeddate, changedtime, createddate, createdtime) "
    			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?)";
    	connect();
    	
    	PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, vendor.getName());
        statement.setString(2, vendor.getGroup_name());
        statement.setString(3, vendor.getSubgroup_name());
        statement.setString(4, vendor.getAddress());
        statement.setString(5, vendor.getMail());
        statement.setString(6, vendor.getContact1());
        statement.setString(7, vendor.getContact2());
        statement.setString(8, vendor.getIdtype1());
        statement.setString(9, vendor.getIdnum1());
        statement.setString(10, vendor.getIdtype2());
        statement.setString(11, vendor.getIdnum2());
        statement.setString(12, vendor.getComments());
        statement.setDate(13, vendor.getJoindate());
        statement.setDate(14, vendor.getLastdate());
        statement.setString(15, vendor.getChangedby());
        statement.setDate(16, vendor.getChangeddate());
        statement.setTime(17, vendor.getChangedtime());
        statement.setDate(18, vendor.getCreateddate());
        statement.setTime(19, vendor.getCreatedtime());
                 
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

// Get 'All' List Vendor Data
     public List<Vendor> listAllVendors() throws SQLException{
    	 List<Vendor> listVendor = new ArrayList<>();
    	 
    	 String sql = "SELECT * FROM vendor order by name";
    	 connect();
    	 
    	 Statement statement = jdbcConnection.createStatement();
         ResultSet resultSet = statement.executeQuery(sql);
          
         while (resultSet.next()) {
             int vendor_id = resultSet.getInt("vendor_id");
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
              
            Vendor vendor = new Vendor(vendor_id, name, group_name,subgroup_name, address, mail, contact1, contact2, idtype1, idnum1,idtype2, idnum2,
            		comments, joindate, lastdate, changedby, changeddate, changedtime, createddate, createdtime);
             listVendor.add(vendor);
         }
          
         resultSet.close();
         statement.close();
          
         disconnect();
          
         return listVendor;
     }
  // Get 'Vendor' List 'Sub_Group' Data
     public List<Sub_Group> listVendorSub_Groups(String fpgroup) throws SQLException{
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


// Delete an entry from 'vendor' table
     public boolean deleteVendor(Vendor vendor) throws SQLException {
         String sql = "DELETE FROM vendor where vendor_id = ?";
          
         connect();
          
         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         statement.setInt(1, vendor.getId());
          
         boolean rowDeleted = statement.executeUpdate() > 0;
         statement.close();
         disconnect();
         return rowDeleted;     
     }
//Update an entry in 'vendor' table     
     public boolean updateVendor(Vendor vendor) throws SQLException {
         String sql = "UPDATE vendor SET name = ?, group_name = ?, subgroup_name = ?, address = ?, mail = ?, contact1 = ?, contact2 = ?, idtype1 = ?, idnum1 = ?, idtype2 = ?, idnum2 = ?, "
         		+ "comments = ?, joindate = ?, lastdate = ?, changedby = ?, changeddate = ?, changedtime = ?, createddate = ?, createdtime = ?";
         sql += " WHERE vendor_id = ?";
         connect();
          
         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         statement.setString(1, vendor.getName());
         statement.setString(2, vendor.getGroup_name());
         statement.setString(3, vendor.getSubgroup_name());
         statement.setString(4, vendor.getAddress());
         statement.setString(5, vendor.getMail());
         statement.setString(6, vendor.getContact1());
         statement.setString(7, vendor.getContact2());
         statement.setString(8, vendor.getIdtype1());
         statement.setString(9, vendor.getIdnum1());
         statement.setString(10, vendor.getIdtype2());
         statement.setString(11, vendor.getIdnum2());
         statement.setString(12, vendor.getComments());
         statement.setDate(13, vendor.getJoindate());
         statement.setDate(14, vendor.getLastdate());
         statement.setString(15, vendor.getChangedby());
         statement.setDate(16, vendor.getChangeddate());
         statement.setTime(17, vendor.getChangedtime());
         statement.setDate(18, vendor.getCreateddate());
         statement.setTime(19, vendor.getCreatedtime());
         statement.setInt(20, vendor.getId());
         
          
         boolean rowUpdated = statement.executeUpdate() > 0;
         statement.close();
         disconnect();
         return rowUpdated;     
     }     
//Read an entry in 'vendor' table
     public Vendor getVendor(int vendor_id) throws SQLException {
         Vendor vendor = null;
         String sql = "SELECT * FROM vendor WHERE vendor_id = ?";
          
         connect();
          
         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         statement.setInt(1, vendor_id);
          
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
             
             vendor = new Vendor(vendor_id, name, group_name,subgroup_name, address, mail, contact1, contact2,idtype1, idnum1,idtype2, idnum2,
            		 comments, joindate, lastdate, changedby, changeddate, changedtime, createddate, createdtime);
         }
          
         resultSet.close();
         statement.close();
          
         return vendor;
     }     
}



