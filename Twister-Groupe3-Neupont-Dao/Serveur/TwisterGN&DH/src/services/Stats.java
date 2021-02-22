package services;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import tools.AuthentificationTools;
import tools.ServicesTools;
import tools.StatsTools;
import tools.UserTools;

public class Stats {
	//SERVICE STATS -----------------------------------------------------------------------------
	
	// SERVICE STATISTIQUES
	
	public static JSONObject statistiques(String key) {
		if(key == null) {
			return(ServicesTools.error("Wrong Arguments",-1)); 
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

			JSONObject res = new JSONObject();
			
			//récupération nb_personnes  
			int nb_users = StatsTools.nbUsers();
			res.append("nb_users", nb_users);
			
			//récupération nb_messages total
			int nb_msg = StatsTools.nbMsg();
			res.append("nb_msg", nb_msg);
			
			return res;
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

	public static JSONObject statistiquesPerso(String key,String id) {
		if((key == null) || (id == null)) {
			return(ServicesTools.error("Wrong Arguments",-1)); 
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

			boolean id_ok = AuthentificationTools.idExists(id);
			if(!id_ok) {
				return(ServicesTools.error("User not found "+id,-1)); 			
			}
			
			JSONObject res = new JSONObject();
			
			//récupération prenom
			String nom = UserTools.getNameFromId(id);
			res.append("nom", nom);
			
			//récupération nom
			String prenom = UserTools.getFirstNameFromId(id);
			res.append("prenom", prenom);
			
			//récupération nb_personnes amies 
			int nb_amis = StatsTools.nbFriends(id);
			res.append("nb_amis", nb_amis);
			
			//récupération nb_messages total envoyés 
			int nb_msgPerso = StatsTools.nbMsgPerso(id);
			res.append("nb_msgPerso", nb_msgPerso);
			
			return res;
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
