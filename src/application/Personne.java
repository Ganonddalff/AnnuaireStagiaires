package application;

public abstract class Personne {

	// déclaration des attributs
	protected String nom;
	protected String prenom;
	
	// déclaration des constructeurs
	
	public Personne() {
	}
	
	
	public Personne(String nom,String prenom) {
		this.nom=nom;
		this.prenom=prenom;
	}
	
	//  getters et setters
	public String  getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom=nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom=prenom;
	}
}
