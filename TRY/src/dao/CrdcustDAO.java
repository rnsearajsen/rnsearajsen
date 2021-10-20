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
//import java.sql.Statement;
//import java.sql.Time;
//import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import main.Cashtxn_cmn;
import main.Crdcust;

/**
 * AbstractDAO.java
 * This DAO class provides CRUD database operations for the table 'Cust_credit'
 * in the database.
 *
 */
public class CrdcustDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public CrdcustDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
   
//Get Credit Customer Details
     public List<Crdcust> listCrdcust(String fpgroup, String fpshift, Date fpedate, Date fpfdate) throws SQLException{
    	 List<Crdcust> listCrdcust = new ArrayList<>();
    	 boolean flag_fetch = false;
    	 String name = null, mop = null,comments = null, tname = null;
    	 Float totcredit = null, crdamount = Float.parseFloat("0"), dbamount = Float.parseFloat("0"), amtinhand = Float.parseFloat("0"), 
    		   pastdue = Float.parseFloat("0"), ch_crdamount = Float.parseFloat("0"), ch_dbamount = Float.parseFloat("0") ;
    	 Crdcust crdcust = null; Cashtxn_cmn listcashtxn = null;
    	 String sql = null;
//Get Current Total Amount in Hand
    	 List<Cashtxn_cmn> listCashtxn = get_amtinhand();    	 
//Get Credit Customer based on Txndate & Shift    	 
    	 sql = "SELECT name, crdamount, dbamount, mop, ch_crdamount, ch_dbamount, comments FROM cust_credit WHERE txndate = ? and labour_shift = ? order by name";
    	 connect();
       	 
       	 PreparedStatement statement = jdbcConnection.prepareStatement(sql);

       		 statement.setDate(1, fpedate);
       		statement.setString(2, fpshift);
    	 
       		ResultSet resultSet = statement.executeQuery();
//Credit Customer Details read
       		while (resultSet.next()) {
       			name = resultSet.getString("name");
       			totcredit = null;
       			crdamount = resultSet.getFloat("crdamount");
       			dbamount = resultSet.getFloat("dbamount");
       			ch_crdamount = resultSet.getFloat("ch_crdamount");
       			ch_dbamount = resultSet.getFloat("ch_dbamount");
       			mop = resultSet.getString("mop");
       			comments = resultSet.getString("comments");
//Get Amount in Hand Details
       		    listcashtxn = listCashtxn.get(0);
       		    amtinhand = listcashtxn.getAggramt(); 
       		    
       			crdcust = new Crdcust(name, totcredit, crdamount, dbamount, mop, ch_crdamount, ch_dbamount, amtinhand, comments);
       			listCrdcust.add(crdcust);
       			flag_fetch = true;
       		}
       		resultSet.close();
            statement.close();
//If No Credit Customer Details obtained above for Txn date & Shift,
//then get all Customer names based on group 
            if	(flag_fetch == false) {  
            	totcredit = Float.parseFloat("0"); 
            	sql = "select name, null as crdamount, null as dbamount, null as pastdue from customer "+ 
            		   "where group_name = ? and subgroup_name = 'credit' and ( lastdate is null or lastdate > sysdate() ) "+
            		   "union select name, (sum(crdamount)+sum(ch_crdamount)) as crdamount, (sum(dbamount)+sum(ch_dbamount)) as dbamount, sum(pastdue) as pastdue from cust_credit "+
            		   "where txndate >= ? and txndate <= ? group by name order by name, crdamount, dbamount";
            	
            	statement = jdbcConnection.prepareStatement(sql);
            	statement.setString(1, fpgroup);
            	statement.setDate(2, fpfdate);
            	statement.setDate(3, fpedate);
            	
            	resultSet = statement.executeQuery();
//Credit Customer Current Credit details
            	       		while (resultSet.next()) {
            	       			name = resultSet.getString("name");  
            	       			if (tname == null) {
            	       				tname = name;	
            	       			}else if(tname.contentEquals(name)){
            	       				crdamount = resultSet.getFloat("crdamount");
                	       			dbamount = resultSet.getFloat("dbamount");
                	       			pastdue = resultSet.getFloat("pastdue");
                	       			totcredit = (crdamount - dbamount) + pastdue;                	       			                                  
                    	       		mop = null; comments = null;	                    	       		
            	       			}else {
//If there are no credit & Debit, then it means it is the first entry of the Month
//So Indicate in 'Mop' with a new entry 'NX'  
            	       			    if (crdamount == 0 && dbamount == 0) {
            	       			      mop = "NX";
            	       			    }            	       	
//Get Amount in Hand Details
            	           		    listcashtxn = listCashtxn.get(0);
            	           		    amtinhand = listcashtxn.getAggramt(); 

            	       				crdcust = new Crdcust(tname, totcredit, crdamount, dbamount, mop, amtinhand, comments);
                    	       		listCrdcust.add(crdcust);
                    	       		flag_fetch = true;
                    	       		totcredit = Float.parseFloat("0"); crdamount = Float.parseFloat("0");dbamount = Float.parseFloat("0");
                    	       		pastdue = Float.parseFloat("0");
                    	       		tname = name;	mop = null;
            	       			}            	       			                	       		           	       			
            	       		}            	  
//For Last Row - Append
                 	       if (tname.contentEquals(name)) {
//If there are no credit & Debit, then it means it is the first entry of the Month
//So Indicate in 'Mop' with a new entry 'NX'  
                 	    	  if (crdamount == 0 && dbamount == 0) {
                 	    	     mop = "NX";}
//Get Amount in Hand Details
      	           		    listcashtxn = listCashtxn.get(0);
      	           		    amtinhand = listcashtxn.getAggramt();   	           		    
                 	    	  crdcust = new Crdcust(tname, totcredit, crdamount, dbamount, mop, amtinhand, comments);
              	       		listCrdcust.add(crdcust);
              	       		flag_fetch = true;
                 	       }
            	       		resultSet.close();
            	            statement.close();            	
            }
            disconnect();
    	 return listCrdcust;
     }        
 //Get Remaining 'Credit Customer', based on Txn Date & Customer
     public List<Crdcust> listRangecrdcustpend(String fpgroup_name, String fpshift, Date fpsdate, Date fpfdate) throws SQLException{
    	 List<Crdcust> listCrdcust = new ArrayList<>();
    	 String name = null, mop = null,comments = null, tname = null;
    	 Float crdamount = Float.parseFloat("0"), dbamount = Float.parseFloat("0"), net_amount = Float.parseFloat("0"),
    	 pastdue = Float.parseFloat("0");
    	 Crdcust crdcust = null;
    	 String sql = null;
    	 
    	 sql = "select name, null as crdamount, null as dbamount, null as pastdue from customer "+ 
      		   "where name not in (select name from cust_credit where txndate = ? and labour_shift = ?) and "+ 
      		   "group_name = ? and subgroup_name = 'credit' and ( lastdate is null or lastdate > sysdate() ) "+
      		   "union select name, (sum(crdamount)+sum(ch_crdamount)) as crdamount, (sum(dbamount)+sum(ch_dbamount)) as dbamount, sum(pastdue) as pastdue from cust_credit "+
      		   "where name not in (select name from cust_credit where txndate = ? and labour_shift = ?) and "+
      		   "txndate >= ? and txndate <= ? group by name order by name, crdamount, dbamount";

    	 connect();
       	 
       	 PreparedStatement statement = jdbcConnection.prepareStatement(sql);

       		statement.setDate(1, fpsdate);
       		statement.setString(2, fpshift);
       		statement.setString(3, fpgroup_name);
       		statement.setDate(4, fpsdate);
       		statement.setString(5, fpshift);
       		statement.setDate(6, fpfdate);
       		statement.setDate(7, fpsdate);
    	 
       		ResultSet resultSet = statement.executeQuery();
//Credit Customer read
       		while (resultSet.next()) {
       			name = resultSet.getString("name");     
       			if (tname == null) {
       				tname = name;			
       			}else if(tname.contentEquals(name)){
       				crdamount = resultSet.getFloat("crdamount");
           			dbamount = resultSet.getFloat("dbamount");
           			pastdue = resultSet.getFloat("pastdue");
           			net_amount = (crdamount - dbamount) + pastdue;    
           		    mop = null;         			    	       		
    	       		comments = null;
       			}else {
//If there are no sum(credit & Debit, then it means it is the first entry of the Month
//So Indicate in 'Mop' with a new entry 'NX'  
       			    if (crdamount == 0 && dbamount == 0) {
       			        mop = "NX";
       			    }
    	       		crdcust = new Crdcust(tname, net_amount, mop, comments);
    	       		listCrdcust.add(crdcust);	
    	       		crdamount = Float.parseFloat("0"); dbamount = Float.parseFloat("0"); net_amount = Float.parseFloat("0");
    	       		mop = null;
    	       		tname = name;
       			}
       			}     
//For Last Row - Append
  	       if (tname.contentEquals(name)) {
//If there are no credit & Debit, then it means it is the first entry of the Month
//So Indicate in 'Mop' with a new entry 'NX'  
  	    	if (crdamount == 0 && dbamount == 0) {
  	    	   mop = "NX";}     	    	   
  	    	 crdcust = new Crdcust(tname, net_amount, mop, comments);
	       	 listCrdcust.add(crdcust);
  	       }
       		resultSet.close();
            statement.close();    	 
    	 
    	 disconnect();
    	 return listCrdcust;
     }     
