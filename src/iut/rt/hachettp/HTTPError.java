package iut.rt.hachettp;
//import java.lang.Exception ;



	public class HTTPError extends java.lang.Exception {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private int code;
		
		public HTTPError ( int code, String s) {
			
			// appel constructeur parent
			super(""+code+" "+ s);
			
	        this.code=code; // affectation code donnee membre
	    
			System.out.println("HTTPexception : " + s); // Trace
			//Exception e; 

		}
		
    public int getHttpCode()
    {
    	return code;}

	}
	
	