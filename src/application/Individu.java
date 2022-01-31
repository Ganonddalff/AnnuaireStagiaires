package application;

public class Individu {
	
	public static final int NB_MAX_STAGIAIRES=2000;
	private Stagiaire[]lesStagiaires;
	private int nbStagiaires;


	
	public Individu() {
		lesStagiaires = new Stagiaire[NB_MAX_STAGIAIRES];
	}

	public void ajouterStagiaire(Stagiaire stagiaire) {
		if (nbStagiaires<NB_MAX_STAGIAIRES) {
			lesStagiaires[nbStagiaires]=stagiaire;
			nbStagiaires++;
		}
	}
	
	public Stagiaire[] getLesStagiaires() {
		return lesStagiaires;
	}
}
