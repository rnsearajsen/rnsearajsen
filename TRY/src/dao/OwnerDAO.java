package dao;

//import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.Owner;
import main.Sub_Group;

/**
 * AbstractDAO.java
 * This DAO class provides CRUD database operations for the table 'Owner'
 * in the database.
 *
 */
public class OwnerDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public OwnerDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
    
// Insert an Entry in 'owner' Table
    public boolean insertOwner(Owner owner) throws SQLException {
    	String sql = "INSERT INTO owner (name, group_name,subgroup_name, address, mail, contact1, contact2,idtype1,idnum1,idtype2,idnum2) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    	connect();
    	
    	PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, owner.getName());
        statement.setString(2, owner.getGroup_name());
        statement.setString(3, owner.getSubgroup_name());
        statement.setString(4, owner.getAddress());
        statement.setString(5, owner.getMail());
        statement.setString(6, owner.getContact1());
        statement.setString(7, owner.getContact2());
        statement.setString(8, owner.getIdtype1());
        statement.setString(9, owner.getIdnum1());
        statement.setString(10, owner.getIdtype2());
        statement.setString(11, owner.getIdnum2());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

// Get 'All' List Owner Data
     public List<Owner> listAllOwners() throws SQLException{
    	 List<Owner> listOwner = new ArrayList<>();
    	 
    	 String sql = "SELECT * FROM owner order by name";
    	 connect();
    	 
    	 Statement statement = jdbcConnection.createStatement();
         ResultSet resultSet = statement.executeQuery(sql);
          
         while (resultSet.next()) {
             int owner_id = resultSet.getInt("owner_id");
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
              
            Owner owner = new Owner(owner_id, name, group_name,subgroup_name, address, mail, contact1, contact2, idtype1, idnum1,idtype2, idnum2);
             listOwner.add(owner);
         }
          
         resultSet.close();
         statement.close();
          
         disconnect();
          
         return listOwner;
     }
  // Get 'Owner' List 'Sub_Group' Data
     public List<Sub_Group> listOwnerSub_Groups(String fpgroup) throws SQLException{
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


// Delete an entry from 'owner' table
     public boolean deleteOwner(Owner owner) throws SQLException {
         String sql = "DELETE FROM owner where owner_id = ?";
          
         connect();
          
         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         statement.setInt(1, owner.getId());
          
         boolean rowDeleted = statement.executeUpdate() > 0;
         statement.close();
         disconnect();
         return rowDeleted;     
     }
//Update an entry in 'owner' table     
     public boolean updateOwner(Owner owner) throws SQLException {
         String sql = "UPDATE owner SET name = ?, group_name = ?, subgroup_name = ?, address = ?, mail = ?, contact1 = ?, contact2 = ?, idtype1 = ?, idnum1 = ?, idtype2 = ?, idnum2 = ? ";
         sql += " WHERE owner_id = ?";
         connect();
          
         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         statement.setString(1, owner.getName());
         statement.setString(2, owner.getGroup_name());
         statement.setString(3, owner.getSubgroup_name());
         statement.setString(4, owner.getAddress());
         statement.setString(5, owner.getMail());
         statement.setString(6, owner.getContact1());
         statement.setString(7, owner.getContact2());
         statement.setString(8, owner.getIdtype1());
         statement.setString(9, owner.getIdnum1());
         statement.setString(10, owner.getIdtype2());
         statement.setString(11, owner.getIdnum2());
         statement.setInt(12, owner.getId());
          
         boolean rowUpdated = statement.executeUpdate() > 0;
         statement.close();
         disconnect();
         return rowUpdated;     
     }     
//Read an entry in 'owner' table
     public Owner getOwner(int owner_id) throws SQLException {
         Owner owner = null;
         String sql = "SELECT * FROM owner WHERE owner_id = ?";
          
         connect();
          
         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         statement.setInt(1, owner_id);
          
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
              
             owner = new Owner(owner_id, name, group_name,subgroup_name, address, mail, contact1, contact2,idtype1, idnum1,idtype2, idnum2);
         }
          
         resultSet.close();
         statement.close();
          
         return owner;
     }     
}



