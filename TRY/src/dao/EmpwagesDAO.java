package dao;

import java.sql.CallableStatement;
//import java.sql.CallableStatement;
//import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import main.Cashtxn_cmn;
import main.Empwages;

/**
 * AbstractDAO.java
 * This DAO class provides CRUD database operations for the table 'Cust_credit'
 * in the database.
 *
 */
public class EmpwagesDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public EmpwagesDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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

//Get Employee Wages Details
     public List<Empwages> listEmpwages(String fpgroup, String fpshift, Date fpedate, Date fpfdate) throws SQLException{
    	 List<Empwages> listEmpwages = new ArrayList<>();
    	 boolean flag_fetch = false;
    	 String name = null, attendance = null, sgname = null; String tname = null;
    	 Float act_salary = Float.parseFloat("0"), crdamount = Float.parseFloat("0"), dbamount = Float.parseFloat("0"), amtinhand = Float.parseFloat("0"),
    			 mth_salary = Float.parseFloat("0"), ch_crdamount = Float.parseFloat("0"), ch_dbamount = Float.parseFloat("0");
    	 Empwages empwages = null; Cashtxn_cmn listcashtxn = null;
    	 String sql = null;
//Get Current Total Amount in Hand
    	 List<Cashtxn_cmn> listCashtxn = get_amtinhand();       	 
    	 //Date edate = getFirstDate(fpedate);
//Get Credit Customer based on Txndate & Shift    	 
    	 sql = "SELECT name, attendance, crdamount, dbamount, ch_crdamount, ch_dbamount, act_salary FROM empwages WHERE txndate = ? and labour_shift = ? order by name";
    	 connect();
       	 
       	 PreparedStatement statement = jdbcConnection.prepareStatement(sql);

       	    statement.setDate(1, fpedate);
       		statement.setString(2, fpshift);
    	 
       		ResultSet resultSet = statement.executeQuery();
//Employee Wages Details read
       		while (resultSet.next()) {
       			name = resultSet.getString("name");
       			attendance = resultSet.getString("attendance");
       			crdamount = resultSet.getFloat("crdamount");
       			dbamount = resultSet.getFloat("dbamount");
       			ch_crdamount = resultSet.getFloat("ch_crdamount");
       			ch_dbamount = resultSet.getFloat("ch_dbamount");
       			act_salary = resultSet.getFloat("act_salary");
//Get Amount in Hand Details
       		    listcashtxn = listCashtxn.get(0);
       		    amtinhand = listcashtxn.getAggramt();
       		    
       			empwages = new Empwages(name, sgname, mth_salary, attendance, crdamount, dbamount,ch_crdamount,ch_dbamount, amtinhand, act_salary);
       			listEmpwages.add(empwages);
       			flag_fetch = true;
       		}
       		resultSet.close();
            statement.close();
//If No 'Employee Wages' Details obtained above for Txn date & Shift,
//then get all 'Employee Wages' names based on group 
            if	(flag_fetch == false) {            	
            	sql = "select name, subgroup_name, salary, salary as crdamount, salary as dbamount, salary as act_salary from employee " +
                      "where group_name = 'Employee' and ( lastdate is null or lastdate > sysdate() ) union " +
            		  "select name, 'zzzzz' as subgroup_name, crdamount as salary, sum(crdamount) as crdamount, sum(dbamount) as dbamount, " +
                      "sum(act_salary) as act_salary from empwages where txndate >= ? and txndate <= ? group by name order by name, subgroup_name" ;
            	
            	statement = jdbcConnection.prepareStatement(sql);
            	statement.setDate(1, fpfdate);
            	statement.setDate(2, fpedate);
            	
            	resultSet = statement.executeQuery();
//Credit Customer Current Credit details
            	       		while (resultSet.next()) {
            	       			name = resultSet.getString("name");
            	       			if (tname == null) {
            	       				tname = name;
            	       				sgname = resultSet.getString("subgroup_name");
                	       			mth_salary = resultSet.getFloat("salary");	
                    	       		attendance = null;
            	       			}else if ( tname.contentEquals(name) ) {
            	       				crdamount = resultSet.getFloat("crdamount");
                	       			dbamount = resultSet.getFloat("dbamount");
                	       			act_salary = resultSet.getFloat("act_salary");                   	       			                  	       			
            	       			}else {
//Get Amount in Hand Details
             	           		    listcashtxn = listCashtxn.get(0);
             	           		    amtinhand = listcashtxn.getAggramt();
             	           		    
            	       				empwages = new Empwages(tname, sgname, mth_salary, attendance, crdamount, dbamount,amtinhand, act_salary);
                    	       		listEmpwages.add(empwages);
                    	       		act_salary = Float.parseFloat("0"); crdamount = Float.parseFloat("0"); dbamount = Float.parseFloat("0");
                    	       		flag_fetch = true;                   	       		
            	       				tname = name;
            	       				sgname = resultSet.getString("subgroup_name");
                	       			mth_salary = resultSet.getFloat("salary");	
                    	       		attendance = null;
            	       			}            	       			            	       			                	       		       	       			
            	       		}
//For Last Row - Append
            	       if (tname.contentEquals(name)) {
//Get Amount in Hand Details
   	           		    listcashtxn = listCashtxn.get(0);
   	           		    amtinhand = listcashtxn.getAggramt();            	    	   
            	    	empwages = new Empwages(tname, sgname, mth_salary, attendance, crdamount, dbamount,amtinhand, act_salary);
           	       		listEmpwages.add(empwages);
           	       	    flag_fetch = true;   
            	       }
            	       		resultSet.close();
            	            statement.close();            	
            }
            disconnect();
    	 return listEmpwages;
     }        
 //Get Remaining 'Employee Wages', based on Txn Date & EmpWages
     public List<Empwages> listRangeempwagespend(String fpgroup_name, String fpshift, Date fpsdate, Date fpfdate) throws SQLException{
    	 List<Empwages> listEmpwages = new ArrayList<>();
    	 String name = null, attendance = null, sgname = null, tname = null;
    	 Float crdamount = Float.parseFloat("0"), dbamount = Float.parseFloat("0"), act_salary = Float.parseFloat("0"),
    			 mth_salary = Float.parseFloat("0");
    	 Empwages empwages = null;
    	 String sql = null;
    	 
    	 sql = "select name, subgroup_name, salary, salary as crdamount, salary as dbamount, salary as act_salary from employee " +
                 "where name not in (select name from empwages where txndate = ? and labour_shift = ?) and " +
                 "group_name = 'Employee' and ( lastdate is null or lastdate > sysdate() ) union " +
       		     "select name, 'zzzzz' as subgroup_name, crdamount as salary, sum(crdamount) as crdamount, sum(dbamount) as dbamount, " +
                 "sum(act_salary) as act_salary from empwages where name not in (select name from empwages where txndate = ? and labour_shift = ?) and "+
                 "txndate >= ? and txndate <= ? group by name order by name, subgroup_name" ;    			     		
    	 connect();
       	 
       	 PreparedStatement statement = jdbcConnection.prepareStatement(sql);

       		statement.setDate(1, fpsdate);
       		statement.setString(2, fpshift);
       		statement.setDate(3, fpsdate);
       		statement.setString(4, fpshift);
       		statement.setDate(5, fpfdate);
       		statement.setDate(6, fpsdate);
    	 
       		ResultSet resultSet = statement.executeQuery();
//Expense read
       		while (resultSet.next()) {
       			name = resultSet.getString("name");
       			if (tname == null) {
       				tname = name;
       				sgname = resultSet.getString("subgroup_name");
	       			mth_salary = resultSet.getFloat("salary");	
    	       		attendance = null;
       			}else if ( tname.contentEquals(name) ) {
       				crdamount = resultSet.getFloat("crdamount");
	       			dbamount = resultSet.getFloat("dbamount");
	       			act_salary = resultSet.getFloat("act_salary");                   	       			                  	       			
       			}else {
       				empwages = new Empwages(tname, sgname, mth_salary, attendance, crdamount, dbamount, act_salary);
    	       		listEmpwages.add(empwages);
    	       		act_salary = Float.parseFloat("0"); crdamount = Float.parseFloat("0"); dbamount = Float.parseFloat("0");                  	       		
       				tname = name;
       				sgname = resultSet.getString("subgroup_name");
	       			mth_salary = resultSet.getFloat("salary");	
    	       		attendance = null;
       			}       			
       		}
       	//For Last Row - Append
 	       if (tname.contentEquals(name)) {
 	    	empwages = new Empwages(tname, sgname, mth_salary, attendance, crdamount, dbamount, act_salary);
	       		listEmpwages.add(empwages);
 	       }       		
       		resultSet.close();
            statement.close();    	 
    	 
    	 disconnect();
    	 return listEmpwages;
     }     
