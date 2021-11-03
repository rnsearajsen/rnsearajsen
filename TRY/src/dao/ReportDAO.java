package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.Report;
import main.Sub_Group;

public class ReportDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public ReportDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
	//******************************************************		
	//Get Subgroup based on Group
	public List<Sub_Group> listSalesSub_Groups(String fpgroup) throws SQLException{
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
	//******************************************************
	//Sales Report
	public List<Report> listSalesrpt(String fpsubgroup, Date fpsdate, Date fpedate, String rptype, String month, String year) throws SQLException{
		List<Report> listSales_rpt = new ArrayList<>();
		String sql = null;
		if (rptype.contentEquals("daily") ) {
			sql = "select product, salesdate , sum(sales_qty) as sales_qty, sum(testqty) as testqty, sum(total_sales_price) as total_sales_price, uom from sales where salesdate between ? and ? " + 
					"and subgroup_name = ? group by product desc, salesdate ";
		}else if (rptype.contentEquals("monthly")) {
			sql = "select product, salesdate , sum(sales_qty) as sales_qty, sum(testqty) as testqty, sum(total_sales_price) as total_sales_price, uom from sales where YEAR(salesdate) = ? and month(salesdate) = ? " + 
					"and subgroup_name = ? group by product";
		}else if (rptype.contentEquals("yearly")) {
			sql = "select product, salesdate , sum(sales_qty) as sales_qty, sum(testqty) as testqty, sum(total_sales_price) as total_sales_price, uom from sales where YEAR(salesdate) = ? " + 
					"and subgroup_name = ? group by product, MONTH(salesdate)";
		}
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		if (rptype.contentEquals("daily") ) {
			statement.setDate(1, fpsdate);	statement.setDate(2, fpedate); statement.setString(3, fpsubgroup);
		}else if (rptype.contentEquals("monthly")) {
			statement.setString(1, year);	statement.setString(2, month); statement.setString(3, fpsubgroup);	
		}else if (rptype.contentEquals("yearly")) {
			statement.setString(1, year);	statement.setString(2, fpsubgroup);
		}
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {

			String product = resultSet.getString("product");
			Date salesdate = resultSet.getDate("salesdate");	
			Float sales_qty = resultSet.getFloat("sales_qty");
			Float testqty = resultSet.getFloat("testqty");
			Float tot_price = resultSet.getFloat("total_sales_price");

			Report salesrpt = new Report(product, salesdate, sales_qty, testqty, tot_price);
			listSales_rpt.add(salesrpt);
		}		
		resultSet.close();
		statement.close();

		disconnect();		
		return listSales_rpt;
	}
}
