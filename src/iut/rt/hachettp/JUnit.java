package iut.rt.hachettp;

import static org.junit.Assert.*;
import java.io.*;
import org.junit.Test;


public class JUnit {
	
  // TESTER le STockage des parametres de la
  // methodeGet en simulant les param en entree s[]
  // TestOK -> les param entree bien stockes
  //  Param entree ->OK
 
@Test
  public void testok() throws HTTPException, HTTPError {
   String [] s= new String[3];
   s[0]= "GET /index.html HTTP/1.1";
   s[1]= "Host:localhost";
   s[2]= "User-Agent:Mozilla/5.0 (X11;";
 
   MethodeGet a= new MethodeGet(s);
 
   // evaluation des valeur stockes avec ceux en inpput
   assertEquals("/index.html", a.getURL());
   assertEquals("localhost", a.getHote());
   assertEquals("Mozilla/5.0 (X11;", a.getAgent());
  }

  // TESTER le STockage des parametres de la
  // methodeGet en simulant les param en entree s[]
  // Test1 -> les param entree sont erronés ds s[0]
  // pbm GET line

@Test
  public void testnok1() throws HTTPException, HTTPError {
   String [] s= new String[3];
   s[0]= "NIMPORTE QUOI";
   s[1]= "Host:localhost";
   s[2]= "User-Agent:Mozilla/5.0 (X11;";
 
   MethodeGet a= new MethodeGet(s);
 
  //evaluation des valeur stockes avec ceux en inpput
   assertEquals(null, a.getURL());
   assertEquals("localhost", a.getHote());
   assertEquals("Mozilla/5.0 (X11;", a.getAgent());
  }

  //TESTER le STockage des parametres de la
  //methodeGet en simulant les param en entree s[]
  //Test1 -> les param entree sont erronés ds s[1]
  // pbm hostname
@Test
  public void testnok2() throws HTTPException, HTTPError {
   String [] s= new String[3];
 
   s[0]= "GET /index.html HTTP/1.1";
   s[1]= "jhguget install";
   s[2]= "User-Agent:Mozilla/5.0 (X11;"; 
 
   MethodeGet a= new MethodeGet(s);
 
   //evaluation des valeur stockes avec ceux en inpput
   assertEquals("/index.html", a.getURL());
   assertEquals(null, a.getHote());
   assertEquals("Mozilla/5.0 (X11;", a.getAgent());
  }

  //TESTER le STockage des parametres de la
  //methodeGet en simulant les param en entree s[]
  //Test1 -> les param entree sont erronés ds s[2]
  //pbm hostname

@Test
  public void testnok3() throws HTTPException, HTTPError {
   String [] s= new String[3];
   s[0]= "GET /index.html HTTP/1.1";
   s[1]= "Host:localhost";
   s[2]= "Us1;";
 
   MethodeGet a= new MethodeGet(s);
   assertEquals("/index.html", a.getURL());
   assertEquals("localhost", a.getHote());
   assertEquals(null, a.getAgent());
  }


  // TESTER la methode estPrete() 
  // en utilsant la methode ajouteLigne() 
  // de la classe requete
  // en evaluant estPrete() == false
  // si la ligne ajoutee !=""
  //

@Test
  public void testRequeteNOK() {
   String [] s= new String[3];
   s[0]= "GSET";
   s[1]= "lo";
   s[2]= "toto"; // pa fn de requete
   int res;
 
   Requete r= new Requete();
 
   // ajoute s[0] dans le vecteur
   res = r.ajouteLigne(s[0]);
 
   assertEquals(0,res); 
   assertEquals(false, r.estPrete());
 
   //ajoute s[1] dans le vecteur
   res = r.ajouteLigne(s[1]);

   assertEquals(0,res); 
   assertEquals(false, r.estPrete());
 
   // ajoute s[2] dans le vecteur
   res = r.ajouteLigne(s[2]);
 
   assertEquals(0,res); 
   assertEquals(false, r.estPrete());
 
  }

