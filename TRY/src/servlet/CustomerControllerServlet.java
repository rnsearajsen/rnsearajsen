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
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.CustomerDAO;
import main.Customer;
import main.Sub_Group;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet(urlPatterns = {"/Customer","/customercreate", "/customerchange", "/customerinsert",
		                   "/customeredit", "/customerupdate", "/customerdelete",
		                   "/customerlist", "/customerlistajax", "/customerfilter"})
public class CustomerControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerDAO customerDAO; 
	private String constant_customer = "customer", constant_idtype = "idcardtype";

	public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        customerDAO = new CustomerDAO(jdbcURL, jdbcUsername, jdbcPassword);            
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
            case "/Customer":
                Customer(request,response);
                break;
            case "/customercreate":
                createCustomer(request,response);
                break;
            case "/customerinsert":     
                insertCustomer(request, response);
                break;
            case "/customerchange":
                changeCustomer(request, response);
                break;                
            case "/customeredit":
                showEditForm(request, response);
                break;
            case "/customerupdate":
                updateCustomer(request, response);
                break;
            case "/customerdelete":
                deleteCustomer(request, response);
                break;                
            case "/customerlist":
                listCustomer(request, response);
                break;
            case "/customerlistajax":
                listajaxCustomer(request, response);
                break;
            case "/customerfilter":
                filterCustomer(request, response);
                break;
            default:
                getCustomer(request, response);
                break;
            }
        } 
		catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }	
	
