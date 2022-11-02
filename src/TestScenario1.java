import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

import gui.GUISimulator;
import gui.ImageElement;

public class TestScenario1 {

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
			
			
			/* scenario 1*/
			NatureTerrain nature = NatureTerrain.EAU;
			Robot robotsTodeplace = robots[1];
			int i = 2;
			simulateur.ajouteEvenement(new EventRobotDeplace(i, Direction.NORD, robotsTodeplace, nature));
			simulateur.ajouteEvenement(new EventRobotFire(i, robotsTodeplace, incendieTableau));	
			simulateur.ajouteEvenement(new EventRobotDeplace(i, Direction.OUEST, robotsTodeplace, nature));
			simulateur.ajouteEvenement(new EventRobotDeplace(i, Direction.OUEST, robotsTodeplace, nature));
			simulateur.ajouteEvenement(new EventRobotDeplace(i, Direction.OUEST, robotsTodeplace, nature));
			simulateur.ajouteEvenement(new EventRobotCharge(i, robotsTodeplace));
			simulateur.ajouteEvenement(new EventRobotDeplace(i, Direction.EST, robotsTodeplace, nature));
			simulateur.ajouteEvenement(new EventRobotDeplace(i, Direction.EST, robotsTodeplace, nature));
			simulateur.ajouteEvenement(new EventRobotDeplace(i, Direction.EST, robotsTodeplace, nature));
			simulateur.ajouteEvenement(new EventRobotFire(i, robotsTodeplace, incendieTableau));
			robotsTodeplace = robots[2];
			i = 3;
			simulateur.ajouteEvenement(new EventRobotDeplace(i, Direction.SUD, robotsTodeplace, nature));
			simulateur.ajouteEvenement(new EventRobotDeplace(i, Direction.SUD, robotsTodeplace, nature));
			simulateur.ajouteEvenement(new EventRobotDeplace(i, Direction.SUD, robotsTodeplace, nature));
			simulateur.ajouteEvenement(new EventRobotFire(i, robotsTodeplace, incendieTableau));	

		}catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        }
	}

}
