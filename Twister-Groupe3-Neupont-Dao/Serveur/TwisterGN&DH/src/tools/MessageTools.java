package tools;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MessageTools {

	//INSERT_MESSAGE : AJOUTE UN MESSAGE A LA MDB COMMENTS
	public static JSONObject insertMessage(String author_id, String name, String date, String text) throws JSONException {
		MongoClient mongo = MongoClients.create();
		MongoDatabase db = mongo.getDatabase("Hiep-Guillaume-MDB");
		MongoCollection<Document> mc = db.getCollection("comments");
		
		Document d = new Document();
		d.append("author_id",author_id);
		d.append("author_name",name);
		d.append("date",date);
		d.append("texte",text);
		
		mc.insertOne(d);
		
		JSONObject res = new JSONObject();
		
		res.put("status", "");
		return res;
	}

	//SUPPR_MESSAGE : SUPPRIME UN MESSAGE DE LA MDB COMMENTS (UN MESSAGE EST RECONNU PAR L'ID DE LA PERSONNE L'AYANT ECRIT ET SA DATE A LA SECONDE PRES)
	public static JSONObject supprMessage(String author_id, String date) throws JSONException {
		MongoClient mongo = MongoClients.create();
		MongoDatabase db = mongo.getDatabase("Hiep-Guillaume-MDB");
		MongoCollection<Document> mc = db.getCollection("comments");
		
		Document d = new Document();
		d.append("author_id",author_id);
		d.append("date",date);
		
		mc.deleteOne(d);
		
		JSONObject res = new JSONObject();
		res.put("status", "");
		return res;		
	}

	//EXISTS_MESSAGE : RENVOIE TRUE SI LE MESSAGE DE AUTHOR_ID A LA DATE DATE EXISTE DEJA DANS LA MDB COMMENTS
	public static boolean existsMessage(String author_id, String date) {
		boolean res;
		MongoClient mongo = MongoClients.create();
		MongoDatabase db = mongo.getDatabase("Hiep-Guillaume-MDB");
		MongoCollection<Document> mc = db.getCollection("comments");
		
		Document d = new Document();
		d.append("author_id",author_id);
		d.append("date",date);
		
		MongoCursor<Document> cursor = mc.find(d).iterator();
		
		if(cursor.hasNext()) {
			res = true;
		}
		else {
			res = false;
		}
		return res;
	}

	//LIST_MESSAGE : RENVOIE LA LISTE DES MESSAGES DE LA PERSONNE CORRESPONDANT A AUTHOR_ID
	public static JSONObject listMessage(String author_id) throws JSONException {
		MongoClient mongo = MongoClients.create();
		MongoDatabase db = mongo.getDatabase("Hiep-Guillaume-MDB");
		MongoCollection<Document> mc = db.getCollection("comments");
		
		Document d = new Document();
		d.append("author_id",author_id);
		Document d2 = new Document();
		d2.append("date", -1);
		
		MongoCursor<Document> cursor = mc.find(d).sort(d2).iterator();
		
		List<JSONObject> l = new ArrayList<JSONObject>();		
		Document res = new Document();
		JSONObject o = new JSONObject();
		JSONObject tmp = new JSONObject();
		
		while(cursor.hasNext()) {
			res = cursor.next();
			tmp.append("texte", res.getString("texte"));
			tmp.append("id", res.getString("author_id"));
			tmp.append("nom", res.getString("author_name"));
			tmp.append("date", res.getString("date"));
			l.add(tmp);
			tmp = new JSONObject();	
		}
		o.append("liste_messages",l);
		o.put("status", "");
		return o;
	}
	
	//LIST_ALL_MESSAGE : RENVOIE LA LISTE DES MESSAGES
	public static JSONObject listAllMessage() throws JSONException {
		MongoClient mongo = MongoClients.create();
		MongoDatabase db = mongo.getDatabase("Hiep-Guillaume-MDB");
		MongoCollection<Document> mc = db.getCollection("comments");
		
		Document d = new Document();
		d.append("date", -1);
		MongoCursor<Document> cursor = mc.find().sort(d).iterator();
		
		List<JSONObject> l = new ArrayList<JSONObject>();		
		JSONObject tmp = new JSONObject();
		Document res = new Document();
		JSONObject o = new JSONObject();
		
		while(cursor.hasNext()) {
			res = cursor.next();
			tmp.append("texte", res.getString("texte"));
			tmp.append("id", res.getString("author_id"));
			tmp.append("nom", res.getString("author_name"));
			tmp.append("date", res.getString("date"));
			l.add(tmp);
			tmp = new JSONObject();			
		}
		o.append("liste_messages",l);
		o.put("status", "");
		return o;
	}

}
