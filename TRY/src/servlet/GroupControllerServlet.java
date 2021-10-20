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

import dao.GroupDAO;
import main.Group;

@WebServlet(urlPatterns = {"/Group","/grpcreate", "/grpchange", "/grpinsert",
                           "/grpedit", "/grpupdate", "/grpdelete",
                           "/grplist", "/grpfilter"})
public class GroupControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GroupDAO groupDAO;   
	
	public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        groupDAO = new GroupDAO(jdbcURL, jdbcUsername, jdbcPassword);
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
            case "/Group":
                Group(request,response);
                break;
            case "/grpcreate":
                createGroup(request,response);
                break;
            case "/grpinsert":     
                insertGroup(request, response);
                break;
            case "/grpchange":
                changeGroup(request, response);
                break;                
            case "/grpedit":
                showEditForm(request, response);
                break;
            case "/grpupdate":
                updateGroup(request, response);
                break;
            case "/grpdelete":
                deleteGroup(request, response);
                break;                
            case "/grplist":
                listGroup(request, response);
                break;     
            case "/grpfilter":
                filterGroup(request, response);
                break;
            default:
                listGroup(request, response);            	
//                getGroup(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }	
//Create Company
		private void createGroup(HttpServletRequest request, HttpServletResponse response)
		        throws SQLException, IOException, ServletException {
		    RequestDispatcher dispatcher = request.getRequestDispatcher("Group/Group_Create.jsp");
		    dispatcher.forward(request, response);
		}	
	
//Filter Group
	private void filterGroup(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {

		int groupId = Integer.parseInt(request.getParameter("group"));
   	    request.setAttribute("selectedGrpId", groupId);		
   	 Group existingGroup = groupDAO.getGroup(groupId);
     RequestDispatcher dispatcher = request.getRequestDispatcher("Group/Group_Change.jsp");
     request.setAttribute("group", existingGroup);
     dispatcher.forward(request, response);

	}	
//List Group
private void listGroup(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    List<Group> listGroup = groupDAO.listAllGroups();
    request.setAttribute("listGroup", listGroup);
    RequestDispatcher dispatcher = request.getRequestDispatcher("Group/Group_Display.jsp");
    dispatcher.forward(request, response);
}

//Change Group
private void changeGroup(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    List<Group> listGroup = groupDAO.listAllGroups();
    request.setAttribute("listGroup", listGroup);
    RequestDispatcher dispatcher = request.getRequestDispatcher("Group/Group_Change.jsp");
    dispatcher.forward(request, response);
}
//private void showNewForm(HttpServletRequest request, HttpServletResponse response)
//        throws ServletException, IOException {
//    RequestDispatcher dispatcher = request.getRequestDispatcher("Group_Create.jsp");
//    dispatcher.forward(request, response);
//}

private void showEditForm(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {
    int group_id = Integer.parseInt(request.getParameter("group_id"));
    Group existingGroup = groupDAO.getGroup(group_id);
    RequestDispatcher dispatcher = request.getRequestDispatcher("Group/Group_Change.jsp");
    request.setAttribute("group", existingGroup);
    dispatcher.forward(request, response);
}

private void insertGroup(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    String name = request.getParameter("name");
    String comments = request.getParameter("comments");
    String message  = null;
  //Check whether already a "Group" exists
   // Group existingGroup = groupDAO.getGroupname(name);
   //   if (existingGroup == null) {
    Group newGroup = new Group(name, comments);
    groupDAO.insertGroup(newGroup);
//Success Creation message;
    message = name+" :Group Created Successfully";  
    //  }else{
    //  	message = "Error: "+name+" :Duplicate Entry!!!";
    //  }
  //request.setAttribute("message", name+message);
  //AJAX    
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(message);    
// Call Group Creation page
// RequestDispatcher dispatcher = request.getRequestDispatcher("Group/Group_Create.jsp");
//    dispatcher.forward(request, response);  
}

private void updateGroup(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException {
    int group_id = Integer.parseInt(request.getParameter("group_id"));
    String name = request.getParameter("name");
    String comments = request.getParameter("comments");
    String message  = null;
    Group group = new Group(group_id, name, comments);
    groupDAO.updateGroup(group);
    message = name+" :Group Updated Successfully";
  //AJAX    
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(message);    
    //response.sendRedirect("grplist");
}

private void deleteGroup(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException {
    int group_id = Integer.parseInt(request.getParameter("group_id"));

    Group group = new Group(group_id);
    groupDAO.deleteGroup(group);
    String message = "Selected Group Deleted Successfully";
    request.setAttribute("message", message);
    response.sendRedirect("grplist");

}
//Main Page Group
	private void Group(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {
	    RequestDispatcher dispatcher = request.getRequestDispatcher("Group/Group_initial.jsp");
	    dispatcher.include(request, response);
	}
//private void getGroup(HttpServletRequest request, HttpServletResponse response)
//        throws SQLException, ServletException, IOException {
   // int group_id = Integer.parseInt(request.getParameter("group_id"));
//	HttpSession session=request.getSession();
//	int group_id = 1;
//    Group existingGroupc = groupDAO.getGroup(group_id);
//    session.setAttribute("groupc", existingGroupc);
//    dispatcher.forward(request, response);
//}

}
