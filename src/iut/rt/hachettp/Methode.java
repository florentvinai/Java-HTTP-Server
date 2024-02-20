package iut.rt.hachettp;


/*
* Classe abstraite Methode incluant :
*
*	Cette classe ne sera donc jamais instancie
*
*	1- On utilisera la classes MethodeGet 
*	heritée de la classe methode
*
*   2- Idem pour la methode abstraite
*   il faudra faire une methode traiter() 
*   dans la classe methodeGet
*	
*/

public abstract class Methode {
	
  // Declaration methhode abstraite consignes TP2
  abstract Reponse traiter(String root, String name) throws HTTPException ;


}
