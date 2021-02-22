package tools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONException;
import org.json.JSONObject;

public class UserTools {
	
	//INSERT_USER : INSERE UN NOUVEL USER DANS LA BD USERS
	public static JSONObject insertUser(String nom, String prenom, String login, String password) throws JSONException, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection con = Database.getMySQLConnection();
		String req = "INSERT INTO Users (login,password,nom,prenom) VALUES ('"+login+"','"+password+"','"+nom+"','"+prenom+"');";
		Statement st = con.createStatement();
		int res = st.executeUpdate(req);	
		st.close();
		con.close();		
		
		if(res == 1) {
			JSONObject x = new JSONObject();
			x.put("status", "");
			return x; 		
		}
		return ServicesTools.error("opération insertion BD Users incorrecte",1000);
	}
	
	//GET_ID_FROM_KEY : RETOURNE L'ID CORRESPONDANT A LA CLE KEY
	public static int getIdFromKey(String key) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection con = Database.getMySQLConnection();
		String req = "select id_user from Sessions where cle='"+key+"';";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(req);	
		
		rs.next(); //On avait vérifié que la clé existait, si elle existe l'id aussi.
		int id_user = rs.getInt("id_user");
	
		rs.close();
		st.close();
		con.close();
		
		return id_user;		
	}
	
	//GET_NAME_FROM_ID : RETOURNE LE NOM CORRESPONDANT A L'ID 
	public static String getNameFromId(String id) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection con = Database.getMySQLConnection();
		String req = "select nom from Users where id_user='"+id+"';";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(req);	
		
		String nom = null;
		
		if(rs.next()) {
			nom = rs.getString("nom");
		}
			
		rs.close();
		st.close();
		con.close();
		
		return nom;		
	}
	
	//GET_FIRSTNAME_FROM_ID : RETOURNE LE NOM CORRESPONDANT A L'ID 
	public static String getFirstNameFromId(String id) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection con = Database.getMySQLConnection();
		String req = "select prenom from Users where id_user='"+id+"';";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(req);	
		
		String prenom = null;
		
		if(rs.next()) {
			prenom = rs.getString("prenom");
		}
			
		rs.close();
		st.close();
		con.close();
		
		return prenom;		
	}
	
	//AFFICHE_USER : RENVOIE UN JSON_OBJECT AVEC TOUTES LES INFOS D'UN USER (ID, NOM, PRENOM)
	public static JSONObject afficheUser(String id) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException, JSONException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection con = Database.getMySQLConnection();
		String req = "select * from Users where id_user='"+id+"';";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(req);	
		
		JSONObject res = new JSONObject();
		
		if(rs.next()) {
			int id_user = rs.getInt(1);
			String nom = rs.getString(4);
			String prenom = rs.getString(5);
			res.put("id", id_user);
			res.put("nom", nom);
			res.put("prenom", prenom);
		}
			
		rs.close();
		st.close();
		con.close();
		
		return res;
	}
	
}
