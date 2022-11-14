import java.util.LinkedList;

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
	public Drone(Case position, double reservoir) {
		super(100, position);
		this.reservoir = reservoir;
	}
	/**
	 * Constructeur public, qui crée un nouveau drone 
	 * @param vitesse vitesse du drone, qui ne doit pas dépasser 150 km/h
	 * @param position case dans laquelle le drone se trouve dans la carte
	 * @param reservoir volume d'eau dans son reservoir
	 */
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
	public void remplirEau() {
		reservoir = 10000;
	}
	@Override
	public boolean has_accessto(NatureTerrain nature) {
		return true;
	}
	//@Override
	//public LinkedList<Direction> findWayTo(Case destination){
		//return new LinkedList<Direction>();
	//}
	
}
