package tools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;


public class SearchTools {
	

	//LIST_FIND : TROUVE TOUS LES NOMS ET PRENOMS DES UTILISATEURS AVEC "TEXT" DANS LEUR NOM OU PRENOM
	public static JSONObject listFind(String text) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, JSONException  {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection con = Database.getMySQLConnection();
		String req = "select id_user,nom,prenom from Users where nom LIKE '%"+text+"%' OR prenom LIKE '%"+text+"%' OR CONCAT(prenom, ' ', nom) LIKE '%"+text+"%' OR CONCAT(prenom, ' ', nom) LIKE '%"+text+"%';";               
		Statement st = con.createStatement();
		ResultSet curseur = st.executeQuery(req);

		List<JSONObject> list_find = new ArrayList<JSONObject>();	
		
		JSONObject tmp = new JSONObject();
		JSONObject x = new JSONObject();

		while(curseur.next()) {
			String id = curseur.getString("id_user");
			tmp.append("id",id);
			String nom = curseur.getString("nom");
			tmp.append("nom",nom); 
			String prenom = curseur.getString("prenom");
			tmp.append("prenom",prenom);
			list_find.add(tmp);
			tmp = new JSONObject();
		}
		st.close();
		con.close();
		
		x.put("res",list_find);
		x.put("status", "");
		
		return x; // liste résultat
	}
}