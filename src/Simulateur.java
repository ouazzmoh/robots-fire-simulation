import gui.Simulable;
import gui.GUISimulator;
import gui.Rectangle;
import java.awt.Color;

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
	
	
	
//	/**
//	 * Initialiser la fenetre en fonction des Donnees de simulation
//	 * */
//private void initGUI() {
//		/**
//		 * Variables utiles
//		 * */
//		Carte carteToDraw = donnees.getCarte();
//		
//		int tailleCases = carteToDraw.getTailleCases();
//		int nbLig = carteToDraw.getNbLignes();
//		int nbCol = carteToDraw.getNBColonnes();
//		
//		this.gui = new GUISimulator(nbCol*tailleCases, nbLig*tailleCases, Color.BLACK, this);
//		
//	}
	
	
	/**
	 * Dessiner selon la carte selon la situation de chaque case
	 * */
	private void draw() {
		
//		initGUI();
		/**
		 * Variables utiles
		 * */
		Carte carteToDraw = donnees.getCarte();
		Incendie[] incendieTableau = donnees.getIncendie();
		Robot[]	robotTableau = donnees.getrobot();
		
		int tailleCases = carteToDraw.getTailleCases();
		int tailleCasesPixel = tailleCases / 100;
		int nbLig = carteToDraw.getNbLignes();
		int nbCol = carteToDraw.getNBColonnes();
	
		/*
		 * Boucler sur les cases et utiliser la bonne couleur
		 * **/
		int count = 0;
		for (int x = 0; x < nbLig; x++) {
			for (int y = 0; y < nbCol; y++) {
				if (count % 2 == 0) {
					gui.addGraphicalElement(new Rectangle(x*tailleCasesPixel, y*tailleCasesPixel , Color.BLUE, Color.BLUE, 1));
				}
				else {
					gui.addGraphicalElement(new Rectangle(x*tailleCasesPixel, y*tailleCasesPixel , Color.BLACK, Color.BLACK, 1));
				}
				
				count++;
			}
		}
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
