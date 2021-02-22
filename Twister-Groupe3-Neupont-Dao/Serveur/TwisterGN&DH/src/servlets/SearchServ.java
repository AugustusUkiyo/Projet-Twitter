package servlets;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.Search;

@SuppressWarnings("serial")
public class SearchServ extends HttpServlet {

	public SearchServ() { }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String key = request.getParameter("key");
		String texte = request.getParameter("texte");
		
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		
		JSONObject x = Search.find(key,texte); 
		out.println(x.toString());
	}
}