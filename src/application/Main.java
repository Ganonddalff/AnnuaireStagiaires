package application;


import java.io.InputStream;
import java.io.Reader;
import java.util.List;
import java.util.Observable;
import java.util.Vector;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
//import javax.swing.JSpinner.ListEditor;
import java.io.FileReader;
import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.layout.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;


public class Main extends Application {

	public void start(Stage loginStage) throws Exception {


		//	Stage loginStage = new Stage();
		loginStage.setTitle("Connexion");
		GridPane loginPane = fenetreLogin ();
		elementsGraphiques(loginPane);
		Scene scene = new Scene(loginPane, 800, 500);
		loginStage.setScene(scene);
		//		Label label = new Label("Bienvenue sur notre plateforme numérique");
		//		label.setAlignment(Pos.CENTER);
		//	
		//		primaryStage.setScene(new Scene(label, 300, 250));
		//	
		loginStage.show();
	}

	private GridPane fenetreLogin () {
		GridPane grille = new GridPane();
		grille.setAlignment(Pos.CENTER);
		grille.setPadding(new Insets(40, 20, 40, 20));
		grille.setHgap(10);
		grille.setVgap(10);

		ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
		columnOneConstraints.setHalignment(HPos.RIGHT);

		ColumnConstraints columnTwoConstraints = new ColumnConstraints (100, 100, Double.MAX_VALUE);
		columnTwoConstraints.setHgrow(Priority.ALWAYS);
		grille.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstraints);

