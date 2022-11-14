import java.util.*;

public abstract class Robot {
	
	
	/**
	 * Classe Robot, une classe abstraite des Robots, qui vont éteindre les incendies
	 */
	protected double vitesse;
	protected Case position;
	protected Carte carte;
	
	protected long dateArrive = (long)0; // = 0 si le robot ne bouge pas sinon = le nombre d'etapes pour qu'il arrive
	
	protected boolean occupeDeplace = false;
	protected boolean occupeIncendie = false;
	protected boolean occupeCharge = false;
	
	
	public long getDateArrive() {
		return dateArrive;
	}


	/**
	 * Constructeur public,
	 * @param position case dans laquelle le robot se trouve
	 */
	public Robot(Case position, Carte carte) {
		this.position = position;
		this.carte = carte;
	}
	
	
	/**
	 * Constructeur public
	 * @param vitesse vitesse du robot
	 * @param position cose dans laquelle le robot se trouve
	 */
	public Robot(double vitesse, Case position, Carte carte) {
		this.vitesse = vitesse;
		this.position = position;
		this.carte = carte;
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
	
	public long max(long a, long b) {
		if (a < b) {
			return b;
		}
		return a;
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
			long dateToAdd = max((long) 1,(long) (temps) / 100) ; //temps d'attente pour le deplacement
			dateArrive += dateToAdd;
			simulateur.ajouteEvenement(new EventRobotDeplace(dateArrive, dir, this, caseArrivee.getNature()));
			
		}
	}

	
	/**********Les methodes pour les incendies************/
	
	/**
	 * Fonction pour eteindre les incendies
	 * On suppose que le robot est deja sur une case avec incendie
	 * @param incendieTableau
	 * @param dateCourante
	 * @param simulateur
	 */
	public void eteindreIncendie(long dateCourante, Simulateur simulateur) {
		long dateToAdd = (long) 4; //temps d'attente pour l'extinction (4 is a placeholder for later)
		System.out.println("Robot is shuting down the fire, time_needed ---->" + dateToAdd + " steps");
		dateArrive += dateToAdd;
		simulateur.ajouteEvenement(new EventRobotFire(dateArrive, this, simulateur.incendie));
		
		
	}
	
	
//	public void eteindreIncendieChemin(Incendie incendie, long dateCourante, Simulateur simulateur) {
//		long dateToAdd = (long) 4;
//		
//	}
	
	
	/*********Les methodes pour le remplissage d'eau*********/
	/**
	 * Fonction pour remplir le reservoir du robot
	 * @param dateCourante
	 * @param simulateur
	 */
	public void remplirReservoir(long dateCourante, Simulateur simulateur) {
		long dateToAdd = (long) 2; //temps d'attente pour le remplissage du reservoir (2 is a placeholder for later)
		System.out.println("Le robot est en train de remplir son reservoir, temps necessaire ----->" + dateToAdd + "steps");
		dateArrive += dateToAdd;
		simulateur.ajouteEvenement(new EventRobotCharge(dateArrive, this));
		
	}
	
	
	
	
	/*********Les methodes pour implementer les strategies*********/
	
	/**
	 * Fonction pour que le robot calcule son chemin
	 * On n'appelle la fonction que si le robot peut s'y rendre a destination
	 * @param destination
	 * @return chemin (LinkedList)
	 */
	public LinkedList<Direction> calculeChemin(Case destination){
		Chemin cheminRobot = new Chemin(this, carte, position, destination);
		return cheminRobot.getChemin();
	}
	
	
	/**
	 * Programmen le deplacement du robot vers la destination ou il existe du feu
	 * @param destination  (La destination est la fin du chemin, on suppose que ca contient un incendie)
	 * @param simulateur
	 */
	public void programmeEvents(Case destination, Simulateur simulateur) {
		Iterator<Direction> it = calculeChemin(destination).iterator();
		int dateWhereToAdd = 1; //La date ou il faut ajouter l'evenement
		while(it.hasNext()) {
			this.deplacerEffectivement(it.next(), carte, dateWhereToAdd,simulateur);
			dateWhereToAdd++;
		}
		//On suppose ici qu'on arrive a une incendie
		this.eteindreIncendie(dateWhereToAdd, simulateur);
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
