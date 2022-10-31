import gui.Simulable;
import gui.GUISimulator;
import gui.Rectangle;
import java.awt.Color;
import gui.Text;

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
		
		
		//Anchor = centre
        int xMax = gui.getWidth();
        xMax -= xMax % 10;
        int yMax = gui.getHeight();
        yMax -= yMax % 10;
        
		int tailleCases_length = (yMax)/nbLig;
		int tailleCases_width = (xMax)/nbCol;
		
		int yMin = tailleCases_length /2;
		int xMin = tailleCases_width /2;
	
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
