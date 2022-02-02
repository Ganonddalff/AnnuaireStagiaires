package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.Vector;

public class FichierATraiter {
	private BufferedReader bfr;

	// Constructeurs
	public FichierATraiter() {
		bfr = null;
	}

	public FichierATraiter(String nomFichier) {
		try {
			// Création d'un flux pour le fichier texte
			// le nom du fichier est passé en argument
			FileReader in = new FileReader(nomFichier);
			bfr = new BufferedReader(in);
		} catch (IOException e) {
			System.out.println("Pb entrée sortie :" + e.getMessage());
		}
	}

	// decompose le fichier txt en objets Stagiaire qui sont stockés dans une list
	public List<Stagiaire> fabriqueChaine() {
		Stagiaire stagiaire;
		String nom;
		String prenom;
		String codeDepartement;
		String promo;
		int dateEntree;
		List<Stagiaire> listestagiaires = new Vector<Stagiaire>();
		int compteur = 0; // compteur pour déterminer le nombre de stagiaires présents dans le fichier txt
		int longMaxNom = 0; // variable pour calculer le plus long nom pour permettre de définir le nombre
							// de caracteres définissant le nom dans le fichier binaire
		int longMaxPrenom = 0;
		int longMaxPromo = 0;

		// try catch pour le bufferReader
		try {
			// tant que le
			do {
				nom = bfr.readLine();

				if (nom != null) {
					// boucle pour completer le nom du stagiaire pour qu'il fasse 25 caracteres
					if (nom.length() > longMaxNom) {
						longMaxNom = nom.length();
					}
					while (nom.length() < 25) {
						nom += " ";
					}

					prenom = bfr.readLine();
					// boucle pour completer le prenom du stagiaire pour qu'il fasse 25 caracteres
					if (prenom.length() > longMaxPrenom) {
						longMaxPrenom = prenom.length();
					}

					while (prenom.length() < 25) {
						prenom += " ";
					}
					;
					codeDepartement = null;
					codeDepartement = bfr.readLine();
					if (codeDepartement == "") {
						codeDepartement = "00";
					}

					promo = bfr.readLine();
					if (promo.length() > longMaxPromo) {
						longMaxPromo = promo.length();
					}

					while (promo.length() < 15) {
						promo += " ";
					}

					try {
						dateEntree = Integer.parseInt(bfr.readLine());
					} catch (NumberFormatException e) {
						dateEntree = 0;
						System.out.println(" y a un pb sur dateEntree de " + nom + " " + prenom);
					}

					compteur++;

					// certains stagiaires n'ont pas de "numero" codeDepartement dans le fichier
					// stagiaire.don
					// ce qui decalait les informations dans le fichier binaire. J'ai testé
					// diffrentes conditions dans le if
					// pour recuperer les absences de departement (seule la derniere fonctionne).
					// le nouveau numero attribué est YS à revoir.. pk pas "00" ?
					if (codeDepartement == null || codeDepartement == "" || codeDepartement == "\r"
							|| codeDepartement == " " || codeDepartement == "\n" || codeDepartement.length() < 2) {
						codeDepartement = "YS";
					}

					stagiaire = new Stagiaire(nom, prenom, codeDepartement, promo, dateEntree);
					listestagiaires.add(stagiaire);
					// lit la ligne "*" qui n'est pas pris en compte dans l'objet stagiaire et passe
					// à la suivante donc
					// inutile dans la mettre dans une variable
					bfr.readLine();

				}
			} while (nom != null);
			System.out.println(" nbre de caractere max pour nom est de " + longMaxNom + " pour prenom " + longMaxPrenom
					+ " pour promo " + longMaxPromo);
			bfr.close();
		} catch (IOException e) {
			System.out.println("Problème de lecture : " + e.getMessage());

		}
		System.out.println(compteur);
		return listestagiaires;
	}

//ecrit le bloc stagiaire - comprenant les attributs de la classe Stagiaire et les indexEnfantGauche indexEnfantDroit et indexEnfantdoublon 
//dans le fichier binaire - à la fin (derniere position) de ce fichier
//cette methode retourne l'index du bloc qui vient d'etre ajouter dans le fichier
//cet index sera transmis a la methode  XXX pour modifier l'indexEnfantYYY du bloc parent   

