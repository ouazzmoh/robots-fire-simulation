
public class Rroue extends Robot {
	private double reservoir;
	public Rroue(Case position, double reservoir) {
		super(80, position);
		this.reservoir = reservoir;
	}
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
	public void remplirReservoir() {
		reservoir = 5000;
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
