package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.User;

/**
 * AbstractDAO.java
 * This DAO class provides CRUD database operations for the table 'User'
 * in the database.
 *
 */
public class UserDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public UserDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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

//Check Login
    public User checkLogin(String fusername, String fpassword) throws SQLException {
    	User user = null;
    	String sql = "SELECT * FROM user WHERE username = ? and password = ?";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, fusername);
        statement.setString(2, fpassword);
 
        ResultSet resultSet = statement.executeQuery();        
 
        if (resultSet.next()) {
        	int user_id = resultSet.getInt("user_id");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String access = resultSet.getString("access");
            String firstname = resultSet.getString("firstname");
            String lastname = resultSet.getString("lastname");
             
            user = new User(user_id, username, password, access, firstname, lastname);
        }
 
        resultSet.close();
        statement.close();
 
        return user;
    }
        
    
// Insert an Entry in 'user' Table
    public boolean insertUser(User user) throws SQLException {
    	String sql = "INSERT INTO user (name, suffix, address, mail, contact1, contact2) VALUES (?, ?, ?, ?, ?, ?)";
    	connect();
    	
    	PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getAccess());
        statement.setString(4, user.getFirstname());
        statement.setString(5, user.getLastname());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

// Get 'All' List User Data
     public List<User> listAllUsers() throws SQLException{
    	 List<User> listUser = new ArrayList<>();
    	 
    	 String sql = "SELECT * FROM user order by name";
    	 connect();
    	 
    	 Statement statement = jdbcConnection.createStatement();
         ResultSet resultSet = statement.executeQuery(sql);
          
         while (resultSet.next()) {
             int user_id = resultSet.getInt("user_id");
             String username = resultSet.getString("username");
             String password = resultSet.getString("password");
             String access = resultSet.getString("access");
             String firstname = resultSet.getString("firstname");
             String lastname = resultSet.getString("lastname");
              
            User user = new User(user_id, username, password, access, firstname, lastname);
             listUser.add(user);
         }
          
         resultSet.close();
         statement.close();
          
         disconnect();
          
         return listUser;
     }

// Delete an entry from 'user' table
     public boolean deleteUser(User user) throws SQLException {
         String sql = "DELETE FROM user where user_id = ?";
          
         connect();
          
         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         statement.setInt(1, user.getUser_id());
          
         boolean rowDeleted = statement.executeUpdate() > 0;
         statement.close();
         disconnect();
         return rowDeleted;     
     }
//Update an entry in 'user' table     
     public boolean updateUser(User user) throws SQLException {
         String sql = "UPDATE user SET username = ?, password = ?, access = ?, firstname = ?, lastname = ?";
         sql += " WHERE user_id = ?";
         connect();
          
         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         statement.setString(1, user.getUsername());
         statement.setString(2, user.getPassword());
         statement.setString(3, user.getAccess());
         statement.setString(4, user.getFirstname());
         statement.setString(5, user.getLastname());
         statement.setInt(6, user.getUser_id());
          
         boolean rowUpdated = statement.executeUpdate() > 0;
         statement.close();
         disconnect();
         return rowUpdated;     
     }     
//Read an entry in 'user' table
     public User getUser(int user_id) throws SQLException {
         User user = null;
         String sql = "SELECT * FROM user WHERE user_id = ?";
          
         connect();
          
         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         statement.setInt(1, user_id);
          
         ResultSet resultSet = statement.executeQuery();
          
         if (resultSet.next()) {
             String username = resultSet.getString("username");
             String password = resultSet.getString("password");
             String access = resultSet.getString("access");
             String firstname = resultSet.getString("firstname");
             String lastname = resultSet.getString("lastname");
              
             user = new User(user_id, username, password, access, firstname, lastname);
         }
          
         resultSet.close();
         statement.close();
          
         return user;
     }     
}



