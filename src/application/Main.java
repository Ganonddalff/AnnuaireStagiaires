package application;

import java.io.InputStream;

//import javax.swing.JSpinner.ListEditor;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

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

public class Main extends Application {

	public void start(Stage primaryStage) throws Exception {

		try {
			//création de root
			BorderPane root = new BorderPane();

			//création d'une scère 1600x900
			Scene scene = new Scene(root,1600,900);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);

			//Bar de titre 
			primaryStage.setTitle("Projet 1 TSKB");

			

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

			root.setTop(myMenu);
			//		Label monTexte = new Label("Bonjour");
			//		root.setCenter(monTexte);

			// Choix de la couleur de fond de l'app
			scene.setFill(Color.DIMGRAY);
			
			
			//Création du bouton ajouter
			Button ajouterBtn = new Button("Ajouter un stagiaire");
			root.setCenter(ajouterBtn);
			
			//création du bouton afficher
			Button afficherBtn = new Button("Afficher la liste");
			root.setRight(afficherBtn);
			
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
					Scene sceneAjout = new Scene(gridAjout,1100,850);
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
							// TODO Auto-generated method stub
							
							/*****	ECRIRE ICI L'APPEL DE LA FONCTION AJOUTER UN STAGIAIRE *****/
							
							// On récupère les différents attributs avec getText()
							String nomStagiaire = (String) champNom.getText();
							String prenomStagiaire = (String) champPrenom.getText();
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
							
							Label succesAjout = new Label("Créé avec succès");
							gridAjout.add(succesAjout, 3, 7);

						}
					});
					
					
					
				}
			});
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
}