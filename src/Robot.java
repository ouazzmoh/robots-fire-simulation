
public abstract class Robot {
	/**
	 * Classe Robot, une classe abstraite des Robots, qui vont éteindre les incendies
	 */
	protected double vitesse;
	protected Case position;
	/**
	 * Constructeur public,
	 * @param position case dans laquelle le robot se trouve
	 */
	public Robot(Case position) {
		this.position = position;
	}
	/**
	 * Constructeur public
	 * @param vitesse vitesse du robot
	 * @param position cose dans laquelle le robot se trouve
	 */
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
	/**
	 * Méthode absrtaite, qui nous permet de connaitre la vitesse du robot sachant la nature du terrain
	 * sur lequel il se trouve
	 * @param nature nature du terrain
	 * @return vitesse : double
	 */
	abstract double getVitesse(NatureTerrain nature);
	/**
	 * Méthode qui permet de deverser l'eau 
	 * @param vol volume d'eau à derveser
	 */
	abstract  void deverserEau(int vol);
	
	/**
	 * Méthode qui permet de remplir le reservoir
	 */
	abstract  void remplirEau();
	/**
	 * Méthode qui permet de retourner le reservoir
	 */
	abstract double getReservoir();
	/**
	 * Méthode qui nous permet de connaitre si le robot peut acceder à une case ou non
	 * @param nature nature du terrain de la case 
	 * @return boolean : true si le robot peur acceder a ce type de terrain, false sinon.
	 */
	abstract boolean has_accessto(NatureTerrain nature);
}
