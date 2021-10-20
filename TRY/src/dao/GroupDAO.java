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

/**
 * AbstractDAO.java
 * This DAO class provides CRUD database operations for the table 'Group'
 * in the database.
 *
 */
public class GroupDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public GroupDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
// Insert an Entry in 'group' Table
    public boolean insertGroup(Group group) throws SQLException {
    	String sql = "INSERT INTO .group (name, comments) VALUES (?, ?)";
    	connect();
    	
    	PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, group.getName());
        statement.setString(2, group.getComments());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
// Get 'All' List Group Data
     public List<Group> listAllGroups() throws SQLException{
    	 List<Group> listGroup = new ArrayList<>();
    	 
    	 String sql = "SELECT * FROM .group order by name";
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

// Delete an entry from 'group' table
     public boolean deleteGroup(Group group) throws SQLException {
         String sql = "DELETE FROM .group where group_id = ?";
          
         connect();
          
         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         statement.setInt(1, group.getId());
          
         boolean rowDeleted = statement.executeUpdate() > 0;
         statement.close();
         disconnect();
         return rowDeleted;     
     }
//Update an entry in 'group' table     
     public boolean updateGroup(Group group) throws SQLException {
         String sql = "UPDATE .group SET name = ?, comments = ?";
         sql += " WHERE group_id = ?";
         connect();
          
         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         statement.setString(1, group.getName());
         statement.setString(2, group.getComments());
         statement.setInt(3, group.getId());
          
         boolean rowUpdated = statement.executeUpdate() > 0;
         statement.close();
         disconnect();
         return rowUpdated;     
     }     
//Read an entry in 'group' table based on ID
     public Group getGroup(int group_id) throws SQLException {
         Group group = null;
         String sql = "SELECT * FROM .group WHERE group_id = ?";
          
         connect();
          
         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         statement.setInt(1, group_id);
          
         ResultSet resultSet = statement.executeQuery();
          
         if (resultSet.next()) {
             String name = resultSet.getString("name");
             String comments = resultSet.getString("comments");
              
             group = new Group(group_id, name, comments);
         }
          
         resultSet.close();
         statement.close();
          
         return group;
     }     
   //Read an entry in 'group' table based on 'Group'
     public Group getGroupname(String fp_name) throws SQLException {
         Group group = null;
         String sql = "SELECT * FROM .group WHERE name = ?";
          
         connect();
          
         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         statement.setString(1, fp_name);
          
         ResultSet resultSet = statement.executeQuery();
          
         if (resultSet.next()) {
             String name = resultSet.getString("name");
             String comments = resultSet.getString("comments");
              
             group = new Group(name, comments);
         }
          
         resultSet.close();
         statement.close();
          
         return group;
     }     

}
