
public class Rpatte extends Robot {
	/**
	 * la classe Rpatte, une classe fille de la classe Robot, les robots à pattes n'ont pas de reservoir
	 */
	
	/**
	 * Constructeur public, qui crée un nouveau Robot à pattes avec la vitesse par défaut 30 km/h
	 * @param position case dans laquelle le robot se trouve
	 */
	public Rpatte(Case position) {
		super(30, position);
	}
	/**
	 * Constructeur public, crée un nouveau Robot à pattes avec une vitesse donnée 
	 * @param vitesse vitesse du robot à pattes
	 * @param position position dans laquelle le robot se trouve
	 */
	public Rpatte(double vitesse, Case position) {
		super(vitesse, position);
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
