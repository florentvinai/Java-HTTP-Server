package iut.rt.hachettp;
/*
 * Le constructeur de la  classe genere le contenu de la reponse 
 * lorsque 
 * 
 * il positionne les donnee membre de la reponse ; code, message_std
 * 
 * 1- construit le contenu de la reponse dans contenu_string
 * 2- convertit le Strig en Byte pour la donneee membre
 *
 * 
 */
public class Reponse505 extends Reponse{
	
	public Reponse505( String s) {
		String contenu_string;
		
		//Trace
		System.out.println("constructeur Reponse 505 : " + s);
		System.out.flush();
		
		
		code = 505;
		message_std= "HTTP VERSION NOT SUPPORTED" ;
		
		
		contenu_string = "<!DOCTYPE HTML PUBLIC \"-//IETF//DTD HTML 2.0//EN\">\n" +
			    "<html><head>\n" + 
				"<title>" + code +" "+ message_std + "</title\n" +
				"<html><head>\n" + "<h1> BAD REQUEST </h1>\n"+ 
				"<p>The requested URL "+ s +" was not found on this server.</p>\n"+ 
				"<hr>\n"+
				"<address>HacheTTP 0.1 Server: dev. by  F.Vinai  IUT RT 1annee  S2  2018/2019</address>\n" +
				"</body></html>\n";
		//conversion String to Bytes
		contenu = contenu_string.getBytes();
		
		//Assignation du contenu dans la donneee membre
		long_contenu = contenu_string.length();
		
	}
}
