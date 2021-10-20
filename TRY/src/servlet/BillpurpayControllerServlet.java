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

import dao.BillpurpayDAO;
import main.Billpurpay;
import main.Sub_Group;

@WebServlet(urlPatterns = {"/Billpurpay", "/billpurpay_ajax", "/billpurpayssave"})

public class BillpurpayControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BillpurpayDAO billpurpayDAO; 
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final String TIME_FORMAT = "HH:mm:ss";
	private String constant_billprd = "Bill_Product";
	public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        billpurpayDAO = new BillpurpayDAO(jdbcURL, jdbcUsername, jdbcPassword);            
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
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			String action = request.getServletPath();		
			try {      
	            switch (action) {
	            case "/Billpurpay":
	            	Billpurpay(request,response);
	                break;
	            case "/billpurpayssave":
	            	Billpurpayssave(request,response);
	                break;
	            case "/billpurpay_ajax":
	            	Billpurpay_ajax(request,response);
	                break;
	            default:
	                // getPurchase(request, response);
	                 break;
	             }
	         } 
	 		catch (SQLException ex) {
	             throw new ServletException(ex);
	         }
	     }	
//********************************************************************************************
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
		//Main Page Billpurpay
		private void Billpurpay(HttpServletRequest request, HttpServletResponse response)
		        throws SQLException, IOException, ServletException {
			List<Sub_Group> listBillprd = billpurpayDAO.listBillpurpaySub_Groups(constant_billprd);
			
			request.setAttribute("listBillprd", listBillprd);
			
		    RequestDispatcher dispatcher = request.getRequestDispatcher("Billpurpay/Billpurpay_initial.jsp");
		    dispatcher.include(request, response);
		}
//********************************************************************************************
//Get Purchase Bill Details
		private void Billpurpay_ajax(HttpServletRequest request, HttpServletResponse response)
		        throws SQLException, IOException, ServletException {
			List<Billpurpay> listBillpurpay = billpurpayDAO.listBillpurpay();

			//AJAX    
			String json = new Gson().toJson(listBillpurpay);

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);			
		}
		
//********************************************************************************************
		//Save
				private void Billpurpayssave(HttpServletRequest request, HttpServletResponse response)
				        throws SQLException, IOException, ServletException {
					//Get Array data [table format process]
					String billpurpays = request.getParameter("billpurpayupds"), return_type = null,
							message = null; int bill_no = 0;
					Gson gson = new Gson(); 
					//gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
					GsonBuilder gSonBuilder=  new GsonBuilder();
					gSonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
					gSonBuilder.registerTypeAdapter(Time.class, new TimeDeserializer());
					gson = gSonBuilder.create();
				
					Type Billpurayelm = new TypeToken<List<Billpurpay>>(){}.getType();
					List<Billpurpay> billpurpayarray= gson.fromJson(billpurpays,Billpurayelm); 
					int i = 0;
//Update
					for(Billpurpay billpurpay :billpurpayarray ) {
						i++;
						//Expense Read Update								            					           
						return_type = billpurpayDAO.saveBillpurpay(billpurpay);
						bill_no = billpurpay.getBill_no();
						//expense_amt = expense.getExpense_amt();
						if (return_type == null) {
							//Set Error Message
							message = "Error While updating Bill no:"+bill_no+" Please Check & Save Again!!!";
							break;							
						}
						//For last Entry do the Cash save
						if (i == billpurpayarray.size()) {														
							message = "All Bill Purchase & Payments Saved successfully";
							//Update 'Cashtxn'
							return_type = billpurpayDAO.saveCashtxnexp(billpurpay);	
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
//********************************************************************************************
				
//********************************************************************************************				
}
