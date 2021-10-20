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
import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletConfig;
//import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession; 

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import dao.SalesDAO;
//import main.Pumpread;
import main.Sales;
import main.Sub_Group;
//import servlet.PumpreadControllerServlet.DateDeserializer;
//import servlet.PumpreadControllerServlet.TimeDeserializer;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet(urlPatterns = {"/sales_pktoilajax","/sales_pktoilsave", "/sales_engoilajax", "/sales_engoilsave", "/sales_engprdpendajax",
		                  "/Sales", "/salescreate","/saleschange", "/salesinsert", "/salesedit", "/salesupdate",
		                    "/salesdelete", "/saleslist", "/saleslistajax","/salesdeleteajax", "/salesfilter"})
public class SalesControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SalesDAO salesDAO; 
	private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String TIME_FORMAT = "HH:mm:ss";
	private String constant_product = "product", constant_uom = "uom",
			       constant_shift = "labour_shift";

	public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        salesDAO = new SalesDAO(jdbcURL, jdbcUsername, jdbcPassword);            
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
		
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();		
		try {      
            switch (action) {
            case "/sales_pktoilajax":
            	Sales_pktoil(request,response);
            	break;
            case "/sales_pktoilsave":
            	Sales_pktoilsave(request,response);
            	break;
            case "/sales_engoilajax":
            	Sales_engoil(request,response);
            	break;
            case "/sales_engoilsave":
            	Sales_engoilsave(request,response);
            	break;
            case "/sales_engprdpendajax":
            	Sales_engprdpendajax(request,response);
            	break;
            case "/Sales":
                Sales(request,response);
                break;
            case "/salescreate":
                createSales(request,response);
                break;
            case "/salesinsert":     
                insertSales(request, response);
                break;
            case "/saleschange":
                changeSales(request, response);
                break;                
            case "/salesedit":
                showEditForm(request, response);
                break;
            case "/salesupdate":
                updateSales(request, response);
                break;
            case "/salesdelete":
                deleteSales(request, response);
                break;                
            case "/saleslist":
                listSales(request, response);
                break;
            case "/saleslistajax":
                listajaxSales(request, response);
                break;
            case "/salesdeleteajax":
            	deleteajaxSales(request, response);
                break;
            case "/salesfilter":
                filterSales(request, response);
                break;
            default:
               // getSales(request, response);
                break;
            }
        } 
		catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }	
