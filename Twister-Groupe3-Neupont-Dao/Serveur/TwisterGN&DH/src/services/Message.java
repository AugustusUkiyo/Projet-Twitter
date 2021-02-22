package services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.json.JSONException;
import org.json.JSONObject;

import tools.AuthentificationTools;
import tools.MessageTools;
import tools.ServicesTools;
import tools.UserTools;

public class Message {

	//SERVICE MESSAGE -----------------------------------------------------------------------------
	
	//SERVICE ADD_MESSAGE
	
	public static JSONObject addMessage(String key, String text) {
		if((key == null) || (text == null)) {
			return(ServicesTools.error("Wrong Arguments",-1)); 			
		}

		try {
			
			boolean exists_key = AuthentificationTools.keyExists(key);
			if(!exists_key) {
				return(ServicesTools.error("Key doesn't exists",-1));				
			}
			
			boolean correct_key = AuthentificationTools.keyCorrect(key);
			if(!correct_key) {
				return(ServicesTools.error("Wrong key",-1));				
			}
			
			if(text.length() > 300) {
				return(ServicesTools.error("Too much letters",-1));					
			}
			
			int user_id = UserTools.getIdFromKey(key);
			String author_id = ""+user_id;
			String name = UserTools.getNameFromId(author_id);
			
			GregorianCalendar calendar = new java.util.GregorianCalendar();
			Date d = calendar.getTime();
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
			String date = dateFormat.format(d);
			
			JSONObject retour = new JSONObject();
			retour = MessageTools.insertMessage(author_id,name,date,text);
			return retour;	
		}
		catch (JSONException e) { 
			return (ServicesTools.error("JSON Problem "+e.getMessage(),100)); 
		}
		catch (Exception e) { 
			return(ServicesTools.error("Problem...",10000)); 
		}
	}
	
	//SERVICE DELETE_MESSAGE
	
	public static JSONObject deleteMessage(String key, String date) {
		if((key == null) || (date == null)) {
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
			String id_author = ""+id_user;
			
			boolean message_exists = MessageTools.existsMessage(id_author,date);
			if(!message_exists) {
				return(ServicesTools.error("Message doesn't exists",-1));				
			}
			

			JSONObject x = new JSONObject();
			x = MessageTools.supprMessage(id_author,date);
	
			return x;
		}
		catch (JSONException e) { 
			return (ServicesTools.error("JSON Problem "+e.getMessage(),100)); 
		}
		catch (Exception e) { 
			return(ServicesTools.error("Problem...",10000)); 
		}
		
	}
	
	//SERVICE LIST_MESSAGE
	
	public static JSONObject listMessage(String key, String id) {
		if(key == null || id == null) {
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

			JSONObject x = new JSONObject();
			x = MessageTools.listMessage(id);
	
			return x;
		}
		catch (JSONException e) { 
			return (ServicesTools.error("JSON Problem "+e.getMessage(),100)); 
		}
		catch (Exception e) { 
			return(ServicesTools.error("Problem...",10000)); 
		}
		
	}
	
	//SERVICE LIST_ALL_MESSAGE
	
	public static JSONObject listAllMessage(String key) {
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

			JSONObject x = new JSONObject();
			x = MessageTools.listAllMessage();
	
			return x;
		}
		catch (JSONException e) { 
			return (ServicesTools.error("JSON Problem "+e.getMessage(),100)); 
		}
		catch (Exception e) { 
			return(ServicesTools.error("Problem...",10000)); 
		}
		
	}
	
}
