package tools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/*import java.text.DateFormat;
import java.text.SimpleDateFormat;*/
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;


public class AuthentificationTools {
	
	// USER_EXISTS : TRUE SI UN UTILISATEUR AYANT CE LOGIN EXISTE, FALSE SINON
	public static boolean userExists(String login) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		boolean res;
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection con = Database.getMySQLConnection();
		String req = "select id_user from Users where login='"+login+"';";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(req);	
		if(rs.next()) {
			res = true;
		}
		else {
			res = false;
		}
			
		rs.close();
		st.close();
		con.close();
			
		return res;
	}
	
	// ID_EXISTS : TRUE SI UN UTILISATEUR AYANT CET ID EXISTE, FALSE SINON
	public static boolean idExists(String id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		boolean res;
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection con = Database.getMySQLConnection();
		String req = "select id_user from Users where id_user='"+id+"';";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(req);	
		if(rs.next()) {
			res = true;
		}
		else {
			res = false;
		}
			
		rs.close();
		st.close();
		con.close();
			
		return res;
	}
	
	// GET_ID_USER : RENVOIE L'ID D'UN UTILISATEUR A PARTIR DE SON LOGIN
	public static int getIdUser(String login) throws InstantiationException , IllegalAccessException , ClassNotFoundException, SQLException{
		int id;
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection con = Database.getMySQLConnection();
		String req = "select id_user from Users where login='"+login+"';";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(req);
				
		rs.next();
		id = rs.getInt("id_user");
						
		rs.close();
		st.close();
		con.close();
				
		return id;
	}
	
	// CHECK_PASSWORD : VERIFIE QU'IL EXISTE BIEN UN N-UPLET DANS LA BD AVEC CE PASSWORD ET CE LOGIN
	public static boolean checkPassword(String login, String password) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		boolean res;
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection con = Database.getMySQLConnection();
		String req = "select id_user,password from Users where login='"+login+"' and password='"+password+"';";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(req);	
		if(rs.next()) {
			res = true;
		}
		else {
			res = false;
		}
			
		rs.close();
		st.close();
		con.close();
			
		return res;
	}
	
	//SAME_PASSWORDS : TRUE SI LES DEUX PASSWORD SAISIS SONT IDENTIQUES, FALSE SINON
	public static boolean samePasswords(String password,String password2) {
		return password.equals(password2);
	}
	
	//INSERT_SESSION : CREE UN CLE ALEATOIRE ET RAJOUTE CETTE CONNEXION DANS LA TABLE SESSION, RENVOIE LE JSONOBJECT DE REPONSE
	public static JSONObject insertSession(int id,boolean b, String login) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, JSONException {
		// COMMENT UTILISER b ?
		String key;
		Random r = new Random();
		int nb;
		
		do {
			nb = r.nextInt(1000000);
		}while(keyExists(""+nb));
		
		key = ""+nb;

		JSONObject res = new JSONObject();
		
		/*
		String date_deb;
		String date_fin;
		Date actuelle = new Date();
		long future_long = actuelle.getTime() + 86400000; // + 1 jour
		Date future = new Date(future_long);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		date_deb = dateFormat.format(actuelle);
		date_fin = dateFormat.format(future);
		*/
		
		
		GregorianCalendar calendar = new java.util.GregorianCalendar();
		Date dateD = calendar.getTime();
		calendar.add(Calendar.HOUR_OF_DAY, +2);
		Date dateF = calendar.getTime();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		String dateD2 = dateFormat.format(dateD);
		String dateF2 = dateFormat.format(dateF);
		
		res = insertKeySession(id,key,dateD2,dateF2,login);
		
		return res;
	}
	
	//KEY_EXISTS : RENVOIE TRUE SI LA CLE EST PRESENTE DANS LA TABLE SESSION, FALSE SINON
	public static boolean keyExists(String key) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		boolean res;
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection con = Database.getMySQLConnection();
		String req = "select 'cle' from Sessions where cle='"+key+"';";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(req);
				
		if(rs.next()) {
			res = true;
		}
		else {
			res = false;
		}
						
		rs.close();
		st.close();
		con.close();
		
		return res;
	}
	
	public static boolean keyCorrect(String key) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException, ParseException {
		boolean res;
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection con = Database.getMySQLConnection();
		String req = "select date_fin from Sessions where cle='"+key+"';";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(req);
				
		if(rs.next()) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
			
			String dfs = rs.getString("date_fin");
			Date df = dateFormat.parse(dfs);
				
			Date da = new Date();
			String das = dateFormat.format(da);
			da = dateFormat.parse(das);
			
			if(da.after(df)) {
				res = false;
			}
			else {
				res = true;				
			}
		}
		else {
			res = false;
		}
						
		rs.close();
		st.close();
		con.close();
		
		return res;			
	}
	
	//INSERT_KEY_SESSION : INSERE UNE "CONNEXION" DANS LA TABLE SESSION
	public static JSONObject insertKeySession(int id, String key, String date_deb, String date_fin, String login) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, JSONException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection con = Database.getMySQLConnection();
		String req = "INSERT INTO Sessions (id_user,cle,date_deb,date_fin) VALUES ('"+id+"','"+key+"','"+date_deb+"','"+date_fin+"');";
		Statement st = con.createStatement();
		int res = st.executeUpdate(req);	
		st.close();
		con.close();		
		
		if(res == 1) {
			JSONObject x = new JSONObject();
			x.put("id", id);
			x.put("login", login);
			x.put("key", key);
			x.put("status", "");
			return x;			
		}
		return ServicesTools.error("opération insertion BD Sessions incorrecte",1000);	
		
	}
	
	//CLOSE_SESSION : SUPPRIMER UNE "CONNEXION" DE LA TABLE SESSION
	public static JSONObject closeSession(String key) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, JSONException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection con = Database.getMySQLConnection();
	
		GregorianCalendar calendar = new java.util.GregorianCalendar();
		calendar.add(Calendar.SECOND, -1); //Le test se faisait très rapidement, quand on créé une session et qu'on la ferme rapidement après la clé reste correcte alors que la session est fermée (tout s'est fait à la même date -> à la même seconde)
		Date d = calendar.getTime();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		String dateclose = dateFormat.format(d);
		
		String req = "UPDATE Sessions SET date_fin='"+dateclose+"' WHERE cle='"+key+"';";
		Statement st = con.createStatement();
		int res = st.executeUpdate(req);	
		st.close();
		con.close();		
		
		if(res == 1) {
			JSONObject x = new JSONObject();
			x.put("status", "");
			return x; //{}			
		}
		return ServicesTools.error("opération insertion BD Sessions incorrecte",1000);	
	}
}
