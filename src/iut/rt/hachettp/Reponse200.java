package iut.rt.hachettp;
import java.io.*;


/*
 * Le constructeur de la  classe genere le contenu de la reponse OK
 * lorsque le fichierest OK et existe
 * params  File f du fichier
 */

public class Reponse200 extends Reponse {
	

	public Reponse200(File f) throws HTTPException   {
		
		System.out.println("8- REPONSE : constructeur Reponse OK 200");
		System.out.flush();
		
		FileInputStream fileIn = null;
		
		//code et msg std de la reponse OK
		code = 200;
		message_std= "OK" ;
		
		// assignation de la longueur du contenu dans la donnee membre
		long_contenu = (int) f.length();
		
		//creation tu tableau de Byte 
		byte[] fileData = new byte[long_contenu];
		
		// gestion erreur de lecteure 
		try {
			fileIn = new FileInputStream(f);
			fileIn.read(fileData);
			
		} catch (IOException e) {
 
			  code = 500 ;
			  message_std = " INTERNAL SERVER ERROR";
			  
			  new HTTPError( code, message_std);
			  
		      throw new HTTPException("Erreur lors de la lectur e fichier ");
		     
		      
		      }	
		
		if (fileIn != null) 
			try {
				fileIn.close();} catch (IOException e) {

			      throw new HTTPException("Erreur fermeture fichier  ");
			      }	
		
		//affectation de la donnee membre 
		//par le resultat de la lecture du fichier
        contenu = fileData;

	}

}

