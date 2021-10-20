package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Types;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;

import main.Pumpread;

public class PumpreadDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection; 

	public PumpreadDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
	//Initial New Entry Array-List preparation
	public List<Pumpread> listRangePumpreads(String fpproduct, Date fpdate, String fpshift, Float fpsalesprice, String fpnozzle, String fpsdate_flag) throws SQLException{
		List<Pumpread> listPumpread = new ArrayList<>();

		Pumpread liststqty = null;
		String sql = null;
		boolean initial_flag = true, flag_exist = true;
		String product, pump_id, labour_shift, changedby;
		Date date, changeddate;
		Time changedtime;
		Float sales_priceunit, pump_reading_open, pump_reading_close, sales_qty, testqty = 0.0f;
		Pumpread pumpread = null;
		//String listpumpid; String[] arrpumpid ; 
		String strpumpid = null, sales_upd = null;
		//Get Testqty
		List<Pumpread> listsalestqty = get_testqty(fpproduct, fpdate, fpshift, fpsalesprice);
		sql = "SELECT * FROM pumpread WHERE product = ? and date = ? and labour_shift = ? and sales_priceunit = ?  order by pumpread.pump_id";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);

		statement.setString(1, fpproduct);
		statement.setDate(2, fpdate);
		statement.setString(3, fpshift);
		statement.setFloat(4, fpsalesprice);

		ResultSet resultSet = statement.executeQuery();
		//Pump Reading details         	
		while (resultSet.next()) {
			product = resultSet.getString("product");
			date = resultSet.getDate("date");
			pump_id = resultSet.getString("pump_id");
			labour_shift = resultSet.getString("labour_shift");
			sales_priceunit = resultSet.getFloat("sales_priceunit");
			pump_reading_open = resultSet.getFloat("pump_reading_open");
			pump_reading_close = resultSet.getFloat("pump_reading_close");      
			sales_qty = resultSet.getFloat("sales_qty");
			changedby = resultSet.getString("changedby");
			changeddate = resultSet.getDate("changeddate");
			changedtime = resultSet.getTime("changedtime");            
			//Get Test Qty
			liststqty = listsalestqty.get(0);
			testqty = liststqty.getTestqty();

			pumpread = new Pumpread(product, date, pump_id, labour_shift, sales_priceunit, pump_reading_open ,pump_reading_close,sales_qty, testqty, changedby, changeddate, changedtime);
			listPumpread.add(pumpread);
			//Get Populated Pumpid
			if (strpumpid == null) {
				strpumpid = "'"+pump_id+"'";
			}else {
				strpumpid = strpumpid+",'"+pump_id+"'";
			}
			initial_flag = false;
		}

		resultSet.close();
		statement.close();
		//Get Previous Close Pump readings of last date
		if	(initial_flag == true) {
			sql = "select pump_id, max(pump_reading_close) as pump_reading_close from pumpread where product = ? and date = (Select max(date) from pumpread where product = ?) group by pump_id";

			statement = jdbcConnection.prepareStatement(sql);
			statement.setString(1, fpproduct);
			statement.setString(2, fpproduct);

			resultSet = statement.executeQuery();
			//Pump Reading details         	
			while (resultSet.next()) {
				product = fpproduct;
				date = null;
				pump_id = resultSet.getString("pump_id");
				labour_shift = null;
				sales_priceunit = null;
				//For Sales Date less than default Sales Date, then Leave 'Pump reading open' blank 	            
				if (fpsdate_flag.contentEquals("lower") ) {
					pump_reading_open = null;
				}else {
					pump_reading_open = resultSet.getFloat("pump_reading_close");
				}
				pump_reading_close = null;      
				sales_qty = null;
				changedby = null;
				changeddate = null;
				changedtime = null;            

				pumpread = new Pumpread(product, date, pump_id, labour_shift, sales_priceunit, pump_reading_open ,pump_reading_close,sales_qty, changedby, changeddate, changedtime);
				listPumpread.add(pumpread);
				//Get Populated Pumpid
				if (strpumpid == null) {
					strpumpid = "'"+pump_id+"'";
				}else {
					strpumpid = strpumpid+",'"+pump_id+"'";
				}
				//Flag set          
				initial_flag = false;
				flag_exist = false;
				sales_upd = "No";
			}
			resultSet.close();
			statement.close();    
		}

		//Get Remaining Nozzle Pump ID (Initial Entry / if anything newly added)
		if (initial_flag == false) {
			//Check Whether Sales Entry already exists in 'Sales' table
			if (flag_exist == true) {        		
				sales_upd = sales_existence_check(pumpread);
			}        	
			if (sales_upd.contentEquals("exists")) {
				pump_id = "exists";
				pumpread = new Pumpread(pump_id);
				listPumpread.add(pumpread);
				sql = null;
			}else {
				sql = "select fsub_group from sub_group where fsub_group not in ("+strpumpid+") and fgroup = ?";
			}
		}else {
			sql = "SELECT fsub_group  FROM sub_group where fgroup = ?";        	

		}
		if (sql != null) {
			statement = jdbcConnection.prepareStatement(sql);
			statement.setString(1, fpnozzle);

			resultSet = statement.executeQuery();             
			//Pump Reading details         	
			while (resultSet.next()) {
				product = fpproduct;
				date = null;
				pump_id = resultSet.getString("fsub_group");
				labour_shift = null;
				sales_priceunit = null;
				pump_reading_open = null;
				pump_reading_close = null;      
				sales_qty = null;
				changedby = null;
				changeddate = null;
				changedtime = null;            

				pumpread = new Pumpread(product, date, pump_id, labour_shift, sales_priceunit, pump_reading_open ,pump_reading_close,sales_qty, changedby, changeddate, changedtime);
				listPumpread.add(pumpread);
				//}
			}

			resultSet.close();
			statement.close();       
		}
		disconnect();

		return listPumpread;
	}
	// Insert an Entry in 'Pumpread' Table
	public String savePumpread(Pumpread pumpread) throws SQLException {
		String upd_type = null;
		connect();
		CallableStatement statement = jdbcConnection.prepareCall("{call fuel_prsave(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");    	
		{	    	

			//OUT Parameters
			statement.registerOutParameter(12, Types.VARCHAR);
			//IN Parameters    	
			statement.setString(1, pumpread.getProduct());
			statement.setDate(2, pumpread.getDate());
			statement.setString(3, pumpread.getPump_id());
			statement.setString(4, pumpread.getLabour_shift());
			statement.setFloat(5, pumpread.getSales_priceunit());
			statement.setFloat(6, pumpread.getPump_reading_open());
			statement.setFloat(7, pumpread.getPump_reading_close());
			statement.setFloat(8, pumpread.getSales_qty());
			statement.setString(9, pumpread.getChangedby());
			statement.setDate(10, pumpread.getChangeddate());
			statement.setTime(11, pumpread.getChangedtime());

			statement.execute();
			upd_type = statement.getString(12);

			statement.close();
			disconnect();
			return upd_type;
		}
	}
	//*************************************************************************************	
	//Check Whether Sales Entry already exists in 'Sales' table
	public String sales_existence_check(Pumpread pumpread) throws SQLException {
		String lv_existence = null;

		String sql = "select product from sales where product = ? and salesdate = ? and labour_shift = ? and sales_priceunit = ? limit 1;"; 

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);

		statement.setString(1, pumpread.getProduct());
		statement.setDate(2, pumpread.getDate());     
		statement.setString(3, pumpread.getLabour_shift());
		statement.setFloat(4, pumpread.getSales_priceunit());

		ResultSet resultSet = statement.executeQuery();
		//Pump Reading details         	
		while (resultSet.next()) {
			lv_existence = "exists";
		}
		resultSet.close();
		statement.close();

		return lv_existence;

	}
	//*************************************************************************************	
	public List<Pumpread> get_testqty(String fpproduct, Date fpdate, String fpshift, Float fpsalesprice) throws SQLException {
		Float lv_testqty = 0.0f; String product = null;
		List<Pumpread> listsalestqty = new ArrayList<>();
		String sql = "select product, testqty from sales where product = ? and salesdate = ? and labour_shift = ? and sales_priceunit = ? limit 1;"; 
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);

		statement.setString(1, fpproduct);
		statement.setDate(2, fpdate);     
		statement.setString(3, fpshift);
		statement.setFloat(4, fpsalesprice);

		ResultSet resultSet = statement.executeQuery();
		//Pump Reading details         	
		while (resultSet.next()) {
			product = resultSet.getString("product");
			lv_testqty = resultSet.getFloat("testqty");
		}
		Pumpread tqty = new Pumpread(product, lv_testqty); 
		listsalestqty.add(tqty);
		resultSet.close();
		statement.close();
		disconnect();
		return listsalestqty;

	}
}
