package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.Vector;

public class FichierATraiter {
    private BufferedReader bfr;
    
    //Constructeurs
    public FichierATraiter(){
        bfr=null;
    }
    
   public FichierATraiter(String nomFichier){
    try{
        //Création d'un flux pour le fichier texte
        //le nom du fichier est passé en argument
        FileReader in = new FileReader(nomFichier);
        bfr= new BufferedReader(in);
    	}catch (IOException e){
        System.out.println("Pb entrée sortie :" + e.getMessage());
    	}
   }
   
   
   //Transforme le fichier en objet Stagiaire
   public List<Stagiaire> fabriqueChaine(){
	   Stagiaire stagiaire;
 //      StringBuffer lignebf=new StringBuffer();
       String nom;
       String prenom;
       String codeDepartement;
       String promo;
       int dateEntree;
       List<Stagiaire> listestagiaires = new Vector<Stagiaire>();
       int compteur=0; 
       int longMaxNom = 0;
       int longMaxPrenom = 0;
       int longMaxPromo = 0;

       try{
           do{
               nom= bfr.readLine() ;

  

               if(nom!=null){
            	   
                   if (nom.length()> longMaxNom) {
                	   longMaxNom=nom.length();
                   }
                   while(nom.length()<25) {
                	   nom+=" ";
                   }
                 
                   
               prenom=bfr.readLine() ;           	   
            	  
               if (prenom.length()> longMaxPrenom) {
            	   longMaxPrenom=prenom.length();
               }
               
               while(prenom.length()<25) {
            	   prenom+=" ";
               }
;               
               		codeDepartement=null;
                    codeDepartement=bfr.readLine();
                    if (codeDepartement == "") {
                    	codeDepartement="00";
                    }
            		
                promo=bfr.readLine() ; 
                if (promo.length()> longMaxPromo) {
             	   longMaxPromo=promo.length();
                }
            	
                while(promo.length()<15) {
             	   promo+=" ";
                }

            	try  { 
            		dateEntree=Integer.parseInt(bfr.readLine());
            	} catch(NumberFormatException e)  {
            		dateEntree=0;
            		System.out.println(" y a un pb sur dateEntree de "+nom+" "+prenom);
            	}               

  

               //System.out.println(nom+" "+prenom+" "+codeDepartement+" "+promo+" "+dateEntree);
               compteur++;

               
               if(codeDepartement == null ||  codeDepartement == "" || codeDepartement =="\r" || codeDepartement == " " || codeDepartement == "\n" || codeDepartement.length() < 2) {codeDepartement="YS";}
 //              System.out.println("---> "+codeDepartement+" "+codeDepartement.length());
               
               stagiaire= new Stagiaire(nom,prenom,codeDepartement,promo,dateEntree);
               listestagiaires.add(stagiaire);
              bfr.readLine();   // pour lire la ligne "*"

               }
           }while(nom!=null); 
           System.out.println(" nbre de caractere max pour nom est de "+longMaxNom+" pour prenom "+longMaxPrenom+" pour promo "+longMaxPromo);
           bfr.close();
       }catch(IOException e){
           System.out.println("Problème de lecture : " +e.getMessage());
           
       }
       System.out.println(compteur);
       return listestagiaires;
   }    
   
 


 public static void ecrire1BlocDsFichierBinaire(Stagiaire stagiaire,int index) {

	 
		try {
			RandomAccessFile raf = new RandomAccessFile("src/application/data/fichier.bin","rw");

			raf.seek(index*150);
			raf.writeChars(stagiaire.getNom()+stagiaire.getPrenom()+stagiaire.getCodeDepartement()+stagiaire.getPromo());					
			raf.writeInt(stagiaire.getDateEntree());
			raf.writeInt(-1);
			raf.writeInt(-1);
			raf.writeInt(-1);		
			
		
			raf.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	 
 }//ecrire
 
 public static String lire1BlocDsFichierBinaire(int index) {
		String lecture="";
		try {
			RandomAccessFile raf = new RandomAccessFile("src/application/data/fichier.bin","rw");


			raf.seek(index*150);
			

			for (int i=0;i<67;i++) {
				lecture+=raf.readChar();
			}
	
			for (int i=0;i<4;i++) {
				lecture+=String.valueOf(raf.readInt());
			}
			//System.out.println(lecture +" "+raf.length());
						
			raf.close();

			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return lecture;
	 
 }//lire
   
 
 
 
 
   
}


