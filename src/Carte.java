
public class Carte {
	private int tailleCases;
	private int nbLignes;
	private int nbColonnes;
	private Case[][] carte;
	public Carte(int tailleCases, int nbLignes, int nbColonnes) {
		this.nbLignes = nbLignes;
		this.nbColonnes = nbColonnes;
		this.carte = new Case[nbLignes][nbColonnes];
	}
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
	public void setCase(int lig, int col, Case new_case) {
		carte[lig][col] = new_case;
	}
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
	public Case getVoisin(Case src, Direction dir) {
		int new_lig = src.getLigne();
		int new_col = src.getColonne();
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
}
