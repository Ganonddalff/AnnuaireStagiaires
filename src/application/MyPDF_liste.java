package testImport;

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

public class MyPDF_liste {

	// Adresse de l'exportation du PDF
	protected static String FICHIER = "/Users/antoine/itextAnt.pdf";

	// Variables de décoration du texte
	protected static Font policeTitre = new Font(Font.FontFamily.HELVETICA, 20, Font.NORMAL);
	protected static Font policeCorps = new Font(Font.FontFamily.HELVETICA, 10,Font.NORMAL);

	public static void exportListePDF(ArrayList <Stagiaire> liste ) {


		try {
			Document document = new Document();

			PdfWriter.getInstance(document, new FileOutputStream(MyPDF_liste.FICHIER));

			document.open();

			MetadonneesPdf(document);

			//contenu(document);
			Paragraph corpus = new Paragraph();

			// Ajout d'une ligne vide en début de document
			ajoutLigneVide(corpus, 1);

			// Titre
			corpus.add(new Paragraph("Liste de stagiaires", policeTitre));

			ajoutLigneVide(corpus, 1);

			// Rapport de génération du document 

			corpus.add(new Paragraph("Liste générée par : " + System.getProperty("user.name") + ", " + LocalDate.now(), policeCorps));

			ajoutLigneVide(corpus, 1);

			corpus.add(new Paragraph("Annuaire ", policeCorps )); 

			ajoutLigneVide(corpus, 1);

			// Création du tableau

			PdfPTable table = new PdfPTable(5);

			PdfPCell c1 = new PdfPCell(new Phrase("Nom"));

			c1.setHorizontalAlignment(Element.ALIGN_CENTER);

			table.addCell(c1);
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

			table.setHeaderRows(1);

			int compteur = 0;

			for (Stagiaire stagiaire : liste ) {

				compteur+=1;
				System.out.println("coucou" + compteur);
			}

			//				table.addCell(.getNom());
			//				table.addCell(liste.getPrenom());
			//				table.addCell(liste.getCodeDepartement());
			//				table.addCell(liste.getPromo());
			//				table.addCell(liste.getDateEntree());

			table.addCell("JACQUOT");
			table.addCell("Pierre");
			table.addCell("92");
			table.addCell("CDA");
			table.addCell("2022");
			table.addCell("2.0");
			table.addCell("2.1");
			table.addCell("2.2");
			table.addCell("2.3");
			table.addCell("2.4");

			//Ajout du corpus et du tableau au document
			document.add(corpus);
			document.add(table);

			//Commencer une nouvelle page 
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

	public static void main(String[] args) {

		ArrayList<Stagiaire> listestagiaires = new ArrayList <>();

		Stagiaire s1 = new Stagiaire ("AzA","A","93","AI 86",2012);
		Stagiaire s2 = new Stagiaire ("MM","M","93","AI 78",2012);	
		Stagiaire s3 = new Stagiaire ("Szenic","Yann","78","CDA 15",2021);
		Stagiaire s4 = new Stagiaire ("BB","B","93","AI 86",2012);
		Stagiaire s5 = new Stagiaire ("AA","A","93","AI 86",2012);
		Stagiaire s6 = new Stagiaire ("MzM","M","93","AI 78",2012);	
		Stagiaire s7 = new Stagiaire ("Sednic","Yann","78","CDA 15",2021);
		Stagiaire s8 = new Stagiaire ("BdB","B","93","AI 86",2012);
		Stagiaire s9 = new Stagiaire ("AdA","A","93","AI 86",2012);
		Stagiaire s10 = new Stagiaire ("MfM","M","93","AI 78",2012);	
		Stagiaire s11= new Stagiaire ("Sevnic","Yann","78","CDA 15",2021);
		Stagiaire s12= new Stagiaire ("BB","B","93","AI 86",2012);

		Collections.addAll(listestagiaires, s1, s2, s3, s4,s5,s6,s7,s8,s9,s10,s11,s12);

		System.out.println(listestagiaires.size());

		System.out.println(listestagiaires.indexOf(s1));
		
	}
}
