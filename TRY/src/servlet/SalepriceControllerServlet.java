package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

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

import dao.SalepriceDAO;
import main.Saleprice;
import main.Sub_Group;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet(urlPatterns = {"/Saleprice","/salepricecreate", "/salepricechange", "/salepriceinsert",
		                   "/salepriceedit", "/salepriceupdate", "/salepricedelete", "/salesprice_fuel",
		                   "/salepricelist", "/salepricelistajax","/salepricedeleteajax", "/salepricefilter"})
public class SalepriceControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SalepriceDAO salepriceDAO; 
	private String constant_product = "product", constant_uom = "uom";

	public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        salepriceDAO = new SalepriceDAO(jdbcURL, jdbcUsername, jdbcPassword);            
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
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();		
		try {      
            switch (action) {
            case "/Saleprice":
                Saleprice(request,response);
                break;
            case "/salepricecreate":
                createSaleprice(request,response);
                break;
            case "/salepriceinsert":     
                insertSaleprice(request, response);
                break;
            case "/salepricechange":
                changeSaleprice(request, response);
                break;                
            case "/salepriceedit":
                showEditForm(request, response);
                break;
            case "/salepriceupdate":
                updateSaleprice(request, response);
                break;
            case "/salepricedelete":
                deleteSaleprice(request, response);
                break;                
            case "/salepricelist":
                listSaleprice(request, response);
                break;
            case "/salepricelistajax":
                listajaxSaleprice(request, response);
                break;
            case "/salepricedeleteajax":
            	deleteajaxSaleprice(request, response);
                break;
            case "/salepricefilter":
                filterSaleprice(request, response);
                break;
            case "/salesprice_fuel":
                fuelajaxSaleprice(request, response);
                break;
            default:
               // getSaleprice(request, response);
                break;
            }
        } 
		catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }	
	
