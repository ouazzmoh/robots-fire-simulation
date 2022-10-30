
public abstract class Robot {
	protected double vitesse;
	protected Case position;
	public Robot(Case position) {
		this.position = position;
	}
	public Robot(double vitesse, Case position) {
		this.vitesse = vitesse;
		this.position = position;
	}
	public String toString() {
		return " le robot a se déplace avec une vitesse de " + vitesse + " km/h et est dans la case " + position.toString();
	}
	public Case getPosition() {
		return position;
	}
	public  void setPosition(Case new_case) {
		position = new_case;
	}
	abstract double getVitesse(NatureTerrain nature);
	abstract  void deverserEau(int vol);
	abstract boolean has_accessto(NatureTerrain nature);
}