// Packet Oil Sales Entry
	private void Sales_pktoil(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {
		String subgroup_name = request.getParameter("subgroup_name");
		String shift = request.getParameter("shift");
//SQL Date Conversion		
	    String date = request.getParameter("date");
	    Date sdate = convertStringToDate(date);
	    List<Sales> listPktoil = salesDAO.listRangePktoil(subgroup_name,shift,sdate);
		 
		//AJAX    
		  String json = new Gson().toJson(listPktoil);
		  
		  response.setContentType("application/json");
		  response.setCharacterEncoding("UTF-8");
		  response.getWriter().write(json);
	}
	// Engine Oil Sales Entry
		private void Sales_engoil(HttpServletRequest request, HttpServletResponse response)
		        throws SQLException, IOException, ServletException {
			String subgroup_name = request.getParameter("subgroup_name");
			String shift = request.getParameter("shift");
	//SQL Date Conversion		
		    String date = request.getParameter("date");
		    Date sdate = convertStringToDate(date);
		    List<Sales> listPktoil = salesDAO.listRangePktoil(subgroup_name,shift,sdate);
			 
			//AJAX    
			  String json = new Gson().toJson(listPktoil);
			  
			  response.setContentType("application/json");
			  response.setCharacterEncoding("UTF-8");
			  response.getWriter().write(json);
		}	
// Packet Oil Sales Save

	private void Sales_pktoilsave(HttpServletRequest request, HttpServletResponse response)
		      throws SQLException, IOException, ServletException {
		//HttpSession session = request.getSession(false);
//Get Array data [table format process]
				String pktoils = request.getParameter("pktoils"), return_type = null, 
						message = null, product = null;
				Float sales_qty = null;
				Gson gson = new Gson(); 
				//gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
				GsonBuilder gSonBuilder=  new GsonBuilder();
			    gSonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
			    gSonBuilder.registerTypeAdapter(Time.class, new TimeDeserializer());
			    gson = gSonBuilder.create();
						
				Type PktoilType = new TypeToken<List<Sales>>(){}.getType();
				List<Sales> pktoilarray= gson.fromJson(pktoils,PktoilType); 
				int i = 0;
//Packet Oil Sales Update
				for(Sales pktoil :pktoilarray ) {
					i++;
					//Fuel Pump Read Update								            					           
								return_type = salesDAO.saveSalesfuel(pktoil);
								product = pktoil.getProduct();
								sales_qty = pktoil.getSales_qty();
								if (return_type == null) {
					//Set Error Message
								message = "Error While updating Product:"+product+" Please Check & Save Again!!!";
								break;							
								}else if (return_type.contentEquals("Nostock")) {
								if (sales_qty > 0) {
									message = "No Stock - "+product+"!!! Please Maintain Purchase Stock!!!";
								break;}
								}
//For last Entry do the Fuel Sales save
								if (i == pktoilarray.size()) {	
										message = "All Packet Oils Saved successfully";															
	}}
//AJAX    
response.setContentType("text/plain");
response.setCharacterEncoding("UTF-8");
response.getWriter().write(message);
}
// Engine Oil Sales Save

		private void Sales_engoilsave(HttpServletRequest request, HttpServletResponse response)
			      throws SQLException, IOException, ServletException {
			//HttpSession session = request.getSession(false);
	//Get Array data [table format process]
					String engoils = request.getParameter("engupdoils"), return_type = null, 
							message = null, product = null;
					Float sales_qty = null;
					Gson gson = new Gson(); 
					//gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
					GsonBuilder gSonBuilder=  new GsonBuilder();
				    gSonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
				    gSonBuilder.registerTypeAdapter(Time.class, new TimeDeserializer());
				    gson = gSonBuilder.create();
							
					Type EngoilType = new TypeToken<List<Sales>>(){}.getType();
					List<Sales> engoilarray= gson.fromJson(engoils,EngoilType); 
					int i = 0;
	//Packet Oil Sales Update
					for(Sales engoil :engoilarray ) {
						i++;
						//Fuel Pump Read Update								            					           
									return_type = salesDAO.saveSalesfuel(engoil);
									product = engoil.getProduct();
									sales_qty = engoil.getSales_qty();
									if (return_type == null) {
						//Set Error Message
									message = "Error While updating Product:"+product+" Please Check & Save Again!!!";
									break;							
									}else if (return_type.contentEquals("Nostock")) {
									if (sales_qty > 0) {
										message = "No Stock - "+product+"!!! Please Maintain Purchase Stock!!!";
									break;}
									}
	//For last Entry do the Fuel Sales save
									if (i == engoilarray.size()) {	
											message = "All Engine Oils Saved successfully";															
		}}
	//AJAX    
	response.setContentType("text/plain");
	response.setCharacterEncoding("UTF-8");
	response.getWriter().write(message);
	}
//Create Sales
	private void createSales(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {

		List<Sub_Group> listSub_Group = salesDAO.listSalesSub_Groups(constant_product);
		List<Sub_Group> listShift = salesDAO.listSalesSub_Groups(constant_shift);
		Date lastsalesdate = salesDAO.getlastsalesdate();
		//List<Sub_Group> listSub_Groupuom = salesDAO.listSalesSub_Groups(constant_uom);
	    request.setAttribute("listSub_Group", listSub_Group);
	    request.setAttribute("listShift", listShift);
	    request.setAttribute("lastsalesdate", lastsalesdate);
	    //request.setAttribute("listSub_Groupuom", listSub_Groupuom);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("Sales/Sales_Create.jsp");
	    dispatcher.include(request, response);
	}
	
//Filter Sales
	private void filterSales(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {

		String product = request.getParameter("product");
	    String ssalesdate = request.getParameter("salesdate");    
	    Date salesdate = convertStringToDate(ssalesdate);
   	    //request.setAttribute("selectedSalesId", salesId);		
   	 Sales existingSales = salesDAO.getSales(product, salesdate);
//Dropdown
	List<Sub_Group> listSub_Group = salesDAO.listSalesSub_Groups(constant_product);
	List<Sub_Group> listSub_Groupuom = salesDAO.listSalesSub_Groups(constant_uom);
    request.setAttribute("listSub_Group", listSub_Group);
    request.setAttribute("listSub_Groupuom", listSub_Groupuom);
    
     RequestDispatcher dispatcher = request.getRequestDispatcher("Sales/Sales_Change.jsp");
     request.setAttribute("sales", existingSales);
     dispatcher.forward(request, response);

	}
//Engoil - Get remaining Product of Engine Oil which are not in sales for given Sales Date & Shift
	private void Sales_engprdpendajax(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {
		String subgroup_name = request.getParameter("subgroup_name");
		String shift = request.getParameter("shift");
//SQL Date Conversion		
	    String date = request.getParameter("date");
	    Date sdate = convertStringToDate(date);
	    List<Sales> listEngpendoil = salesDAO.listRangeEngpendoil(subgroup_name,shift,sdate);
		 
		//AJAX    
		  String json = new Gson().toJson(listEngpendoil);
		  
		  response.setContentType("application/json");
		  response.setCharacterEncoding("UTF-8");
		  response.getWriter().write(json);
	}	
	
//List Sales - Not used
private void listSales(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
//Product Sub-Group
	List<Sub_Group> listSub_Group = salesDAO.listSalesSub_Groups(constant_product);
    request.setAttribute("listSub_Group", listSub_Group);
    
   // List<Sales> listSales = salesDAO.listAllSaless();
   // request.setAttribute("listSales", listSales);
    RequestDispatcher dispatcher = request.getRequestDispatcher("Sales/Sales_Display.jsp");
    dispatcher.forward(request, response);
}
//ListAJAX Sales 
private void listajaxSales(HttpServletRequest request, HttpServletResponse response)
      throws SQLException, IOException, ServletException {
//Get Parameters
	String subgroup_name = request.getParameter("subgroup_name");
    String ssalesdate_from = request.getParameter("salesdate_from");    
    Date salesdate_from = convertStringToDate(ssalesdate_from);
    String ssalesdate_to = request.getParameter("salesdate_to");    
    Date salesdate_to = convertStringToDate(ssalesdate_to);
    
  List<Sales> listSales = salesDAO.listRangeSaless(subgroup_name, salesdate_from, salesdate_to);
 
//AJAX    
  String json = new Gson().toJson(listSales);
  
  response.setContentType("application/json");
  response.setCharacterEncoding("UTF-8");
  response.getWriter().write(json);
}
// Delete entry using AJAX
private void deleteajaxSales(HttpServletRequest request, HttpServletResponse response)
	      throws SQLException, IOException, ServletException {
// Delete entry
	String product = request.getParameter("product");
    String ssalesdate = request.getParameter("salesdate");    
    Date salesdate = convertStringToDate(ssalesdate);
    String labour_shift = request.getParameter("labour_shift");
    Float sales_priceunit = Float.parseFloat(request.getParameter("sales_priceunit"));
    Float purchase_priceunit = Float.parseFloat(request.getParameter("purchase_priceunit"));
    Sales sales = new Sales(product, salesdate,labour_shift, sales_priceunit,purchase_priceunit);
    salesDAO.deleteSales(sales);
    String message = ssalesdate+":"+product+"/"+"labour_shift"+"/"+sales_priceunit+":Sales record Deleted Successfully";
  //AJAX    
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(message);  
	}

//Change Sales
private void changeSales(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    List<Sales> listSales = salesDAO.listAllSaless();
    request.setAttribute("listSales", listSales);
    RequestDispatcher dispatcher = request.getRequestDispatcher("Sales/Sales_Change.jsp");
    dispatcher.forward(request, response);
}
//private void showNewForm(HttpServletRequest request, HttpServletResponse response)
//        throws ServletException, IOException {
//    RequestDispatcher dispatcher = request.getRequestDispatcher("Sales_Create.jsp");
//    dispatcher.forward(request, response);
//}

private void showEditForm(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {
	String product = request.getParameter("product");
    String ssalesdate = request.getParameter("salesdate");    
    Date salesdate = convertStringToDate(ssalesdate);
    Sales existingSales = salesDAO.getSales(product, salesdate);
//Dropdown
	List<Sub_Group> listSub_Group = salesDAO.listSalesSub_Groups(constant_product);
	List<Sub_Group> listSub_Groupuom = salesDAO.listSalesSub_Groups(constant_uom);
    request.setAttribute("listSub_Group", listSub_Group);
    request.setAttribute("listSub_Groupuom", listSub_Groupuom);
    
    RequestDispatcher dispatcher = request.getRequestDispatcher("Sales/Sales_Change.jsp");
    request.setAttribute("sales", existingSales);
    dispatcher.forward(request, response);
}

private void insertSales(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    String product = request.getParameter("product");
    String ssalesdate = request.getParameter("salesdate");    
    Date salesdate = convertStringToDate(ssalesdate);
    String labour_shift = request.getParameter("labour_shift");
    Float sales_priceunit = Float.parseFloat(request.getParameter("sales_priceunit")); 
    Float purchase_priceunit = Float.parseFloat(request.getParameter("purchase_priceunit"));
    String spurchasedate = request.getParameter("purchasedate");    
    Date purchasedate = convertStringToDate(spurchasedate);
    String subgroup_name = request.getParameter("subgroup_name");
    Float sales_qty = Float.parseFloat(request.getParameter("sales_qty"));      
    Float total_sales_price = Float.parseFloat(request.getParameter("total_sales_price"));
    String uom = request.getParameter("uom");        
    Float taxpercent_total = Float.parseFloat(request.getParameter("taxpercent_total"));
    Float taxamt_total = Float.parseFloat(request.getParameter("taxamt_total"));
    String comments = request.getParameter("comments");        
    String changedby= request.getParameter("changedby");
//sql date conversion    
    String schangeddate= request.getParameter("changeddate");
    Date changeddate = convertStringToDate(schangeddate);
    String schangedtime= request.getParameter("changedtime");
//sql time conversion   
    Time changedtime = convertStringToTime(schangedtime);
    
    Sales newSales = new Sales(product,salesdate,labour_shift,sales_priceunit,purchase_priceunit,purchasedate,subgroup_name, sales_qty, total_sales_price, uom, taxpercent_total ,taxamt_total, comments, changedby, changeddate, changedtime);
    salesDAO.insertSales(newSales);
    
    String message = product+" :Sales record Created Successfully";    
//AJAX    
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(message);
   // response.getStatus();
// Call Sales Creation page
//    RequestDispatcher dispatcher = request.getRequestDispatcher("Sales/Sales_Create.jsp");
//    dispatcher.forward(request, response);  
}

private void updateSales(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    String product = request.getParameter("product");
    String ssalesdate = request.getParameter("salesdate");    
    Date salesdate = convertStringToDate(ssalesdate);
    String labour_shift = request.getParameter("labour_shift");
    Float sales_priceunit = Float.parseFloat(request.getParameter("sales_priceunit")); 
    Float purchase_priceunit = Float.parseFloat(request.getParameter("purchase_priceunit")); 
    String spurchasedate = request.getParameter("purchasedate");    
    Date purchasedate = convertStringToDate(spurchasedate);    
    String subgroup_name = request.getParameter("subgroup_name");
    Float sales_qty = Float.parseFloat(request.getParameter("sales_qty"));       
    Float total_sales_price = Float.parseFloat(request.getParameter("total_sales_price"));
    String uom = request.getParameter("uom");        
    Float taxpercent_total = Float.parseFloat(request.getParameter("taxpercent_total"));
    Float taxamt_total = Float.parseFloat(request.getParameter("taxamt_total"));
    String comments = request.getParameter("comments");        
    String changedby= request.getParameter("changedby");
//sql date conversion    
    String schangeddate= request.getParameter("changeddate");
    Date changeddate = convertStringToDate(schangeddate);
    String schangedtime= request.getParameter("changedtime");
//sql time conversion   
    Time changedtime = convertStringToTime(schangedtime);
	
	Sales sales = new Sales(product,salesdate,labour_shift,sales_priceunit,purchase_priceunit,purchasedate,subgroup_name, sales_qty, total_sales_price, uom, taxpercent_total ,taxamt_total, comments, changedby, changeddate, changedtime);
    salesDAO.updateSales(sales);
    
    String message = product+" :Sales record Updated Successfully";
  //AJAX    
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(message);    
    //response.sendRedirect("saleslist");
}

private void deleteSales(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {
	String product = request.getParameter("product");
    String ssalesdate = request.getParameter("salesdate");    
    Date salesdate = convertStringToDate(ssalesdate);
    String labour_shift = request.getParameter("labour_shift");
    Float sales_priceunit = Float.parseFloat(request.getParameter("sales_priceunit"));
    Float purchase_priceunit = Float.parseFloat(request.getParameter("purchase_priceunit"));
    Sales sales = new Sales(product, salesdate,labour_shift, sales_priceunit,purchase_priceunit);
    salesDAO.deleteSales(sales);
     
    String message = "Selected Sales Deleted Successfully";
    //AJAX    
    //response.setContentType("text/plain");
    //response.setCharacterEncoding("UTF-8");
    //response.getWriter().write(salesmessage);      
    request.setAttribute("message", message);
    response.sendRedirect("saleslist");

}


//Main Page Sales
	private void Sales(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {
	    RequestDispatcher dispatcher = request.getRequestDispatcher("Sales/Sales_initial.jsp");
	    dispatcher.include(request, response);
	}
}
