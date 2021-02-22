package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.Message;

@SuppressWarnings("serial")
public class AddMessage extends HttpServlet{
	public AddMessage() { }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String key = request.getParameter("key");
		String text = request.getParameter("text");
		
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		
		JSONObject x = Message.addMessage(key,text); 
		out.println(x); //{}
	}
}
