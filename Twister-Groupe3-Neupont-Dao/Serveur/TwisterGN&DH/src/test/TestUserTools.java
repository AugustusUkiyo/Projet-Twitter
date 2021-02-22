package test;

import java.sql.SQLException;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

import tools.AuthentificationTools;
import tools.UserTools;

public class TestUserTools {
	public static void main( String [] args){
		try {
			System.out.println("Tests pour les méthodes de UserTools : ");
	
			/*-------------------------------------------------------------------------------------------*/
	
			System.out.println("Test de insertUser : ");	
			
			Random r = new Random();
			int nb;
			nb = r.nextInt(1000000);
			
			String nom = "Dupond";
			String prenom = "Jean";
			String login = "logintest"+nb;
			String password = "passwordtest";
	
			UserTools.insertUser(nom,prenom,login,password);
	
			if(AuthentificationTools.userExists(login)){
				System.out.println("L'utilisateur a bien été créé");
			}
			else{
				System.out.println("Echec : L'utilisateur n'a pas été créé");			
			}
	
			String nomtest = UserTools.getNameFromId("11");
			System.out.println("Valeur attendue : Dupond, Valeur obtenue : "+nomtest);
			
			/*-------------------------------------------------------------------------------------------*/
	
			System.out.println("Test de getIdFromKey : ");	
	
			JSONObject t = AuthentificationTools.insertSession(1,false, "abc");
	
			String key = t.getString("key");
	
			int idtest = UserTools.getIdFromKey(key);
			if(idtest == 1){
				System.out.println("Succès du test");
			}
			else{
				System.out.println("Echec du test");
			}
	
			/*-------------------------------------------------------------------------------------------*/
	
			System.out.println("Test de getNameFromId : ");
	
			String nametest = UserTools.getNameFromId("11");
			System.out.println("Valeur attendue : Dupond, Valeur obtenue : "+nametest);
	
			/*-------------------------------------------------------------------------------------------*/
	
			System.out.println("Test de afficheUser : ");		
	
			JSONObject o = UserTools.afficheUser("11");
			System.out.println("Valeur attendue : Jean, Valeur obtenue : "+o.getString("prenom"));
			System.out.println("Valeur attendue : Dupond, Valeur obtenue : "+o.getString("nom"));
			System.out.println("Valeur attendue : 11, Valeur obtenue : "+o.getString("id_user"));
		}
		catch(JSONException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e2) {
			e2.printStackTrace();
		}
		catch(IllegalAccessException e3) {
			e3.printStackTrace();
		}
		catch(InstantiationException e4) {
			e4.printStackTrace();
		}
		catch(SQLException e5) {
			e5.printStackTrace();
		}
	}
}
