package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

public class MainTestAtn {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/* 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 								DEBUT DES TESTS
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 */

		/*
		ObservableList<Stagiaire> maListObservable = FXCollections.observableArrayList();
			
		Stagiaire stg10 = new Stagiaire ("One","pren","dep","prom",20010);
		Stagiaire stg11 = new Stagiaire ("One","pren","dep","prom",20011);
		Stagiaire stg12 = new Stagiaire ("One","pren","dep","prom",20012);
		Stagiaire stg13 = new Stagiaire ("One","pren","dep","prom",20013);
		Stagiaire stg14 = new Stagiaire ("One","pren","dep","prom",20014);
		Stagiaire stg15 = new Stagiaire ("One","pren","dep","prom",20015);
		Stagiaire stg16 = new Stagiaire ("One","pren","dep","prom",20016);
		Stagiaire stg17 = new Stagiaire ("One","pren","dep","prom",20017);
		Stagiaire stg18 = new Stagiaire ("One","pren","dep","prom",20018);
		Stagiaire stg19 = new Stagiaire ("One","pren","dep","prom",20019);
		Stagiaire stg20 = new Stagiaire ("One","pren","dep","prom",20020);
		Stagiaire stg21 = new Stagiaire ("One","pren","dep","prom",20021);
		Stagiaire stg22 = new Stagiaire ("One","pren","dep","prom",20022);
		Stagiaire stg23 = new Stagiaire ("One","pren","dep","prom",20023);
		Stagiaire stg24 = new Stagiaire ("One","pren","dep","prom",20024);
		Stagiaire stg25 = new Stagiaire ("One","pren","dep","prom",20025);
		Stagiaire stg26 = new Stagiaire ("One","pren","dep","prom",20026);
		Stagiaire stg27 = new Stagiaire ("One","pren","dep","prom",20027);
		Stagiaire stg28 = new Stagiaire ("One","pren","dep","prom",20028);
		Stagiaire stg29 = new Stagiaire ("One","pren","dep","prom",20029);
		Stagiaire stg30 = new Stagiaire ("One","pren","dep","prom",20030);
		Stagiaire stg31 = new Stagiaire ("One","pren","dep","prom",20031);
		Stagiaire stg32 = new Stagiaire ("One","pren","dep","prom",20032);
		Stagiaire stg33 = new Stagiaire ("One","pren","dep","prom",20033);
		Stagiaire stg34 = new Stagiaire ("One","pren","dep","prom",20034);
		Stagiaire stg35 = new Stagiaire ("One","pren","dep","prom",20035);
		Stagiaire stg36 = new Stagiaire ("One","pren","dep","prom",20036);
		Stagiaire stg37 = new Stagiaire ("One","pren","dep","prom",20037);
		Stagiaire stg38 = new Stagiaire ("One","pren","dep","prom",20038);
		Stagiaire stg39 = new Stagiaire ("One","pren","dep","prom",20039);
		Stagiaire stg40 = new Stagiaire ("One","pren","dep","prom",20040);
		Stagiaire stg41 = new Stagiaire ("One","pren","dep","prom",20041);
		Stagiaire stg42 = new Stagiaire ("One","pren","dep","prom",20042);
		Stagiaire stg43 = new Stagiaire ("One","pren","dep","prom",20043);
		Stagiaire stg44 = new Stagiaire ("One","pren","dep","prom",20044);
		Stagiaire stg45 = new Stagiaire ("One","pren","dep","prom",20045);
		Stagiaire stg46 = new Stagiaire ("One","pren","dep","prom",20046);
		Stagiaire stg47 = new Stagiaire ("One","pren","dep","prom",20047);
		Stagiaire stg48 = new Stagiaire ("One","pren","dep","prom",20048);
		Stagiaire stg49 = new Stagiaire ("One","pren","dep","prom",20049);
		Stagiaire stg50 = new Stagiaire ("One","pren","dep","prom",20050);
		Stagiaire stg51 = new Stagiaire ("One","pren","dep","prom",20051);
		Stagiaire stg52 = new Stagiaire ("One","pren","dep","prom",20052);
		Stagiaire stg53 = new Stagiaire ("One","pren","dep","prom",20053);
		Stagiaire stg54 = new Stagiaire ("One","pren","dep","prom",20054);
		Stagiaire stg55 = new Stagiaire ("One","pren","dep","prom",20055);
		Stagiaire stg56 = new Stagiaire ("One","pren","dep","prom",20056);
		Stagiaire stg57 = new Stagiaire ("One","pren","dep","prom",20057);
		Stagiaire stg58 = new Stagiaire ("One","pren","dep","prom",20058);
		Stagiaire stg59 = new Stagiaire ("One","pren","dep","prom",20059);
		Stagiaire stg60 = new Stagiaire ("One","pren","dep","prom",20060);
		Stagiaire stg61 = new Stagiaire ("One","pren","dep","prom",20061);
		Stagiaire stg62 = new Stagiaire ("One","pren","dep","prom",20062);
		Stagiaire stg63 = new Stagiaire ("One","pren","dep","prom",20063);
		Stagiaire stg64 = new Stagiaire ("One","pren","dep","prom",20064);
		Stagiaire stg65 = new Stagiaire ("One","pren","dep","prom",20065);
		Stagiaire stg66 = new Stagiaire ("One","pren","dep","prom",20066);
		Stagiaire stg67 = new Stagiaire ("One","pren","dep","prom",20067);
		Stagiaire stg68 = new Stagiaire ("One","pren","dep","prom",20068);
		Stagiaire stg69 = new Stagiaire ("One","pren","dep","prom",20069);
		Stagiaire stg70 = new Stagiaire ("One","pren","dep","prom",20070);
		Stagiaire stg71 = new Stagiaire ("One","pren","dep","prom",20071);
		Stagiaire stg72 = new Stagiaire ("One","pren","dep","prom",20072);
		Stagiaire stg73 = new Stagiaire ("One","pren","dep","prom",20073);
		Stagiaire stg74 = new Stagiaire ("One","pren","dep","prom",20074);
		Stagiaire stg75 = new Stagiaire ("One","pren","dep","prom",20075);
		Stagiaire stg76 = new Stagiaire ("One","pren","dep","prom",20076);
		Stagiaire stg77 = new Stagiaire ("One","pren","dep","prom",20077);
		Stagiaire stg78 = new Stagiaire ("One","pren","dep","prom",20078);
		Stagiaire stg79 = new Stagiaire ("One","pren","dep","prom",20079);
		Stagiaire stg80 = new Stagiaire ("One","pren","dep","prom",20080);
		Stagiaire stg81 = new Stagiaire ("One","pren","dep","prom",20081);
		Stagiaire stg82 = new Stagiaire ("One","pren","dep","prom",20082);
		Stagiaire stg83 = new Stagiaire ("One","pren","dep","prom",20083);
		Stagiaire stg84 = new Stagiaire ("One","pren","dep","prom",20084);
		Stagiaire stg85 = new Stagiaire ("One","pren","dep","prom",20085);
		Stagiaire stg86 = new Stagiaire ("One","pren","dep","prom",20086);
		Stagiaire stg87 = new Stagiaire ("One","pren","dep","prom",20087);
		Stagiaire stg88 = new Stagiaire ("One","pren","dep","prom",20088);
		Stagiaire stg89 = new Stagiaire ("One","pren","dep","prom",20089);
		Stagiaire stg90 = new Stagiaire ("One","pren","dep","prom",20090);
		Stagiaire stg91 = new Stagiaire ("One","pren","dep","prom",20091);
		Stagiaire stg92 = new Stagiaire ("One","pren","dep","prom",20092);
		Stagiaire stg93 = new Stagiaire ("One","pren","dep","prom",20093);
		Stagiaire stg94 = new Stagiaire ("One","pren","dep","prom",20094);
		Stagiaire stg95 = new Stagiaire ("One","pren","dep","prom",20095);
		Stagiaire stg96 = new Stagiaire ("One","pren","dep","prom",20096);
		Stagiaire stg97 = new Stagiaire ("One","pren","dep","prom",20097);
		Stagiaire stg98 = new Stagiaire ("One","pren","dep","prom",20098);
		Stagiaire stg99 = new Stagiaire ("One","pren","dep","prom",20099);

		maListObservable.addAll(stg10, stg11, stg12, stg13, stg14, stg15, stg16, stg17, stg18, stg19, stg20, stg21, stg22, stg23, stg24, stg25, stg26, stg27, stg28, stg29, stg30, stg31, stg32, stg33, stg34, stg35, stg36, stg37, stg38, stg39, stg40, stg41, stg42, stg43, stg44, stg45, stg46, stg47, stg48, stg49, stg50, stg51, stg52, stg53, stg54, stg55, stg56, stg57, stg58, stg59, stg60, stg61, stg62, stg63, stg64, stg65, stg66, stg67, stg68, stg69, stg70, stg71, stg72, stg73, stg74, stg75, stg76, stg77, stg78, stg79, stg80, stg81, stg82, stg83, stg84, stg85, stg86, stg87, stg88, stg89, stg90, stg91, stg92, stg93, stg94, stg95, stg96, stg97, stg98, stg99);
	
		
//		for (Stagiaire obj : maListObservable ) {
//			System.out.println(obj.getDateEntree());
//		}
		
//		for (int i= 10; i<499 ; i++) {
//			System.out.println("Stagiaire stg" +i+ " = new Stagiaire (\"One\",\"pren\",\"dep\",\"prom\",200" +i+");");
//		}
		
		for (int i= 10; i<499 ; i++) {
			System.out.print(", stg"+i);
			
			
		ExportPDF.exportListePDF(maListObservable);
		*/
		
		
		 AidePDF.monPDFgo ();
		}
		

	}

	