//Create Saleprice
	private void createSaleprice(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {

		List<Sub_Group> listSub_Group = salepriceDAO.listSalepriceSub_Groups(constant_product);
		//List<Sub_Group> listSub_Groupuom = salepriceDAO.listSalepriceSub_Groups(constant_uom);
	    request.setAttribute("listSub_Group", listSub_Group);
	    //request.setAttribute("listSub_Groupuom", listSub_Groupuom);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("Saleprice/Saleprice_Create.jsp");
	    dispatcher.include(request, response);
	}
	
//Filter Saleprice
	private void filterSaleprice(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {

		String product = request.getParameter("product");
	    String sdate = request.getParameter("date");    
	    Date date = convertStringToDate(sdate);
   	    //request.setAttribute("selectedSalepriceId", salepriceId);		
   	 Saleprice existingSaleprice = salepriceDAO.getSaleprice(date, product);
//Dropdown
	List<Sub_Group> listSub_Group = salepriceDAO.listSalepriceSub_Groups(constant_product);
	List<Sub_Group> listSub_Groupuom = salepriceDAO.listSalepriceSub_Groups(constant_uom);
    request.setAttribute("listSub_Group", listSub_Group);
    request.setAttribute("listSub_Groupuom", listSub_Groupuom);
    
     RequestDispatcher dispatcher = request.getRequestDispatcher("Saleprice/Saleprice_Change.jsp");
     request.setAttribute("saleprice", existingSaleprice);
     dispatcher.forward(request, response);

	}	
//List Saleprice - Not used
private void listSaleprice(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
//Product Sub-Group
	List<Sub_Group> listSub_Group = salepriceDAO.listSalepriceSub_Groups(constant_product);
    request.setAttribute("listSub_Group", listSub_Group);
    
   // List<Saleprice> listSaleprice = salepriceDAO.listAllSaleprices();
   // request.setAttribute("listSaleprice", listSaleprice);
    RequestDispatcher dispatcher = request.getRequestDispatcher("Saleprice/Saleprice_Display.jsp");
    dispatcher.forward(request, response);
}
//ListAJAX Saleprice 
private void listajaxSaleprice(HttpServletRequest request, HttpServletResponse response)
      throws SQLException, IOException, ServletException {
//Get Parameters
	String subgroup_name = request.getParameter("subgroup_name");
    String sdate_from = request.getParameter("date_from");    
    Date date_from = convertStringToDate(sdate_from);
    String sdate_to = request.getParameter("date_to");    
    Date date_to = convertStringToDate(sdate_to);
    
  List<Saleprice> listSaleprice = salepriceDAO.listRangeSaleprices(subgroup_name, date_from, date_to);
 
//AJAX    
  String json = new Gson().toJson(listSaleprice);
  
  response.setContentType("application/json");
  response.setCharacterEncoding("UTF-8");
  response.getWriter().write(json);
}
// Delete entry using AJAX
private void deleteajaxSaleprice(HttpServletRequest request, HttpServletResponse response)
	      throws SQLException, IOException, ServletException {
// Delete entry
	String product = request.getParameter("product");
    String sdate = request.getParameter("date");    
    Date date = convertStringToDate(sdate);
    Saleprice saleprice = new Saleprice(date, product);
    salepriceDAO.deleteSaleprice(saleprice);
    String message = sdate+":"+product+" :Saleprice record Deleted Successfully";
  //AJAX    
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(message);  
	}

//Change Saleprice
private void changeSaleprice(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    List<Saleprice> listSaleprice = salepriceDAO.listAllSaleprices();
    request.setAttribute("listSaleprice", listSaleprice);
    RequestDispatcher dispatcher = request.getRequestDispatcher("Saleprice/Saleprice_Change.jsp");
    dispatcher.forward(request, response);
}
//private void showNewForm(HttpServletRequest request, HttpServletResponse response)
//        throws ServletException, IOException {
//    RequestDispatcher dispatcher = request.getRequestDispatcher("Saleprice_Create.jsp");
//    dispatcher.forward(request, response);
//}

private void showEditForm(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {
	String product = request.getParameter("product");
    String sdate = request.getParameter("date");    
    Date date = convertStringToDate(sdate);
    Saleprice existingSaleprice = salepriceDAO.getSaleprice(date, product);
//Dropdown
	List<Sub_Group> listSub_Group = salepriceDAO.listSalepriceSub_Groups(constant_product);
	List<Sub_Group> listSub_Groupuom = salepriceDAO.listSalepriceSub_Groups(constant_uom);
    request.setAttribute("listSub_Group", listSub_Group);
    request.setAttribute("listSub_Groupuom", listSub_Groupuom);
    
    RequestDispatcher dispatcher = request.getRequestDispatcher("Saleprice/Saleprice_Change.jsp");
    request.setAttribute("saleprice", existingSaleprice);
    dispatcher.forward(request, response);
}

private void insertSaleprice(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    String product = request.getParameter("product");
    String sdate = request.getParameter("date");    
    Date date = convertStringToDate(sdate);
    String subgroup_name = request.getParameter("subgroup_name");
    Float sell_price = Float.parseFloat(request.getParameter("sell_price"));    
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
    
    Saleprice newSaleprice = new Saleprice(date, product,subgroup_name,sell_price,uom, taxpercent_total,taxamt_total, comments, changedby, changeddate, changedtime);
    salepriceDAO.insertSaleprice(newSaleprice);
    
    String message = product+" :Saleprice record Created Successfully";    
//AJAX    
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(message);
   // response.getStatus();
// Call Saleprice Creation page
//    RequestDispatcher dispatcher = request.getRequestDispatcher("Saleprice/Saleprice_Create.jsp");
//    dispatcher.forward(request, response);  
}

private void updateSaleprice(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    String product = request.getParameter("product");
    String sdate = request.getParameter("date");
    Date date = convertStringToDate(sdate);
    String subgroup_name = request.getParameter("subgroup_name");
    Float sell_price = Float.parseFloat(request.getParameter("sell_price"));    
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
    Saleprice saleprice = new Saleprice( date, product, subgroup_name, sell_price, uom, taxpercent_total,taxamt_total, comments, changedby, changeddate, changedtime);
    salepriceDAO.updateSaleprice(saleprice);
    
    String message = product+" :Saleprice record Updated Successfully";
  //AJAX    
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(message);    
    //response.sendRedirect("salepricelist");
}

private void deleteSaleprice(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {
	String product = request.getParameter("product");
    String sdate = request.getParameter("date");    
    Date date = convertStringToDate(sdate);
    Saleprice saleprice = new Saleprice(date, product);
    salepriceDAO.deleteSaleprice(saleprice);
     
    String message = "Selected Saleprice Deleted Successfully";
    //AJAX    
    //response.setContentType("text/plain");
    //response.setCharacterEncoding("UTF-8");
    //response.getWriter().write(salepricemessage);      
    request.setAttribute("message", message);
    response.sendRedirect("salepricelist");

}
//Get Sales Price for given date
//ListAJAX Saleprice 
private void fuelajaxSaleprice(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
//Get Parameters
  String product = request.getParameter("product");
  String sdate = request.getParameter("date");    
  Date date = convertStringToDate(sdate);
  sdate = request.getParameter("nextdate");    
  Date nextdate = convertStringToDate(sdate);
  
List<Saleprice> listSaleprice = salepriceDAO.listfuelSaleprices(date,nextdate, product);

//AJAX    
String json = new Gson().toJson(listSaleprice);

response.setContentType("application/json");
response.setCharacterEncoding("UTF-8");
response.getWriter().write(json);
}

//Main Page Saleprice
	private void Saleprice(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {
	    RequestDispatcher dispatcher = request.getRequestDispatcher("Saleprice/Saleprice_initial.jsp");
	    dispatcher.include(request, response);
	}
}
