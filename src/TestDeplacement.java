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
			
			GUISimulator gui = new GUISimulator(500, 500, Color.RED);
			Simulateur simulateur = new Simulateur(gui, donnees);
			
			Robot[] robots = donnees.getrobot();
			Robot robotsTodeplace = robots[1];
			
			//Deplacer le robot in the current date of simulateur
			robotsTodeplace.deplacerEffectivement(Direction.NORD, carteToDraw, 1,simulateur);
			robotsTodeplace.eteindreIncendie(1, simulateur);
			robotsTodeplace.deplacerEffectivement(Direction.OUEST, carteToDraw, 2 ,simulateur);
			robotsTodeplace.deplacerEffectivement(Direction.OUEST, carteToDraw, 3 ,simulateur);
			robotsTodeplace.deplacerEffectivement(Direction.OUEST, carteToDraw, 4 ,simulateur);
			robotsTodeplace.remplirReservoir(4, simulateur);
			robotsTodeplace.deplacerEffectivement(Direction.EST, carteToDraw, 5 ,simulateur);
			robotsTodeplace.deplacerEffectivement(Direction.EST, carteToDraw, 6 ,simulateur);
			robotsTodeplace.deplacerEffectivement(Direction.EST, carteToDraw, 7 ,simulateur);
			robotsTodeplace.eteindreIncendie(7, simulateur);
			robotsTodeplace.deplacerEffectivement(Direction.EST, carteToDraw, 8 ,simulateur);
			robotsTodeplace.eteindreIncendie(9, simulateur);
			System.out.println("TEST");



		}catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        
        }

	}
}
