package application;



import javafx.application.Application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.scene.Scene;
import javafx.stage.Stage;

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
	
	// *******************************************************************
	// decompose le fichier txt en objets Stagiaire qui sont stockés dans une list
	//et  on concatene par des espaces  les chaines string Nom, Prenom, promo  pour avoir respectivement la meme longueur de chaine
	// 25   25  15 caracteres
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
					// les noms sont tous mis en majuscule
					nom=nom.toUpperCase();
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
//					System.out.println("fabriqueChaine()  nom "+ nom+" date "+dateEntree);
					compteur++;

					// certains stagiaires n'ont pas de "numero" codeDepartement dans le fichier
					// stagiaire.don
					// ce qui decalait les informations dans le fichier binaire. J'ai testé
					// diffrentes conditions dans le if
					// pour recuperer les absences de departement (seule la derniere fonctionne).
					// le nouveau numero attribué est ** à revoir.. pk pas "00" ?
					if (codeDepartement == null || codeDepartement == "" || codeDepartement == "\r"
							|| codeDepartement == " " || codeDepartement == "\n" || codeDepartement.length() < 2) {
						codeDepartement = "**";
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
	
// *******************************************************************
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
			
			// controle si chaque attribut a le bon nbre de caracteres sinon il complete par des espaces
			String nom=stagiaire.getNom();
			while (nom.length() < 25) {
				nom += " ";
			}
			stagiaire.setNom(nom);
			
			//****
			String prenom=stagiaire.getPrenom();
			while (prenom.length() < 25) {
				prenom += " ";
			}
			stagiaire.setPrenom(prenom);
			
			//****
			// on ajoute ici une asterisque si il manque un digit
			String codeDepartement=stagiaire.getCodeDepartement();
			while (codeDepartement.length() < 2) {
				codeDepartement += "*";
			}
			stagiaire.setCodeDepartement(codeDepartement);
			
			//****
			String promo=stagiaire.getPromo();
			while (promo.length() < 15) {
				promo += " ";
			}
			stagiaire.setPromo(promo);
			System.out.println("ecrire1BlocDsFichierBinaire() "+stagiaire.getNom()+" "+stagiaire.getPrenom()+" DEp " +stagiaire.getCodeDepartement()+ " Prom "+promo+" date "+stagiaire.getDateEntree() );
			//****
			raf.writeChars(stagiaire.getNom() + stagiaire.getPrenom() + stagiaire.getCodeDepartement() + stagiaire.getPromo());
			raf.writeInt(stagiaire.getDateEntree());
			raf.writeInt(-1);// IndexEnfantGauche
			raf.writeInt(-1);// IndexEnfantDroit
			raf.writeInt(-1);// indexEnfantDoublon

			raf.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		// longueur du fichier en octets divisé par le nbre d'octets d'un bloc pour
		// déterminer l'index du bloc
		indexCurrent = longueurFichier / 150;
		return indexCurrent;

	}// ecrire
	
	
	// *******************************************************************
	// cette methode ne sert que pour les tests. elle affiche le contenu de tt un
	// bloc dans la console java
	// en fonction de l'index du bloc

