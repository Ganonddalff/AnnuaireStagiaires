package application;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

public class Individu {
	
	public static final int NB_MAX_STAGIAIRES=1365;
	protected Stagiaire[]lesStagiaires;
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
	
	public void write(PrintStream output) {
		write(output);
		
	}
}