//Create Customer
	private void createCustomer(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {

		List<Sub_Group> listSub_Group = customerDAO.listCustomerSub_Groups(constant_customer);
		List<Sub_Group> listSub_Groupidtype = customerDAO.listCustomerSub_Groups(constant_idtype);
	    request.setAttribute("listSub_Group", listSub_Group);
	    request.setAttribute("listSub_Groupidtype", listSub_Groupidtype);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("Customer/Customer_Create.jsp");
	    dispatcher.include(request, response);
	}
	
//Filter Customer
	private void filterCustomer(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {

		int customerId = Integer.parseInt(request.getParameter("customer"));
   	    //request.setAttribute("selectedCustomerId", customerId);		
   	 Customer existingCustomer = customerDAO.getCustomer(customerId);
//Dropdown
	List<Sub_Group> listSub_Group = customerDAO.listCustomerSub_Groups(constant_customer);
	List<Sub_Group> listSub_Groupidtype = customerDAO.listCustomerSub_Groups(constant_idtype);
    request.setAttribute("listSub_Group", listSub_Group);
    request.setAttribute("listSub_Groupidtype", listSub_Groupidtype);
    
     RequestDispatcher dispatcher = request.getRequestDispatcher("Customer/Customer_Change.jsp");
     request.setAttribute("customer", existingCustomer);
     dispatcher.forward(request, response);

	}	
//List Customer
private void listCustomer(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    List<Customer> listCustomer = customerDAO.listAllCustomers();
    request.setAttribute("listCustomer", listCustomer);
    RequestDispatcher dispatcher = request.getRequestDispatcher("Customer/Customer_Display.jsp");
    dispatcher.forward(request, response);
}
//ListAJAX Customer - Not used
private void listajaxCustomer(HttpServletRequest request, HttpServletResponse response)
      throws SQLException, IOException, ServletException {
  List<Customer> listCustomer = customerDAO.listAllCustomers();
//AJAX
  String json = new Gson().toJson(listCustomer);
  
  response.setContentType("application/json");
  response.setCharacterEncoding("UTF-8");
  response.getWriter().write(json);
}
//Change Customer
private void changeCustomer(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    List<Customer> listCustomer = customerDAO.listAllCustomers();
    request.setAttribute("listCustomer", listCustomer);
    RequestDispatcher dispatcher = request.getRequestDispatcher("Customer/Customer_Change.jsp");
    dispatcher.forward(request, response);
}
//private void showNewForm(HttpServletRequest request, HttpServletResponse response)
//        throws ServletException, IOException {
//    RequestDispatcher dispatcher = request.getRequestDispatcher("Customer_Create.jsp");
//    dispatcher.forward(request, response);
//}

private void showEditForm(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {
    int customer_id = Integer.parseInt(request.getParameter("customer_id"));
    Customer existingCustomer = customerDAO.getCustomer(customer_id);
//Dropdown
	List<Sub_Group> listSub_Group = customerDAO.listCustomerSub_Groups(constant_customer);
	List<Sub_Group> listSub_Groupidtype = customerDAO.listCustomerSub_Groups(constant_idtype);
    request.setAttribute("listSub_Group", listSub_Group);
    request.setAttribute("listSub_Groupidtype", listSub_Groupidtype);
    
    RequestDispatcher dispatcher = request.getRequestDispatcher("Customer/Customer_Change.jsp");
    request.setAttribute("customer", existingCustomer);
    dispatcher.forward(request, response);
}

private void insertCustomer(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    String name = request.getParameter("name");
    String group_name = request.getParameter("group_name");
    String subgroup_name = request.getParameter("subgroup_name");
    String address = request.getParameter("address");
    String mail = request.getParameter("mail");
    String contact1 = request.getParameter("contact1");
    String contact2 = request.getParameter("contact2");
    String idtype1 = request.getParameter("idtype1");
    String idnum1 = request.getParameter("idnum1");
    String idtype2 = request.getParameter("idtype2");
    String idnum2 = request.getParameter("idnum2");
    String comments = request.getParameter("comments");
    String sjoindate= request.getParameter("joindate");
//sql date conversion    
    Date joindate = convertStringToDate(sjoindate);
    String slastdate= request.getParameter("lastdate");
    Date lastdate = convertStringToDate(slastdate);
    String changedby= request.getParameter("changedby");
    String schangeddate= request.getParameter("changeddate");
    Date changeddate = convertStringToDate(schangeddate);
    String schangedtime= request.getParameter("changedtime");
//sql time conversion   
    Time changedtime = convertStringToTime(schangedtime);
    String screateddate= request.getParameter("createddate");
    Date createddate = convertStringToDate(screateddate);
    String screatedtime= request.getParameter("createdtime");
    Time createdtime = convertStringToTime(screatedtime);
    
    Customer newCustomer = new Customer(name, group_name, subgroup_name, address, mail, contact1, contact2, idtype1,idnum1, idtype2,idnum2,
    		comments, joindate, lastdate, changedby, changeddate, changedtime, createddate, createdtime);
    customerDAO.insertCustomer(newCustomer);
    
    String message = name+" :Customer Created Successfully";    
//AJAX    
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(message);
   // response.getStatus();
// Call Customer Creation page
//    RequestDispatcher dispatcher = request.getRequestDispatcher("Customer/Customer_Create.jsp");
//    dispatcher.forward(request, response);  
}

private void updateCustomer(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    int customer_id = Integer.parseInt(request.getParameter("customer_id"));
    String name = request.getParameter("name");
    String group_name = request.getParameter("group_name");
    String subgroup_name = request.getParameter("subgroup_name");
    String address = request.getParameter("address");
    String mail = request.getParameter("mail");
    String contact1 = request.getParameter("contact1");
    String contact2 = request.getParameter("contact2");
    String idtype1 = request.getParameter("idtype1");
    String idnum1 = request.getParameter("idnum1");
    String idtype2 = request.getParameter("idtype2");
    String idnum2 = request.getParameter("idnum2");
    String comments = request.getParameter("comments");
    String sjoindate= request.getParameter("joindate");
//sql date conversion    
    Date joindate = convertStringToDate(sjoindate);
    String slastdate= request.getParameter("lastdate");
    Date lastdate = convertStringToDate(slastdate);
    String changedby= request.getParameter("changedby");
    String schangeddate= request.getParameter("changeddate");
    Date changeddate = convertStringToDate(schangeddate);
    String schangedtime= request.getParameter("changedtime");
//sql time conversion   
    Time changedtime = convertStringToTime(schangedtime);
    String screateddate= request.getParameter("createddate");
    Date createddate = convertStringToDate(screateddate);
    String screatedtime= request.getParameter("createdtime");
    Time createdtime = convertStringToTime(screatedtime);
    Customer customer = new Customer(customer_id, name, group_name, subgroup_name, address, mail, contact1, contact2, idtype1,idnum1, idtype2,idnum2,
    		comments, joindate, lastdate, changedby, changeddate, changedtime, createddate, createdtime);
    customerDAO.updateCustomer(customer);
    
    String message = name+" :Customer Updated Successfully";
  //AJAX    
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(message);    
    //response.sendRedirect("customerlist");
}

private void deleteCustomer(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException {
    int customer_id = Integer.parseInt(request.getParameter("customer_id"));

    Customer customer = new Customer(customer_id);
    customerDAO.deleteCustomer(customer);
     
    String message = "Selected Customer Deleted Successfully";
    //AJAX    
    //response.setContentType("text/plain");
    //response.setCharacterEncoding("UTF-8");
    //response.getWriter().write(customermessage);      
    request.setAttribute("message", message);
    response.sendRedirect("customerlist");

}

private void getCustomer(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {
   // int customer_id = Integer.parseInt(request.getParameter("customer_id"));
	HttpSession session=request.getSession();
	int customer_id = 1;
    Customer existingCustomerc = customerDAO.getCustomer(customer_id);
    session.setAttribute("customerc", existingCustomerc);
}

//Main Page Customer
	private void Customer(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {
	    RequestDispatcher dispatcher = request.getRequestDispatcher("Customer/Customer_initial.jsp");
	    dispatcher.include(request, response);
	}

}
