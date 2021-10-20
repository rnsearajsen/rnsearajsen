
//************************************Not Used********************************************
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Company extends HttpServlet 
{    
	// TO avoid Warning added this 'SerialVersionUID
	private static final long serialVersionUID = -1641096228274971485L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
//		out.println("<title>Company Details</title>");
		out.println("<link rel='stylesheet' href='./CSS/Page_Main.css'/>");
		out.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css'/>");	
		out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js'></script>");
		out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js'></script>");
		out.println("<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js'></script>");
		out.println("</head>");
		out.println("<body class='bg'>");
//      ***Header***
		request.getRequestDispatcher("/Header.jsp").include(request, response);
//      ***Horizontal Navigation***
		request.getRequestDispatcher("/Hor_Navigation.jsp").include(request, response);
		out.println("<div class='container'>");
//      ***Content Row***
		request.getRequestDispatcher("/Company/Company.jsp").include(request, response);
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");		
		out.close();	
	}
}