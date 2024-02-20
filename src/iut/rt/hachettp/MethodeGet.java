package iut.rt.hachettp;

import java.io.File;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
//import java.io.*;


/*
* Classe Methodeget incluant :
*
*	Donnees Membres -> 
*	Constructeurs -> permet d'initialiser les l_url, l_hote,l_agent a partir 
*                    d'un tableau de string qui corespond a la lecture du socket
*	Methodes -> different public accesseurs,et private  analyse(), analyse ligne1()
*
*	
*/
class MethodeGet extends Methode {
	
		private String l_url;  // DMembre : nom du fichier associe au GET
		private String l_hote; // DMembre : nom du Hotequi fait le GET
		private String l_agent;// DMembre : nom de l'agent utilise : Firefoc, chrome...
		
		// Constructeur de MthodeGet
		// @arg : tableau de ligne de la requete
		// Initialisze les Donneee Membres
		// appelle la methode analyse ( pour le moment)
		// Remonte les HttpError code a traiter si excepiton
		
		
		public MethodeGet(String[] lignes) throws HTTPError {
			
			// initilisation donneee membre
			l_url= null;
			l_hote = null;
			l_agent = null;
		
			
			 try {  
				 analyse(lignes);	// analyse de la requete HTTP
					    
				 } catch (HTTPError e) {
					 
					 System.out.println("Erreur du a l'analyse de la requete : " + e);
					 //erreur sur la requete
					 
					 if ( e.getHttpCode() == 400) new Reponse400("BAD REQUEST");
					 
					 if ( e.getHttpCode() == 505) new Reponse505("HTTP NON SUPPORTED");
					 
				   }	
		        
		 }
		     
		
		// methode accesseur pour recup URL
		public String getURL() {
			return l_url;
		
		}
		// methode accesseur pour recup Hote
		public String getHote() {
			return l_hote;
		
		}
		
		// methode accesseur pour recup agent
		public String getAgent() {
			return l_agent;
		
		}
		
		
		/*
	     *  Traiter : algorithme donne en consignes TP5
	     *  
	     *  @param
	     *   cette methode renvoi l'objet reponse qu'elle
	     *     aura creer en fonction de la valeur url stoque dans la methode
	     */
	    
	Reponse traiter(String root, String name) throws HTTPException {
				
				Reponse reponse;
				String ROOT = root;
				String filename, FICHIER_DEFAUT = name;
				
			    File fichier_servi;

			    filename = ROOT + this.getURL();
			    System.out.println("7- METHODEGET -> Methode traiter()");
			    System.out.println("7- METHODEGET -> Resources file  : " + filename );
			     
			    
			      fichier_servi = new File(filename);
			   

			    if ( fichier_servi.exists() == false) 
			    	reponse = new Reponse404(this.getURL());
			  
			    else   {
			    	
			      if (fichier_servi.isDirectory() == true) {
			    	fichier_servi = new File(fichier_servi,FICHIER_DEFAUT);
			      }
			    
			      if ( fichier_servi.exists() == true) {
			    	  
			    	reponse = new Reponse200(fichier_servi);
			      }
			      else reponse = new Reponse404(this.getURL());
			    }
			    
			    
		        
				return reponse;
				
			}
		    
		
		/**
	     * Methode analyse
	     * @param c Requete à analyser : String[] c
	     * 
	     * Recupere les differente parties d'une requete HTTP.
	     * verifie la struture de la requete
	     * Stocke certaine donneee dans les donnees membres : url,hote, agent
	     */
	     
	     private void analyse(String[] c) throws HTTPError {
	        
	        String champ;
	        String valeur;
	        
	        Pattern pattern = Pattern.compile("^[-a-zA-Z]+: *.+ *$"); //Permet de valider le format d'un requete (Champ:Valeur)
	        Matcher matcher;
	        boolean valide;
	        
	        //Permet de separer Champ et valeur.
	        Pattern scinder = Pattern.compile(":"); 
	        String[] sous_parties;  	
	        
	        try {  
	        	analyse_ligne_1(c[0]); //Analyse de la ligne 1 
				    
			    } 
	             catch (HTTPError e) {

			      new HTTPError(400, "Requete mal Formee ");
			      
			      }	
	        //Analyse des ligne suivante.
	        for(int i = 1; i < c.length; i++) { 
	            if(c[i] != null && c[i].equals("") != true) {
	                matcher = pattern.matcher(c[i]); //Verifie le respect
	                valide = matcher.matches();      //du pattern (champ:valeur)
	                if(valide == true) {
	                    sous_parties = scinder.split(c[i]); //Decoupe une chaine en deux partie champ et valeur 
	                    champ = sous_parties[0];
	                    valeur = sous_parties[1];
	                    
	                    // Trace pour verifier la requete
	                    //System.out.println("champs : " + champ + "  valeur : " + valeur);
	                    
	                    if(champ.equals("Host") == true) {
	                        sous_parties = scinder.split(valeur); //Redecoupage dans le cas ou un numero de port est specifier.
	                        l_hote = sous_parties[0];
	                    } else {
	                        if(champ.equals("User-Agent") == true) {
	                            l_agent = valeur;
	                        }
	                    }
	                } else {
	                	//TODO
	                    System.out.println("HTTP Bad Request " + c[i]); 
	                    new HTTPError(400, "HTTP Bad Request : " + c[i]);
	                    //Si la chaine n'est pas de la forme (Champ:Valeur)
	                }
	            }
	        }
	    }
	     
	     
	     
	    /**
	     * 
	     * @param l Premiere ligne d'une requete HTTP.
	     * Analyse la premiere ligne d'une requete HTTP.
	     * @throws HTTPError
	     */
	    private void analyse_ligne_1(String l) throws HTTPError {
	    	
	        String[] mot = l.split(" "); //Separe une chaine avec pour decoupage un espace.
	        if(mot[0] == null) {
	            System.out.println("HTTP Bad Request: Methode non specifier");
	            //TODO
	            new HTTPError(400, "HTTP Bad Request: Methode non specifier");
	            
	        }else {
	            if(mot[0].equals("GET") == true) { //Verifie que la methode HTTP soit bien de type GET
	                if(mot[1] == null) {
	                	//TODO
	                	//System.out.println("HTTP Bad Request: Chemin ressource non indiquer");
	                	new HTTPError(400, "HTTP Bad Request: Methode non specifier");
	    	            
	                } else {
	                    l_url = mot[1]; //Recupere le chemin de la ressource demander.
	                    if(mot[2] == null) {
	                    	//TODO
	                    	//System.out.println("HTTP Bad Request: Version HTTP non indiquer");
	                    	new HTTPError(505, "HTTP Bad Request: Methode non specifier");
	        	            
	                    }else {
	                        if((mot[2].equals("HTTP/1.0") != true) && (mot[2].equals("HTTP/1.1") != true)) { //Verifie que la methode http est supporter par le serveur.
	                        	//System.out.println("HTTP Version Unhandled");
	                        	//TODO
	                        	new HTTPError(505,"HTTP Bad Request: Methode non specifier");
	            	           
	                        }
	                    }
	                }
	            }else {
	            	//System.out.println("HTTP Bad Request: Methode "+mot[0]+" non supporter !"); //Autre type de methode HTTP.
	            	 new HTTPError(400, "HTTP Bad Request: Methode "+ mot[0]+ " non supporter !");
	            	
	            }
	        }
	    }


		
	    

}
