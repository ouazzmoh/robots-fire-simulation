import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

import gui.GUISimulator;
import gui.ImageElement;

public class TestScenario2 {

	public static void main(String[] args) {
		try {
			DonneesSimulation donnees = NewLecteurDonnees.lire("cartes/carteSujet.map");
			Carte carteToDraw = donnees.getCarte();
			Incendie[] incendieTableau = donnees.getIncendie();
			
			int tailleCases = carteToDraw.getTailleCases();
			int tailleCasesPixel = tailleCases / 100;
			int nbLig = carteToDraw.getNbLignes();
			int nbCol = carteToDraw.getNBColonnes();
			
			GUISimulator gui = new GUISimulator(500, 500, Color.RED);
			Simulateur simulateur = new Simulateur(gui, donnees, 40, incendieTableau);
			Robot[] robots = donnees.getrobot();
			
			
			/* scenario 2: Testing movement of multiple robots */
			NatureTerrain nature = NatureTerrain.EAU;
			Robot robotsTodeplace = robots[1];
			Robot robotsTodeplace2 = robots[2];
			simulateur.ajouteEvenement(new EventRobotDeplace(1, "nord", robotsTodeplace, nature));
			simulateur.ajouteEvenement(new EventRobotDeplace(2, "ouest", robotsTodeplace, nature));
			simulateur.ajouteEvenement(new EventRobotDeplace(3, "est", robotsTodeplace, nature));
			simulateur.ajouteEvenement(new EventRobotDeplace(4, "ouest", robotsTodeplace, nature));
			simulateur.ajouteEvenement(new EventRobotDeplace(4, "estt", robotsTodeplace, nature));
			simulateur.ajouteEvenement(new EventRobotDeplace(5, "nord", robotsTodeplace, nature));
			simulateur.ajouteEvenement(new EventRobotDeplace(6, "sud", robotsTodeplace, nature));
			simulateur.ajouteEvenement(new EventRobotDeplace(7, "sud", robotsTodeplace, nature));
			simulateur.ajouteEvenement(new EventRobotDeplace(8, "sud", robotsTodeplace, nature));
			simulateur.ajouteEvenement(new EventRobotDeplace(1, "nord", robotsTodeplace2, nature));
			simulateur.ajouteEvenement(new EventRobotDeplace(2, "ouest", robotsTodeplace2, nature));
				

		}catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        }
	}

}
