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

import dao.PurchaseDAO;
import main.Purchase;
import main.Sub_Group;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet(urlPatterns = {"/Purchase","/purchasecreate", "/purchasechange", "/purchaseinsert",
		                   "/purchaseedit", "/purchaseupdate", "/purchasedelete",
		                   "/purchaselist", "/purchaselistajax","/purchasedeleteajax", "/purchasefilter"})
public class PurchaseControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PurchaseDAO purchaseDAO; 
	private String constant_product = "product", constant_uom = "uom";

	public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        purchaseDAO = new PurchaseDAO(jdbcURL, jdbcUsername, jdbcPassword);            
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
            case "/Purchase":
                Purchase(request,response);
                break;
            case "/purchasecreate":
                createPurchase(request,response);
                break;
            case "/purchaseinsert":     
                insertPurchase(request, response);
                break;
            case "/purchasechange":
                changePurchase(request, response);
                break;                
            case "/purchaseedit":
                showEditForm(request, response);
                break;
            case "/purchaseupdate":
                updatePurchase(request, response);
                break;
            case "/purchasedelete":
                deletePurchase(request, response);
                break;                
            case "/purchaselist":
                listPurchase(request, response);
                break;
            case "/purchaselistajax":
                listajaxPurchase(request, response);
                break;
            case "/purchasedeleteajax":
            	deleteajaxPurchase(request, response);
                break;
            case "/purchasefilter":
                filterPurchase(request, response);
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
	
