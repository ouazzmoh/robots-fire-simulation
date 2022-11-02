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
			
			simulateur.ajouteEvenement(new EventRobotDeplace(simulateur.Date, Direction.NORD, robotsTodeplace, nature));

			simulateur.ajouteEvenement(new EventRobotFire(simulateur.Date + 1, robotsTodeplace, incendieTableau));	
			simulateur.ajouteEvenement(new EventRobotDeplace(simulateur.Date + 1, Direction.OUEST, robotsTodeplace, nature));
			simulateur.ajouteEvenement(new EventRobotDeplace(simulateur.Date + 1, Direction.OUEST, robotsTodeplace, nature));
			simulateur.ajouteEvenement(new EventRobotDeplace(simulateur.Date + 1, Direction.OUEST, robotsTodeplace, nature));
			simulateur.ajouteEvenement(new EventRobotCharge(simulateur.Date + 1, robotsTodeplace));
			simulateur.ajouteEvenement(new EventRobotDeplace(simulateur.Date + 1, Direction.EST, robotsTodeplace, nature));
			simulateur.ajouteEvenement(new EventRobotDeplace(simulateur.Date + 1, Direction.EST, robotsTodeplace, nature));
			simulateur.ajouteEvenement(new EventRobotDeplace(simulateur.Date + 1, Direction.EST, robotsTodeplace, nature));
			simulateur.ajouteEvenement(new EventRobotFire(simulateur.Date + 1, robotsTodeplace, incendieTableau));
			robotsTodeplace = robots[2];
			
			simulateur.ajouteEvenement(new EventRobotDeplace(simulateur.Date + 1, Direction.SUD, robotsTodeplace, nature));
			simulateur.ajouteEvenement(new EventRobotDeplace(simulateur.Date + 1, Direction.SUD, robotsTodeplace, nature));
			simulateur.ajouteEvenement(new EventRobotDeplace(simulateur.Date + 1, Direction.SUD, robotsTodeplace, nature));

			simulateur.ajouteEvenement(new EventRobotFire(simulateur.Date + 1, robotsTodeplace, incendieTableau));	

		}catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        }
	}

}
