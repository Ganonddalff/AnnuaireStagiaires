package application;

public class Stagiaire extends Personne implements Comparable<Stagiaire>{

	// déclaration des attributs
	private String codeDepartement;
	private String promo;
	private int dateEntree;
	
	// déclaration des constructeurs
	public Stagiaire() {
	}

	public Stagiaire(String nom,String prenom, String codeDepartement, String promo,int dateEntree) {
		super(nom,prenom);
		this.codeDepartement=codeDepartement;
		this.promo=promo;
		this.dateEntree=dateEntree;
		
	}

	
	//  getters et setters

	public String getCodeDepartement() {
		return codeDepartement;
	}

	public void setCodeDepartement(String codeDepartement) {
		this.codeDepartement = codeDepartement;
	}

	public String getPromo() {
		return promo;
	}

	public void setPromo(String promo) {
		this.promo = promo;
	}

		public int getDateEntree() {
		return dateEntree;
	}

	public void setDateEntree(int dateEntree) {
		this.dateEntree = dateEntree;
	}

	

	@Override
	public int compareTo(Stagiaire stagiaire) {
		int i = this.nom.compareTo(stagiaire.getNom());
		if ( i == 0) {
			i = this.prenom.compareTo(stagiaire.getPrenom());
			if (i == 0) {
				i = this.promo.compareTo(stagiaire.getPromo());
			}
		}
		
		return i;
	}
}
