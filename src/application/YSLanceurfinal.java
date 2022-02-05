package application;



import java.io.File;
import java.util.List;
import java.util.Vector;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class YSLanceurfinal {

	public static void main(String[] args) {

	int index =0;
	ObservableList<Stagiaire> listeObserStag = FXCollections.observableArrayList();
		
	//*********************	
//	List<Stagiaire> listestagiaires = new Vector<Stagiaire>();	
//		lit le fichier txt le transforme en List de stagiaires avec le bon de nbre caracteres/octets de chaque attribut
// 	    	
//		FichierATraiter fichier = new FichierATraiter("./src/application/data/STAGIAIRES.DON");
//		listestagiaires=fichier.fabriqueChaine();

	//*********************		
//		Pour effacer le fichier si necessaire		
//		File file =new File("src/application/data/fichier.bin");
//		file.delete();

	//*********************		
//		creation du fichier binaire
//		1) creation de la racine (donc pas de parent)
//		index = FichierATraiter.ecrire1BlocDsFichierBinaire(listestagiaires.get(0));
//		listeObserStag.add(listestagiaires.get(0));	
	
//		2) on ajoute le reste des stagiaires dans le fichier binaire et on cree le lien avec son parent dans la structure ABR
//		for(int i=1; i<listestagiaires.size(); i++ ) {
//			index=FichierATraiter.ecrire1BlocDsFichierBinaire(listestagiaires.get(i));	
//			FichierATraiter.rechercheParentDsAB(0,listestagiaires.get(i),index);
//			listeObserStag.add(listestagiaires.get(i));	
//		}
		

	
	//*********************		



	FichierATraiter.ecrireStagiaireFBDsListObs(0,listeObserStag);
	
//		System.out.println(" taille de la liste observable "+listeObserStag.size()); 
//	for (int i =0; i<3418;i++) {
//		System.out.println(FichierATraiter.lire1BlocDsFB(i).getNom());
//	
//	}	
		

//		//  gestion multicriteres                       nom prenom departement promotion date debut(entier)
//		listeObserStag=FichierATraiter.multiCriteres(listeObserStag,"la","-1","-1","-1",-1);
		
	
		for (Stagiaire stagiaire : listeObserStag) {

			System.out.println(stagiaire.getNom()+" "+ stagiaire.getPrenom()+ " "+stagiaire.getDateEntree() );
		}
		System.out.println(" taille de la liste observable "+listeObserStag.size()); 	
	}
}
