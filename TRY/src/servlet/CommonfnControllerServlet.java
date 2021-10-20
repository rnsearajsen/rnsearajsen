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
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletConfig;
//import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.CommonfnDAO;
import main.Commonfn;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet(urlPatterns = {"/product_list"})
public class CommonfnControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommonfnDAO commonfnDAO; 

	public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        commonfnDAO = new CommonfnDAO(jdbcURL, jdbcUsername, jdbcPassword);            
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
            case "/product_list":
            	listProduct(request, response);
                break;
            default:
               // getCommonfn(request, response);
                break;
            }
        } 
		catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }	
	
//Product list - From Sub-Group
		private void listProduct(HttpServletRequest request, HttpServletResponse response)
			      throws SQLException, IOException, ServletException {
			String subgroup_name = request.getParameter("subgroup_name");
			List<Commonfn> listProduct = commonfnDAO.listProductDyn(subgroup_name);
			//AJAX
			  String json = new Gson().toJson(listProduct);
			  
			  response.setContentType("application/json");
			  response.setCharacterEncoding("UTF-8");
			  response.getWriter().write(json);
			}

}
