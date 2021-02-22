package services;

public class Operation {
	
	public static double calcul(String a , String b , String choix) {
		try {
			if(choix.equals("plus")) {
				return somme(a , b);
			}
			if(choix.equals("moins")) {
				return soustraction(a , b);
			}
			if(choix.equals("fois")) {
				return fois(a , b);
			}
			if(choix.equals("divise")) {
				return division(a , b);
			}
		}
		catch(Exception e) {
			return 0;
		}
		return 0;
	}
	
	private static double somme(String a , String b) {
		return Double.parseDouble(a) + Double.parseDouble(b);
	}
	
	private static double soustraction(String a , String b) {
		return Double.parseDouble(a) - Double.parseDouble(b);
	}
	
	private static double fois(String a , String b) {
		return Double.parseDouble(a) * Double.parseDouble(b);
	}
	
	private static double division(String a , String b) {
		return Double.parseDouble(a) / Double.parseDouble(b);
	}
}
