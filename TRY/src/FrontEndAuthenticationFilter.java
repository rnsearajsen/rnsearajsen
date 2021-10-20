

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
 
/**
 * This Java filter demonstrates how to intercept the request
 * and transform the response to implement authentication feature
 * for the website's front-end.
 *
 * @author www.codejava.net
 */
//@WebFilter("/userlogin")
public class FrontEndAuthenticationFilter implements Filter {
    private HttpServletRequest httpRequest;
 
    //private static final String[] loginRequiredURLs = {
    //        "/view_profile", "/edit_profile", "/update_profile"
    //};
 
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        httpRequest = (HttpServletRequest) request;
 
      //  String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
 
      //  if (path.startsWith("/admin/")) {
      //      chain.doFilter(request, response);
      //      return;
     //   }
 
        HttpSession session = httpRequest.getSession(false);
 //String Loginuser = (String) session.getAttribute("user");
        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);
 
        String loginURI = httpRequest.getContextPath() + "/userlogin";
        boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI);
        boolean isLoginPage = httpRequest.getRequestURI().endsWith("Login.jsp");
// Logout Check
        String logoutURI = httpRequest.getContextPath() + "/userlogout";
        boolean isLogoutRequest = httpRequest.getRequestURI().equals(logoutURI);
// Initial Login check
        boolean isIniLog = httpRequest.getRequestURI().equals(httpRequest.getContextPath()+"/");
        
        if (isLoggedIn && (isLoginRequest || isLoginPage)) {
        //if (isLoggedIn) {
            // the user is already logged in and he's trying to login again
            // then forward to the homepage
        	String message = "User Already Logged In";
            request.setAttribute("message", message);
            httpRequest.getRequestDispatcher("/").forward(request, response);
        } else if (!isLoggedIn && (isLoginRequest || isLoginPage)) {
        	// the user is not logged in, and request for Login
        	chain.doFilter(request, response);
        //} else if (!isLoggedIn && (isLoginRequest || isLoginPage)) {        
        } else if (!isLoggedIn && session != null && !isLogoutRequest && !isIniLog) { 	
            // the user is not logged in, and the requested page requires
            // authentication, then forward to the login page        	
        	String message = "Don't access directly without Login sequence";
        	request.setAttribute("message", message);
            //httpRequest.getRequestDispatcher("/").forward(request, response);
            //String loginPage = "Login.jsp";
            RequestDispatcher dispatcher = httpRequest.getRequestDispatcher("Login.jsp");
            dispatcher.forward(request, response);
        } else if (!isLoggedIn) {
        	// the user is not logged in, and request for Login
        	chain.doFilter(request, response);
        } else {
            // for other requested pages that do not require authentication
            // or the user is already logged in, continue to the destination
            chain.doFilter(request, response);
        }
    }
 
 
    //private boolean isLoginRequired() {
      //  String requestURL = httpRequest.getRequestURL().toString();
 
        //for (String loginRequiredURL : loginRequiredURLs) {
        //    if (requestURL.contains(loginRequiredURL)) {
        //        return true;
        //    }
       // }
 
      //  return false;
    //}
 
    //public CustomerLoginFilter() {
    //}
 
    public void destroy() {
    }
 
    public void init(FilterConfig fConfig) throws ServletException {
    }
 
}