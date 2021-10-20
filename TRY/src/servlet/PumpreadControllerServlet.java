package servlet;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.sql.Date;
import java.util.List;
//import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import dao.PumpreadDAO;
import dao.SalesDAO;
import main.Pumpread;
import main.Sales;
import main.User;


@WebServlet(urlPatterns = {"/pumpread_listajax", "/pumpread_fuelsave"})

public class PumpreadControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PumpreadDAO pumpreadDAO;
	private SalesDAO salesDAO;
	private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String TIME_FORMAT = "HH:mm:ss";
	private String constant_petrol = "petrol", constant_diesel = "diesel",
			constant_pnozzle = "NOZZLE_PETROL", constant_dnozzle = "NOZZLE_DIESEL";

	public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        pumpreadDAO = new PumpreadDAO(jdbcURL, jdbcUsername, jdbcPassword);      
        salesDAO = new SalesDAO(jdbcURL, jdbcUsername, jdbcPassword);
	}
   //@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);		
	}
//*******************************************************************	
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
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();		
		try {      
            switch (action) {
            case "/pumpread_listajax":
            	listajaxPumpread(request,response);
                break;
            case "/pumpread_fuelsave":
            	fuelsavePumpread(request,response);
                break;
            default:
               // getPumpread(request, response);
                break;
            }
        } 
		catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }	

	//ListAJAX Pumpread 
	private void listajaxPumpread(HttpServletRequest request, HttpServletResponse response)
	      throws SQLException, IOException, ServletException {
	//Get Parameters
		String product = request.getParameter("product");
		String shift = request.getParameter("shift");
	    String sdate = request.getParameter("date");
	    Float salesprice = Float.parseFloat(request.getParameter("salesprice"));
	    Date date = convertStringToDate(sdate);
	    String nozzle = null;
	    String sdate_flag = request.getParameter("sdate_flag");
//Set NOZZLE Group based on Product
	    if	(product.toLowerCase().contains(constant_petrol)) {
	    	nozzle = constant_pnozzle;
	    }else if(product.toLowerCase().contains(constant_diesel)) {
	    	nozzle = constant_dnozzle;
	    }
	  List<Pumpread> listPumpread = pumpreadDAO.listRangePumpreads(product,date,shift,salesprice,nozzle,sdate_flag);
	 
	//AJAX    
	  String json = new Gson().toJson(listPumpread);
	  
	  response.setContentType("application/json");
	  response.setCharacterEncoding("UTF-8");
	  response.getWriter().write(json);
	}
	
//Fuel Save - Pump read
	private void fuelsavePumpread(HttpServletRequest request, HttpServletResponse response)
		      throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession(false);
		String message = null;
		String return_type = null ;		
		Float total_salesqty = 0.0f ;
		Sales sales = null;
		//String[] myJsonData = request.getParameterValues("json");
//Get Array data [table format process]
		String pumpreads =request.getParameter("pumpreads");
		Gson gson = new Gson(); 
		//gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
		GsonBuilder gSonBuilder=  new GsonBuilder();
	    gSonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
	    gSonBuilder.registerTypeAdapter(Time.class, new TimeDeserializer());
	    gson = gSonBuilder.create();
				
		Type PumpreadType = new TypeToken<List<Pumpread>>(){}.getType();
		List<Pumpread> prarray= gson.fromJson(pumpreads,PumpreadType); 
		int i = 0;
		for(Pumpread pumpread :prarray ) {
			i++;
//Fuel Pump Read Update			
			return_type = pumpreadDAO.savePumpread(pumpread);
			if (return_type == null) {
//Set Error Message
				String pump_id = pumpread.getPump_id();
			message = "Error While updating reading for Nozzle:"+pump_id+"Please Check & Save Again!!!";
			break;
			}
//Total Sales Qty		
			//Float sales_qty =  pumpread.getSales_qty().floatValue();
			total_salesqty = total_salesqty.floatValue() + pumpread.getSales_qty().floatValue();
//For last Entry do the Fuel Sales save
			if (i == prarray.size()) {
//Fuel Save		
			String product = pumpread.getProduct();    
		    Date salesdate = pumpread.getDate();
		    String labour_shift = pumpread.getLabour_shift();
		    Float sales_priceunit = pumpread.getSales_priceunit();//Float.parseFloat(request.getParameter("sales_priceunit"));
		    Float testqty = pumpread.getTestqty();
		    total_salesqty = total_salesqty - testqty;
		    String subgroup_name = "fuel";
		    //Float sales_qty = total_salesqty;
		    Float taxpercent_total = 0.0f; String comments = "";		    
		    User user = (User) session.getAttribute("user");
		    String changedby = null;
		    if (user != null) {
		    	changedby = user.getFirstname();
		    }
//Date		    
		    long millis=System.currentTimeMillis();  
	        java.sql.Date changeddate=new java.sql.Date(millis); 
//Time
		    LocalTime now = LocalTime.now();		    
	        Time changedtime = Time.valueOf( now );
	        sales = new Sales(product,salesdate,labour_shift,sales_priceunit,subgroup_name, total_salesqty, testqty, taxpercent_total ,comments, changedby, changeddate, changedtime);
			}
		}			
//Set Success Message
		if (return_type != null) {		
			return_type = null;
			return_type = salesDAO.saveSalesfuel(sales);
		if (return_type.contentEquals("Nostock")) {
			message = "No Stock!!! Please Maintain Purchase Stock!!!";
		}else {
		message = "All below Pump readings " + return_type + " successfully";
		}
		}
		//AJAX    
	    response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(message);

	}
}
