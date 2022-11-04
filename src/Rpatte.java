import gui.ImageElement;

public class Rpatte extends Robot {
	/**
	 * la classe Rpatte, une classe fille de la classe Robot, les robots à pattes n'ont pas de reservoir
	 */
	
	/**
	 * Constructeur public, qui crée un nouveau Robot à pattes avec la vitesse par défaut 30 km/h
	 * @param position case dans laquelle le robot se trouve
	 */
	public Rpatte(Case position, Carte carte) {
		super(30, position, carte);
	}
	/**
	 * Constructeur public, crée un nouveau Robot à pattes avec une vitesse donnée 
	 * @param vitesse vitesse du robot à pattes
	 * @param position position dans laquelle le robot se trouve
	 */
	public Rpatte(double vitesse, Case position, Carte carte) {
		super(vitesse, position, carte);
	}
	@Override
	public double getVitesse(NatureTerrain nature) {
		switch(nature) {
			case ROCHE:
				return 10;
			default :
				return vitesse;
		}
	}

	@Override
	void deverserEau(int vol) {
		return;
	}
	void remplirEau() {
		return;
	}
	public double getReservoir() {
		return Double.POSITIVE_INFINITY;
	}
	@Override
	boolean has_accessto(NatureTerrain nature) {
		switch(nature) {
			case EAU:
				return false;
			default : 
				return true;
		}
	}
	
	@Override
	public void draw() {
		
		int nbLig = carte.getNbLignes();
		int nbCol = carte.getNBColonnes();
		int xMax = gui.getWidth();
        xMax -= xMax % 10 + 50;  //50 est la taille de la partie non utile de la fenetre
        int yMax = gui.getHeight();
        yMax -= yMax % 10 + 80; // 80 est la taille de la partie non utile de la fenetre
        
		int tailleCases_length = (yMax)/nbLig;
		int tailleCases_width = (xMax)/nbCol;
		
		gui.addGraphicalElement(new ImageElement(position.getColonne() * tailleCases_width, position.getLigne() *tailleCases_length, "./images/RPATTE.png", tailleCases_width, tailleCases_length, null));
	}

}