		return grille;
	}

	private void elementsGraphiques(GridPane gridPane) {
		//		Text titre = new Text("Bienvenue sur votre espace");
		//		  titre.setId("titreText");

		//	       grille.add(titre, 0, 0, 2, 1);

		Label headerLabel = new Label("Bienvenue sur l'espace de connexion");
		headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		gridPane.add(headerLabel, 0, 0, 2, 1);
		GridPane.setHalignment(headerLabel, HPos.CENTER);
		GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));

		Label labelNomUtilisateur = new Label("Nom d'utilisateur : ");
		gridPane.add(labelNomUtilisateur, 0, 2);

		TextField champNom = new TextField();
		champNom.setPrefHeight(35);
		gridPane.add(champNom, 1, 2);

		Label labelPswd = new Label("Mot de passe : ");
		gridPane.add(labelPswd, 0, 3);

		PasswordField champPswd = new PasswordField();
		champPswd.setPrefHeight(35);
		gridPane.add(champPswd, 1, 3);

		Button recupInfosBtn = new Button("Récupérer mes informations de connexion");
		recupInfosBtn.setPrefHeight (40);
		recupInfosBtn.setDefaultButton(true);
		gridPane.add(recupInfosBtn, 1, 5);

		//		GridPane.setMargin(recupInfos, new Insets(20, 0, 20, 0));
		HBox hbBtn = new HBox(200);
		hbBtn.setAlignment(Pos.CENTER_LEFT); 

		Button creerCompte = new Button("Créer un compte");

		creerCompte.setPrefHeight (40);
		creerCompte.setDefaultButton(true);

		gridPane.add(creerCompte, 1, 4);
		//		GridPane.setHalignment(creerCompte, HPos.CENTER);
		//		GridPane.setMargin(creerCompte, new Insets(20, 0, 20, 0));
		HBox hbBtnc = new HBox(40);
		hbBtnc.setAlignment(Pos.CENTER_RIGHT); 

		Button seConnecter = new Button("Se Connecter");
		seConnecter.setPrefHeight(40);
		seConnecter.setDefaultButton(true);
		//		seConnecter.setPrefWidth(100);
		gridPane.add(seConnecter, 1, 4);
		GridPane.setHalignment(seConnecter, HPos.RIGHT);





		//		GridPane.setMargin(seConnecter, new Insets(20, 0, 20, 0));

		seConnecter.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {

				File fichierUser =new File("/home/matt/git/AnnuaireStagiaires/src/application/data/users");

				Users[] utilisateurs = new Users[Users.getNbusers()];

				try(BufferedReader lecteurUser = new BufferedReader(new FileReader(fichierUser))) {


					String line;

					int userIndex=0;

					while(userIndex<utilisateurs.length) {
						line=lecteurUser.readLine();	
						if (line=="STOP") {
							break;
						}

						if(line=="") {
							line=lecteurUser.readLine();
						}

						else {
							String userName = line;
							line = lecteurUser.readLine();
							String pswd = line;
							line = lecteurUser.readLine();
							Boolean admin = Boolean.valueOf(line);
							lecteurUser.readLine();


							Users user = new Users(userName,pswd,admin);
							utilisateurs[userIndex]=user;
							userIndex++;

							//							System.out.println("nom :"+user.getUserName()+"\n"+user.getPswd()+"\n"+user.getAdmin());

						}



					}

				} catch (FileNotFoundException e1) {

					e1.printStackTrace();
					System.out.println("raté");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if(champNom.getText().isEmpty() ) {
					showAlert (Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Erreur!", "Saisir correctement votre email");
					return;
				}
				if(champPswd.getText().isEmpty()) {
					showAlert (Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Erreur!", "Le mot de passe saisi est incorrect");
					return;
				}

				Users userCurrent = new Users(champNom.getText(), champPswd.getText());
				System.out.println("nom saisi : "+userCurrent.getUserName()+" pswd : "+userCurrent.getPswd());
				//***ajouter ici le code de lecture du fichier USER***//



				for(int i = 0; i<utilisateurs.length;i++) {
					System.out.println("nom fichier : "+utilisateurs[i].getUserName()+"\n"+"Pswd : "+utilisateurs[i].getPswd());
					System.out.println(userCurrent.compareUsers(utilisateurs[i]));
					if (userCurrent.compareUsers(utilisateurs[i])==true) {
						//showAlert (Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Validation de votre inscription", "Bienvenue sur votre espace");



						userCurrent.setAdmin(utilisateurs[i].getAdmin());
						Stage primaryStage = new Stage();

						try {
							//création de root
							GridPane root = new GridPane();

							//création d'une scène 
							Scene scene = new Scene(root,900,900);
							scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());



							//Bar de titre 
							primaryStage.setTitle("Projet 1 TSKB");

							// Choix de la couleur de fond de l'app
							//scene.setFill(Color.GRAY);
							primaryStage.setScene(scene);
							//			//Création de myMenu qui est une barre de menu
							//			MenuBar myMenu = new MenuBar();
							//
							//			//Création des menus
							//			Menu menuFichier = new Menu("Fichier");
							//			Menu menuEditer = new Menu("Editer");
							//			Menu menuAide = new Menu("Aide");
							//
							//
							//			//Création des items du menu pour Fichier :
							//			MenuItem nouveauItem = new MenuItem("Nouveau");
							//			MenuItem importItem = new MenuItem("Importer un fichier texte");
							//			MenuItem recherItem = new MenuItem("Rechercher");
							//			MenuItem recherAvItem = new MenuItem("Rechercher...");
							//			MenuItem quitterItem = new MenuItem("Quitter");
							//
							//			//Création des items du menu Editer : 
							//			MenuItem copierItem = new MenuItem ("Copier");
							//			MenuItem collerItem = new MenuItem("Coller");
							//			MenuItem ajouterItem = new MenuItem ("Ajouter");
							//			MenuItem modifItem = new MenuItem ("Modifier");
							//			MenuItem supprItem = new MenuItem ("Supprimer");
							//
							//			//Création de l'item d'aide
							//			MenuItem aideItem = new MenuItem("Aide");
							//
							//			//Ajouter les items aux menus
							//			menuFichier.getItems().addAll(nouveauItem,importItem,recherItem,recherAvItem,quitterItem);
							//			menuEditer.getItems().addAll(copierItem,collerItem,ajouterItem,modifItem,supprItem);
							//			menuAide.getItems().add(aideItem);
							//
							//			//ajouter les menus à la barre : 
							//			myMenu.getMenus().addAll(menuFichier,menuEditer,menuAide); 
							//			//afficher la barre de menu
							//			//	root.setTop(myMenu);

							//Couleur root transparante pour couleur Pane seulement
							root.setStyle("-fx-background-color: null;");



							//Création du bouton ajouter
							Button ajouterBtn = new Button("Ajouter un stagiaire");
							ajouterBtn.setPrefSize(150, 20);


							//Création du bouton modifier un élément de la liste
							Button modifierBtn = new Button("Modifier un stagiaire");
							modifierBtn.setPrefSize(150, 20);

							//Création du bouton exportPDF
							Button exportPDF = new Button("Exporter en pdf");
							exportPDF.setPrefSize(150, 20);

							//Création du bouton AIDE -Atn
							Button aide = new Button("Aide");
							aide.setPrefSize(150, 20);



							//Le titre de la liste situé en haut
							Text titreListe = new Text("Liste des stagiaires");
							titreListe.setFont(Font.font("Tahoma", FontWeight.THIN, 20));

							//Un champ de recherche
							Label rechercheLabel = new Label(" Rechercher selon ");
							TextField rechecherChamp = new TextField();

							Button supprimerBtn = new Button("Supprimer");
							supprimerBtn.setPrefSize(150, 20);


							// un bouton rechercher
							Button rechercherBtn = new Button("Lancer la recherche");
							rechercherBtn.setPrefSize(150, 20);
							// Décaler le bouton du bord de l'écran ??
							//	rechercherBtn.setPadding(new Insets(0,0,40,20));

							//un bouton recherche multicritère
							Button rechercheAvanceeBtn = new Button("Recherche avancée");
							rechercheAvanceeBtn.setPrefSize(150, 20);






							//Liste des criteres
							String criteres[]= {"Nom","Prénom","Département","Promotion","Année"};

							//ComboBox choix du critère
							ComboBox<String> choixCritere = new ComboBox<String>(FXCollections.observableArrayList(criteres));

							/*** LES CHOSES SERIEUSES : AFFICHER
							 * CETTE FICHUE 
							 * LISTE
							 */
							//Création de l'objet tableStagiaire
							TableView<Stagiaire> tableStagiaire = new TableView<Stagiaire>();

							//Création des colonnes du tableau
							//Colonne Nom
							TableColumn<Stagiaire, String> colonneNom = new TableColumn<Stagiaire, String>("Nom") ;
							//Colonne Prénom
							TableColumn<Stagiaire, String> colonnePrenom = new TableColumn<Stagiaire, String>("Prénom");
							//Colonne Département
							TableColumn<Stagiaire, String> colonneDept = new TableColumn<Stagiaire, String>("Département");
							//Colonne Promo
							TableColumn<Stagiaire, String> colonnePromo = new TableColumn<Stagiaire, String>("Promotion");
							//Colonne Année 
							TableColumn<Stagiaire, String> colonneAnnee = new TableColumn<Stagiaire, String>("Année");

							//Ajouter les colonnes à la table

							tableStagiaire.getColumns().addAll(colonneNom,colonnePrenom,colonneDept,colonnePromo,colonneAnnee);

							//Donner leurs valeurs aux colonnes 
							colonneNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
							colonnePrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
							colonneDept.setCellValueFactory(new PropertyValueFactory<>("codeDepartement"));
							colonnePromo.setCellValueFactory(new PropertyValueFactory<>("promo"));
							colonneAnnee.setCellValueFactory(new PropertyValueFactory<>("dateEntree"));


							//Trier par ordre alphabétiqueDépartement
							//		colonneNom.setSortType(SortType.ASCENDING);
							//			colonnePrenom.setSortable(false);

							ObservableList<Stagiaire> listeStagiaire = FXCollections.observableArrayList();


							FichierATraiter.ecrireStagiaireFBDsListObs(0,listeStagiaire);

							tableStagiaire.setItems(listeStagiaire);
							System.out.println(listeStagiaire);

							tableStagiaire.setPrefHeight(800);
							tableStagiaire.setPrefWidth(582);




							//VBox pour les boutons sur la droite
							VBox boutons = new VBox();

							boutons.getChildren().addAll(rechercheAvanceeBtn,ajouterBtn,modifierBtn,supprimerBtn,exportPDF,aide);
							boutons.setSpacing(50);
							boutons.setAlignment(Pos.CENTER_RIGHT);
							//	boutons.setPadding(new Insets(15,0,40,10));


							// Droit admin
							if(userCurrent.getAdmin()==false) {
								modifierBtn.setVisible(false);
								supprimerBtn.setVisible(false);

							}



							//Positionnement

							root.add(titreListe,1,0);
							root.add(rechercheLabel, 0, 0);
							root.add(choixCritere, 0, 1);
							root.add(rechecherChamp, 1, 1);
							root.add(rechercherBtn, 2, 1);
							root.add(tableStagiaire,1,2);
							root.add(boutons, 2, 2);


							//Espacement entre les items
							root.setVgap(10);
							root.setHgap(10);


							//Lancement de primaryStage
							primaryStage.show();



							//			tableStagiaire.setOnMouseClicked(new EventHandler<Event>() {
							//
							//
							//
							//				@Override
							//				public void handle(Event clic) {
							//					// TODO Auto-generated method stub
							//
							//					Boolean clicBool = true;
							//					//					TableCell<Stagiaire,Stagiaire> stagiaireClic = (TableCell<Stagiaire, Stagiaire>) arg0.getSource();
							//
							//				}
							//
							//			});

							//Action "lancer la recherche"
							rechercherBtn.setOnAction(new EventHandler<ActionEvent>() {

								@Override
								public void handle(ActionEvent arg0) {
									// TODO Auto-generated method stub



									if(choixCritere.getSelectionModel().getSelectedItem()=="Nom"){
										tableStagiaire.setItems(FichierATraiter.critereNom(listeStagiaire, rechecherChamp.getText()));
										tableStagiaire.refresh();
										return;

									}

									if(choixCritere.getSelectionModel().getSelectedItem()=="Prénom") {
										tableStagiaire.setItems(FichierATraiter.criterePrenom(listeStagiaire, rechecherChamp.getText()));
										tableStagiaire.refresh();
										return;
									}

									if(choixCritere.getSelectionModel().getSelectedItem()=="Département") {
										tableStagiaire.setItems(FichierATraiter.critereCodeDepartement(listeStagiaire, rechecherChamp.getText()));
										tableStagiaire.refresh();
										return;
									}

									if(choixCritere.getSelectionModel().getSelectedItem()=="Promotion") {
										tableStagiaire.setItems(FichierATraiter.criterePromo(listeStagiaire, rechecherChamp.getText()));
										tableStagiaire.refresh();
										return;
									}

									if(choixCritere.getSelectionModel().getSelectedItem()=="Année") {
										tableStagiaire.setItems(FichierATraiter.critereDateEntree(listeStagiaire, Integer.parseInt(rechecherChamp.getText())));
										tableStagiaire.refresh();
										return;
									}

									else {
										tableStagiaire.setItems(FichierATraiter.critereNom(listeStagiaire, rechecherChamp.getText()));
										tableStagiaire.refresh();
									}



								}
							});

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
									Scene sceneAjout = new Scene(gridAjout,900,380);
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
												String nomStagiaire = champNom.getText().toUpperCase();
												String prenomStagiaire =  champPrenom.getText();
												String deptStagiaire = champDept.getText();
												String promoStagiaire = champPromo.getText();
												//	Il faut convertir de string vers int avec parseInt
												int dateEntreeStagiaire =  Integer.parseInt(champDateDebut.getText());

												//On crée le stagiaire
												Stagiaire newStagiaire = new Stagiaire(nomStagiaire,prenomStagiaire,deptStagiaire,promoStagiaire,dateEntreeStagiaire);

												//								System.out.println(newStagiaire.getNom()+"\n"+
												//										newStagiaire.getPrenom()+"\n"+
												//										newStagiaire.getCodeDepartement()+"\n"+
												//										newStagiaire.getPromo()+"\n"+
												//										newStagiaire.getDateEntree());
												int index=0;
												index=FichierATraiter.ecrire1BlocDsFichierBinaire(newStagiaire);	
												FichierATraiter.rechercheParentDsAB(0,newStagiaire,index);

												Label succesAjout = new Label("Créé avec succès.\nVous pouvez fermer cette fenêtre.");
												gridAjout.add(succesAjout, 3, 7);

												Button fermerFenetre = new Button("Fermer");
												HBox hbFermerFenetre = new HBox(10);
												hbFermerFenetre.getChildren().add(fermerFenetre);
												gridAjout.add(fermerFenetre,3,9);

												//On ajoute le nouveau stagiaire à la liste
												listeStagiaire.add(newStagiaire);

												tableStagiaire.refresh();

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

									Stagiaire stagiaireClic = tableStagiaire.getSelectionModel().getSelectedItem();

									if(stagiaireClic != null) {
										Stage modifStage = new Stage();
										GridPane gridModif = new GridPane();
										gridModif.setAlignment(Pos.TOP_CENTER);
										gridModif.setHgap(10);
										gridModif.setVgap(10);
										gridModif.setPadding(new Insets(25, 25, 25, 25));

										//Création d'une Scene
										Scene modifScene = new Scene (gridModif, 400,300,Color.GRAY);
										modifScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
										modifStage.setScene(modifScene);
										modifStage.setTitle("Modification du stagiaire");
										modifStage.show();

										//éléments visuels de la fenêtre
										//titre du formulaire
										Text titreFenetre = new Text("Modifier");
										gridModif.add(titreFenetre, 0, 0);
										titreFenetre.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

										//						//champ de recherche
										//						Label rechercheNom = new Label("Nom : ");
										//						gridModif.add(rechercheNom, 0, 1);
										//						TextField champNom = new TextField();
										//						gridModif.add(champNom, 1, 1);
										//						//bouton lancer la recherche
										//						Button lancerRecherche = new Button("rechercher");
										//						gridModif.add(lancerRecherche, 2, 1);

										//Type de donnée
										Label nomLabel = new Label("Nom : ");
										Label prenomLabel = new Label("Prénom : ");
										Label deptLabel = new Label("Département : ");
										Label promoLabel = new Label("Promotion : ");
										Label anneeLabel = new Label("Année : ");



										//Champ de modification
										TextField nom = new TextField(tableStagiaire.getSelectionModel().getSelectedItem().getNom().trim());
										TextField prenom= new TextField(tableStagiaire.getSelectionModel().getSelectedItem().getPrenom().trim());
										TextField dept = new TextField(tableStagiaire.getSelectionModel().getSelectedItem().getCodeDepartement().trim());		
										TextField promo = new TextField(tableStagiaire.getSelectionModel().getSelectedItem().getPromo().trim());
										TextField annee = new TextField(""+tableStagiaire.getSelectionModel().getSelectedItem().getDateEntree());
										//Récupérer Stagiaire à supprimer
										//									String;
										//									String;
										//									String;
										//									String;
										//									int;

										//Positonnement des éléments à afficher
										gridModif.add(nomLabel,0,3);
										gridModif.add(prenomLabel, 0, 4);
										gridModif.add(deptLabel, 0, 5);
										gridModif.add(promoLabel, 0, 6);
										gridModif.add(anneeLabel, 0, 7);
										gridModif.add(nom,1,3);
										gridModif.add(prenom, 1, 4);
										gridModif.add(dept, 1, 5);
										gridModif.add(promo, 1, 6);
										gridModif.add(annee, 1, 7);


										Stagiaire oldStagiaire = tableStagiaire.getSelectionModel().getSelectedItem();

										Button enregistrer = new Button("Enregistrer");
										gridModif.add(enregistrer, 2, 7);

										enregistrer.setOnAction(new EventHandler<ActionEvent>() {

											@Override
											public void handle(ActionEvent arg0) {
												// TODO Auto-generated method stub

												String newNom = nom.getText();
												String newPrenom = prenom.getText();
												String newDpt = dept.getText();
												String newPromo = promo.getText();
												int newAnnee = Integer.parseInt(annee.getText());
												Stagiaire newStagiaire = new Stagiaire(newNom,newPrenom,newDpt,newPromo,newAnnee);
												FichierATraiter.majStagiaire(oldStagiaire, newStagiaire);
												listeStagiaire.clear();
												tableStagiaire.refresh();
												FichierATraiter.ecrireStagiaireFBDsListObs(0, listeStagiaire);
												tableStagiaire.setItems(listeStagiaire);
												//	listeStagiaire.add(tableStagiaire.getSelectionModel().getSelectedIndex(), newStagiaire);
												//	listeStagiaire.remove(oldStagiaire);
												tableStagiaire.refresh();

												System.out.println();
												modifStage.close();
												rechecherChamp.clear();
												rechercherBtn.fireEvent(arg0);

											}
										});



										return;
									}

									else {
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

										//éléments visuels de la fenêtre
										//titre du formulaire
										Text titreFenetre = new Text("Rechercher un stagiaire");
										gridModif.add(titreFenetre, 0, 0);
										titreFenetre.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

										//champ de recherche
										Label rechercheNom = new Label("Nom : ");
										gridModif.add(rechercheNom, 0, 1);
										TextField champNom = new TextField();
										gridModif.add(champNom, 1, 1);
										//bouton lancer la recherche
										Button lancerRecherche = new Button("rechercher");
										gridModif.add(lancerRecherche, 2, 1);
										//evenement
										lancerRecherche.setOnAction(new EventHandler<ActionEvent>() {

											@Override
											/*début de l'action*/					

											public void handle(ActionEvent arg0) {
												ObservableList<Stagiaire> listeStagiaire = FXCollections.observableArrayList();


												FichierATraiter.ecrireStagiaireFBDsListObs(0,listeStagiaire);


												System.out.println(listeStagiaire);




												/*****ECRIRE ICI PROGRAMME RECHERCHE*****/	
												for(int i=0; i<listeStagiaire.size();i++ ) {

													if(listeStagiaire.get(i).getNom().startsWith(champNom.getText().toUpperCase())) {

														//Affichage du résultat de la recherche
														//Création des éléments à afficher
														Label stagiaireTrouve = new Label("Résultat : ");
														//Type de donnée
														Label nomLabel = new Label("Nom : ");
														Label prenomLabel = new Label("Prénom : ");
														Label deptLabel = new Label("Département : ");
														Label promoLabel = new Label("Promotion : ");
														Label anneeLabel = new Label("Année : ");



														//Champ de modification
														TextField nom = new TextField(listeStagiaire.get(i).getNom().trim());
														TextField prenom= new TextField(listeStagiaire.get(i).getPrenom().trim());
														TextField dept = new TextField(listeStagiaire.get(i).getCodeDepartement().trim());		
														TextField promo = new TextField(listeStagiaire.get(i).getPromo().trim());
														TextField annee = new TextField(""+listeStagiaire.get(i).getDateEntree());
														//Récupérer Stagiaire à supprimer
														//									String;
														//									String;
														//									String;
														//									String;
														//									int;

														//Positonnement des éléments à afficher
														gridModif.add(nomLabel,0,3);
														gridModif.add(prenomLabel, 0, 4);
														gridModif.add(deptLabel, 0, 5);
														gridModif.add(promoLabel, 0, 6);
														gridModif.add(anneeLabel, 0, 7);
														gridModif.add(stagiaireTrouve, 1, 7);
														gridModif.add(nom,1,3);
														gridModif.add(prenom, 1, 4);
														gridModif.add(dept, 1, 5);
														gridModif.add(promo, 1, 6);
														gridModif.add(annee, 1, 7);
														//A ce stage, nous avons une fenêtre de recherche
														//avec un résultat qui apparait dans un 
														// TextField() ==> donc éditable.

														// Nous allons ajouter un bouton "enregistrer" 
														// et un EventHandler

														Stagiaire oldStagiaire = listeStagiaire.get(i);

														Button enregistrer = new Button("Enregistrer");
														gridModif.add(enregistrer, 2, 7);

														enregistrer.setOnAction(new EventHandler<ActionEvent>() {

															@Override
															public void handle(ActionEvent arg0) {
																// TODO Auto-generated method stub

																//On crée  l'objet stagiaire get(i) 
																// et aux valeurs des TextField
																// qu'on écrit à l'endroit de la résultat de la recherche


																String newNom = nom.getText();
																String newPrenom = prenom.getText();
																String newDpt = dept.getText();
																String newPromo = promo.getText();
																int newAnnee = Integer.parseInt(annee.getText());
																Stagiaire newStagiaire = new Stagiaire(newNom,newPrenom,newDpt,newPromo,newAnnee);
																FichierATraiter.majStagiaire(oldStagiaire, newStagiaire);
																listeStagiaire.clear();
																tableStagiaire.refresh();
																FichierATraiter.ecrireStagiaireFBDsListObs(0, listeStagiaire);
																tableStagiaire.setItems(listeStagiaire);
																//	listeStagiaire.add(tableStagiaire.getSelectionModel().getSelectedIndex(), newStagiaire);
																//	listeStagiaire.remove(oldStagiaire);
																tableStagiaire.refresh();
																tableStagiaire.refresh();
																System.out.println();
																rechecherChamp.clear();
																rechercherBtn.fireEvent(arg0);



															}
														});




														return;
													}
													else {

													}
													//		System.out.println(listestagiaire.get(i).getNom());
												}
											}});



										return;
									}}
							});
							rechercheAvanceeBtn.setOnAction(new EventHandler<ActionEvent>() {

								@Override
								public void handle(ActionEvent arg0) {
									// TODO Auto-generated method stub

									//Création de la fenêtre
									Stage rechercheAvStage = new Stage ();
									GridPane rechercheAvPane = new GridPane();
									rechercheAvPane.setHgap(10);
									rechercheAvPane.setVgap(10);
									rechercheAvPane.setPadding(new Insets(25,25,25,25));
									Scene rechercheScene = new Scene(rechercheAvPane,600,350);
									rechercheAvStage.setScene(rechercheScene);
									rechercheAvStage.show();


									//Création des éléments graphiques
									Text rechercheTitre = new Text("Recherche avancée");
									rechercheTitre.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

									Label labelNom = new Label("Nom : ");
									labelNom.setPrefHeight(20);
									Label labelPrenom = new Label("Prénom : ");
									labelPrenom.setPrefHeight(20);
									Label labelDpt = new Label("Département : ");
									labelDpt.setPrefHeight(20);
									Label labelPromo = new Label("Promotion : ");
									labelPromo.setPrefHeight(20);
									Label labelAnnee = new Label("Année : ");
									labelAnnee.setPrefHeight(20);

									TextField champNom = new TextField();
									champNom.setPrefHeight(20);
									TextField champPrenom = new TextField();
									champPrenom.setPrefHeight(20);
									TextField champDpt = new TextField();
									champDpt.setPrefHeight(20);
									TextField champPromo = new TextField();
									champPromo.setPrefHeight(20);
									TextField champAnnee = new TextField();
									champAnnee.setPrefHeight(20);

									Button lancerRechercheAv = new Button("Lancer la recherche");
									lancerRechercheAv.setPrefSize(150, 20);

									//Création de deux VBox pour les champs et les labels

									VBox vboxLabel = new VBox();
									vboxLabel.getChildren().addAll(labelNom,labelPrenom,labelDpt,labelPromo,labelAnnee);
									vboxLabel.setSpacing(22);

									VBox vboxChamp = new VBox();
									vboxChamp.getChildren().addAll(champNom,champPrenom,champDpt,champPromo,champAnnee);
									vboxChamp.setSpacing(15);

									//Positionnemnt sur Grid

									rechercheAvPane.setAlignment(Pos.TOP_CENTER);

									rechercheAvPane.add(rechercheTitre,0,0);

									rechercheAvPane.add(vboxLabel, 0, 2);
									rechercheAvPane.add(vboxChamp, 1, 2);

									rechercheAvPane.add(lancerRechercheAv, 3, 3);

									//Execution de la recherche avancée à l'appui du bouton

									lancerRechercheAv.setOnAction(new EventHandler<ActionEvent>() {

										@Override
										public void handle(ActionEvent arg0) {
											// TODO Auto-generated method stub


											//Méthode multriCriteres du FichierATraiter : les champs vides recoivent la valeur -1

											if (champNom.getText().trim().isEmpty()) {
												champNom.setText("-1");
											}
											if (champPrenom.getText().trim().isEmpty()) {
												champPrenom.setText("-1");
											}

											if (champDpt.getText().trim().isEmpty()) {
												champDpt.setText("-1");
											}

											if (champPromo.getText().trim().isEmpty()) {
												champPromo.setText("-1");
											}

											if (champAnnee.getText().trim().isEmpty()) {
												champAnnee.setText("-1");
											}

											//on place le résultat de multicriteres dans le tableview
											tableStagiaire.setItems(FichierATraiter.multiCriteres(listeStagiaire, champNom.getText(),champPrenom.getText(),champDpt.getText(), champPromo.getText(), Integer.parseInt(champAnnee.getText())));
											//refresh de la tableview
											tableStagiaire.refresh();
											//		tableStagiaire.getItems().clear();

											//on ferme la fenetre
											rechercheAvStage.close();

										}
									});



								}


							});
							supprimerBtn.setOnAction(new EventHandler<ActionEvent>() {

								@Override
								public void handle(ActionEvent arg0) {
									// TODO Auto-generated method stub
									Stagiaire stagiaireClic = tableStagiaire.getSelectionModel().getSelectedItem();
									if(stagiaireClic==null) {
										System.out.println("T'as pas cliqué");
										Stage erreurStage = new Stage();
										StackPane erreurPane = new StackPane();
										Text erreurTexte = new Text("Erreur !\nVous n'avez pas sélectionné \nla ligne à supprimer.");
										erreurTexte.setTextAlignment(TextAlignment.CENTER);
										Scene sceneErreur = new Scene(erreurPane,250,150);
										erreurStage.show();

										//erreurPane.setAlignment(Pos.CENTER);
										sceneErreur.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
										erreurStage.setScene(sceneErreur);
										erreurStage.setTitle("Erreur");

										erreurPane.getChildren().add(erreurTexte);

										return;

									}
									else {
										System.out.println("Suppression de : "+tableStagiaire.getSelectionModel().getSelectedIndex());
										System.out.println(tableStagiaire.getSelectionModel().getSelectedItem().getNom()); 


										Stage confirmStage = new Stage();
										confirmStage.setTitle("Supprimer");
										GridPane confirmPane = new GridPane();
										Text confirmTexte = new Text("Supprimer \n"+tableStagiaire.getSelectionModel().getSelectedItem().getNom()+""+tableStagiaire.getSelectionModel().getSelectedItem().getPrenom()+"\n ?");
										confirmTexte.setTextAlignment(TextAlignment.CENTER);
										Scene sceneConfirm = new Scene(confirmPane,250,300);
										confirmStage.setScene(sceneConfirm);
										confirmStage.show();
										sceneConfirm.getStylesheets().add(getClass().getResource("application.css").toExternalForm());


										Button oui = new Button("oui");
										Button non = new Button("non");
										HBox choix = new HBox(oui,non);
										choix.setPadding(new Insets(10,10,10,10) );
										choix.setSpacing(20);

										confirmPane.setAlignment(Pos.CENTER);
										confirmPane.add(confirmTexte, 0, 0);
										confirmPane.add(choix, 0, 1);

										oui.setOnAction(new EventHandler<ActionEvent>() {

											@Override
											public void handle(ActionEvent arg0) {
												// TODO Auto-generated method stub
												//	listeStagiaire.remove(tableStagiaire.getSelectionModel().getSelectedIndex());
												listeStagiaire.clear();

												tableStagiaire.refresh();	
												FichierATraiter.supprimer(0, stagiaireClic, 0);

												FichierATraiter.ecrireStagiaireFBDsListObs(0, listeStagiaire);
												tableStagiaire.setItems(listeStagiaire);
												//	listeStagiaire.add(tableStagiaire.getSelectionModel().getSelectedIndex(), newStagiaire);
												//	listeStagiaire.remove(oldStagiaire);
												tableStagiaire.refresh();
												System.out.println("Le stagiaire a bien été effacé");
												confirmStage.close();
												rechercherBtn.fireEvent(arg0);
											}
										});

										non.setOnAction(new EventHandler<ActionEvent>() {
											@Override
											public void handle(ActionEvent arg0) {
												// TODO Auto-generated method stub

												confirmStage.close();
												return;

											}
										});


										return;

									}

								}
							});
							exportPDF.setOnAction(new EventHandler<ActionEvent>() {

								@Override
								public void handle(ActionEvent arg0) {
									// TODO Auto-generated method stub

									System.out.println("Lancement de l'écriture du fichier ...");	
									ExportPDF.exportListePDF(listeStagiaire);
									System.out.println("Le fichier PDF a bien été généré");

									

								}
							});

							aide.setOnAction(new EventHandler<ActionEvent>() {

								@Override
								public void handle(ActionEvent arg0) {
									// TODO Auto-generated method stub
									AidePDF.monPDFgo();

								}
							});

























						} catch(Exception e) {
							e.printStackTrace();
						}
					}
					else {
						System.out.println("mauvais identifiant ?");
					}
				}











			}
		});
	}

	private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(owner);
		alert.show();












	}}