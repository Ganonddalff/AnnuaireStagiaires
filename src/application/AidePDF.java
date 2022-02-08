package application;

import java.awt.Desktop;
import java.io.File;

//************** Voir les deux points d'attention plus bas *********** ///

public class AidePDF {
	
// ************** Rajouter dans le module-info.java ->>>>  requires java.desktop    <<<<<---- *********** ///
// ************** Modifier le chemin du fichier *********** ///
	
		  public static void main(String args[]){
			  
			  String cheminFichier = "/home/matt/Documents/ISIKA/Projet1/liste.pdf";
		    try 
		    {
		    	File f  = new File(cheminFichier);
		    	Desktop.getDesktop().open(f);
		    } catch (Exception ex) {
		        ex.printStackTrace();
		        System.out.println("Pas de fichier");
		    }
		    
		    
		  }
		  
		  
		  
		}
