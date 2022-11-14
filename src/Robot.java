import java.util.LinkedList;
public abstract class Robot {
	
	
	/**
	 * Classe Robot, une classe abstraite des Robots, qui vont éteindre les incendies
	 */
	protected double vitesse;
	protected Case position;
	
	protected long dateArrive; // = 0 si le robot ne bouge pas sinon = le nombre d'etapes pour qu'il arrive
	protected long dateExtinction;
	protected long dateRemplissage;
	protected boolean isAvailable;
	
	
	/**
	 * Constructeur public,
	 * @param position case dans laquelle le robot se trouve
	 */
	public Robot(Case position) {
		this.position = position;
		this.dateArrive  = (long) 0;
		this.dateExtinction = (long) 0;
		this.dateRemplissage = (long) 0;
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
	
	
	
	/**********Les methodes pour le deplacement************/
	/**
	 * Méthode qui nous permet de connaitre le temps de deplacement 
	 * @param caseArrivee caseArrivee
	 * @param carte map 
	 * @return le temps de deplacement : double
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
	
	

	/**
	 * Méthode qui nous permet de ajouter l evenement effectif dans la liste des
	 * evenements qui sera executé dans une date calculée
	 * @param dir Direction (NORD, SUD, OUEST, EST)
	 * @param carte map 
	 * @param simulateur simulateur du jeu
	 */
	public void deplacerEffectivement(Direction dir, Carte carte, long dateCourante, Simulateur simulateur) {
		Case caseArrivee = carte.getVoisin(position, dir);
		if (this.has_accessto(caseArrivee.getNature())) {
			double temps = tempsDeplacement(caseArrivee, carte);
			System.out.println(temps);
			long dateToAdd = (long) (temps) / 100 ; //temps d'attente pour le deplacement
			simulateur.ajouteEvenement(new EventRobotDeplace(dateToAdd + dateCourante + dateArrive + dateExtinction, dir, this, caseArrivee.getNature()));
			//Le robot va etre alors on mouvement et il arrive dans ...
			dateArrive = dateToAdd +  dateArrive;
		}
	}
	
	
	/**********Les methodes pour les incendies************/
	
	/**
	 * Fonction pour eteindre les incendies
	 * @param incendieTableau
	 * @param dateCourante
	 * @param simulateur
	 */
	public void eteindreIncendie(Incendie[] incendieTableau, long dateCourante, Simulateur simulateur) {
		long dateToAdd = (long) 4; //temps d'attente pour l'extinction (4 is a placeholder for later)
		System.out.println("Robot is shuting down the fire, time_needed ---->" + dateToAdd + " steps");
		simulateur.ajouteEvenement(new EventRobotFire(dateCourante + dateToAdd + dateArrive + dateExtinction + dateRemplissage, this, simulateur.incendie));
		dateExtinction = dateToAdd + dateExtinction;
		
	}
	
	
	/*********Les methodes pour le remplissage d'eau*********/
	/**
	 * Fonction pour remplir le reservoir du robot
	 * @param dateCourante
	 * @param simulateur
	 */
	public void remplirReservoir(long dateCourante, Simulateur simulateur) {
		long dateToAdd = (long) 2; //temps d'attente pour le remplissage du reservoir (2 is a placeholder for later)
		System.out.println("Le robot est en train de remplir son reservoir, temps necessaire ----->" + dateToAdd + "steps");
		simulateur.ajouteEvenement(new EventRobotCharge(dateCourante + dateToAdd + dateArrive + dateExtinction + dateRemplissage, this));
		dateRemplissage = dateToAdd + dateRemplissage;
	}
	
	public boolean isAvailable() {
		return isAvailable;
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
	/**
	 * Méthode qui trouve le plus court chemin entre la position du robot et la destination.
	 * @param destination
	 * @return LinkedList<Direction> 
	 */
	//abstract LinkedList<Direction> findWayTo(Case destination);
	
	/*public void goTo(Case destination) {
		LinkedList<Direction> path = findWayTo(destination);
		//TODO
	}*/
}
