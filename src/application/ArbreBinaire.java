package application;

public class ArbreBinaire {
	// ATTRIBUTS
	private Noeud racine;

	// CONSTRUCTEUR vide par défaut
	public ArbreBinaire () {
		this.racine = null;
	}

	/* METHODE POUR AJOUTER UN NOEUD
	 * Elle est récursive et retourne le noeud ajouté
	 * 
	 * ETAPE 1 : Déterminer la condition de fin de récursivité  
	 * 
	 * La condition de fin intervient si le noeud courrant (celui auquel je compare ma valeur) est  null :      
	 * > soit il n'a pas de racine, 
	 * > soit c'est la place où je dois ajouter le noeud
	 * Je crée le noeud avec stagiaire puis je retourne ce noeud
	 *
	 * ETAPE 2 : Déterminer les étapes de la partie récursive
	 * 
	 * On effectue une comparaison															/!!!!\  Nécessite de redéfinir compareTo ()
	 * Cas n°2.1 : Si current.valeur > valeur ajout, je vais à gauche
	 * Je rappelle la fonction ajout (current.gauche, valeurAjout)
	 * Cas n°2.2 : Si current.valeur < valeur d'ajout, je vais à droite
	 * Je rappelle a fonction ajout (current.droit, valeurAjout)
	 */

	public Noeud ajout (Noeud current, Stagiaire ajoutStagiaire) {
		// ETAPE 1
		if (current == null) {
			// Je retourne le nouveau noeud crée à partir de la valeur du noeud
			return new Noeud(ajoutStagiaire);		
		}
		// ETAPE 2 																			/!!!!\  Nécessite de redéfinir compareTo ()
		if (current.getStagiaire().compareTo (ajoutStagiaire) > 0) {
			current.setNoeudGauche(ajout(current.getNoeudGauche(), ajoutStagiaire));
		} else { 	// <-> valeurAjout >=  current.getCle()
			// Je modifie le noeud droit et à la place je mettrai le nv ss arbre, le ss arbre avec le noeud ajouté 
			current.setNoeudDroit(ajout(current.getNoeudDroit(), ajoutStagiaire));	
		} 
		// Retour du noeud courant 
		return current;
	}

	// AFFICHAGE DES PARCOURS DANS L'ARBRE BINAIRE

	// Parcours INFIXE (Gauche Noeud Droit)

	public void afficherInfixe (Noeud current) {
		// GAUCHE
		if (current.getNoeudGauche() != null) {
			afficherInfixe (current.getNoeudGauche());
		}
		// NOEUD (Si current == null)
		System.out.println(current.getStagiaire());
		// DROIT
		if (current.getNoeudDroit() != null) {
			afficherInfixe (current.getNoeudDroit());
		} 
	}

	/* SUPPRESSION D'UN NOEUD 																				(WORK IN PROGRESS )
	 * 3 méthodes coopèrent 
	 *
	 * Méthode 1 : <supprimer> recherche le nœud portant la clé à supprimer
	 *
	 * Méthode 2 : <supprimerNoeud> effectue la suppression 
	 * 
	 * Méthode 3 : <dernierDescendant> recherche le dernier descendant
	 */

	// METHODE SUPPRIMER

	public Noeud supprimer (int valeurSuppression, Noeud current) {
		return current;
	}

	// METHODE SUPPRIMER UN NOEUD

		//	private Noeud supprimerNoeud (Noeud current) {

		// Cas n°1 : Suppression d'une feuille 
		// Cas n°2 : Suppression d'un noeud avec un seul sous-arbre
		// Fils droit null mais un fils gauche existe
		// Fils gauche null mais un fils droit existe
		// Cas n°3 : Suppression d'un noeud avec deux sous-arbre	
		// Noeud ayant un fils droit ET un fils gauche

		// 1) Je vais chercher le dernier descendant : le plus petit du sous-arbre droit (ou le plus grand du sous arbre gauche) - je le stocke dans remplacant

		// 2) Je prends la *valeur* de ce noeud et je la met à la place de ma racine 

		// 3) Je supprime le remplacant - je me mets dans l'arbre droit et je supprime le noeud remplaçant
		//	}
		//	return current;
		//}

// RECHERCHE DU DERNIER DESCENDANT

private Noeud dernierDescendant (Noeud current) {
	// Si current n'a pas de descendant
	// Sinon je rappelle la méthode  dernierDescendant (Noeud current)
	// dernierDescendant (current.getGauche());
	return current.getNoeudGauche();
}

// GETTER SETTER 

public Noeud getRacine() {
	return racine;
}

public void setRacine(Noeud racine) {
	this.racine = racine;
}

}

