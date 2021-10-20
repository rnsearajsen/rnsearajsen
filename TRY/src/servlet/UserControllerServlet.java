package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import main.User;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet(urlPatterns = {"/usercreate", "/userchange", "/userinsert",
		                   "/useredit", "/userupdate", "/userdelete",
		                   "/userlist", "/userfilter", "/userlogin", "/userlogout"})
public class UserControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;   

	public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        userDAO = new UserDAO(jdbcURL, jdbcUsername, jdbcPassword);            
	}
//Get User Details	
//Application Context
	public void init(ServletConfig conf) throws ServletException {
		   super.init(conf);
		   ServletContext service = conf.getServletContext();
	    	int user_id = 1;
	        User existingUserc;
			try {
				existingUserc = userDAO.getUser(user_id);
				   service.setAttribute("userc", existingUserc);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
   // @Override
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
            case "/usercreate":
                createUser(request,response);
                break;
            case "/userinsert":     
                insertUser(request, response);
                break;
            case "/userchange":
                changeUser(request, response);
                break;                
            case "/useredit":
                showEditForm(request, response);
                break;
            case "/userupdate":
                updateUser(request, response);
                break;
            case "/userdelete":
                deleteUser(request, response);
                break;                
            case "/userlist":
                listUser(request, response);
                break;     
            case "/userfilter":
                filterUser(request, response);
                break;
            case "/userlogin":
                Userlogin(request, response);
                break;
            case "/userlogout":
                Userlogout(request, response);
                break;
            default:
                getUser(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }	

//User Login
private void Userlogin(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
	String username = request.getParameter("username");
    String password = request.getParameter("password");     
     
        User user = userDAO.checkLogin(username, password);
        String destPage = "Login.jsp";
         
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            destPage = "page_main.jsp";
        } else {
            String message = "Invalid Username/password";
            request.setAttribute("message", message);
        }
         
        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);         
}	

//User Logout
private void Userlogout(HttpServletRequest request, HttpServletResponse response)
      throws SQLException, IOException, ServletException {
	HttpSession session = request.getSession(false);
    if (session != null) {
        session.removeAttribute("user");
        RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
        dispatcher.forward(request, response);
    }
}

//Create User
	private void createUser(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {
	    RequestDispatcher dispatcher = request.getRequestDispatcher("User/User_Create.jsp");
	    dispatcher.forward(request, response);
	}
	
//Filter User
	private void filterUser(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {

		int userId = Integer.parseInt(request.getParameter("user"));
   	    request.setAttribute("selectedUserId", userId);		
   	 User existingUser = userDAO.getUser(userId);
     RequestDispatcher dispatcher = request.getRequestDispatcher("User/User_Change.jsp");
     request.setAttribute("user", existingUser);
     dispatcher.forward(request, response);

	}	
//List User
private void listUser(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    List<User> listUser = userDAO.listAllUsers();
    request.setAttribute("listUser", listUser);
    RequestDispatcher dispatcher = request.getRequestDispatcher("User/User_Display.jsp");
    dispatcher.forward(request, response);
}

//Change User
private void changeUser(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    List<User> listUser = userDAO.listAllUsers();
    request.setAttribute("listUser", listUser);
    RequestDispatcher dispatcher = request.getRequestDispatcher("User/User_Change.jsp");
    dispatcher.forward(request, response);
}
//private void showNewForm(HttpServletRequest request, HttpServletResponse response)
//        throws ServletException, IOException {
//    RequestDispatcher dispatcher = request.getRequestDispatcher("User_Create.jsp");
//    dispatcher.forward(request, response);
//}

private void showEditForm(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {
    int user_id = Integer.parseInt(request.getParameter("user_id"));
    User existingUser = userDAO.getUser(user_id);
    RequestDispatcher dispatcher = request.getRequestDispatcher("User/User_Change.jsp");
    request.setAttribute("user", existingUser);
    dispatcher.forward(request, response);
}

private void insertUser(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String access = request.getParameter("access");
    String firstname = request.getParameter("firstname");
    String lastname = request.getParameter("lastname");

    User newUser = new User(username, password, access, firstname, lastname);
    userDAO.insertUser(newUser);
//    response.sendRedirect("User");
// Call User Home page
    response.setContentType("text/html");
	PrintWriter out=response.getWriter();
	out.println("<!DOCTYPE html>");
	out.println("<html>");
	out.println("<head>");
//	out.println("<title>User Details</title>");
	out.println("<link rel='stylesheet' href='./CSS/Page_Main.css'/>");
	out.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css'/>");		
	out.println("</head>");
	out.println("<body class='bg'>");
//  ***Header***
	request.getRequestDispatcher("/Header.jsp").include(request, response);
//  ***Horizontal Navigation***
	request.getRequestDispatcher("/Hor_Navigation.jsp").include(request, response);
	out.println("<div class='container'>");
//  ***Content Row***
	request.getRequestDispatcher("User/User.jsp").include(request, response);
	out.println("</div>");	
//Success Creation message;
	out.println("<h6 style='color: #b3003b;' class='text-center'>'");
	out.printf(username);
	out.println("' User Created Successfully!!!</h6>");
	//out.println( "<h6 style='color: #b3003b;' class='text-center'>User Created Successfully</h6>");	
	out.println("</body>");
	out.println("</html>");		
	out.close();
}

private void updateUser(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException {
    int user_id = Integer.parseInt(request.getParameter("user_id"));
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String access = request.getParameter("access");
    String firstname = request.getParameter("firstname");
    String lastname = request.getParameter("lastname");

    User user = new User(user_id, username, password, access, firstname, lastname);
    userDAO.updateUser(user);
    response.sendRedirect("userlist");
}

private void deleteUser(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException {
    int user_id = Integer.parseInt(request.getParameter("user_id"));

    User user = new User(user_id);
    userDAO.deleteUser(user);
    response.sendRedirect("userlist");

}

private void getUser(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {
   // int user_id = Integer.parseInt(request.getParameter("user_id"));
	HttpSession session=request.getSession();
	int user_id = 1;
    User existingUserc = userDAO.getUser(user_id);
    session.setAttribute("userc", existingUserc);
}

}
