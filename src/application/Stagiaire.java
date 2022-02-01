package application;

public class Stagiaire extends Personne {

	// déclaration des attributs
	private String codeDepartement;
	private String typePromo;
	private String dateEntree;
	protected int nbStagiaires;
	
	// déclaration des constructeurs
	public Stagiaire() {
	}

	public Stagiaire(String nom,String prenom, String codeDepartement, String typePromo,String dateEntree) {
		super(nom,prenom);
		this.codeDepartement=codeDepartement;
		this.typePromo=typePromo;
	
		this.dateEntree=dateEntree;
	//	this.nbStagiaires++;
	
		
	}

	
	//  getters et setters

	public String getCodeDepartement() {
		return codeDepartement;
	}

	public void setCodeDepartement(String codeDepartement) {
		this.codeDepartement = codeDepartement;
	}

	public String getTypePromo() {
		return typePromo;
	}

	public void setTypePromo(String typePromo) {
		this.typePromo = typePromo;
	}





	@Override
	public String toString() {
		return "\n"+"Nom=" + nom + ", Prenom=" + prenom+"codeDepartement=" + codeDepartement + ", typePromo=" + typePromo + ", dateEntree="
				+ dateEntree + "" ; //+ ", getNom()="
			//	+ getNom() + ", getPrenom()=" + getPrenom() + ", getClass()=" + getClnbStagiairesass() + ", hashCode()="
			//	+ hashCode() + ", toString()=" + super.toString() + "]";
	}

	public String getDateEntree() {
		return dateEntree;
	}

	public void setDateEntree(String dateEntree) {
		this.dateEntree = dateEntree;
	}
	
/*	public int getnbStagiaires() {
	return nbStagiaires;
	}*/
}
