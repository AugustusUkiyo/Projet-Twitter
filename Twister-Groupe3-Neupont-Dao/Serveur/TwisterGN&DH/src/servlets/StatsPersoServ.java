package servlets;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.Stats;

@SuppressWarnings("serial")
public class StatsPersoServ extends HttpServlet{
	public StatsPersoServ() { }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String key = request.getParameter("key");
		String id = request.getParameter("id");
		
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		
		JSONObject x = Stats.statistiquesPerso(key,id); 
		out.println(x.toString()); 
		out.flush();
	}


}
