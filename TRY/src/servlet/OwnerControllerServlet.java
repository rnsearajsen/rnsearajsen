package servlet;

import java.io.IOException;
//import java.io.PrintWriter;
import java.sql.SQLException;
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

import dao.OwnerDAO;
import main.Owner;
import main.Sub_Group;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet(urlPatterns = {"/Owner","/ownercreate", "/ownerchange", "/ownerinsert",
		                   "/owneredit", "/ownerupdate", "/ownerdelete",
		                   "/ownerlist", "/ownerlistajax", "/ownerfilter"})
public class OwnerControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OwnerDAO ownerDAO;   
	private String constant_owner = "owner", constant_idtype = "idcardtype";
	public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        ownerDAO = new OwnerDAO(jdbcURL, jdbcUsername, jdbcPassword);            
	}
   //@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();		
		try {      
            switch (action) {
            case "/Owner":
                Owner(request,response);
                break;
            case "/ownercreate":
                createOwner(request,response);
                break;
            case "/ownerinsert":     
                insertOwner(request, response);
                break;
            case "/ownerchange":
                changeOwner(request, response);
                break;                
            case "/owneredit":
                showEditForm(request, response);
                break;
            case "/ownerupdate":
                updateOwner(request, response);
                break;
            case "/ownerdelete":
                deleteOwner(request, response);
                break;                
            case "/ownerlist":
                listOwner(request, response);
                break;
            case "/ownerlistajax":
                listajaxOwner(request, response);
                break;
            case "/ownerfilter":
                filterOwner(request, response);
                break;
            default:
                getOwner(request, response);
                break;
            }
        } 
		catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }	
	
//Create Owner
	private void createOwner(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {
		
		List<Sub_Group> listSub_Group = ownerDAO.listOwnerSub_Groups(constant_owner);
		List<Sub_Group> listSub_Groupidtype = ownerDAO.listOwnerSub_Groups(constant_idtype);
	    request.setAttribute("listSub_Group", listSub_Group);
	    request.setAttribute("listSub_Groupidtype", listSub_Groupidtype);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("Owner/Owner_Create.jsp");
	    dispatcher.include(request, response);
	}
	
//Filter Owner
	private void filterOwner(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {

		int ownerId = Integer.parseInt(request.getParameter("owner"));
   	    //request.setAttribute("selectedOwnerId", ownerId);		
   	 Owner existingOwner = ownerDAO.getOwner(ownerId);
//Dropdown

	List<Sub_Group> listSub_Group = ownerDAO.listOwnerSub_Groups(constant_owner);
	List<Sub_Group> listSub_Groupidtype = ownerDAO.listOwnerSub_Groups(constant_idtype);
    request.setAttribute("listSub_Group", listSub_Group);
    request.setAttribute("listSub_Groupidtype", listSub_Groupidtype);
    
     RequestDispatcher dispatcher = request.getRequestDispatcher("Owner/Owner_Change.jsp");
     request.setAttribute("owner", existingOwner);
     dispatcher.forward(request, response);

	}	
//List Owner
private void listOwner(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    List<Owner> listOwner = ownerDAO.listAllOwners();
    request.setAttribute("listOwner", listOwner);
    RequestDispatcher dispatcher = request.getRequestDispatcher("Owner/Owner_Display.jsp");
    dispatcher.forward(request, response);
}
//ListAJAX Owner - Not used
private void listajaxOwner(HttpServletRequest request, HttpServletResponse response)
      throws SQLException, IOException, ServletException {
  List<Owner> listOwner = ownerDAO.listAllOwners();
//AJAX
  String json = new Gson().toJson(listOwner);
  
  response.setContentType("application/json");
  response.setCharacterEncoding("UTF-8");
  response.getWriter().write(json);
}
//Change Owner
private void changeOwner(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    List<Owner> listOwner = ownerDAO.listAllOwners();
    request.setAttribute("listOwner", listOwner);
    RequestDispatcher dispatcher = request.getRequestDispatcher("Owner/Owner_Change.jsp");
    dispatcher.forward(request, response);
}
//private void showNewForm(HttpServletRequest request, HttpServletResponse response)
//        throws ServletException, IOException {
//    RequestDispatcher dispatcher = request.getRequestDispatcher("Owner_Create.jsp");
//    dispatcher.forward(request, response);
//}

private void showEditForm(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {
    int owner_id = Integer.parseInt(request.getParameter("owner_id"));
    Owner existingOwner = ownerDAO.getOwner(owner_id);
//Dropdown

	List<Sub_Group> listSub_Group = ownerDAO.listOwnerSub_Groups(constant_owner);
	List<Sub_Group> listSub_Groupidtype = ownerDAO.listOwnerSub_Groups(constant_idtype);
    request.setAttribute("listSub_Group", listSub_Group);
    request.setAttribute("listSub_Groupidtype", listSub_Groupidtype);
    
    RequestDispatcher dispatcher = request.getRequestDispatcher("Owner/Owner_Change.jsp");
    request.setAttribute("owner", existingOwner);
    dispatcher.forward(request, response);
}

private void insertOwner(HttpServletRequest request, HttpServletResponse response)
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
    
    Owner newOwner = new Owner(name, group_name, subgroup_name, address, mail, contact1, contact2, idtype1,idnum1, idtype2,idnum2);
    ownerDAO.insertOwner(newOwner);
    
    String message = name+" :Owner Created Successfully";    
//AJAX    
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(message);
   // response.getStatus();
// Call Owner Creation page
//    RequestDispatcher dispatcher = request.getRequestDispatcher("Owner/Owner_Create.jsp");
//    dispatcher.forward(request, response);  
}

private void updateOwner(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException {
    int owner_id = Integer.parseInt(request.getParameter("owner_id"));
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

    Owner owner = new Owner(owner_id, name, group_name, subgroup_name, address, mail, contact1, contact2, idtype1,idnum1, idtype2,idnum2);
    ownerDAO.updateOwner(owner);
    
    String message = name+" :Owner Updated Successfully";
  //AJAX    
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(message);    
    //response.sendRedirect("ownerlist");
}

private void deleteOwner(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException {
    int owner_id = Integer.parseInt(request.getParameter("owner_id"));

    Owner owner = new Owner(owner_id);
    ownerDAO.deleteOwner(owner);
     
    String message = "Selected Owner Deleted Successfully";
    //AJAX    
    //response.setContentType("text/plain");
    //response.setCharacterEncoding("UTF-8");
    //response.getWriter().write(ownermessage);      
    request.setAttribute("message", message);
    response.sendRedirect("ownerlist");

}

private void getOwner(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {
   // int owner_id = Integer.parseInt(request.getParameter("owner_id"));
	HttpSession session=request.getSession();
	int owner_id = 1;
    Owner existingOwnerc = ownerDAO.getOwner(owner_id);
    session.setAttribute("ownerc", existingOwnerc);
}

//Main Page Owner
	private void Owner(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {
	    RequestDispatcher dispatcher = request.getRequestDispatcher("Owner/Owner_initial.jsp");
	    dispatcher.include(request, response);
	}

}
