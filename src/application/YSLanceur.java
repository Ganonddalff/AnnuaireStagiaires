package application;


import java.io.File;
import java.util.List;
import java.util.Vector;

public class YSLanceur {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Noeud testNoeud;
//		
//		Stagiaire s1 = new Stagiaire ("AA","A","93","AI 86",2012);
//		Stagiaire s2 = new Stagiaire ("MM","M","93","AI 78",2012);	
//		Stagiaire s3 = new Stagiaire ("Senic","Yann","78","CDA 15",2021);
//		Stagiaire s4 = new Stagiaire ("BB","B","93","AI 86",2012);
//		Stagiaire sASUpprimer = new Stagiaire ("de L'ESCALOPIER","Agnès","45","AI 61",2003);
//		
//		Arbre arbreStagiaire =new Arbre();
		
		FichierATraiter fichier = new FichierATraiter("./src/application/data/STAGIAIRES.DON");
		List<Stagiaire> listestagiaires = new Vector<Stagiaire>();
		listestagiaires=fichier.fabriqueChaine();
		

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
		
		FichierATraiter.ecrire1BlocDsFichierBinaire(listestagiaires.get(0),0);
		
		System.out.println(FichierATraiter.lire1BlocDsFichierBinaire(0));
		FichierATraiter.ecrireIndexEnfantGaucheDsParent(0, 3);
		System.out.println(FichierATraiter.lire1BlocDsFichierBinaire(0));
		
		FichierATraiter.ecrireIndexEnfantDroitDsParent(0, 4);
		System.out.println(FichierATraiter.lire1BlocDsFichierBinaire(0));
		FichierATraiter.ecrireIndexEnfantDoublonDsParent(0, 6);
		System.out.println(FichierATraiter.lire1BlocDsFichierBinaire(0));
		
		
		System.out.println("\n index Gauche de 0 "+ FichierATraiter.lire1IndexEnfantGauche(0));
		System.out.println("\n index Droit de 0 "+ FichierATraiter.lire1IndexEnfantDroit(0));
		System.out.println("\n index doublon de 0 "+ FichierATraiter.lire1IndexEnfantDoublon(0));
		
		
		
	}
}
