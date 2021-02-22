package test;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.Operation;

@SuppressWarnings("serial")
public class Addition extends HttpServlet{
	
	public void doGet(HttpServletRequest request,
			 HttpServletResponse response) throws ServletException, IOException {
		String a = request.getParameter("a");
		String b = request.getParameter("b");
		String choix = request.getParameter("c");
		double res = Operation.calcul(a,b,choix);
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		out.println("résultat : "+res + " " + a + " " + b + " " + choix);
	}	

}


