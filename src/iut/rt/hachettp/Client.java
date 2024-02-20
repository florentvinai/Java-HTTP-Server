package iut.rt.hachettp;

// Bibliotheque Java pour gerer les Entree/Sortie (vu sur internet)

import java.io.*;
import java.net.*;

/*
* Classe Client : appele/creeer lorsque Serveur socket Accept ()
*
*	cet Objet Permet de gerer la communication en lecture/ecriture
*	et inclus le socket 
*	il cobtient aussi un objet Requete :
*	qui lui meme contient la methode, la reponse
*	
*/
public class Client {

	 // declaration des donnee membre
	 private Socket         le_socket;  // Reference le socket de comm
	 public  BufferedReader l_entree ;	// refrence pour lire la requete
	 private BufferedWriter la_sortie ; // refernce pour ecrire la reponse
	 public  Requete        la_requete; // stocke toute info de la requete
	 
	

	 /*
	 * Constructeur Classe Client incluant :
	 *
	 * @arg : le socket
	 * 
	 * initialisation des donnee membre 
	 * Ouverture flux a lire
	 * Ouverture flux a ecrire 
	 * 
	 * throws : levee/ catch & gestion des HTTPException
	 */
   
     public Client(Socket s) throws HTTPException {
    	 	     
		  le_socket = s ; // initialisation donnee membre
 
		  try {
			    l_entree = new BufferedReader(new InputStreamReader(le_socket.getInputStream()));
			   
		  } catch (IOException e) {

		      throw new HTTPException("Access en lecture au socket impossible ");
		    }
		  
		  try {
			  la_sortie= new BufferedWriter(new OutputStreamWriter(le_socket.getOutputStream()));
		    } catch (IOException e) {

		    	throw new HTTPException("Access en ecriture au socket impossible ");
		    }
	  }
 
     
    /* Methode Traiter public de la classe Client
     * 
     *  @arg : root_file, filename pour 
     *  localisation du fichier index.html a lire
     *  
     *  1- appel methode  lecture requete 
     *  2- appel determinemethode : quele est la methode ( seul get est implemente)  
     *  3- Traiter la requete : appel a la methode requete 
     *  4- construction et envoi de la reponse : appel a la methide envoyer-reponse
     *  5- liberation des resource de communication socket & filedescripteur : appel a liberation()
     * 
     *  Throw : gestion des HTTPexceptions, HTTP Error
     */  
     
