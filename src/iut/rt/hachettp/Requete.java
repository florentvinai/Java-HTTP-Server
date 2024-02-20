package iut.rt.hachettp;

 
import java.io.*;
import java.util.Vector; 

/*
* Classe Requete incluant :
*
*	Donnees Membres -> 
*	Constructeurs ->
*	Methodes -> 
*
*	
*/
public class Requete {

	public int nombre_ligne;	 // contient le nombre de ligne pour chaque objet requete cree
	public int status_requete;   // Indique etat de la requete ( Prete= 1, pas Prete = 0
	
	public Vector<String> lignes; // vecteur chaine contenant toute les ligne requete
	
    public MethodeGet la_methode ;       // Methode GET ...donnee membre associe a la requete
	public Reponse la_reponse; // genere une reponse pour la requete
	//private Object MethodeGet;
	
	// Constructeur de la classe Requete 
	// initialisation donnee membre 
	
	public Requete(){
		lignes = new Vector<String>(); // initialisation vecteur null
		nombre_ligne = 0; // init =0
		status_requete = 0 ;
		la_reponse = new Reponse();
		
	}
	
	
	// Methode estPrete public  qui teste  si requete est prete
	//  qui renvoi VRAI si la requete est prete
	//  qui renvoi FALSE si la requete est prete
	
	public  boolean estPrete() {
		
		boolean test = false;	
		if (status_requete == 1)  test = true;
		else test = false;
		
		return test; // return TRUE si requete prete False Sinon
		
	}
	

   // Methode ajouteLigne public  qui permet de remplir l'objet Requete par les elements
   // lus sur le serveur
   // Elle ajoute une ligne au vecteur de chaine ( nbre de ligne requete)
   // @param : la ligne a ajouter
	
    public  int ajouteLigne( String s) {
      //System.out.println("String to add : " + s);  
      if (s.equals("") != true ) {
    	  
        lignes.add(s);
        // TRACE
        // affiche le Vector 
        // System.out.println("vector String : " + lignes); 
      } else {
    	  // mise a jour etat requete
    	  status_requete= 1;
    	  //System.out.println("ETAT REQUETE : " + status_requete); 
        }
      
      return status_requete;   
	   
   }
    
    Reponse getReponse() {
    	return this.la_reponse;
    	
    	
    }
    
    /*
     * Methode Traiter() 
     * appelle la methde traiter (TP6)
     */
    public void traiter(String root, String name) throws HTTPException {
    
 		System.out.println("6- REQUETE->TRAITER ");
 		this.la_reponse = this.la_methode.traiter(root, name);
    }
    
   
    
    
    /*
     *  Cette methode determine quelle si c 'est un GET ou pas
     *  
     *  1- si c 'est GET ; on cree un objet MethodeGet en passant
     *     en parametre le tableau de string qui correspond a la requete
     *     
     *     si ce nest pas un GET : on doit creer un objet reponse de type 405
     */
    public void determine_methode() throws HTTPException, HTTPError {
    	
    	
    	int code = 0; // code erreur
    	
    	int REQ_MAL_FORME= 400;
    	int HTTP_NON_SUPPORTE=505;
    	
    	// Creation de la table de String	               
        String[] result = new String [nombre_ligne -1] ;
       
        
	    // trace   
		// System.out.println("Stockage objet Requete : " + lignes);
		// System.out.println("nbre ligne : " + nombre_ligne + "  Status requete: "+ status_requete);
            
		 // affectation Vector de chaine  dans la table de String	  	               
		 result = lignes.toArray(result);  
		
	    String[] mot = result[0].split(" ");  //Separe une chaine avec pour decoupage un espace.	        
         
		 //test 1ere ligne 
		 if( mot[0].equals("GET") == true) {
			 
			 	
			 	try {  
			 		System.out.println("6- CLIENT->METHODE -> Creation Objet MethodeGet");
			 		
			 		this.la_methode = new MethodeGet(result);	// creation Methode get HTTP
			 	// assignation de la reponse a la Donne membre 
			          
					} 
			 	  catch (HTTPError e) {
			 		  
			 		  // on recupere le code error
			 		  code = e.getHttpCode();
			 		  
			 		  // REQUETE_MAL_FORME = 1
			 		  if (code == REQ_MAL_FORME) {
			 		      // gestion des erreurs et creation reponse associee
			 		      this.la_reponse = new Reponse400("BAD REQUEST");
					      throw new HTTPError(400, "Pbm requete analyse ");
			 		  }
			 		 
			 		 // HTTP_NON_SUPPORTE = 2
			 		 if (code == HTTP_NON_SUPPORTE) {
			 		      // gestion des erreurs et creation reponse associee
			 		      this.la_reponse = new Reponse505("HTTP VERSION NOT SUPPORTED");
					      throw new HTTPError(505, "Pbm requete analyse ");
			 		  }
			 		  
			      }	
			 	
		 		System.out.println("6- CLIENT->METHODE-> REQUETE->Methode GET ok!!! ");
		 }
		 else {
			 // gestion des erreurs et creation reponse associee
			 System.out.println("Methode INCONNUE !!! ");
			 System.out.println("Creation Reponse type 405 " );
			 this.la_reponse = new Reponse405("METHOD NOT ALLOWED");
			 new HTTPException("Pbm requete analyse ");
		 }
    	
    	
    }

}
