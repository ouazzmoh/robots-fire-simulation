import gui.ImageElement;

import java.util.Objects;

import gui.GUISimulator;

public class Case {
	public void setLigne(int ligne) {
		this.ligne = ligne;
	}

	public void setColonne(int colonne) {
		this.colonne = colonne;
	}
	/**
	 * classe case, qui nous permet de caractériser une case par :
	 * la ligne et la colonne  où elle se trouve
	 * et la nature de terrain dans cette case 
	 */
	private int ligne;
	private int colonne;
	private NatureTerrain nature;
	//Les deux attributs suivants nous permettent de faire le redessin optimale
	private Robot currentRobot; //Make it an array cuz multiple robots in same position
	private Incendie currentIncendie;
	
	public Robot getCurrentRobot() {
		return currentRobot;
	}

	public void setCurrentRobot(Robot currentRobot) {
		this.currentRobot = currentRobot;
	}

	public Incendie getCurrentIncendie() {
		return currentIncendie;
	}

	public void setCurrentIncendie(Incendie currentIncendie) {
		this.currentIncendie = currentIncendie;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Case other = (Case) obj;
		return colonne == other.colonne && ligne == other.ligne;
	}
	
	/**
	 * Constructeur public qui crée un case sans connaitre la  nature de terrain
	 * @param ligne ligne où elle se triuve dans la carte
	 * @param colonne colonne où elle se trouve dans la carte
	 */
	public Case(int ligne, int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.nature = null;
		this.currentIncendie = null;
	}
	/**
	 * Constructeur public qui crée une case avec tous ses attributs 
	 * @param ligne ligne où elle se trouve dans la carte
	 * @param colonne colonne où elle se trouve dans la carte
	 * @param nature nature du terrain de la case
	 */
	public Case(int ligne, int colonne, NatureTerrain nature) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.nature = nature;
		this.currentRobot = null;
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
	/**
	 * Méthode qui sert a définir la nature du terrain d'une case créé par le premier constructeur, 
	 * ou bien changer la nature du terrain ( ce qui n'est pas utile dans notre cas d'utilisation de la carte)
	 * @param nature (nouvelle) nature du terrain de la case
	 */
	public void setNature(NatureTerrain nature) {
		this.nature = nature;
	}
	
}
