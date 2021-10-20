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

import dao.CollectDAO;
import main.Collect;

@WebServlet(urlPatterns = { "/collect_ajax", "/dailytxn_collectpendajax", "/dailytxn_collectsave"})

public class CollectControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CollectDAO collectDAO; 
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final String TIME_FORMAT = "HH:mm:ss";
	private String constant_collect = "collection_type";

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

		collectDAO = new CollectDAO(jdbcURL, jdbcUsername, jdbcPassword);            
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
			case "/collect_ajax":
				Collect_ajax(request,response);
				break;
			case "/dailytxn_collectpendajax":
				Dailytxn_collectpendajax(request,response);
				break;
			case "/dailytxn_collectsave":
				Dailytxn_collectsave(request,response);
				break;
			}
		} 
		catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}	
	//Collect Details
	private void Collect_ajax(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String group_name = constant_collect; //request.getParameter("subgroup_name");
		String shift = request.getParameter("shift");
		//SQL Date Conversion		
		String date = request.getParameter("date");
		Date edate = convertStringToDate(date);
		List<Collect> listCollecttype = collectDAO.listCollect(group_name,shift,edate);

		//AJAX    
		String json = new Gson().toJson(listCollecttype);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}					
	//Collect - Get remaining 'Collect Type' which are not in sales for given Txn Date & Shift
	private void Dailytxn_collectpendajax(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String group_name = constant_collect;
		String shift = request.getParameter("shift");
		//SQL Date Conversion
		String date = request.getParameter("date");
		Date sdate = convertStringToDate(date);
		List<Collect> listCollectpend = collectDAO.listRangeCollectpend(group_name,shift,sdate);						 
		//AJAX    
		String json = new Gson().toJson(listCollectpend);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}		

	//Daily Txn Collect Save
	private void Dailytxn_collectsave(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		//HttpSession session = request.getSession(false);
		//Get Array data [table format process]
		String collects = request.getParameter("collectupds"), return_type = null, 
				message = null, collect_type = null;
		//Float collect_amt = null;
		Gson gson = new Gson(); 
		//gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
		GsonBuilder gSonBuilder=  new GsonBuilder();
		gSonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
		gSonBuilder.registerTypeAdapter(Time.class, new TimeDeserializer());
		gson = gSonBuilder.create();

		Type CollectType = new TypeToken<List<Collect>>(){}.getType();
		List<Collect> collectarray= gson.fromJson(collects,CollectType); 
		int i = 0;
		//Collect Update
		for(Collect collect :collectarray ) {
			i++;
			//Collect Read Update								            					           
			return_type = collectDAO.saveDailytxncollect(collect);
			collect_type = collect.getCollect_type();
			//collect_amt = collect.getCollect_amt();
			if (return_type == null) {
				//Set Error Message
				message = "Error While updating Collect Type:"+collect_type+" Please Check & Save Again!!!";
				break;							
			}
			//For last Entry do the Cash save
			if (i == collectarray.size()) {														
				message = "All Collect Txns Saved successfully";
				//Update 'Cashtxn'
				return_type = collectDAO.saveCashtxncollect(collect);	
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
