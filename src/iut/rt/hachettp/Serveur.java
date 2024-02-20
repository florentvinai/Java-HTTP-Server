package iut.rt.hachettp;

import java.net.*;
import java.io.*;


//*	class Serveur  
//* Donnees Membres -> propriete de l'objet Serveur
//*	Constructeurs -> pour instancier un nouvel objet serveur
//*	Methodes -> definir le comportement de la classe serveur
//*	Main() pour tester/executer le prg utilisant les classes
//* Prof : Wurbel
//*
//* @author Vinai Florent IUT R&T
//* @date 2019
//*/

public class Serveur {

 // Declaration Donnees mem
  private int ServerPort = 55555;// Valeur par default
 
  
  public final String racine_servie = "c:/Users/flo13" ; //DMenbre constant
  public final String fichier_default = "/index.html";      //DMenbre constant

  // Constructeur de la classe Serveur
  //
  // Fct 	: Le constructeur initialise les donneees membre
  //		et cree l'objet serveur 
  // Arg 	: numero port d'ecoute int Port
  // return : none
  // ---------------------------------

  public Serveur(int Port) {

    this.ServerPort = Port; // init donnee membre

    System.out.println("SERVEUR -> creation du serveur sur le port :" + this.ServerPort);
    System.out.println("\n\n");
  }


  // ---------------------------------
  // Methode publique lance()
  //
  // Fct 	: creer l'objet serversocket av le port, 
  // 		  effectue diverses initialisations.
  // 			boucle infinie : attend une connexion TCP entrante sur le port qui a ete passe au constructeur, 
  // 			la traite, puis se remet en attente d'une nouvelle connexion entrante. 
  //
  // Arg 	: none
  // return : none
  // ---------------------------------

  public void lance() throws HTTPException, HTTPError{


		ServerSocket ss;
		Socket s;
		Client send;
	

		while(true) {	
			
		try {
			ss = new ServerSocket(this.ServerPort);
			
			//System.out.println("Création du serveur socket réussie sur Port=" + this.ServerPort);
		}
		catch(IOException e) {
			System.out.println("erreur de création du socket serveur");
			return;
		}

			try {
				System.out.println("1- SERVEUR-> Attente requete");
				//instruction bloquante tant que rien sur	
				// le Serveursocket
				s = ss.accept();
				ss.close();
				
			}
			catch(IOException e) {
				
				System.out.println("connexion échouée");
				return;
			}
			
			
			
			System.out.println("2- SERVEUR-> Création Objet client");
			
			// creation objet client
			send = new Client (s);
			
			// traiter 
			send.traiter( this.racine_servie, this.fichier_default);
			
			System.out.println("10- SERVEUR-> Delete du client");
			System.out.println("\n\n");
			
		}
	}
 

  
//---------------------------------
 // Methode publique main() de TEST du serveur
 //
 // Fct 	: creer l'objet serversocket av le port, 
 // 		  effectue diverses initialisations.
 // 			boucle infinie : attend une connexion TCP entrante sur le port qui a ete passe au constructeur, 
 // 			la traite, puis se remet en attente d'une nouvelle connexion entrante. 
 //
 // Arg 	: none
 // return : none
 // ---------------------------------

  public static void main(String args[]) throws HTTPException, HTTPError {

    Serveur serveur = new Serveur(8083);

    serveur.lance();

	

  }

}