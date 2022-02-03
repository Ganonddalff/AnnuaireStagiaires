package application;

import java.util.*;
import java.io.*;

public class Arbre {
	Stagiaire stagiaire;
	Arbre filsG, filsD;
	
	Arbre(Arbre g, Stagiaire stagiaire, Arbre d)
	{
		
		filsG = g;
		this.stagiaire = stagiaire;
		filsD = d;
		
	}
}