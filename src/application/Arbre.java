package application;



public class Arbre {

	// déclaration des attributs
	private Noeud racine;
	
	// déclaration des constructeurs

	public Arbre() {
		this.racine=null;
	}

	//  getters et setters
	
	public Noeud getRacine() {
		return racine;
	}
	public void setRacine(Noeud racine) {
		this.racine=racine;
	}

	
	public Noeud ajoutNoeud(Noeud current, Stagiaire stagiaire){
		if (current == null) {
			
			return new Noeud(stagiaire);
		}
		if (current.getStagiaire().compareTo(stagiaire)>0) {
			current.setNoeudGauche(ajoutNoeud(current.getNoeudGauche(),stagiaire));
			
		}else{
			current.setNoeudDroit(ajoutNoeud(current.getNoeudDroit(),stagiaire));
		}
		
		return current;
	}
	
	//affichage via le parcours infixe
	// infixe : gauche noeud droit
	
	public void afficherInfixe(Noeud current) {
		if (current.getNoeudGauche() != null) {
			afficherInfixe(current.getNoeudGauche());
		}
		System.out.println(current.getStagiaire().getNom()+" "+current.getStagiaire().getPrenom());
		if (current.getNoeudDroit() != null) {
			afficherInfixe(current.getNoeudDroit());
		}

	}
	

	
	//supression d un noeud
	//chercher le noeud a supprimer
	//qd trouver  je le supprime
	public Noeud supprimer(Stagiaire stagiaireSupr, Noeud current) {
		if (current == null) {
			//pas trouve le noeud a suprimer fin de recursivite
			return current;
		}
		if (stagiaireSupr.compareTo(current.getStagiaire()) == 0) {
			// trouver le noeud a supp
			return supprimerNoeud(current);
		}

		if (stagiaireSupr.compareTo(current.getStagiaire()) <0) {
			current.setNoeudGauche(supprimer(stagiaireSupr, current.getNoeudGauche()));
		}else {
			current.setNoeudDroit(supprimer(stagiaireSupr, current.getNoeudDroit()));
		}
		return current;
	}
	
	
	private Noeud supprimerNoeud(Noeud current) {
		// si noeud a supprimer est une feuille
		if (current.getNoeudDroit() == null && current.getNoeudGauche() == null) {
			return null;
		} else if (current.getNoeudDroit() == null) {
			// pas de fils droit  mais un gauche qui doit prendre sa place
			return current.getNoeudGauche();
		} else if(current.getNoeudGauche() == null) {
			// pas de fils gauche mais un droit qui doit prendre sa place
			return current.getNoeudDroit();
		}else {
			//noeud avec un fils gauche et droit
			// on doit prendre le plus petit (a gauche du sous arbre a droit du noeud a supprimer
			//je vais chercher le dernier descendant (le + petit du sous arbre droit)
			Noeud remplacant = dernierDescendant(current.getNoeudDroit());
			//je remplace la cle du noeud a supprimer par la valeur qui le remplace
			current.setStagiaire(remplacant.getStagiaire());
			//je me mets dans le sous arbredroit et je supprime le noeud remplacant
			current.setNoeudDroit(supprimer(remplacant.getStagiaire(),current.getNoeudDroit()));
		}
		return current;//
	}
	
	
	
	
	private Noeud dernierDescendant(Noeud current) {
		if (current.getNoeudGauche() == null) {
			return current;
		}else {
			return dernierDescendant(current.getNoeudGauche());
		}
		

	}
	
	
	
}
