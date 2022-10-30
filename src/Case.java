
public class Case {
	private int ligne;
	private int colonne;
	private NatureTerrain nature;
	public Case(int ligne, int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.nature = null;
	}
	public Case(int ligne, int colonne, NatureTerrain nature) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.nature = nature;
	}
	public String toString() {
		return " la case de nature " + nature + " se situe dans la ligne " + ligne + " et la colonne " + colonne;
	}
	public int getLigne() {
		return ligne;
	}
	public int getColonne() {
		return colonne;
	}
	public NatureTerrain getNature() {
		return nature;
	}
	public void setNature(NatureTerrain nature) {
		this.nature = nature;
	}
}
