package application;

public class Noeud  {
	// déclaration des attributs
	String itemStagiaire;
	int indexDroit ;
	int indexGauche;
	int indexDoublon;
	
	
	// déclaration des constructeurs
	public Noeud() {
		this.itemStagiaire="";
		this.indexDroit=-1;
		this.indexGauche=-1;
		this.indexDoublon=-1;
	}
	// constructeur pour une feuille
	public Noeud(String itemStagiaire) {
		this.itemStagiaire=itemStagiaire;
		this.indexDroit=-1;
		this.indexGauche=-1;
		this.indexDoublon=-1;
	}
	
	public Noeud(String itemStagiaire,int indexDroit,int indexGauche,int indexDoublon) {
		this.itemStagiaire=itemStagiaire;
		this.indexDroit=indexDroit;
		this.indexGauche=indexGauche;
		
	}
	//  getters et setters

	public String getItemStagiaire() {
		return itemStagiaire;
	}

	public void setStagiaire(String itemStagiaire) {
		this.itemStagiaire = itemStagiaire;
	}

	public int getIndexDroit() {
		return indexDroit;
	}

	public void setIndexDroit(int indexDroit) {
		this.indexDroit = indexDroit;
	}

	public int getIndexGauche() {
		return indexGauche;
	}

	public void setIndexGauche(int indexGauche) {
		this.indexGauche = indexGauche;
	}
	

	public int getIndexDoublon() {
		return indexDoublon;
	}

	public void setindexDoublon(int indexDoublon) {
		this.indexDoublon = indexDoublon;
	}
	
	
	
	
}
