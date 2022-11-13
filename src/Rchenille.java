import gui.ImageElement;

public class Rchenille extends Robot {
	/**
	 * Classe Rchenille, une classe fille de la classe Robot, qui caractérise un nouveau type de robots:
	 * Les robots à chenilles, qui not une vitesse maximale de 80km/h, un reservoir de 2000 L,
	 * et qui ne peuvent pas traverser l'eau et les roches.
	 */
	private double reservoir;
	/**
	 * Constructeur public, qui construit un robot à chenille avec une vitesse par défaut 60 km/h
	 * @param position case dans laquelle le robot à chenilles se trouve
	 * @param reservoir volume d'eau dans son reservoir.
	 */
	public Rchenille(Case position, double reservoir, Carte carte) {
		super(60 * 1000 / 3600, position, carte);
		this.reservoir = reservoir;
	}
	/**
	 * Constructeur public, qui crée un robot à chenilles avec une vitesse donnée (qui ne doit pas dépasser 80 km/h)
	 * @param vitesse vitesse du robot
	 * @param position case dans laquelle le robot à chenilles se trouve
	 * @param reservoir volume d'eau dans le reservoir du robot à chenilles
	 */
	public Rchenille(double vitesse, Case position, double reservoir, Carte carte) {
		super(vitesse, position, carte);
		if (vitesse > 80 * 1000 / 3600) {
			this.vitesse = 80 * 1000 / 3600;
		}
		this.reservoir = reservoir;
	}
	public String toString() {
		return " le robot à chenilles a " + reservoir + " litres dans son réservoir et se situe dans la position " + 
				super.getPosition().toString() + " et se déplace à " + vitesse;
	}
	@Override
	public double getVitesse(NatureTerrain nature) {
		switch(nature) {
			case FORET:
				return vitesse / 2;
			default :
				return vitesse;
		}
	}

	@Override
	public void deverserEau(int vol) {
		reservoir -= vol;
	}
	public double getReservoir() {
		return reservoir;
	}
	public void remplirEau() {
		reservoir = 2000;
	}

	@Override
	public boolean has_accessto(NatureTerrain nature) {
		switch(nature) {
			case EAU:
				return false;
			case ROCHE:
				return false;
			default:
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
		
		gui.addGraphicalElement(new ImageElement(position.getColonne() * tailleCases_width, position.getLigne() *tailleCases_length, "./images/RCHENILLE.png", tailleCases_width, tailleCases_length, null));
	}

}
