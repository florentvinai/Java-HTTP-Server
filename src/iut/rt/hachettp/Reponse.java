package iut.rt.hachettp;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Reponse {
	
	int type_reponse = -1 ; // Positif si Requete OK, sinon negatif
	
	// Reponse associe un code avec un ntexte std
	int code ;
	String message_std;
	
	byte [] contenu; // contenu
	
	int long_contenu;
	
	
	/*
	 * une m�thode String toString() qui renvoie toute la partie textuelle (non-Javadoc)
	 * @see java.lang.Object#toString()
	 *de la r�ponse (c'est � dire la premi�re ligne et les champs d'ent�te).
	 *
	 **/
	public String toString() {
		
	  SimpleDateFormat formater = null;
	  String entete ="", date_formate;
	 
	  
	  // format demande TP
	  String format="EEE, dd MMM yyyy HH:mm:ss zzz";  
	  String content = "text/html; charset=UTF-8";
	
	  // creation du format de la date voulu dans le TP
	  formater = new SimpleDateFormat(format);
      Date date = new Date();
      
      // appel a la methode String format(Date) suggere ds le TP
	  date_formate = formater.format(date);

     // Creationn de l'Entete HTTP reponse
   
	 entete = "HTTP/1.1 " + code +" "+ message_std + "\n" +
     "Server: HachTTP\n" + 
	 "Date: " + date_formate + "\n" + 
     "Content-type: " + content + "\n"+
	 "Content-length: " + long_contenu +"\n";
	
	  // renvoi l'entete de la reponse en String
     return (entete);
	}
	
	
	
	//une m�thode byte [] getContenu() qui renvoie le contenu de la r�ponse
	byte [] getContenu() {	
		
	// renvoi le contenu	
    return (contenu);
		
	}

}
