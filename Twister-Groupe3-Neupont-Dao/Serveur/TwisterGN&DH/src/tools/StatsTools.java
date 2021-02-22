package tools;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bson.Document;



public class StatsTools {

	public static int nbUsers() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection con = Database.getMySQLConnection();
		String req = "select * from Users;";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(req);	
		
		int nb_users = 0;
		
		while(rs.next()) {
			nb_users++;
		}
			
		rs.close();
		st.close();
		con.close();
		
		return nb_users;		
	}

	public static int nbMsg() {
		MongoClient mongo = MongoClients.create();
		MongoDatabase db = mongo.getDatabase("Hiep-Guillaume-MDB");
		MongoCollection<Document> mc = db.getCollection("comments");

		MongoCursor<Document> cursor = mc.find().iterator();
		
		int nb_msg = 0;
		while(cursor.hasNext()) {
			nb_msg++;
			cursor.next();
		}
		return nb_msg;
	}

	public static int nbFriends(String id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection con = Database.getMySQLConnection();
		String req = "select * from Friends where id_user='"+id+"';";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(req);	
		
		int nb_friends = 0;
		
		while(rs.next()) {
			nb_friends++;
		}
			
		rs.close();
		st.close();
		con.close();
		
		return nb_friends;	
	}

	public static int nbMsgPerso(String id) {
		MongoClient mongo = MongoClients.create();
		MongoDatabase db = mongo.getDatabase("Hiep-Guillaume-MDB");
		MongoCollection<Document> mc = db.getCollection("comments");
		
		Document d = new Document();
		d.append("author_id",id);
		
		MongoCursor<Document> cursor = mc.find(d).iterator();
		
		int nb_msg = 0;
		while(cursor.hasNext()) {
			nb_msg++;
			cursor.next();
		}
		return nb_msg;
	}

}