     public void traiter(String root, String name) throws HTTPException, HTTPError {
 					
    	    System.out.println("3- CLIENT->traiter client : "); // Trace
    	   
    	    // gestion& remontee des exceptions de la methode
    	    // lecture-requete()
    	    try {
    	    	
 			// 1- Lecture du socket et stockage requete dans les 
    	    // donnee membre Client -> creation de lobjet requete
 			this.lecture_requete();
 			
    	        } catch (HTTPException e) {	
    	        	
    	        	// TODO Auto-generated catch block
    				e.printStackTrace();
    			    }  
    	    
    	    // gestion& remontee des exception de la methode
    	    // determine_methode
    	    try {
    	    			 
 			// 2- requete est prete -> quelle methode ?
 			//creation de lobjet methode
		    this.la_requete.determine_methode(); 
		    
		   
		    // Trace des donnee membre
	 	    // System.out.println("\nl_host  :\t" + this.la_requete.la_methode.getHote() + 
	 					//"\nl_agent :\t" + this.la_requete.la_methode.getAgent() + 
	 					//"\nl_url   :\t" + this.la_requete.la_methode.getURL()); 
	 			
					//System.out.println("\nStatus requete   :\t" + la_requete.status_requete );
			    } 
    	    
    	    catch (HTTPException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
    	    catch (HTTPError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		    }
		    
    	    // reůmontee des exceptions dela methode
    	    // traiter()
    	    try {
	 	      /*  3-  appel la  methode Traiter  de la requete 
	 		   *  Prepare et construit Objet  reponse en fonction des valeurs de :
	 		   *  this.la_requete.la_methode.getURL()
	 		   *  root et name
	 	       */
 			  this.la_requete.traiter(root, name);
            } 
    	    
    	    catch (HTTPException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
    	    
    	    // reůmontee des exceptions dela methode
    	    // envoyer_reponse()
    	    try {
 			  /*
 			   * 4- Envoi Objet reponse HTML
 			   */
 			  this.envoyer_reponse();
 			  
                } 
    	     catch (HTTPException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			 }
    	    
    	    // reůmontee des exceptions dela methode
    	    // liberation()
    	    try {
 			  /* 5- Libere toutes les resources de
 			  * communication
 			  */
 			  this.liberation();
    	    } 
   	         catch (HTTPException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			 }
 		    
 	 }


 	 /*
 	  * Methode Lecture_requete pour modeliser les infos de la socket dans le client
 	  * 
 	  * 1- on cree un objet requete /assignation donnee membre
 	  * 
 	  * 2- tant que la requete nest pas prete - onlit la ligne, 
 	  *    on ajoute la ligne dans la donnee membre
 	  * 3- fin lecture qd la requete est prete
 	  * 
 	  * @HTTPException
      */
 	 public  void lecture_requete() throws HTTPException  {
 		 
 		  int i = 0; 
		  String ligne = ".";
		  
		  Requete req = new Requete(); // creation de l'objet
		  la_requete = req; // affectation objet creer a la donnee membre
		  		  
		  System.out.println("4- CLIENT-> lecture_requete ..."); // trace
		  
	      // Boucle tant que la requete GET n'est pas prete
		  // la requete est prete qd status-requete    
		     
		  while (  la_requete.estPrete() == false ) {
			  
			   try {  
				   ligne= l_entree.readLine();
				    
			       } catch (IOException e) {

			      throw new HTTPException("Erreur lors de la lecture du socket ");
			      }	

		    	la_requete.ajouteLigne(ligne); // ajoute une ligne dans le vecteur requete.
		    	i = i + 1; // on incremente nombre de ligne
		    	la_requete.nombre_ligne = i ; // affectation du nombre de ligne de la requete dans la donnee membre
		    	
		        
		        // Trace execution
		        //System.out.println("lignes : " + la_requete.nombre_ligne + "***"+ ligne + "prete:" + la_requete.estPrete() +"  status" + la_requete.status_requete); 
	
		    	
		     
		    }
		   
		 
		    System.out.println("5- CLIENT-> fin lecture_requete");
		    System.out.flush();

	  }

	  

	  /*
	   * methode envoyerreponse
	   * 
	   * cette methode  :
	   * 1- recupere l'entete de la reponse
	   * 2- recupere le contenu de la reponse
	   * 
	   * Ecrit la reponse sur le socket d'ecriture la reponse=entete+contenu
	   * 
	   */

	  private void envoyer_reponse() throws HTTPException  {
	    String entete;
	    String contenu;
	 
	    // recupere l'entete & le contenu de la reponse HTML
	    entete = this.la_requete.la_reponse.toString();
	    contenu = new String(this.la_requete.la_reponse.getContenu());
	  
	    // Trace
	    //System.out.println("8- CLIENT -> envoyer_reponse, \n  entete : \n"+ entete);
	    //System.out.println("envoyer_reponse contenu : \n" + contenu);
	 
	    try {  
		 
	     // ecrit la reponse : (entete +contenu) sur le socket d'ecriture	
	     this.la_sortie.write( entete );
	     this.la_sortie.newLine();
	     this.la_sortie.write( contenu );
	     this.la_sortie.flush();
	   
	    } catch (IOException e) {

	        throw new HTTPException("Erreur lors decriture socket ");
	  }	
	  
	  }

	
	  /*
	   * 
	   * methode de la classe client pour liberer tous les ressource 
	   * ouverte pour la communicatiuon
	   * 
	   *  1- fermeture de l_entree
	   *  2- fermeture la_sortie
	   *  3- fermeture le_socket
	   *
	   */

	  public  void liberation() throws HTTPException {

		 System.out.println("9- Client-> methode liberation...");  
		 
		 // libere resources
		 try { 
		 l_entree.close();
		 la_sortie.close();
		 le_socket.close();
		 
		 } catch (IOException e) {

		      throw new HTTPException("Erreur liberation ressources ");
		  }	

	  }
}


