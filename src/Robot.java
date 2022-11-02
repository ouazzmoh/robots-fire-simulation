

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
		return " le robot a se déplace avec une vitesse de " + vitesse + " km/h et est dans la case " + this.position.toString();
	}
	public Case getPosition() {
		return position;
	}
	public  void setPosition(Case new_case) {
		this.position = new_case;
	}
	/**
	 * Méthode qui nous permet de connaitre le temps de deplacement 
	 * @param caseArrivee caseArrivee
	 * @param carte map 
	 * @param simulateur simulateur principal
	 * @return la Date specifique : double
	 */
	public double tempsDeplacement(Case caseArrivee, Carte carte) {
		// supposant caseArrivee est une case voisine 
		// si on ajoute l algo de chemin on peut effectuer ce calcul sur n'importe quelle case
		// on verifie deja si la case est accessible
		double vitesse1 = this.getVitesse(caseArrivee.getNature());
		double vitesse2 = this.getVitesse(this.position.getNature());
		double tailleCase = carte.getTailleCases();
		return (tailleCase)/(((vitesse1+vitesse2)*1000)/(2*3600));
	}
	public void deplacerEffectivement(Direction dir, Carte carte, Simulateur simulateur) {
		Case caseArrivee = carte.getVoisin(position, dir);
		if (this.has_accessto(caseArrivee.getNature())) {
			double temps = tempsDeplacement(caseArrivee, carte);
			int Date = (int) (temps/100) ;
			int DateCourante = simulateur.Date; // l indice du dernier evenement insere
			System.out.println(">>>>>>>>>>>>>>>>>>>><<" + Date);
			simulateur.ajouteEvenement(new EventRobotDeplace(Date + DateCourante, dir, this, caseArrivee.getNature()));
		}
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
