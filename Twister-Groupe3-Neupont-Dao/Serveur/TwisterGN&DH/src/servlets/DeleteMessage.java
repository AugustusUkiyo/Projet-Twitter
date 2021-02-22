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
public class DeleteMessage extends HttpServlet{
	
	public DeleteMessage() { }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String key = request.getParameter("key");
		String date = request.getParameter("date");
		
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		
		JSONObject x = Message.deleteMessage(key,date);
		out.println(x); //{}
	}
}
