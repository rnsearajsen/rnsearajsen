package dao;

import java.sql.CallableStatement;
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
import main.Overall;

/**
 * AbstractDAO.java
 * This DAO class provides CRUD database operations for the table 'oth_income'
 * in the database.
 *
 */

public class OverallDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public OverallDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
	//Get Overall Sales & Txn Details
	public List<Overall> listOverall(String fpgroup, String fpshift, Date fpedate) throws SQLException{
		List<Overall> listOverall = new ArrayList<>();
		String product = null, subgrp = null, txndet = null;
		Float sales_qty = Float.parseFloat("0"), totsales_price = Float.parseFloat("0"), sumamt = Float.parseFloat("0"), 
				amtinhand = Float.parseFloat("0"), collection_amt = Float.parseFloat("0"), difference = Float.parseFloat("0");    		  
		Overall overall = null; Cashtxn_cmn listcashtxn = null;
		String sql = null;
		//Get Current Total Amount in Hand
		List<Cashtxn_cmn> listCashtxn = get_amtinhand(fpshift, fpedate);  

		//Get Overall Sales details based on Txndate & Shift    	 
		sql = "SELECT product, labour_shift, sum(sales_qty) as sales_qty, sum(total_sales_price) as totsales_price, subgroup_name FROM sales "+
				"where salesdate = ? and labour_shift = ? and subgroup_name != 'engine_oil' group by product, subgroup_name "+
				"union SELECT 'Engine Oil' as product, labour_shift, sum(sales_qty) as sales_qty, sum(total_sales_price) as totsales_price, subgroup_name FROM sales "+
				"where salesdate = ? and labour_shift = ? and subgroup_name = 'engine_oil' group by subgroup_name "+
				"order by subgroup_name, product ";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);

		statement.setDate(1, fpedate);
		statement.setString(2, fpshift);
		statement.setDate(3, fpedate);
		statement.setString(4, fpshift);

		ResultSet resultSet = statement.executeQuery();
		//Sales Details read
		while (resultSet.next()) {
			product = resultSet.getString("product");
			sales_qty = resultSet.getFloat("sales_qty");
			totsales_price = resultSet.getFloat("totsales_price");
			subgrp = resultSet.getString("subgroup_name");
			//Get Amount in Hand Details
			listcashtxn = listCashtxn.get(0);
			amtinhand = listcashtxn.getAggramt();
			collection_amt = listcashtxn.getCollection_amt();
			difference = listcashtxn.getDifference();

			overall = new Overall(product, sales_qty, totsales_price, subgrp, txndet, sumamt, amtinhand, collection_amt, difference);
			listOverall.add(overall);       			       		
		}
		resultSet.close();
		statement.close();
		//Daily Txn Details read
		sql = "SELECT 'Expenses' as text, -sum(expense_amt) as sumamt FROM expense where txndate = ? and labour_shift = ? "+
				"union select 'Customer Credits' as text, -(sum(crdamount) - sum(dbamount)) as sumamt FROM cust_credit where txndate = ? and labour_shift = ? "+
				"union select 'Owner Credits' as text, -(sum(crdamount) - sum(dbamount)) as sumamt FROM owner_credit where txndate = ? and labour_shift = ? "+
				"union select 'Emp. Wages' as text, -(sum(crdamount) - sum(dbamount)) as sumamt FROM empwages where txndate = ? and labour_shift = ? "+
				"union select 'Other Income' as text, sum(dbamount) as sumamt FROM oth_income where txndate = ? and labour_shift = ? ";

		statement = jdbcConnection.prepareStatement(sql);
		statement.setDate(1, fpedate); statement.setString(2, fpshift);
		statement.setDate(3, fpedate); statement.setString(4, fpshift);
		statement.setDate(5, fpedate); statement.setString(6, fpshift);
		statement.setDate(7, fpedate); statement.setString(8, fpshift);
		statement.setDate(9, fpedate); statement.setString(10, fpshift);

		resultSet = statement.executeQuery();
		//Txn Details read
		int i = 0;
		while (resultSet.next()) {
			sumamt = resultSet.getFloat("sumamt");	
			txndet = resultSet.getString("text");
			overall = listOverall.get(i);
			overall.setTxndet(txndet);
			overall.setSumamt(sumamt);
			//listOverall.set(i, overall);
			i = i + 1;
		}

		resultSet.close();
		statement.close();

		disconnect();
		return listOverall;
	}        
	//***************************************************************************************************************
	//Save Cash Txns
	public String saveDailytxncashs(Overall cash) throws SQLException {
		String upd_type = null;
		connect();
		CallableStatement statement = jdbcConnection.prepareCall("{call dailytxn_cashtxnsave(?, ?, ?, ?, ?, ?, ?, ?, ?)}");    
		{
			//OUT Parameters
			statement.registerOutParameter(9, Types.VARCHAR);
			//IN Parameters
			//statement.setString(1, "");
			statement.setDate(1, cash.getTxndate());        
			statement.setString(2, cash.getLabour_shift());
			statement.setFloat(3, cash.getAggramt());
			statement.setFloat(4, cash.getCollection_amt());      
			statement.setFloat(5, cash.getDifference());      
			statement.setString(6, cash.getChangedby());
			statement.setDate(7, cash.getChangeddate());
			statement.setTime(8, cash.getChangedtime());
			statement.execute();
			upd_type = statement.getString(9);
			statement.close();
			disconnect();
			return upd_type;
		}
	}  
	//************************************************************************************************************
	//Get total Amount in Hand
	public List<Cashtxn_cmn> get_amtinhand(String fpshift, Date fpedate) throws SQLException {
		boolean flag = false;
		List<Cashtxn_cmn> listCashtxn = new ArrayList<>();    	 
		Float amtinhand = Float.parseFloat("0"), collection_amt = Float.parseFloat("0"),difference = Float.parseFloat("0") ; 
		String sql = "(SELECT txndate, labour_shift, aggramt,collection_amt, difference FROM cashtxn where txndate = (select max(txndate) from cashtxn) order by labour_shift desc limit 1) " +
				"union all select txndate, labour_shift, aggramt, collection_amt, difference FROM cashtxn where txndate = ? and labour_shift = ? ";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setDate(1, fpedate); statement.setString(2, fpshift);

		ResultSet resultSet = statement.executeQuery();
		int i = 0;
		while (resultSet.next()) {
			if (i == 0) {
				amtinhand = resultSet.getFloat("aggramt");}
			else {collection_amt = resultSet.getFloat("collection_amt");
			difference = resultSet.getFloat("difference");
			if (collection_amt != 0) {
				Cashtxn_cmn cashtxn = new Cashtxn_cmn(amtinhand, collection_amt, difference);
				listCashtxn.add(cashtxn);			
				flag = true;}
			}
			i = i + 1;}


		resultSet.close();
		statement.close();
		if (flag == false) {
			sql = "SELECT sum(collect_amt) as collection_amt FROM collect where txndate = ? and labour_shift = ? ";
			statement = jdbcConnection.prepareStatement(sql);
			statement.setDate(1, fpedate); statement.setString(2, fpshift);			

			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				collection_amt = resultSet.getFloat("collection_amt");

				Cashtxn_cmn cashtxn = new Cashtxn_cmn(amtinhand, collection_amt, difference);
				listCashtxn.add(cashtxn);					
			}
			resultSet.close();
			statement.close();
		}
		disconnect();

		return listCashtxn;
	}     
}
