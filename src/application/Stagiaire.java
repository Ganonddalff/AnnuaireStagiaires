package application;

public class Stagiaire extends Personne implements Comparable<Stagiaire>{

	// déclaration des attributs
	private int codeDepartement;
	private String typePromo;
	private int numeroPromo;
	private int dateEntree;
	
	// déclaration des constructeurs
	public Stagiaire() {
	}

	public Stagiaire(String nom,String prenom, int codeDepartement, String typePromo,int numeroPromo,int dateEntree) {
		super(nom,prenom);
		this.codeDepartement=codeDepartement;
		this.typePromo=typePromo;
		this.numeroPromo=numeroPromo;
		this.dateEntree=dateEntree;
		
	}

	
	//  getters et setters

	public int getCodeDepartement() {
		return codeDepartement;
	}

	public void setCodeDepartement(int codeDepartement) {
		this.codeDepartement = codeDepartement;
	}

	public String getTypePromo() {
		return typePromo;
	}

	public void setTypePromo(String typePromo) {
		this.typePromo = typePromo;
	}

	public int getNumeroPromo() {
		return numeroPromo;
	}

	public void setNumeroPromo(int numeroPromo) {
		this.numeroPromo = numeroPromo;
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
				i = this.typePromo.compareTo(stagiaire.getTypePromo());
			}
		}
		
		return i;
	}
}
