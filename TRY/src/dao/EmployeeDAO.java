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

import main.Employee;
import main.Sub_Group;

/**
 * AbstractDAO.java
 * This DAO class provides CRUD database operations for the table 'Employee'
 * in the database.
 *
 */
public class EmployeeDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public EmployeeDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
    
// Insert an Entry in 'employee' Table
    public boolean insertEmployee(Employee employee) throws SQLException {
    	String sql = "INSERT INTO employee (name, group_name, gender, subgroup_name, salary, address, contact1, contact2, idtype1, idnum1, comments, joindate, lastdate, changedby, changeddate, changedtime) "
    			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    	connect();
    	
    	PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, employee.getName());
        statement.setString(2, employee.getGroup_name());
        statement.setString(3, employee.getGender());
        statement.setString(4, employee.getSubgroup_name());
        statement.setDouble(5, employee.getSalary());
        statement.setString(6, employee.getAddress());        
        statement.setString(7, employee.getContact1());
        statement.setString(8, employee.getContact2());
        statement.setString(9, employee.getIdtype1());
        statement.setString(10, employee.getIdnum1());
        statement.setString(11, employee.getComments());
        statement.setDate(12, employee.getJoindate());
        statement.setDate(13, employee.getLastdate());
        statement.setString(14, employee.getChangedby());
        statement.setDate(15, employee.getChangeddate());
        statement.setTime(16, employee.getChangedtime());
                 
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

// Get 'All' List Employee Data
     public List<Employee> listAllEmployees() throws SQLException{
    	 List<Employee> listEmployee = new ArrayList<>();
    	 
    	 String sql = "SELECT * FROM employee order by name";
    	 connect();
    	 
    	 Statement statement = jdbcConnection.createStatement();
         ResultSet resultSet = statement.executeQuery(sql);
          
         while (resultSet.next()) {
             int employee_id = resultSet.getInt("employee_id");
             String name = resultSet.getString("name");             
             String group_name = resultSet.getString("group_name");
             String gender = resultSet.getString("gender");
             String subgroup_name = resultSet.getString("subgroup_name");
             Double salary = resultSet.getDouble("salary");
             String address = resultSet.getString("address");
             String contact1 = resultSet.getString("contact1");
             String contact2 = resultSet.getString("contact2");
             String idtype1 = resultSet.getString("idtype1");
             String idnum1 = resultSet.getString("idnum1");             
             String comments = resultSet.getString("comments");
             Date joindate = resultSet.getDate("joindate");
             Date lastdate = resultSet.getDate("lastdate");
             String changedby = resultSet.getString("changedby");
             Date changeddate = resultSet.getDate("changeddate");
             Time changedtime = resultSet.getTime("changedtime");
              
            Employee employee = new Employee(employee_id, name, group_name, gender, subgroup_name, salary, address, contact1, contact2, idtype1, idnum1,
            		comments, joindate, lastdate, changedby, changeddate, changedtime);
             listEmployee.add(employee);
         }
          
         resultSet.close();
         statement.close();
          
         disconnect();
          
         return listEmployee;
     }
  // Get 'Employee' List 'Sub_Group' Data
     public List<Sub_Group> listEmployeeSub_Groups(String fpgroup) throws SQLException{
    	 List<Sub_Group> listSub_Group = new ArrayList<>();
    	 
    	 //String sql = "SELECT * FROM try.sub_group WHERE fgroup = ?";
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


// Delete an entry from 'employee' table
     public boolean deleteEmployee(Employee employee) throws SQLException {
         String sql = "DELETE FROM employee where employee_id = ?";
          
         connect();
          
         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         statement.setInt(1, employee.getId());
          
         boolean rowDeleted = statement.executeUpdate() > 0;
         statement.close();
         disconnect();
         return rowDeleted;     
     }
//Update an entry in 'employee' table     
     public boolean updateEmployee(Employee employee) throws SQLException {
         String sql = "UPDATE employee SET name = ?, group_name = ?, gender = ?, subgroup_name = ?, salary = ?, address = ?, contact1 = ?, contact2 = ?, idtype1 = ?, idnum1 = ?, "
         		+ "comments = ?, joindate = ?, lastdate = ?, changedby = ?, changeddate = ?, changedtime = ?";
         sql += " WHERE employee_id = ?";
         connect();
          
         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         statement.setString(1, employee.getName());
         statement.setString(2, employee.getGroup_name());
         statement.setString(3, employee.getGender());
         statement.setString(4, employee.getSubgroup_name());
         statement.setDouble(5, employee.getSalary());
         statement.setString(6, employee.getAddress());
         statement.setString(7, employee.getContact1());
         statement.setString(8, employee.getContact2());
         statement.setString(9, employee.getIdtype1());
         statement.setString(10, employee.getIdnum1());
         statement.setString(11, employee.getComments());
         statement.setDate(12, employee.getJoindate());
         statement.setDate(13, employee.getLastdate());
         statement.setString(14, employee.getChangedby());
         statement.setDate(15, employee.getChangeddate());
         statement.setTime(16, employee.getChangedtime());
         statement.setInt(17, employee.getId());
         
          
         boolean rowUpdated = statement.executeUpdate() > 0;
         statement.close();
         disconnect();
         return rowUpdated;     
     }     
//Read an entry in 'employee' table
     public Employee getEmployee(int employee_id) throws SQLException {
         Employee employee = null;
         String sql = "SELECT * FROM employee WHERE employee_id = ?";
          
         connect();
          
         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         statement.setInt(1, employee_id);
          
         ResultSet resultSet = statement.executeQuery();
          
         if (resultSet.next()) {
             String name = resultSet.getString("name");
             String group_name = resultSet.getString("group_name");
             String gender = resultSet.getString("gender");
             String subgroup_name = resultSet.getString("subgroup_name");
             Double salary = resultSet.getDouble("salary");              
             String address = resultSet.getString("address");
             String contact1 = resultSet.getString("contact1");
             String contact2 = resultSet.getString("contact2");
             String idtype1 = resultSet.getString("idtype1");
             String idnum1 = resultSet.getString("idnum1");
             String comments = resultSet.getString("comments");
             Date joindate = resultSet.getDate("joindate");
             Date lastdate = resultSet.getDate("lastdate");
             String changedby = resultSet.getString("changedby");
             Date changeddate = resultSet.getDate("changeddate");
             Time changedtime = resultSet.getTime("changedtime");
             
             employee = new Employee(employee_id, name, group_name, gender, subgroup_name, salary, address, contact1, contact2, idtype1, idnum1,
            		 comments, joindate, lastdate, changedby, changeddate, changedtime);
         }
          
         resultSet.close();
         statement.close();
          
         return employee;
     }     
}



