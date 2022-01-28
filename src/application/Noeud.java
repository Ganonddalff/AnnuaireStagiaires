package application;

public class Noeud {
	// déclaration des attributs
	Stagiaire stagiaire =new Stagiaire();
	Noeud noeudDroit = new Noeud();
	Noeud noeudGauche = new Noeud();
	
	// déclaration des constructeurs
	public Noeud() {
	}
	// constructeur pour une feuille
	public Noeud(Stagiaire stagiaire) {
		this.stagiaire=stagiaire;
		this.noeudDroit=null;
		this.noeudGauche=null;
	}
	
	public Noeud(Stagiaire stagiaire,Noeud noeudDroit,Noeud noeudGauche) {
		this.stagiaire=stagiaire;
		this.noeudDroit=noeudDroit;
		this.noeudGauche=noeudGauche;
		
	}
	//  getters et setters

	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	public Noeud getNoeudDroit() {
		return noeudDroit;
	}

	public void setNoeudDroit(Noeud noeudDroit) {
		this.noeudDroit = noeudDroit;
	}

	public Noeud getNoeudGauche() {
		return noeudGauche;
	}

	public void setNoeudGauche(Noeud noeudGauche) {
		this.noeudGauche = noeudGauche;
	}
	

	
	
	
	
}
