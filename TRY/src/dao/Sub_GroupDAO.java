package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.Group;
import main.Sub_Group;

/**
 * AbstractDAO.java
 * This DAO class provides CRUD database operations for the table 'sub_group'
 * in the database.
 *
 */
public class Sub_GroupDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public Sub_GroupDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
// Insert an Entry in 'sub_group' Table
    public boolean insertSub_Group(Sub_Group sub_group) throws SQLException {
    	String sql = "INSERT INTO sub_group (fsub_group, fgroup, comments) VALUES (?, ?, ?)";
    	connect();
    	
    	PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, sub_group.getFsub_group());
        statement.setString(2, sub_group.getFgroup());
        statement.setString(3, sub_group.getComments());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
// Get 'All' List 'Sub_Group' Data
     public List<Sub_Group> listAllSub_Groups() throws SQLException{
    	 List<Sub_Group> listSub_Group = new ArrayList<>();
    	 
    	 String sql = "SELECT * FROM sub_group order by fsub_group";
    	 connect();
    	 
    	 Statement statement = jdbcConnection.createStatement();
         ResultSet resultSet = statement.executeQuery(sql);
          
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

  // Get 'All' List 'Group' Data
     public List<Group> listAllGroups() throws SQLException{
    	 List<Group> listGroup = new ArrayList<>();
    	 
    	 String sql = "SELECT * FROM .group";
    	 connect();
    	 
    	 Statement statement = jdbcConnection.createStatement();
         ResultSet resultSet = statement.executeQuery(sql);
          
         while (resultSet.next()) {
             int group_id = resultSet.getInt("group_id");
             String name = resultSet.getString("name");
             String comments = resultSet.getString("comments");
              
            Group group = new Group(group_id, name, comments);
             listGroup.add(group);
         }
          
         resultSet.close();
         statement.close();
          
         disconnect();
          
         return listGroup;
     }
     
     
// Delete an entry from 'sub_group' table
     public boolean deleteSub_Group(Sub_Group sub_group) throws SQLException {
         String sql = "DELETE FROM sub_group where subgrp_id = ?";
          
         connect();
          
         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         statement.setInt(1, sub_group.getId());
          
         boolean rowDeleted = statement.executeUpdate() > 0;
         statement.close();
         disconnect();
         return rowDeleted;     
     }
//Update an entry in 'sub_group' table     
         public boolean updateSub_Group(Sub_Group sub_group) throws SQLException {
             String sql = "UPDATE sub_group SET fsub_group = ?, fgroup = ?, comments = ?";
             sql += " WHERE subgrp_id = ?";
             connect();
              
             PreparedStatement statement = jdbcConnection.prepareStatement(sql); 
             statement.setString(1, sub_group.getFsub_group());
             statement.setString(2, sub_group.getFgroup());
             statement.setString(3, sub_group.getComments());
             statement.setInt(4, sub_group.getId());
              
             boolean rowUpdated = statement.executeUpdate() > 0;
             statement.close();
             disconnect();
             return rowUpdated;        
     }     
//Read an entry in 'sub_group' table based on ID
     public Sub_Group getSub_Group(int subgrp_id) throws SQLException {
         Sub_Group sub_group = null;
         String sql = "SELECT * FROM sub_group WHERE subgrp_id = ?";
          
         connect();
          
         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         statement.setInt(1, subgrp_id);
          
         ResultSet resultSet = statement.executeQuery();
          
         if (resultSet.next()) {
             String fsub_group = resultSet.getString("fsub_group");
             String fgroup = resultSet.getString("fgroup");
             String comments = resultSet.getString("comments");
              
             sub_group = new Sub_Group(subgrp_id, fsub_group, fgroup, comments);
         }
          
         resultSet.close();
         statement.close();
          
         return sub_group;
     }     

   //Read an entry in 'sub_group' table based on "Sub-Group'
     public Sub_Group getSub_Groupname(String fp_subgrp, String fpgroup) throws SQLException {
         Sub_Group sub_group = null;
         String sql = "SELECT * FROM sub_group WHERE fsub_group = ? and fgroup = ?";
          
         connect();
          
         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         statement.setString(1, fp_subgrp);
         statement.setString(2, fpgroup);
          
         ResultSet resultSet = statement.executeQuery();
          
         if (resultSet.next()) {
        	 //int subgrp_id = resultSet.getInt("subgrp_id");
             String fsub_group = resultSet.getString("fsub_group");
             String fgroup = resultSet.getString("fgroup");
             String comments = resultSet.getString("comments");
              
             sub_group = new Sub_Group(fsub_group, fgroup, comments);
         }
          
         resultSet.close();
         statement.close();
          
         return sub_group;
     }     
     
}