//	public static String lire1BlocDsFichierBinaire(int index) {
//		String lecture = "";
//		try {
//			RandomAccessFile raf = new RandomAccessFile("src/application/data/fichier.bin", "rw");
//
//			raf.seek(index * 150);
//
//			for (int i = 0; i < 67; i++) {
//				lecture += raf.readChar();
//			}
//
//			for (int i = 0; i < 4; i++) {
//				lecture += String.valueOf(raf.readInt());
//			}
//			// System.out.println(lecture +" "+raf.length());
//
//			raf.close();
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return lecture;
//
//	}// lire
	
	// *******************************************************************
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

			dateEntree=raf.readInt();
		//	for (int i = 0; i < 2; i++) {
		//		dateEntree += raf.readInt();
		//	}

			// System.out.println(lecture +" "+raf.length());

			raf.close();

			stagiaire = new Stagiaire(nom, prenom, codeDepartement, promo, dateEntree);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stagiaire;

	}// lire
	
	// *******************************************************************
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
	// *******************************************************************
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
	
	// *******************************************************************
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
	// *******************************************************************
	public static int lire1IndexEnfantGauche(int index) {
		int IndexEnfantGauche = 0;
		try {
			RandomAccessFile raf = new RandomAccessFile("src/application/data/fichier.bin", "rw");
			raf.seek(index * 150 + 138);
			IndexEnfantGauche = raf.readInt();
			raf.close();

		} catch (IOException e) {

			e.printStackTrace();
		}
		return IndexEnfantGauche;

	}// lire
	// *******************************************************************
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
	
	
	// *******************************************************************
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
	
	// *******************************************************************
	// methode pour trouver le parent du stagaire afin de placer son index et donc
	// de contruire l'arbre
	public static void rechercheParentDsAB(int indexCurrent, Stagiaire stagiaire, int indexEnfant) {

		// on teste si on doit partir sur la gauche

		if ((lire1BlocDsFB(indexCurrent).getNom()+lire1BlocDsFB(indexCurrent).getPrenom()).compareTo((stagiaire.getNom()+stagiaire.getPrenom())) > 0) {

			if (lire1IndexEnfantGauche(indexCurrent) == -1) {// Est ce que la place à gauche est libre ?
				// si oui on change l' indicateur indexGauche
				ecrireIndexEnfantGaucheDsParent(indexCurrent, indexEnfant);
			} else {// sinon je fais l'appel récursif et on se deplace sur l'enfant qui est à gauche
				rechercheParentDsAB(lire1IndexEnfantGauche(indexCurrent), stagiaire, indexEnfant);
			}

		}

		// sinon je fais l'appel récursif

		// on teste si on doit partir sur la droite
		if ((lire1BlocDsFB(indexCurrent).getNom()+lire1BlocDsFB(indexCurrent).getPrenom()).compareTo((stagiaire.getNom()+stagiaire.getPrenom())) < 0) {
			// Est ce que la place à droite est libre ?
			if (lire1IndexEnfantDroit(indexCurrent) == -1) {
				ecrireIndexEnfantDroitDsParent(indexCurrent, indexEnfant);
			} else {
				rechercheParentDsAB(lire1IndexEnfantDroit(indexCurrent), stagiaire, indexEnfant);
			}
		}

		// on teste si on doit partir sur vers les doublons
		if ((lire1BlocDsFB(indexCurrent).getNom()+lire1BlocDsFB(indexCurrent).getPrenom()).compareTo((stagiaire.getNom()+stagiaire.getPrenom())) == 0) {
			// Est ce que la place coté doublon est libre ?
			if (lire1IndexEnfantDoublon(indexCurrent) == -1) {
				// si oui on change l' indicateur indexDoublon
				ecrireIndexEnfantDoublonDsParent(indexCurrent, indexEnfant);
			} else {// sinon je fais l'appel récursif et on se deplace sur l'enfant qui est à gauche
				rechercheParentDsAB(lire1IndexEnfantDoublon(indexCurrent), stagiaire, indexEnfant);
			}

		}

	}
	

	
	
	
	// *******************************************************************
	//methode pour trouver l'index d'un bloc stagiaire que l'on cherche a supprimer
	public static void supprimer( int indexCurrent, Stagiaire stagiaire,int indexParent) {


		
		System.out.println(lire1BlocDsFB(indexCurrent).getNom() +" "+stagiaire.getNom());
		
			// on compare l'objet stagiaire avec l objet du noeud  via une surchage de la méthode compareTo					
	 if (lire1BlocDsFB(indexCurrent).compareTo(stagiaire) == 0) {
			System.out.println(" la méthode supprimer() a trouvé le stagiaire "+lire1BlocDsFB(indexCurrent).getNom() +" à suppr son indexParent " +indexParent +"son IndexCurrent "+indexCurrent);
			supprimerStagiaire(indexParent, indexCurrent);
			// on teste si il est doublon cad si les  noms et prenoms sont identiques  
	 }else if ((lire1BlocDsFB(indexCurrent).getNom()+lire1BlocDsFB(indexCurrent).getPrenom()).compareTo((stagiaire.getNom()+stagiaire.getPrenom())) == 0) {
			// && (lire1IndexEnfantDoublon(indexCurrent) != -1)) inutile  
			supprimer(lire1IndexEnfantDoublon(indexCurrent),stagiaire,indexCurrent);
		 
		 
		}else if (lire1BlocDsFB(indexCurrent).compareTo(stagiaire) > 0) {

			supprimer(lire1IndexEnfantGauche(indexCurrent),stagiaire,indexCurrent);
			
		} else
		//if (lire1BlocDsFB(indexCurrent).getNom().compareTo(stagiaire.getNom()) < 0)
		{

			supprimer(lire1IndexEnfantDroit(indexCurrent),stagiaire,indexCurrent);
		}
		//si le stagiaire recherché n'est pas dans le fichier comment on sort de la methode  ????????????
		
		
		
	}
	
	//**********
	
	public static void supprimerStagiaire(int indexParent, int indexStagiaire) {
	
		System.out.println(" supprimerStagiaire() : stagiaire "+ lire1BlocDsFB(indexStagiaire).getNom() +"a  IndexEnfantGauche: "+ lire1IndexEnfantGauche(indexStagiaire) +" droit " +lire1IndexEnfantDroit(indexStagiaire));
	
		//cas du stagiaire a supprime qui a un doublon dans son indexEnfant
		if(lire1IndexEnfantDoublon(indexStagiaire) != -1) {
			// remplacer le stagiaire par son doublon dans IndexEnfant du parent
			if (lire1IndexEnfantGauche(indexParent) == indexStagiaire) {
				ecrireIndexEnfantGaucheDsParent(indexParent, lire1IndexEnfantDoublon(indexStagiaire));			
			}
			if (lire1IndexEnfantDroit(indexParent) == indexStagiaire) {
				ecrireIndexEnfantDroitDsParent(indexParent, lire1IndexEnfantDoublon(indexStagiaire));			
			}
			
			
			//mettre l IndexEnfantDroit et gauche du stagiaire dans celui du doublon
			

			ecrireIndexEnfantGaucheDsParent(lire1IndexEnfantDoublon(indexStagiaire), lire1IndexEnfantGauche(indexStagiaire)); 
			ecrireIndexEnfantDroitDsParent(lire1IndexEnfantDoublon(indexStagiaire), lire1IndexEnfantDroit(indexStagiaire)); 			
			
			
			
		// si le stagiaire n'a pas d'enfant, on modifie l'indexEnfantXXX (correspondant
		// au stagiaire) de son parent a -1
		} else if (lire1IndexEnfantGauche(indexStagiaire) == -1 && lire1IndexEnfantDroit(indexStagiaire) == -1) {
			if (lire1IndexEnfantGauche(indexParent) == indexStagiaire) {
				ecrireIndexEnfantGaucheDsParent(indexParent, -1);

			}
			if (lire1IndexEnfantDroit(indexParent) == indexStagiaire) {
				ecrireIndexEnfantDroitDsParent(indexParent, -1);
			}
			//rien a droite donc l'enfant gauche du stagiaire remplace le stagiaire dans l'IndexEnfantGauche du parent
		} else if (lire1IndexEnfantDroit(indexStagiaire) == -1) {
			// pour savoir si le stagiaire a supprimer se trouve dans l indexEnfantGauche ou droite  du parent
			// dans le but de remplacer la valeur de l indexEnfantxxx du parent (le stagiaire) par l index de l'enfant unique qui se trouve a gauche du stagiaire
			if (lire1IndexEnfantGauche(indexParent) == indexStagiaire) {
			ecrireIndexEnfantGaucheDsParent(indexParent, lire1IndexEnfantGauche(indexStagiaire));
			}else {
				ecrireIndexEnfantDroitDsParent(indexParent, lire1IndexEnfantGauche(indexStagiaire));

			}
		} else if (lire1IndexEnfantGauche(indexStagiaire) == -1) {
			// pour savoir si le stagiaire a supprimer se trouve dans l indexEnfantGauche ou droite  du parent
			// dans le but de remplacer la valeur de l indexEnfantxxx du parent (le stagiaire) par l index de l'enfant unique qui se trouve a droite du stagiaire
			if (lire1IndexEnfantGauche(indexParent) == indexStagiaire) {
			ecrireIndexEnfantGaucheDsParent(indexParent, lire1IndexEnfantDroit(indexStagiaire));
			}else {
				ecrireIndexEnfantDroitDsParent(indexParent, lire1IndexEnfantDroit(indexStagiaire));

				}
			
		//	ecrireIndexEnfantDroitDsParent(indexParent, lire1IndexEnfantDroit(indexStagiaire));
		}else {
			
			//Stagiaire a supprimé avec un enfant gauche et droit
			// on doit prendre le plus petit (cad le +gauche) du sous arbre qui se trouve a droite du noeud a supprimer
			//je vais chercher le dernier descendant (le + petit du sous arbre droit)
			int indexRemplacant =  dernierDescendant(lire1IndexEnfantDroit(indexStagiaire));
			//je remplace le stagiaire a supprimer par son remplacant en modifiant l'indexEnfantXXX du parent au stagiaire
			if (lire1IndexEnfantGauche(indexParent) == indexStagiaire) {
				ecrireIndexEnfantGaucheDsParent(indexParent, indexRemplacant);
				System.out.println(" supprimerStagiaire() ecrit dans le parent "+lire1BlocDsFB(indexParent).getNom()+" Gauche "+ indexRemplacant);
			}else {
				ecrireIndexEnfantDroitDsParent(indexParent, indexRemplacant);
				System.out.println(" supprimerStagiaire() ecrit dans le parent "+lire1BlocDsFB(indexParent).getNom()+" IndexEnfantDroit "+ indexRemplacant);
			}
			
			
			System.out.println(" dans "+lire1BlocDsFB(indexRemplacant).getNom()+"je mets a G "+lire1IndexEnfantGauche(indexStagiaire)+" D "+ lire1IndexEnfantDroit(indexStagiaire));
			
			supprimer(indexStagiaire,lire1BlocDsFB(indexRemplacant),0);
			// pourquoi  faut il mettre le ligne ci dessus avant les 2 lignes cidessous ?
			// je dois recuperer les indexEnfantDroit et gauche du stagiaire pour les mettre dans son remplacant
			ecrireIndexEnfantDroitDsParent(indexRemplacant,lire1IndexEnfantDroit(indexStagiaire));
			ecrireIndexEnfantGaucheDsParent(indexRemplacant,lire1IndexEnfantGauche(indexStagiaire));
			
		}
	}
		
	
	
	public static int dernierDescendant(int index) {
		// le dernierDescendant doit etre le plus petit du sousarbre ayant comme racine le noeud a supprimer 
		if ( lire1IndexEnfantGauche(index)== -1) {
			return index;
		}else {
			return dernierDescendant(lire1IndexEnfantGauche(index));
		}
	}
	
	
	//********************************************************************
	// pour lire dans l'ordre alphabetique  les stagiaires
	public static void afficherInfixe(int index) {
		if (lire1IndexEnfantDoublon(index) != -1) {
			afficherInfixe((lire1IndexEnfantDoublon(index)));
		}	
		if (lire1IndexEnfantGauche(index) != -1) {
			afficherInfixe((lire1IndexEnfantGauche(index)));
		}
		System.out.println("methode afficherInfixe() "+lire1BlocDsFB(index).getNom()+" "+ lire1BlocDsFB(index).getPrenom()+" "+ lire1BlocDsFB(index).getDateEntree()+" "+ lire1BlocDsFB(index).getPromo()+" "+ lire1BlocDsFB(index).getPrenom()+" "+" G "+ lire1IndexEnfantGauche(index)+" D "+lire1IndexEnfantDroit(index)+" DO "+lire1IndexEnfantDoublon(index)+" index "+ index);
		

		//while(Integer.toString(index).lenght()<5) {}

		if (lire1IndexEnfantDroit(index) != -1) {
			afficherInfixe((lire1IndexEnfantDroit(index)));
		}	
	}
	
	//***********************************************************************
	// pour lire les stagiaires (par ordre alphabetique) du fichier binaire et les ecrire dans une liste observable
 	public static void ecrireStagiaireFBDsListObs(int index,ObservableList<Stagiaire> listestagiaires) {
 		if (listestagiaires == null) {
 			listestagiaires= FXCollections.observableArrayList();
 		}
 		
		if (lire1IndexEnfantDoublon(index) != -1) {
			ecrireStagiaireFBDsListObs(lire1IndexEnfantDoublon(index),listestagiaires);
		}	
		if (lire1IndexEnfantGauche(index) != -1) {
			ecrireStagiaireFBDsListObs(lire1IndexEnfantGauche(index),listestagiaires);
		}
		
		
//		System.out.println(" Fucking "+lire1BlocDsFB(index).getNom()+" "+ lire1BlocDsFB(index).getPrenom()+" "+ lire1BlocDsFB(index).getDateEntree()+" "+ lire1BlocDsFB(index).getPromo()+" "+ lire1BlocDsFB(index).getPrenom()+" "+" G "+ lire1IndexEnfantGauche(index)+" D "+lire1IndexEnfantDroit(index)+" DO "+lire1IndexEnfantDoublon(index)+" index "+ index);		 

		Stagiaire stagiaire = new Stagiaire(lire1BlocDsFB(index).getNom(), lire1BlocDsFB(index).getPrenom(), lire1BlocDsFB(index).getCodeDepartement(), lire1BlocDsFB(index).getPromo(), lire1BlocDsFB(index).getDateEntree());
		listestagiaires.add(stagiaire);	
	
		if (lire1IndexEnfantDroit(index) != -1) {
			ecrireStagiaireFBDsListObs(lire1IndexEnfantDroit(index),listestagiaires);
		}	
	
	}
	
	
	//******************************************************
	// methode pour MAJ/modifier
	//si le nom n'est pas modifié il suffit de remplacer les attributs de stagiaire en conservant le meme index et les memes IndexEnfant
	//si le nom est modifié il faut supprimer le stagiaire present dans le fichier et l'ajouter comme une nouvelle entrée
	public static void majStagiaire(Stagiaire stagiaire, int indexStagiaire) {
		int index=0;
		
		//on teste si le nom du stagiaire a changé
		// le nom n'a pas changé donc on modifie directement dans le bloc stagiaire référencé par l'indexStagiaire
		if (stagiaire.getNom().compareTo(lire1BlocDsFB(indexStagiaire).getNom()) == 0) {
			lire1BlocDsFB(indexStagiaire).setPrenom(stagiaire.getPrenom());
			lire1BlocDsFB(indexStagiaire).setPromo(stagiaire.getPromo());
			lire1BlocDsFB(indexStagiaire).setCodeDepartement(stagiaire.getCodeDepartement());			
			lire1BlocDsFB(indexStagiaire).setDateEntree(stagiaire.getDateEntree());
			
			
		//le nom a changé donc	1 supprimer le stagiaire (que l'on souhaite modifier) de la structure de l'arbre
		// 	ajouter le stagiaire modifié comme un  nouveau stagiaire 
		}else {
			supprimer(0,stagiaire,0);
			index=FichierATraiter.ecrire1BlocDsFichierBinaire(stagiaire);	
			FichierATraiter.rechercheParentDsAB(0,stagiaire,index);
			
		}
	}
	//*******************************************************
	//methodes criteresXXX
	// renvoie le tableau de Stagiaires  mis en argument en ne conservant que les objets correspondant au critere de selection (2nd arg) 
	// en 1er arguments le tableau complet des stagiaires et en 2nd soit un  string pour le nom,  prenom, codeDepartement  
	//-ces String peuvent contenir une chaine incomplete- soit un int dateEntree
	
	// recherche par nom 
	public static ObservableList<Stagiaire> critereNom(ObservableList<Stagiaire> liste, String nom) {
		ObservableList<Stagiaire> listeInterne= FXCollections.observableArrayList();

		for (int i=0; i<liste.size();i++) {

			// toUpperCase() pour prendre en compte la chaine
			if (liste.get(i).getNom().startsWith(nom.toUpperCase())) {
			listeInterne.add(liste.get(i));
			
			}
		}
		//si le critere ne selectionne aucun objet alors la methode retourne la liste en argument (liste complete)
		if (listeInterne.size() == 0) {
		return liste;	
		}else {
		return listeInterne;
		}
	}
	

	
	// recherche par prenom 
	public static ObservableList<Stagiaire> criterePrenom(ObservableList<Stagiaire> liste, String prenom) {
		ObservableList<Stagiaire> listeInterne= FXCollections.observableArrayList();

		for (int i=0; i<liste.size();i++) {

			// toUpperCase() pour prendre en compte la chaine
			if (liste.get(i).getPrenom().startsWith((prenom.substring(0,1).toUpperCase())+prenom.substring(1,prenom.length()).toLowerCase())) {
			listeInterne.add(liste.get(i));
			
			}
		}
		//si le critere ne selectionne aucun objet alors la methode retourne la liste en argument (liste complete)
		if (listeInterne.size() == 0) {
		return liste;	
		}else {
		return listeInterne;
		}
	}

	// recherche par codeDepartement 
	public static ObservableList<Stagiaire> critereCodeDepartement (ObservableList<Stagiaire> liste, String  codeDepartement ) {
		ObservableList<Stagiaire> listeInterne= FXCollections.observableArrayList();

		for (int i=0; i<liste.size();i++) {

			// toUpperCase() pour prendre en compte la chaine
			if (liste.get(i).getCodeDepartement().startsWith((codeDepartement.toUpperCase()))) {
			listeInterne.add(liste.get(i));
			
			}
		}
		//si le critere ne selectionne aucun objet alors la methode retourne la liste en argument (liste complete)
		if (listeInterne.size() == 0) {
		return liste;	
		}else {
		return listeInterne;
		}
	}	
	
	// recherche par promo 
	public static ObservableList<Stagiaire> criterePromo(ObservableList<Stagiaire> liste, String promo) {
		ObservableList<Stagiaire> listeInterne= FXCollections.observableArrayList();

		for (int i=0; i<liste.size();i++) {

			// toUpperCase() pour prendre en compte la chaine
			if (liste.get(i).getPromo().startsWith(promo.toUpperCase())) {
			listeInterne.add(liste.get(i));
			
			}
		}
		//si le critere ne selectionne aucun objet alors la methode retourne la liste en argument (liste complete)
		if (listeInterne.size() == 0) {
		return liste;	
		}else {
		return listeInterne;
		}
	}
	
	// recherche par dateEntree 
	public static ObservableList<Stagiaire> critereDateEntree(ObservableList<Stagiaire> liste, int dateEntree) {
		ObservableList<Stagiaire> listeInterne= FXCollections.observableArrayList();

		for (int i=0; i<liste.size();i++) {
			System.out.println("TEST BOUCLE");
			if (dateEntree >= 1000 && liste.get(i).getDateEntree() == (dateEntree)) {
			
					listeInterne.add(liste.get(i));
					}
			if (dateEntree >= 100 && dateEntree < 1000 && liste.get(i).getDateEntree() >= (dateEntree * 10)) {
				System.out.println(" 3 chiffres soit "+dateEntree*10 );
					listeInterne.add(liste.get(i));
			}
			if (dateEntree >= 10 && dateEntree < 100 && liste.get(i).getDateEntree() >= (dateEntree * 100)) {
				listeInterne.add(liste.get(i));
				System.out.println(" 2 chiffres soit "+dateEntree*100 );
				}
			
			if (dateEntree < 10 && dateEntree >= 0 && liste.get(i).getDateEntree() >= (dateEntree * 1000)) {
			listeInterne.add(liste.get(i));
			System.out.println(" 1 seul chiffre soit "+dateEntree*1000 );
			}
			

		}
		//si le critere ne selectionne aucun objet alors la methode retourne la liste en argument (liste complete)
		if (listeInterne.size() == 0) {
		return liste;	
		}else {
		return listeInterne;
		}
	}
	
	// methode qui recuperent les criteresXXX (monocritere)
	public static ObservableList<Stagiaire> multiCriteres(ObservableList<Stagiaire> liste,String nom, String prenom, String codeDepartement, String promo, int dateEntree) {
		ObservableList<Stagiaire> listeInterne= FXCollections.observableArrayList();
		listeInterne=liste;
		
		if (nom != "-1") {
			listeInterne=critereNom(listeInterne,nom);
		}
		if (prenom != "-1") {
			listeInterne=criterePrenom(listeInterne,prenom);
		}	
		if (codeDepartement != "-1") {
			listeInterne=critereCodeDepartement(listeInterne,codeDepartement);
		}	
		if (promo != "-1") {
			listeInterne=criterePrenom(listeInterne,promo);
		}		
		
		if (dateEntree != -1) {
			listeInterne=critereDateEntree(listeInterne,dateEntree);
		}		
		return listeInterne;
	}
	
     //       FIN DU PROGRAMME
	//**************************************************	
	//**************************************************	
	//**************************************************	
	//**************************************************	
	//a voir avec Vincent
	public static int pourquoireturn( int indexCurrent, Stagiaire stagiaire) {

//		if(lire1BlocDsFB(indexCurrent) == null) {
//			return indexCurrent;
//		}
		
		System.out.println(lire1BlocDsFB(indexCurrent).getNom() +" "+stagiaire.getNom());
		
		if (lire1BlocDsFB(indexCurrent).getNom().compareTo(stagiaire.getNom()) == 0) {
			System.out.println(indexCurrent);
			return indexCurrent;
			

		}else if (lire1BlocDsFB(indexCurrent).getNom().compareTo(stagiaire.getNom()) > 0) {
			System.out.println("gauche");
			pourquoireturn(lire1IndexEnfantGauche(indexCurrent),stagiaire);

		} else
		//if (lire1BlocDsFB(indexCurrent).getNom().compareTo(stagiaire.getNom()) < 0)
		{
			System.out.println("droit");
			pourquoireturn(lire1IndexEnfantDroit(indexCurrent),stagiaire);
		}
		return 42;

	}
	
	
	
}

// A toute personne qui est arrivé jusqu'ici merci d'avoir pris le temps de parcourir toutes ces lignes de java