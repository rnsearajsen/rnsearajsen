package servlet;

import java.io.IOException;
//import java.io.PrintWriter;
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

import com.google.gson.Gson;

import dao.CompanyDAO;
import main.Company;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet(urlPatterns = {"/Company","/compcreate", "/compchange", "/compinsert",
		                   "/compedit", "/compupdate", "/compdelete",
		                   "/complist", "/complistajax", "/compfilter"},
           loadOnStartup = 1)
public class CompanyControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CompanyDAO companyDAO;   

	public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        companyDAO = new CompanyDAO(jdbcURL, jdbcUsername, jdbcPassword);            
	}
//Get Company Details	
//Application Context
	public void init(ServletConfig conf) throws ServletException {
		   super.init(conf);
		   ServletContext service = conf.getServletContext();
	    	int company_id = 1;
	        Company existingCompanyc;
			try {
				existingCompanyc = companyDAO.getCompany(company_id);
				   service.setAttribute("companyc", existingCompanyc);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
            case "/Company":
                Company(request,response);
                break;
            case "/compcreate":
                createCompany(request,response);
                break;
            case "/compinsert":     
                insertCompany(request, response);
                break;
            case "/compchange":
                changeCompany(request, response);
                break;                
            case "/compedit":
                showEditForm(request, response);
                break;
            case "/compupdate":
                updateCompany(request, response);
                break;
            case "/compdelete":
                deleteCompany(request, response);
                break;                
            case "/complist":
                listCompany(request, response);
                break;
            case "/complistajax":
                listajaxCompany(request, response);
                break;
            case "/compfilter":
                filterCompany(request, response);
                break;
            default:
                getCompany(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }	
	
//Create Company
	private void createCompany(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {
	    RequestDispatcher dispatcher = request.getRequestDispatcher("Company/Company_Create.jsp");
	    dispatcher.include(request, response);
	}
	
//Filter Company
	private void filterCompany(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {

		int companyId = Integer.parseInt(request.getParameter("company"));
   	    request.setAttribute("selectedCompId", companyId);		
   	 Company existingCompany = companyDAO.getCompany(companyId);
     RequestDispatcher dispatcher = request.getRequestDispatcher("Company/Company_Change.jsp");
     request.setAttribute("company", existingCompany);
     dispatcher.forward(request, response);

	}	
//List Company
private void listCompany(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    List<Company> listCompany = companyDAO.listAllCompanys();
    request.setAttribute("listCompany", listCompany);
    RequestDispatcher dispatcher = request.getRequestDispatcher("Company/Company_Display.jsp");
    dispatcher.forward(request, response);
}
//ListAJAX Company - Not used
private void listajaxCompany(HttpServletRequest request, HttpServletResponse response)
      throws SQLException, IOException, ServletException {
  List<Company> listCompany = companyDAO.listAllCompanys();
//AJAX
  String json = new Gson().toJson(listCompany);
  
  response.setContentType("application/json");
  response.setCharacterEncoding("UTF-8");
  response.getWriter().write(json);
}
//Change Company
private void changeCompany(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    List<Company> listCompany = companyDAO.listAllCompanys();
    request.setAttribute("listCompany", listCompany);
    RequestDispatcher dispatcher = request.getRequestDispatcher("Company/Company_Change.jsp");
    dispatcher.forward(request, response);
}
//private void showNewForm(HttpServletRequest request, HttpServletResponse response)
//        throws ServletException, IOException {
//    RequestDispatcher dispatcher = request.getRequestDispatcher("Company_Create.jsp");
//    dispatcher.forward(request, response);
//}

private void showEditForm(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {
    int company_id = Integer.parseInt(request.getParameter("company_id"));
    Company existingCompany = companyDAO.getCompany(company_id);
    RequestDispatcher dispatcher = request.getRequestDispatcher("Company/Company_Change.jsp");
    request.setAttribute("company", existingCompany);
    dispatcher.forward(request, response);
}

private void insertCompany(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    String name = request.getParameter("name");
    String suffix = request.getParameter("suffix");
    String address = request.getParameter("address");
    String mail = request.getParameter("mail");
    String contact1 = request.getParameter("contact1");
    String contact2 = request.getParameter("contact2");

    Company newCompany = new Company(name, suffix, address, mail, contact1, contact2);
    companyDAO.insertCompany(newCompany);
    
    String message = name+" :Company Created Successfully";    
//AJAX    
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(message);
   // response.getStatus();
// Call Company Creation page
//    RequestDispatcher dispatcher = request.getRequestDispatcher("Company/Company_Create.jsp");
//    dispatcher.forward(request, response);  
}

private void updateCompany(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException {
    int company_id = Integer.parseInt(request.getParameter("company_id"));
    String name = request.getParameter("name");
    String suffix = request.getParameter("suffix");
    String address = request.getParameter("address");
    String mail = request.getParameter("mail");
    String contact1 = request.getParameter("contact1");
    String contact2 = request.getParameter("contact2");

    Company company = new Company(company_id, name, suffix, address, mail, contact1, contact2);
    companyDAO.updateCompany(company);
    
    String message = name+" :Company Updated Successfully";
  //AJAX    
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(message);    
    //response.sendRedirect("complist");
}

private void deleteCompany(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException {
    int company_id = Integer.parseInt(request.getParameter("company_id"));

    Company company = new Company(company_id);
    companyDAO.deleteCompany(company);
     
    String message = "Selected Company Deleted Successfully";
    //AJAX    
    //response.setContentType("text/plain");
    //response.setCharacterEncoding("UTF-8");
    //response.getWriter().write(compmessage);      
    request.setAttribute("message", message);
    response.sendRedirect("complist");

}

private void getCompany(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {
   // int company_id = Integer.parseInt(request.getParameter("company_id"));
	HttpSession session=request.getSession();
	int company_id = 1;
    Company existingCompanyc = companyDAO.getCompany(company_id);
    session.setAttribute("companyc", existingCompanyc);
}
//Main Page Company
	private void Company(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {
	    RequestDispatcher dispatcher = request.getRequestDispatcher("Company/Company_initial.jsp");
	    dispatcher.include(request, response);
	}
}
