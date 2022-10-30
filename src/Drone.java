
public class Drone extends Robot {
	private double reservoir;
	public Drone(Case position, double reservoir) {
		super(100, position);
		this.reservoir = reservoir;
	}
	public Drone(double vitesse, Case position, double reservoir) {
		super(vitesse, position);
		if (this.vitesse > 150) {
			this.vitesse = 150;
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
	public void remplirReservoir() {
		reservoir = 10000;
	}
	@Override
	public boolean has_accessto(NatureTerrain nature) {
		return true;
	}
	
}
