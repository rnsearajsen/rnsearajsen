package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.Company;

/**
 * AbstractDAO.java
 * This DAO class provides CRUD database operations for the table 'Company'
 * in the database.
 *
 */
public class CompanyDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public CompanyDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
    
// Insert an Entry in 'company' Table
    public boolean insertCompany(Company company) throws SQLException {
    	String sql = "INSERT INTO company (name, suffix, address, mail, contact1, contact2) VALUES (?, ?, ?, ?, ?, ?)";
    	connect();
    	
    	PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, company.getName());
        statement.setString(2, company.getSuffix());
        statement.setString(3, company.getAddress());
        statement.setString(4, company.getMail());
        statement.setString(5, company.getContact1());
        statement.setString(6, company.getContact2());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

// Get 'All' List Company Data
     public List<Company> listAllCompanys() throws SQLException{
    	 List<Company> listCompany = new ArrayList<>();
    	 
    	 String sql = "SELECT * FROM company order by name";
    	 connect();
    	 
    	 Statement statement = jdbcConnection.createStatement();
         ResultSet resultSet = statement.executeQuery(sql);
          
         while (resultSet.next()) {
             int company_id = resultSet.getInt("company_id");
             String name = resultSet.getString("name");
             String suffix = resultSet.getString("suffix");
             String address = resultSet.getString("address");
             String mail = resultSet.getString("mail");
             String contact1 = resultSet.getString("contact1");
             String contact2 = resultSet.getString("contact2");
              
            Company company = new Company(company_id, name, suffix, address, mail, contact1, contact2);
             listCompany.add(company);
         }
          
         resultSet.close();
         statement.close();
          
         disconnect();
          
         return listCompany;
     }

// Delete an entry from 'company' table
     public boolean deleteCompany(Company company) throws SQLException {
         String sql = "DELETE FROM company where company_id = ?";
          
         connect();
          
         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         statement.setInt(1, company.getId());
          
         boolean rowDeleted = statement.executeUpdate() > 0;
         statement.close();
         disconnect();
         return rowDeleted;     
     }
//Update an entry in 'company' table     
     public boolean updateCompany(Company company) throws SQLException {
         String sql = "UPDATE company SET name = ?, suffix = ?, address = ?, mail = ?, contact1 = ?, contact2 = ?";
         sql += " WHERE company_id = ?";
         connect();
          
         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         statement.setString(1, company.getName());
         statement.setString(2, company.getSuffix());
         statement.setString(3, company.getAddress());
         statement.setString(4, company.getMail());
         statement.setString(5, company.getContact1());
         statement.setString(6, company.getContact2());
         statement.setInt(7, company.getId());
          
         boolean rowUpdated = statement.executeUpdate() > 0;
         statement.close();
         disconnect();
         return rowUpdated;     
     }     
//Read an entry in 'company' table
     public Company getCompany(int company_id) throws SQLException {
         Company company = null;
         String sql = "SELECT * FROM company WHERE company_id = ?";
          
         connect();
          
         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         statement.setInt(1, company_id);
          
         ResultSet resultSet = statement.executeQuery();
          
         if (resultSet.next()) {
             String name = resultSet.getString("name");
             String suffix = resultSet.getString("suffix");
             String address = resultSet.getString("address");
             String mail = resultSet.getString("mail");
             String contact1 = resultSet.getString("contact1");
             String contact2 = resultSet.getString("contact2");
              
             company = new Company(company_id, name, suffix, address, mail, contact1, contact2);
         }
          
         resultSet.close();
         statement.close();
          
         return company;
     }     
}