	public static int ecrire1BlocDsFichierBinaire(Stagiaire stagiaire) {
		int longueurFichier = 0;
		int indexCurrent = 0;
		try {
			RandomAccessFile raf = new RandomAccessFile("src/application/data/fichier.bin", "rw");
			// determiner la longueur du fichierpour positionner le curseur juste apres le
			// dernier pour y ajouter le nouveau
			// bloc
			longueurFichier = (int) raf.length();
			raf.seek(longueurFichier);
			raf.writeChars(
					stagiaire.getNom() + stagiaire.getPrenom() + stagiaire.getCodeDepartement() + stagiaire.getPromo());
			raf.writeInt(stagiaire.getDateEntree());
			raf.writeInt(-1);// IndexEnfantGauche
			raf.writeInt(-1);// IndexEnfantDroit
			raf.writeInt(-1);// indexEnfantDoublon

			raf.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// longueur du fichier en octets divisé par le nbre d'octets d'un bloc pour
		// déterminer l'index du bloc
		indexCurrent = longueurFichier / 150;
		return indexCurrent;

	}// ecrire

	// cette methode ne sert que pour les tests. elle affiche le contenu de tt un
	// bloc dans la console java
	// en fonction de l'index du bloc

	public static String lire1BlocDsFichierBinaire(int index) {
		String lecture = "";
		try {
			RandomAccessFile raf = new RandomAccessFile("src/application/data/fichier.bin", "rw");

			raf.seek(index * 150);

			for (int i = 0; i < 67; i++) {
				lecture += raf.readChar();
			}

			for (int i = 0; i < 4; i++) {
				lecture += String.valueOf(raf.readInt());
			}
			// System.out.println(lecture +" "+raf.length());

			raf.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lecture;

	}// lire

	// methode pour lire le contenu du fichier binaire et le transformer en objet
	// stagiaire
	public static Stagiaire lire1BlocDsFB(int index) {
		Stagiaire stagiaire = null;
		String nom = "";
		String prenom = "";
		String codeDepartement = "";
		String promo = "";
		int dateEntree = 0;

		try {
			RandomAccessFile raf = new RandomAccessFile("src/application/data/fichier.bin", "rw");

			raf.seek(index * 150);

			for (int i = 0; i < 25; i++) {
				nom += raf.readChar();
			}
			for (int i = 25; i < 50; i++) {
				prenom += raf.readChar();
			}
			for (int i = 50; i < 52; i++) {
				codeDepartement += raf.readChar();
			}
			for (int i = 52; i < 67; i++) {
				promo += raf.readChar();
			}

			for (int i = 0; i < 2; i++) {
				dateEntree += raf.readInt();
			}

			// System.out.println(lecture +" "+raf.length());

			raf.close();

			stagiaire = new Stagiaire(nom, prenom, codeDepartement, promo, dateEntree);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stagiaire;

	}// lire

	// permet de modifier l'indexEnfantGauche d'un bloc stagiaire a partir de
	// l'index du bloc stagiaire enfant
	public static void ecrireIndexEnfantGaucheDsParent(int indexParent, int indexEnfant) {

		try {
			RandomAccessFile raf = new RandomAccessFile("src/application/data/fichier.bin", "rw");
			// on positionne le curseur
			raf.seek(indexParent * 150 + 138);
			raf.writeInt(indexEnfant);// IndexEnfantGauche

			raf.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void ecrireIndexEnfantDroitDsParent(int indexParent, int indexEnfant) {
		try {
			RandomAccessFile raf = new RandomAccessFile("src/application/data/fichier.bin", "rw");

			raf.seek(indexParent * 150 + 142);
			raf.writeInt(indexEnfant);// IndexEnfantDroit
			raf.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void ecrireIndexEnfantDoublonDsParent(int indexParent, int indexEnfant) {
		try {
			RandomAccessFile raf = new RandomAccessFile("src/application/data/fichier.bin", "rw");

			raf.seek(indexParent * 150 + 146);
			raf.writeInt(indexEnfant);// IndexDoublon
			raf.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static int lire1IndexEnfantGauche(int index) {
		int IndexEnfantGauche = 0;
		try {
			RandomAccessFile raf = new RandomAccessFile("src/application/data/fichier.bin", "rw");
			raf.seek(index * 150 + 138);
			IndexEnfantGauche = raf.readInt();
			raf.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return IndexEnfantGauche;

	}// lire

	public static int lire1IndexEnfantDroit(int index) {
		int IndexEnfantDroit = 0;
		try {
			RandomAccessFile raf = new RandomAccessFile("src/application/data/fichier.bin", "rw");
			raf.seek(index * 150 + 142);
			IndexEnfantDroit = raf.readInt();
			raf.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return IndexEnfantDroit;

	}// lire

	public static int lire1IndexEnfantDoublon(int index) {
		int IndexEnfantDoublon = 0;
		try {
			RandomAccessFile raf = new RandomAccessFile("src/application/data/fichier.bin", "rw");
			raf.seek(index * 150 + 146);
			IndexEnfantDoublon = raf.readInt();
			raf.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return IndexEnfantDoublon;

	}// lire

	// methode pour trouver le parent du stagaire afin de placer son index et donc
	// de contruire l'arbre
	public static void rechercheParentDsAB(int indexCurrent, Stagiaire stagiaire, int indexEnfant) {

		// on teste si on doit partir sur la gauche

		if (lire1BlocDsFB(indexCurrent).getNom().compareTo(stagiaire.getNom()) > 0) {

			if (lire1IndexEnfantGauche(indexCurrent) == -1) {// Est ce que la place à gauche est libre ?
				// si oui on change l' indicateur indexGauche
				ecrireIndexEnfantGaucheDsParent(indexCurrent, indexEnfant);
			} else {// sinon je fais l'appel récursif et on se deplace sur l'enfant qui est à gauche
				rechercheParentDsAB(lire1IndexEnfantGauche(indexCurrent), stagiaire, indexEnfant);
			}

		}

		// sinon je fais l'appel récursif

		// on teste si on doit partir sur la droite
		if (lire1BlocDsFB(indexCurrent).getNom().compareTo(stagiaire.getNom()) < 0) {
			// Est ce que la place à droite est libre ?
			if (lire1IndexEnfantDroit(indexCurrent) == -1) {
				ecrireIndexEnfantDroitDsParent(indexCurrent, indexEnfant);
			} else {
				rechercheParentDsAB(lire1IndexEnfantDroit(indexCurrent), stagiaire, indexEnfant);
			}
		}

		// on teste si on doit partir sur vers les doublons
		if (lire1BlocDsFB(indexCurrent).getNom().compareTo(stagiaire.getNom()) == 0) {
			// Est ce que la place coté doublon est libre ?
			if (lire1IndexEnfantDoublon(indexCurrent) == -1) {
				// si oui on change l' indicateur indexDoublon
				ecrireIndexEnfantDoublonDsParent(indexCurrent, indexEnfant);
			} else {// sinon je fais l'appel récursif et on se deplace sur l'enfant qui est à gauche
				rechercheParentDsAB(lire1IndexEnfantDoublon(indexCurrent), stagiaire, indexEnfant);
			}

		}

	}

//	public static void afficherInfixe(int indexCurrent) {
//		if (lire1IndexEnfantGauche(indexCurrent) != -1) {
//			afficherInfixe(lire1IndexEnfantGauche(lire1IndexEnfantGauche(indexCurrent)));
//		}
//		System.out.println(" le nom " + lire1BlocDsFB(indexCurrent).getNom());
//		if (lire1IndexEnfantDroit(indexCurrent) != -1) {
//			afficherInfixe(lire1IndexEnfantDroit(lire1IndexEnfantDroit(indexCurrent)));
//		}
//
//	}
}

// A toute personne qui est arrivé jusqu'ici merci d'avoir pris le temps de parcourir toutes ces lignes de java