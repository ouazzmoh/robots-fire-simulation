
public class Rpatte extends Robot {
	public Rpatte(Case position) {
		super(30, position);
	}
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
