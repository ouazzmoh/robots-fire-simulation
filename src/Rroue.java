import gui.ImageElement;

public class Rroue extends Robot {
	/**
	 * Classe Rroue, une classe fille de la classe Robot, qui définit un nouveau type de robot : robot à roues
	 * qui a un reservoir de 5000 L et une vitesse par défaut de 80 km/h
	 */
	private double reservoir;
	/**
	 * Constructeur public, qui crée un nouveau robot à roues, avec une vitesse par défaut de 80 km/h
	 * @param position case dans laquelle le robot se trouve.
	 * @param reservoir volume d'eau contenu dans le reservoir du robot à roues.
	 */
	public Rroue(Case position, double reservoir, Carte carte) {
		super(80 * 1000 / 3600, position, carte);
		this.reservoir = reservoir;
	}
	/**
	 * Constructeur public, qui crée un nouveau robot à roue avec une vitesse donnée
	 * @param vitesse vitesse du robot à roues
	 * @param position case dans laquelle le robot se trouve.
	 * @param reservoir volume d'eau contenu dans le reservoir du robot à roues
	 */
	public Rroue(double vitesse, Case position, double reservoir, Carte carte) {
		super(vitesse, position, carte);
		this.reservoir = reservoir;
	}
	@Override
	public String toString() {
		return "le robot à roues a " + reservoir  + " litres d'eau dans son réservoir, et est dans la position " + 
				super.getPosition().toString() + " et roule à une vitesse de " + vitesse;
	}
	@Override
	public double getVitesse(NatureTerrain nature) {
		return vitesse;
	}
	@Override
	public void deverserEau(int vol) {
		reservoir -= vol;
	}
	/**
	 * méthode qui remplit le reservoir du robot à roues au maximum.
	 */
	public void remplirEau() {
		reservoir = 5000;
	}
	public double getReservoir() {
		return reservoir;
	}
	@Override
	public boolean has_accessto(NatureTerrain nature) {
		switch(nature) {
			case TERRAIN_LIBRE:
				return true;
			case HABITAT:
				return true;
			default:
				return false;
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
		
		double currentWaterPercentage = (reservoir/5000)*100; 
		int waterBar;
		if (currentWaterPercentage < 25) {
			waterBar = 0;
		}
		else if (currentWaterPercentage < 50 && currentWaterPercentage >= 25) {
			waterBar = 25;
		}
		else if (currentWaterPercentage < 75 && currentWaterPercentage >= 50) {
			waterBar = 50;
		}
		else if (currentWaterPercentage < 100 && currentWaterPercentage >= 75) {
			waterBar = 75;
		}
		else {
			waterBar = 100;
		}
		
		gui.addGraphicalElement(new ImageElement(position.getColonne() * tailleCases_width, position.getLigne() *tailleCases_length, "./images/WATERBAR"+ waterBar +".png", tailleCases_width, tailleCases_length, null));
		gui.addGraphicalElement(new ImageElement(position.getColonne() * tailleCases_width, position.getLigne() *tailleCases_length, "./images/RROUE.png", tailleCases_width, tailleCases_length, null));
	}
}
