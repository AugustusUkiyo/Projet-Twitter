package services;


import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import tools.AuthentificationTools;
import tools.ServicesTools;
import tools.SearchTools;

public class Search {
	
	// SERVICE SEARCH -----------------------------------------------------------------------------------------------------------------------------
	

	// SERVICE FIND

	public static JSONObject find(String key, String text) {
		if((key == null) || (text == null)){
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
			
			JSONObject retour = new JSONObject();
			retour = SearchTools.listFind(text);
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
}