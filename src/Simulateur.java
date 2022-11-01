import gui.Simulable;
import gui.Text;
import gui.GUISimulator;
import gui.Rectangle;
import java.awt.Color;
import gui.Text;
import gui.ImageElement;

/**Classe qui implement simulable
 * Le but c'est de visualiser les donnees recu par NewLecteurDonnees
 * Input: donnees = (carte, incendies, robots)
 * Output: visualisation de tout les elements de la simulation**/
public class Simulateur implements Simulable {
	
	private static final boolean TRUE = false;

	/** Interface graphique */
	private GUISimulator gui;
	
	/** Donnees a visualiser */
	private DonneesSimulation donnees;
	
	/** entier qui permet de suivre l'execution des evenements */
	private long dateSimualtion;
	
	/** Liste evenement*/
	Evenement[] Evenements;
	
	/** Constructeur, et association a la gui*/

	public Simulateur(GUISimulator gui, DonneesSimulation donnees, long nbEvenements) {
		this.gui = gui;
		this.donnees = donnees;
		this.dateSimualtion = 1;  /** se renetialise a 0 au debut des evenements : on n'a executer aucun evenement */
		this.Evenements = new Evenement[(int) nbEvenements];
		gui.setSimulable(this);
		draw();
		
	}
	
	public void  incrementeDate() {
		this.dateSimualtion += 1;
	}
	
	public void ajouteEvenement(Evenement e) {
		int count = (int) e.getDate();
		while (Evenements[count] != null) {
			count += 1;
		}
		this.Evenements[count] = e;
	}
	
	public boolean simulationTerminee() {
		return (this.dateSimualtion == Evenements.length);
	}
	/*
	public void executeEvenement() {
		while (!(simulationTerminee())) {
			System.out.println("Next... Current date :" + this.dateSimualtion);
			if (Evenements[(int) this.dateSimualtion] != null) {
				Evenements[(int) this.dateSimualtion].execute();
			}
			incrementeDate();
					
		}
	}*/
	
	/**
	 * Dessiner selon la carte selon la situation de chaque case
	 * */
	private void draw() {
		
		/**
		 * Variables utiles
		 * */
		Carte carteToDraw = donnees.getCarte();
		Incendie[] incendieTableau = donnees.getIncendie();
		Robot[]	robotTableau = donnees.getrobot();
		
		int nbLig = carteToDraw.getNbLignes();
		int nbCol = carteToDraw.getNBColonnes();
		
		
		//Anchor = centre for rectangles and text, topleft for image
		
		
        int xMax = gui.getWidth();
        xMax -= xMax % 10 + 50;  //50 est la taille de la partie non utile de la fenetre
        int yMax = gui.getHeight();
        yMax -= yMax % 10 + 80; // 80 est la taille de la partie non utile de la fenetre
        
		int tailleCases_length = (yMax)/nbLig;
		int tailleCases_width = (xMax)/nbCol;
		
		int yMin = tailleCases_length /2;
		int xMin = tailleCases_width /2;
	
		/**
		 * Boucler sur les cases, et les dessiner selon la nature du terrain
		 * */
		for (int y = 0; y < nbLig; y++) {
			for (int x = 0; x < nbCol; x++) {
				Case caseCourante = carteToDraw.getCase(y, x);
		        gui.addGraphicalElement(new ImageElement(x*tailleCases_width, y*tailleCases_length, "./images/" + caseCourante.getNature() +".png", tailleCases_width, tailleCases_length, null));
			}
		}
		
		/**
		 * Faire l'incendie
		 */
		for (int i = 0; i < incendieTableau.length; i++) {
			Case positionCase = incendieTableau[i].getPosition();
			double intensite = incendieTableau[i].getIntensite();
			int x = positionCase.getColonne();
			int y = positionCase.getLigne();
			gui.addGraphicalElement(new ImageElement(x*tailleCases_width, y*tailleCases_length, "./images/fire.png", tailleCases_width, tailleCases_length, null));
		}
		
		/**
		 * Deployer les robots
		 */
		for (int i = 0; i < robotTableau.length; i++) {
			Case positionCase = robotTableau[i].getPosition();
//			double vitesse = robotTableau[i].getVitesse();
			int x = positionCase.getColonne();
			int y = positionCase.getLigne();
			gui.addGraphicalElement(new ImageElement(x*tailleCases_width, y*tailleCases_length, "./images/robot.png", tailleCases_width, tailleCases_length, null));
		}
	}

	@Override
	public void next() {
		// TODO Auto-generated method stub
		if (!(simulationTerminee())) {
			System.out.println("Next... Current date :" + this.dateSimualtion);
			if (Evenements[(int) this.dateSimualtion] != null) {
				Evenements[(int) this.dateSimualtion].execute();
			}
			incrementeDate();
					
		}
		
	}


	@Override
	public void restart() {
		// TODO Auto-generated method stub
		
	}
	
}
