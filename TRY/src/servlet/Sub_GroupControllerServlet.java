package servlet;

import java.io.IOException;
//import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import dao.Sub_GroupDAO;
import main.Group;
import main.Sub_Group;

@WebServlet(urlPatterns = {"/SubGroup","/subgrpcreate", "/subgrpchange", "/subgrpinsert",
                           "/subgrpedit", "/subgrpupdate", "/subgrpdelete",
                           "/subgrplist", "/subgrpfilter"})
public class Sub_GroupControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Sub_GroupDAO sub_groupDAO;   
	
	public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        sub_groupDAO = new Sub_GroupDAO(jdbcURL, jdbcUsername, jdbcPassword);
    }
   // @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();		
		try {      
            switch (action) {
            case "/SubGroup":
                Sub_Group(request,response);
                break;
            case "/subgrpcreate":
                createSub_Group(request,response);
                break;
            case "/subgrpinsert":     
                insertSub_Group(request, response);
                break;
            case "/subgrpchange":
                changeSub_Group(request, response);
                break;                
            case "/subgrpedit":
                showEditForm(request, response);
                break;
            case "/subgrpupdate":
                updateSub_Group(request, response);
                break;
            case "/subgrpdelete":
                deleteSub_Group(request, response);
                break;                
            case "/subgrplist":
                listSub_Group(request, response);
                break;     
            case "/subgrpfilter":
                filterSub_Group(request, response);
                break;
            default:
                listSub_Group(request, response);            	
//                getSub_Group(request, response);
                break;
            }
        } catch (SQLException ex) {
        String message =	ex.getLocalizedMessage();
        request.setAttribute("message", message);
            throw new ServletException(ex);        
        }
    }	
//Create Sub-Group
		private void createSub_Group(HttpServletRequest request, HttpServletResponse response)
		        throws SQLException, IOException, ServletException {
		    List<Group> listGroup = sub_groupDAO.listAllGroups();
		    request.setAttribute("listGroup", listGroup);
		    RequestDispatcher dispatcher = request.getRequestDispatcher("Sub_Group/Sub_Group_Create.jsp");
		    dispatcher.forward(request, response);
		}	
	
//Filter Sub_Group
	private void filterSub_Group(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {

		int subgrpId = Integer.parseInt(request.getParameter("sub_group"));
   	 Sub_Group existingSub_Group = sub_groupDAO.getSub_Group(subgrpId);
   //List Dropdown 'Group'
     List<Group> listGroup = sub_groupDAO.listAllGroups();
     request.setAttribute("listGroup", listGroup);
     RequestDispatcher dispatcher = request.getRequestDispatcher("Sub_Group/Sub_Group_Change.jsp");
     request.setAttribute("sub_group", existingSub_Group);
     dispatcher.forward(request, response);

	}	
//List Sub_Group
private void listSub_Group(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    List<Sub_Group> listSub_Group = sub_groupDAO.listAllSub_Groups();
    request.setAttribute("listSub_Group", listSub_Group);
    RequestDispatcher dispatcher = request.getRequestDispatcher("Sub_Group/Sub_Group_Display.jsp");
    dispatcher.forward(request, response);
}

//Change Sub_Group
private void changeSub_Group(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
//Sub-Group List
    List<Sub_Group> listSub_Group = sub_groupDAO.listAllSub_Groups();
    request.setAttribute("listSub_Group", listSub_Group);

    RequestDispatcher dispatcher = request.getRequestDispatcher("Sub_Group/Sub_Group_Change.jsp");
    dispatcher.forward(request, response);
}
//private void showNewForm(HttpServletRequest request, HttpServletResponse response)
//        throws ServletException, IOException {
//    RequestDispatcher dispatcher = request.getRequestDispatcher("Sub_Group_Create.jsp");
//    dispatcher.forward(request, response);
//}

private void showEditForm(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {

    int subgrp_id = Integer.parseInt(request.getParameter("subgrp_id"));
    Sub_Group existingSub_Group = sub_groupDAO.getSub_Group(subgrp_id);    
//List Dropdown 'Group'
    List<Group> listGroup = sub_groupDAO.listAllGroups();
    request.setAttribute("listGroup", listGroup);
    RequestDispatcher dispatcher = request.getRequestDispatcher("Sub_Group/Sub_Group_Change.jsp");
    request.setAttribute("sub_group", existingSub_Group);
    dispatcher.forward(request, response);
}

private void insertSub_Group(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
	String fsub_group = request.getParameter("fsub_group");
    String fgroup = request.getParameter("group");
    String comments = request.getParameter("comments");
    String message  = null;
//Check whether already a "Sub-Group" exists
    Sub_Group existingSub_Group = sub_groupDAO.getSub_Groupname(fsub_group, fgroup);
    if (existingSub_Group == null) {
    Sub_Group newSub_Group = new Sub_Group(fsub_group, fgroup, comments);
    sub_groupDAO.insertSub_Group(newSub_Group);
  //Success Creation message;
    message = fsub_group+" :Sub-Group Created Successfully";    
    }else{
    	message = "Error: "+fsub_group+" :Duplicate Entry!!!";
    }
    //AJAX    
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(message);    
    
// Call Sub-Group Creation page
    //List<Group> listGroup = sub_groupDAO.listAllGroups();
    //request.setAttribute("listGroup", listGroup);
//    RequestDispatcher dispatcher = request.getRequestDispatcher("Sub_Group/Sub_Group_Create.jsp");
//    dispatcher.include(request, response);
}

private void updateSub_Group(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException {
	int subgrp_id = Integer.parseInt(request.getParameter("subgrp_id"));
    String fsub_group = request.getParameter("fsub_group");
    String fgroup = request.getParameter("group");
    String comments = request.getParameter("comments");
    String message  = null;
    Sub_Group sub_group = new Sub_Group(subgrp_id, fsub_group, fgroup, comments);
    sub_groupDAO.updateSub_Group(sub_group);
    message = fsub_group+" :Sub-Group Updated Successfully";
  //AJAX    
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(message);        
    //response.sendRedirect("subgrplist");
}

private void deleteSub_Group(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException {
	int subgrp_id = Integer.parseInt(request.getParameter("subgrp_id"));

    Sub_Group sub_group = new Sub_Group(subgrp_id);
    sub_groupDAO.deleteSub_Group(sub_group);
    String message = "Selected Sub-Group Deleted Successfully";
    request.setAttribute("message", message);
    response.sendRedirect("subgrplist");

}

//Main Page Sub_Group
	private void Sub_Group(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {
	    RequestDispatcher dispatcher = request.getRequestDispatcher("Sub_Group/Sub_Group_initial.jsp");
	    dispatcher.include(request, response);
	}

//private void getSub_Group(HttpServletRequest request, HttpServletResponse response)
//        throws SQLException, ServletException, IOException {
   // int group_id = Integer.parseInt(request.getParameter("group_id"));
//	HttpSession session=request.getSession();
//	int group_id = 1;
//    Sub_Group existingSub_Groupc = groupDAO.getSub_Group(group_id);
//    session.setAttribute("groupc", existingSub_Groupc);
//    dispatcher.forward(request, response);
//}

}
