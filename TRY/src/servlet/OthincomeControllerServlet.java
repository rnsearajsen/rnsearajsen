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

//import javax.servlet.RequestDispatcher;
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

import dao.OthincomeDAO;
import main.Othincome;
//import main.Sub_Group;

@WebServlet(urlPatterns = {"/othincome_ajax", "/dailytxn_othincomessave"})

public class OthincomeControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OthincomeDAO othincomeDAO; 
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final String TIME_FORMAT = "HH:mm:ss";
	//private String constant_shift = "labour_shift";

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

		othincomeDAO = new OthincomeDAO(jdbcURL, jdbcUsername, jdbcPassword);            
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
			case "/othincome_ajax":
				Othincome_ajax(request,response);
				break;
			case "/dailytxn_othincomessave":
				Dailytxn_othincomessave(request,response);
				break;
			}
		} 
		catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}	
	//*******************************************************************
	//Other Income Details
	private void Othincome_ajax(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String group_name = request.getParameter("subgroup_name");
		String shift = request.getParameter("shift");
		//SQL Date Conversion		
		String date = request.getParameter("date");
		Date edate = convertStringToDate(date);
		List<Othincome> listOthincome = othincomeDAO.listOthincome(group_name,shift,edate);

		//AJAX    
		String json = new Gson().toJson(listOthincome);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}
	//*******************************************************************					
	//*******************************************************************										
	//Daily Txn Other Income Save
	private void Dailytxn_othincomessave(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		//HttpSession session = request.getSession(false);
		//Get Array data [table format process]
		String othincomes = request.getParameter("othincomeupds"), return_type = null, 
				message = null, inctype = null;
		//Float expense_amt = null;
		Gson gson = new Gson(); 
		//gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
		GsonBuilder gSonBuilder=  new GsonBuilder();
		gSonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
		gSonBuilder.registerTypeAdapter(Time.class, new TimeDeserializer());
		gson = gSonBuilder.create();

		Type IncType = new TypeToken<List<Othincome>>(){}.getType();
		List<Othincome> othincomearray= gson.fromJson(othincomes,IncType); 
		int i = 0;
		//Expense Update
		for(Othincome othincome :othincomearray ) {
			i++;
			//Other Income Read Update								            					           
			return_type = othincomeDAO.saveDailytxnothincome(othincome);
			inctype = othincome.getInctype();
			if (return_type == null) {
				//Set Error Message
				message = "Error While updating Income Type:"+inctype+" Please Check & Save Again!!!";
				break;							
			}
			//For last Entry 
			if (i == othincomearray.size()) {	
				message = "All 'Income Type' Transactions Saved successfully";	
//Update 'Cashtxn'
				return_type = othincomeDAO.saveCashtxnothinc(othincome);	
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