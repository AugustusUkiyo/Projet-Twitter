package test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.json.JSONException;
import org.json.JSONObject;

import tools.MessageTools;

public class TestMessageTools {
	
	public static void main( String [] args) throws JSONException{
		
		System.out.println("Tests pour les méthodes de MessageTools : ");
		
		/*-------------------------------------------------------------------------------------------*/
		
		System.out.println("Test de insertMessage : ");
		
		GregorianCalendar calendar = new java.util.GregorianCalendar();
		Date d = calendar.getTime();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		String dateD = dateFormat.format(d);
		
		MessageTools.insertMessage("10","Ali",dateD,"TEST_INSERT_MESSAGE toto");
		MessageTools.insertMessage("9","Jean",dateD,"TEST_INSERT_MESSAGE toto2");
		
		// verifier dans MongoDB
		// db.comments.find()
		// db.comments.find({author_id:'10'})
		// db.comments.find({author_id:'9'})

		/*-------------------------------------------------------------------------------------------*/
		
		System.out.println("Test de supprMessage : ");
		
		MessageTools.supprMessage("12" , dateD); //si pb remplacer la date par la chaine de carac de l'époque où on a insert
		
		// verifier dans MongoDB
		// db.comments.find({author_id:'9'})

		/*-------------------------------------------------------------------------------------------*/

		System.out.println("Test de existsMessage : ");
		
		if(MessageTools.existsMessage("10", dateD)){
			System.out.println("Succès du test");
		}
		else{
			System.out.println("Echec du test");
		}

		MessageTools.supprMessage("10", dateD);
		
		if(MessageTools.existsMessage("10", dateD)){
			System.out.println("Echec du test");			
		}
		else{
			System.out.println("Succès du test");
		}
		
		System.out.println("Test de ListAllMessage : ");
		JSONObject list = new JSONObject();
		list = MessageTools.listAllMessage();
		System.out.println(list.toString());
	}

}
