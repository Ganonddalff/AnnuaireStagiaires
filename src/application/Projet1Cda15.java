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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setPadding(new Insets(40, 40, 40, 40));
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		
		ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
		columnOneConstraints.setHalignment(HPos.RIGHT);
		
		ColumnConstraints columnTwoConstraints = new ColumnConstraints (200, 200, Double.MAX_VALUE);
		columnTwoConstraints.setHgrow(Priority.ALWAYS);
		gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstraints);
		
		return gridPane;
	}
	
	private void addUIControls(GridPane gridPane) {
		Label headerLabel = new Label("Créer un compte");
		headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		gridPane.add(headerLabel, 0, 0, 2, 1);
		GridPane.setHalignment(headerLabel, HPos.CENTER);
		GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));
		
		Label email = new Label("Email : ");
		gridPane.add(email, 0, 2);
		
		TextField emailField = new TextField();
		emailField.setPrefHeight(40);
		gridPane.add(emailField, 1, 2);
		
		Label password = new Label("Mot de passe : ");
		gridPane.add(password, 0, 3);
		
		PasswordField passwordMDP = new PasswordField();
		passwordMDP.setPrefHeight(40);
		gridPane.add(password, 1, 3);
		
		Button seConnecter = new Button("Se Connecter");
		seConnecter.setPrefHeight(40);
		seConnecter.setDefaultButton(true);
		seConnecter.setPrefWidth(100);
		gridPane.add(seConnecter, 0, 4, 2, 1);
		GridPane.setHalignment(seConnecter, HPos.CENTER);
		GridPane.setMargin(seConnecter, new Insets(20, 0, 20, 0));
		
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
