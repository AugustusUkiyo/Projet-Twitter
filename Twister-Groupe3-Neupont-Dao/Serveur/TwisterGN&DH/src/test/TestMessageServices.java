package test;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

import services.Message;
import services.User;
import tools.MessageTools;
import tools.UserTools;

public class TestMessageServices {
	
	public static void main( String [] args){
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println("Test de addMessage : ");
			
		GregorianCalendar calendar = new java.util.GregorianCalendar();
			Date d = calendar.getTime();
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
			String dateD = dateFormat.format(d);
			
			User.createUser("NOM_TEST_MESSAGE", "PRENOM_TEST_MESSAGE", "LOGIN_TEST_MESSAGE", "PASSWORD_TEST_MESSAGE","PASSWORD_TEST_MESSAGE");
			
			JSONObject o = User.login("LOGIN_TEST_MESSAGE", "PASSWORD_TEST_MESSAGE");
			String key = o.getString("key");
			int i = UserTools.getIdFromKey(key);
			String id = ""+i;
			
			if(MessageTools.existsMessage(id, dateD)) {
				System.out.println("Echec du test");
			}
			else{
				System.out.println("Succès du test");
			}
			
			Message.addMessage(key,"TEST_ADD_MESSAGE_SERVICE");
			
			@SuppressWarnings("unused")
			String line = scanner.nextLine(); 
			
			// verifier dans MongoDB
			// db.comments.find()
			// db.comments.find({author_id:id})
			
			/*-------------------------------------------------------------------------------------------*/
			
			System.out.println("Test de listMessage : ");			
			
			JSONObject o2 = new JSONObject();
			
			int ida = UserTools.getIdFromKey(key);
			String idas = ""+ida;
			
			o2 = Message.listMessage(key,idas);
			
			System.out.println(o2.toString());
			
			line = scanner.nextLine();
	

			/*-------------------------------------------------------------------------------------------*/
			
			System.out.println("Test de deleteMessage : ");
			
			//utiliser la date de addMessage
			Message.deleteMessage(key , dateD/*DATE DE ADD_MESSAGE*/); 
			
			// verifier dans MongoDB
			// db.comments.find({author_id:id})
			
			
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