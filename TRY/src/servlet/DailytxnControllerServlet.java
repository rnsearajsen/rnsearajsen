package servlet;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import dao.DailytxnDAO;
import main.Dailytxn;
import main.Sub_Group;

@WebServlet(urlPatterns = {"/dlytxnupd", "/expense_ajax", "/dailytxn_exppendajax", "/dailytxn_expensesave"})

public class DailytxnControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DailytxnDAO dailytxnDAO; 
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final String TIME_FORMAT = "HH:mm:ss";
	private String constant_shift = "labour_shift", constant_txntype = "txn_type";

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

		dailytxnDAO = new DailytxnDAO(jdbcURL, jdbcUsername, jdbcPassword);            
	}
	//@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);		
	}
	//Date Conversion
	public Date convertStringToDate(String dateString) throws ServletException
	{
		Date sdate = null; //sql.date
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");    
		if (dateString != "") {
			try{
				java.util.Date udate = sdf.parse(dateString);
				//date = Date.valueOf(dateString);
				sdate = new Date(udate.getTime());
			}
			catch ( ParseException ex ){
				throw new ServletException(ex);	    	
				// System.out.println(ex);
			}
		}
		return sdate;
	}
	//Time Conversion
	//Date Conversion
	public Time convertStringToTime(String timeString) throws ServletException 
	{
		Time stime = null; //sql.time
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");    
		if (timeString != "") {
			try{
				java.util.Date utime = sdf.parse(timeString);
				//date = Date.valueOf(dateString);
				stime = new Time(utime.getTime());
			}
			catch ( ParseException ex ){
				throw new ServletException(ex);
				//System.out.println(ex);
			}
		}
		return stime;
	}
	//*******************************************************************
	//GSON - Date Conversion
	private class DateDeserializer implements JsonDeserializer<Date> {

		@Override
		public Date deserialize(JsonElement jsonElement, Type typeOF,
				JsonDeserializationContext context) throws JsonParseException {
			try {
				java.util.Date utdate = new SimpleDateFormat(DATE_FORMAT, Locale.US).parse(jsonElement.getAsString());
				//Convert Util Date to SQL date
				Date sqldate = new Date(utdate.getTime());
				return sqldate;
			} catch (ParseException e) {
			}

			throw new JsonParseException("Unparseable date: \"" + jsonElement.getAsString()
			+ "\". Supported formats: " + DATE_FORMAT);
		}
	}
	//GSON - Time Deserializer
	private class TimeDeserializer implements JsonDeserializer<Time> {

		@Override
		public Time deserialize(JsonElement jsonElement, Type typeOF,
				JsonDeserializationContext context) throws JsonParseException {
			try {

				String s = jsonElement.getAsString();
				SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT, Locale.US);
				sdf.parse(s);
				long ms = sdf.parse(s).getTime();
				Time t = new Time(ms);
				return t;
			} catch (ParseException e) {
			}
			throw new JsonParseException("Unparseable time: \"" + jsonElement.getAsString()
			+ "\". Supported formats: " + TIME_FORMAT);
		}
	}		
	//*******************************************************************
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();		
		try {      
			switch (action) {
			case "/dlytxnupd":
				Daily_txnupd(request,response);
				break;
			case "/expense_ajax":
				Expense_ajax(request,response);
				break;
			case "/dailytxn_exppendajax":
				Dailytxn_exppendajax(request,response);
				break;
			case "/dailytxn_expensesave":
				Dailytxn_exppensesave(request,response);
				break;
			}
		} 
		catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}	

	//Main Page Sales
	private void Daily_txnupd(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		List<Sub_Group> listShift = dailytxnDAO.listDailytxnSub_Groups(constant_shift);
		List<Sub_Group> listMop = dailytxnDAO.listDailytxnSub_Groups(constant_txntype);
		Date lastsalesdate = dailytxnDAO.getlasttxndate();
		//List<Sub_Group> listSub_Groupuom = dailytxnDAO.listSalesSub_Groups(constant_uom);
		request.setAttribute("listShift", listShift);
		request.setAttribute("listMop", listMop);
		request.setAttribute("lastsalesdate", lastsalesdate);
		//request.setAttribute("listSub_Groupuom", listSub_Groupuom);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Daily_Txn/Daily_Update.jsp");
		dispatcher.include(request, response);
	}
	//Expense Details
	private void Expense_ajax(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String group_name = request.getParameter("subgroup_name");
		String shift = request.getParameter("shift");
		//SQL Date Conversion		
		String date = request.getParameter("date");
		Date edate = convertStringToDate(date);
		List<Dailytxn> listExptype = dailytxnDAO.listExpense(group_name,shift,edate);

		//AJAX    
		String json = new Gson().toJson(listExptype);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}					
	//Expense - Get remaining 'Expense Type' which are not in sales for given Txn Date & Shift
	private void Dailytxn_exppendajax(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String group_name = request.getParameter("subgroup_name");
		String shift = request.getParameter("shift");
		//SQL Date Conversion
		String date = request.getParameter("date");
		Date sdate = convertStringToDate(date);
		List<Dailytxn> listExpensepend = dailytxnDAO.listRangeExppend(group_name,shift,sdate);						 
		//AJAX    
		String json = new Gson().toJson(listExpensepend);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}		

	//Daily Txn Expense Save
	private void Dailytxn_exppensesave(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		//HttpSession session = request.getSession(false);
		//Get Array data [table format process]
		String expenses = request.getParameter("expenseupds"), return_type = null, 
				message = null, expense_type = null;
		//Float expense_amt = null;
		Gson gson = new Gson(); 
		//gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
		GsonBuilder gSonBuilder=  new GsonBuilder();
		gSonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
		gSonBuilder.registerTypeAdapter(Time.class, new TimeDeserializer());
		gson = gSonBuilder.create();

		Type ExpenseType = new TypeToken<List<Dailytxn>>(){}.getType();
		List<Dailytxn> expensearray= gson.fromJson(expenses,ExpenseType); 
		int i = 0;
		//Expense Update
		for(Dailytxn expense :expensearray ) {
			i++;
			//Expense Read Update								            					           
			return_type = dailytxnDAO.saveDailytxnexp(expense);
			expense_type = expense.getExpense_type();
			//expense_amt = expense.getExpense_amt();
			if (return_type == null) {
				//Set Error Message
				message = "Error While updating Expense Type:"+expense_type+" Please Check & Save Again!!!";
				break;							
			}
			//For last Entry do the Cash save
			if (i == expensearray.size()) {														
				message = "All Expense Txns Saved successfully";
				//Update 'Cashtxn'
				return_type = dailytxnDAO.saveCashtxnexp(expense);	
				if (return_type == null) {
					//Set Error Message
					message = "Error While updating Cash Txn: Please Check!!!";
					break;							
				}												
			}}
		//AJAX    
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(message);
	}					
}
