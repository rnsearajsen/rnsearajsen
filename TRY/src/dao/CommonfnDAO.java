package dao;

//import java.sql.Array;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
//import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import main.Commonfn;
//import main.Sub_Group;

/**
 * AbstractDAO.java
 * This DAO class provides CRUD database operations for Common Fetch
 * in the database.
 *
 */
public class CommonfnDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public CommonfnDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
    
  // Get 'Product' List Dynamically - 'Sub_Group' Data
     public List<Commonfn> listProductDyn(String fpgroup) throws SQLException{
    	 List<Commonfn> listProduct = new ArrayList<>();
    	 Commonfn product = null;
    	 //String sql = "SELECT * FROM try.product WHERE subgroup_name = ?";
    	 String sql = "SELECT * FROM product WHERE subgroup_name = ?";
    	 connect();
    	 
    	PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, fpgroup);
         ResultSet resultSet = statement.executeQuery();
         
         while (resultSet.next()) {
             String name = resultSet.getString("name");
             String uom = resultSet.getString("uom");

                 product = new Commonfn(name, uom);                   

             listProduct.add(product);
         }
                          
         resultSet.close();
         statement.close();
          
         disconnect();
          
         return listProduct;
     }
}



