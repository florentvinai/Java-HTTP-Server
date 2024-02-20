package iut.rt.hachettp;


import static org.junit.Assert.assertEquals;
import org.junit.Test;


/*public class RequeteTest{
	
	@Test
	public void testReq() {
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
	 
	 
	 // ajoute s[2] dans le vecteur
	 res = r.ajouteLigne(s[2]);
	 
	 assertEquals(1,res); 
	 assertEquals(true, r.estPrete());
	 
	}

	@Test
	public void testRequPrete() {
		
		 String [] s= new String[3];
		 s[0]= "";
		 s[1]= "localhost";
		 s[2]= "";
		 Requete r= new Requete();
		 
		 // ajoute s[0] dans le vecteur
		 r.ajouteLigne(s[0]);
		 assertEquals(true, r.estPrete());
	}



	@Test
	public void testTraiterRequete() throws HTTPError, HTTPException {
		 String [] s= new String[3];
		 s[0]= "GET /index.html HTTP/1.1";
		 s[1]= "Host:localhost";
		 s[2]= "User-Agent:Mozilla/5.0 (X11;";
		 MethodeGet a= new MethodeGet(s);

		 Requete r= new Requete();
		 r.la_methode = a;
		 
		// ajoute s[0] dans le vecteur
	    r.ajouteLigne(s[0]);
		r.ajouteLigne(s[1]);
		r.ajouteLigne(s[2]);
		
		
		Reponse rep = r.traiter("c:/Users/flo13","/index.html");
		//Reponse rep = r.traiter("c:/Users","/index.html");
		
		r.la_reponse = rep;
		
		//assertEquals(404, r.la_reponse.code);
		assertEquals(200, r.la_reponse.code);

	}


	@Test
	public void testError() {
		
		 String [] s= new String[6];
		 s[0]= "TEST 200";
		 s[1]= "TEST 400";
		 s[2]= "TEST 404";
		 s[3]= "TEST 405";
		 s[4]= "TEST 500";
		 s[5]= "TEST 505";
		 
		HTTPError er = new HTTPError(200,s[0]) ;
		
		assertEquals(200, er.getHttpCode());
		assertEquals("200 TEST 200", er.getMessage());
		
	    HTTPError er2 = new HTTPError(404,s[2]) ;
		
		assertEquals(404, er2.getHttpCode());
		assertEquals("404 TEST 404", er2.getMessage());
		
		// a completer
	}

	@Test
	public void testReponseRequete() throws HTTPError, HTTPException {
		
		 String [] s= new String[6];
		 //File f = new File("/index.html");
		 
		 s[0]= "TEST 200";
		 s[1]= "TEST 400";
		 s[2]= "TEST 404";
		 s[3]= "TEST 405";
		 s[4]= "TEST 500";
		 s[5]= "TEST 505";
		 
		//Reponse  r1 = new Reponse200(f);
		Reponse  r2 = new Reponse400(s[1]);
		Reponse  r3 = new Reponse404(s[2]);
		Reponse  r4 = new Reponse405(s[3]);
		Reponse  r5 = new Reponse500(s[4]);
		Reponse  r6 = new Reponse505(s[5]);
		
		
		//assertEquals(200, r1.code);
		assertEquals(400, r2.code);
		assertEquals(404, r3.code);
		assertEquals(405, r4.code);
		assertEquals(500, r5.code);
		assertEquals(505, r6.code);
		
	}

	}
*/