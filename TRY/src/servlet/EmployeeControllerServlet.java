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

import dao.EmployeeDAO;
import main.Employee;
import main.Sub_Group;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet(urlPatterns = {"/Employee","/employeecreate", "/employeechange", "/employeeinsert",
		                   "/employeeedit", "/employeeupdate", "/employeedelete",
		                   "/employeelist", "/employeelistajax", "/employeefilter"})
public class EmployeeControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeDAO employeeDAO;   
	private String constant_gender = "gender", constant_employee = "employee",
			       constant_idtype = "idcardtype";

	public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        employeeDAO = new EmployeeDAO(jdbcURL, jdbcUsername, jdbcPassword);            
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
            case "/Employee":
                Employee(request,response);
                break;
            case "/employeecreate":
                createEmployee(request,response);
                break;
            case "/employeeinsert":     
                insertEmployee(request, response);
                break;
            case "/employeechange":
                changeEmployee(request, response);
                break;                
            case "/employeeedit":
                showEditForm(request, response);
                break;
            case "/employeeupdate":
                updateEmployee(request, response);
                break;
            case "/employeedelete":
                deleteEmployee(request, response);
                break;                
            case "/employeelist":
                listEmployee(request, response);
                break;
            case "/employeelistajax":
                listajaxEmployee(request, response);
                break;
            case "/employeefilter":
                filterEmployee(request, response);
                break;
            default:
                getEmployee(request, response);
                break;
            }
        } 
		catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }	
	
