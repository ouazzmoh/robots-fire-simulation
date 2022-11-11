

public class Rpatte extends Robot {
	/**
	 * la classe Rpatte, une classe fille de la classe Robot, les robots à pattes n'ont pas de reservoir
	 */
	
	/**
	 * Constructeur public, qui crée un nouveau Robot à pattes avec la vitesse par défaut 30 km/h
	 * @param position case dans laquelle le robot se trouve
	 */
//	public Rpatte(Case position) {
//		super(30, position);
//	}
	/**
	 * Constructeur public, crée un nouveau Robot à pattes avec une vitesse donnée 
	 * @param vitesse vitesse du robot à pattes
	 * @param position position dans laquelle le robot se trouve
	 */
	public Rpatte(double vitesse, Case position, Carte carte) {
		super(vitesse, position, carte);
		if (vitesse == 0) {
			this.vitesse = 30;
		}
	}
	
	@Override
	public Robot deepCopy() {
		Robot newRobot = new Rpatte(this.vitesse, new Case(this.position.getLigne(), this.position.getColonne(), this.position.getNature()), new Carte(this.carte));
		return newRobot;
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

}
