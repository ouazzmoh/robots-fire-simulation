import java.util.*;

public abstract class Robot {
	
	
	/**
	 * Classe Robot, une classe abstraite des Robots, qui vont éteindre les incendies
	 */
	protected double vitesse;
	protected Case position;
	protected Carte carte;
	
	long dateArrive;// = 0 si le robot ne bouge pas sinon = le nombre d'etapes pour qu'il arrive
	
	Case positionCourante;
	
		


	/**
	 * Constructeur public,
	 * @param position case dans laquelle le robot se trouve
	 */
	public Robot(Case position, Carte carte) {
		this.position = position;
		this.carte = carte;
		this.positionCourante = position;
		}
	
	public long getDateArrive() {
		return this.dateArrive;
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
		this.dateArrive = (long) 0;
		this.positionCourante = position;
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
		// si on ajoute l algo de Path on peut effectuer ce calcul sur n'importe quelle case
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
	
	public long min(long a, long b) {
		if (a < b) {
			if (a == 0) {
				return b;
			}
			return a;
		}
		if (b == 0) {
			return a;
		}
		return b;
	}
	

	/**
	 * Méthode qui nous permet de ajouter l evenement effectif dans la liste des
	 * evenements qui sera executé dans une date calculée
	 * @param dir Direction (NORD, SUD, OUEST, EST)
	 * @param carte map 
	 * @param simulateur simulateur du jeu
	 */
	public void deplacerEffectivement(Direction dir, Carte carte, Simulateur simulateur) {
		Case caseArrivee = carte.getVoisin(positionCourante, dir);
		if (this.has_accessto(caseArrivee.getNature())) {
			double temps = tempsDeplacement(caseArrivee, carte);
			System.out.println(temps);
			long dateToAdd = max((long) 1,(long) (temps) / 5) ; //temps d'attente pour le deplacement
			this.dateArrive = this.dateArrive + dateToAdd;
			simulateur.ajouteEvenement(new EventRobotDeplace(this.dateArrive, dir, this, caseArrivee.getNature()));	
			this.positionCourante = caseArrivee;
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
	public void eteindreIncendie(Simulateur simulateur, Incendie incendie) {
		double reservoir = this.getReservoir();
		double litresAverser = incendie.getIntensiteCourante()- reservoir;
		long dateToAdd = 1;
		if ( litresAverser > 0) {
			dateToAdd = max((long)1,this.tempsEteinte(litresAverser)/5);
		}
		System.out.println("Robot is shuting down the fire, time_needed ---->" + dateToAdd + " steps");
		this.dateArrive =this.dateArrive + dateToAdd;
		simulateur.ajouteEvenement(new EventRobotFire(this.dateArrive, this, simulateur.incendie));
		try {
		if (litresAverser > 0) {
			incendie.setIntensiteCourante(incendie.getIntensiteCourante() - reservoir);
			System.out.println("Il reste " + incendie.getIntensiteCourante() + " pour l'éteindre");
		}
		else {
			System.out.println("c bon");
			incendie.setIntensiteCourante(0);
		}
		}catch(NullPointerException e) {
			System.out.println("La case n'a pas d'incendie");
		}		
	}
	
	
	abstract long tempsEteinte(double litresAverser);
	
	
	/*********Les methodes pour le remplissage d'eau*********/
	/**
	 * Fonction pour remplir le reservoir du robot
	 * @param dateCourante
	 * @param simulateur
	 */
	public void remplirReservoir(Simulateur simulateur) {
		long dateToAdd = max((long)1, this.tempsCharge()/5); //temps d'attente pour le remplissage du reservoir (2 is a placeholder for later)
		System.out.println("Le robot est en train de remplir son reservoir, temps necessaire ----->" + dateToAdd + "steps");
		this.dateArrive = this.dateArrive+ dateToAdd;
		simulateur.ajouteEvenement(new EventRobotCharge(this.dateArrive, this));
		
	}
	
	public void setDat() {
		this.dateArrive = dateArrive;
	}
	
	abstract long tempsCharge();
	
	
	
	/*********Les methodes pour implementer les strategies*********/
	
	/**
	 * Fonction pour que le robot calcule son Path
	 * On n'appelle la fonction que si le robot peut s'y rendre a destination
	 * @param destination
	 * @return Path (LinkedList)
	 */
	public Path calculePath(Case destination){
		Path PathRobot = new Path(this, carte, this.positionCourante, destination);
		return PathRobot;
	}
	

	
	
	/**
	 * Programmen le deplacement du robot vers la destination ou il existe du feu
	 * @param destination  (La destination est la fin du Path, on suppose que ca contient un incendie)
	 * @param simulateur
	 */
	public void programmeEvents(Case destination, Simulateur simulateur) {
		if (!(destination.equals(this.positionCourante))) {
			Path Path = calculePath(destination);
			if (Path.getPath() != null) {
				Iterator<Direction> it = Path.getPath().iterator();
				while(it.hasNext()) {
					this.deplacerEffectivement(it.next(), carte, simulateur);
				}
				//On suppose ici qu'on arrive a une incendie
				}
		}
	}
	
	
	public boolean access(Case destination) {
		if (this.has_accessto(destination.getNature())) {
			if (calculePath(destination) != null) {
				return true;
			}
		}
		return false;
	}
	
	
	
	
	abstract public String returnType();
	
	abstract public double waterBar();

	
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
