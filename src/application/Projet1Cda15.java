package application;

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

public class Projet1Cda15 extends Application {
	
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
		
		Label headerLabel = new Label("Bienvenue sur l'espace de connexion");
		headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		gridPane.add(headerLabel, 0, 0, 2, 1);
		GridPane.setHalignment(headerLabel, HPos.CENTER);
		GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));
		
		Label email = new Label("Email : ");
		gridPane.add(email, 0, 2);
		
		TextField emailField = new TextField();
		emailField.setPrefHeight(35);
		gridPane.add(emailField, 1, 2);
		
		Label password = new Label("Mot de passe : ");
		gridPane.add(password, 0, 3);
		
		PasswordField passwordMDP = new PasswordField();
		passwordMDP.setPrefHeight(35);
		gridPane.add(passwordMDP, 1, 3);
		
		Button recupInfos = new Button("Récupérer mes informations de connexion");
		recupInfos.setPrefHeight (40);
		recupInfos.setDefaultButton(true);
//		recupInfos.setPrefWidth(250);
		gridPane.add(recupInfos, 1, 5);

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
				if(emailField.getText().isEmpty() ) {
					showAlert (Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Erreur!", "Saisir correctement votre email");
					return;
				}
				if(passwordMDP.getText().isEmpty()) {
					showAlert (Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Erreur!", "Le mot de passe saisi est incorrect");
					return;
				}
				showAlert (Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Validation de votre inscription", "Bienvenue sur votre espace");
				
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
			}
		
	
	public static void main(String[] args) {
		launch(args);
	}

}
