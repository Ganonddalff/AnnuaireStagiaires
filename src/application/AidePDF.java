package application;

import java.awt.Desktop;
import java.io.File;

//************** Voir les deux points d'attention plus bas *********** ///

public class AidePDF {
	
// ************** Rajouter dans le module-info.java ->>>>  requires java.desktop    <<<<<---- *********** ///
// ************** Modifier le chemin du fichier *********** ///
	
		  public static String  monPDFgo (){
		    try 
		    {
		    	File f  = new File("/Users/antoine/Documents/Me/Informatique/ISIKA/projet_1_fichiers/liste.pdf");
		    	Desktop.getDesktop().open(f);
		    } catch (Exception ex) {
		        ex.printStackTrace();
		    }
		    return "fichier trouvé";
		  }
		}
