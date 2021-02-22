package services;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import tools.AuthentificationTools;
import tools.ServicesTools;
import tools.UserTools;

public class User {
	
	//SERVICE USER -----------------------------------------------------------------------------
	
	// SERVICE CREATE_USER
	
	public static JSONObject createUser(String nom, String prenom, String login, String password, String password2) {
		if((nom == null) || (prenom == null) || (login == null) || (password == null)) {
			return(ServicesTools.error("Wrong Arguments",-1)); 
		}			
		
		try {
			boolean is_user = AuthentificationTools.userExists(login);
			if(is_user) {
				return(ServicesTools.error("User already exists",-1)); 			
			}
			
			boolean same_passwords = AuthentificationTools.samePasswords(password,password2); 
			if(!same_passwords) {
				return(ServicesTools.error("Different passwords !",-1));			
			}

			JSONObject retour = new JSONObject();
			retour = UserTools.insertUser(nom,prenom,login,password);
			
			return retour;
		}
		catch(JSONException e) { 
			return (ServicesTools.error("JSON Problem "+e.getMessage(),100)); 
		} 
		catch(SQLException e) {
			return (ServicesTools.error("SQL Problem "+e.getMessage(),1000)); 			
		}
		catch (Exception e) { 
			return (ServicesTools.error("Problem...",10000)); 
		}
	}
	
	//SERVICE LOGIN
	
	public static JSONObject login(String login, String password) {
		if ((login==null) || (password==null)) {
			return(ServicesTools.error("Wrong Arguments",-1)); 
		}
		
		try { 
			boolean is_user = AuthentificationTools.userExists(login); 
			if (!is_user) {
				return(ServicesTools.error("Unknown user "+login,-1)); 
			}
		
			boolean password_ok=AuthentificationTools.checkPassword(login,password); 
			if (!password_ok) {
				return(ServicesTools.error("Bad password ",-1));
			}

			
			int id_user = AuthentificationTools.getIdUser(login); 
			
			JSONObject retour=new JSONObject(); 
			retour = AuthentificationTools.insertSession(id_user,false, login); 
			
			return retour; 
		} 
		catch(JSONException e) { 
			return (ServicesTools.error("JSON Problem "+e.getMessage(),100)); 
		} 
		catch(SQLException e) {
			return (ServicesTools.error("SQL Problem "+e.getMessage(),1000)); 			
		}
		catch (Exception e) { 
			return (ServicesTools.error("Problem...",10000)); 
		}
		
	}
	
	//SERVICE LOGOUT
	
	public static JSONObject logout(String key) {
		if(key == null) {
			return(ServicesTools.error("Wrong Argument",-1)); 			
		}
		
		try {
			boolean exists_key = AuthentificationTools.keyExists(key);
			if(!exists_key) {
				return(ServicesTools.error("Wrong key",-1));				
			}
			
			boolean correct_key = AuthentificationTools.keyCorrect(key);
			if(!correct_key) {
				return(ServicesTools.error("Wrong key",-1));				
			}
			
			JSONObject retour = new JSONObject();
			retour = AuthentificationTools.closeSession(key);
			return retour;
		}
		catch(JSONException e) { 
			return (ServicesTools.error("JSON Problem "+e.getMessage(),100)); 
		} 
		catch(SQLException e) {
			return (ServicesTools.error("SQL Problem "+e.getMessage(),1000)); 			
		}
		catch (Exception e) { 
			return (ServicesTools.error("Problem...",10000)); 
		}
	}	

}