//Save Expense  
     public String saveDailytxnempwages(Empwages empwages) throws SQLException {
    	 String upd_type = null;
    	 connect();
     	CallableStatement statement = jdbcConnection.prepareCall("{call dailytxn_empwagessave(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");    
     	{
 //OUT Parameters
    	statement.registerOutParameter(16, Types.VARCHAR);
//IN Parameters
    	//statement.setString(1, "");
    	statement.setString(1, empwages.getName());
    	statement.setDate(2, empwages.getTxndate());        
        statement.setString(3, empwages.getLabour_shift());        
        statement.setString(4, empwages.getAttendance());
        statement.setFloat(5, empwages.getCrdamount());
        statement.setFloat(6, empwages.getDbamount());
        statement.setFloat(7, empwages.getCh_crdamount());
        statement.setFloat(8, empwages.getCh_dbamount());
        statement.setFloat(9, empwages.getAct_salary());
        statement.setString(10, empwages.getNewentry());
        statement.setDate(11, empwages.getPsdate());
        statement.setDate(12, empwages.getPedate());
        statement.setString(13, empwages.getChangedby());
        statement.setDate(14, empwages.getChangeddate());
        statement.setTime(15, empwages.getChangedtime());
        
		statement.execute();
        upd_type = statement.getString(16);
        statement.close();
        disconnect();
        return upd_type;
     	}
     }     
