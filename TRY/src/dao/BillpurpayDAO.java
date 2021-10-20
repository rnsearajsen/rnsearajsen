package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import main.Billpurpay;
import main.Cashtxn_cmn;
import main.Sub_Group;

public class BillpurpayDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public BillpurpayDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
  //***************************************************************************************    
    public List<Billpurpay> listBillpurpay() throws SQLException{
    	List<Billpurpay> listBillpurpay = new ArrayList<>();    
		String sql = null;
		Billpurpay billpurpay = null;

		sql = "SELECT * FROM billpurpay WHERE paid = '' order by bill_no, bill_date";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		
		ResultSet resultSet = statement.executeQuery();
		//Billpurpay read
		while (resultSet.next()) {
			int bill_no = resultSet.getInt("bill_no");
			Date bill_date = resultSet.getDate("bill_date");
			String bill_prdgrp = resultSet.getString("bill_prdgrp");
			Float bill_amt = resultSet.getFloat("bill_amt");
			Float discount = resultSet.getFloat("discount");
			Float interest = resultSet.getFloat("interest");
			Float paymnt_amt = resultSet.getFloat("paymnt_amt");
			Date paymnt_date = resultSet.getDate("paymnt_date");
			String paid	= resultSet.getString("paid");
			
			billpurpay = new Billpurpay(bill_no, bill_date, bill_prdgrp, bill_amt, discount, interest, paymnt_amt,
					paymnt_date, paid);
			listBillpurpay.add(billpurpay);
		}
		resultSet.close();
		statement.close();

		disconnect();
    	return listBillpurpay;
    }
//***************************************************************************************    
	// Get 'Sub_Group' Data
	public List<Sub_Group> listBillpurpaySub_Groups(String fpgroup) throws SQLException{
		List<Sub_Group> listSub_Group = new ArrayList<>();
		Cashtxn_cmn listcashtxn = null;
		//Get Current Total Amount in Hand
		List<Cashtxn_cmn> listCashtxn = get_amtinhand();
		
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
			//Get Amount in Hand Details
			listcashtxn = listCashtxn.get(0);
			Float amtinhand = listcashtxn.getAggramt();	
			
			Sub_Group sub_group = new Sub_Group(subgrp_id, fsub_group, fgroup, comments, amtinhand);
			listSub_Group.add(sub_group);
		}

		resultSet.close();
		statement.close();

		disconnect();

		return listSub_Group;
	}
//***************************************************************************************
	//Save Bill Purchase  
	public String saveBillpurpay(Billpurpay billpurpay) throws SQLException {
		String upd_type = null;
		connect();
		CallableStatement statement = jdbcConnection.prepareCall("{call billpurpay_save(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");    
		{
			//OUT Parameters
			statement.registerOutParameter(13, Types.VARCHAR);
			//IN Parameters
			//statement.setString(1, "");
			statement.setInt(1, billpurpay.getBill_no());        
			statement.setDate(2, billpurpay.getBill_date());
			statement.setString(3, billpurpay.getBill_prdgrp());
			statement.setFloat(4, billpurpay.getBill_amt());
			statement.setFloat(5, billpurpay.getDiscount());
			statement.setFloat(6, billpurpay.getInterest());
			statement.setFloat(7, billpurpay.getPaymnt_amt());
			statement.setDate(8, billpurpay.getPaymnt_date());
			statement.setString(9, billpurpay.getPaid());
			statement.setString(10, billpurpay.getChangedby());
			statement.setDate(11, billpurpay.getChangeddate());
			statement.setTime(12, billpurpay.getChangedtime());
			statement.execute();
			upd_type = statement.getString(13);
			statement.close();
			disconnect();
			return upd_type;
		}
	}
//***************************************************************************************	
	//Save Cashtxn
	public String saveCashtxnexp(Billpurpay billpurpay) throws SQLException {
		String upd_type = null;
		Float collect_amt = Float.parseFloat("0"), difference = Float.parseFloat("0");
		String shift = "dummy"; 
		connect();
		CallableStatement statement = jdbcConnection.prepareCall("{call dailytxn_cashtxnsave(?, ?, ?, ?, ?, ?, ?, ?, ?)}");    
		{
			//OUT Parameters
			statement.registerOutParameter(9, Types.VARCHAR);
			//IN Parameters
			//statement.setString(1, "");
			statement.setDate(1, billpurpay.getTxndate());        
			statement.setString(2, shift);
			statement.setFloat(3, billpurpay.getAmtinhand());
			statement.setFloat(4, collect_amt);        
			statement.setFloat(5, difference);
			statement.setString(6, billpurpay.getChangedby());
			statement.setDate(7, billpurpay.getChangeddate());
			statement.setTime(8, billpurpay.getChangedtime());
			statement.execute();
			upd_type = statement.getString(9);
			statement.close();
			disconnect();
			return upd_type;
		}
	}
//***************************************************************************************	
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
