package application;

import java.io.File;
import java.util.List;
import java.util.Vector;


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
import javafx.scene.control.PasswordField;

import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

import javafx.application.Platform;

public class Initialisation extends Application {
	
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("Groupe 1 - projet 1 ISIKA");
		GridPane gridPane = createRegistrationFormPane ();
		addUIControls(gridPane);
		Scene scene = new Scene(gridPane, 800, 500);
		primaryStage.setScene(scene);
//		Label label = new Label("Bienvenue sur notre plateforme numérique");
//		label.setAlignment(Pos.CENTER);
//		
//		primaryStage.setScene(new Scene(label, 300, 250));
//		
		primaryStage.show();
	}
	
	private GridPane createRegistrationFormPane () {
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
	
	private void addUIControls(GridPane gridPane) {
//		Text titre = new Text("Bienvenue sur votre espace");
//		  titre.setId("titreText");
	        
//	       grille.add(titre, 0, 0, 2, 1);
		
		Label headerLabel = new Label("Voulez vous reinitialiser le fichier binaire ?");
		headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		gridPane.add(headerLabel, 0, 0, 2, 1);
		GridPane.setHalignment(headerLabel, HPos.CENTER);
		GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));
		
		
//		Label labelBIN = new Label("fichier.bin ");
//		gridPane.add(labelBIN, 0, 2);
//		
//		TextField cheminAccesBIN = new TextField("chemin d'accès du fichier bin ");
//		cheminAccesBIN.setPrefHeight(35);
//		gridPane.add(cheminAccesBIN, 1, 2);
		
		
		Label labelSuppDon = new Label("Pour supprimer le fichier DON veuillez appuyer sur ce bouton ---> ");
		gridPane.add(labelSuppDon, 0, 3);
		
		Button supprimerBIN = new Button("supprimer  bin");
		gridPane.add(supprimerBIN, 1, 3);
		

		
		Label labelDON = new Label("STAGIAIRES.DON ");
		gridPane.add(labelDON, 0, 4);
		
		TextField cheminAccesDON = new TextField("chemin d'accès du fichier DON ");
		cheminAccesDON.setPrefHeight(35);
		gridPane.add(cheminAccesDON, 1, 4);
		

		
		Button fermerFen = new Button("fermer la fenetre");
		fermerFen.setPrefHeight (35);
		fermerFen.setDefaultButton(true);
//		recupInfos.setPrefWidth(250);
		gridPane.add(fermerFen, 1, 6);

//		GridPane.setMargin(recupInfos, new Insets(20, 0, 20, 0));
		HBox hbBtn = new HBox(200);
        hbBtn.setAlignment(Pos.CENTER_LEFT); 
		
		Button  genererBIN= new Button(" generer fichier bin");
		
		genererBIN.setPrefHeight (40);
		genererBIN.setDefaultButton(true);

		gridPane.add(genererBIN, 1, 5);
//		GridPane.setHalignment(creerCompte, HPos.CENTER);
//		GridPane.setMargin(creerCompte, new Insets(20, 0, 20, 0));
		HBox hbBtnc = new HBox(40);
        hbBtnc.setAlignment(Pos.CENTER_RIGHT); 
        		
//		Button seConnecter = new Button("Se Connecter");
//		seConnecter.setPrefHeight(40);
//		seConnecter.setDefaultButton(true);
////		seConnecter.setPrefWidth(100);
//		gridPane.add(seConnecter, 1, 4);
//		GridPane.setHalignment(seConnecter, HPos.RIGHT);
	
		
		
		
		supprimerBIN.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {


				File file =new File("src/application/data/fichier.bin");
					file.delete();		
				}
			});	
			
		
		

		
		
		genererBIN.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				int index =0;	
				
				
				//FichierATraiter fichier = new FichierATraiter("./src/application/data/STAGIAIRES.DON");				
				FichierATraiter fichier = new FichierATraiter(cheminAccesDON.getText());

				List<Stagiaire> listestagiaires = new Vector<Stagiaire>();
				listestagiaires=fichier.fabriqueChaine();
				
				// on cree la racine du fichier
				index = FichierATraiter.ecrire1BlocDsFichierBinaire(listestagiaires.get(0));
				//on ajoute chaque stagiaire dans le fichier binaire et on cree le lien avec son parent dans la structure ABR
				for(int i=1; i<listestagiaires.size(); i++ ) {
					index=FichierATraiter.ecrire1BlocDsFichierBinaire(listestagiaires.get(i));	
					FichierATraiter.rechercheParentDsAB(0,listestagiaires.get(i),index);

				}				
				
				}
			});	
		
		
		
		fermerFen.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {

				Platform.exit();

				}				

			});	
		

		}
				
	
		
	
	public static void main(String[] args) {
		launch(args);
	}

}