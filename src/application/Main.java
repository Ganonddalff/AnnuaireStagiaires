package application;


import java.io.InputStream;
import java.util.List;
import java.util.Vector;

//import javax.swing.JSpinner.ListEditor;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.layout.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import application.YSLanceur;

public class Main extends Application {

	public void start(Stage primaryStage) throws Exception {

		try {
			//création de root
			BorderPane root = new BorderPane();

			//création d'une scère 1600x900
			final Scene scene = new Scene(root,1600,900,Color.DIMGRAY);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());



			//Bar de titre 
			primaryStage.setTitle("Projet 1 TSKB");

			// Choix de la couleur de fond de l'app
			scene.setFill(Color.GRAY);
			primaryStage.setScene(scene);
			//Création de myMenu qui est une barre de menu
			MenuBar myMenu = new MenuBar();

			//Création des menus
			Menu menuFichier = new Menu("Fichier");
			Menu menuEditer = new Menu("Editer");
			Menu menuAide = new Menu("Aide");


			//Création des items du menu pour Fichier :
			MenuItem nouveauItem = new MenuItem("Nouveau");
			MenuItem importItem = new MenuItem("Importer un fichier texte");
			MenuItem recherItem = new MenuItem("Rechercher");
			MenuItem recherAvItem = new MenuItem("Rechercher...");
			MenuItem quitterItem = new MenuItem("Quitter");

			//Création des items du menu Editer : 
			MenuItem copierItem = new MenuItem ("Copier");
			MenuItem collerItem = new MenuItem("Coller");
			MenuItem ajouterItem = new MenuItem ("Ajouter");
			MenuItem modifItem = new MenuItem ("Modifier");
			MenuItem supprItem = new MenuItem ("Supprimer");

			//Création de l'item d'aide
			MenuItem aideItem = new MenuItem("Aide");

			//Ajouter les items aux menus
			menuFichier.getItems().addAll(nouveauItem,importItem,recherItem,recherAvItem,quitterItem);
			menuEditer.getItems().addAll(copierItem,collerItem,ajouterItem,modifItem,supprItem);
			menuAide.getItems().add(aideItem);

			//ajouter les menus à la barre : 
			myMenu.getMenus().addAll(menuFichier,menuEditer,menuAide); 
			//afficher la barre de menu
			root.setTop(myMenu);

			//Couleur root transparante pour couleur Pane seulement
			root.setStyle("-fx-background-color: null;");



			//Création du bouton ajouter
			Button ajouterBtn = new Button("Ajouter un stagiaire");
			ajouterBtn.setLayoutX(700);
			ajouterBtn.setLayoutY(450);


			//création du bouton afficher
			Button afficherBtn = new Button("Afficher la liste");
			afficherBtn.setLayoutX(300);
			afficherBtn.setLayoutY(450);


			//Création du bouton modifier un élément de la liste
			Button modifierBtn = new Button("Modifier un stagiaire");
			modifierBtn.setLayoutX(1100);
			modifierBtn.setLayoutY(450);

			//grouper les boutons
			Group groupeStagiaire = new Group();
			groupeStagiaire.getChildren().addAll(ajouterBtn,afficherBtn,modifierBtn);

			//ajouter le groupe à root

			root.getChildren().add(groupeStagiaire);

			//Pas de couleur pour menubar
			myMenu.setStyle("-fx-background-color: null;");

			//Lancement de primaryStage
			primaryStage.show();

			//Action appuyer sur ajouter ouvre une nouvelle fenêtre

			ajouterBtn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub

					//On crée la deuxième fenêtre
					Stage secondaryStage = new Stage();

					//On crée un grid pour cette fenêtre
					GridPane gridAjout = new GridPane();
					gridAjout.setAlignment(Pos.TOP_CENTER);
					gridAjout.setHgap(10);
					gridAjout.setVgap(10);
					gridAjout.setPadding(new Insets(25, 25, 25, 25));

					//On crée une scène, plus petite que la 1ère
					Scene sceneAjout = new Scene(gridAjout,900,380,Color.GRAY);
					sceneAjout.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					//On lie la scène à la fenêtre
					secondaryStage.setScene(sceneAjout);
					//On nomme cette fenêtre 
					secondaryStage.setTitle("Nouveau stagiaire");
					//Et on l'affiche
					secondaryStage.show();

					/****On ajoute des éléments : ****/

					//Le titre de la PAGE
					/* Le texte*/	Text titreFenetre = new Text("Nouveau stagiaire");
					titreFenetre.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
					/*Ajouter à la grille*/				
					gridAjout.add(titreFenetre, 0, 0);

					//Les différents attributs du stagiaire

					//Le label nom
					Label nomStagiaire = new Label("Nom : ");
					gridAjout.add(nomStagiaire, 0, 1);

					//Le champ nom
					TextField champNom = new TextField();
					gridAjout.add(champNom, 1, 1);

					// On répète pour tous les attributs

					//Rang 2 Prénom

					Label prenomStagiaire = new Label("Prénom : ");
					gridAjout.add(prenomStagiaire, 0, 2);

					TextField champPrenom = new TextField();
					gridAjout.add(champPrenom, 1, 2);

					//Rang 3 

					Label dept = new Label("Département : ");
					gridAjout.add(dept, 0, 3);

					TextField champDept = new TextField();
					gridAjout.add(champDept, 1, 3);
					/*ajout d'un commentaire sur la grid*/
					Label deptPrecision = new Label ("Attention ! entrez uniquement le numéro du département.");
					gridAjout.add(deptPrecision, 3, 3);

					//Rang 4

					Label promo = new Label("Promotion : ");
					gridAjout.add(promo, 0, 4);

					TextField champPromo = new TextField();
					gridAjout.add(champPromo, 1, 4);

					//Rang 5

					Label dateDebut = new Label("Date de début de formation : ");
					gridAjout.add(dateDebut, 0, 5);

					TextField champDateDebut = new TextField();
					gridAjout.add(champDateDebut, 1, 5);

					Button saveAjout = new Button("Enregistrer");
					HBox hbsaveAjout = new HBox(10);
					hbsaveAjout.setAlignment(Pos.BOTTOM_RIGHT);
					hbsaveAjout.getChildren().add(saveAjout);
					gridAjout.add(saveAjout, 3, 6);


					saveAjout.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent arg0) {

							/*****	ECRIRE ICI L'APPEL DE LA FONCTION AJOUTER UN STAGIAIRE *****/

							//Pour ne pas créer d'objet avec attribut vide
							if(champNom.getText().equals("")) {
								Label champVide = new Label ("Tous les champs ne sont pas remplis");
								gridAjout.add(champVide, 0, 7);
								return;
							}
							if(champPrenom.getText().equals("")){
								Label champVide = new Label ("Tous les champs ne sont pas remplis");
								gridAjout.add(champVide, 0, 7);
								return;
							}
							if(champDept.getText().equals("")) {
								Label champVide = new Label ("Tous les champs ne sont pas remplis");
								gridAjout.add(champVide, 0, 7);
								return;
							}
							if(champPromo.getText().equals("")) {
								Label champVide = new Label ("Tous les champs ne sont pas remplis");
								gridAjout.add(champVide, 0, 7);
								return;
							}
							if(champDept.getText().equals("")) {
								Label champVide = new Label ("Tous les champs ne sont pas remplis");
								gridAjout.add(champVide, 0, 7);
								return;
							}
							else {
								// On récupère les différents attributs avec getText()
								String nomStagiaire = champNom.getText();
								String prenomStagiaire =  champPrenom.getText();
								String deptStagiaire = champDept.getText();
								String promoStagiaire = champPromo.getText();
								//	Il faut convertir de string vers int avec parseInt
								int dateEntreeStagiaire =  Integer.parseInt(champDateDebut.getText());

								//On crée le stagiaire
								Stagiaire newStagiaire = new Stagiaire(nomStagiaire,prenomStagiaire,deptStagiaire,promoStagiaire,dateEntreeStagiaire);

								System.out.println(newStagiaire.getNom()+"\n"+
										newStagiaire.getPrenom()+"\n"+
										newStagiaire.getCodeDepartement()+"\n"+
										newStagiaire.getPromo()+"\n"+
										newStagiaire.getDateEntree());

								Label succesAjout = new Label("Créé avec succès.\nVous pouvez fermer cette fenêtre.");
								gridAjout.add(succesAjout, 3, 7);

								Button fermerFenetre = new Button("Fermer");
								HBox hbFermerFenetre = new HBox(10);
								hbFermerFenetre.getChildren().add(fermerFenetre);
								gridAjout.add(fermerFenetre,3,9);

								fermerFenetre.setOnAction(new EventHandler<ActionEvent>() {

									@Override
									public void handle(ActionEvent arg0) {
										// TODO Auto-generated method stub

										secondaryStage.close();
									}
								});


							}}
					});



				}
			});
			modifierBtn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// Création d'un Stage et d'une Grid
					Stage modifStage = new Stage();
					GridPane gridModif = new GridPane();
					gridModif.setAlignment(Pos.TOP_CENTER);
					gridModif.setHgap(10);
					gridModif.setVgap(10);
					gridModif.setPadding(new Insets(25, 25, 25, 25));

					//Création d'une Scene
					Scene modifScene = new Scene (gridModif, 900,300,Color.GRAY);
					modifScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					modifStage.setScene(modifScene);
					modifStage.setTitle("Modification du stagiaire");
					modifStage.show();


					Text titreFenetre = new Text("Rechercher un stagiaire");
					gridModif.add(titreFenetre, 0, 0);
					titreFenetre.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
					Label rechercheNom = new Label("Nom : ");
					gridModif.add(rechercheNom, 0, 1);
					TextField champNom = new TextField();
					gridModif.add(champNom, 1, 1);

					Button lancerRecherche = new Button("rechercher");
					gridModif.add(lancerRecherche, 2, 1);

					lancerRecherche.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent arg0) {
							FichierATraiter fichier = new FichierATraiter("./src/application/data/STAGIAIRES.DON");
							List<Stagiaire> listestagiaires = new Vector<Stagiaire>();
							listestagiaires=fichier.fabriqueChaine();
							
							
//							int index;
//							ObservableList<Stagiaire> listeObserStag = FXCollections.observableArrayList(); 
//							
//							
//							//creation de la racine
//							index = FichierATraiter.ecrire1BlocDsFichierBinaire(listestagiaires.get(0));
//							listeObserStag.add(listestagiaires.get(0));	
							
							
							
								System.out.println(champNom.getText());
								System.out.println(listestagiaires.get(0).getNom());


							/*****ECRIRE ICI PROGRAMME RECHERCHE*****/	
							for(int i=0; i<listestagiaires.size();i++ ) {
							//	if(champNom.getText().compareTo(listestagiaires.get(2).getNom())==0) {
								if(listestagiaires.get(i).getNom().startsWith(champNom.getText().toUpperCase())) {
									System.out.println("ok");
									return;
									
								}
								else {
									System.out.println("pas ok");
								}
								System.out.println(listestagiaires.get(i).getNom());
							}
						}});




				}
			});

		} catch(Exception e) {
			e.printStackTrace();
		}

	}
}