//*****************************************************************************
   //Save Cashtxn
     public String saveCashtxnempwages(Empwages empwages) throws SQLException {
    	 String upd_type = null;
    	 Float collect_amt = Float.parseFloat("0"), difference = Float.parseFloat("0");
    	 connect();
     	CallableStatement statement = jdbcConnection.prepareCall("{call dailytxn_cashtxnsave(?, ?, ?, ?, ?, ?, ?, ?, ?)}");    
     	{
 //OUT Parameters
    	statement.registerOutParameter(9, Types.VARCHAR);
//IN Parameters
    	//statement.setString(1, "");
        statement.setDate(1, empwages.getTxndate());        
        statement.setString(2, empwages.getLabour_shift());
        statement.setFloat(3, empwages.getAmtinhand());
        statement.setFloat(4, collect_amt);      
        statement.setFloat(5, difference);      
        statement.setString(6, empwages.getChangedby());
        statement.setDate(7, empwages.getChangeddate());
        statement.setTime(8, empwages.getChangedtime());
		statement.execute();
        upd_type = statement.getString(9);
        statement.close();
        disconnect();
        return upd_type;
     	}
     }
//*******************************************************************************     
//Get total Amount in Hand
     public List<Cashtxn_cmn> get_amtinhand() throws SQLException {
    	 //public List<Sub_Group> listDailytxnSub_Groups(String fpgroup) throws SQLException{
        	 List<Cashtxn_cmn> listCashtxn = new ArrayList<>();    	 
        	 Float amtinhand = Float.parseFloat("0"); 
    	 String sql = "SELECT txndate, labour_shift, aggramt FROM cashtxn where txndate = (select max(txndate) from cashtxn) order by labour_shift desc limit 1 ";
    	 connect();
    	 
    	PreparedStatement statement = jdbcConnection.prepareStatement(sql);

         ResultSet resultSet = statement.executeQuery();

         while (resultSet.next()) {
        	 amtinhand = resultSet.getFloat("aggramt"); }
         
         Cashtxn_cmn cashtxn = new Cashtxn_cmn(amtinhand);
         listCashtxn.add(cashtxn);
         
         resultSet.close();
         statement.close();
          
         disconnect();
          
         return listCashtxn;
     }     
}