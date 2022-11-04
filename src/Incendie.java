import gui.GUISimulator;
import gui.ImageElement;

public class Incendie {
	/**
	 * Classe Incendie, une classe qui nous donne toutes les informations d'une incendie:
	 * ses coordonnées dans la carte
	 * Son intensité (nb de litres nécessaire pour éteindre le feu
	 */
	private Case position;
	private double intensite;
	private Carte carte;
	private GUISimulator gui;
	/**
	 * Constructeur public, qui crée une nouvelle incendie 
	 * @param position case dans laquelle l'incendie se situe dans la carte
	 * @param intensite volume nécessaire pour arrêter l'incendie
	 */
	public Incendie(Case position, double intensite, Carte carte) {
		this.position = position;
		this.intensite = intensite;
		this.carte = carte;
	}
	public String toString() {
		return " l'incendie se trouve dans la case " + position.toString() + " et a besoin de " + intensite 
				+ " litres d'eau pour s'eteindre";
	}
	public Case getPosition(){
		return position;
	}
	public double getIntensite() {
		return intensite;
	}
	public Carte getCarte() {
		return carte;
	}
	public void setGui(GUISimulator gui) {
		this.gui = gui;
	}
	/**
	 * Méthode qui réduit l'intensité d'une incendie grâce à l'aide d'un robot
	 * @param vol volume déversé
	 */
	public void eaudeversee(double vol) {
		intensite -= vol;
	}
	public void setPosition(Case new_position) {
		position = new_position;
	}
	public void setIntensite(double intensite) {
		this.intensite = intensite;
	}
	/**
	 * Méthode qui met à jour l'intensité de l'incendie,
	 * @param vol volume qu'il faut deverser en plus
	 */
	public void updateIntensite(double vol) {
		intensite += vol;
	}

	public void draw(String fireType) {
		int nbLig = carte.getNbLignes();
		int nbCol = carte.getNBColonnes();
		int xMax = gui.getWidth();
        xMax -= xMax % 10 + 50;  //50 est la taille de la partie non utile de la fenetre
        int yMax = gui.getHeight();
        yMax -= yMax % 10 + 80; // 80 est la taille de la partie non utile de la fenetre
        
		int tailleCases_length = (yMax)/nbLig;
		int tailleCases_width = (xMax)/nbCol;
		
		if (intensite != 0) {
			gui.addGraphicalElement(new ImageElement(position.getColonne() * tailleCases_width, position.getLigne() *tailleCases_length, "./images/"+ fireType +".png", tailleCases_width, tailleCases_length, null));
		}
		
	}
	
	public void updateCase(String fireType) {
		int nbLig = carte.getNbLignes();
		int nbCol = carte.getNBColonnes();
        int xMax = gui.getWidth();
        xMax -= xMax % 10 + 50;  //50 est la taille de la partie non utile de la fenetre
        int yMax = gui.getHeight();
        yMax -= yMax % 10 + 80; // 80 est la taille de la partie non utile de la fenetre
		int tailleCases_length = (yMax)/nbLig;
		int tailleCases_width = (xMax)/nbCol;
		//Dessiner le terrain
		gui.addGraphicalElement(new ImageElement(position.getColonne()*tailleCases_width, position.getLigne()*tailleCases_length, "./images/" + position.getNature() +".png", tailleCases_width, tailleCases_length, null));
		//Dessiner les instances qui existe deja sur la place
		if (position.getCurrentRobot() != null) {
			position.getCurrentRobot().draw();
		}
		if (position.getCurrentIncendie() != null) {
			position.getCurrentIncendie().draw(fireType);
		}
	}


	
	
	public void update_case() {
		if (intensite != 0) {
			return;
		}
		
		int nbLig = carte.getNbLignes();
		int nbCol = carte.getNBColonnes();
	
		//Anchor = centre for rectangles and text, topleft for image
		
		
        int xMax = gui.getWidth();
        xMax -= xMax % 10 + 50;  //50 est la taille de la partie non utile de la fenetre
        int yMax = gui.getHeight();
        yMax -= yMax % 10 + 80; // 80 est la taille de la partie non utile de la fenetre
        
		int tailleCases_length = (yMax)/nbLig;
		int tailleCases_width = (xMax)/nbCol;
		int x = position.getColonne();
		int y = position.getLigne();
		gui.addGraphicalElement(new ImageElement(x*tailleCases_width, y*tailleCases_length, "./images/" + position.getNature() +".png", tailleCases_width, tailleCases_length, null));
	}
}
