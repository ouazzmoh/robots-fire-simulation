import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

import gui.GUISimulator;

public class TestDeplacement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {

			DonneesSimulation donnees = NewLecteurDonnees.lire("cartes/carteSujet.map");
			Carte carteToDraw = donnees.getCarte();
			Incendie[] incendieTableau = donnees.getIncendie();
			
			GUISimulator gui = new GUISimulator(500, 500, Color.RED);
			Simulateur simulateur = new Simulateur(gui, donnees, 70, incendieTableau);
			
			Robot[] robots = donnees.getrobot();
			Robot robotsTodeplace = robots[1];
			
			robotsTodeplace.deplacerEffectivement(Direction.NORD, carteToDraw, simulateur);
			simulateur.ajouteEvenement(new EventRobotFire(simulateur.Date + 1, robotsTodeplace, incendieTableau));	
			robotsTodeplace.deplacerEffectivement(Direction.OUEST, carteToDraw, simulateur);
			robotsTodeplace.deplacerEffectivement(Direction.OUEST, carteToDraw, simulateur);
			robotsTodeplace.deplacerEffectivement(Direction.OUEST, carteToDraw, simulateur);
			simulateur.ajouteEvenement(new EventRobotCharge(simulateur.Date + 1, robotsTodeplace));
			robotsTodeplace.deplacerEffectivement(Direction.EST, carteToDraw, simulateur);
			robotsTodeplace.deplacerEffectivement(Direction.EST, carteToDraw, simulateur);
			robotsTodeplace.deplacerEffectivement(Direction.EST, carteToDraw, simulateur);
			simulateur.ajouteEvenement(new EventRobotFire(simulateur.Date + 1, robotsTodeplace, incendieTableau));	



		}catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        
        }

	}
}
