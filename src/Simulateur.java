import gui.Simulable;
import gui.Text;
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
		
		int nbLig = carteToDraw.getNbLignes();
		int nbCol = carteToDraw.getNBColonnes();
		int width = gui.getWidth();
		int length = gui.getHeight();
		
        int xMin = 60;
        int yMin = 40;
        int xMax = gui.getWidth() - xMin;
        xMax -= xMax % 10;
        int yMax = gui.getHeight() - yMin;
        yMax -= yMax % 10;
        
		int tailleCases_length = (yMax-yMin)/nbLig;
		int tailleCases_width = (xMax-xMin)/nbCol;
		

        
		/*
		 * Boucler sur les cases et utiliser la bonne couleur
		 * **/
		int count = 0;
		for (int y = 0; y < nbLig; y++) {
			for (int x = 0; x < nbCol; x++) {
				if (count % 2 == 0) {
					gui.addGraphicalElement(new Rectangle(x*tailleCases_width + xMin, y*tailleCases_length + yMin , Color.WHITE, Color.BLUE, tailleCases_width, tailleCases_length));
					
				}
				else {
					gui.addGraphicalElement(new Rectangle(x*tailleCases_width + xMin, y*tailleCases_length + yMin , Color.WHITE, Color.BLACK, tailleCases_width, tailleCases_length));
				}
				
				count++;
				Case caseCourante = carteToDraw.getCase(x, y);
				String text = "" + caseCourante.getNature();
		        gui.addGraphicalElement(new Text(x*tailleCases_width + xMin, y*tailleCases_length + yMin, Color.GREEN, text));

			}
		}
		
		
		for (int index = 0; index<incendieTableau.length; index++) {
			Incendie incendieCourante = incendieTableau[index];
			Case caseIncendie = incendieCourante.getPosition();
	        gui.addGraphicalElement(new Text(caseIncendie.getColonne()*tailleCases_width + xMin + 10, caseIncendie.getLigne()*tailleCases_length + yMin + 10, Color.RED, "FEU"));
			
		}
		
		for (int index = 0; index<robotTableau.length; index++) {
			Robot robotCourant = robotTableau[index];
			Case caseRobot = robotCourant.getPosition();
	        gui.addGraphicalElement(new Text(caseRobot.getColonne()*tailleCases_width + xMin + 30, caseRobot.getLigne()*tailleCases_length + yMin + 30, Color.WHITE, "ROBOT"));
			
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
