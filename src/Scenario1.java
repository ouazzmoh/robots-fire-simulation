import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.zip.DataFormatException;

import gui.GUISimulator;

public class Scenario1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {

			DonneesSimulation donnees = NewLecteurDonnees.lire("cartes/desertOfDeath-20x20.map");
			Carte carteToDraw = donnees.getCarte();
			Incendie[] incendieTableau = donnees.getIncendie();
			
			GUISimulator gui = new GUISimulator(500, 500, Color.RED);
			Simulateur simulateur = new Simulateur(gui, donnees, 70, incendieTableau);
			
			Robot[] robots = donnees.getrobot();
			Incendie[] incendie = donnees.getIncendie();
			
			Robot robot = robots[2];
			LinkedList<Direction> chemin = robot.Chemin(carteToDraw.getCase(0, 0), carteToDraw);
			for (int i = 0 ; i < robot.indiceTest; i++) {
				Direction casee = chemin.get(i);
				robot.deplacerEffectivement(casee, carteToDraw, i+1,simulateur);

			}

		}catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        
        }

	}
}
