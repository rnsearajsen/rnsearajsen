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

import dao.BankacctDAO;
import main.Bankacct;
import main.Sub_Group;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet(urlPatterns = {"/Bankacct","/bankacctcreate", "/bankacctchange", "/bankacctinsert",
		                   "/bankacctedit", "/bankacctupdate", "/bankacctdelete",
		                   "/bankacctlist", "/bankacctlistajax", "/bankacctfilter", "/bankacct_dropdown"})
public class BankacctControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BankacctDAO bankacctDAO; 
	private String constant_bankacctype = "bank acct type";

	public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        bankacctDAO = new BankacctDAO(jdbcURL, jdbcUsername, jdbcPassword);            
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
            case "/Bankacct":
                Bankacct(request,response);
                break;
            case "/bankacctcreate":
                createBankacct(request,response);
                break;
            case "/bankacctinsert":     
                insertBankacct(request, response);
                break;
            case "/bankacctchange":
                changeBankacct(request, response);
                break;                
            case "/bankacctedit":
                showEditForm(request, response);
                break;
            case "/bankacctupdate":
                updateBankacct(request, response);
                break;
            case "/bankacctdelete":
                deleteBankacct(request, response);
                break;                
            case "/bankacctlist":
                listBankacct(request, response);
                break;
            case "/bankacctlistajax":
                listajaxBankacct(request, response);
                break;
            case "/bankacctfilter":
                filterBankacct(request, response);
                break;
            case "/bankacct_dropdown":
            	dropdownBankacct(request, response);
            	break;
            default:
                getBankacct(request, response);
                break;
            }
        } 
		catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }	
	
