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

import dao.ProductDAO;
import main.Product;
import main.Sub_Group;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet(urlPatterns = {"/Product","/productcreate", "/productchange", "/productinsert",
		                   "/productedit", "/productupdate", "/productdelete",
		                   "/productlist", "/productlistajax", "/productfilter"})
public class ProductControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO productDAO; 
	private String constant_product = "product", constant_uom = "uom";

	public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        productDAO = new ProductDAO(jdbcURL, jdbcUsername, jdbcPassword);            
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
            case "/Product":
                Product(request,response);
                break;
            case "/productcreate":
                createProduct(request,response);
                break;
            case "/productinsert":     
                insertProduct(request, response);
                break;
            case "/productchange":
                changeProduct(request, response);
                break;                
            case "/productedit":
                showEditForm(request, response);
                break;
            case "/productupdate":
                updateProduct(request, response);
                break;
            case "/productdelete":
                deleteProduct(request, response);
                break;                
            case "/productlist":
                listProduct(request, response);
                break;
            case "/productlistajax":
                listajaxProduct(request, response);
                break;
            case "/productfilter":
                filterProduct(request, response);
                break;
            default:
                getProduct(request, response);
                break;
            }
        } 
		catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }	
	
//Create Product
	private void createProduct(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {

		List<Sub_Group> listSub_Group = productDAO.listProductSub_Groups(constant_product);
		List<Sub_Group> listSub_Groupuom = productDAO.listProductSub_Groups(constant_uom);
	    request.setAttribute("listSub_Group", listSub_Group);
	    request.setAttribute("listSub_Groupuom", listSub_Groupuom);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("Product/Product_Create.jsp");
	    dispatcher.include(request, response);
	}
	
//Filter Product
	private void filterProduct(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {

		int productId = Integer.parseInt(request.getParameter("product"));
   	    //request.setAttribute("selectedProductId", productId);		
   	 Product existingProduct = productDAO.getProduct(productId);
//Dropdown
	List<Sub_Group> listSub_Group = productDAO.listProductSub_Groups(constant_product);
	List<Sub_Group> listSub_Groupuom = productDAO.listProductSub_Groups(constant_uom);
    request.setAttribute("listSub_Group", listSub_Group);
    request.setAttribute("listSub_Groupuom", listSub_Groupuom);
    
     RequestDispatcher dispatcher = request.getRequestDispatcher("Product/Product_Change.jsp");
     request.setAttribute("product", existingProduct);
     dispatcher.forward(request, response);

	}	
//List Product
private void listProduct(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    List<Product> listProduct = productDAO.listAllProducts();
    request.setAttribute("listProduct", listProduct);
    RequestDispatcher dispatcher = request.getRequestDispatcher("Product/Product_Display.jsp");
    dispatcher.forward(request, response);
}
//ListAJAX Product - Not used
private void listajaxProduct(HttpServletRequest request, HttpServletResponse response)
      throws SQLException, IOException, ServletException {
  List<Product> listProduct = productDAO.listAllProducts();
//AJAX
  String json = new Gson().toJson(listProduct);
  
  response.setContentType("application/json");
  response.setCharacterEncoding("UTF-8");
  response.getWriter().write(json);
}
//Change Product
private void changeProduct(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    List<Product> listProduct = productDAO.listAllProducts();
    request.setAttribute("listProduct", listProduct);
    RequestDispatcher dispatcher = request.getRequestDispatcher("Product/Product_Change.jsp");
    dispatcher.forward(request, response);
}
//private void showNewForm(HttpServletRequest request, HttpServletResponse response)
//        throws ServletException, IOException {
//    RequestDispatcher dispatcher = request.getRequestDispatcher("Product_Create.jsp");
//    dispatcher.forward(request, response);
//}

private void showEditForm(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {
    int product_id = Integer.parseInt(request.getParameter("product_id"));
    Product existingProduct = productDAO.getProduct(product_id);
//Dropdown
	List<Sub_Group> listSub_Group = productDAO.listProductSub_Groups(constant_product);
	List<Sub_Group> listSub_Groupuom = productDAO.listProductSub_Groups(constant_uom);
    request.setAttribute("listSub_Group", listSub_Group);
    request.setAttribute("listSub_Groupuom", listSub_Groupuom);
    
    RequestDispatcher dispatcher = request.getRequestDispatcher("Product/Product_Change.jsp");
    request.setAttribute("product", existingProduct);
    dispatcher.forward(request, response);
}

private void insertProduct(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    String name = request.getParameter("name");
    String group_name = request.getParameter("group_name");
    String subgroup_name = request.getParameter("subgroup_name");
    String uom = request.getParameter("uom");
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
    
    Product newProduct = new Product(name, group_name, subgroup_name, uom, 
    		comments, changedby, changeddate, changedtime, createddate);
    productDAO.insertProduct(newProduct);
    
    String message = name+" :Product Created Successfully";    
//AJAX    
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(message);
   // response.getStatus();
// Call Product Creation page
//    RequestDispatcher dispatcher = request.getRequestDispatcher("Product/Product_Create.jsp");
//    dispatcher.forward(request, response);  
}

private void updateProduct(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    int product_id = Integer.parseInt(request.getParameter("product_id"));
    String name = request.getParameter("name");
    String group_name = request.getParameter("group_name");
    String subgroup_name = request.getParameter("subgroup_name");
    String uom = request.getParameter("uom");
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
    Product product = new Product(product_id, name, group_name, subgroup_name, uom, comments, changedby, changeddate, changedtime, createddate);
    productDAO.updateProduct(product);
    
    String message = name+" :Product Updated Successfully";
  //AJAX    
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(message);    
    //response.sendRedirect("productlist");
}

private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException {
    int product_id = Integer.parseInt(request.getParameter("product_id"));

    Product product = new Product(product_id);
    productDAO.deleteProduct(product);
     
    String message = "Selected Product Deleted Successfully";
    //AJAX    
    //response.setContentType("text/plain");
    //response.setCharacterEncoding("UTF-8");
    //response.getWriter().write(productmessage);      
    request.setAttribute("message", message);
    response.sendRedirect("productlist");

}

private void getProduct(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {
   // int product_id = Integer.parseInt(request.getParameter("product_id"));
	HttpSession session=request.getSession();
	int product_id = 1;
    Product existingProductc = productDAO.getProduct(product_id);
    session.setAttribute("productc", existingProductc);
}

//Main Page Product
	private void Product(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {
	    RequestDispatcher dispatcher = request.getRequestDispatcher("Product/Product_initial.jsp");
	    dispatcher.include(request, response);
	}

}
