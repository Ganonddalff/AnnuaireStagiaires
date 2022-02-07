package application;



import java.io.File;
import java.util.List;
import java.util.Vector;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class YSLanceurfinal {

	/**
	 * @param args
	 */
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


 
//		FichierATraiter.ecrireStagiaireFBDsListObs(0,listeObserStag);
		


//		//  gestion multicriteres                       nom prenom departement promotion dateEntree(entier)
//	listeObserStag=FichierATraiter.multiCriteres(listeObserStag,"-1","-1","91","-1",-1);
//		
		


		
//		for (Stagiaire stagiaire : listeObserStag) {
//			
//			System.out.println(stagiaire.getNom()+" "+ stagiaire.getPrenom()+ " dep "+stagiaire.getCodeDepartement()+" promo "+stagiaire.getPromo() +" date "+stagiaire.getDateEntree() );
//		}

//		
	

		
		
//		for (int i = 00; i < 20; i++) {
//		System.out.println(FichierATraiter.lire1BlocDsFB(i).getNom()+" "+ FichierATraiter.lire1BlocDsFB(i).getPrenom()+" "+ FichierATraiter.lire1BlocDsFB(i).getDateEntree()+" "+ FichierATraiter.lire1BlocDsFB(i).getPromo()+" "+ FichierATraiter.lire1BlocDsFB(i).getPrenom()+" "+" G "+ FichierATraiter.lire1IndexEnfantGauche(i)+" D "+FichierATraiter.lire1IndexEnfantDroit(i)+" DO "+FichierATraiter.lire1IndexEnfantDoublon(i)+" index "+ i);		
//		}
	
	
//		Stagiaire s1 =  new Stagiaire("ZZZzz","Top","01","CDA15",2022);
//		FichierATraiter.convertirBonFormat(s1);
//		Stagiaire s2 =  new Stagiaire("YAHIAOUI","Marwan","92","ATOD 15 CP",2012);
//		FichierATraiter.convertirBonFormat(s2);		
//		
//		Stagiaire s3 =  new Stagiaire("ZBERRO","Maxime","78","AI 96",2015);	
//		FichierATraiter.convertirBonFormat(s3);
//		
//		Stagiaire s4 =  new Stagiaire("ZAMOUN","Mourad","93","ATOD 7",2009);	
//		FichierATraiter.convertirBonFormat(s4);
		
//		Stagiaire s5 =  new Stagiaire("ZOUAOUI","Faouzi","75","ATOD 10",2010);	
//		FichierATraiter.convertirBonFormat(s5);
		
//		Stagiaire s6 =  new Stagiaire("ZAKA","Naoual","92","AI 73",2008);	
//		FichierATraiter.convertirBonFormat(s6);		

//		Stagiaire s7 =  new Stagiaire("ZAMI","Samuel","95","AI 63",2004);	
//		FichierATraiter.convertirBonFormat(s7);		

	Stagiaire s8 =  new Stagiaire("YOUYOUTTE","Jonathan","78","AI 78",2010);	
	FichierATraiter.convertirBonFormat(s8);	
		
//		index=FichierATraiter.ecrire1BlocDsFichierBinaire(s1);	
//		FichierATraiter.rechercheParentDsAB(0, s1, index);	
		
		FichierATraiter.supprimer(0, s8, 0);
		

		FichierATraiter.afficherInfixe(0);		
		
		
//		System.out.println("apres convertirBonFormat() "+s1.getNom()+" "+s1.getPrenom()+" DEp " +s1.getCodeDepartement()+ " Prom "+s1.getPromo()+" date "+s1.getDateEntree() );		

//		System.out.println((lire1BlocDsFB(1365).getNom()+lire1BlocDsFB(1365).getPrenom()).compareTo((stagiaire.getNom()+stagiaire.getPrenom())));

	//	System.out.println(" taille de la liste observable "+listeObserStag.size());
		

	}
	
	
	
}
