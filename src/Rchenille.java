

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
	public Rchenille(Case position, Carte carte, double reservoir) {
		super(60, position, carte);
		this.reservoir = reservoir;
	}
	/**
	 * Constructeur public, qui crée un robot à chenilles avec une vitesse donnée (qui ne doit pas dépasser 80 km/h)
	 * @param vitesse vitesse du robot
	 * @param position case dans laquelle le robot à chenilles se trouve
	 * @param reservoir volume d'eau dans le reservoir du robot à chenilles
	 */
	public Rchenille(double vitesse, Case position, Carte carte, double reservoir) {
		super(vitesse, position, carte);
		if (vitesse > 80) {
			this.vitesse = 80;
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

}
