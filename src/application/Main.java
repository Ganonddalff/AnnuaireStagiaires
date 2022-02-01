package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;  


public class Main extends Application {
	@Override


	public void start(Stage primaryStage) throws FileNotFoundException  {
		/*	try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		} */
		String fileImport = "/home/matt/Documents/ISIKA/Projet1/STAGIAIRES.DON"; // On met le fichier et son adresse dans le String file
		//	String fileExport = "/home/matt/Documents/ISIKA/Projet1/STAGIAIRES.bin";
		//	Individu tab = new Individu();
		IndividuList liste = new IndividuList();

		//On invoque BufferedReader pour lire les gros fichiers texte
		try(BufferedReader br = new BufferedReader(new FileReader(fileImport))) {

			//br va lire ligne par ligne on crée donc une variable line
			String line;
			//		String lineDpt;
			while((line = br.readLine()) != null) { //tant que line n'est pas nul on continue
				if (line=="[*]") {
					line=br.readLine();
				}
				else {

					String nom = line;
					line = br.readLine();
					String prenom = line;
					line = br.readLine();
					String codeDepartement = line;
					line = br.readLine();
					String promo = line;
					line = br.readLine();
					String anneeEntree =  line ;
					line = br.readLine();
					liste.ajouterStagiaire(new Stagiaire (nom,prenom,codeDepartement,promo, anneeEntree));
					//System.out.println("nom : " +nom +" prénom : "+prenom+" Dpt : "+codeDepartement+" Promo : "+promo+ " Entré en : "+anneeEntree);
				}
			}
		}

		catch(IOException e) {
			System.out.println("Une erreur est survenue. Déso.");
			e.printStackTrace();
		}
		//for (int i=0;tab.getLesStagiaires()!=null;i++) {
		//	for (int j=0;j<liste.getLesStagiaires().length;j++) {
		//System.out.println("La liste est la suivante : "+Arrays.toString((Stagiaire[]) liste.toArray(new Stagiaire[((Object) liste).size()])));
		//System.out.println(tab.getLesStagiaires()[j].getPrenom());				

		//		}
		//		break;


		System.out.println("liste : "+liste.toString());

		try {
			File bin = new File("/home/matt/Documents/ISIKA/Projet1/STAGIAIRES.bin");
			if (bin.createNewFile()) {
				System.out.println("Fichier créé: " + bin.getName());
			} else {
				System.out.println("Le fichier existe");
			}
		} catch (IOException e) {
			System.out.println("Erreur.");
			e.printStackTrace();
		}

		try {
			FileWriter export = new FileWriter("/home/matt/Documents/ISIKA/Projet1/STAGIAIRES.bin");
			export.write(liste.toString());
			export.close();
			System.out.println("Success");
		} catch (IOException e) {
			System.out.println("Erreur");
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		launch(args);




	}
}


