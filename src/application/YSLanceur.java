package application;



import java.io.File;
import java.util.List;
import java.util.Vector;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class YSLanceur {

	public static void main(String[] args) {

	int index =0;	
		

		
		FichierATraiter fichier = new FichierATraiter("./src/application/data/STAGIAIRES.DON");
		List<Stagiaire> listestagiaires = new Vector<Stagiaire>();
		listestagiaires=fichier.fabriqueChaine();
		ObservableList<Stagiaire> listeObserStag = FXCollections.observableArrayList(); 

		File file =new File("src/application/data/fichier.bin");
		file.delete();
		

//		for (int i=0; i<listestagiaires.size();i++) {
//		//System.out.println(" "+j);
//		FichierATraiter.ecrire1BlocDsFichierBinaire(listestagiaires.get(i),i);
//	
//		}
//		String lecture="";
//		//listestagiaires.size()
//		for (int i=0; i<listestagiaires.size();i++) {
//		//lecture+=FichierATraiter.lireUnNoeudFichierBinaire(i);
//			
//			System.out.println(FichierATraiter.lire1BlocDsFichierBinaire(i));
//		
//
//		}
		
		//System.out.println(lecture);
		
//		FichierATraiter.ecrire1BlocDsFichierBinaire(listestagiaires.get(0));
//		
//		System.out.println(FichierATraiter.lire1BlocDsFichierBinaire(0));
//		FichierATraiter.ecrireIndexEnfantGaucheDsParent(0, 3);
//		System.out.println(FichierATraiter.lire1BlocDsFichierBinaire(0));
//		
//		FichierATraiter.ecrireIndexEnfantDroitDsParent(0, 4);
//		System.out.println(FichierATraiter.lire1BlocDsFichierBinaire(0));
//		FichierATraiter.ecrireIndexEnfantDoublonDsParent(0, 6);
//		System.out.println(FichierATraiter.lire1BlocDsFichierBinaire(0));
//		
//		
//		System.out.println("\n index Gauche de 0 "+ FichierATraiter.lire1IndexEnfantGauche(0));
//		System.out.println("\n index Droit de 0 "+ FichierATraiter.lire1IndexEnfantDroit(0));
//		System.out.println("\n index doublon de 0 "+ FichierATraiter.lire1IndexEnfantDoublon(0));
		
	//*********************	
		// on cree la racine du fichier

		index = FichierATraiter.ecrire1BlocDsFichierBinaire(listestagiaires.get(0));
		listeObserStag.add(listestagiaires.get(0));
//		System.out.println(" ***  "+" "+FichierATraiter.lire1BlocDsFichierBinaire(0));

		//on ajoute chaque stagiaire dans le fichier binaire et on cree le lien avec son parent dans la structure ABR
		for(int i=1; i<20; i++ ) {
			index=FichierATraiter.ecrire1BlocDsFichierBinaire(listestagiaires.get(i));	
			FichierATraiter.rechercheParentDsAB(0,listestagiaires.get(i),index);
			listeObserStag.add(listestagiaires.get(i));
			
		}
		
//		System.out.println("\n ***** \n");
//		
//		for(int i=0; i<20; i++ ) {
//			System.out.println("index "+i+" nom "+ listestagiaires.get(i).getNom());
//
//		}	
//		
//		System.out.println("\n ***** \n");	
		
//		for(int i=0; i<20; i++ ) {
//			System.out.println(FichierATraiter.lire1BlocDsFichierBinaire(i));
//
//		}	
//		System.out.println("\n ***** \n");	
//		




// test pour supprimer un feuille		
//		System.out.println(FichierATraiter.lire1BlocDsFichierBinaire(15));
//		System.out.println(FichierATraiter.lire1BlocDsFichierBinaire(16));
//		FichierATraiter.supprimerStagiaire(15,16);
//		System.out.println(FichierATraiter.lire1BlocDsFichierBinaire(15));
//		System.out.println(FichierATraiter.lire1BlocDsFichierBinaire(16));	
		
//test pour supprimer un stagiaire bouchet13 ayant un seul enfantbouamama14 (le parent est augereau4)
//
//		System.out.println(FichierATraiter.lire1BlocDsFichierBinaire(4));		
//		System.out.println(FichierATraiter.lire1BlocDsFichierBinaire(13));
//		System.out.println(FichierATraiter.lire1BlocDsFichierBinaire(14));
//		FichierATraiter.supprimerStagiaire(4,13);
//		System.out.println("\n ***** \n");	
//		System.out.println(FichierATraiter.lire1BlocDsFichierBinaire(4));		
//		System.out.println(FichierATraiter.lire1BlocDsFichierBinaire(13));
//		System.out.println(FichierATraiter.lire1BlocDsFichierBinaire(14));
//		System.out.println(" ***** ");	
//		System.out.println(FichierATraiter.dernierDescendant(2));
		
		FichierATraiter.afficherInfixe(0);
//		FichierATraiter.supprimer(0,listestagiaires.get(3),0);
//		FichierATraiter.afficherInfixe(0);
//		
//		System.out.println("\n***\n");
//		
//		for (Stagiaire stagiaire : listeObserStag) {
//
//			System.out.println(stagiaire.getNom()+" "+ stagiaire.getPrenom());
//		}
//		
//		
//
//		listeObserStag=FichierATraiter.critereNom(listeObserStag,"wa");
//		listeObserStag=FichierATraiter.criterePrenom(listeObserStag,"PI");
//		System.out.println("\n* apres xxx criteres\n");
//		
//		for (Stagiaire stagiaire : listeObserStag) {
//
//			System.out.println(stagiaire.getNom()+" "+ stagiaire.getPrenom());
//		}
		
	}
}
