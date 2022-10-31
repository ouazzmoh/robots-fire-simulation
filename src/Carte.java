
public class Carte {
	/**
	 * classe Carte, caracterisée par la taille de ses cases (en mètres, et pas la taille de tracé en pixels) , son nombre de lignes
	 * son nombres de colonnes qui sert à définir la matrice des cases, enfin la matrice des cases
	 * qui contient les diffrentes cases.
	 */
	private int tailleCases;
	private int nbLignes;
	private int nbColonnes;
	private Case[][] carte;
	/**
	 * un premier constructeur, qui crée un carte vide
	 * @param tailleCases taille des cases en mètres
	 * @param nbLignes nombre de cases dans chaque ligne
	 * @param nbColonnes nombre de cases dans chaque colonne
	 */
	public Carte(int tailleCases, int nbLignes, int nbColonnes) {
		this.tailleCases = tailleCases;
		this.nbLignes = nbLignes;
		this.nbColonnes = nbColonnes;
		this.carte = new Case[nbLignes][nbColonnes];
	}
	/**
	 * Un deuxième constructeur, qui crée une carte dont les cases existent déjà
	 * @param tailleCases taille des cases en mètres
	 * @param nbLignes nombre de cases dans chaque ligne
	 * @param nbColonnes nombre de cases dans chaque colonne
	 * @param carte la matrice de cases
	 */
	public Carte(int tailleCases, int nbLignes, int nbColonnes, Case[][] carte) {
		this.tailleCases = tailleCases;
		this.nbLignes = nbLignes;
		this.nbColonnes = nbColonnes;
		this.carte = carte;
	}
	public int getNbLignes() {
		return nbLignes;
	}
	public int getNBColonnes() {
		return nbColonnes;
	}
	public int getTailleCases() {
		return tailleCases;
	}
	public Case getCase(int lig, int col) {
		return carte[lig][col];
	}
	public Case[][] getCarte(){
		return carte;
	}
	public void setCase(int lig, int col, Case new_case) {
		carte[lig][col] = new_case;
	}
	/**
	 * Méthode qui vérifie l'existence d'une case voisine dans la direction donnée
	 * @param src la cases qu'on vérifie si elle a un voisin ou non
	 * @param dir la direction du voisin eventuel
	 * @return boolean true si le voisin existe, false sinon
	 */
	public boolean voisinExiste(Case src, Direction dir) {
		switch(dir) {
			case NORD:
				if (src.getLigne() == 0) {
					return false;
				}
				return true;
			case SUD:
				if (src.getLigne() == nbLignes - 1) {
					return false;
				}
				return true;
			case OUEST:
				if (src.getColonne() == 0) {
					return false;
				}
				return true;
			case EST:
				if (src.getColonne() == nbColonnes - 1) {
					return false;
				}
				return true;
			default :
				return false;
		}	
	}
	/**
	 * méthode qui nous permet d'accéder au voisin de la case src
	 * @param src case qu'on veut accéder à son voisin
	 * @param dir direction du voisin
	 * @return le voisin s'il existe
	 */
	public Case getVoisin(Case src, Direction dir) {
		int new_lig = src.getLigne();
		int new_col = src.getColonne();
		if (voisinExiste(src, dir)) {
			switch(dir) {
			case NORD:
				new_lig -= 1;
			case SUD:
				new_lig += 1;
			case OUEST:
				new_col -= 1;
			case EST:
				new_col += 1;
		}
		return carte[new_lig][new_col];
		}
		else {
			throw new IllegalArgumentException("ce voisin n'existe pas!!!");
		}
	}
}
