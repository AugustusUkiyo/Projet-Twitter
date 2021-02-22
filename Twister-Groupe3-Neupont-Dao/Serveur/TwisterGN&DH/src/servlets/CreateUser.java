package servlets;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.User;

@SuppressWarnings("serial")
public class CreateUser extends HttpServlet{
	
	public CreateUser() { }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");		
		
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		
		JSONObject x = User.createUser(nom,prenom,login,password,password2); 
		out.println(x.toString()); //{}
	}
}
