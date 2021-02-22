package tools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;


public class FriendTools {
	
	
	//IS_FRIEND : RENVOIE TRUE SI ID_USER A POUR AMI IF_FRIEND (AMITIE PAS FORCEMENT RECIPROQUE)
	public static boolean is_friend(String id_user,String id_friend) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException  {
		boolean rest;
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection con = Database.getMySQLConnection();
		String req = "select * from Friends where id_user='"+id_user+"' and id_friend='"+id_friend+"';";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(req);	
		if(rs.next()) {
			rest = true;
		}
		else {
			rest = false;
		}
			
		rs.close();
		st.close();
		con.close();
			
		return rest;
	}

	//ADD_FRIEND : AJOUTE L'AMI ID_FRIEND A ID_USER DANS LA BD FRIENDS
	public static JSONObject addFriend(String id_user,String id_friend) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, JSONException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection con = Database.getMySQLConnection();
		String req = "INSERT INTO Friends (id_user,id_friend) VALUES ('"+id_user+"','"+id_friend+"');";
		Statement st = con.createStatement();
		int res = st.executeUpdate(req);	
		st.close();
		con.close();		
		
		if(res == 1) {
			JSONObject x = new JSONObject();
			x.put("status", "");
			return x; //{}			
		}
		return ServicesTools.error("opération insertion BD Friends incorrecte",1000);
	}

	//DELETE_FRIEND : SUPPRIME L'AMI ID_FRIEND DE ID_USER DE LA BD FRIENDS
	public static JSONObject deleteFriend(String id_user,String id_friend) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, JSONException  {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection con = Database.getMySQLConnection();
		String req = "DELETE from Friends where id_user='"+id_user+"' and id_friend='"+id_friend+"';";
		Statement st = con.createStatement();
		int res = st.executeUpdate(req);
		st.close();
		con.close();
		
		if(res == 1) {
			JSONObject x = new JSONObject();
			x.put("status", "");
			return x; //{}		
		}
		return ServicesTools.error("opération suppression BD Friends incorrecte",1000);
	}

	//LIST_FRIEND : CREE UN JSON OBJECT CONTENANT UNE LISTE DES INFOS DES AMIS DE ID_USER
	public static JSONObject listFriend(String id_user) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, JSONException  {
		List<JSONObject> list_friend = new ArrayList<JSONObject>();
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection con = Database.getMySQLConnection();
		String req = "select id_friend from Friends where id_user='"+id_user+"';";
		Statement st = con.createStatement();
		ResultSet curseur = st.executeQuery(req);
		
		JSONObject x = new JSONObject();
		while(curseur.next()) {
			String id_friend = curseur.getString("id_friend");
			list_friend.add(UserTools.afficheUser(id_friend)); 
		}
		st.close();
		con.close();
		
		x.put("amis",list_friend);
		x.put("status", "");
		
		return x; // liste des amis
	}
}