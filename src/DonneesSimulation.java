import java.util.Arrays;

public class DonneesSimulation {
	/**
	 * Classe DonneesSimulation, une classe qui regroupe toutes les données dont le simulateur aura besoin, à savoir :
	 * la carte, la taille des cases, le format et les cases (ou encore les natures des terrains)
	 * Informations sur les incendies : leurs positions et leurs intensités
	 * Informations sur les robots : leurs positions, leurs types, vitesse et réservoir
	 */
	private Carte carte;
	private Incendie[] incendies;
	private Robot[] robots;
	/**
	 * Constructeur public de classe, qui crée un objet DonneesSimulation avec tous ses attribus=ts, ce qui est logique
	 * car en l'absence d'un seul attribut il n'aura pas de simulation
	 * @param carte la carte sur laquelle la simulation aura lieu 
	 * @param incendies tableau de TOUTES les incendies dans la carte
	 * @param robots tableau de TOUS les robots dans la carte 
	 */
	public DonneesSimulation(Carte carte, Incendie[] incendies, Robot[] robots) {
		this.carte = carte;
		this.incendies = incendies;
		this.robots = robots;
	}
	
	// Copy Constructor
	public DonneesSimulation(DonneesSimulation donnees) {
		this.carte = new Carte(donnees.carte);
		Incendie[] incendieCopy = new Incendie[donnees.incendies.length];
		Robot[] robotsCopy = new Robot[donnees.robots.length];
		
		//Copying incendie
		for(int i = 0; i < donnees.incendies.length; i++) {
			Case newPosition = new Case(donnees.incendies[i].getPosition().getLigne(), donnees.incendies[i].getPosition().getColonne(), donnees.incendies[i].getPosition().getNature());
			Incendie newIncendie = new Incendie(newPosition, donnees.incendies[i].getIntensite());
			incendieCopy[i] =newIncendie;
		}
		
		//Copying robots
		for(int i = 0; i < donnees.robots.length; i++) {
			robotsCopy[i] =donnees.robots[i].deepCopy();
		}
		
		this.incendies = incendieCopy;
		this.robots = robotsCopy;

		
	}
	public Carte getCarte() {
		return carte;
	}
	public void setCarte(Carte carte) {
		this.carte = carte;
	}
	public Incendie[] getIncendie() {
		return incendies;
	}
	public void setIncendie(Incendie[] incendies) {
		this.incendies = incendies;
	}
	public Incendie getIncendie(int i) {
		return incendies[i];
	}
	public Robot[] getrobot() {
		return robots;
	}
	public void setRobot(Robot[] robots) {
		this.robots = robots;
	}
	public Robot getrobot(int i) {
		return robots[i];
	}
	
	
//	@Override
//	public DonneesSimulation clone() {
//		//
////		Robot[] robotsCopy = Arrays.copyOf(robots, robots.length);
//		
//		DonneesSimulation donneesClone = new DonneesSimulation(carte, incendies, robots);
//		return donneesClone;
//	}
	
}
