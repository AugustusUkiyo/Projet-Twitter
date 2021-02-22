package tools;

import org.json.JSONException;
import org.json.JSONObject;

public class ServicesTools {
	
	public static JSONObject error(String msg, int num) {
		JSONObject x = new JSONObject();
		try {
			x.put("Erreur", msg);
			x.put("status","Error");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return x;
	}
	
}
