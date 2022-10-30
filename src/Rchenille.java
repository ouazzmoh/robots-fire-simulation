
public class Rchenille extends Robot {
	private double reservoir;
	public Rchenille(Case position, double reservoir) {
		super(60, position);
		this.reservoir = reservoir;
	}
	public Rchenille(double vitesse, Case position, double reservoir) {
		super(vitesse, position);
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
	public void remplirReservoir() {
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
