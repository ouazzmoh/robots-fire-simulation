import gui.ImageElement;

public class Drone extends Robot {
	/**
	 * La classe Drone, une classe fille de la classe robot, qui caractérise un nouveau type de robots:
	 * Les drones, qui ont une vitesse maximale de 150 km/h, un réservoir de 10000 L et qui peuvent 
	 * traverser tout type de terrain. 
	 */
	private double reservoir;
	/**
	 * Constructeur public, qiu crée un nouveau drone qui a une vitesse par défaut (ie 100 km/h)
	 * @param position case dans laquelle le drone se trouve dans la carte
	 * @param reservoir volume d'eau dans son reservoir
	 */
	public Drone(Case position, double reservoir, Carte carte) {
		super(100 * 1000 / 3600, position, carte);
		this.reservoir = reservoir;
	}
	/**
	 * Constructeur public, qui crée un nouveau drone 
	 * @param vitesse vitesse du drone, qui ne doit pas dépasser 150 km/h
	 * @param position case dans laquelle le drone se trouve dans la carte
	 * @param reservoir volume d'eau dans son reservoir
	 */
	public Drone(double vitesse, Case position, double reservoir, Carte carte) {
		super(vitesse, position, carte);
		if (this.vitesse > 150 * 1000 / 3600) {
			this.vitesse = 150 * 1000 / 3600;
		}
		this.reservoir = reservoir;
	}
	@Override
	public String toString() {
		return "le Drone a " + reservoir  + " litres d'eau dans son réservoir, et est dans la position " + 
				super.getPosition().toString() + " et vole à une vitesse de " + vitesse;
	}
	@Override
	public double getVitesse(NatureTerrain nature) {
		return vitesse;
	}
	public double getReservoir() {
		return reservoir;
	}
	@Override
	public void deverserEau(int vol) {
		reservoir -= vol;
	}
	public void remplirEau() {
		reservoir = 10000;
	}
	@Override
	public boolean has_accessto(NatureTerrain nature) {
		return true;
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
		
		double currentWaterPercentage = (reservoir/10000)*100; 
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
		
		gui.addGraphicalElement(new ImageElement(position.getColonne() * tailleCases_width, position.getLigne() *tailleCases_length - 50, "./images/WATERBAR"+ waterBar +".png", tailleCases_width, tailleCases_length, null));
		gui.addGraphicalElement(new ImageElement(position.getColonne() * tailleCases_width, position.getLigne() *tailleCases_length, "./images/DRONE.png", tailleCases_width, tailleCases_length, null));
	}
	
}
