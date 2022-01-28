package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import java.io.*;


public class Main extends Application {
	@Override


	public void start(Stage primaryStage) {
	/*	try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		} */
		String file = "/home/matt/Documents/ISIKA/Projet1/STAGIAIRES.DON"; // On met le fichier et son adresse dans le String file


		//On invoque BufferedReader pour lire les gros fichiers texte
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {

			//br va lire ligne par ligne on crée donc une variable line
			String line;
			while((line = br.readLine()) != null) { //tant que line n'est pas nul on continue
				System.out.println(line);
			}

		}
		catch(IOException e) {
			System.out.println("Une erreur est survenue. Déso.");
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		launch(args);




	}
}


