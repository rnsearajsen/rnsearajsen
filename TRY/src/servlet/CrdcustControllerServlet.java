package servlet;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
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

import dao.CrdcustDAO;
import main.Crdcust;
//import main.Sub_Group;

@WebServlet(urlPatterns = {"/crdcust_ajax", "/dailytxn_crdcustpendajax", "/dailytxn_crdcustsave"})

public class CrdcustControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CrdcustDAO crdcustDAO; 
	private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String TIME_FORMAT = "HH:mm:ss";
	//private String constant_shift = "labour_shift";

	public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        crdcustDAO = new CrdcustDAO(jdbcURL, jdbcUsername, jdbcPassword);            
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
//Get First Date of Month
	public Date getFirstDate(String dateString) throws ServletException
	{
	    Date sdate = null, fdate = null; //sql.date
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");    
	    if (dateString != "") {
	    	try {
	    		java.util.Date udate = sdf.parse(dateString);
	    		sdate = new Date(udate.getTime());

	    	Calendar gc = new GregorianCalendar();
	    	gc.setTime(sdate);
			int month = gc.get(Calendar.MONTH);
			gc.set(Calendar.MONTH, month);
			gc.set(Calendar.DAY_OF_MONTH, 1);
			java.util.Date idate = gc.getTime();
			fdate = new Date(idate.getTime());
					//new Date(gc.getTime()); 
	    }
	    catch ( ParseException ex ){
	    	 throw new ServletException(ex);	    	
	       // System.out.println(ex);
	    }
	    }
	    return fdate;
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
			            case "/crdcust_ajax":
			            	Crdcust_ajax(request,response);
			            	break;
			            case "/dailytxn_crdcustpendajax":
			            	Dailytxn_crdcustpendajax(request,response);
			            	break;
			            case "/dailytxn_crdcustsave":
			            	Dailytxn_crdcustsave(request,response);
			            	break;
			            }
			        } 
					catch (SQLException ex) {
			            throw new ServletException(ex);
			        }
			    }	
//*******************************************************************
//Credit Customer Details
					private void Crdcust_ajax(HttpServletRequest request, HttpServletResponse response)
					        throws SQLException, IOException, ServletException {
						String group_name = request.getParameter("subgroup_name");
						String shift = request.getParameter("shift");
				//SQL Date Conversion		
					    String date = request.getParameter("date");
					    Date edate = convertStringToDate(date);
					    Date fdate = getFirstDate(date);
					    List<Crdcust> listExptype = crdcustDAO.listCrdcust(group_name,shift,edate,fdate);
						 
						//AJAX    
						  String json = new Gson().toJson(listExptype);
						  
						  response.setContentType("application/json");
						  response.setCharacterEncoding("UTF-8");
						  response.getWriter().write(json);
					}
//*******************************************************************					
//Credit Customer - Get remaining 'Credit Customer' which are not in saved list for given Txn Date & Shift
					private void Dailytxn_crdcustpendajax(HttpServletRequest request, HttpServletResponse response)
					        throws SQLException, IOException, ServletException {
						String group_name = request.getParameter("subgroup_name");
						String shift = request.getParameter("shift");
//SQL Date Conversion
					    String date = request.getParameter("date");
					    Date sdate = convertStringToDate(date);
					    Date fdate = getFirstDate(date);
					    List<Crdcust> listCrdcustpend = crdcustDAO.listRangecrdcustpend(group_name,shift,sdate,fdate);						 
//AJAX    
						  String json = new Gson().toJson(listCrdcustpend);
						  
						  response.setContentType("application/json");
						  response.setCharacterEncoding("UTF-8");
						  response.getWriter().write(json);
					}		
//*******************************************************************										
//Daily Txn Credit Customer Save
					private void Dailytxn_crdcustsave(HttpServletRequest request, HttpServletResponse response)
						      throws SQLException, IOException, ServletException {
						//HttpSession session = request.getSession(false);
				//Get Array data [table format process]
								String crdcusts = request.getParameter("crdcustupds"), return_type = null, 
										message = null, name = null;
								//Float expense_amt = null;
								Gson gson = new Gson(); 
								//gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
								GsonBuilder gSonBuilder=  new GsonBuilder();
							    gSonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
							    gSonBuilder.registerTypeAdapter(Time.class, new TimeDeserializer());
							    gson = gSonBuilder.create();
										
								Type CustName = new TypeToken<List<Crdcust>>(){}.getType();
								List<Crdcust> crdcustarray= gson.fromJson(crdcusts,CustName); 
								int i = 0;
				//Expense Update
								for(Crdcust crdcust :crdcustarray ) {
									i++;
									//Credit Customer Read Update								            					           
												return_type = crdcustDAO.saveDailytxncrdcust(crdcust);
												name = crdcust.getName();
												if (return_type == null) {
									//Set Error Message
												message = "Error While updating Customer:"+name+" Please Check & Save Again!!!";
												break;							
												}
				//For last Entry 
												if (i == crdcustarray.size()) {	
														message = "All Customer Transactions Saved successfully";		
//Update 'Cashtxn'
											return_type = crdcustDAO.saveCashtxncrdcust(crdcust);	
											if (return_type == null) {
//Set Error Message
           									message = "Error While updating Cash Txn: Please Check!!!";
										break;}
					}}
				//AJAX    
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(message);
				}					
}