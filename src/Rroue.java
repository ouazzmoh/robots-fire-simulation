
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
	public Rroue(Case position, double reservoir) {
		super(80, position);
		this.reservoir = reservoir;
	}
	/**
	 * Constructeur public, qui crée un nouveau robot à roue avec une vitesse donnée
	 * @param vitesse vitesse du robot à roues
	 * @param position case dans laquelle le robot se trouve.
	 * @param reservoir volume d'eau contenu dans le reservoir du robot à roues
	 */
	public Rroue(double vitesse, Case position, double reservoir) {
		super(vitesse, position);
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
}