//Save Credit Cust
     public String saveDailytxncrdcust(Crdcust crdcust) throws SQLException {
    	 String upd_type = null;
    	 connect();
     	CallableStatement statement = jdbcConnection.prepareCall("{call dailytxn_crdcustsave(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");    
     	{
 //OUT Parameters
    	statement.registerOutParameter(16, Types.VARCHAR);
//IN Parameters
    	//statement.setString(1, "");
        statement.setDate(1, crdcust.getTxndate());        
        statement.setString(2, crdcust.getLabour_shift());
        statement.setString(3, crdcust.getName());
        statement.setFloat(4, crdcust.getCrdamount());
        statement.setFloat(5, crdcust.getDbamount());
        statement.setString(6, crdcust.getMop());
        statement.setFloat(7, crdcust.getCh_crdamount());
        statement.setFloat(8, crdcust.getCh_dbamount());
        statement.setString(9, crdcust.getComments());
        statement.setString(10, crdcust.getNewentry());
        statement.setDate(11, crdcust.getPsdate());
        statement.setDate(12, crdcust.getPedate());
        statement.setString(13, crdcust.getChangedby());
        statement.setDate(14, crdcust.getChangeddate());
        statement.setTime(15, crdcust.getChangedtime());
		statement.execute();
        upd_type = statement.getString(16);
        statement.close();
        disconnect();
        return upd_type;
     	}
     }     
//Save Cashtxn
     public String saveCashtxncrdcust(Crdcust crdcust) throws SQLException {
    	 String upd_type = null;
    	 Float collect_amt = Float.parseFloat("0"), difference = Float.parseFloat("0");
    	 connect();
     	CallableStatement statement = jdbcConnection.prepareCall("{call dailytxn_cashtxnsave(?, ?, ?, ?, ?, ?, ?, ?, ?)}");    
     	{
 //OUT Parameters
    	statement.registerOutParameter(9, Types.VARCHAR);
//IN Parameters
    	//statement.setString(1, "");
        statement.setDate(1, crdcust.getTxndate());        
        statement.setString(2, crdcust.getLabour_shift());
        statement.setFloat(3, crdcust.getAmtinhand());
        statement.setFloat(4, collect_amt);      
        statement.setFloat(5, difference);      
        statement.setString(6, crdcust.getChangedby());
        statement.setDate(7, crdcust.getChangeddate());
        statement.setTime(8, crdcust.getChangedtime());
		statement.execute();
        upd_type = statement.getString(9);
        statement.close();
        disconnect();
        return upd_type;
     	}
     }     
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