//Create Employee
	private void createEmployee(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {
		
		List<Sub_Group> listSub_Group = employeeDAO.listEmployeeSub_Groups(constant_employee);
		request.setAttribute("listSub_Group", listSub_Group);
//IDCard Type
		List<Sub_Group> listSub_Groupidtype = employeeDAO.listEmployeeSub_Groups(constant_idtype);
		request.setAttribute("listSub_Groupidtype", listSub_Groupidtype);
//Gender
		List<Sub_Group> listSub_Groupgender = employeeDAO.listEmployeeSub_Groups(constant_gender);
		request.setAttribute("listSub_Groupgender", listSub_Groupgender);
	    
	    
	    RequestDispatcher dispatcher = request.getRequestDispatcher("Employee/Employee_Create.jsp");
	    dispatcher.include(request, response);
	}
	
//Filter Employee
	private void filterEmployee(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {

		int employeeId = Integer.parseInt(request.getParameter("employee"));
   	    //request.setAttribute("selectedEmployeeId", employeeId);		
   	 Employee existingEmployee = employeeDAO.getEmployee(employeeId);
//Dropdown
	List<Sub_Group> listSub_Group = employeeDAO.listEmployeeSub_Groups(constant_employee);
	List<Sub_Group> listSub_Groupidtype = employeeDAO.listEmployeeSub_Groups(constant_idtype);
    request.setAttribute("listSub_Group", listSub_Group);
    request.setAttribute("listSub_Groupidtype", listSub_Groupidtype);
//Gender
    List<Sub_Group> listSub_Groupgender = employeeDAO.listEmployeeSub_Groups(constant_gender);
	request.setAttribute("listSub_Groupgender", listSub_Groupgender);
     RequestDispatcher dispatcher = request.getRequestDispatcher("Employee/Employee_Change.jsp");
     request.setAttribute("employee", existingEmployee);
     dispatcher.forward(request, response);

	}	
//List Employee
private void listEmployee(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    List<Employee> listEmployee = employeeDAO.listAllEmployees();
    request.setAttribute("listEmployee", listEmployee);
    RequestDispatcher dispatcher = request.getRequestDispatcher("Employee/Employee_Display.jsp");
    dispatcher.forward(request, response);
}
//ListAJAX Employee - Not used
private void listajaxEmployee(HttpServletRequest request, HttpServletResponse response)
      throws SQLException, IOException, ServletException {
  List<Employee> listEmployee = employeeDAO.listAllEmployees();
//AJAX
  String json = new Gson().toJson(listEmployee);
  
  response.setContentType("application/json");
  response.setCharacterEncoding("UTF-8");
  response.getWriter().write(json);
}
//Change Employee
private void changeEmployee(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    List<Employee> listEmployee = employeeDAO.listAllEmployees();
    request.setAttribute("listEmployee", listEmployee);
    RequestDispatcher dispatcher = request.getRequestDispatcher("Employee/Employee_Change.jsp");
    dispatcher.forward(request, response);
}
//private void showNewForm(HttpServletRequest request, HttpServletResponse response)
//        throws ServletException, IOException {
//    RequestDispatcher dispatcher = request.getRequestDispatcher("Employee_Create.jsp");
//    dispatcher.forward(request, response);
//}

private void showEditForm(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {
    int employee_id = Integer.parseInt(request.getParameter("employee_id"));
    Employee existingEmployee = employeeDAO.getEmployee(employee_id);
//Dropdown
	List<Sub_Group> listSub_Group = employeeDAO.listEmployeeSub_Groups(constant_employee);
	List<Sub_Group> listSub_Groupidtype = employeeDAO.listEmployeeSub_Groups(constant_idtype);
//Gender
	List<Sub_Group> listSub_Groupgender = employeeDAO.listEmployeeSub_Groups(constant_gender);
	request.setAttribute("listSub_Groupgender", listSub_Groupgender);
    request.setAttribute("listSub_Group", listSub_Group);
    request.setAttribute("listSub_Groupidtype", listSub_Groupidtype);
    
    RequestDispatcher dispatcher = request.getRequestDispatcher("Employee/Employee_Change.jsp");
    request.setAttribute("employee", existingEmployee);
    dispatcher.forward(request, response);
}

private void insertEmployee(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    String name = request.getParameter("name");
    String group_name = request.getParameter("group_name");
    String gender = request.getParameter("gender");
    String subgroup_name = request.getParameter("subgroup_name");
    Double salary = Double.parseDouble(request.getParameter("salary"));
    String address = request.getParameter("address");    
    String contact1 = request.getParameter("contact1");
    String contact2 = request.getParameter("contact2");
    String idtype1 = request.getParameter("idtype1");
    String idnum1 = request.getParameter("idnum1");
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
    
    Employee newEmployee = new Employee(name, group_name, gender, subgroup_name, salary, address, contact1, contact2, idtype1,idnum1,
    		comments, joindate, lastdate, changedby, changeddate, changedtime);
    employeeDAO.insertEmployee(newEmployee);
    
    String message = name+" :Employee Created Successfully";    
//AJAX    
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(message);
   // response.getStatus();
// Call Employee Creation page
//    RequestDispatcher dispatcher = request.getRequestDispatcher("Employee/Employee_Create.jsp");
//    dispatcher.forward(request, response);  
}

private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    int employee_id = Integer.parseInt(request.getParameter("employee_id"));
    String name = request.getParameter("name");
    String group_name = request.getParameter("group_name");
    String gender = request.getParameter("gender");
    String subgroup_name = request.getParameter("subgroup_name");
    Double salary = Double.parseDouble(request.getParameter("salary"));
    String address = request.getParameter("address");    
    String contact1 = request.getParameter("contact1");
    String contact2 = request.getParameter("contact2");
    String idtype1 = request.getParameter("idtype1");
    String idnum1 = request.getParameter("idnum1");
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

    Employee employee = new Employee(employee_id, name, group_name, gender, subgroup_name, salary, address, contact1, contact2, idtype1,idnum1, 
    		comments, joindate, lastdate, changedby, changeddate, changedtime );
    employeeDAO.updateEmployee(employee);
    
    String message = name+" :Employee Updated Successfully";
  //AJAX    
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(message);    
    //response.sendRedirect("employeelist");
}

private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException {
    int employee_id = Integer.parseInt(request.getParameter("employee_id"));

    Employee employee = new Employee(employee_id);
    employeeDAO.deleteEmployee(employee);
     
    String message = "Selected Employee Deleted Successfully";
    //AJAX    
    //response.setContentType("text/plain");
    //response.setCharacterEncoding("UTF-8");
    //response.getWriter().write(employeemessage);      
    request.setAttribute("message", message);
    response.sendRedirect("employeelist");

}

private void getEmployee(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {
   // int employee_id = Integer.parseInt(request.getParameter("employee_id"));
	HttpSession session=request.getSession();
	int employee_id = 1;
    Employee existingEmployeec = employeeDAO.getEmployee(employee_id);
    session.setAttribute("employeec", existingEmployeec);
}

//Main Page Employee
	private void Employee(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {
	    RequestDispatcher dispatcher = request.getRequestDispatcher("Employee/Employee_initial.jsp");
	    dispatcher.include(request, response);
	}

}
