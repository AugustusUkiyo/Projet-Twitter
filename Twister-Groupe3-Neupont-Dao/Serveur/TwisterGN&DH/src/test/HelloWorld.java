package test;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
/**
 * Servlet implementation class HelloWorld
 */
@SuppressWarnings("serial")
public class HelloWorld extends HttpServlet {
 
 /**
	 * 
	 */
	//private static final long serialVersionUID = 5655090058815084878L;

/**
 * Default constructor.
 */
 public HelloWorld() {
 }
 
 /*
 * This method will handle all GET request.
 */
 protected void doGet(HttpServletRequest request,
 HttpServletResponse response) throws ServletException, IOException {
 
 	response.setContentType( " text / plain " );
	PrintWriter out = response.getWriter ();
	out.println( " Hello , hello !!!! " );
 }
 

}
