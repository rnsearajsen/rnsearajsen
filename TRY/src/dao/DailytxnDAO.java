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

import main.Dailytxn;
import main.Sub_Group;
import main.Cashtxn_cmn;

/**
 * AbstractDAO.java
 * This DAO class provides CRUD database operations for the table 'Dailytxn'
 * in the database.
 *
 */
public class DailytxnDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public DailytxnDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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

	// Get 'Sub_Group' Data
	public List<Sub_Group> listDailytxnSub_Groups(String fpgroup) throws SQLException{
		List<Sub_Group> listSub_Group = new ArrayList<>();

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
	//Get Expense Details

	public List<Dailytxn> listExpense(String fpgroup, String fpshift, Date fpedate) throws SQLException{
		List<Dailytxn> listExpense = new ArrayList<>();    	 
		boolean flag_fetch = false;
		String expense_type, comments;
		Float expense_amt, chexp_amt;
		Dailytxn dailytxn = null; Cashtxn_cmn listcashtxn = null;
		String sql = null;
		//Get Current Total Amount in Hand
		List<Cashtxn_cmn> listCashtxn = get_amtinhand();
		//Get Expense based on Txndate & Shift    	 
		sql = "SELECT expense_type, expense_amt, chexp_amt, comments FROM expense WHERE txndate = ? and labour_shift = ? order by expense_type";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);

		statement.setDate(1, fpedate);
		statement.setString(2, fpshift);

		ResultSet resultSet = statement.executeQuery();
		//Expense read
		while (resultSet.next()) {
			expense_type = resultSet.getString("expense_type");
			expense_amt = resultSet.getFloat("expense_amt");
			chexp_amt = resultSet.getFloat("chexp_amt");
			comments = resultSet.getString("comments");
			//Get Amount in Hand Details
			listcashtxn = listCashtxn.get(0);
			Float amtinhand = listcashtxn.getAggramt(); 

			dailytxn = new Dailytxn(expense_type, expense_amt, chexp_amt, amtinhand, comments);
			listExpense.add(dailytxn);
			flag_fetch = true;
		}
		resultSet.close();
		statement.close();
		//If No Expense data obtained above for Txndate & Shift,
		//then get all Expenses
		if	(flag_fetch == false) {
			sql = "SELECT * FROM sub_group WHERE fgroup = ?";

			statement = jdbcConnection.prepareStatement(sql);
			statement.setString(1, fpgroup);

			resultSet = statement.executeQuery();
			//Packet_oil Selling Price & Stock Qty read
			while (resultSet.next()) {
				expense_type = resultSet.getString("fsub_group");
				expense_amt = null;
				chexp_amt = null;
				comments = null;
				//Get Amount in Hand Details
				listcashtxn = listCashtxn.get(0);
				Float amtinhand = listcashtxn.getAggramt(); 

				dailytxn = new Dailytxn(expense_type, expense_amt, chexp_amt, amtinhand, comments);
				listExpense.add(dailytxn);
				flag_fetch = true;
			}
			resultSet.close();
			statement.close();            	
		}
		disconnect();
		return listExpense;
	}     
	//Get Last Expense Date
	public Date getlasttxndate() throws SQLException {
		Date date = null;
		String sql = "SELECT max(txndate) txndate FROM expense";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			date = resultSet.getDate("txndate");
		}
		resultSet.close();
		statement.close();    	 
		return date;
	}     
	//Pending Expense Type, based on Txn Date & Product
	public List<Dailytxn> listRangeExppend(String fpgroup_name, String fpshift, Date fpsdate) throws SQLException{
		List<Dailytxn> listExpense = new ArrayList<>();
		String expense_type,comments;
		Float expense_amt;
		Dailytxn dailytxn = null;
		String sql = null;

		//Get Expense based on Txndate & Shift    	 
		sql = "select fsub_group from sub_group where " + 
				"fsub_group not in (select expense_type from expense where txndate = ? and labour_shift = ?) " + 
				"and fgroup = ?";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);

		statement.setDate(1, fpsdate);
		statement.setString(2, fpshift);
		statement.setString(3, fpgroup_name);

		ResultSet resultSet = statement.executeQuery();
		//Expense read
		while (resultSet.next()) {
			expense_type = resultSet.getString("fsub_group");
			expense_amt = null;
			Float chexp_amt = Float.parseFloat("0");;
			comments = null;
			Float amtinhand = Float.parseFloat("0");
			dailytxn = new Dailytxn(expense_type, expense_amt, chexp_amt, amtinhand, comments);
			listExpense.add(dailytxn);
		}
		resultSet.close();
		statement.close();    	 

		disconnect();
		return listExpense;
	}     
	//Save Expense  
	public String saveDailytxnexp(Dailytxn dailytxn) throws SQLException {
		String upd_type = null;
		connect();
		CallableStatement statement = jdbcConnection.prepareCall("{call dailytxn_expensesave(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");    
		{
			//OUT Parameters
			statement.registerOutParameter(10, Types.VARCHAR);
			//IN Parameters
			//statement.setString(1, "");
			statement.setDate(1, dailytxn.getTxndate());        
			statement.setString(2, dailytxn.getLabour_shift());
			statement.setString(3, dailytxn.getExpense_type());
			statement.setFloat(4, dailytxn.getExpense_amt());
			statement.setFloat(5, dailytxn.getChexp_amt());
			statement.setString(6, dailytxn.getComments());
			statement.setString(7, dailytxn.getChangedby());
			statement.setDate(8, dailytxn.getChangeddate());
			statement.setTime(9, dailytxn.getChangedtime());
			statement.execute();
			upd_type = statement.getString(10);
			statement.close();
			disconnect();
			return upd_type;
		}
	}     
	//Save Cashtxn
	public String saveCashtxnexp(Dailytxn dailytxn) throws SQLException {
		String upd_type = null;
		Float collect_amt = Float.parseFloat("0"), difference = Float.parseFloat("0");
		connect();
		CallableStatement statement = jdbcConnection.prepareCall("{call dailytxn_cashtxnsave(?, ?, ?, ?, ?, ?, ?, ?, ?)}");    
		{
			//OUT Parameters
			statement.registerOutParameter(9, Types.VARCHAR);
			//IN Parameters
			//statement.setString(1, "");
			statement.setDate(1, dailytxn.getTxndate());        
			statement.setString(2, dailytxn.getLabour_shift());
			statement.setFloat(3, dailytxn.getAmtinhand());
			statement.setFloat(4, collect_amt);        
			statement.setFloat(5, difference);
			statement.setString(6, dailytxn.getChangedby());
			statement.setDate(7, dailytxn.getChangeddate());
			statement.setTime(8, dailytxn.getChangedtime());
			statement.execute();
			upd_type = statement.getString(9);
			statement.close();
			disconnect();
			return upd_type;
		}
	}          
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