package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.ReportDAO;
import main.Report;
import main.Sub_Group;

@WebServlet(urlPatterns = {"/Salesrpt", "/Salesrpt_ajax", "/Custcrdrpt"})

public class ReportControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReportDAO reportDAO; 
	private String constant_product = "product";
	public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        reportDAO = new ReportDAO(jdbcURL, jdbcUsername, jdbcPassword);            
	}
   //@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);		
	}
//*************************************************
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();		
		try {      
            switch (action) {
            case "/Salesrpt":
                Salesrpt(request,response);
                break;
            case "/Salesrpt_ajax":
            	Salesrpt_ajax(request,response);
                break;
            case "/Custcrdrpt":
            	Custcrdrpt(request,response);
                break;    
            }
		}
		catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }	
//*************************************************	
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
//*************************************************
//Main Page Sales Report
		private void Salesrpt(HttpServletRequest request, HttpServletResponse response)
		        throws SQLException, IOException, ServletException {
			Calendar now = Calendar.getInstance();
			int intyear = now.get(Calendar.YEAR);
			String syear = String.valueOf(intyear);
			List<Sub_Group> listSub_Group = reportDAO.listSalesSub_Groups(constant_product);
			request.setAttribute("listSub_Group", listSub_Group);
			request.setAttribute("syear", syear);
			request.setAttribute("btnrptgo", "btnsalesrpt");
		    RequestDispatcher dispatcher = request.getRequestDispatcher("Report/Salesrpt.jsp");
		    dispatcher.include(request, response);
		}	
//*****************************************************
//Sales Report
		private void Salesrpt_ajax(HttpServletRequest request, HttpServletResponse response)
		        throws SQLException, IOException, ServletException {
			String subgroup_name = request.getParameter("subgroup_name");
			String rptype = request.getParameter("rptype");
			String month = request.getParameter("month");
			String year = request.getParameter("year");
			//SQL Date Conversion		
		    String date = request.getParameter("sdate");
		    Date sdate = convertStringToDate(date);
		    date = request.getParameter("edate");
		    Date edate = convertStringToDate(date);
		    List<Report> listSalesrpt = reportDAO.listSalesrpt(subgroup_name,sdate,edate,rptype,month,year);
			//AJAX    
			  String json = new Gson().toJson(listSalesrpt);
			  
			  response.setContentType("application/json");
			  response.setCharacterEncoding("UTF-8");
			  response.getWriter().write(json);		    
		}
//*************************************************
//Main Page Customer Credit Report
				private void Custcrdrpt(HttpServletRequest request, HttpServletResponse response)
				        throws SQLException, IOException, ServletException {
					Calendar now = Calendar.getInstance();
					int intyear = now.get(Calendar.YEAR);
					String syear = String.valueOf(intyear);
					//List<Sub_Group> listSub_Group = reportDAO.listSalesSub_Groups(constant_product);
					//request.setAttribute("listSub_Group", listSub_Group);
					request.setAttribute("syear", syear);
					request.setAttribute("btnrptgo", "btncustcrdrpt");
				    RequestDispatcher dispatcher = request.getRequestDispatcher("Report/Custcrdrpt.jsp");
				    dispatcher.include(request, response);
				}		
}