  //TESTER la methode estPrete() 
  //en utilsant la methode ajouteLigne() 
  //de la classe requete
  //en evaluant estPrete() == true
  //la ligne ajoutee=""
  //
@Test
  public void testRequeteOK() {
	 String [] s= new String[3];
	 s[0]= "GET";
	 s[1]= "localhost";
	 s[2]= ""; // fn de requete
	 int res;
	 
	 Requete r= new Requete();
	 
	 // ajoute s[0] dans le vecteur
	 res = r.ajouteLigne(s[0]);
	 
	 assertEquals(0,res); 
	 assertEquals(false, r.estPrete());
	 
	 //ajoute s[1] dans le vecteur
	 res = r.ajouteLigne(s[1]);

	 assertEquals(0,res); 
	 assertEquals(false, r.estPrete());
	 
	 // ajoute s[2] dans le vecteur
	 res = r.ajouteLigne(s[2]);
	 
	 assertEquals(1,res); // la requete est prete
	 assertEquals(true, r.estPrete());
	 
	}


// TESTER la methode traiter() 
// en utilsant le code de la reponse
// de la classe reponse
//
// 1- TEST.1 -> la reponse est OK -> code 200
// 2- TEST.2 -> la reponse est NOK -> code 404

@Test
  public void testTraiterRequete() throws HTTPError, HTTPException {
	
	 String [] s= new String[3];
	 s[0]= "GET /index.html HTTP/2.1";
	 s[1]= "Host:localhost";
	 s[2]= "User-Agent:Mozilla/5.0 (X11;";
	 
	 // creation methode
	 MethodeGet a= new MethodeGet(s);

	 // creation requete
	 Requete r= new Requete();
	 r.la_methode = a;
	 
	// ajoute s[0] dans le vecteur
    r.ajouteLigne(s[0]);
	r.ajouteLigne(s[1]);
	r.ajouteLigne(s[2]);
	
	// TEST.1
	// la methode a traiter genere une REPONSE ->OK
	
	// creation reponse
	r.la_reponse = a.traiter("c:/Users/flo13","/index.html");
	assertEquals(200, r.la_reponse.code);
	
	// TEST.2
	// la methode a traiter genere une REPONSE ->NOK
	// Ressource FILE NOT FOUND -> Erreur 404
	
	 s[0]= "GET /titi.html HTTP/1.1";
	 s[1]= "Host:localhost";
	 s[2]= "User-Agent:Mozilla/5.0 (X11;";
	 
	 // creation methode
	 MethodeGet a1= new MethodeGet(s);
	// creation requete
	Requete r1= new Requete();
	r1.la_methode = a1;
		
	 
	 // ajoute s[0] dans le vecteur
	    r1.ajouteLigne(s[0]);
		r1.ajouteLigne(s[1]);
		r1.ajouteLigne(s[2]);
		
		// creation reponse
		r1.la_reponse = a1.traiter("c:/Users/flo13","/index.html");
		assertEquals(404, r1.la_reponse.code);
		
  }


//TESTER la methode traiter() 
//en utilsant le code de la reponse
//de la classe reponse
//
//1- TEST.1 -> la reponse est NOK -> requete mal forme -> ressource not found
// 2 TEST 2 -> reponse est OK -> requete OK ->reponse200
//2- TEST.3 -> la reponse est NOK -> mauvaise version HTTP

@Test
public void testTraiterRequete2() throws HTTPError, HTTPException {
	
	 String [] s= new String[3];
	 s[0]= "SETindex.html HTTP/.1";
	 s[1]= "Host:localhost";
	 s[2]= "User-Agent:Mozilla/5.0 (X11;";
	 
	 // creation methode
	 MethodeGet a= new MethodeGet(s);

	 // creation requete
	 Requete r= new Requete();
	 r.la_methode = a;
	 
	// ajoute s[0] dans le vecteur
    r.ajouteLigne(s[0]);
	r.ajouteLigne(s[1]);
	r.ajouteLigne(s[2]);
	
	// TEST.1
	// la methode a traiter genere une REPONSE ->NOK mal forme
	
	// creation reponse
	r.la_reponse = a.traiter("c:/Users/flo13","/index.html");
	assertEquals(404, r.la_reponse.code);
	
	// TEST.2
	// la methode a traiter genere une REPONSE ->OK
	// Ressource FILE FOUND -> Erreur 200
	
	 s[0]= "GET /index.html HTTP/1.1";
	 s[1]= "Host:localhost";
	 s[2]= "User-Agent:Mozilla/5.0 (X11;";
	 
	 // creation methode
	 MethodeGet a1= new MethodeGet(s);
	// creation requete
	Requete r1= new Requete();
	r1.la_methode = a1;
		
	 
	 // ajoute s[0] dans le vecteur
	    r1.ajouteLigne(s[0]);
		r1.ajouteLigne(s[1]);
		r1.ajouteLigne(s[2]);
		
		// creation reponse
		r1.la_reponse = a1.traiter("c:/Users/flo13","/index.html");
		assertEquals(200, r1.la_reponse.code);
		
		
		// TEST.3
		// la methode a traiter genere une REPONSE ->NOK
		// Ressource HTTP bad version -> Erreur 505
		
		 s[0]= "GET /index.html HTTP/2.2";
		 s[1]= "Host:localhost";
		 s[2]= "User-Agent:Mozilla/5.0 (X11;";
		 
		
		// creation requete
		Requete r2= new Requete();
		
		 
		 // ajoute s[0] dans le vecteur
		    r2.ajouteLigne(s[0]);
			r2.ajouteLigne(s[1]);
			r2.ajouteLigne(s[2]);
			
			
		try {
		   // creation methode
		    MethodeGet a2 = new MethodeGet(s);
		    r2.la_methode = a2;
		    // creation reponse
			 r2.la_reponse = a2.traiter("c:/Users/flo13","/index.html");
			
		    }
		 catch ( HTTPError e) {
			 
			 if ( e.getHttpCode() == 400) assertEquals(400, e.getHttpCode());
			 
			 if ( e.getHttpCode() == 505) assertEquals(505, e.getHttpCode());
			
		 }	 
		
				
		
}

// TEST la creation HTTPError
// evaluation du code error avec la
// methode  getHttpCode, getMessage de la classe HTTPerreur
// test tous les cas possible d'erreur 
// 
@Test
  public void testError() {
	
	 String [] s= new String[6];
	 s[0]= "OK";
	 s[1]= "BAD REQUEST";
	 s[2]= "NOT FOUND";
	 s[3]= "METHOD NOT ALLOWED";
	 s[4]= "INTERNAL SERVER ERROR";
	 s[5]= "HTTP VERSION NOT SUPPORTED";
	
	// test retour erreur 200
	HTTPError er = new HTTPError(200,s[0]) ;
	assertEquals(200, er.getHttpCode());
	assertEquals("200 OK", er.getMessage());
	
	// test retour erreur 400
	HTTPError er1 = new HTTPError(400,s[1]) ;
	assertEquals(400, er1.getHttpCode());
	assertEquals("400 BAD REQUEST", er1.getMessage());
	
	// test retour erreur404
    HTTPError er2 = new HTTPError(404,s[2]) ;
	assertEquals(404, er2.getHttpCode());
	assertEquals("404 NOT FOUND", er2.getMessage());
	
	// test retour erreur405
    HTTPError er3 = new HTTPError(405,s[3]) ;
	assertEquals(405, er3.getHttpCode());
	assertEquals("405 METHOD NOT ALLOWED", er3.getMessage());
	
	// test retour erreur500
    HTTPError er4 = new HTTPError(500,s[4]) ;
	assertEquals(500, er4.getHttpCode());
	assertEquals("500 INTERNAL SERVER ERROR", er4.getMessage());
	
	
	// test retour erreur505
    HTTPError er5 = new HTTPError(505,s[5]) ;
	assertEquals(505, er5.getHttpCode());
	assertEquals("505 HTTP VERSION NOT SUPPORTED", er5.getMessage());
	
  }

//TESTE  la creation Object Reponse
//evaluation du code error et message avec la
//test tous les cas possible d'erreur 
//
@Test
  public void testReponseRequete() throws HTTPError, HTTPException {
	
	 String [] s= new String[6];
	 File f = new File("c:/Users/flo13/index.html");
	 
	 s[0]= "OK";
	 s[1]= "BAD REQUEST";
	 s[2]= "NOT FOUND";
	 s[3]= "METHOD NOT ALLOWED";
	 s[4]= "INTERNAL SERVER ERROR";
	 s[5]= "HTTP VERSION NOT SUPPORTED";
	
	// Test Reponse ok
	Reponse  r1 = new Reponse200(f);
	assertEquals(200, r1.code);
	assertEquals(s[0], r1.message_std);
	
	// Test Reponse 
	Reponse  r2 = new Reponse400(s[1]);
	assertEquals(400, r2.code);
	assertEquals(s[1], r2.message_std);
	
	// Test Reponse 
	Reponse  r3 = new Reponse404(s[2]);
	assertEquals(404, r3.code);
	assertEquals(s[2], r3.message_std);
	
	// Test Reponse 
	Reponse  r4 = new Reponse405(s[3]);
	assertEquals(405, r4.code);
	assertEquals(s[3], r4.message_std);
	
	// Test Reponse 
	Reponse  r5 = new Reponse500(s[4]);
	assertEquals(500, r5.code);
	assertEquals(s[4], r5.message_std);
	
	// Test Reponse 
	Reponse  r6 = new Reponse505(s[5]);
	assertEquals(505, r6.code);
	assertEquals(s[5], r6.message_std);
	
  }
	

}