package test;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

import tools.AuthentificationTools;

public class TestAuthentificationTools {
	public static void main( String [] args){
		int cpt = 0;
		try {
			System.out.println("Tests pour les méthodes de UserTools : ");
	
			/*-------------------------------------------------------------------------------------------*/
	
			System.out.println("Test de userExists : ");
	
			if(AuthentificationTools.userExists("abc")){
				System.out.println("Succès du test");
				cpt++;
			}
			else{
				System.out.println("Echec du test");
			}
	
			if(AuthentificationTools.userExists("loginnonexistant")){
				System.out.println("Echec du test");			
			}
			else{
				System.out.println("Succès du test");
				cpt++;
			}
	
	
			/*-------------------------------------------------------------------------------------------*/
	
			System.out.println("Test de idExists : ");
	
			if(AuthentificationTools.idExists("10")){
				System.out.println("Succès du test");
				cpt++;
			}
			else{
				System.out.println("Echec du test");
			}
	
			if(AuthentificationTools.idExists("1000")){
				System.out.println("Echec du test");			
			}
			else{
				System.out.println("Succès du test");
				cpt++;
			}
	
			/*-------------------------------------------------------------------------------------------*/
	
			System.out.println("Test de getIdUser : ");
	
			int i = AuthentificationTools.getIdUser("abc");

			if( i == 1 ){
				System.out.println("Succès du test");
				cpt++;
			}
			else{
				System.out.println("Echec du test");
			}
	
			/*-------------------------------------------------------------------------------------------*/
	
			System.out.println("Test de checkPassword : ");
	
			if(AuthentificationTools.checkPassword("abc","abc123")){
				System.out.println("Succès du test");
				cpt++;
			}
			else{
				System.out.println("Echec du test");
			}
	
			if(AuthentificationTools.checkPassword("abc","abc1234")){
				System.out.println("Echec du test");			
			}
			else{
				System.out.println("Succès du test");
				cpt++;
			}
	
			if(AuthentificationTools.checkPassword("abcd","nopassword")){
				System.out.println("Echec du test");			
			}
			else{
				System.out.println("Succès du test");
				cpt++;
			}
	
			/*-------------------------------------------------------------------------------------------*/
	
			System.out.println("Test de insertSession : ");
	
			JSONObject o2 = AuthentificationTools.insertSession(1,false,"abc");
	
			System.out.println("Valeur attendue : entre 0 et 1000000, valeur obtenue : "+o2.getString("key"));
	
			/*-------------------------------------------------------------------------------------------*/
	
			System.out.println("Test de insertKeySession : ");
	
			GregorianCalendar calendar = new java.util.GregorianCalendar();
			Date dateD = new Date();
			calendar.add(Calendar.YEAR, 10);
			Date dateF = calendar.getTime();
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
			String dateD2 = dateFormat.format(dateD);
			String dateF2 = dateFormat.format(dateF);
			
			String key1;
			Random r = new Random();
			int nb;
			
			do {
				nb = r.nextInt(1000000);
			}while(AuthentificationTools.keyExists(""+nb));
			
			key1 = ""+nb;
	
			JSONObject o = AuthentificationTools.insertKeySession(1, key1, dateD2, dateF2, "abc");
	
			System.out.println("Valeur attendue : "+key1+", valeur obtenue : "+o.getString("key"));
	
			// Utile pour les prochains tests :
	
			Date dateDeb = new Date();
			calendar.add(Calendar.YEAR, -15); //en l'occurence on a ajouté 10 avant donc on sera à -5 par rapport à la date actuelle
			Date dateFin = calendar.getTime();
			
			DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
			String dateDeb2 = dateFormat2.format(dateDeb);
			String dateFin2 = dateFormat2.format(dateFin);
	
			String key2;
			Random ra = new Random();
			int nb2;
			
			do {
				nb2 = ra.nextInt(1000000);
			}while(AuthentificationTools.keyExists(""+nb2));
			
			key2 = ""+nb2;
	
			AuthentificationTools.insertKeySession(1, key2, dateDeb2, dateFin2, "abc"); 
	
			/*-------------------------------------------------------------------------------------------*/
	
			System.out.println("Test de keyExists : ");
	
			if(AuthentificationTools.keyExists(key2)){
				System.out.println("Succès du test");
				cpt++;
			}
			else{
				System.out.println("Echec du test");
			}
	
			/*-------------------------------------------------------------------------------------------*/
	
			System.out.println("Test de keyCorrect : ");	
			
			if(AuthentificationTools.keyCorrect(key2)){
				System.out.println("Echec du test");
			}
			else{
				System.out.println("Succès du test");
				cpt++;
			}	
	
			if(AuthentificationTools.keyCorrect(key1)){ //avoir une clé key1 dans la BD qui est correcte (dateFin très loin)
				System.out.println("Succès du test");
				cpt++;
			}
			else{
				System.out.println("Echec du test");
			}	
	
			if(AuthentificationTools.keyCorrect(o2.getString("key"))){  // Si échec -> potentiel pb avec la dateFin depuis insertSession
				System.out.println("Succès du test");
				cpt++;
			}
			else{
				System.out.println("Echec du test");
			}	
	
			/*-------------------------------------------------------------------------------------------*/
	
			System.out.println("Test de closeSession : ");
	
			if(AuthentificationTools.keyCorrect(o2.getString("key"))){
				System.out.println("[AVANT] Succès du test");
				cpt++;
			}
			else{
				System.out.println("[APRES] Echec du test");			
			}
	
			AuthentificationTools.closeSession(o2.getString("key")); // Si échec -> potentiel pb depuis insertSession
	
			if(AuthentificationTools.keyCorrect(o2.getString("key"))){
				System.out.println("[APRES] Echec du test");
			}
			else{
				System.out.println("[APRES] Succès du test");
				cpt++;
			}
			
			if(cpt == 14) {
				System.out.println("TOUS LES TESTS BOOLEENS SONT UN SUCCES");
			}
			else {
				System.out.println("AU MOINS UN TEST BOOLEEN EST UN ECHEC");				
			}
			
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
		catch(ParseException e6) {
			e6.printStackTrace();
		}
	}
}