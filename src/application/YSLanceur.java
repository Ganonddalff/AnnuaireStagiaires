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
		//ObservableList<Stagiaire> listeObserStag = FXCollections.observableArrayList(); 

		File file =new File("src/application/data/fichier.bin");
		file.delete();
		
//for (Stagiaire stagiaire :  listestagiaires) {
//	
//	System.out.println(" List<Stagiaire> 1 "+stagiaire.getNom()+ " "+ stagiaire.getPrenom()+" "+ stagiaire.getDateEntree() );
//}
//for (int i=listestagiaires.size()-1;i>=0;i-- ) {		
//System.out.println(" List<Stagiaire> "+listestagiaires.get(i).getNom()+ " "+ listestagiaires.get(i).getPrenom()+" "+ listestagiaires.get(i).getDateEntree() );	
//}
		
	//*********************	
		// on cree la racine du fichier

		index = FichierATraiter.ecrire1BlocDsFichierBinaire(listestagiaires.get(0));
//		listeObserStag.add(listestagiaires.get(0));
//		System.out.println(" ***  "+" "+FichierATraiter.lire1BlocDsFichierBinaire(0));

		//on ajoute chaque stagiaire dans le fichier binaire et on cree le lien avec son parent dans la structure ABR
		for(int i=1; i<listestagiaires.size(); i++ ) {
			index=FichierATraiter.ecrire1BlocDsFichierBinaire(listestagiaires.get(i));	
			FichierATraiter.rechercheParentDsAB(0,listestagiaires.get(i),index);
//			listeObserStag.add(listestagiaires.get(i));
		}

		//*********************	
//		int i =0;
		
//		for (int i=0;i<10;i++) {
//		System.out.println(" List<Stagiaire> "+listestagiaires.get(i).getNom()+ " "+ listestagiaires.get(i).getPrenom()+" "+ listestagiaires.get(i).getDateEntree()+"*" );
//		System.out.println(" lirebloc>       "+FichierATraiter.lire1BlocDsFB(i).getNom()+ " "+ FichierATraiter.lire1BlocDsFB(i).getPrenom()+" "+ FichierATraiter.lire1BlocDsFB(i).getDateEntree() );
//		}

		
//		FichierATraiter.afficherInfixe(0);
		
//		//compareTo Stagiaire()
//		 System.out.println("\n*****\n");
//		 Stagiaire s1 =  new Stagiaire("nom","pren","dep","prom",2941);
//		 Stagiaire s2 =  new Stagiaire("nom","pren","dep","prom",1941);
//		 Stagiaire s3 =  new Stagiaire("nom","pren","dep","prom",1943);
//		 Stagiaire s4 =  new Stagiaire("nom","pren","dep","prom4",1941);
//		 Stagiaire s5 =  new Stagiaire("nom","pren","dep5","prom",1941);
//		 Stagiaire s6 =  new Stagiaire("nom","pren6","dep","prom",1941);		
//		 Stagiaire s7 =  new Stagiaire("nom7","pren","dep","prom",1941);			
//		 System.out.println(" s1 a s2 " +s1.compareTo(s2));
//		 System.out.println(" s1 a s3 " +s1.compareTo(s3));	
//		 System.out.println(" s1 a s4 " +s1.compareTo(s4));		
//		 System.out.println(" s1 a s5 " +s1.compareTo(s5));
//		 System.out.println(" s1 a s6 " +s1.compareTo(s6));	
//		 System.out.println(" s1 a s7 " +s1.compareTo(s7));				
		


	}
}