package test;

import java.sql.SQLException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import tools.FriendTools;

public class TestFriendTools {
	public static void main( String [] args){
		try {
			System.out.println("Tests pour les méthodes de FriendTools : ");

			/*-------------------------------------------------------------------------------------------*/
			
			System.out.println("Test de addFriend : ");
			
			JSONObject x = new JSONObject(); // vide
			
			if(FriendTools.addFriend("1", "8").toString().equals(x.toString())){
				System.out.println("Succès du test");
			}
			else{
				System.out.println("Echec du test");
			}
			if(FriendTools.addFriend("1", "2").toString().equals(x.toString())){
				System.out.println("Succès du test");
			}
			else{
				System.out.println("Echec du test");
			}		
			
			/*-------------------------------------------------------------------------------------------*/
			
			System.out.println("Test de is_friend : ");
	
			if(FriendTools.is_friend("1", "2")){
				System.out.println("Succès du test");
			}
			else{
				System.out.println("Echec du test");
			}
	
			if(FriendTools.is_friend("2", "1")){
				System.out.println("Echec du test");			
			}
			else{
				System.out.println("Succès du test");
			}
			
			/*-------------------------------------------------------------------------------------------*/
	
	
			System.out.println("Test de deleteFriend : ");
	
			if(FriendTools.deleteFriend("1", "2").toString().equals(x.toString())){
				System.out.println("Succès du test");
			}
			else{
				System.out.println("Echec du test");
			}
	
			if(FriendTools.deleteFriend("1", "8").toString().equals(x.toString())){
				System.out.println("Succès du test");			
			}
			else{
				System.out.println("Echec du test");
			}
			if(FriendTools.is_friend("1", "2")){
				System.out.println("Echec du test");
			}
			else{
				System.out.println("Succès du test");
			}
			
	
			/*-------------------------------------------------------------------------------------------*/
			
			System.out.println("Test de listFriend : ");
			
			FriendTools.addFriend("6", "8");
			FriendTools.addFriend("6", "9");
			FriendTools.addFriend("6", "10");
			
			JSONObject res = FriendTools.listFriend("6");
			JSONArray l = res.getJSONArray("amis");
			System.out.println("Valeur attendue : {\"id_user\":8,\"nom\":\"Tydelg\",\"prenom\":\"Bye\"}, valeur obtenue : "+l.get(0));
			System.out.println("Valeur attendue : {\"id_user\":9,\"nom\":\"Sarko\",\"prenom\":\"Ump\"}, valeur obtenue : "+l.get(1));			
			System.out.println("Valeur attendue : {\"id_user\":10,\"nom\":\"Holand\",\"prenom\":\"KL\"}, valeur obtenue : "+l.get(2));
			
			FriendTools.deleteFriend("6", "8");
			FriendTools.deleteFriend("6", "9");
			FriendTools.deleteFriend("6", "10");
			
		}catch (InstantiationException e) {
			e.printStackTrace();
		}catch (IllegalAccessException e2) {
			e2.printStackTrace();
		}catch (ClassNotFoundException e3) {
			e3.printStackTrace();
		}catch (SQLException e4) {
			e4.printStackTrace();
		}
		catch(JSONException e5) {
			e5.printStackTrace();			
		}
	}
}