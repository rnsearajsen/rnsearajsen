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
import main.Othincome;

/**
 * AbstractDAO.java
 * This DAO class provides CRUD database operations for the table 'oth_income'
 * in the database.
 *
 */
public class OthincomeDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public OthincomeDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
	//***************************************************************************************************************   
	//Get Other Income Details
	public List<Othincome> listOthincome(String fpgroup, String fpshift, Date fpedate) throws SQLException{
		boolean flag_fetch = false;
		List<Othincome> listOthincome = new ArrayList<>();
		String inctype = null, mop = null,comments = null;
		Float dbamount = Float.parseFloat("0"), ch_dbamount = Float.parseFloat("0");    		  
		Othincome othincome = null; Cashtxn_cmn listcashtxn = null;
		String sql = null;
		//Get Current Total Amount in Hand
		List<Cashtxn_cmn> listCashtxn = get_amtinhand();    	 
		//Get Other Income based on Txndate & Shift    	 
		sql = "SELECT inctype, dbamount, mop, ch_dbamount, comments FROM oth_income WHERE txndate = ? and labour_shift = ? order by inctype";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);

		statement.setDate(1, fpedate);
		statement.setString(2, fpshift);

		ResultSet resultSet = statement.executeQuery();
		//Other Income Details read
		while (resultSet.next()) {
			inctype = resultSet.getString("inctype");
			dbamount = resultSet.getFloat("dbamount");
			ch_dbamount = resultSet.getFloat("ch_dbamount");
			mop = resultSet.getString("mop");
			//Get Amount in Hand Details
			listcashtxn = listCashtxn.get(0);
			Float amtinhand = listcashtxn.getAggramt(); 

			comments = resultSet.getString("comments");
			othincome = new Othincome(inctype, dbamount, mop, ch_dbamount, amtinhand, comments);
			listOthincome.add(othincome);
			flag_fetch = true;
		}
		resultSet.close();
		statement.close();
		if (flag_fetch == false) {
			//For 'No Data' in above fetch populate 'Amount in Hand'
			//Get Amount in Hand Details
			listcashtxn = listCashtxn.get(0);
			Float amtinhand = listcashtxn.getAggramt(); 

			othincome = new Othincome(inctype, dbamount, mop, ch_dbamount, amtinhand, comments);
			listOthincome.add(othincome);
		}
		disconnect();
		return listOthincome;
	}        
	//***************************************************************************************************************
	//Save Other Income
	public String saveDailytxnothincome(Othincome othincome) throws SQLException {
		String upd_type = null;
		connect();
		CallableStatement statement = jdbcConnection.prepareCall("{call dailytxn_othincomesave(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");    
		{
			//OUT Parameters
			statement.registerOutParameter(11, Types.VARCHAR);
			//IN Parameters
			//statement.setString(1, "");
			statement.setDate(1, othincome.getTxndate());        
			statement.setString(2, othincome.getLabour_shift());
			statement.setString(3, othincome.getInctype());
			statement.setFloat(4, othincome.getDbamount());
			statement.setString(5, othincome.getMop());
			statement.setFloat(6, othincome.getCh_dbamount());
			statement.setString(7, othincome.getComments());      
			statement.setString(8, othincome.getChangedby());
			statement.setDate(9, othincome.getChangeddate());
			statement.setTime(10, othincome.getChangedtime());
			statement.execute();
			upd_type = statement.getString(11);
			statement.close();
			disconnect();
			return upd_type;
		}
	}  
	//***************************************************************************************************************
	//Save Cashtxn
	public String saveCashtxnothinc(Othincome othincome) throws SQLException {
		String upd_type = null;
		Float collect_amt = Float.parseFloat("0"), difference = Float.parseFloat("0");
		connect();
		CallableStatement statement = jdbcConnection.prepareCall("{call dailytxn_cashtxnsave(?, ?, ?, ?, ?, ?, ?, ?, ?)}");    
		{
			//OUT Parameters
			statement.registerOutParameter(9, Types.VARCHAR);
			//IN Parameters
			//statement.setString(1, "");
			statement.setDate(1, othincome.getTxndate());        
			statement.setString(2, othincome.getLabour_shift());
			statement.setFloat(3, othincome.getAmtinhand());
			statement.setFloat(4, collect_amt);        
			statement.setFloat(5, difference);
			statement.setString(6, othincome.getChangedby());
			statement.setDate(7, othincome.getChangeddate());
			statement.setTime(8, othincome.getChangedtime());
			statement.execute();
			upd_type = statement.getString(9);
			statement.close();
			disconnect();
			return upd_type;
		}
	}
	//************************************************************************************************************     
	//Get total Amount in Hand
	public List<Cashtxn_cmn> get_amtinhand() throws SQLException {
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
//***************************************************************************************************************