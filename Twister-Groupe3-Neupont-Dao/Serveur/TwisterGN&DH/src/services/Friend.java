package services;


import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import tools.AuthentificationTools;
import tools.FriendTools;
import tools.ServicesTools;
import tools.UserTools;

public class Friend {
	
	// SERVICE FRIEND -----------------------------------------------------------------------------------------------------------------------------
	
	// SERVICE ADD_FRIEND

	public static JSONObject addFriend(String key, String id_friend) {
		if((key == null) || (id_friend == null)) {
			return(ServicesTools.error("Wrong Arguments ",-1)); 
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
			
			int id_user = UserTools.getIdFromKey(key);
			
			boolean id_friend_ok = AuthentificationTools.idExists(id_friend);
			if(!id_friend_ok) {
				return(ServicesTools.error("Friend not found "+id_friend,-1)); 			
			}
			
			//Vérifier que les 2 ne sont pas encore amis
			boolean is_friend = FriendTools.is_friend(""+id_user,id_friend);
			if(is_friend) {
				return(ServicesTools.error("Users already friends "+id_friend,-1)); 			
			}
			

			JSONObject retour = new JSONObject();
			retour = FriendTools.addFriend(""+id_user,id_friend);
			return retour;
		}
		catch(JSONException e) { 
			return(ServicesTools.error("JSON Problem "+e.getMessage(),100)); 
		} 
		catch(SQLException e) {
			return(ServicesTools.error("SQL Problem "+e.getMessage(),1000)); 			
		}
		catch (Exception e) { 
			return(ServicesTools.error("Problem...",10000)); 
		}
	}
	
	// SERVICE DELETE_FRIEND

	public static JSONObject deleteFriend(String key, String id_friend) {
		if((key == null) || (id_friend == null)) {
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
			
			int id_user = UserTools.getIdFromKey(key);
			
			boolean id_friend_ok = AuthentificationTools.idExists(id_friend);
			if(!id_friend_ok) {
				return(ServicesTools.error("Friend not found "+id_friend,-1)); 			
			}
			
			// Verifier que la personne est dans la liste d'ami de l'utisisateur
			boolean is_friend = FriendTools.is_friend(""+id_user,id_friend);
			if(!is_friend) {
				return(ServicesTools.error("Users are not friends "+id_friend,-1)); 			
			}
			
			
			// supprimer l'ami dans la base de données
			JSONObject retour = new JSONObject();
			retour = FriendTools.deleteFriend(""+id_user,id_friend);
			return retour;
		}
		catch(JSONException e) { 
			return(ServicesTools.error("JSON Problem "+e.getMessage(),100)); 
		} 
		catch(SQLException e) {
			return(ServicesTools.error("SQL Problem "+e.getMessage(),1000)); 			
		}
		catch (Exception e) { 
			return(ServicesTools.error("Problem...",10000)); 
		}
	
}
	
	// SERVICE LIST_FRIEND

	public static JSONObject listFriends(String key, String id) {
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
			
			boolean is_user = AuthentificationTools.idExists(id); 
			if (!is_user) {
				return(ServicesTools.error("Unknown user "+id,-1)); 
			}	
			
			JSONObject retour = new JSONObject();
			retour = FriendTools.listFriend(id);
			return retour;
		}
		catch(JSONException e) { 
			return(ServicesTools.error("JSON Problem "+e.getMessage(),100)); 
		} 
		catch(SQLException e) {
			return(ServicesTools.error("SQL Problem "+e.getMessage(),1000)); 			
		}
		catch (Exception e) { 
			return(ServicesTools.error("Problem...",10000)); 
		}
	}

	public static JSONObject testFriends(String key, String id, String id_friend) {
		if((key == null) || (id == null) || (id_friend == null)) {
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
			
			boolean id_user_ok = AuthentificationTools.idExists(id);
			if(!id_user_ok) {
				return(ServicesTools.error("User not found "+id,-1)); 			
			}
	
			boolean id_friend_ok = AuthentificationTools.idExists(id_friend);
			if(!id_friend_ok) {
				return(ServicesTools.error("Friend not found "+id_friend,-1)); 			
			}
			
			// Verifier que la personne est dans la liste d'ami de l'utisisateur
			boolean is_friend = FriendTools.is_friend(id,id_friend);
			JSONObject res = new JSONObject();
			if(is_friend) {
				res.append("res", "true");
			}
			else{
				res.append("res", "false");				
			}
			res.append("status", "");
			res.append("Erreur", "");
			return res;
		}
		catch(JSONException e) { 
			return(ServicesTools.error("JSON Problem "+e.getMessage(),100)); 
		} 
		catch(SQLException e) {
			return(ServicesTools.error("SQL Problem "+e.getMessage(),1000)); 			
		}
		catch (Exception e) { 
			return(ServicesTools.error("Problem...",10000)); 
		}
	}
}