//Create Bankacct
	private void createBankacct(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {
//String fpgroup = request.getParameter("fgroup");
//String fpsubgroup = request.getParameter("fsub_group");
		//List<Sub_Group> listSub_Group = bankacctDAO.listBankacctSub_Groups(fpgroup);
		List<Sub_Group> listSub_Groupacctype = bankacctDAO.listBankacctSub_Groups(constant_bankacctype);
	   // request.setAttribute("listSub_Group", listSub_Group);
	   request.setAttribute("listSub_Groupacctype", listSub_Groupacctype);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("Bankacct/Bankacct_Create.jsp");
	    dispatcher.include(request, response);
	}
	
//Filter Bankacct
	private void filterBankacct(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {

		int bankacctId = Integer.parseInt(request.getParameter("bankacct"));
   	    //request.setAttribute("selectedBankacctId", bankacctId);		
   	 Bankacct existingBankacct = bankacctDAO.getBankacct(bankacctId);
//Dropdown
	List<Sub_Group> listSub_Group = bankacctDAO.listBankacctSub_Groups(existingBankacct.getGroup_name());
	List<Sub_Group> listSub_Groupacctype = bankacctDAO.listBankacctSub_Groups(constant_bankacctype);
    request.setAttribute("listSub_Group", listSub_Group);
    request.setAttribute("listSub_Groupacctype", listSub_Groupacctype);
    
     RequestDispatcher dispatcher = request.getRequestDispatcher("Bankacct/Bankacct_Change.jsp");
     request.setAttribute("bankacct", existingBankacct);
     dispatcher.forward(request, response);

	}	
//List Bankacct
private void listBankacct(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    List<Bankacct> listBankacct = bankacctDAO.listAllBankaccts();
    request.setAttribute("listBankacct", listBankacct);
    RequestDispatcher dispatcher = request.getRequestDispatcher("Bankacct/Bankacct_Display.jsp");
    dispatcher.forward(request, response);
}
//ListAJAX Bankacct - Not used
private void listajaxBankacct(HttpServletRequest request, HttpServletResponse response)
      throws SQLException, IOException, ServletException {
  List<Bankacct> listBankacct = bankacctDAO.listAllBankaccts();
//AJAX
  String json = new Gson().toJson(listBankacct);
  
  response.setContentType("application/json");
  response.setCharacterEncoding("UTF-8");
  response.getWriter().write(json);
}
//Change Bankacct
private void changeBankacct(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    List<Bankacct> listBankacct = bankacctDAO.listAllBankaccts();
    request.setAttribute("listBankacct", listBankacct);
    RequestDispatcher dispatcher = request.getRequestDispatcher("Bankacct/Bankacct_Change.jsp");
    dispatcher.forward(request, response);
}
//private void showNewForm(HttpServletRequest request, HttpServletResponse response)
//        throws ServletException, IOException {
//    RequestDispatcher dispatcher = request.getRequestDispatcher("Bankacct_Create.jsp");
//    dispatcher.forward(request, response);
//}

private void showEditForm(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {
    int bankacct_id = Integer.parseInt(request.getParameter("bankacct_id"));
    Bankacct existingBankacct = bankacctDAO.getBankacct(bankacct_id);
//Dropdown
	//List<Sub_Group> listSub_Group = bankacctDAO.listBankacctSub_Groups(constant_bankacct);
    List<Sub_Group> listSub_Groupacctype = bankacctDAO.listBankacctSub_Groups(constant_bankacctype);
	//List<Sub_Group> listSub_Groupbank = bankacctDAO.listBankacctSub_Groups(constant_bank);
    request.setAttribute("listSub_Groupacctype", listSub_Groupacctype);
    //request.setAttribute("listSub_Groupbank", listSub_Groupbank);
    
    RequestDispatcher dispatcher = request.getRequestDispatcher("Bankacct/Bankacct_Change.jsp");
    request.setAttribute("bankacct", existingBankacct);
    dispatcher.forward(request, response);
}

private void insertBankacct(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    String name = request.getParameter("name");
    String group_name = request.getParameter("group_name");
    String subgroup_name = request.getParameter("subgroup_name");
    String bank = request.getParameter("bank");
    String acctype = request.getParameter("acctype");
    String ifsc = request.getParameter("ifsc");
    String accountno = request.getParameter("accountno");
    String comments = request.getParameter("comments");        
    String changedby= request.getParameter("changedby");
//sql date conversion    
    String schangeddate= request.getParameter("changeddate");
    Date changeddate = convertStringToDate(schangeddate);
    String schangedtime= request.getParameter("changedtime");
//sql time conversion   
    Time changedtime = convertStringToTime(schangedtime);
    String screateddate= request.getParameter("createddate");
    Date createddate = convertStringToDate(screateddate);
    
    Bankacct newBankacct = new Bankacct(name, group_name, subgroup_name, bank, acctype, ifsc, accountno,
    		comments, changedby, changeddate, changedtime, createddate);
    bankacctDAO.insertBankacct(newBankacct);
    
    String message = name+" :Bankacct Created Successfully";    
//AJAX    
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(message);
   // response.getStatus();
// Call Bankacct Creation page
//    RequestDispatcher dispatcher = request.getRequestDispatcher("Bankacct/Bankacct_Create.jsp");
//    dispatcher.forward(request, response);  
}

private void updateBankacct(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    int bankacct_id = Integer.parseInt(request.getParameter("bankacct_id"));
    String name = request.getParameter("name");
    String group_name = request.getParameter("group_name");
    String subgroup_name = request.getParameter("subgroup_name");
    String bank = request.getParameter("bank");
    String acctype = request.getParameter("acctype");
    String ifsc = request.getParameter("ifsc");
    String accountno = request.getParameter("accountno");
    String comments = request.getParameter("comments");    
    String changedby= request.getParameter("changedby");
  //sql date conversion
    String schangeddate= request.getParameter("changeddate");
    Date changeddate = convertStringToDate(schangeddate);
    String schangedtime= request.getParameter("changedtime");
//sql time conversion   
    Time changedtime = convertStringToTime(schangedtime);
    String screateddate= request.getParameter("createddate");
    Date createddate = convertStringToDate(screateddate);
    Bankacct bankacct = new Bankacct(bankacct_id, name, group_name, subgroup_name, bank, acctype, ifsc, accountno,
    		comments, changedby, changeddate, changedtime, createddate);
    bankacctDAO.updateBankacct(bankacct);
    
    String message = name+" :Bankacct Updated Successfully";
  //AJAX    
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(message);    
    //response.sendRedirect("bankacctlist");
}

private void deleteBankacct(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException {
    int bankacct_id = Integer.parseInt(request.getParameter("bankacct_id"));

    Bankacct bankacct = new Bankacct(bankacct_id);
    bankacctDAO.deleteBankacct(bankacct);
     
    String message = "Selected Bankacct Deleted Successfully";
    //AJAX    
    //response.setContentType("text/plain");
    //response.setCharacterEncoding("UTF-8");
    //response.getWriter().write(bankacctmessage);      
    request.setAttribute("message", message);
    response.sendRedirect("bankacctlist");

}

private void getBankacct(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {
   // int bankacct_id = Integer.parseInt(request.getParameter("bankacct_id"));
	HttpSession session=request.getSession();
	int bankacct_id = 1;
    Bankacct existingBankacctc = bankacctDAO.getBankacct(bankacct_id);
    session.setAttribute("bankacctc", existingBankacctc);
}

//Main Page Bankacct
	private void Bankacct(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {
	    RequestDispatcher dispatcher = request.getRequestDispatcher("Bankacct/Bankacct_initial.jsp");
	    dispatcher.include(request, response);
	}
//DropDown list - From Sub-Group
	private void dropdownBankacct(HttpServletRequest request, HttpServletResponse response)
		      throws SQLException, IOException, ServletException {
		String group_name = request.getParameter("group_name");
		List<Bankacct> listBankacct = bankacctDAO.listBankacctDyn(group_name);
		//AJAX
		  String json = new Gson().toJson(listBankacct);
		  
		  response.setContentType("application/json");
		  response.setCharacterEncoding("UTF-8");
		  response.getWriter().write(json);
		}
}
