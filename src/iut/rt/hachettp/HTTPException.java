package iut.rt.hachettp;

// Bibliotheque Java pour gerer les Exception

// ----------------------------------------------

//import java.lang.Exception ;


public class HTTPException extends java.lang.Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HTTPException ( String s) {

		// appelle le constructeur du parent
		super(" "+ s);		
		System.out.println("HTTPexception : " + s); // Trace
		//Exception e; 

	}

}
