package application;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.io.FileOutputStream;
import java.time.LocalDate;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
//import com.itextpdf.text.List;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ExportPDF {
	
	// ********************** Attention à bien modifier la destination de l'export x2 ******************* 
	
	// ********************** Attention à bien intégrer la méthode ajoutLigneVide  ******************* 

	// Adresse de l'exportation du PDF
	protected static String FICHIER =  "/Users/antoine/Documents/Me/Informatique/ISIKA/projet_1_fichiers/liste.pdf"; 

	// Décoration du texte
	protected static Font policeTitre = new Font(Font.FontFamily.HELVETICA, 20, Font.NORMAL);
	protected static Font policeCorps = new Font(Font.FontFamily.HELVETICA, 10,Font.NORMAL);
	
	public static void exportListePDF(ObservableList <Stagiaire> multiCriteres ) { 

		try {
			
			Document document = new Document();

			PdfWriter.getInstance(document, new FileOutputStream(ExportPDF.FICHIER));

			document.open();

			MetadonneesPdf(document);

			// Contenu(document);
			Paragraph corpus = new Paragraph();

			// Ajout d'une ligne vide en début de document
			ajoutLigneVide(corpus, 1);

			// Titre
			corpus.add(new Paragraph("Liste de stagiaires", policeTitre));

			ajoutLigneVide(corpus, 1);

			// Rapport de génération du document 

			corpus.add(new Paragraph("Liste générée le : " + LocalDate.now() + " par " + System.getProperty("user.name") , policeCorps));

			ajoutLigneVide(corpus, 1);

			corpus.add(new Paragraph("Annuaire ", policeCorps )); 

			ajoutLigneVide(corpus, 1);

			// Création du tableau

			PdfPTable table = new PdfPTable(5);

			PdfPCell c1 = new PdfPCell(new Phrase("Nom"));

			c1.setHorizontalAlignment(Element.ALIGN_CENTER);

			table.addCell(c1);
			
			// Place dans la page
			table.setWidthPercentage(100); 

			c1 = new PdfPCell(new Phrase("Prénom"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);

			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("Code du département"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("Promotion"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("Année"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
			
			// Nombre de Headers
			table.setHeaderRows(1);

			// Intégration des attributs de la liste observable dans le tableau
			for (Stagiaire stagiaire : multiCriteres ) {
				table.addCell(stagiaire.getNom());
				table.addCell(stagiaire.getPrenom());
				table.addCell(stagiaire.getCodeDepartement());
				table.addCell(stagiaire.getPromo());
				table.addCell(String.valueOf(stagiaire.getDateEntree()));
			}

			//Ajout du corpus et du tableau au document
			document.add(corpus);
			document.add(table);

			//document.newPage();

			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Metadatas du fichier PDF 
	protected static void MetadonneesPdf (Document document) {
		document.addTitle("Annuaire de stagiaires");
		document.addSubject("Stagiaire");
		document.addAuthor("AMPY");
	}
	
	//static void contenu (Document document) throws DocumentException - méthode effacée et réintégrée 
	
	protected static void ajoutLigneVide(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
}
