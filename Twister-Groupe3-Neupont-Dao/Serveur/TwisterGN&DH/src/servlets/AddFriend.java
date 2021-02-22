package servlets;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.Friend;

@SuppressWarnings("serial")
public class AddFriend extends HttpServlet {

	public AddFriend() { }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String key = request.getParameter("key");
		String login_friend = request.getParameter("id_friend");
		
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		
		JSONObject x = Friend.addFriend(key,login_friend); 
		out.println(x.toString()); //{}
	}
}