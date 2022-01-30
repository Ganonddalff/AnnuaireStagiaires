package application;

import java.util.List;
import java.util.Vector;

public class YSLanceur {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Noeud testNoeud;
		
		Stagiaire s1 = new Stagiaire ("AA","A",93,"AI 86",2012);
		Stagiaire s2 = new Stagiaire ("MM","M",93,"AI 78",2012);	
		Stagiaire s3 = new Stagiaire ("Senic","Yann",78,"CDA 15",2021);
		Stagiaire s4 = new Stagiaire ("BB","B",93,"AI 86",2012);
		Stagiaire sASUpprimer = new Stagiaire ("de L'ESCALOPIER","Agnès",45,"AI 61",2003);
		
		Arbre arbreStagiaire =new Arbre();
		
		FichierATraiter fichier = new FichierATraiter("./src/application/data/STAGIAIRES.DON");
		List<Stagiaire> listestagiaires = new Vector<Stagiaire>();
		listestagiaires=fichier.fabriqueChaine();
		



	

			if (arbreStagiaire.getRacine() == null) {

				arbreStagiaire.setRacine(new Noeud(listestagiaires.get(0)));
		}
		

		for (int i=1; i<listestagiaires.size();i++){	
			arbreStagiaire.ajoutNoeud(arbreStagiaire.getRacine(),listestagiaires.get(i));
		}
			
		
		arbreStagiaire.afficherInfixe(arbreStagiaire.getRacine());
		
		arbreStagiaire.supprimer(sASUpprimer,arbreStagiaire.getRacine());
		System.out.println("\n*********\n");
		
		arbreStagiaire.afficherInfixe(arbreStagiaire.getRacine());
		
	}
}
