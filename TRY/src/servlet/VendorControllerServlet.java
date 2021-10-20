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

import dao.VendorDAO;
import main.Vendor;
import main.Sub_Group;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet(urlPatterns = {"/Vendor","/vendorcreate", "/vendorchange", "/vendorinsert",
		                   "/vendoredit", "/vendorupdate", "/vendordelete",
		                   "/vendorlist", "/vendorlistajax", "/vendorfilter"})
public class VendorControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VendorDAO vendorDAO;   
	private String constant_vendor = "vendor", constant_idtype = "idcardtype";
	public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        vendorDAO = new VendorDAO(jdbcURL, jdbcUsername, jdbcPassword);            
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
            case "/Vendor":
                Vendor(request,response);
                break;
            case "/vendorcreate":
                createVendor(request,response);
                break;
            case "/vendorinsert":     
                insertVendor(request, response);
                break;
            case "/vendorchange":
                changeVendor(request, response);
                break;                
            case "/vendoredit":
                showEditForm(request, response);
                break;
            case "/vendorupdate":
                updateVendor(request, response);
                break;
            case "/vendordelete":
                deleteVendor(request, response);
                break;                
            case "/vendorlist":
                listVendor(request, response);
                break;
            case "/vendorlistajax":
                listajaxVendor(request, response);
                break;
            case "/vendorfilter":
                filterVendor(request, response);
                break;
            default:
                getVendor(request, response);
                break;
            }
        } 
		catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }	
	
//Create Vendor
	private void createVendor(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {
		
		List<Sub_Group> listSub_Group = vendorDAO.listVendorSub_Groups(constant_vendor);
		List<Sub_Group> listSub_Groupidtype = vendorDAO.listVendorSub_Groups(constant_idtype);
	    request.setAttribute("listSub_Group", listSub_Group);
	    request.setAttribute("listSub_Groupidtype", listSub_Groupidtype);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("Vendor/Vendor_Create.jsp");
	    dispatcher.include(request, response);
	}
	
//Filter Vendor
	private void filterVendor(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {

		int vendorId = Integer.parseInt(request.getParameter("vendor"));
   	    //request.setAttribute("selectedVendorId", vendorId);		
   	 Vendor existingVendor = vendorDAO.getVendor(vendorId);
//Dropdown
	List<Sub_Group> listSub_Group = vendorDAO.listVendorSub_Groups(constant_vendor);
	List<Sub_Group> listSub_Groupidtype = vendorDAO.listVendorSub_Groups(constant_idtype);
    request.setAttribute("listSub_Group", listSub_Group);
    request.setAttribute("listSub_Groupidtype", listSub_Groupidtype);
    
     RequestDispatcher dispatcher = request.getRequestDispatcher("Vendor/Vendor_Change.jsp");
     request.setAttribute("vendor", existingVendor);
     dispatcher.forward(request, response);

	}	
//List Vendor
private void listVendor(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    List<Vendor> listVendor = vendorDAO.listAllVendors();
    request.setAttribute("listVendor", listVendor);
    RequestDispatcher dispatcher = request.getRequestDispatcher("Vendor/Vendor_Display.jsp");
    dispatcher.forward(request, response);
}
//ListAJAX Vendor - Not used
private void listajaxVendor(HttpServletRequest request, HttpServletResponse response)
      throws SQLException, IOException, ServletException {
  List<Vendor> listVendor = vendorDAO.listAllVendors();
//AJAX
  String json = new Gson().toJson(listVendor);
  
  response.setContentType("application/json");
  response.setCharacterEncoding("UTF-8");
  response.getWriter().write(json);
}
//Change Vendor
private void changeVendor(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    List<Vendor> listVendor = vendorDAO.listAllVendors();
    request.setAttribute("listVendor", listVendor);
    RequestDispatcher dispatcher = request.getRequestDispatcher("Vendor/Vendor_Change.jsp");
    dispatcher.forward(request, response);
}
//private void showNewForm(HttpServletRequest request, HttpServletResponse response)
//        throws ServletException, IOException {
//    RequestDispatcher dispatcher = request.getRequestDispatcher("Vendor_Create.jsp");
//    dispatcher.forward(request, response);
//}

private void showEditForm(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {
    int vendor_id = Integer.parseInt(request.getParameter("vendor_id"));
    Vendor existingVendor = vendorDAO.getVendor(vendor_id);
//Dropdown

	List<Sub_Group> listSub_Group = vendorDAO.listVendorSub_Groups(constant_vendor);
	List<Sub_Group> listSub_Groupidtype = vendorDAO.listVendorSub_Groups(constant_idtype);
    request.setAttribute("listSub_Group", listSub_Group);
    request.setAttribute("listSub_Groupidtype", listSub_Groupidtype);
    
    RequestDispatcher dispatcher = request.getRequestDispatcher("Vendor/Vendor_Change.jsp");
    request.setAttribute("vendor", existingVendor);
    dispatcher.forward(request, response);
}

private void insertVendor(HttpServletRequest request, HttpServletResponse response)
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
    
    Vendor newVendor = new Vendor(name, group_name, subgroup_name, address, mail, contact1, contact2, idtype1,idnum1, idtype2,idnum2,
    		comments, joindate, lastdate, changedby, changeddate, changedtime, createddate, createdtime);
    vendorDAO.insertVendor(newVendor);
    
    String message = name+" :Vendor Created Successfully";    
//AJAX    
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(message);
   // response.getStatus();
// Call Vendor Creation page
//    RequestDispatcher dispatcher = request.getRequestDispatcher("Vendor/Vendor_Create.jsp");
//    dispatcher.forward(request, response);  
}

private void updateVendor(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    int vendor_id = Integer.parseInt(request.getParameter("vendor_id"));
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
    Vendor vendor = new Vendor(vendor_id, name, group_name, subgroup_name, address, mail, contact1, contact2, idtype1,idnum1, idtype2,idnum2,
    		comments, joindate, lastdate, changedby, changeddate, changedtime, createddate, createdtime);
    vendorDAO.updateVendor(vendor);
    
    String message = name+" :Vendor Updated Successfully";
  //AJAX    
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(message);    
    //response.sendRedirect("vendorlist");
}

private void deleteVendor(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException {
    int vendor_id = Integer.parseInt(request.getParameter("vendor_id"));

    Vendor vendor = new Vendor(vendor_id);
    vendorDAO.deleteVendor(vendor);
     
    String message = "Selected Vendor Deleted Successfully";
    //AJAX    
    //response.setContentType("text/plain");
    //response.setCharacterEncoding("UTF-8");
    //response.getWriter().write(vendormessage);      
    request.setAttribute("message", message);
    response.sendRedirect("vendorlist");

}

private void getVendor(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {
   // int vendor_id = Integer.parseInt(request.getParameter("vendor_id"));
	HttpSession session=request.getSession();
	int vendor_id = 1;
    Vendor existingVendorc = vendorDAO.getVendor(vendor_id);
    session.setAttribute("vendorc", existingVendorc);
}

//Main Page Vendor
	private void Vendor(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {
	    RequestDispatcher dispatcher = request.getRequestDispatcher("Vendor/Vendor_initial.jsp");
	    dispatcher.include(request, response);
	}

}