//Create Purchase
	private void createPurchase(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {

		List<Sub_Group> listSub_Group = purchaseDAO.listPurchaseSub_Groups(constant_product);
		//List<Sub_Group> listSub_Groupuom = purchaseDAO.listPurchaseSub_Groups(constant_uom);
	    request.setAttribute("listSub_Group", listSub_Group);
	    //request.setAttribute("listSub_Groupuom", listSub_Groupuom);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("Purchase/Purchase_Create.jsp");
	    dispatcher.include(request, response);
	}
	
//Filter Purchase
	private void filterPurchase(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {

		String product = request.getParameter("product");
	    String spurchasedate = request.getParameter("purchasedate");    
	    Date purchasedate = convertStringToDate(spurchasedate);
   	    //request.setAttribute("selectedPurchaseId", purchaseId);		
   	 Purchase existingPurchase = purchaseDAO.getPurchase(product, purchasedate);
//Dropdown
	List<Sub_Group> listSub_Group = purchaseDAO.listPurchaseSub_Groups(constant_product);
	List<Sub_Group> listSub_Groupuom = purchaseDAO.listPurchaseSub_Groups(constant_uom);
    request.setAttribute("listSub_Group", listSub_Group);
    request.setAttribute("listSub_Groupuom", listSub_Groupuom);
    
     RequestDispatcher dispatcher = request.getRequestDispatcher("Purchase/Purchase_Change.jsp");
     request.setAttribute("purchase", existingPurchase);
     dispatcher.forward(request, response);

	}	
//List Purchase - Not used
private void listPurchase(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
//Product Sub-Group
	List<Sub_Group> listSub_Group = purchaseDAO.listPurchaseSub_Groups(constant_product);
    request.setAttribute("listSub_Group", listSub_Group);
    
   // List<Purchase> listPurchase = purchaseDAO.listAllPurchases();
   // request.setAttribute("listPurchase", listPurchase);
    RequestDispatcher dispatcher = request.getRequestDispatcher("Purchase/Purchase_Display.jsp");
    dispatcher.forward(request, response);
}
//ListAJAX Purchase 
private void listajaxPurchase(HttpServletRequest request, HttpServletResponse response)
      throws SQLException, IOException, ServletException {
//Get Parameters
	String subgroup_name = request.getParameter("subgroup_name");
    String spurchasedate_from = request.getParameter("purchasedate_from");    
    Date purchasedate_from = convertStringToDate(spurchasedate_from);
    String spurchasedate_to = request.getParameter("purchasedate_to");    
    Date purchasedate_to = convertStringToDate(spurchasedate_to);
    
  List<Purchase> listPurchase = purchaseDAO.listRangePurchases(subgroup_name, purchasedate_from, purchasedate_to);
 
//AJAX    
  String json = new Gson().toJson(listPurchase);
  
  response.setContentType("application/json");
  response.setCharacterEncoding("UTF-8");
  response.getWriter().write(json);
}
// Delete entry using AJAX
private void deleteajaxPurchase(HttpServletRequest request, HttpServletResponse response)
	      throws SQLException, IOException, ServletException {
// Delete entry
	String product = request.getParameter("product");
    String spurchasedate = request.getParameter("purchasedate");    
    Date purchasedate = convertStringToDate(spurchasedate);
    Purchase purchase = new Purchase(product, purchasedate);
    purchaseDAO.deletePurchase(purchase);
    String message = spurchasedate+":"+product+" :Purchase record Deleted Successfully";
  //AJAX    
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(message);  
	}

//Change Purchase
private void changePurchase(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    List<Purchase> listPurchase = purchaseDAO.listAllPurchases();
    request.setAttribute("listPurchase", listPurchase);
    RequestDispatcher dispatcher = request.getRequestDispatcher("Purchase/Purchase_Change.jsp");
    dispatcher.forward(request, response);
}
//private void showNewForm(HttpServletRequest request, HttpServletResponse response)
//        throws ServletException, IOException {
//    RequestDispatcher dispatcher = request.getRequestDispatcher("Purchase_Create.jsp");
//    dispatcher.forward(request, response);
//}

private void showEditForm(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {
	String product = request.getParameter("product");
    String spurchasedate = request.getParameter("purchasedate");    
    Date purchasedate = convertStringToDate(spurchasedate);
    Purchase existingPurchase = purchaseDAO.getPurchase(product, purchasedate);
//Dropdown
	List<Sub_Group> listSub_Group = purchaseDAO.listPurchaseSub_Groups(constant_product);
	List<Sub_Group> listSub_Groupuom = purchaseDAO.listPurchaseSub_Groups(constant_uom);
    request.setAttribute("listSub_Group", listSub_Group);
    request.setAttribute("listSub_Groupuom", listSub_Groupuom);
    
    RequestDispatcher dispatcher = request.getRequestDispatcher("Purchase/Purchase_Change.jsp");
    request.setAttribute("purchase", existingPurchase);
    dispatcher.forward(request, response);
}

private void insertPurchase(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    String product = request.getParameter("product");
    String spurchasedate = request.getParameter("purchasedate");    
    Date purchasedate = convertStringToDate(spurchasedate);
    String subgroup_name = request.getParameter("subgroup_name");
    Float purchase_qty = Float.parseFloat(request.getParameter("purchase_qty"));
    Float stock_qty = Float.parseFloat(request.getParameter("stock_qty"));    
    String uom = request.getParameter("uom");
    Float price_unit = Float.parseFloat(request.getParameter("price_unit"));
    Float price_total = Float.parseFloat(request.getParameter("price_total"));
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
    
    Purchase newPurchase = new Purchase(product, purchasedate,subgroup_name,purchase_qty,stock_qty, uom,price_unit,price_total, taxpercent_total,taxamt_total, comments, changedby, changeddate, changedtime);
    purchaseDAO.insertPurchase(newPurchase);
    
    String message = product+" :Purchase record Created Successfully";    
//AJAX    
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(message);
   // response.getStatus();
// Call Purchase Creation page
//    RequestDispatcher dispatcher = request.getRequestDispatcher("Purchase/Purchase_Create.jsp");
//    dispatcher.forward(request, response);  
}

private void updatePurchase(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    String product = request.getParameter("product");
    String spurchasedate = request.getParameter("purchasedate");
    Date purchasedate = convertStringToDate(spurchasedate);
    String subgroup_name = request.getParameter("subgroup_name");
    Float purchase_qty = Float.parseFloat(request.getParameter("purchase_qty"));
    Float stock_qty = Float.parseFloat(request.getParameter("stock_qty"));    
    String uom = request.getParameter("uom");
    Float price_unit = Float.parseFloat(request.getParameter("price_unit"));
    Float price_total = Float.parseFloat(request.getParameter("price_total"));
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
    Purchase purchase = new Purchase( product, purchasedate, subgroup_name, purchase_qty,stock_qty, uom,price_unit,price_total, taxpercent_total,taxamt_total, comments, changedby, changeddate, changedtime);
    purchaseDAO.updatePurchase(purchase);
    
    String message = product+" :Purchase record Updated Successfully";
  //AJAX    
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(message);    
    //response.sendRedirect("purchaselist");
}

private void deletePurchase(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {
	String product = request.getParameter("product");
    String spurchasedate = request.getParameter("purchasedate");    
    Date purchasedate = convertStringToDate(spurchasedate);
    Purchase purchase = new Purchase(product, purchasedate);
    purchaseDAO.deletePurchase(purchase);
     
    String message = "Selected Purchase Deleted Successfully";
    //AJAX    
    //response.setContentType("text/plain");
    //response.setCharacterEncoding("UTF-8");
    //response.getWriter().write(purchasemessage);      
    request.setAttribute("message", message);
    response.sendRedirect("purchaselist");

}


//Main Page Purchase
	private void Purchase(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {
	    RequestDispatcher dispatcher = request.getRequestDispatcher("Purchase/Purchase_initial.jsp");
	    dispatcher.include(request, response);
	}
}
