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
			
			//Deplacer le robot in the current date of simulateur
			robotsTodeplace.deplacerEffectivement(Direction.NORD, carteToDraw, simulateur.getDateSimulation() ,simulateur);
			//
//			simulateur.ajouteEvenement(new EventRobotFire(simulateur.getDateSimulation() + 1, robotsTodeplace, incendieTableau));
			
			robotsTodeplace.deplacerEffectivement(Direction.OUEST, carteToDraw, simulateur.getDateSimulation()+1 ,simulateur);
			System.out.println(simulateur.getDateSimulation());
			robotsTodeplace.deplacerEffectivement(Direction.OUEST, carteToDraw, simulateur.getDateSimulation()+2 ,simulateur);
			robotsTodeplace.deplacerEffectivement(Direction.OUEST, carteToDraw, simulateur.getDateSimulation()+3 ,simulateur);
//			simulateur.ajouteEvenement(new EventRobotCharge(simulateur.getDateSimulation()  + 1, robotsTodeplace));
			robotsTodeplace.deplacerEffectivement(Direction.EST, carteToDraw, simulateur.getDateSimulation()+4 ,simulateur);
			robotsTodeplace.deplacerEffectivement(Direction.EST, carteToDraw, simulateur.getDateSimulation()+5 ,simulateur);
			robotsTodeplace.deplacerEffectivement(Direction.EST, carteToDraw, simulateur.getDateSimulation()+6 ,simulateur);
//			simulateur.ajouteEvenement(new EventRobotFire(simulateur.getDateSimulation()  + 1, robotsTodeplace, incendieTableau));	



		}catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        
        }

	}
}
