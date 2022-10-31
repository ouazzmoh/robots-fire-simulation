import gui.Simulable;
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
	
	/** Interface graphique */
	private GUISimulator gui;
	
	/** Donnees a visualiser */
	private DonneesSimulation donnees;
	
	/** Constructeur, et association a la gui*/
	public Simulateur(GUISimulator gui, DonneesSimulation donnees) {
		this.gui = gui;
		this.donnees = donnees;
		gui.setSimulable(this);
		draw();
		
	}
	
	
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
        xMax -= xMax % 10 + 50;  //50 est la taille de la partie non utile du fenetre
        int yMax = gui.getHeight();
        yMax -= yMax % 10 + 80; // 80 est la taille de la partie non utile du fenetre
        
		int tailleCases_length = (yMax)/nbLig;
		int tailleCases_width = (xMax)/nbCol;
		
//		int yMin = tailleCases_length /2;
//		int xMin = tailleCases_width /2;
	
		/**
		 * Boucler sur les cases, et les dessiner selon la nature du terrain
		 * */
		for (int y = 0; y < nbLig; y++) {
			for (int x = 0; x < nbCol; x++) {
				Case caseCourante = carteToDraw.getCase(x, y);
		        gui.addGraphicalElement(new ImageElement(x*tailleCases_width, y*tailleCases_length, "./images/" + caseCourante.getNature() +".jpeg", tailleCases_width, tailleCases_length, null));
			}
		}
		
		/**
		 * Faire l'incendie
		 */
		
		
		/**
		 * Deployer les robots
		 */
	}
	

	@Override
	public void next() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void restart() {
		// TODO Auto-generated method stub
		
	}
	
}
