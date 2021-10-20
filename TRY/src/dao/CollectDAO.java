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

import main.Collect;
import main.Sub_Group;
import main.Cashtxn_cmn;

/**
 * AbstractDAO.java
 * This DAO class provides CRUD database operations for the table 'Collect'
 * in the database.
 *
 */
public class CollectDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public CollectDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
	public List<Sub_Group> listCollectSub_Groups(String fpgroup) throws SQLException{
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
	//Get Collect Details

	public List<Collect> listCollect(String fpgroup, String fpshift, Date fpedate) throws SQLException{
		List<Collect> listCollect = new ArrayList<>();    	 
		boolean flag_fetch = false;
		String collect_type, comments;
		Float collect_amt;
		Collect collect = null; Cashtxn_cmn listcashtxn = null;
		String sql = null;
		//Get Current Total Amount in Hand
		List<Cashtxn_cmn> listCashtxn = get_amtinhand();
		//Get Collect based on Txndate & Shift    	 
		sql = "SELECT collect_type, collect_amt, comments FROM collect WHERE txndate = ? and labour_shift = ? order by collect_type";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);

		statement.setDate(1, fpedate);
		statement.setString(2, fpshift);

		ResultSet resultSet = statement.executeQuery();
		//Collect read
		while (resultSet.next()) {
			collect_type = resultSet.getString("collect_type");
			collect_amt = resultSet.getFloat("collect_amt");
			comments = resultSet.getString("comments");
			//Get Amount in Hand Details
			listcashtxn = listCashtxn.get(0);
			Float amtinhand = listcashtxn.getAggramt(); 

			collect = new Collect(collect_type, collect_amt, amtinhand, comments);
			listCollect.add(collect);
			flag_fetch = true;
		}
		resultSet.close();
		statement.close();
		//If No Collect data obtained above for Txndate & Shift,
		//then get all Collects
		if	(flag_fetch == false) {
			sql = "SELECT * FROM sub_group WHERE fgroup = ?";

			statement = jdbcConnection.prepareStatement(sql);
			statement.setString(1, fpgroup);

			resultSet = statement.executeQuery();
			//Packet_oil Selling Price & Stock Qty read
			while (resultSet.next()) {
				collect_type = resultSet.getString("fsub_group");
				collect_amt = null;
				comments = null;
				//Get Amount in Hand Details
				listcashtxn = listCashtxn.get(0);
				Float amtinhand = listcashtxn.getAggramt(); 

				collect = new Collect(collect_type, collect_amt, amtinhand, comments);
				listCollect.add(collect);
				flag_fetch = true;
			}
			resultSet.close();
			statement.close();            	
		}
		disconnect();
		return listCollect;
	}     
	//Get Last Collect Date
	public Date getlasttxndate() throws SQLException {
		Date date = null;
		String sql = "SELECT max(txndate) txndate FROM collect";
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
	//Pending Collect Type, based on Txn Date & Product
	public List<Collect> listRangeCollectpend(String fpgroup_name, String fpshift, Date fpsdate) throws SQLException{
		List<Collect> listCollect = new ArrayList<>();
		String collect_type,comments;
		Float collect_amt;
		Collect collect = null;
		String sql = null;

		//Get Collect based on Txndate & Shift    	 
		sql = "select fsub_group from sub_group where " + 
				"fsub_group not in (select collect_type from collect where txndate = ? and labour_shift = ?) " + 
				"and fgroup = ?";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);

		statement.setDate(1, fpsdate);
		statement.setString(2, fpshift);
		statement.setString(3, fpgroup_name);

		ResultSet resultSet = statement.executeQuery();
		//Collect read
		while (resultSet.next()) {
			collect_type = resultSet.getString("fsub_group");
			collect_amt = null;
			comments = null;
			Float amtinhand = Float.parseFloat("0");
			collect = new Collect(collect_type, collect_amt, amtinhand, comments);
			listCollect.add(collect);
		}
		resultSet.close();
		statement.close();    	 

		disconnect();
		return listCollect;
	}     
	//Save Collect  
	public String saveDailytxncollect(Collect collect) throws SQLException {
		String upd_type = null;
		connect();
		CallableStatement statement = jdbcConnection.prepareCall("{call dailytxn_collectsave(?, ?, ?, ?, ?, ?, ?, ?, ?)}");    
		{
			//OUT Parameters
			statement.registerOutParameter(9, Types.VARCHAR);
			//IN Parameters
			//statement.setString(1, "");
			statement.setDate(1, collect.getTxndate());        
			statement.setString(2, collect.getLabour_shift());
			statement.setString(3, collect.getCollect_type());
			statement.setFloat(4, collect.getCollect_amt());
			statement.setString(5, collect.getComments());
			statement.setString(6, collect.getChangedby());
			statement.setDate(7, collect.getChangeddate());
			statement.setTime(8, collect.getChangedtime());
			statement.execute();
			upd_type = statement.getString(9);
			statement.close();
			disconnect();
			return upd_type;
		}
	}     
	//Save Cashtxn
	public String saveCashtxncollect(Collect collect) throws SQLException {
		String upd_type = null;
		Float collect_amt = Float.parseFloat("0"), difference = Float.parseFloat("0");
		connect();
		CallableStatement statement = jdbcConnection.prepareCall("{call dailytxn_cashtxnsave(?, ?, ?, ?, ?, ?, ?, ?, ?)}");    
		{
			//OUT Parameters
			statement.registerOutParameter(9, Types.VARCHAR);
			//IN Parameters
			//statement.setString(1, "");
			statement.setDate(1, collect.getTxndate());        
			statement.setString(2, collect.getLabour_shift());
			statement.setFloat(3, collect.getAmtinhand());
			statement.setFloat(4, collect_amt);        
			statement.setFloat(5, difference);
			statement.setString(6, collect.getChangedby());
			statement.setDate(7, collect.getChangeddate());
			statement.setTime(8, collect.getChangedtime());
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