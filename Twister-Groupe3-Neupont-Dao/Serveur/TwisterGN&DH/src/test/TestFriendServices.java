package test;

import java.sql.SQLException;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import services.Friend;
import services.User;
import tools.FriendTools;
import tools.UserTools;

public class TestFriendServices {
	public static void main( String [] args){
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		try {
			
			System.out.println("Test de addFriend : ");
			
			JSONObject x = new JSONObject(); // vide
			
			User.createUser("NOM_TEST_FRIEND", "PRENOM_TEST_FRIEND", "LOGIN_TEST_FRIEND", "PASSWORD_TEST_FRIEND","PASSWORD_TEST_FRIEND");
			
			JSONObject o = User.login("LOGIN_TEST_FRIEND", "PASSWORD_TEST_FRIEND");
			String key = o.getString("key");
			int i = UserTools.getIdFromKey(key);
			String id = ""+i;
			
			if(Friend.addFriend(key, "8").toString().equals(x.toString())){
				System.out.println("Succès du test");
			}
			else{
				System.out.println("Echec du test");
			}
			if(Friend.addFriend(key, "2").toString().equals(x.toString())){
				System.out.println("Succès du test");
			}
			else{
				System.out.println("Echec du test");
			}		
			
			if(FriendTools.is_friend(id, "8")){
				System.out.println("Succès du test");
			}
			else{
				System.out.println("Echec du test");
			}
			if(FriendTools.is_friend(id, "2")){
				System.out.println("Succès du test");
			}
			else{
				System.out.println("Echec du test");
			}
			
			@SuppressWarnings("unused")
			String line = scanner.nextLine();
			
			/*-------------------------------------------------------------------------------------------*/
	
	
			System.out.println("Test de deleteFriend : ");
	
			if(Friend.deleteFriend(key, "2").toString().equals(x.toString())){
				System.out.println("Succès du test");
			}
			else{
				System.out.println("Echec du test");
			}
	
			if(Friend.deleteFriend(key, "8").toString().equals(x.toString())){
				System.out.println("Succès du test");			
			}
			else{
				System.out.println("Echec du test");
			}
			if(FriendTools.is_friend(id, "2")){
				System.out.println("Echec du test");
			}
			else{
				System.out.println("Succès du test");
			}
			if(FriendTools.is_friend(id, "8")){
				System.out.println("Echec du test");
			}
			else{
				System.out.println("Succès du test");
			}

			line = scanner.nextLine();
			
			/*-------------------------------------------------------------------------------------------*/
			
			System.out.println("Test de listFriends : ");
			
			Friend.addFriend(key, "8");
			Friend.addFriend(key, "9");
			Friend.addFriend(key, "10");
			
			JSONObject res = Friend.listFriends(key,id);
			JSONArray l = res.getJSONArray("amis");
			System.out.println("Valeur attendue : {\"id_user\":8,\"nom\":\"Tydelg\",\"prenom\":\"Bye\"}, valeur obtenue : "+l.get(0));
			System.out.println("Valeur attendue : {\"id_user\":9,\"nom\":\"Sarko\",\"prenom\":\"Ump\"}, valeur obtenue : "+l.get(1));			
			System.out.println("Valeur attendue : {\"id_user\":10,\"nom\":\"Holand\",\"prenom\":\"KL\"}, valeur obtenue : "+l.get(2));
			
			Friend.deleteFriend(key, "8");
			Friend.deleteFriend(key, "9");
			Friend.deleteFriend(key, "10");
			
			User.logout(key);
			
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