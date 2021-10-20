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

import main.Bankacct;
import main.Sub_Group;

/**
 * AbstractDAO.java
 * This DAO class provides CRUD database operations for the table 'Bankacct'
 * in the database.
 *
 */
public class BankacctDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public BankacctDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
    
// Insert an Entry in 'bankacct' Table
    public boolean insertBankacct(Bankacct bankacct) throws SQLException {
    	String sql = "INSERT INTO bankacct (name, group_name,subgroup_name, bank, acctype, ifsc, accountno, comments, changedby, changeddate, changedtime, createddate) "
    			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    	connect();
    	
    	PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, bankacct.getName());
        statement.setString(2, bankacct.getGroup_name());
        statement.setString(3, bankacct.getSubgroup_name());
        statement.setString(4, bankacct.getBank());
        statement.setString(5, bankacct.getAcctype());
        statement.setString(6, bankacct.getIfsc());
        statement.setString(7, bankacct.getAccountno());
        statement.setString(8, bankacct.getComments());
        statement.setString(9, bankacct.getChangedby());
        statement.setDate(10, bankacct.getChangeddate());
        statement.setTime(11, bankacct.getChangedtime());
        statement.setDate(12, bankacct.getCreateddate());
                 
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

// Get 'All' List Bankacct Data
     public List<Bankacct> listAllBankaccts() throws SQLException{
    	 List<Bankacct> listBankacct = new ArrayList<>();
    	 
    	 String sql = "SELECT * FROM bankacct order by name";
    	 connect();
    	 
    	 Statement statement = jdbcConnection.createStatement();
         ResultSet resultSet = statement.executeQuery(sql);
          
         while (resultSet.next()) {
             int bankacct_id = resultSet.getInt("bankacct_id");
             String name = resultSet.getString("name");
             String group_name = resultSet.getString("group_name");
             String subgroup_name = resultSet.getString("subgroup_name");
             String bank = resultSet.getString("bank");
             String acctype = resultSet.getString("acctype");
             String ifsc = resultSet.getString("ifsc");
             String accountno = resultSet.getString("accountno");
             String comments = resultSet.getString("comments");
             String changedby = resultSet.getString("changedby");
             Date changeddate = resultSet.getDate("changeddate");
             Time changedtime = resultSet.getTime("changedtime");
             Date createddate = resultSet.getDate("createddate");
              
            Bankacct bankacct = new Bankacct(bankacct_id, name, group_name,subgroup_name, bank, acctype, ifsc, accountno, comments, changedby, changeddate, changedtime, createddate);
             listBankacct.add(bankacct);
         }
          
         resultSet.close();
         statement.close();
          
         disconnect();
          
         return listBankacct;
     }
  // Get 'Bankacct' List 'Sub_Group' Data
     public List<Sub_Group> listBankacctSub_Groups(String fpgroup) throws SQLException{
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

// Get 'Bankacct' List Dynamically - 'Sub_Group' Data
     public List<Bankacct> listBankacctDyn(String fpgroup) throws SQLException{
    	 List<Bankacct> listBankacct = new ArrayList<>();
    	 Bankacct bankacct = null;
    	// String company = "company";
    	//String sql = "SELECT * FROM try."+fpgroup+ " order by name";
    	 String sql = "SELECT * FROM "+fpgroup+ " order by name";
    	 connect();
    	 
    	PreparedStatement statement = jdbcConnection.prepareStatement(sql);
       // statement.setString(1, fpgroup);
         ResultSet resultSet = statement.executeQuery();
          String pid = fpgroup+"_id";

         while (resultSet.next()) {
        	 int id = resultSet.getInt(pid);
             String name = resultSet.getString("name");
//In Company "Sub-group" field is not maintained
             if (fpgroup.equals("company")) {
            	 String subgroup_name = "company";
                 bankacct = new Bankacct(id, name, subgroup_name);                   
             }else{
            	 String subgroup_name = resultSet.getString("subgroup_name");
             bankacct = new Bankacct(id, name, subgroup_name);
             }	              

             listBankacct.add(bankacct);
         }
                          
         resultSet.close();
         statement.close();
          
         disconnect();
          
         return listBankacct;
     }

// Delete an entry from 'bankacct' table
     public boolean deleteBankacct(Bankacct bankacct) throws SQLException {
         String sql = "DELETE FROM bankacct where bankacct_id = ?";
          
         connect();
          
         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         statement.setInt(1, bankacct.getId());
          
         boolean rowDeleted = statement.executeUpdate() > 0;
         statement.close();
         disconnect();
         return rowDeleted;     
     }
//Update an entry in 'bankacct' table     
     public boolean updateBankacct(Bankacct bankacct) throws SQLException {
         String sql = "UPDATE bankacct SET name = ?, group_name = ?, subgroup_name = ?, bank = ?, acctype = ?, ifsc = ?,"
         		+ "accountno = ?, comments = ?, changedby = ?, changeddate = ?, changedtime = ?, createddate = ?";
         sql += " WHERE bankacct_id = ?";
         connect();
          
         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         statement.setString(1, bankacct.getName());
         statement.setString(2, bankacct.getGroup_name());
         statement.setString(3, bankacct.getSubgroup_name());
         statement.setString(4, bankacct.getBank());
         statement.setString(5, bankacct.getAcctype());
         statement.setString(6, bankacct.getIfsc());
         statement.setString(7, bankacct.getAccountno());
         statement.setString(8, bankacct.getComments());
         statement.setString(9, bankacct.getChangedby());
         statement.setDate(10, bankacct.getChangeddate());
         statement.setTime(11, bankacct.getChangedtime());
         statement.setDate(12, bankacct.getCreateddate());
         statement.setInt(13, bankacct.getId());
                   
         boolean rowUpdated = statement.executeUpdate() > 0;
         statement.close();
         disconnect();
         return rowUpdated;     
     }     
//Read an entry in 'bankacct' table
     public Bankacct getBankacct(int bankacct_id) throws SQLException {
         Bankacct bankacct = null;
         String sql = "SELECT * FROM bankacct WHERE bankacct_id = ?";
          
         connect();
          
         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         statement.setInt(1, bankacct_id);
          
         ResultSet resultSet = statement.executeQuery();
          
         if (resultSet.next()) {
             String name = resultSet.getString("name");
             String group_name = resultSet.getString("group_name");
             String subgroup_name = resultSet.getString("subgroup_name");
             String bank = resultSet.getString("bank");
             String acctype = resultSet.getString("acctype");
             String ifsc = resultSet.getString("ifsc");
             String accountno = resultSet.getString("accountno");
             String comments = resultSet.getString("comments");
             String changedby = resultSet.getString("changedby");
             Date changeddate = resultSet.getDate("changeddate");
             Time changedtime = resultSet.getTime("changedtime");
             Date createddate = resultSet.getDate("createddate");
             
             bankacct = new Bankacct(bankacct_id, name, group_name,subgroup_name, bank, acctype, ifsc, accountno, comments, changedby, changeddate, changedtime, createddate);
         }
          
         resultSet.close();
         statement.close();
          
         return bankacct;
     }     
}



