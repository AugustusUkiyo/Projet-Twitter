package test;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

import services.User;
import tools.AuthentificationTools;

public class TestUserServices {
	public static void main( String [] args) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		try {
			
			System.out.println("Test de createUser : ");
			
			User.createUser("TestNomServiceUser", "TestPrenomServiceUser", "TestLoginServiceUser", "TestPasswordServiceUser","TestPasswordServiceUser");
			
			if(AuthentificationTools.userExists("TestLoginServiceUser")) {
				System.out.println("Succès du test");
			}
			else {
				System.out.println("Echec du test");
			}
			
			@SuppressWarnings("unused")
			String line = scanner.nextLine();
			
			/*-------------------------------------------------------------------------------------------*/
			
			System.out.println("Test de login : ");
			
			JSONObject o = User.login("TestLoginServiceUser", "TestPasswordServiceUser");

			String key = o.getString("key");
			System.out.println("La clé de connection obtenue est : "+key);
			
			if(AuthentificationTools.keyExists(key)) {
				System.out.println("Succès du test");				
			}
			else {
				System.out.println("Echec du test");				
			}

			line = scanner.nextLine();
			
			/*-------------------------------------------------------------------------------------------*/
			
			System.out.println("Test de logout : ");
			
			User.logout(key);
			if(AuthentificationTools.userExists("TestLoginServiceUser")) {
				System.out.println("Succès du test");
			}
			else {
				System.out.println("Echec du test");
			}			
			
			if(AuthentificationTools.keyCorrect(key)) {
				System.out.println("Echec du test");				
			}
			else {
				System.out.println("Succès du test");				
			}
			
		}
		catch(JSONException e) {
			e.printStackTrace();
		}
		catch(SQLException e2) {
			e2.printStackTrace();
		}
		catch(ClassNotFoundException e3) {
			e3.printStackTrace();
		}
		catch(IllegalAccessException e4) {
			e4.printStackTrace();
		}
		catch(InstantiationException e5) {
			e5.printStackTrace();
		}
		catch(ParseException e6) {
			e6.printStackTrace();
		}
	}
}